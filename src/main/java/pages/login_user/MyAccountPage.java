package pages.login_user;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class MyAccountPage {
    SelenideElement headerUserName = $("span.logged-in");

    @Step("Check that user successfully logged in")
    public void checkUserFirstName (String expectedText) {
        headerUserName.shouldHave(Condition.text(expectedText));
    }
}
