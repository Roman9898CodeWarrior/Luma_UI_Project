package tests.men_bottoms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.home.HomePage;
import pages.login_user.LoginPage;
import pages.men_bottoms.*;
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
                .getFirstItemPrice();
        String firstItemName = menBottomsPage
                .getFirstItemName();

        FirstItemPage firstItemPage = menBottomsPage
                .clickOnFirstItem();

        firstItemPage.checkItemNameAndPrice(firstItemName, firstItemPrice);
    }
}