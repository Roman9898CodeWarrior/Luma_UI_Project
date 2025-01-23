package tests.shop;

import models.ShippingAddressModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.home.HomePage;
import pages.login_user.LoginPage;
import pages.shop.GoodsCategoryPage;
import pages.shop.components.PaymentComponent;
import pages.shop.components.ShippingAddressComponent;
import tests.AbstractTest;
import utils.User;

public class AddToBasket extends AbstractTest {

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
                .loggingIn(User.getEmail(), User.getPassword());

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
