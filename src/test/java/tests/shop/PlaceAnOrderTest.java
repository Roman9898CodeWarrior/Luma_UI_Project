package tests.shop;

import models.ShippingAddressModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.home.HomePage;
import pages.login_user.LoginPage;
import pages.shop.CartPage;
import pages.shop.MenBottomsPage;
import pages.shop.OrderDetailsPage;
import pages.shop.ThankYouForPurchasePage;
import tests.AbstractTest;
import utils.User;

public class PlaceAnOrderTest extends AbstractTest {

    @DisplayName("Check the functionality of placing an order")
    @Test
    void placeOrderTest() {
        String pageTitle= "Thank you for your purchase!";
        String orderMessage= "We'll email you an order confirmation with details and tracking info.";

        LoginPage loginPage = new HomePage()
                .openSignInPage();

        HomePage homePage = loginPage
                .loggingIn(User.getEmail(), User.getPassword());

        MenBottomsPage menBottomsPage = homePage
                .clickMenBottoms();

        CartPage cartPage = menBottomsPage
                .addFirstItemToCart()
                .addSecondItemToCart()
                .goToCart();

        OrderDetailsPage orderDetailsPage = cartPage.clickProceedToCheckout();

        orderDetailsPage
                .getShippingAddressComponent()
                .clickNextBtn();

        ThankYouForPurchasePage thankYouForPurchasePage = orderDetailsPage
                .getPaymentComponent()
                .clickPlaceOrderBtn();

        thankYouForPurchasePage.checkPageTitle(pageTitle);

        thankYouForPurchasePage.checkOrderMessage(orderMessage);
    }

    @DisplayName("Check the functionality of placing an order and adding new shipping address")
    @Test
    void addNewShippingAddressTest() {
        String pageTitle= "Thank you for your purchase!";
        String orderMessage= "We'll email you an order confirmation with details and tracking info.";
        ShippingAddressModel fields = ShippingAddressModel.builder()
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

        MenBottomsPage menBottomsPage = homePage
                .clickMenBottoms();

        CartPage cartPage = menBottomsPage
                .addFirstItemToCart()
                .addSecondItemToCart()
                .goToCart();

        OrderDetailsPage orderDetailsPage = cartPage.clickProceedToCheckout();

        orderDetailsPage
                .getShippingAddressComponent()
                .clickAddAddressBtn()
                .enterShippingAddress(fields)
                .checkSaveInAddressBookCheckbox()
                .clickShipHereBtn()
                .clickNextBtn();

        ThankYouForPurchasePage thankYouForPurchasePage = orderDetailsPage
                .getPaymentComponent()
                .clickPlaceOrderBtn();

        thankYouForPurchasePage.checkPageTitle(pageTitle);

        thankYouForPurchasePage.checkOrderMessage(orderMessage);
    }

    @DisplayName("Check the functionality of placing an order and adding new shipping address - negative case (city field is empty)")
    @Test
    void addNewShippingAddressCityFieldIsEmptyTest() {
        String pageTitle= "Thank you for your purchase!";
        String orderMessage= "We'll email you an order confirmation with details and tracking info.";
        String errorMessage = "This is a required field.";
        ShippingAddressModel fields = ShippingAddressModel.builder()
                .firstname(RandomStringUtils.randomAlphabetic(5))
                .lastname(RandomStringUtils.randomAlphabetic(5))
                .streetAddress(RandomStringUtils.randomAlphabetic(5))
                .city("")
                .stateOrProvince("Florida")
                .postcode(RandomStringUtils.randomNumeric(5))
                .country("United States")
                .phoneNumber(RandomStringUtils.randomNumeric(5))
                .build();

        LoginPage loginPage = new HomePage()
                .openSignInPage();

        HomePage homePage = loginPage
                .loggingIn(User.getEmail(), User.getPassword());

        MenBottomsPage menBottomsPage = homePage
                .clickMenBottoms();

        CartPage cartPage = menBottomsPage
                .addFirstItemToCart()
                .addSecondItemToCart()
                .goToCart();

        OrderDetailsPage orderDetailsPage = cartPage.clickProceedToCheckout();

        orderDetailsPage
                .getShippingAddressComponent()
                .clickAddAddressBtn()
                .enterShippingAddress(fields)
                .checkSaveInAddressBookCheckbox()
                .clickShipHereBtnNegative()
                .checkCityFieldIsEmptyErrorMessage(errorMessage);

        orderDetailsPage
                .getShippingAddressComponent()
                .enterCity("Seoul")
                .checkSaveInAddressBookCheckbox()
                .clickShipHereBtn()
                .clickNextBtn();

        ThankYouForPurchasePage thankYouForPurchasePage = orderDetailsPage
                .getPaymentComponent()
                .clickPlaceOrderBtn();

        thankYouForPurchasePage.checkPageTitle(pageTitle);

        thankYouForPurchasePage.checkOrderMessage(orderMessage);
    }

    @DisplayName("Check the functionality of placing an order")
    @Test
    void placeOrderInvalidDiscountCodeTest() {
        String pageTitle= "Thank you for your purchase!";
        String orderMessage= "We'll email you an order confirmation with details and tracking info.";
        String discountCode= "1";
        String errorMessage= "The coupon code isn't valid. Verify the code and try again.";

        LoginPage loginPage = new HomePage()
                .openSignInPage();

        HomePage homePage = loginPage
                .loggingIn(User.getEmail(), User.getPassword());

        MenBottomsPage menBottomsPage = homePage
                .clickMenBottoms();

        CartPage cartPage = menBottomsPage
                .addFirstItemToCart()
                .addSecondItemToCart()
                .goToCart();

        OrderDetailsPage orderDetailsPage = cartPage
                .clickProceedToCheckout();

        orderDetailsPage
                .getShippingAddressComponent()
                .clickNextBtn();

        orderDetailsPage
                .getPaymentComponent()
                .applyInvalidDiscountCode(discountCode)
                .checkInvalidDiscountCodeErrorMessage(errorMessage);

        ThankYouForPurchasePage thankYouForPurchasePage = orderDetailsPage
                .getPaymentComponent()
                .clickPlaceOrderBtn();

        thankYouForPurchasePage.checkPageTitle(pageTitle);

        thankYouForPurchasePage.checkOrderMessage(orderMessage);
    }
}
