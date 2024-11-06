package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/Menu.feature",
    glue = {"stepdefinition","hooks"},
    plugin = {"pretty", "html:reports3/Menu-cucumber-reports.html",
    		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    		
    },
    dryRun=false,
    monochrome = true,
    publish=true
)
public class Menurunner extends AbstractTestNGCucumberTests {
}
