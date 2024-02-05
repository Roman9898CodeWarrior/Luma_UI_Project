package utils;

import io.github.cdimascio.dotenv.Dotenv;
public class User {

    private static String email;
    private static String password;

    public static void initialize(Dotenv dotenv) {
        email = dotenv.get("user_email");
        password = dotenv.get("user_password");
    }

    public static String getEmail() {
        return email;
    }

    public static String getPassword() {
        return password;
    }
}
