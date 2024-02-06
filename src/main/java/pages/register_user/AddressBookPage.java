package pages.register_user;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class AddressBookPage {
    private final SelenideElement alertMessage = $("div[role='alert'] div div");

    @Step("Checking the alert message: {value} expected")
    public void checkMessage(String value) {
        alertMessage.shouldHave(Condition.text(value));
    }
}
