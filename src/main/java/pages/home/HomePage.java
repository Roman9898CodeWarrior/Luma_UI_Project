package pages.home;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.shop.*;
import pages.login_user.LoginPage;
import pages.login_user.MyAccountPage;
import pages.register_user.CreateAccountPage;
import pages.shop.GoodsCategoryPage;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HomePage {

    private final SelenideElement signInBtn = $(By.linkText("Sign In"));
    private final SelenideElement signUpBtn = $(By.linkText("Create an Account"));
    private final SelenideElement topBar = $("span.logged-in");
    private final ElementsCollection menuPanel = $$("a.level-top");
    private final SelenideElement topsLink = $(By.linkText("Tops"));
    private final SelenideElement bottomsLink = $(By.linkText("Bottoms"));
    private final SelenideElement bagsLink = $(By.linkText("Bags"));
    private final SelenideElement dropdownArrow = $("button[data-action='customer-menu-toggle']");
    private final ElementsCollection dropdownMenu = $$("ul.header.links a");
    private final SelenideElement busketIcon = $("a.action.showcart");
    private final SelenideElement deleteItemFromBuscketButton = $("a.action.delete");
    private final SelenideElement deleteItemFromBuscketAcceptButton = $("button.action-primary.action-accept");


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
    public MyAccountPage clickMyAccount() {
        dropdownArrow.click();
        dropdownMenu.find(text("My Account")).click();

        return new MyAccountPage();
    }

    @Step("Checking that user successfully logged in: {value} expected")
    public void checkUserPersonalInfo(String value) {
        topBar.shouldHave(text(value));
    }

    @Step("Going to clothing category page")
    public GoodsCategoryPage goToGoodsCategoryPage(String navItemId, String subcategoryId, String typeOfClothingId) {
        String firstPartOfLocator = "a.ui-corner-all#";

        $(firstPartOfLocator + navItemId).hover();
        $(firstPartOfLocator + subcategoryId).hover();
        $(firstPartOfLocator + typeOfClothingId).hover().click();

        System.out.println("ok");

        return new GoodsCategoryPage();
    }

    @Step("Going to Women Tops Page")
    public WomenTopsPage clickWomenTops() {
        menuPanel.find(text("Women")).hover();
        topsLink.click();

        return new WomenTopsPage();
    }

    @Step("Going to Men Bottoms Page")
    public MenBottomsPage clickMenBottoms() {
        menuPanel.find(exactText("Men")).hover();
        bottomsLink.click();

        return new MenBottomsPage();
    }

    @Step("Going to Bags Page")
    public GearBagsPage clickBags() {
        menuPanel.find(text("Gear")).hover();
        bagsLink.click();

        return new GearBagsPage();
    }

    @Step("DeleteOrdersFromBusket")
    public HomePage deleteOrdersFromBusket() {
        busketIcon.click();
        deleteItemFromBuscketButton.click();
        deleteItemFromBuscketAcceptButton.click();

        return this;
    }
}
