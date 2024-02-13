package pages.my_account;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.login_user.MyAccountPage;

import static com.codeborne.selenide.Selenide.$;

public class EditAccountInformationPage {
    private final SelenideElement lastname  = $("input#lastname");
    private final SelenideElement email = $("input#email");
    private final SelenideElement changeEmailCheckbox = $("input#change-email");
    private final SelenideElement emailError = $("div#email-error");
    private final SelenideElement password = $("input#password");
    private final SelenideElement changePasswordCheckbox = $("input#change-password");
    private final SelenideElement confirmPassword = $("input#password-confirmation");
    private final SelenideElement saveBtn = $("button.action.save");

    @Step("Entering new user last name {value}")
    public EditAccountInformationPage enterLastname(String value) {
        lastname.setValue(value);

        return this;
    }

    @Step("Clicking 'Change Email' checkbox")
    public EditAccountInformationPage clickChangeEmailCheckbox() {
        changeEmailCheckbox.click();

        return this;
    }

    @Step("Entering new user last email {value}")
    public EditAccountInformationPage enterEmail(String value) {
        email.setValue(value);

        return this;
    }

    @Step("Clearing user email {value}")
    public EditAccountInformationPage clearEmailField() {
        email.clear();

        return this;
    }

    @Step("Clicking 'Change Password' checkbox")
    public EditAccountInformationPage clickChangePasswordCheckbox() {
        changePasswordCheckbox.click();

        return this;
    }

    @Step("Entering new user password {value}")
    public EditAccountInformationPage enterPassword(String value) {
        password.setValue(value);

        return this;
    }

    @Step("Entering password confirmation {value}")
    public EditAccountInformationPage enterPasswordConfirmation(String value) {
        confirmPassword.setValue(value);

        return this;
    }

    @Step("Clicking 'Save' button")
    public MyAccountPage clickSaveBtn() {
        saveBtn.click();

        return new MyAccountPage();
    }

    @Step("Clicking 'Reset My Password' for negative cases (no redirection to My Account Page)")
    public EditAccountInformationPage clickSaveBtnNegative() {
        saveBtn.click();

        return this;
    }

    @Step("Checking email error message: {expectedErrorMessage} expected")
    public void checkEmailErrorMessage(String expectedErrorMessage) {
        emailError.shouldHave(Condition.text(expectedErrorMessage));
    }
}
