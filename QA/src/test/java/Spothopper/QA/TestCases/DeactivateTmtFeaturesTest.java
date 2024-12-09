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

import org.openqa.selenium.WebDriver;
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

 





public class DeactivateTmtFeaturesTest extends BaseTest {
	
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
				System.getProperty("user.dir") + "/data/deactivate_tmt.json");
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
        System.out.println("*******Deactivate TMT started!********");
        VariablesAndUrlsPage variablesAndUrlsPage = new VariablesAndUrlsPage(driver);
        CustomizeMenuPage customizeMenuPage = new CustomizeMenuPage(driver);
        SpecialsPage specialsPage =	new SpecialsPage(driver);
        ReadWriteFilePage readWriteFilePage = new ReadWriteFilePage(driver);
        NewMenusPage newMenusPage = new NewMenusPage(driver);
        String emailFromPopupOrJson = variablesAndUrlsPage.email;
        FeaturePage featurePage = new FeaturePage(driver);
        BusinessInfoPage businessInfoPage = new BusinessInfoPage(driver);
        
        
        //Google verification
        variablesAndUrlsPage.googleVerification(driver, emailFromPopupOrJson);
        Thread.sleep(2000);
        
        //Spothopper application
        Gson gson = new Gson();
	    FileReader reader = new FileReader("data/deactivate_tmt.json");
	    Type listType = new TypeToken<List<Map<String, String>>>(){}.getType();
	    List<Map<String, String>> websites = gson.fromJson(reader, listType);
	    String spotIdFromPopupOrJson;
	    String personalNameFromPopupOrJson;
	    for (int j = 0; j<websites.size(); j++) {
	    	spotIdFromPopupOrJson = websites.get(j).get("spot_id");
	    	personalNameFromPopupOrJson = websites.get(j).get("personal_name");
	        variablesAndUrlsPage.setUrls(driver,spotIdFromPopupOrJson); 
	      
	        
	        // Deactivate features
	        currentTimeString = getStringLocalDateTime();
	        //List<FeaturePage> tmtFeatures = readWriteFilePage.getFeaturesActiveList(driver,readWriteFilePage,spotIdFromPopupOrJson);
	        List<FeaturePage> tmtFeatures = featurePage.getTmtFeatures(driver);
	        variablesAndUrlsPage.goTo(variablesAndUrlsPage.privatePartiesSettingsURL);
	        if(j==0) {
	        	variablesAndUrlsPage.clickContinueWithGoogleButton(driver);
		 	    Thread.sleep(2000);
	        }
	        for(FeaturePage feature:tmtFeatures) {
	        	if(feature.shCtaName.equals("private parties")) { 
	        		if(readWriteFilePage.shouldDeactivateTmt(driver,"activatePrivateParties",spotIdFromPopupOrJson).equals("false")) {
	        			feature.deactivateTmtFeature(driver,"activatePrivateParties");
	        			feature.confirmDeactivation(driver);
	        			feature.clickSaveChanges(driver);	        		}
	        	}
	        	if(feature.shCtaName.equals("catering")) { 
	        		System.out.println(feature.shCtaName+" should deactivate "+feature.shouldDeactivate);
	        		if(readWriteFilePage.shouldDeactivateTmt(driver,"activateCatering",spotIdFromPopupOrJson).equals("false")) {
		        		variablesAndUrlsPage.goTo(variablesAndUrlsPage.cateringSettingsURL);
		        		feature.deactivateTmtFeature(driver,"activateCatering");
		        		feature.confirmDeactivation(driver);
		        		feature.clickSaveChanges(driver);
		        	}
	        	}
	        	if(feature.shCtaName.equals("reservations")) { 
	        		System.out.println(feature.shCtaName+" should deactivate "+feature.shouldDeactivate);
	        		if(readWriteFilePage.shouldDeactivateTmt(driver,"activateReservations",spotIdFromPopupOrJson).equals("false")) {
	        			variablesAndUrlsPage.goTo(variablesAndUrlsPage.reservationsSettingsURL);
		        		feature.deactivateTmtFeature(driver,"activateReservations");
		        		feature.confirmDeactivation(driver);
		        		feature.clickSaveChanges(driver);
	        		}
	        	}
	        	if(feature.shCtaName.equals("job listing")) { 
	        		System.out.println(feature.shCtaName+" should deactivate "+feature.shouldDeactivate);
	        		if(readWriteFilePage.shouldDeactivateTmt(driver,"activateJobsApplications",spotIdFromPopupOrJson).equals("false")) {
	        			variablesAndUrlsPage.goTo(variablesAndUrlsPage.jobApplicationsSettingsURL);
		        		feature.deactivateTmtFeature(driver,"activateJobsApplications");
		        		Thread.sleep(2000);		        		
		        		feature.confirmDeactivation(driver);
		        		feature.clickSaveJobsChanges(driver);
	        		}
	        		
	        	}
	        	if(feature.shCtaName.equals("order link 1")) {
	        		
	        		System.out.println(feature.shCtaName+" should deactivate "+feature.shouldDeactivate);
	        		if(readWriteFilePage.shouldDeactivateTmt(driver,"activateOnlineOrders",spotIdFromPopupOrJson).equals("false")) {
		        		variablesAndUrlsPage.goTo(variablesAndUrlsPage.ordersSettingsURL);
		        		feature.deactivateTmtFeature(driver,"activateOnlineOrders");
		        		Thread.sleep(2000);	
		        		feature.confirmDeactivation(driver);
		        		feature.clickSaveOrdersChanges(driver);
	        		}
	        	}
	        	
	        }	        
	    }
	    
        driver.close();
        System.out.println("Deactivate TMT closing ***********");
    }
	
}//close class
