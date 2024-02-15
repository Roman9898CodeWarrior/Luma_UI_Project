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

public class ChangePasswordTests extends AbstractTest {

    @DisplayName("Check the functionality of changing user password - negative case (Confirm New Password field is empty)")
    @Test
    void changePasswordTestConfirmPasswordFieldIsEmpty() {
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
                .clickChangePasswordCheckbox()
                .getChangePasswordComponent()
                .enterPassword("autotest!%$" + RandomStringUtils.randomNumeric(3));

        editAccountInformationPage
                .clickSaveBtnNegative();

        editAccountInformationPage
                .getChangePasswordComponent()
                .checkPasswordConfirmationErrorMessage(expectedMessageText);
    }

    @DisplayName("Check the functionality of changing user password - negative case (insufficient password complexity)")
    @Test
    void changePasswordTestInsufficientPasswordComplexity() {
        String expectedMessageText = "Minimum of different classes of characters in password is 3. Classes of characters: Lower Case, Upper Case, Digits, Special Characters.";
        String password = RandomStringUtils.randomNumeric(5) + "*?%;";

        LoginPage loginPage = new HomePage()
                .openSignInPage();

        HomePage homePage = loginPage
                .loggingIn(User.getEmail(), User.getPassword());

        MyAccountPage myAccountPage = homePage
                .clickMyAccount();

        EditAccountInformationPage editAccountInformationPage = myAccountPage
                .clickAccountInformation();

        editAccountInformationPage
                .clickChangePasswordCheckbox()
                .getChangePasswordComponent()
                .enterPassword(password)
                .enterPasswordConfirmation(password);

        editAccountInformationPage
                .clickSaveBtnNegative();

        editAccountInformationPage
                .getChangePasswordComponent()
                .checkPasswordErrorMessage(expectedMessageText);
    }

    @DisplayName("Check the functionality of changing user password - negative case (password length is less than 8 symbols)")
    @Test
    void changePasswordTestPasswordTooShort() {
        String expectedMessageText = "Minimum length of this field must be equal or greater than 8 symbols. Leading and trailing spaces will be ignored.";
        String password = RandomStringUtils.randomNumeric(5);

        LoginPage loginPage = new HomePage()
                .openSignInPage();

        HomePage homePage = loginPage
                .loggingIn(User.getEmail(), User.getPassword());

        MyAccountPage myAccountPage = homePage
                .clickMyAccount();

        EditAccountInformationPage editAccountInformationPage = myAccountPage
                .clickAccountInformation();

        editAccountInformationPage
                .clickChangePasswordCheckbox()
                .getChangePasswordComponent()
                .enterPassword(password)
                .enterPasswordConfirmation(password);

        editAccountInformationPage
                .clickSaveBtnNegative();

        editAccountInformationPage
                .getChangePasswordComponent()
                .checkPasswordErrorMessage(expectedMessageText);
    }

    @DisplayName("Check the functionality of changing user password - negative case (invalid password confirmation)")
    @Test
    void changePasswordTestInvalidPasswordConfirmation() {
        String expectedMessageText = "Please enter the same value again.";

        LoginPage loginPage = new HomePage()
                .openSignInPage();

        HomePage homePage = loginPage
                .loggingIn(User.getEmail(), User.getPassword());

        MyAccountPage myAccountPage = homePage
                .clickMyAccount();

        EditAccountInformationPage editAccountInformationPage = myAccountPage
                .clickAccountInformation();

        editAccountInformationPage
                .clickChangePasswordCheckbox()
                .getChangePasswordComponent()
                .enterPassword(RandomStringUtils.randomAlphabetic(10) + RandomStringUtils.randomNumeric(5) + "*?%;")
                .enterPasswordConfirmation(RandomStringUtils.randomAlphabetic(10) + RandomStringUtils.randomNumeric(5) + "*?%;");

        editAccountInformationPage
                .clickSaveBtnNegative();

        editAccountInformationPage
                .getChangePasswordComponent()
                .checkPasswordConfirmationErrorMessage(expectedMessageText);
    }
}
