package stepDefinitions;

import dane.DaneLogin;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import settings.Konfig;

public class Login {

    WebDriver driver;
    public Login() {

        driver = Konfig.getDriver();
    }

    @Given("Login page")
    public void enter_login_page() {

        System.out.println("Entering login page");
        LoginPage lp = new LoginPage(driver);
        lp.enterLoginPage(driver);
    }

    // pobiera dane testowe z innego pliku, pobieranie zdefiniowane w metodzie z klasy page
    @When("Enter wrong credentials")
    public void enter_wrong_credentials(DaneLogin dane) {

        System.out.println("Entering wrong credentials");
        LoginPage lp = new LoginPage(driver);
        lp.loginWrongUser(dane);
    }
    @Then("Check wrong credentials message")
    public void check_wrong_credentials_message() {

        System.out.println("Checking wrong credentials message");
        LoginPage lp = new LoginPage(driver);
        lp.checkWrongPasswordErrorMessage();
    }

    // dane zdefiniowane tutaj na sztywno, przypisanie jako parametr metody z klasy page
    @When("Enter blocked user credentials")
    public void enter_blocked_user_credentials() {

        System.out.println("Entering blocked user credentials");
        LoginPage lp = new LoginPage(driver);
        lp.loginBlockedUser("locked_out_user", "secret_sauce");
    }
    @Then("Check blocked user message")
    public void check_blocked_user_message() {

        System.out.println("Checking blocked user message");
        LoginPage lp = new LoginPage(driver);
        lp.checkBlockedErrorMessage();
    }

    // dane testowe zdefiniowane w feature
    @When("Enter good manual credentials {},{}")
    public void enter_good_manual_credentials(String test1, String test2) {

        System.out.println("Entering manual good credentials");
        LoginPage lp = new LoginPage(driver);
        lp.loginManual(test1,test2);
    }

    // pobiera dane testowe z innego pliku, pobieranie zdefiniowane w metodzie z klasy page
    @When("Enter good credentials")
    public void enter_good_credentials(DaneLogin dane) {

        System.out.println("Entering good credentials");
        LoginPage lp = new LoginPage(driver);
        lp.loginGoodUser(dane);
    }

    @Then("Check after good login")
    public void check_after_good_login() {

        System.out.println("Checking login");
        MainPage mp = new MainPage(driver);
        mp.checkAfterCorrectLogin();
    }
}
