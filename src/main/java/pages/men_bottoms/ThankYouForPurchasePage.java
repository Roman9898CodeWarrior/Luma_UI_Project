package pages.men_bottoms;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ThankYouForPurchasePage {

    private final SelenideElement pageTitle = $("h1.page-title span");
    private final SelenideElement orderMessage = $("div.checkout-success");

    @Step("Checking page title: {value} expected")
    public void checkPageTitle(String value) {
        pageTitle.shouldHave(text(value));
    }

    @Step("Checking that order was successfully placed: {value} expected")
    public void checkOrderMessage(String value) {
        orderMessage.shouldHave(text(value));
    }
}
