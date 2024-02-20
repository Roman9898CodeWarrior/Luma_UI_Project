package tests.men_bottoms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.home.HomePage;
import pages.login_user.LoginPage;
import pages.men_bottoms.CartPage;
import pages.men_bottoms.MenBottomsPage;
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
                .getFirstItemPrice();
        String firstItemColor = menBottomsPage
                .getFirstItemColor();
        String firstItemSize = menBottomsPage
                .getFirstItemSize();
        String firstItemName = menBottomsPage
                .getFirstItemName();

        CartPage cartPage = menBottomsPage
                .addFirstItemToCart()
                .clickCart()
                .clickViewCart();

        cartPage
                .checkItemAddedToCart(firstItemName, firstItemPrice, firstItemColor, firstItemSize);

        cartPage
                .deleteItemFromCart();
    }
}
