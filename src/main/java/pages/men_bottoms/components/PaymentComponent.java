package pages.men_bottoms.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.men_bottoms.ThankYouForPurchasePage;

import static com.codeborne.selenide.Selenide.$;

public class PaymentComponent {
    private final SelenideElement placeOrderBtn = $("button.action.checkout");
    private final SelenideElement applyDiscountCode  = $("span#block-discount-heading");
    private final SelenideElement discountCodeField = $("input#discount-code");
    private final SelenideElement applyDiscountBtn = $("button.action-apply");
    private final SelenideElement discountError = $("div[data-role='checkout-messages'] div");

    @Step("Clicking 'Change Email' checkbox")
    public ThankYouForPurchasePage clickPlaceOrderBtn() {
        placeOrderBtn.click();

        return new ThankYouForPurchasePage();
    }


    @Step("Applying invalid discount code")
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
}
