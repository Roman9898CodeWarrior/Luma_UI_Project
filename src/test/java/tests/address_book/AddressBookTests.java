package tests.address_book;

import models.ShippingAddressModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.address_book.EditAddressPage;
import pages.home.HomePage;
import pages.login_user.LoginPage;
import pages.login_user.MyAccountPage;
import pages.address_book.AddressBookDetailsPage;
import tests.AbstractTest;
import utils.User;

public class AddressBookTests extends AbstractTest {

    @DisplayName("Check the functionality of changing user address")
    @Test
    void changeAddressTest() {
        ShippingAddressModel fields = ShippingAddressModel.builder()
                .streetAddress(RandomStringUtils.randomAlphabetic(5))
                .city(RandomStringUtils.randomAlphabetic(5))
                .postcode(RandomStringUtils.randomNumeric(5))
                .country("United States")
                .phoneNumber(RandomStringUtils.randomNumeric(5))
                .build();

        LoginPage loginPage = new HomePage()
                .openSignInPage();

        HomePage homePage = loginPage
                .loggingIn(User.getEmail(), User.getPassword());

        MyAccountPage myAccountPage = homePage.clickMyAccount();

        AddressBookDetailsPage addressBookDetailsPage = myAccountPage.clickAddressBook();

        EditAddressPage editAddressPage = addressBookDetailsPage.clickingChangeBillingAddress();

        addressBookDetailsPage = editAddressPage
                .enterPhoneNumber(fields.getPhoneNumber())
                .enterStreetAddress(fields.getStreetAddress())
                .enterCity(fields.getCity())
                .enterPostcode(fields.getPostcode())
                .selectCountry(fields.getCountry())
                .clickSaveBtn();

        addressBookDetailsPage.checkChangedBillingAddress(fields);
    }

    @DisplayName("Check the functionality of changing user address - negative case (street field is empty)")
    @Test
    void changeAddressTestStreetFieldIsEmpty() {
        ShippingAddressModel fields = ShippingAddressModel.builder()
                .city(RandomStringUtils.randomAlphabetic(5))
                .postcode(RandomStringUtils.randomNumeric(5))
                .country("United States")
                .phoneNumber(RandomStringUtils.randomNumeric(5))
                .build();
        String addressError = "This is a required field.";

        LoginPage loginPage = new HomePage()
                .openSignInPage();

        HomePage homePage = loginPage
                .loggingIn(User.getEmail(), User.getPassword());

        MyAccountPage myAccountPage = homePage.clickMyAccount();

        AddressBookDetailsPage addressBookDetailsPage = myAccountPage.clickAddressBook();

        EditAddressPage editAddressPage = addressBookDetailsPage.clickingChangeBillingAddress();

        editAddressPage
                .enterPhoneNumber(fields.getPhoneNumber())
                .clearStreetAddress()
                .enterCity(fields.getCity())
                .enterPostcode(fields.getPostcode())
                .selectCountry(fields.getCountry())
                .clickSaveBtnNegative()
                .checkStreetAddressError(addressError);
    }
}
