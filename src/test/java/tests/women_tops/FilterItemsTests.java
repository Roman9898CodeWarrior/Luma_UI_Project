package tests.women_tops;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.home.HomePage;
import pages.login_user.LoginPage;
import pages.women_tops.WomenTopsPage;
import tests.AbstractTest;
import utils.User;

public class FilterItemsTests extends AbstractTest {

    @DisplayName("Check the functionality of filtering clothes by color")
    @Test
    void filterClothesByColorTest() {
        LoginPage loginPage = new HomePage()
                .openSignInPage();

        HomePage homePage = loginPage
                .loggingIn(User.getEmail(), User.getPassword());

        WomenTopsPage womenTopsPage = homePage
                .clickWomenTops();

        womenTopsPage
                .filterItemsByColor("Color")
                .checkEachItemIsInBlueColor();
    }

    @DisplayName("Check the functionality of filtering clothes by price")
    @Test
    void filterClothesByPriceTest() {
        LoginPage loginPage = new HomePage()
                .openSignInPage();

        HomePage homePage = loginPage
                .loggingIn(User.getEmail(), User.getPassword());

        WomenTopsPage womenTopsPage = homePage
                .clickWomenTops();

        womenTopsPage
                .filterItemsByPrice("Price", "$60.00")
                .checkPricesInRange();
    }

    @DisplayName("Check the functionality of clearing filters")
    @Test
    void clearFilterTest() {
        LoginPage loginPage = new HomePage()
                .openSignInPage();

        HomePage homePage = loginPage
                .loggingIn(User.getEmail(), User.getPassword());

        WomenTopsPage womenTopsPage = homePage
                .clickWomenTops();

        womenTopsPage
                .filterItemsByPrice("Price", "$60.00")
                .checkPricesInRange();

        womenTopsPage
                .clickClearAll()
                .checkPricesNotInRange();
    }
}
