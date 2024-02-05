package tests.login_user;

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


    @DisplayName("Check the functionality of registering a user - negative case (invalid email)")
    @Test
    void loginUserTestInvalidEmail() {
        String expectedMessageText = "Please enter a valid email address (Ex: johndoe@domain.com).";

        LoginPage loginPage = new HomePage()
                .openSignInPage();

        loginPage
                .enterEmail("autotest12gmail.com")
                .enterPassword("Autotest12!")
                .clickSignInBtnNegative()
                .checkEmailErrorMessage(expectedMessageText);
    }
}
