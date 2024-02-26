package pages.men_bottoms.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ComparisonListPage {

    private final ElementsCollection itemsInComparisonList = $$("td[data-th='Product']");
    private final SelenideElement itemWasRemovedMessage = $("div[role='alert'] div div");
    private final SelenideElement noItemsMessage = $("div.message.empty");
    private final SelenideElement deleteBtn = $("a.delete");
    private final SelenideElement confirmDeleteBtn = $("button.action-accept");

    @Step("Checking item's price")
    public void checkItemPrice(String price) {
        itemsInComparisonList.first().$("span.price-wrapper").shouldHave(text(price));
    }

    @Step("Checking item's name")
    public void checkItemName(String name) {
        itemsInComparisonList.first().$("strong.product-item-name").shouldHave(text(name));
    }

    @Step("Deleting item from comparison list")
    public ComparisonListPage deleteItemFromComparisonList() {
        deleteBtn.click();
        confirmDeleteBtn.click();

        return this;
    }

    @Step("Check message that item was removed: {expectedMessage} expected")
    public void checkItemWasRemovedMessage(String expectedMessage) {
        itemWasRemovedMessage.shouldHave(text(expectedMessage));
    }

    @Step("Check message that there is no items in comparison list: {expectedMessage} expected")
    public void checkNoItemsMessage(String expectedMessage) {
        noItemsMessage.shouldHave(text(expectedMessage));
    }
}
