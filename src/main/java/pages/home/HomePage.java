package pages.home;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.login_user.LoginPage;
import pages.login_user.MyAccountPage;
import pages.register_user.CreateAccountPage;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HomePage {
    private final SelenideElement signInBtn = $(By.linkText("Sign In"));
    private final SelenideElement signUpBtn = $(By.linkText("Create an Account"));
    private final SelenideElement topBar = $("span.logged-in");
    private final SelenideElement dropdownArrow = $("button[data-action='customer-menu-toggle']");
    private final ElementsCollection dropdownMenu = $$("ul.header.links a");

    @Step("Going to Sign In Page")
    public LoginPage openSignInPage() {
        signInBtn.click();

        return new LoginPage();
    }

    @Step("Going to Registration Page")
    public CreateAccountPage openSignUpPage() {
        signUpBtn.click();

        return new CreateAccountPage();
    }

    @Step("Going to My Account Page")
    public MyAccountPage clickMyAccount () {
        dropdownArrow.click();
        dropdownMenu.find(text("My Account")).click();

        return new MyAccountPage();
    }

    @Step("Checking that user successfully logged in: {expectedUserName} and {expectedUserLastname} expected")
    public void checkUserFirstName (String expectedUserName, String expectedUserLastname) {
        topBar.shouldHave(and("required fields",
                text(expectedUserName),
                text(expectedUserLastname)));
    }
}
