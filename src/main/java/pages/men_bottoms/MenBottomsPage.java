package pages.men_bottoms;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MenBottomsPage {

    private final ElementsCollection items = $$("div.product-item-info");
    private final ElementsCollection itemNames = $$("a.product-item-link");
    private final ElementsCollection sizeOptions = $$("div[aria-label='Size'] div");
    private final ElementsCollection colorOptions = $$("div[aria-label='Color'] div");
    private final ElementsCollection prices = $$("span[data-price-type='finalPrice']");
    private final SelenideElement goToCart = $("div[role='alert'] a");

    @Step("Adding first item to cart")
    public MenBottomsPage addFirstItemToCart() {
        items.first().$("div[aria-label='Size'] div").click();
        items.first().$("div[aria-label='Color'] div").click();
        items.first().$("button.tocart").click();

        return this;
    }

    @Step("Clicking on first item")
    public FirstItemPage clickOnFirstItem() {
        items.first().click();

        return new FirstItemPage();
    }

    @Step("Adding second item to cart")
    public MenBottomsPage addSecondItemToCart() {
        items.get(1).$("div[aria-label='Size'] div").click();
        items.get(1).$("div[aria-label='Color'] div").click();
        items.get(1).$("button.tocart").click();

        return this;
    }

    @Step("Clicking 'Shopping cart' link")
    public CartPage goToCart() {
        goToCart.click();

        return new CartPage();
    }

    @Step("Getting first item price")
    public String getFirstItemPrice() {
        return prices.first().getText();
    }

    @Step("Getting first item color")
    public String getFirstItemColor() {
        return colorOptions.first().getAttribute("option-label");
    }

    @Step("Getting first item size")
    public String getFirstItemSize() {
        return sizeOptions.first().getAttribute("option-label");
    }

    @Step("Getting first item name")
    public String getFirstItemName() {
        return itemNames.first().getText();
    }
}
