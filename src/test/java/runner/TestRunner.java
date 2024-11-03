package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/StudyChairs.feature",
	//	features = "src/test/resources/features",
    glue = {"stepdefinition","hooks"},
    plugin = {"pretty", "html:reports/cucumber-reports.html",
    		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    		
    },
    dryRun=false,
    monochrome = true,
    publish=true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
