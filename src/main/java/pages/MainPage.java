package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class MainPage {

    WebDriver driver;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void checkAfterCorrectLogin() {
        Assert.assertTrue(menuButton.isDisplayed());
    }
}