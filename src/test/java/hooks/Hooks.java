package hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class Hooks extends BaseClass{
	
	WebDriver driver;
	@Before
	public void setUp() {
		driver = initializeBrowser();
    }
	
	@After
    public void tearDown() {
       if(driver != null) { 		
        quitBrowser();
       }
    }
	
    @AfterStep
    public void addScreenshot(Scenario scenario) {
        
        if(scenario.isFailed()) {
        	
        	TakesScreenshot ts=(TakesScreenshot) driver;
        	byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
        	scenario.attach(screenshot, "image/png",scenario.getName());
        	            
        }
    }      
}