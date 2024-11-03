package stepdefinition;

import io.cucumber.java.en.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import factory.BaseClass;
import pages.GiftCardsPage;
import utility.excel;
import utility.readexcel;

public class GiftCardSteps  extends BaseClass{
	private static final Logger logger = Logger.getLogger(GiftCardSteps.class);
    WebDriver driver;
    GiftCardsPage giftCardsPage;

    @Given("the user navigates to the gift cards page")
    public void navigateToGiftCardsPage() throws IOException {
    	logger.info("Initializing WebDriver and setting up the page objects");
    	driver = initializeBrowser();
    	giftCardsPage = new GiftCardsPage(driver);
    }

    @When("the user clicks on the gift card link")
    public void clickGiftCardLink() throws InterruptedException, IOException {
    	logger.info("Starting test: selectGiftCardTest");
    	Assert.assertTrue(giftCardsPage.isGiftCardLinkDisplayed(),"GiftCard menu not Displayed");
        giftCardsPage.clickGiftCardLink();
    }

    @And("the user selects a birthday gift card")
    public void selectBirthdayGiftCard() throws InterruptedException, IOException {
    	logger.info("Selected gift card successfully");
    	Assert.assertTrue(giftCardsPage.isHbdGiftCardDisplayed(),"Happy Birthday GiftCard not Displayed");
        giftCardsPage.selectBirthdayGiftCard();
        
    }

    @Then("the gift card selection is successful")
    public void verifyGiftCardSelection() {
        System.out.println("Gift card selection successful.");
    }

    @When("the user fills recipient details with {string}, {string}, {string}, and {string}")
    public void fillRecipientDetails(String name, String phone, String email, String message) throws IOException, InterruptedException {
    	logger.info("Starting test: fillGiftDetailsTest");
    	String filePath = "Input_Excel/GiftCardInputData.xlsx";
        String sheetName = "Sheet1";
        String[][] data = readexcel.readExcelData(filePath, sheetName);
        Assert.assertTrue(giftCardsPage.isFormtextboxDisplayed(),"Form not Displayed");
        giftCardsPage.fillRecipientDetails(data[0][0], data[0][1],data[0][2],data[0][3]);
        giftCardsPage.fillSenderDetails(data[1][0], data[1][1],data[1][2]);
        logger.info("All data filled successfully");
    }
    
    

    @And("the user fills sender details with {string}, {string}, and {string}")
    public void fillSenderDetails(String name, String phone, String email) throws InterruptedException, IOException {
    	logger.info("Starting test: extractErrorMessagesToExcelTest");
        List<List<String>> errorData = new ArrayList<>();
        logger.info("Retrieved error messages successfully");


        List<String> header = new ArrayList<>();
        header.add("Error Type");
        header.add("Error Message");
        errorData.add(header);
        logger.info("Header added to excelData succesfully");


        List<String> row1 = new ArrayList<>();
        row1.add("Empty Email Error");
        row1.add(giftCardsPage.getEmptyEmailError());
        errorData.add(row1);

        List<String> row2 = new ArrayList<>();
        row2.add("Invalid Email Error");
        row2.add(giftCardsPage.getInvalidEmailError());
        errorData.add(row2);

        System.out.println("Extracted Error Messages:");
        System.out.println("Error Type: " + row1.get(0) + " | Message: " + row1.get(1));
        System.out.println("Error Type: " + row2.get(0) + " | Message: " + row2.get(1));
        
        String filePath = "Output_Excel/GiftCardErrors.xlsx";
        excel.writeToExcel(filePath, errorData);
        Assert.assertTrue(new File(filePath).exists(),"Excel file was not created successfully");
        logger.info("Data written to Excel file: " + filePath);
        System.out.println("Error messages written to Excel: " + filePath);
        logger.info("Excel file verified successfully");
        
    }
    
    @And("the denomination is selected")
    public void denominationSelected() throws InterruptedException {
    	giftCardsPage.selectDenominations();
    }

    @Then("the gift card details are filled successfully")
    public void verifyGiftCardDetailsFilled() {
        System.out.println("Gift card details filled successfully.");
        logger.info("Quiting the WebDriver");
    }
}


