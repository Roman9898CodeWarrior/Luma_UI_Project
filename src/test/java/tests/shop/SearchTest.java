package tests.shop;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.home.HomePage;
import pages.login_user.LoginPage;
import pages.shop.WomenTopsPage;
import tests.AbstractTest;
import utils.User;

public class SearchTest extends AbstractTest {

    @DisplayName("Check the functionality of search field")
    @Test
    void searchFieldTest() {
        String searchWord = "jacket";

        LoginPage loginPage = new HomePage()
                .openSignInPage();

        HomePage homePage = loginPage
                .loggingIn(User.getEmail(), User.getPassword());

        WomenTopsPage womenTopsPage = homePage
                .clickWomenTops();

        womenTopsPage
                .searchForItemsUsingKeyword(searchWord)
                .checkItemsNames(searchWord);
    }

    @DisplayName("Check the functionality of search field")
    @Test
    void searchFieldTestInvalidKeyword() {
        String searchWord = RandomStringUtils.randomAlphabetic(5);
        String errorMessage = "Your search returned no results.";

        LoginPage loginPage = new HomePage()
                .openSignInPage();

        HomePage homePage = loginPage
                .loggingIn(User.getEmail(), User.getPassword());

        WomenTopsPage womenTopsPage = homePage
                .clickWomenTops();

        womenTopsPage
                .searchForItemsUsingKeyword(searchWord)
                .checkSearchErrorText(errorMessage);
    }
}
