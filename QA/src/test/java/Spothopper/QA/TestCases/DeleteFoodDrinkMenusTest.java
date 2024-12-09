package Spothopper.QA.TestCases;



import Spothopper.QA.PageObjects.*;
import Spothopper.QA.TestComponents.BaseTest;
import Spothopper.QA.TestComponents.Retry;
import net.bytebuddy.description.ModifierReviewable.OfAbstraction;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.module.Input;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
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
import java.util.AbstractMap.SimpleEntry;

import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.Arrays;





public class DeleteFoodDrinkMenusTest extends BaseTest {
	
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
				System.getProperty("user.dir") + "/data/delete_build.json");
		int numOfSpots = data.size();
		Object[][] dataArray = new Object[numOfSpots][1]; 
        for (int i = 0; i < numOfSpots; i++) {
            dataArray[i][0] = data.get(i);}
        return dataArray;
		//return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)},{data.get(3)},{data.get(4)},{data.get(5)},{data.get(6)}};
	}
	
	
	@Parameters({"dataProviderCounter"})
    @Test(dataProvider = "getData")
	public void deleteFoodDrinkMenus(HashMap<String, String> input) throws ParseException, IOException, InterruptedException {
		 System.out.println("***********Deleting started - Test is lounched from "+local_github);
		 VariablesAndUrlsPage variablesAndUrlsPage = new VariablesAndUrlsPage(driver);
         CustomizeMenuPage customizeMenuPage = new CustomizeMenuPage(driver);
         SpecialsPage specialsPage =	new SpecialsPage(driver);
         ReadWriteFilePage readWriteFilePage = new ReadWriteFilePage(driver);
         NewMenusPage newMenusPage = new NewMenusPage(driver);
         String emailFromPopupOrJson = variablesAndUrlsPage.email;
        
         //Google verification
         variablesAndUrlsPage.googleVerification(driver, emailFromPopupOrJson);
         Thread.sleep(2000);
	     
         //Spothopper application
         Gson gson = new Gson();
 	     FileReader reader = new FileReader("data/delete_build.json");
 	     Type listType = new TypeToken<List<Map<String, String>>>(){}.getType();
 	     List<Map<String, String>> websites = gson.fromJson(reader, listType);
 	     String spotIdFromPopupOrJson;
 	     //String personalNameFromPopupOrJson;
 	     for (int j = 0; j<websites.size(); j++) {
 	    	spotIdFromPopupOrJson = websites.get(j).get("spot_id");
 	    	//personalNameFromPopupOrJson = websites.get(j).get("personal_name");
 	        variablesAndUrlsPage.setUrls(driver,spotIdFromPopupOrJson); 
 	        customizeMenuPage.goTo(variablesAndUrlsPage.menusLandingURL);
 		    Thread.sleep(2000);
 	        if(j==0) {
 	        	
 	        	variablesAndUrlsPage.clickContinueWithGoogleButton(driver);
 		 	    Thread.sleep(2000);
 	        }
 	        
 	      //list of menus in application
 		  Thread.sleep(2000);
 		  ArrayList<String> allMenusInApp = customizeMenuPage.getFoodAndDrinkMenusFromApp(driver);
 		  ArrayList<String> foodAndDrinkMenusInApp = new ArrayList<String>();
 		  for(String menu:allMenusInApp) {
 			 if(menu.length()>=spotIdFromPopupOrJson.length()) {
		    	if(menu.substring(0,spotIdFromPopupOrJson.length()).equals(spotIdFromPopupOrJson))
		    		foodAndDrinkMenusInApp.add(menu);
 			  }
 		  }
 		 System.out.println("Lista menija koje treba obrisati: "+foodAndDrinkMenusInApp);
 		  
	     //deleting menus
	     for (String foodOrDrinkMenuName : foodAndDrinkMenusInApp) {
			   Thread.sleep(2000);
		       customizeMenuPage.clickPencilButton(driver, foodOrDrinkMenuName);
		       Thread.sleep(2000);
		       customizeMenuPage.clickConfigureSetHours(driver);
		       customizeMenuPage.clickDeleteMenuTrash(driver, production_staging);
		       customizeMenuPage.clickYesDeleteMenu(driver);
		       Thread.sleep(3000);
		       readWriteFilePage.writeDeleteStatusInFile(driver, foodOrDrinkMenuName);
		       System.out.println("menu " + foodOrDrinkMenuName + " deleted ***********");
		       customizeMenuPage.goTo(variablesAndUrlsPage.menusLandingURL);
		       Thread.sleep(2000);
 		    }
 		     
 		     //Specials
 		     specialsPage.goTo(variablesAndUrlsPage.specialsUrl);
 		     Thread.sleep(4000);
 		     List <WebElement> specialElementsToDelete = specialsPage.getTestSpecialElements(driver,
 		    		 variablesAndUrlsPage.spotID);   		 
 			 for(WebElement element : specialElementsToDelete) {
 				 String specialName = specialsPage.getSpecialName(driver, element);
 				 specialsPage.clickDeleteButton(driver, element, variablesAndUrlsPage.spotID);
 				 Thread.sleep(6000);
 				 specialsPage.clickYesDelete(driver);
 				 readWriteFilePage.writeDeleteStatusInFile(driver, specialName);
 			 }
 			 
 			//Events
 			 EventsPage eventsPage = new EventsPage(driver);
 		     eventsPage.goTo(variablesAndUrlsPage.eventsUrl);
 		     Thread.sleep(2000);
 		     List <WebElement> eventElementsToDelete = eventsPage.getTestEventElements(driver,
 		    		 variablesAndUrlsPage.spotID);   		 
 			 for(WebElement element : eventElementsToDelete) {
 				 String eventName = eventsPage.getEventName(driver, element);
 				 Thread.sleep(4000);
 				 variablesAndUrlsPage.scrollPageUncoverElement(driver, element);
 				 eventsPage.clickDeleteButton(driver, element, variablesAndUrlsPage.spotID);
 				 Thread.sleep(6000);
 				 eventsPage.clickYesDelete(driver);
 				 Thread.sleep(4000);
 				 readWriteFilePage.writeDeleteStatusInFile(driver, eventName);
 			 }
 			 
 			//Twitter
 			BusinessInfoPage businessInfoPage = new BusinessInfoPage(driver);
 	        businessInfoPage.goTo(variablesAndUrlsPage.businessInfoUrl);
 	        Thread.sleep(2000);
 	        businessInfoPage.clickCloseInstagramPopupButton(driver);
 	        if(businessInfoPage.getTextFromTwitterUserName(driver).equals("automation_test")) {
 	        	System.out.println("getTextFromTwitterUserName = "+businessInfoPage.getTextFromTwitterUserName(driver)); 
 	        	businessInfoPage.eraseTwitterUserName(driver);
 	        	Thread.sleep(2000);
 	        	System.out.println("getTextFromTwitterUserName = "+businessInfoPage.getTextFromTwitterUserName(driver));
 	        	businessInfoPage.clickSaveBusinessInfo(driver);
 	        	Thread.sleep(2000);
 	        	List <String> twitterUserNames = readWriteFilePage.getFilteredLogFiles(
 	        			variablesAndUrlsPage.spotID,
 	             		"TWITTER");
 	        	System.out.println("getFilteredLogFiles = "+twitterUserNames);
 	        	for(String twUserName : twitterUserNames){
 	        		 readWriteFilePage.writeDeleteStatusInFile(driver, 
 	        				 twUserName.substring(0,twUserName.length()-4));
 	        	}
 	        }
 	        
 	        //Instagram
 	        if(businessInfoPage.getTextFromInstagramUserName(driver).equals("instagram_automation_test")) {
 		      	System.out.println("getTextFromInstagramUserName = "
 		      			+businessInfoPage.getTextFromInstagramUserName(driver)); 
 		      	businessInfoPage.eraseInstagramUserName(driver);
 		      	Thread.sleep(2000);
 		      	System.out.println("getTextFromInstagramUserName = "
 		      			+businessInfoPage.getTextFromInstagramUserName(driver));
 		      	businessInfoPage.clickSaveBusinessInfo(driver);
 		      	Thread.sleep(2000);
 		      	List <String> instagramUserNames = readWriteFilePage.getFilteredLogFiles(
 		      			variablesAndUrlsPage.spotID,
 		           		"INSTAGRAM");
 		      	System.out.println("getFilteredLogFiles = "+instagramUserNames);
 		      	for(String inUserName : instagramUserNames){
 		      		 readWriteFilePage.writeDeleteStatusInFile(driver, 
 		      				 inUserName.substring(0,inUserName.length()-4));
 		      	}
 	        }
 	       System.out.println(j+"test");
 	    }
	     
	     
		 
	     //end 
		 driver.close();
	}
	
	
    
  
    


}//close class
