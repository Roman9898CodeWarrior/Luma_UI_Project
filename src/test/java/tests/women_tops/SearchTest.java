package tests.women_tops;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.home.HomePage;
import pages.login_user.LoginPage;
import pages.women_tops.WomenTopsPage;
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
}
