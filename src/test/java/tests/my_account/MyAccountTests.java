package tests.my_account;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.home.HomePage;
import pages.login_user.LoginPage;
import pages.login_user.MyAccountPage;
import pages.my_account.EditAccountInformationPage;
import tests.AbstractTest;
import utils.User;

public class MyAccountTests extends AbstractTest {

    @DisplayName("Check the functionality of changing user lastname")
    @Test
    void changeLastnameTest() {
        String lastname = "Autotest" + RandomStringUtils.randomNumeric(7);

        LoginPage loginPage = new HomePage()
                .openSignInPage();

        HomePage homePage = loginPage
                .loggingIn(User.getEmail(), User.getPassword());

        MyAccountPage myAccountPage = homePage
                .clickMyAccount();

        EditAccountInformationPage editAccountInformationPage = myAccountPage
                .clickAccountInformation();

        myAccountPage = editAccountInformationPage
                .enterLastname(lastname)
                .clickSaveBtn();

        myAccountPage.checkAccountInformation(lastname);
    }

    @DisplayName("Check the functionality of changing user email - negative case (invalid email)")
    @Test
    void changeEmailTestInvalidEmail() {
        String expectedMessageText = "Please enter a valid email address.";

        LoginPage loginPage = new HomePage()
                .openSignInPage();

        HomePage homePage = loginPage
                .loggingIn(User.getEmail(), User.getPassword());

        MyAccountPage myAccountPage = homePage
                .clickMyAccount();

        EditAccountInformationPage editAccountInformationPage = myAccountPage
                .clickAccountInformation();

        editAccountInformationPage
                .clickChangeEmailCheckbox()
                .enterEmail("user" + RandomStringUtils.randomNumeric(5) + "gmail.com")
                .clickSaveBtnNegative()
                .checkEmailErrorMessage(expectedMessageText);
    }

    @DisplayName("Check the functionality of changing user email - negative case (email field is empty)")
    @Test
    void changeEmailTestEmailFieldIsEmpty() {
        String expectedMessageText = "This is a required field.";

        LoginPage loginPage = new HomePage()
                .openSignInPage();

        HomePage homePage = loginPage
                .loggingIn(User.getEmail(), User.getPassword());

        MyAccountPage myAccountPage = homePage
                .clickMyAccount();

        EditAccountInformationPage editAccountInformationPage = myAccountPage
                .clickAccountInformation();

        editAccountInformationPage
                .clickChangeEmailCheckbox()
                .clearEmailField()
                .clickSaveBtnNegative()
                .checkEmailErrorMessage(expectedMessageText);
    }
}
