package pages.shop;

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
    private final SelenideElement messageLink = $("div[role='alert'] a");

    @Step("Adding item to cart by index")
    public MenBottomsPage addItemsToCartByIndex(int index) {
        items.get(index).$("div[aria-label='Size'] div").click();
        items.get(index).$("div[aria-label='Color'] div").click();
        items.get(index).$("button.tocart").click();

        return this;
    }

    @Step("Adding item to wishlist")
    public MyWishListPage addFirstItemToWishlist(int index) {
        items.get(index).hover();
        items.get(index).$("a.towishlist").click();

        return new MyWishListPage();
    }

    @Step("Adding item to comparison list")
    public MenBottomsPage addFirstItemToCompare(int index) {
        items.get(index).hover();
        items.get(index).$("a.tocompare").click();

        return this;
    }

    @Step("Clicking on item")
    public FirstItemPage clickOnItem(int index) {
        items.get(index).click();

        return new FirstItemPage();
    }

    @Step("Clicking 'Shopping cart' link")
    public CartPage goToCart() {
        messageLink.click();

        return new CartPage();
    }

    @Step("Clicking 'Comparison list' link")
    public ComparisonListPage goToComparisonList() {
        messageLink.click();

        return new ComparisonListPage();
    }

    @Step("Getting item's price")
    public String getItemPrice(int index) {
        return prices.get(index).getText();
    }

    @Step("Getting item's color")
    public String getItemColor(int index) {
        return colorOptions.get(index).getAttribute("option-label");
    }

    @Step("Getting item's size")
    public String getItemSize(int index) {
        return sizeOptions.get(index).getAttribute("option-label");
    }

    @Step("Getting item's name")
    public String getItemName(int index) {
        return itemNames.get(index).getText();
    }
}
