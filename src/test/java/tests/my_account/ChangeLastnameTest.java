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

public class ChangeLastnameTest extends AbstractTest {

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
}
