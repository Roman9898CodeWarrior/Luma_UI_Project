package tests.shop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.shop.GearBagsPage;
import pages.home.HomePage;
import pages.login_user.LoginPage;
import tests.AbstractTest;
import utils.User;

public class FilterItemsTest extends AbstractTest {

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
