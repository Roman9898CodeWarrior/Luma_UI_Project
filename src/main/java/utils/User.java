package utils;

import io.github.cdimascio.dotenv.Dotenv;
public class User {

    private static String email;
    private static String password;
    private static String name;
    private static String lastname;

    public static void initialize(Dotenv dotenv) {
        email = dotenv.get("user_email");
        password = dotenv.get("user_password");
        name = dotenv.get("user_name");
        lastname = dotenv.get("user_lastname");
    }

    public static String getEmail() {
        return email;
    }

    public static String getPassword() {
        return password;
    }

    public static String getName() {
        return name;
    }

    public static String getLastname() {
        return lastname;
    }
}
