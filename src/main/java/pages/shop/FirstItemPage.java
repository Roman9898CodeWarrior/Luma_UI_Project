package pages.shop;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FirstItemPage {

    private final SelenideElement itemName = $("span[data-ui-id='page-title-wrapper']");
    private final ElementsCollection itemPrices = $$("span[data-price-type='finalPrice']");

    @Step("Checking item's name and price")
    public void checkItemNameAndPrice(String name, String price) {
        itemName.shouldHave(text(name));
        itemPrices.first().shouldHave(text(price));
    }
}
