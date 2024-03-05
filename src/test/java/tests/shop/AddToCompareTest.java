package tests.shop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.home.HomePage;
import pages.login_user.LoginPage;
import pages.shop.MenBottomsPage;
import pages.shop.ComparisonListPage;
import tests.AbstractTest;
import utils.User;

public class AddToCompareTest extends AbstractTest {

    @DisplayName("Check the functionality of adding item to comparison list")
    @Test
    void addItemToComparisonListTest() {
        String removedFromWishlistMessage = "You removed product %s from the comparison list.";
        String noItemsInWishlistMessage = "You have no items to compare.";

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

        ComparisonListPage comparisonListPage = menBottomsPage
                .addFirstItemToCompare(0)
                .goToComparisonList();

        comparisonListPage
                .checkItemPrice(firstItemPrice);

        comparisonListPage
                .checkItemName(firstItemName);

        comparisonListPage
                .deleteItemFromComparisonList()
                .checkItemWasRemovedMessage(String.format(removedFromWishlistMessage, firstItemName));

        comparisonListPage
                .checkNoItemsMessage(noItemsInWishlistMessage);
    }
}
