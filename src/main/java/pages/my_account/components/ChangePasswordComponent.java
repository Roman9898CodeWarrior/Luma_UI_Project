package pages.my_account.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class ChangePasswordComponent {
    private final SelenideElement newPassword = $("input#password");
    private final SelenideElement confirmPassword = $("input#password-confirmation");
    private final SelenideElement confirmPasswordError = $("div#password-confirmation-error");
    private final SelenideElement passwordError = $("div#password-error");

    @Step("Entering new user password {value}")
    public ChangePasswordComponent enterPassword(String value) {
        newPassword.setValue(value);

        return this;
    }

    @Step("Entering password confirmation {value}")
    public ChangePasswordComponent enterPasswordConfirmation(String value) {
        confirmPassword.setValue(value);

        return this;
    }

    @Step("Checking password confirmation error message: {expectedErrorMessage} expected")
    public void checkPasswordConfirmationErrorMessage(String passwordErrorMessage) {
        confirmPasswordError.shouldHave(Condition.text(passwordErrorMessage));
    }

    @Step("Checking password error message: {expectedErrorMessage} expected")
    public void checkPasswordErrorMessage(String passwordErrorMessage) {
        passwordError.shouldHave(Condition.text(passwordErrorMessage));
    }
}
