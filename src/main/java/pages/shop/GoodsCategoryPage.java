package pages.shop;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.shop.components.ShippingAddressComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class GoodsCategoryPage {

    private final ElementsCollection items = $$("div.product-item-info");
    private final ElementsCollection itemNames = $$("a.product-item-link");
    private final ElementsCollection sizeOptions = $$("div[aria-label='Size'] div");
    private final ElementsCollection colorOptions = $$("div[aria-label='Color'] div");
    private final ElementsCollection prices = $$("span[data-price-type='finalPrice']");
    private final SelenideElement message = $("div.message-success");
    private final SelenideElement busketIcon = $("a.action.showcart");
    private final SelenideElement proceedToCheckoutButton = $("#top-cart-btn-checkout");
    public String choosenItemPrice;

    @Step("Add item to the basket")
    public void addItemToTheBasket(String itemName, String itemSize, String itemColor) {

        SelenideElement itemForBascket = $("div.product-item-info").shouldHave(text(itemName));

        itemForBascket.hover();

        itemForBascket.find("div[aria-label='Size'] div[option-label=" + itemSize + "]").click();

        itemForBascket.find("div[aria-label='Color'] div[option-label=" + itemColor + "]").click();

        choosenItemPrice = itemForBascket.find("span.price").getText();

        itemForBascket.find("button.action.tocart.primary").click();
    }

    @Step("Checking message about successful adding of item in bascket: {expectedMessage} expected")
    public void checkSuccessfulAddingOfItemMessage(String itemName) {
        message.shouldHave(Condition.text("You added " +  itemName + " to your shopping cart."));
    }

    @Step("Proceed to checkout page after adding item to the basket")
    public ShippingAddressComponent proceedToCheckoutPage() {
        busketIcon.click();

        proceedToCheckoutButton.click();

        return new ShippingAddressComponent();
    }
}
