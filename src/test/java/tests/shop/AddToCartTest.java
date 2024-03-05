package tests.shop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.home.HomePage;
import pages.login_user.LoginPage;
import pages.shop.CartPage;
import pages.shop.MenBottomsPage;
import tests.AbstractTest;
import utils.User;

public class AddToCartTest extends AbstractTest {

    @DisplayName("Check the functionality of adding item to cart")
    @Test
    void addItemToCartTest() {
        LoginPage loginPage = new HomePage()
                .openSignInPage();

        HomePage homePage = loginPage
                .loggingIn(User.getEmail(), User.getPassword());

        MenBottomsPage menBottomsPage = homePage
                .clickMenBottoms();

        String firstItemPrice = menBottomsPage
                .getItemPrice(0);

        String firstItemColor = menBottomsPage
                .getItemColor(0);

        String firstItemSize = menBottomsPage
                .getItemSize(0);

        String firstItemName = menBottomsPage
                .getItemName(0);


        CartPage cartPage = menBottomsPage
                .addItemsToCartByIndex(0)
                .goToCart();

        cartPage
                .checkItemAddedToCart(firstItemName, firstItemPrice, firstItemColor, firstItemSize);

        cartPage
                .deleteItemFromCart();
    }
}
