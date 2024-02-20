package pages.men_bottoms;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class CartPage {

    private final SelenideElement cartItem = $("tbody.cart.item");
    private final SelenideElement deleteItem = $("a.action-delete");
    private final SelenideElement proceedToCheckoutBtn = $("button[data-role='proceed-to-checkout']");

    @Step("Checking that correct item was added to cart")
    public void checkItemAddedToCart(String itemName, String itemPrice, String itemColor, String itemSize) {
        cartItem.shouldHave(and("required fields",
                text(itemName),
                text(itemPrice),
                text(itemColor),
                text(itemSize)));
    }

    @Step("Deleting item that was added to cart")
    public CartPage deleteItemFromCart() {
        deleteItem.click();

        return this;
    }

    @Step("Clicking 'Proceed To Checkout'")
    public OrderDetailsPage clickProceedToCheckout() {
        proceedToCheckoutBtn.click();

        return new OrderDetailsPage();
    }
}
