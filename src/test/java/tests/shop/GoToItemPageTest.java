package tests.shop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.home.HomePage;
import pages.login_user.LoginPage;
import pages.shop.FirstItemPage;
import pages.shop.MenBottomsPage;
import tests.AbstractTest;
import utils.User;

public class GoToItemPageTest extends AbstractTest {

    @DisplayName("Check the functionality of going to an item's page")
    @Test
    void goToItemPageTest() {
        LoginPage loginPage = new HomePage()
                .openSignInPage();

        HomePage homePage = loginPage
                .loggingIn(User.getEmail(), User.getPassword());

        MenBottomsPage menBottomsPage = homePage
                .clickMenBottoms();

        String firstItemPrice = menBottomsPage
                .getItemPrice(0);
        String firstItemName = menBottomsPage
                .getItemName(0);

        FirstItemPage firstItemPage = menBottomsPage
                .clickOnItem(0);

        firstItemPage
                .checkItemNameAndPrice(firstItemName, firstItemPrice);
    }
}