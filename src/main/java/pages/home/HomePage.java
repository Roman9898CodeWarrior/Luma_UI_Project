package pages.home;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.login_user.LoginPage;
import pages.register_user.CreateAccountPage;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    private final SelenideElement signInBtn = $("li.authorization-link a");
    private final SelenideElement signUpBtn = $(By.linkText("Create an Account"));

    @Step("Going to sign in page")
    public LoginPage openSignInPage() {
        signInBtn.click();

        return new LoginPage();
    }

    @Step("Going to registration page")
    public CreateAccountPage openSignUpPage() {
        signUpBtn.click();

        return new CreateAccountPage();
    }
}
