package pages.login_user;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement email = $("input#email");
    private final SelenideElement password  = $("input#pass");
    private final SelenideElement signInBtn  = $("button.action.login");
    private final SelenideElement emailErrorMessage = $("div#email-error");

    @Step("Entering user first name {value}")
    public LoginPage enterEmail(String value) {
        email.setValue(value);

        return this;
    }

    @Step("Entering user last name {value}")
    public LoginPage enterPassword(String value) {
        password.setValue(value);

        return this;
    }

    @Step("Clicking sign in button")
    public MyAccountPage clickSignInBtn() {
        signInBtn.click();

        return new MyAccountPage();
    }

    @Step("Clicking sign in button")
    public LoginPage clickSignInBtnNegative() {
        signInBtn.click();

        return this;
    }

    @Step("Check email error message")
    public void checkEmailErrorMessage(String expectedErrorMessage) {
        emailErrorMessage.shouldHave(Condition.text(expectedErrorMessage));
    }
}
