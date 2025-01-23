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

public class ChangeEmailTests extends AbstractTest {

    @DisplayName("Check the functionality of changing user email")
    @Test
    void changeEmailTest() {
        String expectedMessageText = "You saved the account information.";
        String email = "user1234@gmail.com";

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
                .getChangeEmailComponent()
                .enterEmail(email)
                .enterCurrentPassword(User.getPassword());

        loginPage = editAccountInformationPage
                .clickSaveBtnAfterMailChange();

        loginPage.checkSuccessfulChangeOfEmailMessage(expectedMessageText);

        myAccountPage = loginPage
                .loggingInAfterChangeOfEmail("user1234@gmail.com", User.getPassword());

        myAccountPage.checkAccountInformation(email);

        editAccountInformationPage = myAccountPage
                .clickAccountInformation();

        editAccountInformationPage
                .clickChangeEmailCheckbox()
                .getChangeEmailComponent()
                .enterEmail("bychkov.roman17@yandex.ru")
                .enterCurrentPassword(User.getPassword());

        loginPage = editAccountInformationPage
                .clickSaveBtnAfterMailChange();

        loginPage.checkSuccessfulChangeOfEmailMessage(expectedMessageText);
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
                .getChangeEmailComponent()
                .enterEmail("user" + RandomStringUtils.randomNumeric(5) + "gmail.com");

        editAccountInformationPage
                .clickSaveBtnNegative();

        editAccountInformationPage
                .getChangeEmailComponent()
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
                .getChangeEmailComponent()
                .clearEmailField();

        editAccountInformationPage
                .clickSaveBtnNegative();

        editAccountInformationPage
                .getChangeEmailComponent()
                .checkEmailErrorMessage(expectedMessageText);
    }

    @DisplayName("Check the functionality of changing user email - negative case (invalid password)")
    @Test
    void changeEmailTestInvalidPassword() {
        String expectedMessageText = "The password doesn't match this account. Verify the password and try again.";

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
                .getChangeEmailComponent()
                .enterEmail("autotest1234@gmail.com")
                .enterCurrentPassword(RandomStringUtils.randomAlphabetic(6));

        editAccountInformationPage
                .clickSaveBtnNegative()
                .checkInvalidPasswordErrorMessage(expectedMessageText);
    }

    @DisplayName("Check the functionality of changing user email - negative case (password field is empty)")
    @Test
    void changeEmailTestPasswordFieldIsEmpty() {
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
                .getChangeEmailComponent()
                .enterEmail("autotest1234@gmail.com")
                .clearCurrentPassword();

        editAccountInformationPage
                .clickSaveBtnNegative();

        editAccountInformationPage
                .getChangeEmailComponent()
                .checkPasswordIsAbsentErrorMessage(expectedMessageText);
    }
}
