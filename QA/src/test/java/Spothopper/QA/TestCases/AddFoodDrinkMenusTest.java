package Spothopper.QA.TestCases;



import Spothopper.QA.PageObjects.*;
import Spothopper.QA.TestComponents.BaseTest;
//import Spothopper.QA.TestCases.VariablesAndUrls;
//import net.bytebuddy.description.ModifierReviewable.OfAbstraction;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
//import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
//import org.openqa.selenium.Alert;
//import org.openqa.selenium.By;
//import org.openqa.selenium.OutputType;

import com.google.gson.Gson;

import org.openqa.selenium.WebElement;

import java.time.LocalDate;

import org.apache.commons.io.FileUtils;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import com.google.gson.reflect.TypeToken;

 





public class AddFoodDrinkMenusTest extends BaseTest {
	
	// Variables
	
	
	
	public int dataProviderCounter;
	public int currentCounter = 0;
	public String menuName = "Dinner";
	public String menuItemName_1 = "Chicken Soup";
	public String menuItemPrice_1 = "35.00";
	public String menuItemName_2 = "Beef Soup";
	public String menuItemPrice_2 = "38.00";
	public String menuNumber = "0";
	public String creditCardNumber = "4242424242424242";
	public String expiryDate = "04/24";
	public String cvcNumber = "242";
	public String postalCode = "42424";
	public String orderNumber = "0";
	public List<String> parent_child_add_menus = new ArrayList<>();
	

	// Methods
		public int setDataProviderCounter(String num) {
			dataProviderCounter = Integer.parseInt(num);
			System.out.println("dataProviderCounter is: " + dataProviderCounter);
			return dataProviderCounter;
		}

		public int getCurrentCounter() {
			return currentCounter;
		}

		public int increaseCurrentCounter() {
			currentCounter++;
			return currentCounter;
		}
	

	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		System.out.println("*********getting data from json_" + getCurrentCounter());
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "/data/build_websites.json");
		int numOfSpots = data.size();
		Object[][] dataArray = new Object[numOfSpots][1]; 
        for (int i = 0; i < numOfSpots; i++) {
            dataArray[i][0] = data.get(i);}
        return dataArray;
		//return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)},{data.get(3)},{data.get(4)},{data.get(5)},{data.get(6)}};
	}
	
	@Parameters({"dataProviderCounter"})
    @Test(dataProvider = "getData")
	public void createMenusOnApp(HashMap<String, String> input) throws IOException, InterruptedException {
        System.out.println("*******Creating Menus started!********");
        VariablesAndUrlsPage variablesAndUrlsPage = new VariablesAndUrlsPage(driver);
        CustomizeMenuPage customizeMenuPage = new CustomizeMenuPage(driver);
        SpecialsPage specialsPage =	new SpecialsPage(driver);
        ReadWriteFilePage readWriteFilePage = new ReadWriteFilePage(driver);
        NewMenusPage newMenusPage = new NewMenusPage(driver);
        String emailFromPopupOrJson = variablesAndUrlsPage.email;
        FeaturePage featurePage = new FeaturePage(driver);
        BusinessInfoPage businessInfoPage = new BusinessInfoPage(driver);
        OnlineOrdersTest onlineOrdersTest = new OnlineOrdersTest();
        
        
        //Google verification
        variablesAndUrlsPage.googleVerification(driver, emailFromPopupOrJson);
        Thread.sleep(2000);
        
        //Spothopper application
        Gson gson = new Gson();
	    FileReader reader = new FileReader("data/build_websites.json");
	    Type listType = new TypeToken<List<Map<String, String>>>(){}.getType();
	    List<Map<String, String>> websites = gson.fromJson(reader, listType);
	    String spotIdFromPopupOrJson;
	    String personalNameFromPopupOrJson;
	    for (int j = 0; j<websites.size(); j++) {
	    	spotIdFromPopupOrJson = websites.get(j).get("spot_id");
	    	personalNameFromPopupOrJson = websites.get(j).get("personal_name");
	        variablesAndUrlsPage.setUrls(driver,spotIdFromPopupOrJson); 
	        
	        //Menus
            customizeMenuPage.goTo(variablesAndUrlsPage.menusLandingURL);
            if(j==0) {
	        	variablesAndUrlsPage.clickContinueWithGoogleButton(driver);
		 	    Thread.sleep(2000);
	        }
            for(int i=1;i<4;i++) {
            	Thread.sleep(2000);
            	customizeMenuPage.clickAddNewFoodMenu(driver);
            	Thread.sleep(2000);
            	parent_child_add_menus = customizeMenuPage.switchToChild(driver, parent_child_add_menus);
            	driver.switchTo().window(parent_child_add_menus.get(1));
            	currentTimeString = getStringLocalDateTime();
            	String food_menu_name = spotIdFromPopupOrJson+"_FOOD_"+i;
                newMenusPage.insertNewMenuDescription(driver, "menu_description_"+i);
                //onlineOrdersTest.setMenuItemName_1("Menu_item_1"); 
                newMenusPage.insertNewMenuName(driver, food_menu_name);
                newMenusPage.clickSaveButtonNewMenu(driver);
                Thread.sleep(3000);
                newMenusPage.insertNewMenuSectionName(driver, "Section_"+i);
                newMenusPage.insertNewMenuSectionDescription(driver, "section_description_"+i);
                newMenusPage.clickSaveButtonNewMenuSection(driver);
                Thread.sleep(3000);
                newMenusPage.insertNewMenuItemPrice(driver, "10.00");
                newMenusPage.insertNewMenuItemDescription(driver, "item_description_"+i);
                newMenusPage.insertNewMenuItemName(driver, "menu_item_"+i);
                newMenusPage.clickSaveButtonNewMenuItem(driver);
                Thread.sleep(3000);
                readWriteFilePage.createMenuFile(driver, food_menu_name,currentTimeString);
                driver.close();
                driver.switchTo().window(parent_child_add_menus.get(0));
                System.out.println(food_menu_name+" is set ***********");
                Thread.sleep(2000);
                customizeMenuPage.clickAddNewDrinkMenu(driver);
                Thread.sleep(2000);
            	parent_child_add_menus = customizeMenuPage.switchToChild(driver, parent_child_add_menus);
            	driver.switchTo().window(parent_child_add_menus.get(1));
            	currentTimeString = getStringLocalDateTime();
            	String drink_menu_name = spotIdFromPopupOrJson+"_DRINK_"+i;
                newMenusPage.insertNewMenuDescription(driver, "menu_description_"+i);
                onlineOrdersTest.setMenuItemName_1("Menu_item_1"); 
                newMenusPage.insertNewMenuName(driver, spotIdFromPopupOrJson+"_DRINK_"+i);
                newMenusPage.clickSaveButtonNewMenu(driver);
                Thread.sleep(4000);
                newMenusPage.insertNewMenuSectionName(driver, "Section_"+i);
                newMenusPage.insertNewMenuSectionDescription(driver, "section_description_"+i);
                newMenusPage.clickSaveButtonNewMenuSection(driver);
                Thread.sleep(3000);
                newMenusPage.insertNewMenuItemPrice(driver, "10.00");
                newMenusPage.insertNewMenuItemDescription(driver, "item_description_"+i);
                newMenusPage.insertNewMenuItemName(driver, "menu_item_"+i);
                newMenusPage.clickSaveButtonNewMenuItem(driver);
                Thread.sleep(3000);
                readWriteFilePage.createMenuFile(driver,drink_menu_name,currentTimeString);
                driver.close();
                driver.switchTo().window(parent_child_add_menus.get(0));
                System.out.println(drink_menu_name+" is set ***********");
            }
            
            //Events
            EventsPage eventsPage = new EventsPage(driver);
            for(int i=1;i<3;i++) {
    	    	String eventName = variablesAndUrlsPage.spotID+"_"
    	    			+getStringLocalDateTime()
    	    			+"_EVENT_"+i+"_"
    	    			+personalNameFromPopupOrJson;
    	        eventsPage.goTo(variablesAndUrlsPage.eventsUrl);
    	        Thread.sleep(2000);
    	        eventsPage.clickAddNewEvenet(driver);
    	        eventsPage.insertNewEventName(driver, spotIdFromPopupOrJson+"_EVENT_"+i);
    	        eventsPage.insertNewEventDescription(driver, i);
    	        eventsPage.clickEventStartDatePicker(driver);
    	        String dayOfTheMonth = String.format(
    	        		"%02d",
    	        		LocalDate.now().getDayOfMonth());
    	        Thread.sleep(2000);
    	        //eventsPage.clickEventDateInCalendar(driver, dayOfTheMonth);
    	        eventsPage.clickActiveDateInDatePicker(driver);
    	        eventsPage.clickCreateEvent(driver);
    	        readWriteFilePage.createMenuFile(driver, eventName,currentTimeString);
            }
	    }
        driver.close();
        System.out.println("Add menus closing ***********");
    }
	
}//close class
