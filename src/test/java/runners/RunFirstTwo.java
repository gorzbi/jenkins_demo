package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = {"stepDefinitions", "settings"}, // nazwa paczek, a nie pliku java
        monochrome = true, // sformatowane info w konsoli
        plugin = {"pretty", "html:src/main/resources/raporty/firstTwo.html"}, // ścieżka dla raportu
        tags="@first or @second"  // odpali wszystkie co mają ten tag, można stosować z poziomu features lub danego scenario
)

public class RunFirstTwo extends AbstractTestNGCucumberTests {

}