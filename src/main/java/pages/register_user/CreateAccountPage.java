package pages.register_user;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class CreateAccountPage {

    private final SelenideElement firstname = $("input#firstname");
    private final SelenideElement lastname  = $("input#lastname");
    private final SelenideElement email = $("input#email_address");
    private final SelenideElement password = $("input#password");
    private final SelenideElement confirmPassword = $("input#password-confirmation");
    private final SelenideElement createAnAccountBtn = $("button.submit");
    private final SelenideElement passwordErrorMessage = $("div#password-error");
    private final SelenideElement passwordConfirmationErrorMessage = $("div#password-confirmation-error");
    private final SelenideElement emailErrorMessage = $("div#email_address-error");
    private final SelenideElement firstnameErrorMessage = $("div#firstname-error");

    @Step("Entering user first name {value}")
    public CreateAccountPage enterName(String value) {
        firstname.setValue(value);

        return this;
    }

    @Step("Entering user last name {value}")
    public CreateAccountPage enterLastname(String value) {
        lastname.setValue(value);

        return this;
    }

    @Step("Entering user email {value}")
    public CreateAccountPage enterEmail(String value) {
        email.setValue(value);

        return this;
    }

    @Step("Entering user password {value}")
    public CreateAccountPage enterPassword(String value) {
        password.setValue(value);

        return this;
    }

    @Step("Entering user password confirmation {value}")
    public CreateAccountPage enterConfirmPassword(String value) {
        confirmPassword.setValue(value);

        return this;
    }

    @Step("Clicking 'Create an account' button")
    public MyAccountPage clickCreateAccountBtn() {
        createAnAccountBtn.click();

        return new MyAccountPage();
    }

    @Step("Clicking 'Create an account' button for negative cases (no redirection to an Address Book Page)")
    public CreateAccountPage clickCreateAccountBtnNegative() {
        createAnAccountBtn.click();

        return this;
    }

    @Step("Checking password error message: {expectedErrorMessage} expected")
    public void checkPasswordErrorMessage(String expectedErrorMessage) {
        passwordErrorMessage.shouldHave(Condition.text(expectedErrorMessage));
    }

    @Step("Checking email error message: {expectedErrorMessage} expected")
    public void checkEmailErrorMessage(String expectedErrorMessage) {
        emailErrorMessage.shouldHave(Condition.text(expectedErrorMessage));
    }

    @Step("Checking password confirmation error message: {expectedErrorMessage} expected")
    public void checkPasswordConfirmationErrorMessage(String expectedErrorMessage) {
        passwordConfirmationErrorMessage.shouldHave(Condition.text(expectedErrorMessage));
    }

    @Step("Checking first name error message: {expectedErrorMessage} expected")
    public void checkFirstnameConfirmationErrorMessage(String expectedErrorMessage) {
        firstnameErrorMessage.shouldHave(Condition.text(expectedErrorMessage));
    }
}
