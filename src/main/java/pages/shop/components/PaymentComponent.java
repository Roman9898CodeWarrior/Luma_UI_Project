package pages.shop.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.home.HomePage;
import pages.shop.ThankYouForPurchasePage;

import static com.codeborne.selenide.Selenide.$;

public class PaymentComponent {

    private final SelenideElement placeOrderBtn = $("button.action.checkout");
    private final SelenideElement applyDiscountCode  = $("span#block-discount-heading");
    private final SelenideElement discountCodeField = $("input#discount-code");
    private final SelenideElement applyDiscountBtn = $("button.action-apply");
    private final SelenideElement discountError = $("div[data-role='checkout-messages'] div");
    private final SelenideElement cardSubtotalSum = $("span[data-th='Cart Subtotal']");
    private final SelenideElement homePageLink = $("a.logo");

    @Step("Clicking 'Place Order' button")
    public ThankYouForPurchasePage clickPlaceOrderBtn() {
        placeOrderBtn.click();

        return new ThankYouForPurchasePage();
    }


    @Step("Applying invalid discount code {value}")
    public PaymentComponent applyInvalidDiscountCode(String value) {
        applyDiscountCode.click();
        discountCodeField.setValue(value);
        applyDiscountBtn.click();

        return this;
    }

    @Step("Checking invalid discount code error message: {expectedErrorMessage} expected")
    public void checkInvalidDiscountCodeErrorMessage(String expectedErrorMessage) {
        discountError.shouldHave(Condition.text(expectedErrorMessage));
    }

    @Step("Checking that the sum in card subtotal is right")
    public void checkСardSubtotalSum(String expectedСardSubtotalSum) {
        cardSubtotalSum.shouldHave(Condition.text(expectedСardSubtotalSum));
    }

    @Step("Go to home page")
    public HomePage GoToHomePage() {
        homePageLink.click();

        return new HomePage();
    }
}
