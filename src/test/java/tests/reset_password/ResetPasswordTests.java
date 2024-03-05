package tests.reset_password;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.reset_password.ResetPasswordPage;
import pages.home.HomePage;
import pages.login_user.LoginPage;
import tests.AbstractTest;
import utils.User;

public class ResetPasswordTests extends AbstractTest {

    @DisplayName("Check the functionality of resetting password")
    @Test
    void resetPasswordTest() {
        String expectedMessageText = "you will receive an email with a link to reset your password.";

        LoginPage loginPage = new HomePage()
                .openSignInPage();

        ResetPasswordPage resetPasswordPage = loginPage
                .clickForgotPassword();

        loginPage = resetPasswordPage
                .enterEmail(User.getEmail())
                .clickResetPassword();

        loginPage
                .checkResetPasswordMessage(expectedMessageText);
    }

    @DisplayName("Check the functionality of resetting password - negative case (invalid email)")
    @Test
    void resetPasswordTestInvalidEmail() {
        String expectedMessageText = "Please enter a valid email address (Ex: johndoe@domain.com).";

        LoginPage loginPage = new HomePage()
                .openSignInPage();

        ResetPasswordPage resetPasswordPage = loginPage
                .clickForgotPassword();

        resetPasswordPage
                .enterEmail("user" + RandomStringUtils.randomNumeric(5) + "gmail.com")
                .clickResetPasswordNegative()
                .checkEmailErrorMessage(expectedMessageText);
    }

    @DisplayName("Check the functionality of resetting password - negative case (email field is empty)")
    @Test
    void resetPasswordTestEmailFieldIsEmpty() {
        String expectedMessageText = "This is a required field.";

        LoginPage loginPage = new HomePage()
                .openSignInPage();

        ResetPasswordPage resetPasswordPage = loginPage
                .clickForgotPassword();

        resetPasswordPage
                .clickResetPasswordNegative()
                .checkEmailErrorMessage(expectedMessageText);
    }
}
