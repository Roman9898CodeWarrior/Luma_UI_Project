package tests.men_bottoms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.home.HomePage;
import pages.login_user.LoginPage;
import pages.men_bottoms.MenBottomsPage;
import pages.men_bottoms.components.ComparisonListPage;
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
                .getFirstItemPrice();

        String firstItemName = menBottomsPage
                .getFirstItemName();

        ComparisonListPage comparisonListPage = menBottomsPage
                .addFirstItemToCompare()
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
