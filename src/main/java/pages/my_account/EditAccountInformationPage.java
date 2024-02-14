package pages.my_account;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.login_user.MyAccountPage;
import pages.my_account.components.ChangeEmailComponent;
import pages.my_account.components.ChangePasswordComponent;

import static com.codeborne.selenide.Selenide.$;

public class EditAccountInformationPage {
    private final SelenideElement lastname  = $("input#lastname");
    private final SelenideElement changeEmailCheckbox = $("input#change-email");
    private final SelenideElement invalidPasswordError = $("div.message-error div");
    private final SelenideElement changePasswordCheckbox = $("input#change-password");
    private final SelenideElement saveBtn = $("button.action.save");

    public ChangeEmailComponent changeEmailComponent;
    public ChangePasswordComponent changePasswordComponent;

    public EditAccountInformationPage() {
        changeEmailComponent = new ChangeEmailComponent();
        changePasswordComponent = new ChangePasswordComponent();
    }

    public ChangeEmailComponent getChangeEmailComponent() {
        return changeEmailComponent;
    }

    public ChangePasswordComponent getChangePasswordComponent() {
        return changePasswordComponent;
    }

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

    @Step("Clicking 'Change Password' checkbox")
    public EditAccountInformationPage clickChangePasswordCheckbox() {
        changePasswordCheckbox.click();

        return this;
    }

    @Step("Clicking 'Save' for negative cases (no redirection to My Account Page)")
    public EditAccountInformationPage clickSaveBtnNegative() {
        saveBtn.click();

        return this;
    }

    @Step("Clicking 'Save' button")
    public MyAccountPage clickSaveBtn() {
        saveBtn.click();

        return new MyAccountPage();
    }

    @Step("Checking password error message: {expectedErrorMessage} expected")
    public void checkInvalidPasswordErrorMessage(String passwordErrorMessage) {
        invalidPasswordError.shouldHave(Condition.text(passwordErrorMessage));
    }
}
