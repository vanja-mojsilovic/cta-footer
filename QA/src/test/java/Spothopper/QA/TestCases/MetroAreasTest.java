package Spothopper.QA.TestCases;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
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
		Gson gson = new Gson();
		FileReader reader = new FileReader("data/metro_areas.json");
		Type listType = new TypeToken<List<Map<String, String>>>(){}.getType();
		List<Map<String, String>> spots = gson.fromJson(reader, listType);
		String spotId;
		
		
		// Google verification
		String emailFromPopupOrJson = variablesAndUrlsPage.email;
        variablesAndUrlsPage.googleVerification(driver, emailFromPopupOrJson);
        Thread.sleep(2000);
       
        
        
        int firstGoogleAccess=0;
		for (int j = 0; j<spots.size(); j++) {
			spotId = spots.get(j).get("spot_id");
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
