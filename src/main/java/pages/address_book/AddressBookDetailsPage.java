package pages.address_book;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import models.ShippingAddressModel;

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
    public void checkChangedBillingAddress(ShippingAddressModel fields) {
        billingAddress.shouldHave(and("required fields",
                        text(fields.getPhoneNumber()),
                        text(fields.getStreetAddress()),
                        text(fields.getCity()),
                        text(fields.getPostcode()),
                        text(fields.getCountry())));
    }
}
