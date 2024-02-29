package pages.login_user;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.address_book.AddressBookDetailsPage;
import pages.my_account.EditAccountInformationPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MyAccountPage {

    private final ElementsCollection sideMenu = $$("ul.nav.items a");
    private final SelenideElement contactInformation = $("div.box-information div.box-content");

    @Step("Clicking 'Address Book'")
    public AddressBookDetailsPage clickAddressBook () {
        sideMenu.find(text("Address Book")).click();

        return new AddressBookDetailsPage();
    }

    @Step("CLicking 'Account Information'")
    public EditAccountInformationPage clickAccountInformation () {
        sideMenu.find(text("Account Information")).click();

        return new EditAccountInformationPage();
    }

    @Step("Checking 'Account Information': {value} expected")
    public void checkAccountInformation (String value) {
        contactInformation.shouldHave(text(value));
    }
}
