package tests.register_user;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.home.HomePage;
import pages.register_user.MyAccountPage;
import pages.register_user.CreateAccountPage;
import tests.AbstractTest;

public class RegisterUserTests extends AbstractTest {

    @DisplayName("Check the functionality of registering a user")
    @Test
    void registerUserTest() {
        String expectedMessageText = "Thank you for registering with Main Website Store.";
        String password = RandomStringUtils.randomAlphabetic(10) + RandomStringUtils.randomNumeric(5) + "*?%;";

        CreateAccountPage createAccountPage = new HomePage()
                .openSignUpPage();

        MyAccountPage myAccountPage = createAccountPage
                .enterName("Alex")
                .enterLastname("Black")
                .enterEmail("user" + RandomStringUtils.randomNumeric(5) + "@gmail.com")
                .enterPassword(password)
                .enterConfirmPassword(password)
                .clickCreateAccountBtn();

        myAccountPage.checkMessage(expectedMessageText);
    }

    @DisplayName("Check the functionality of registering a user - negative case (invalid email)")
    @Test
    void registerUserTestInvalidEmail() {
        String expectedMessageText = "Please enter a valid email address (Ex: johndoe@domain.com).";
        String password = RandomStringUtils.randomAlphabetic(6);

        CreateAccountPage createAccountPage = new HomePage()
                .openSignUpPage();

        createAccountPage
                .enterName("Alex")
                .enterLastname("Black")
                .enterEmail("user" + RandomStringUtils.randomNumeric(5) + "gmail.com")
                .enterPassword(password)
                .enterConfirmPassword(password)
                .clickCreateAccountBtnNegative()
                .checkEmailErrorMessage(expectedMessageText);
    }

    @DisplayName("Check the functionality of registering a user - negative case (password length is less than 8 symbols)")
    @Test
    void registerUserTestPasswordTooShort() {
        String expectedMessageText = "Minimum length of this field must be equal or greater than 8 symbols. Leading and trailing spaces will be ignored.";
        String password = RandomStringUtils.randomAlphabetic(6);

        CreateAccountPage createAccountPage = new HomePage()
                .openSignUpPage();

        createAccountPage
                .enterName("Alex")
                .enterLastname("Black")
                .enterEmail("user" + RandomStringUtils.randomNumeric(5) + "@gmail.com")
                .enterPassword(password)
                .enterConfirmPassword(password)
                .checkPasswordErrorMessage(expectedMessageText);
    }

    @DisplayName("Check the functionality of registering a user - negative case (insufficient password complexity)")
    @Test
    void registerUserTestInsufficientPasswordComplexity () {
        String expectedMessageText = "Minimum of different classes of characters in password is 3. Classes of characters: Lower Case, Upper Case, Digits, Special Characters.";
        String password = RandomStringUtils.randomNumeric(5) + "*?%;";

        CreateAccountPage createAccountPage = new HomePage()
                .openSignUpPage();

        createAccountPage
                .enterName("Alex")
                .enterLastname("Black")
                .enterEmail("user" + RandomStringUtils.randomNumeric(5) + "@gmail.com")
                .enterPassword(password)
                .enterConfirmPassword(password)
                .checkPasswordErrorMessage(expectedMessageText);
    }

    @DisplayName("Check the functionality of registering a user - negative case (invalid password confirmation)")
    @Test
    void registerUserTestInvalidPasswordConfirmation () {
        String expectedMessageText = "Please enter the same value again.";

        CreateAccountPage createAccountPage = new HomePage()
                .openSignUpPage();

        createAccountPage
                .enterName("Alex")
                .enterLastname("Black")
                .enterEmail("user" + RandomStringUtils.randomNumeric(5) + "@gmail.com")
                .enterPassword(RandomStringUtils.randomAlphabetic(10) + RandomStringUtils.randomNumeric(5) + "*?%;")
                .enterConfirmPassword(RandomStringUtils.randomAlphabetic(10) + RandomStringUtils.randomNumeric(5) + "*?%;")
                .clickCreateAccountBtnNegative()
                .checkPasswordConfirmationErrorMessage(expectedMessageText);
    }

    @DisplayName("Check the functionality of registering a user - negative case (first name field is empty)")
    @Test
    void registerUserTestNameFieldIsEmpty () {
        String expectedMessageText = "This is a required field.";
        String password = RandomStringUtils.randomNumeric(5) + "*?%;";

        CreateAccountPage createAccountPage = new HomePage()
                .openSignUpPage();

        createAccountPage
                .enterLastname("Black")
                .enterEmail("user" + RandomStringUtils.randomNumeric(5) + "@gmail.com")
                .enterPassword(password)
                .enterConfirmPassword(password)
                .clickCreateAccountBtnNegative()
                .checkFirstnameConfirmationErrorMessage(expectedMessageText);
    }
}
