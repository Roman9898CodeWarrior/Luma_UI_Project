package pages.women_tops;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class WomenTopsPage {
    private final SelenideElement sorterDropdown = $("select#sorter");
    private final ElementsCollection productPrices = $$("span.price-wrapper");
    private final ElementsCollection filterOptions = $$("div.filter-options div.filter-options-item");
    private final SelenideElement blueFilter = $("div.filter-options-content div[option-label='Blue']");
    private final ElementsCollection filteredItemsColor = $$("div[aria-label='Color']");
    private final ElementsCollection priceFilter = $$("div.filter-options-content span.price");
    private final SelenideElement clearFilter = $("a.action.filter-clear");
    private final SelenideElement searchField = $("input#search");
    private final ElementsCollection clothesNames = $$("a.product-item-link");

    @Step("Sort items by price")
    public WomenTopsPage sortItemsByPrice() {
        sorterDropdown.selectOptionContainingText("Price");

        return this;
    }

    @Step("Checking that items are sorted by price")
    public void checkItemsSortedByPrice() {
        productPrices.asDynamicIterable().stream()
                .mapToInt(element -> Integer.parseInt(element.getAttribute("data-price-amount")))
                .reduce((currentPrice, nextPrice) -> {
                    assertTrue("Prices are not sorted properly", currentPrice <= nextPrice);
                    return nextPrice;
                });
    }

    @Step("Filter items by color")
    public WomenTopsPage filterItemsByColor(String filterName) {
        filterOptions.find(text(filterName)).click();
        blueFilter.click();

        return this;
    }

    @Step("Checking that each item is present in blue color")
    public void checkEachItemIsInBlueColor() {
        filteredItemsColor.forEach(element ->
                element.find("[option-label='Blue']").should(exist));
    }

    @Step("Filter items by price")
    public WomenTopsPage filterItemsByPrice(String filterName, String priceFilterValue) {
        filterOptions.find(text(filterName)).click();
        priceFilter.find(text(priceFilterValue)).parent().click();

        return this;
    }

    @Step("Checking that prices are in range 60-69 dollars")
    public void checkPricesInRange() {
        productPrices.forEach(priceElement -> {
            int priceValue = Integer.parseInt(priceElement.getAttribute("data-price-amount"));
            assertTrue("Price is not in range 60-69 dollars", priceValue >= 60 && priceValue <= 69);
        });
    }

    @Step("Clicking 'Clear all' filters")
    public WomenTopsPage clickClearAll() {
        clearFilter.click();

        return this;
    }

    @Step("Checking that prices are not in range 60-69 dollars")
    public void checkPricesNotInRange() {
        productPrices.forEach(priceElement -> {
            int priceValue = Integer.parseInt(priceElement.getAttribute("data-price-amount"));
            assertFalse("Price is not in range 60-69 dollars", priceValue >= 60 && priceValue <= 69);
        });
    }

    @Step("Searching clothes by {value} keyword")
    public WomenTopsPage searchForItemsUsingKeyword(String value) {
        searchField.setValue(value);
        searchField.pressEnter();

        return this;
    }

    @Step("Checking that prices are not in range 60-69 dollars")
    public void checkItemsNames(String value) {
        clothesNames.forEach(clothesName -> clothesName.shouldHave(text(value)));
    }
}
