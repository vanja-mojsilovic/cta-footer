package Spothopper.QA.TestCases;



import Spothopper.QA.PageObjects.*;
import Spothopper.QA.TestComponents.BaseTest;
import Spothopper.QA.TestComponents.Retry;
import groovy.transform.Undefined.EXCEPTION;
import groovyjarjarantlr4.v4.parse.ANTLRParser.exceptionGroup_return;
import net.bytebuddy.description.ModifierReviewable.OfAbstraction;
import Spothopper.QA.Resources.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.github.dockerjava.api.model.Network;
import com.google.common.base.Optional;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.module.Input;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.jboss.aerogear.security.otp.Totp;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.AccessException;
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





public class BuildTest extends BaseTest {
	
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
	
	
	
	

    @Test()
    public void getWebsiteFeatures() throws IOException, InterruptedException {
    	
		System.out.println("*******Build test started!********");
				
		//Initial settings
		BuildPage buildPage = new BuildPage(driver);
		VariablesAndUrlsPage variablesAndUrlsPage = new VariablesAndUrlsPage(driver);
    	String emailFromPopupOrJson = variablesAndUrlsPage.email;
    	Blockchain blockchain = new Blockchain();
    	FeaturePage featurePage = new FeaturePage(driver);
    	List<FeaturePage> featurePageList = new ArrayList<>();
	    featurePage.fillfeaturePageList(featurePageList);
	    Gson gson = new Gson();
	    FileReader reader = new FileReader("data/build_websites.json");
	    ReadWriteFilePage readWriteFilePage = new ReadWriteFilePage(driver);
	    Type listType = new TypeToken<List<Map<String, String>>>(){}.getType();
	    List<Map<String, String>> websites = gson.fromJson(reader, listType);
	    CtaLinksPage ctaLinksPage = new CtaLinksPage(driver);
	    ErrorHandlingPage errorHandlingPage = new ErrorHandlingPage(driver);
	    String spotIdFromPopupOrJson; 
	    //String gitId;
	    String websiteURL;
	    String issueKey;
	  
	    
	    // Google verification
        variablesAndUrlsPage.googleVerification(driver, emailFromPopupOrJson);
        Thread.sleep(2000);
        currentTimeString = getStringLocalDateTime();
        int firstGoogleAccess=0;
        int firtsEntering=1;
        int errorOrderNumber=0;
        
        buildPage.goToWithResponseCode("https://spothopper.atlassian.net/");
    	Thread.sleep(3000);
    	buildPage.clickSignInGoogle(driver);
    
    	buildPage.clickGoogleAccunt(driver);
    	
    	//buildPage.clickContinueButton(driver);
    	
        for (int j = 0; j<websites.size(); j++) {
        	
        	
        	
        	
        	
        	
	       
         }// end of for loop 
	     //driver.close();
	     System.out.println("Build test closing ***********");
     }// @Test

   }// class
