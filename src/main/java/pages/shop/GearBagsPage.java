package pages.shop;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class GearBagsPage {

    private final ElementsCollection filters = $$("div.filter-options div.filter-options-item");
    private final ElementsCollection filterOptions = $$("div.filter-options div.filter-options-item a");
    private final ElementsCollection bagsNames = $$("ol.products.product-items a.product-item-link");

    @Step("Filter items by style")
    public GearBagsPage filterItemsByStyle(String filterName, String bagStyle) {
        filters.find(text(filterName)).click();
        filterOptions.find(text(bagStyle)).click();

        return this;
    }

    @Step("Checking that all items were filtered by style")
    public void checkItemsNames(String value) {
        bagsNames.forEach(clothesName -> clothesName.shouldHave(text(value)));
    }
}
