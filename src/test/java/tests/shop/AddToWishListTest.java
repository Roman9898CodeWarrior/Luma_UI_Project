package tests.shop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.home.HomePage;
import pages.login_user.LoginPage;
import pages.shop.MenBottomsPage;
import pages.shop.MyWishListPage;
import tests.AbstractTest;
import utils.User;

public class AddToWishListTest extends AbstractTest {

    @DisplayName("Check the functionality of adding item to wishlist")
    @Test
    void addItemToWishlistTest() {
        String noItemsInWishlistMessage = "You have no items in your wish list.";
        String removedFromWishlistMessage = "has been removed from your Wish List.";

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

        MyWishListPage myWishListPage = menBottomsPage
                .addFirstItemToWishlist(0);

        myWishListPage
                .checkItemPrice(firstItemPrice);

        myWishListPage
                .checkItemName(firstItemName);

        myWishListPage
                .deleteItemFromWishlist()
                .checkItemWasRemovedMessage(removedFromWishlistMessage);

        myWishListPage
                .checkNoItemsMessage(noItemsInWishlistMessage);
    }
}
