package settings;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;


public class Konfig {

    private static final Logger log = LoggerFactory.getLogger(Konfig.class);
    protected static WebDriver driver;

    String reportPath="src/main/resources/raporty/";

    // osobne wywołanie driver dla cucumber
    public static WebDriver getDriver() {

        return driver;
    }


    /*
     * wykonywane raz jako pierwsza czynność
     * inicjalizacja raportu
     */

    @BeforeSuite
    @Before
    public void start() {

        // rozwiązanie problemu z uruchamianiem na chrome
        System.setProperty("webdriver.http.factory", "jdk-http-client");

        System.out.println("Before suit");

    }

    // wykonywane raz jako druga czynność
    @BeforeTest
    public void startRaport() {

        System.out.println("Before test");
    }

    // wykonywane raz przed każdą klasą testową zawierającą metody testowe @Test
    @BeforeClass
    public void clas() {

        System.out.println("Before class");
    }

    /*
     * wykonywane raz przed każdą metodą testową @Test dla każdej klasy z testami
     * wybór przeglądarki
     * adres strony startowej
     */
    @BeforeMethod
    @Before
    public static void takeBrowser() {

        driver = DriverFactory.getDriver(DriverType.FIREFOX);
        driver.manage().window().maximize();

        System.out.println("Before method");
    }




    // ------------after

    /*
     * wykonywane raz po każdej metodzie testowej @Test dla każdej klasy z testami
     * pobranie statusu testu
     * zrobienie screena błędu
     * zamknięcie przeglądarki
     */

    @AfterMethod
    @After
    public void tearDown(Scenario scenario) {

        //validate if scenario has failed
        if(scenario.isFailed()) {

            String errorMessage = scenario.getStatus() + ": " + scenario.getName();
            scenario.log(errorMessage);

            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot");

        }

        driver.quit();
        System.out.println("After method");
    }


    // wykonywane raz po każdej klasie testowej zawierającej metody testowe @Test
    @AfterClass
    public void klas(){

        System.out.println("After class");
    }

    // wykonywane raz jako przedostatnia rzecz
    @AfterTest
    public void test() {
        System.out.println("After test");
    }

    /*
     * wykonywane raz jako ostatnia czynność
     * wygenerowanie raportu
     * info systemowe do raportu
     * automatyczne otwarcie raportu
     */
    @AfterSuite
    public void generateExtentReport() {

        System.out.println("After suite");

    }
}