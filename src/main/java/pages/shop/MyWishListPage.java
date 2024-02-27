package pages.shop;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MyWishListPage {

    private final ElementsCollection itemsInWishlist = $$("li.product-item div.product-item-info");
    private final SelenideElement itemWasRemovedMessage = $("div[role='alert'] div div");
    private final SelenideElement noItemsMessage = $("div.message.empty");

    @Step("Checking item's price")
    public void checkItemPrice(String price) {
        itemsInWishlist.first().$("span.price-wrapper").shouldHave(text(price));
    }

    @Step("Checking item's name")
    public void checkItemName(String name) {
        itemsInWishlist.first().$("strong.product-item-name").shouldHave(text(name));
    }

    @Step("Deleting item from wishlist")
    public MyWishListPage deleteItemFromWishlist() {
        itemsInWishlist.first().hover();
        itemsInWishlist.first().$("a.btn-remove.delete").click();

        return this;
    }

    @Step("Check message that item was removed: {expectedMessage} expected")
    public void checkItemWasRemovedMessage(String expectedMessage) {
        itemWasRemovedMessage.shouldHave(text(expectedMessage));
    }

    @Step("Check message that there is no items in wishlist: {expectedMessage} expected")
    public void checkNoItemsMessage(String expectedMessage) {
        noItemsMessage.shouldHave(text(expectedMessage));
    }
}
