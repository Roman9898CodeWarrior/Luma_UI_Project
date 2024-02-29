package pages.reset_password;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.login_user.LoginPage;

import static com.codeborne.selenide.Selenide.$;

public class ResetPasswordPage {

    private final SelenideElement email = $("input#email_address");
    private final SelenideElement resetBtn  = $("button.action.submit");
    private final SelenideElement emailErrorMessage = $("div#email_address-error");

    @Step("Entering user email {value}")
    public ResetPasswordPage enterEmail(String value) {
        email.setValue(value);

        return this;
    }

    @Step("Clicking 'Reset My Password' button")
    public LoginPage clickResetPassword() {
        resetBtn.click();

        return new LoginPage();
    }

    @Step("Clicking 'Reset My Password' for negative cases (no redirection to Login Page)")
    public ResetPasswordPage clickResetPasswordNegative() {
        resetBtn.click();

        return this;
    }

    @Step("Checking email error message: {expectedErrorMessage} expected")
    public void checkEmailErrorMessage(String expectedErrorMessage) {
        emailErrorMessage.shouldHave(Condition.text(expectedErrorMessage));
    }
}
