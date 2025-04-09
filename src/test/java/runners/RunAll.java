package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = {"stepDefinitions", "settings"}, // nazwa paczek, a nie pliku java
        monochrome = true, // sformatowane info w konsoli
        plugin = {"pretty", "html:src/main/resources/raporty/cucumber_all.html"} // ścieżka dla raportu
)

public class RunAll extends AbstractTestNGCucumberTests {

}