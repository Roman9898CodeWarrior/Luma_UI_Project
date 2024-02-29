package pages.my_account.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$;

public class ChangeEmailComponent {

    private final SelenideElement email = $("input#email");
    private final SelenideElement currentPassword = $("input#current-password");
    private final SelenideElement emailError = $("div#email-error");
    private final SelenideElement passwordIsAbsent = $("div#current-password-error");

    @Step("Entering new user email {value}")
    public ChangeEmailComponent enterEmail(String value) {
        email.setValue(value);

        return this;
    }

    @Step("Clearing user email")
    public ChangeEmailComponent clearEmailField() {
        email.clear();

        return this;
    }

    @Step("Entering user current password {value}")
    public ChangeEmailComponent enterCurrentPassword(String value) {
        currentPassword.setValue(value);

        return this;
    }

    @Step("Clearing user current password")
    public ChangeEmailComponent clearCurrentPassword() {
        currentPassword.clear();

        return this;
    }

    @Step("Checking email error message: {expectedErrorMessage} expected")
    public void checkEmailErrorMessage(String expectedErrorMessage) {
        emailError.shouldHave(Condition.text(expectedErrorMessage));
    }

    @Step("Checking password error message: {expectedErrorMessage} expected")
    public void checkPasswordIsAbsentErrorMessage(String passwordErrorMessage) {
        passwordIsAbsent.shouldHave(Condition.text(passwordErrorMessage));
    }
}
