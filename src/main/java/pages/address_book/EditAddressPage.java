package pages.address_book;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class EditAddressPage {
    private final SelenideElement phoneNumber = $("input#telephone");
    private final SelenideElement streetAddress = $("input#street_1");
    private final SelenideElement city = $("input#city");
    private final SelenideElement zip = $("input#zip");
    private final SelenideElement country = $("select#country");
    private final SelenideElement saveBtn = $("button.save");

    @Step("Entering user phone number {value}")
    public EditAddressPage enterPhoneNumber(String value) {
        phoneNumber.setValue(value);

        return this;
    }

    @Step("Entering user street address {value}")
    public EditAddressPage enterStreetAddress(String value) {
        streetAddress.setValue(value);

        return this;
    }

    @Step("Entering user city {value}")
    public EditAddressPage enterCity(String value) {
        city.setValue(value);

        return this;
    }

    @Step("Entering user zip-code {value}")
    public EditAddressPage enterZip(String value) {
        zip.setValue(value);

        return this;
    }

    @Step("Selecting user country {value}")
    public EditAddressPage selectCountry(String value) {
        country.selectOptionContainingText(value);

        return this;
    }

    @Step("Click 'Create an account' button")
    public AddressBookDetailsPage clickSaveBtn() {
        saveBtn.click();

        return new AddressBookDetailsPage();
    }

    @Step("Click 'Save Address' button for negative cases (no redirection to an Address Book Details Page)")
    public EditAddressPage clickSaveBtnNegative() {
        saveBtn.click();

        return this;
    }

}
