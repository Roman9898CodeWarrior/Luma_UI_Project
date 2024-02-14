package pages.my_account.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class ChangePasswordComponent {
    private final SelenideElement newPassword = $("input#password");
    private final SelenideElement confirmPassword = $("input#password-confirmation");

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
}
