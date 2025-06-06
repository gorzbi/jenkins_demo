package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = {"stepDefinitions", "settings"}, // nazwa paczek, a nie pliku java
        monochrome = true, // sformatowane info w konsoli
        plugin = {"pretty",
                "html:src/main/resources/raporty/first.html", // ścieżka do raportu
                "json:target/cucumber.json"}, // ścieżka do raportu dla Jenkins
        tags="@first" // odpali wszystkie co mają ten tag, można stosować z poziomu features lub danego scenario
)

public class RunFirst extends AbstractTestNGCucumberTests {

}