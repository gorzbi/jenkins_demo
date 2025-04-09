package pages;

import dane.DaneLogin;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(id = "error")
    private WebElement errorElement;

    @FindBy(xpath = "//h3[contains(text(),'do not match any user')]")
    private WebElement wrongLoginErrorMessage;

    @FindBy(xpath = "//h3[contains(text(),'Sorry, this user has been locked out')]")
    private WebElement blockedLoginErrorMessage;


    WebDriver driver;
    DaneLogin daneLogin;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.daneLogin = new DaneLogin();
    }

    public void loginAuto(DaneLogin dane){
        usernameInput.clear();
        usernameInput.sendKeys(dane.getUsername());
        passwordInput.clear();
        passwordInput.sendKeys(dane.getPassword());
        loginButton.click();
    }

    public void loginManual(String user, String password){
        usernameInput.clear();
        usernameInput.sendKeys(user);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    public void error() {
        errorElement.click();
    }

    public void checkWrongPasswordErrorMessage() {
        Assert.assertTrue(wrongLoginErrorMessage.isDisplayed());

    }

    public void checkBlockedErrorMessage() {
        Assert.assertTrue(blockedLoginErrorMessage.isDisplayed());

    }
}