package pages.address_book;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class AddressBookDetailsPage {
    private final SelenideElement changeAddressBtn = $("div.box-address-billing a.edit");
    private final SelenideElement billingAddress = $("div.box-address-billing div.box-content");

    @Step("Clicking 'Change Billing Address'")
    public EditAddressPage clickingChangeBillingAddress() {
        changeAddressBtn.click();

        return new EditAddressPage();
    }

    @Step("Checking changed Billing Address")
    public void checkChangedBillingAddress(String phoneNumber, String streetAddress, String city, String zip, String country) {
        billingAddress.shouldHave(and("required fields",
                        text(phoneNumber),
                        text(streetAddress),
                        text(city),
                        text(zip),
                        text(country)));
    }
}
