package pages.register_user;

public class UserData {
    String form_key;
    String success_url;
    String error_url;
    String firstname;
    String lastname;
    String email;
    String password;
    String password_confirmation;

    public UserData(String form_key, String success_url, String error_url, String firstname, String lastname, String email, String password, String password_confirmation) {
        this.form_key = form_key;
        this.success_url = success_url;
        this.error_url = error_url;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.password_confirmation = password_confirmation;
    }
}
