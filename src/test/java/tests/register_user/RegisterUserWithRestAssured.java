package tests.register_user;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import pages.register_user.UserData;

public class RegisterUserWithRestAssured {
    String password = RandomStringUtils.randomAlphabetic(10) + RandomStringUtils.randomNumeric(5) + "*?%;";
    String email = "user" + RandomStringUtils.randomNumeric(5) + "@gmail.com";
    UserData uD = new UserData("XbW2plyKhAPX8vVK", "", "", "Alex", "Black", email, password, password);


    @Test @SneakyThrows
    public void registerUser() {
        ObjectMapper mapper = new ObjectMapper();
        RestAssured.given()
                .baseUri("https://magento.softwaretestingboard.com/customer/account/createpost/")
                .contentType(ContentType.TEXT)
                .body(mapper.writeValueAsString(uD))
                .when().post().then()
                .statusCode(302);
    }
}
