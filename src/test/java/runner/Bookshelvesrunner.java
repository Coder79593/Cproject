package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/Bookshelves.feature",
		//features = "src/test/resources/features",
    glue = {"stepdefinition","hooks"},
    plugin = {"pretty", "html:reports/cucumber-reports.html",
    		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    		
    },
    dryRun=false,
    monochrome = true,
    publish=true
)
public class Bookshelvesrunner extends AbstractTestNGCucumberTests {
}
