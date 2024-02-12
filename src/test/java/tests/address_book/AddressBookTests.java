package tests.address_book;

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
        String phoneNumber = RandomStringUtils.randomNumeric(7);
        String streetAddress = "Seongdong-gu Haengdang-dong 19-109";
        String city = "Seoul";
        String zip = RandomStringUtils.randomNumeric(4);
        String country = "South Korea";

        LoginPage loginPage = new HomePage()
                .openSignInPage();

        HomePage homePage = loginPage
                .loggingIn(User.getEmail(), User.getPassword());

        MyAccountPage myAccountPage = homePage.clickMyAccount();

        AddressBookDetailsPage addressBookDetailsPage = myAccountPage.clickAddressBook();

        EditAddressPage editAddressPage = addressBookDetailsPage.clickingChangeBillingAddress();

        addressBookDetailsPage = editAddressPage
                .enterPhoneNumber(phoneNumber)
                .enterStreetAddress(streetAddress)
                .enterCity(city)
                .enterZip(zip)
                .selectCountry(country)
                .clickSaveBtn();

        addressBookDetailsPage.checkChangedBillingAddress(phoneNumber, streetAddress, city, zip, country);
    }

    @DisplayName("Check the functionality of changing user address - negative case (street field is empty)")
    @Test
    void changeAddressTestStreetFieldIsEmpty() {
        String phoneNumber = RandomStringUtils.randomNumeric(7);
        String city = "Seoul";
        String zip = RandomStringUtils.randomNumeric(4);
        String country = "South Korea";
        String addressError = "This is a required field.";

        LoginPage loginPage = new HomePage()
                .openSignInPage();

        HomePage homePage = loginPage
                .loggingIn(User.getEmail(), User.getPassword());

        MyAccountPage myAccountPage = homePage.clickMyAccount();

        AddressBookDetailsPage addressBookDetailsPage = myAccountPage.clickAddressBook();

        EditAddressPage editAddressPage = addressBookDetailsPage.clickingChangeBillingAddress();

        editAddressPage
                .enterPhoneNumber(phoneNumber)
                .clearStreetAddress()
                .enterCity(city)
                .enterZip(zip)
                .selectCountry(country)
                .clickSaveBtnNegative()
                .checkStreetAddressError(addressError);
    }
}
