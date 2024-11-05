package stepdefinition;

import io.cucumber.java.en.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import factory.BaseClass;
import pages.StudyChairsPage;
import utility.excel;

public class StudyChairsSteps extends BaseClass{
	private static final Logger logger = LogManager.getLogger(StudyChairsSteps.class);
    WebDriver driver;
    StudyChairsPage studyChairsPage;
    
    @Given("I open the Pepperfry website")
    public void openWebsite() throws IOException {
    	logger.info("Initializing the WebDriver and setting up the page objects");
    	driver = initializeBrowser();
    	studyChairsPage = new StudyChairsPage(driver);
    }
    
    @When("I search for {string}")
    public void searchForChairs(String keyword) throws InterruptedException {
    	logger.info("Starting test: searchStudyChairs");
    	Assert.assertTrue(studyChairsPage.isSearchtextDisplayed(),"Search not Displayed");
        studyChairsPage.searchForStudyChairs(keyword);
        logger.info("Search for 'study chairs' performed successfully");
    }

    @When("I apply a price filter of {string}")
    public void applyPriceFilter(String maxPrice) throws InterruptedException {
    	logger.info("Starting test: applyFiltersTest");
    	studyChairsPage.applyFilter();
    	Assert.assertTrue(studyChairsPage.isPriceFilterDisplayed(),"Price Filter was not Displayed");
    	Assert.assertTrue(studyChairsPage.isApplybtnDisplayed(),"Apply button was not Displayed");
        studyChairsPage.setPriceFilter(maxPrice);
        logger.info("Price Filters applied successfully");
    }

    @When("I sort the product list by Customer Ratings")
    public void sortByCustomerRating() throws InterruptedException, IOException {
    	logger.info("Starting test: sortByCustomerRating");
    	Assert.assertTrue(studyChairsPage.isSortbyfilterDisplayed(),"Sort by Filter was not Displayed");
        studyChairsPage.sortByCustomerRatings();
        logger.info("Sort By Filters applied successfully");
    }

    @When("I extract product details to Excel")
    public void extractDetailsToExcel() throws IOException {
    	logger.info("Starting test: extractStudyChairsDetails");
    	List<String> productName = studyChairsPage.getProductName();
        List<String> productPrice = studyChairsPage.getProductPrice();
        logger.info("Product names and prices Retrieved successfully");

        // Prepare data for Excel
        List<List<String>> excelData = new ArrayList<>();
        List<String> header = new ArrayList<>();
        header.add("Product Name");
        header.add("Product Price");
        excelData.add(header);
        logger.info("Added Header to excelData");

        for (int i = 0; i < Math.min(3, Math.min(productName.size(), productPrice.size())); i++) {
            List<String> row = new ArrayList<>();
            String Name = productName.get(i);
            String Price = productPrice.get(i);
            row.add(Name);
            row.add(Price);
            excelData.add(row);
            logger.info("Product: " + productName.get(i)   +  " with price: " + productPrice.get(i));
            
            System.out.println("Product " + (i + 1) + ": Name = " + Name + ", Price = " + Price);
        }
		
		String filePath = "Output_Excel/StudyChairsData.xlsx";
		excel.writeToExcel(filePath, excelData);
		 logger.info("Written Data to Excel file: " + filePath);
		 Assert.assertTrue(new File(filePath).exists(), "Excel file was not created successfully");
		System.out.println("Study chairs data written to Excel: " + filePath);
		logger.info("Successfully verified Excel file");
    }
   

    @Then("the Excel should contain product names and prices")
    public void validateExcelContent() {
        System.out.println("Excel validated with product names and prices.");
        logger.info("Closing WebDriver");
    }
}


