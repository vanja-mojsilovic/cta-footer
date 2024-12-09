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





public class DeleteCtaLinksTest extends BaseTest {
	
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
				System.getProperty("user.dir") + "/data/test_websites.json");
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
	System.out.println("***********Deleting CTA started - Test is lounched from "+local_github);
	VariablesAndUrlsPage variablesAndUrlsPage = new VariablesAndUrlsPage(driver);
    ReadWriteFilePage readWriteFilePage = new ReadWriteFilePage(driver);
    String emailFromPopupOrJson = variablesAndUrlsPage.email;
    WebsiteFeaturesPage websiteFeaturesPage = new WebsiteFeaturesPage(driver);
    CtaLinksPage ctaLinksPage = new CtaLinksPage(driver);
    FeaturePage featurePage = new FeaturePage(driver);
    List<FeaturePage> featurePageList = new ArrayList<>();
    
   
    //Google verification
    variablesAndUrlsPage.googleVerification(driver, emailFromPopupOrJson);
    Thread.sleep(2000);
     
    //Spothopper application
    Gson gson = new Gson();
    FileReader reader = new FileReader("data/cta_links.json");
    Type listType = new TypeToken<List<Map<String, String>>>(){}.getType();
    List<Map<String, String>> websites = gson.fromJson(reader, listType);
    String spotIdFromPopupOrJson;
    String personalNameFromPopupOrJson;
    
    featurePage.fillfeaturePageList(featurePageList);
    
    
    
    for (int j = 0; j<websites.size(); j++) {
    	spotIdFromPopupOrJson = websites.get(j).get("id");
 
    	personalNameFromPopupOrJson = websites.get(j).get("personal_name");
        variablesAndUrlsPage.setUrls(driver,spotIdFromPopupOrJson); 
	    
        // cta links
        ctaLinksPage.goTo(variablesAndUrlsPage.ctaLinksUrl);
        if(j==0) {
        	variablesAndUrlsPage.clickContinueWithGoogleButton(driver);
	 	    Thread.sleep(2000);
        }
        
 	    System.out.println(j+"test");
 	 }
	
	     //end 
		 driver.close();
	}
	
	
    
  
    


}//close class
