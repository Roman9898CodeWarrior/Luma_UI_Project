package pages.login_user;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import pages.address_book.AddressBookDetailsPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class MyAccountPage {
    private final ElementsCollection sideMenu = $$("ul.nav.items a");

    @Step("CLicking 'Address Book'")
    public AddressBookDetailsPage clickAddressBook () {
        sideMenu.find(text("Address Book")).click();

        return new AddressBookDetailsPage();
    }
}
