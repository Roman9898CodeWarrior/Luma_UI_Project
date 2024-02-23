package pages.men_bottoms.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import models.ShippingAddressModel;

import static com.codeborne.selenide.Selenide.$;

public class ShippingAddressComponent {
    private final SelenideElement nextBtn = $("button.action.continue");
    private final SelenideElement firstname = $("input[name='firstname']");
    private final SelenideElement lastname = $("input[name='lastname']");
    private final SelenideElement streetAddress = $("input[name='street[0]']");
    private final SelenideElement city = $("input[name='city']");
    private final SelenideElement selectStateOrProvince = $("select[name='region_id']");
    private final SelenideElement postcode = $("input[name='postcode']");
    private final SelenideElement selectCountry = $("select[name='country_id']");
    private final SelenideElement phoneNumber = $("input[name='telephone']");
    private final SelenideElement shipHereBtn = $("button.action-save-address");
    private final SelenideElement saveInAddressBookCheckbox = $("input#shipping-save-in-address-book");
    private final SelenideElement addAddressBtn = $("button.action-show-popup");
    private final SelenideElement fieldError = $("div.field-error");

    @Step("Clicking '+ New Address' button")
    public ShippingAddressComponent clickAddAddressBtn() {
        addAddressBtn.click();

        return this;
    }

    @Step("Clicking 'Change Email' checkbox")
    public PaymentComponent clickNextBtn() {
        nextBtn.click();

        return new PaymentComponent();
    }

    @Step("Entering new shipping address {value}")
    public ShippingAddressComponent enterShippingAddress(ShippingAddressModel fields) {
        enterFirstname(fields.getFirstname());
        enterLastname(fields.getLastname());
        enterStreetAddress(fields.getStreetAddress());
        enterCity(fields.getCity());
        selectCountry(fields.getCountry());
        selectStateOrProvince(fields.getStateOrProvince());
        enterPostcode(fields.getPostcode());
        enterPhoneNumber(fields.getPhoneNumber());

        return this;
    }

    @Step("Entering user firstname {value}")
    public ShippingAddressComponent enterFirstname(String value) {
        firstname.setValue(value);

        return this;
    }

    @Step("Entering user lastname {value}")
    public ShippingAddressComponent enterLastname(String value) {
        lastname.setValue(value);

        return this;
    }

    @Step("Entering user street address {value}")
    public ShippingAddressComponent enterStreetAddress(String value) {
        streetAddress.setValue(value);

        return this;
    }

    @Step("Entering user city {value}")
    public ShippingAddressComponent enterCity(String value) {
        city.setValue(value);

        return this;
    }

    @Step("Selecting user state or province {value}")
    public ShippingAddressComponent selectStateOrProvince(String value) {
        selectStateOrProvince.selectOptionContainingText(value);

        return this;
    }

    @Step("Entering user postcode {value}")
    public ShippingAddressComponent enterPostcode(String value) {
        postcode.setValue(value);

        return this;
    }

    @Step("Selecting user country {value}")
    public ShippingAddressComponent selectCountry(String value) {
        selectCountry.selectOptionContainingText(value);

        return this;
    }

    @Step("Entering user phoneNumber {value}")
    public ShippingAddressComponent enterPhoneNumber(String value) {
        phoneNumber.setValue(value);

        return this;
    }

    @Step("Checking 'Save In Address Book' Checkbox")
    public ShippingAddressComponent checkSaveInAddressBookCheckbox() {
        saveInAddressBookCheckbox.click();

        return this;
    }

    @Step("Clicking 'Ship Here' button")
    public ShippingAddressComponent clickShipHereBtn() {
        shipHereBtn.click();

        return this;
    }

    @Step("Clicking 'Ship Here' button for negative cases - no redirection to ")
    public ShippingAddressComponent clickShipHereBtnNegative() {
        shipHereBtn.click();

        return this;
    }

    @Step("Checking city field is empty error message: {expectedErrorMessage} expected")
    public void checkCityFieldIsEmptyErrorMessage(String expectedErrorMessage) {
        fieldError.shouldHave(Condition.text(expectedErrorMessage));
    }
}
