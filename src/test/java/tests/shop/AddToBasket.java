package tests.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.ShippingAddressModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.home.HomePage;
import pages.login_user.LoginPage;
import pages.register_user.UserData;
import pages.shop.GoodsCategoryPage;
import pages.shop.components.PaymentComponent;
import pages.shop.components.ShippingAddressComponent;
import tests.AbstractTest;
import utils.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.SneakyThrows;

public class AddToBasket extends AbstractTest {
    String password = RandomStringUtils.randomAlphabetic(10) + RandomStringUtils.randomNumeric(5) + "*?%;";
    String email = "user" + RandomStringUtils.randomNumeric(5) + "@gmail.com";
    UserData uD = new UserData("XbW2plyKhAPX8vVK", "", "", "Alex", "Black", email, password, password);

    @Test @SneakyThrows
    public void registerUser() {
        ObjectMapper mapper = new ObjectMapper();
        RestAssured.given()
                .baseUri("https://magento.softwaretestingboard.com/customer/account/createpost/")
                .contentType(ContentType.TEXT)
                .body(mapper.writeValueAsString(uD))
                .when().post().then()
                .statusCode(302);
    }


    @DisplayName("Check that When user add item to the basket, it's correct price is seen on checkout ")
    @Test
    void addItemToTheBasket() {
        String navItemId = "ui-id-5";
        String subcategoryId = "ui-id-17";
        String typeOfClothingId = "ui-id-19";
        String itemName = "Proteus Fitness Jackshirt";
        String itemSize = "M";
        String itemColor = "Blue";

        ShippingAddressModel addresFields = ShippingAddressModel.builder()
                .firstname(RandomStringUtils.randomAlphabetic(5))
                .lastname(RandomStringUtils.randomAlphabetic(5))
                .streetAddress(RandomStringUtils.randomAlphabetic(5))
                .city(RandomStringUtils.randomAlphabetic(5))
                .stateOrProvince("Florida")
                .postcode(RandomStringUtils.randomNumeric(5))
                .country("United States")
                .phoneNumber(RandomStringUtils.randomNumeric(5))
                .build();

        LoginPage loginPage = new HomePage()
                .openSignInPage();

        HomePage homePage = loginPage
                .loggingIn(email, password);

        GoodsCategoryPage goodsCategoryPage = homePage.goToGoodsCategoryPage(navItemId, subcategoryId, typeOfClothingId);

        goodsCategoryPage.addItemToTheBasket(itemName, itemSize, itemColor);

        goodsCategoryPage.checkSuccessfulAddingOfItemMessage(itemName);

        ShippingAddressComponent shippingAddressComponent = goodsCategoryPage.proceedToCheckoutPage();

        PaymentComponent paymentComponent = shippingAddressComponent
                .enterShippingAddress(addresFields)
                .clickFirstShippingMethodButton()
                .clickNextBtn();

        paymentComponent.checkСardSubtotalSum(goodsCategoryPage.choosenItemPrice);

        homePage = paymentComponent.GoToHomePage();

        homePage.deleteOrdersFromBusket();
    }
}
