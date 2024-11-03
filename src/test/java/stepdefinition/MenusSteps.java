package stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import factory.BaseClass;
import pages.MenusPage;
import utility.excel;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MenusSteps  extends BaseClass{
	private static final Logger logger = Logger.getLogger(MenusSteps.class);

    WebDriver driver;
    MenusPage menusPage;

    @Given("I open the browser with Menus page")
    public void i_open_the_browser_with_menus_page() throws IOException {
    	logger.info("Initializing WebDriver and setting up page objects");
    	driver = initializeBrowser();
    	menusPage = new MenusPage(driver);
    }
    
    @When("I hover over Luxury Menu")
    public void i_hover_over_luxury_menu() throws InterruptedException {
    	logger.info("Starting test: MenuTest");
    	Assert.assertTrue(menusPage.isLuxuryMenuDisplayed(),"Luxury menu not Displayed");
    	menusPage.hoverOverLuxuryMenu();
    }
    
    @When("I select the Sofas Menu")
    public void i_select_the_sofas_menu() throws InterruptedException {
    	Assert.assertTrue(menusPage.isSofaMenuDisplayed(),"Sofa submenu not Displayed");
    	menusPage.hoverAndClickSofasMenu();
    }

    @When("I extract all Submenu items to Excel")
    public void i_extract_all_menu_items_to_excel() throws IOException {
    	logger.info("Starting test: extractMenuItemsToExcel");
    	List<WebElement> menuItems = menusPage.getMenuItems(); 
        List<List<String>> excelData = new ArrayList<>();
        logger.info("Retrieved sub menu data successfully");


        for (int i = 0; i < Math.min(9, menuItems.size()); i++) {
            String menuItemName = menuItems.get(i).getText();
            System.out.println("Menu Item " + (i + 1) + ": " + menuItemName);


            List<String> rowData = new ArrayList<>();
            rowData.add("Menu Item " + (i + 1));
            rowData.add(menuItemName);
            excelData.add(rowData);
        }


        String filePath = "Output_Excel/MenuData.xlsx";
       
            excel.writeToExcel(filePath, excelData);
            logger.info("Data written to Excel file: " + filePath);
            Assert.assertTrue(new File(filePath).exists(),"Excel file was not created successfully");
            System.out.println("Menu data written to Excel: " + filePath);
            logger.info("Excel file Verified Successfully");
   
        
    }

    @Then("the menu data is saved successfully")
    public void the_menu_data_is_saved_successfully() {
        System.out.println("Menu items saved successfully.");
        logger.info("Quiting the WebDriver");
    }
    
    
}


