package pages.login_user;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.home.HomePage;
import pages.reset_password.ResetPasswordPage;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final SelenideElement email = $("input#email");
    private final SelenideElement password  = $("input#pass");
    private final SelenideElement signInBtn  = $("button.action.login");
    private final SelenideElement emailErrorMessage = $("div#email-error");
    private final SelenideElement passwordErrorMessage = $("div#pass-error");
    private final SelenideElement invalidCredentialsErrorMessage = $("div.message-error div");
    private final SelenideElement forgotPassword = $("a.remind");
    private final SelenideElement resetPasswordMessage = $("div.message-success div");
    private final SelenideElement sucсessfulChangeOfAccountInformationMessage = $("div[data-bind='html: $parent.prepareMessageForHtml(message.text)']");

    @Step("Entering user email {value}")
    public LoginPage enterEmail(String value) {
        email.setValue(value);

        return this;
    }

    @Step("Entering user password {value}")
    public LoginPage enterPassword(String value) {
        password.setValue(value);

        return this;
    }

    @Step("Clicking sign in button")
    public HomePage clickSignInBtn() {
        signInBtn.click();

        return new HomePage();
    }

    @Step("Clicking sign in button for negative cases (no redirection to Home Page")
    public LoginPage clickSignInBtnNegative() {
        signInBtn.click();

        return this;
    }

    @Step("Logging in")
    public HomePage loggingIn(String emailValue, String passwordValue) {
        enterEmail(emailValue);
        enterPassword(passwordValue);
        clickSignInBtn();

        return new HomePage();
    }

    @Step("Logging in after change of email")
    public MyAccountPage loggingInAfterChangeOfEmail(String emailValue, String passwordValue) {
        enterEmail(emailValue);
        enterPassword(passwordValue);
        clickSignInBtn();

        return new MyAccountPage();
    }

    @Step("Clicking 'Forgot Your Password?'")
    public ResetPasswordPage clickForgotPassword() {
        forgotPassword.click();

        return new ResetPasswordPage();
    }

    @Step("Checking email error message: {expectedErrorMessage} expected")
    public void checkEmailErrorMessage(String expectedErrorMessage) {
        emailErrorMessage.shouldHave(Condition.text(expectedErrorMessage));
    }

    @Step("Checking password error message: {expectedErrorMessage} expected")
    public void checkPasswordErrorMessage(String expectedErrorMessage) {
        passwordErrorMessage.shouldHave(Condition.text(expectedErrorMessage));
    }

    @Step("Checking invalid credentials error message: {expectedErrorMessage} expected")
    public void checkInvalidCredentialsErrorMessage(String expectedErrorMessage) {
        invalidCredentialsErrorMessage.shouldHave(Condition.text(expectedErrorMessage));
    }

    @Step("Checking reset password message: {expectedErrorMessage} expected")
    public void checkResetPasswordMessage(String expectedErrorMessage) {
        resetPasswordMessage.shouldHave(Condition.text(expectedErrorMessage));
    }

    @Step("Checking successful change of email message: {expectedMessage} expected")
    public void checkSuccessfulChangeOfEmailMessage(String expectedMessage) {
        sucсessfulChangeOfAccountInformationMessage.shouldHave(Condition.text(expectedMessage));
    }
}
