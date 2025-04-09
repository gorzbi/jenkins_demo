package dane;

public class DaneLogin {

    //wrong user
    String wrongUsername = "elo";
    String wrongPassword = "elo";

    public String getWrongUsername() {
        return wrongUsername;
    }
    public String getWrongPassword() {
        return wrongPassword;
    }

    //ok user
    String username = "standard_user";
    String password = "secret_sauce";

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}