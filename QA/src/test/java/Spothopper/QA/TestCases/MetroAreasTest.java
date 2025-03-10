package Spothopper.QA.TestCases;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Spothopper.QA.PageObjects.MetroAreasPage;
import Spothopper.QA.PageObjects.VariablesAndUrlsPage;
import Spothopper.QA.TestComponents.BaseTest;

public class MetroAreasTest extends BaseTest{
	
	
	@Test()
    public void sinchronizeChanges() throws IOException, InterruptedException {
		
		System.out.println("*******Synchronizing SH app started!********");
		
		MetroAreasPage metroAreasPage = new MetroAreasPage(driver);
		VariablesAndUrlsPage variablesAndUrlsPage = new VariablesAndUrlsPage(driver);
		String emailFromPopupOrJson = variablesAndUrlsPage.myEmail;
		Gson gson = new Gson();
		FileReader reader = new FileReader("data/metro_areas.json");
		Type listType = new TypeToken<List<Map<String, String>>>(){}.getType();
		//List<Map<String, String>> spots = gson.fromJson(reader, listType);
		List<String> spots = new ArrayList<>(Arrays.asList(
			     "97257", "444081",
			    "443850", "437976", "365607", "425047", "138604", "108086", "437515", "437514", "394593", "299871",
			    "67803", "440254", "439824", "438702", "431510", "431509", "431575", "405009", "400401", "91384",
			    "393889", "439956", "434413", "367884", "102842", "440122", "445170", "440913", "431013", "125918",
			    "445236", "329541", "178335", "431739", "294525", "356730", "289080", "261616", "254628", "434412",
			    "413462", "87230", "444246", "434148", "427086", "362373", "447051", "437415", "440253", "432960",
			    "424578", "104742", "424677"
			));
		String spotId;
		
		
		// Google verification
		//String emailFromPopupOrJson = variablesAndUrlsPage.email;
        variablesAndUrlsPage.googleVerification(driver, emailFromPopupOrJson);
        Thread.sleep(2000);
       
        
        
        int firstGoogleAccess=0;
		for (int j = 0; j<spots.size(); j++) {			System.out.println("spot number "+j+".");
			//spotId = spots.get(j).get("spot_id");
			spotId = spots.get(j);
			variablesAndUrlsPage.setUrls(driver, spotId);
			metroAreasPage.goToWithResponseCode(variablesAndUrlsPage.businessInfoUrl);
			
			// First Google Access
	        if(firstGoogleAccess==0) {
	        	Thread.sleep(4000);
	        	variablesAndUrlsPage.clickContinueWithGoogleButton(driver);
		 	    Thread.sleep(2000);
		 	    firstGoogleAccess++;
	        }
	        Thread.sleep(1000);
	        metroAreasPage.closeHolidayPopupButton(driver);
	        metroAreasPage.clickCloseInstagramPopupButton(driver);
	        metroAreasPage.enterZero(driver);
	        Thread.sleep(2000);
	        metroAreasPage.saveChanges(driver);
		}
		driver.close();
	}
}
