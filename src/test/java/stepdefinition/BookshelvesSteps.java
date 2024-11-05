package stepdefinition;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import factory.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BookshelvesPage;
import utility.excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookshelvesSteps  extends BaseClass {
	private static final Logger logger = LogManager.getLogger(BookshelvesSteps.class);
    WebDriver driver;
    BookshelvesPage bookshelvesPage;

    @Given("I open the browser")
    public void i_open_the_browser() throws IOException {
    	logger.info("Initializing WebDriver and setting up the page objects");
    	driver = initializeBrowser();
    	bookshelvesPage = new BookshelvesPage(driver);

    }

    @And("I navigate to {string}")
    public void i_navigate_to(String url) {

    }

    @Then("I search for the {string}")
    public void i_search_for(String item) throws InterruptedException, IOException {
    	logger.info("Starting test: searchBookshelvesTest");
    	Assert.assertTrue(bookshelvesPage.isSearchtextDisplayed(),"Search not Displayed");
        bookshelvesPage.searchForBookshelves(item);
        logger.info("Search for 'bookshelves' performed successfully");
    }

    @Then("the search results are displayed")
    public void the_search_results_are_displayed() {

    }

    @When("I apply the price filter with max value {string}")
    public void i_apply_the_price_filter_with_max_value(String maxPrice) throws InterruptedException, IOException {
    	logger.info("Starting test: applyFilterTest");
    	bookshelvesPage.applyFilter();
    	Assert.assertTrue(bookshelvesPage.isPriceFilterDisplayed(),"Price Filter was not Displayed");
    	Assert.assertTrue(bookshelvesPage.isApplybtnDisplayed(),"Apply button was not Displayed");
        bookshelvesPage.applyPriceFilter(maxPrice);
        logger.info("Price Filter applied successfully");
    }

    @When("I sort the items by {string}")
    public void i_sort_the_items_by(String sortOrder) throws InterruptedException, IOException {
    	logger.info("Starting test: sortByLowestPriceTest");
    	Assert.assertTrue(bookshelvesPage.isSortbyfilterDisplayed(),"Sort by Filter was not Displayed");
        bookshelvesPage.sortByLowestPrice();
        logger.info("Sort by Filter applied successfully");
    }

    @When("I extract product names and prices")
    public void i_extract_product_names_and_prices() throws IOException {
    	logger.info("Starting test: extractBookshelfDetailsToExcelTest");
    	List<String> productNames = bookshelvesPage.getProductNames();
        List<String> productPrices = bookshelvesPage.getProductPrices();
        logger.info("Retrieved product names and prices successfully");

      
        List<List<String>> excelData = new ArrayList<>();
        List<String> header = new ArrayList<>();
        header.add("Product Name");
        header.add("Product Price");
        excelData.add(header);
        logger.info("Header added to excelData");

     
        for (int i = 0; i < Math.min(3, Math.min(productNames.size(), productPrices.size())); i++) {
            List<String> row = new ArrayList<>();
            String productName = productNames.get(i);
            String productPrice = productPrices.get(i);
            row.add(productName);
            row.add(productPrice);
            excelData.add(row);
            logger.info("Added product: " + productNames.get(i)   +  " with price: " + productPrices.get(i));
            
            System.out.println("Product " + (i + 1) + ": Name = " + productName + ", Price = " + productPrice);
        }


        String filePath = "Output_Excel/BookshelvesData.xlsx";
        excel.writeToExcel(filePath, excelData);
        logger.info("Data written to Excel file: " + filePath);
        Assert.assertTrue(new File(filePath).exists(),"Excel file was not created successfully");
        System.out.println("Bookshelf data written to Excel: " + filePath);
        logger.info("Excel file verified successfully");
    }

    @Then("I write the details to {string}")
    public void i_write_the_details_to(String filePath) {
        System.out.println("Bookshelf data written to Excel: " + filePath);
        logger.info("Closing the WebDriver");
    }
    
    
    
}
