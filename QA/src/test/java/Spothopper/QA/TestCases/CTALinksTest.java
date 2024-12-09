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





public class CTALinksTest extends BaseTest {
	
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
				System.getProperty("user.dir") + "/data/cta_links.json");
		int numOfSpots = data.size();
		Object[][] dataArray = new Object[numOfSpots][1]; 
        for (int i = 0; i < numOfSpots; i++) {
            dataArray[i][0] = data.get(i);}
        return dataArray;
		//return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)},{data.get(3)},{data.get(4)},{data.get(5)},{data.get(6)}};
	}
	
	//@Parameters({"dataProviderCounter"})
    //@Test(dataProvider = "getData")
    @Test()
    //public void getWebsiteFeatures(HashMap<String, String> input) throws IOException, InterruptedException {
    public void getWebsiteFeatures() throws IOException, InterruptedException {
    	
		System.out.println("*******Cta links started!********");
				
		//Initial settings
		VariablesAndUrlsPage variablesAndUrlsPage = new VariablesAndUrlsPage(driver);
    	String emailFromPopupOrJson = variablesAndUrlsPage.email;
    	Blockchain blockchain = new Blockchain();
    	FeaturePage featurePage = new FeaturePage(driver);
    	List<FeaturePage> featurePageList = new ArrayList<>();
	    featurePage.fillfeaturePageList(featurePageList);
	    Gson gson = new Gson();
	    FileReader reader = new FileReader("data/cta_links.json");
	    ReadWriteFilePage readWriteFilePage = new ReadWriteFilePage(driver);
	    Type listType = new TypeToken<List<Map<String, String>>>(){}.getType();
	    List<Map<String, String>> websites = gson.fromJson(reader, listType);
	    CtaLinksPage ctaLinksPage = new CtaLinksPage(driver);
	    ErrorHandlingPage errorHandlingPage = new ErrorHandlingPage(driver);
	    String spotIdFromPopupOrJson; 
	    //String gitId;
	    String websiteURL;
	    String issueKey;
	    String errorMessage =" SUCCESS! ";
	    
	    // Google verification
        variablesAndUrlsPage.googleVerification(driver, emailFromPopupOrJson);
        Thread.sleep(2000);
        currentTimeString = getStringLocalDateTime();
        int firstGoogleAccess=0;
        int firtsEntering=1;
        for (int j = 0; j<websites.size(); j++) {
        	errorMessage =" SUCCESS! ";
        	List<FeaturePage> onlineOrderDropDownFaetures = new ArrayList<FeaturePage>();
        	List<FeaturePage> foodMenuDropDownFaetures = new ArrayList<FeaturePage>();
        	List<FeaturePage> drinkMenuDropDownFaetures = new ArrayList<FeaturePage>();
        	spotIdFromPopupOrJson = websites.get(j).get("spot_id");
        	variablesAndUrlsPage.setUrls(driver,spotIdFromPopupOrJson); 
        	websiteURL = ctaLinksPage.putDashOnEndOfUrl(driver,websites.get(j).get("website_url"));
        	System.out.println(websiteURL+", website number "+(j)+".");
        	issueKey = websites.get(j).get("issue_key");
        	
        	//Website desktop
 	        WebsiteFeaturesPage websiteFeaturesPageDesktop = new WebsiteFeaturesPage(driver);
 	        Thread.sleep(1000);
 	        int responseCode = websiteFeaturesPageDesktop.goToWithResponseCode(websiteURL);
 	        Thread.sleep(2000);
 	      
 	        if(responseCode==-1){
 	        	errorMessage = issueKey+", WEB PAGE CAN NOT BE OPENED!";
 	    		errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,(j+1));
 	    		continue;
 	        }        
 	        
        	List<WebElementPage> navBarElements = websiteFeaturesPageDesktop.getAllementsFromNavBar(driver);
 	        if(navBarElements.isEmpty()) {
 	        	errorMessage = issueKey+", NO WEB ELEMENTS ON WEBSITE!";
 	        	errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,(j+1));
 	    		continue;}
 	        
 	        boolean hasDropDown = websiteFeaturesPageDesktop.hasDropDown(driver,navBarElements);
        	if(hasDropDown) {
        		websiteFeaturesPageDesktop.fillOrderDropDownList(driver,onlineOrderDropDownFaetures,navBarElements);
        		websiteFeaturesPageDesktop.fillFoodMenuDropDownList(driver,foodMenuDropDownFaetures,navBarElements);
        		websiteFeaturesPageDesktop.fillDrinkMenuDropDownList(driver, drinkMenuDropDownFaetures, navBarElements);
        		
            	System.out.println("onlineOrderDropDownFaetures: "+onlineOrderDropDownFaetures);
            	if(onlineOrderDropDownFaetures.isEmpty()){ 
            		if(foodMenuDropDownFaetures.size()>1) {
                		errorMessage = issueKey+", FOOD MENU IN NAV BAR!";
            			errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,(j+1));
            			continue;
        			}
            		if(!(foodMenuDropDownFaetures.size()==1 && drinkMenuDropDownFaetures.size()==1)) {
            			errorMessage = issueKey+", DROP DOWN IN NAV BAR!";
            			errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,(j+1));
            			continue;
            		}
     	    	}
        	}
        
        	//check all links from home page
        	boolean linkProblem = websiteFeaturesPageDesktop.checkAllLinksFromHomePage(driver,navBarElements); 
        	if(linkProblem){
    			errorMessage = issueKey+", PROBLEM WITH LINK ON HOME PAGE!";
    			errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,(j+1));
    			continue;
    		}
        	
    		List<FeaturePage> featurePageListOnWebsite = websiteFeaturesPageDesktop.updateFeatures(
    				driver,
    				navBarElements,
    				featurePageList);
   			List<FeaturePage> featuresNotOnNavBar = websiteFeaturesPageDesktop.getFeaturesNotOnNavBar(driver,featurePageListOnWebsite,featurePageList); 
   			System.out.println("Features which are not on the Nav bar:");
   			websiteFeaturesPageDesktop.closeNewsLetter(driver);
   			websiteFeaturesPageDesktop.getFeaturesOnButtons(driver,featuresNotOnNavBar,featurePageListOnWebsite);
 	   		boolean giftCardsLinkTooLong = websiteFeaturesPageDesktop.checkGiftCardsFeature(driver,navBarElements);
	 	   	if(giftCardsLinkTooLong) {
		    		errorMessage = issueKey+", GIFT CARDS!";
		    		errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,(j+1));
		    		continue; 
		    }
	        
	        // Website smart footer
		    setIphoneView(driver);
        	WebsiteFeaturesPage websiteFeaturesPageIphone = new WebsiteFeaturesPage(driver);   
        	websiteFeaturesPageIphone.hardRefresh(websiteURL);
 	    	Thread.sleep(3000);
 	    	websiteFeaturesPageIphone.closeNewsLetter(driver);
 	    	List<String> listOfAllFeaturesInSmartFooterBefore = websiteFeaturesPageIphone.getAllSmartFooterFeatureNames(driver);
 	    	websiteFeaturesPageIphone.goTo(websiteURL+"#smartFooterV2=enabled");
 	    	websiteFeaturesPageIphone.hardRefresh(websiteURL+"#smartFooterV2=enabled");
 	    	Thread.sleep(3000);
 	    	websiteFeaturesPageIphone.closeNewsLetter(driver);
 	    	List<String> listOfAllFeaturesInSmartFooterAfter = websiteFeaturesPageIphone.getAllSmartFooterFeatureNames(driver);
 	    	websiteFeaturesPageIphone.getAllFooterFeatures(driver,featurePageListOnWebsite);
 	    	System.out.println("listOfAllFeaturesInSmartFooter BEFORE activating Smart footer V2:");
 	    	System.out.println(listOfAllFeaturesInSmartFooterBefore);
 	    	System.out.println("listOfAllFeaturesInSmartFooter AFTER activating Smart footer V2:");
 	    	System.out.println(listOfAllFeaturesInSmartFooterAfter);
 	    	long numberOfOrderLinks = listOfAllFeaturesInSmartFooterAfter.stream().filter("order"::equals).count();
 	    	boolean moreThanOneOrderLink = numberOfOrderLinks>1;
 	    	boolean equalListsBeforeAndAfter=listOfAllFeaturesInSmartFooterBefore.equals(listOfAllFeaturesInSmartFooterAfter);
 	    	boolean conditionDrpoUp = websiteFeaturesPageIphone.hasDropUp(driver);
 	    	if(moreThanOneOrderLink && (onlineOrderDropDownFaetures.size()<numberOfOrderLinks)) {
 	    		errorMessage = issueKey+", ORDER FOOTER LINKS!";
 	    		errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,(j+1));
 	 	    	continue;
 	    	}
 	    	if(conditionDrpoUp) {
 	    		errorMessage = issueKey+",DROP UP IN FOOTER LINKS!";
 	    		errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,(j+1));
 	 	    	continue;
 	    	}
 	    	if(!equalListsBeforeAndAfter && !moreThanOneOrderLink) {
 	    		if(listOfAllFeaturesInSmartFooterAfter.stream()
                        .map(String::trim)
                        .noneMatch("gift cards"::equals)) {
 	    			errorMessage = issueKey+", NON TMT FEATURE IN FOOTER LINKS!";
 	 	    		errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,(j+1));
 	 	 	    	continue;
 	    		}
 	    	}
 	    	boolean targetError=websiteFeaturesPageIphone.checkTarget(driver,websiteURL);
 	    	if(targetError) {
 	 	    	errorMessage = issueKey+", CHANGE TARGET TO SELF!";
 	 	    	errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,(j+1));
 	 	    	continue;
 	    	}
    		setDesktopView(driver);
    		
	    	// CTA links
    		try{
    			ctaLinksPage.goTo(variablesAndUrlsPage.privatePartiesSettingsURL);
    		}catch (TimeoutException e) {
    			errorMessage = issueKey+", SH PAGE COULD NOT BE OPENED!";
 	 	    	errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,(j+1));
 	 	    	continue;
			}
    		// First Google Access
	        if(firstGoogleAccess==0) {
	        	Thread.sleep(4000);
	        	variablesAndUrlsPage.clickContinueWithGoogleButton(driver);
		 	    Thread.sleep(2000);
		 	    firstGoogleAccess++;
	        } 
	        System.out.println("TMT features activation status:");
	        websiteFeaturesPageDesktop.getActiveStatusTmtFeatures(driver,featurePageListOnWebsite,variablesAndUrlsPage);
		    ctaLinksPage.goTo(variablesAndUrlsPage.ctaLinksUrl);
		    String activeTmtDifferentLinks = ctaLinksPage.compareShAndWebsiteTmtLinks(driver,featurePageListOnWebsite,websiteURL,spotIdFromPopupOrJson);
		    if(activeTmtDifferentLinks=="domain") {
		    	errorMessage = issueKey+", DIFFERENT LINKS WITH DOMAIN IN SH AND ON WEBSITE!";
		    	errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,(j+1));
 	    		continue;
		    }
		    if(activeTmtDifferentLinks == "spotapps") {
		    	errorMessage = issueKey+", DIFFERENT LINKS WITH SPOTAPPS IN SH AND ON WEBSITE!";
		    	errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,(j+1));
 	    		continue;
		    }
	    	List<FeaturePage> featurePageListFDSE = websiteFeaturesPageDesktop.getFDSEFeatures(driver, featurePageListOnWebsite);
	        ctaLinksPage.enterFDSECtaLinks(driver, featurePageListFDSE,spotIdFromPopupOrJson,currentTimeString,websiteURL,websiteFeaturesPageDesktop);
	        ctaLinksPage.saveChangesCtaLinks(driver);		    
		    
	        // website footer links in SH
		    boolean orderSideBySide = false;
		    if(!equalListsBeforeAndAfter && numberOfOrderLinks>1) {
		    	variablesAndUrlsPage.goTo(variablesAndUrlsPage.footerLinksURL);
		    	Thread.sleep(1000);
		    	ctaLinksPage.renameOrderLinks(driver,onlineOrderDropDownFaetures);
		    	orderSideBySide = ctaLinksPage.placeOredrsSideBySide(driver,numberOfOrderLinks);
		    }
		    if(orderSideBySide) {
		    	errorMessage=issueKey+", ORDERS IN FOOTER SIDE BY SIDE!";
		    	errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,(j+1));
		    	continue;
		    }
		  
		    String featureName = websiteFeaturesPageDesktop.checkIfDeactivateTmtHasLink(driver,featurePageListOnWebsite);
		    if(!featureName.equals("")) {
		    	errorMessage = issueKey+", "+featureName+" TOO LONG EXTERNAL LINK!";
		    	errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,(j+1));
		    	continue;
		    }
		    
		 // Activate Smart footer in SH
		    ctaLinksPage.goTo(variablesAndUrlsPage.websiteAdminPanelURL);
		    ctaLinksPage.activeWcache(driver);
		    boolean stillNotActiveSmartFooter = ctaLinksPage.clickIfNotActiveSmartFooter(driver);
		    if(stillNotActiveSmartFooter) {
		    	errorMessage=issueKey+" STILL NOT ACTIVE SMART FOOTER!";
		    	errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,(j+1));
		    	continue;
		    }
		    Thread.sleep(1000);
		    try {
		    	boolean footerVersion1 = ctaLinksPage.footerVersion1(driver);
		    	if(footerVersion1) {
		    		ctaLinksPage.clickDropDownSmartFooterVersion(driver);
		    		boolean problemWithSmartFooterV2 = ctaLinksPage.click2SmartFooterVersion(driver);
		    		if(problemWithSmartFooterV2){
		    			errorMessage=issueKey+" PROBLEM WITH V2 SMART FOOTER!";
				    	errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,(j+1));
				    	continue;
		    		}
		    	}
		    }catch(Exception e) {
		    	errorMessage=issueKey+" PROBLEM WITH V2 SMART FOOTER!";
		    	errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,(j+1));
		    	continue;
		    }
	    	
	    	
	    	ctaLinksPage.saveAdminPanel(driver);
	    	
	    	// error message
        	System.out.println("<><><> "+issueKey+","+errorMessage+" <><><>");
        	readWriteFilePage.createCtaLinksFooterFile(driver, issueKey,currentTimeString,firtsEntering);
        	firtsEntering++;
	       
         }// end of for loop 
	     driver.close();
	     System.out.println("CTA links closing ***********");
     }// @Test

   }// class
