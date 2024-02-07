package tests.login_user;

import org.apache.commons.lang3.RandomStringUtils;
import utils.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.home.HomePage;
import pages.login_user.LoginPage;
import pages.login_user.MyAccountPage;
import tests.AbstractTest;

public class LoginTests extends AbstractTest {

    @DisplayName("Check the functionality of user login")
    @Test
    void loginUserTest() {
        String expectedMessageText = "Welcome, AutotestFirstName";

        LoginPage loginPage = new HomePage()
                .openSignInPage();

        MyAccountPage myAccountPage = loginPage
                .enterEmail(User.getEmail())
                .enterPassword(User.getPassword())
                .clickSignInBtn();

        myAccountPage.checkUserFirstName(expectedMessageText);
    }


    @DisplayName("Check the functionality of user login - negative case (invalid email)")
    @Test
    void loginUserTestInvalidEmail() {
        String expectedMessageText = "Please enter a valid email address (Ex: johndoe@domain.com).";

        LoginPage loginPage = new HomePage()
                .openSignInPage();

        loginPage
                .enterEmail("user" + RandomStringUtils.randomNumeric(5) + "gmail.com")
                .enterPassword(User.getPassword())
                .clickSignInBtnNegative()
                .checkEmailErrorMessage(expectedMessageText);
    }

    @DisplayName("Check the functionality of user login - negative case (email field is empty)")
    @Test
    void loginUserEmailFieldIsEmpty() {
        String expectedMessageText = "This is a required field";

        LoginPage loginPage = new HomePage()
                .openSignInPage();

        loginPage
                .enterPassword(User.getPassword())
                .clickSignInBtnNegative()
                .checkEmailErrorMessage(expectedMessageText);
    }

    @DisplayName("Check the functionality of user login - negative case (password field is empty)")
    @Test
    void loginUserTestPasswordFieldIsEmpty() {
        String expectedMessageText = "This is a required field";

        LoginPage loginPage = new HomePage()
                .openSignInPage();

        loginPage
                .enterEmail(User.getEmail())
                .clickSignInBtnNegative()
                .checkPasswordErrorMessage(expectedMessageText);
    }

    @DisplayName("Check the functionality of user login - negative case (invalid credentials)")
    @Test
    void loginUserTestInvalidCredentials() {
        String expectedMessageText = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";

        LoginPage loginPage = new HomePage()
                .openSignInPage();

        loginPage
                .enterEmail("user" + RandomStringUtils.randomNumeric(5) + "@gmail.com")
                .enterPassword(RandomStringUtils.randomNumeric(5))
                .clickSignInBtnNegative()
                .checkInvalidCredentialsErrorMessage(expectedMessageText);
    }
}
