package tests.shop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.home.HomePage;
import pages.login_user.LoginPage;
import pages.shop.GearBagsPage;
import pages.shop.WomenTopsPage;
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
                .filterItemsByColor("Color", "Blue")
                .checkEachItemIsInBlueColor("Blue");
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
                .checkPricesInRange(60, 69);
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
                .checkPricesInRange(60, 69);

        womenTopsPage
                .clickClearAll()
                .checkPricesNotInRange(60, 69);
    }

    @DisplayName("Check the functionality of filtering bags by style")
    @Test
    void filterBagsByStyleTest() {
        String bagStyle = "Backpack";

        LoginPage loginPage = new HomePage()
                .openSignInPage();

        HomePage homePage = loginPage
                .loggingIn(User.getEmail(), User.getPassword());

        GearBagsPage gearBagsPage = homePage
                .clickBags();

        gearBagsPage
                .filterItemsByStyle("Style", bagStyle)
                .checkItemsNames(bagStyle);
    }
}
