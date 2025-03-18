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
import java.net.URLEncoder;
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
	
	public String menuNumber = "0";
	
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
     try {	
		System.out.println("*******Cta links started!********");
				
		//Initial settings
		VariablesAndUrlsPage variablesAndUrlsPage = new VariablesAndUrlsPage(driver);
		JiraCommentsPage jiraCommentsPage = new JiraCommentsPage(driver);
    	String emailFromPopupOrJson = variablesAndUrlsPage.myEmail;
    	//String emailFromPopupOrJson = variablesAndUrlsPage.email;
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
	    
	    //String errorMessage =" SUCCESS! ";
	    
	    // Google verification
        variablesAndUrlsPage.googleVerification(driver, emailFromPopupOrJson);
        Thread.sleep(2000);
        currentTimeString = getStringLocalDateTime();
        variablesAndUrlsPage.spothopperAppSignIn(driver);
        int firtsEntering=1;
        int errorOrderNumber=0;
        jiraCommentsPage.jiraSignIn(driver);
       
        
        String changeDate = "2025-03-12";
        String jql = "type = Publish AND summary ~ \"Go Live\" AND status = Done  AND statusCategoryChangedDate >= \""+changeDate+" 00:00\"AND statusCategoryChangedDate <= \""+changeDate+" 23:59\"";
        // comment out
        //jql = "issue in(WEB-166199)";
        
        String allKeyIssues = jiraCommentsPage.getKeyIssuesByApi(driver,jql,"");
        Thread.sleep(1000);
        //String tasksInJson = jiraCommentsPage.getTasksInJson(driver);
        //System.out.println("tasksInJson: "+tasksInJson);
        allKeyIssues = "issue in ("+allKeyIssues+")";
        
        List<String> spotIdCollection = new ArrayList<>();
        List<String> issueKeyCollection = new ArrayList<>();
        List<String> websiteUrlCollection = new ArrayList<>();
     
        String encodedJql = URLEncoder.encode(jql, "UTF-8");
        String apiQueryUrl = "https://spothopper.atlassian.net/rest/api/3/search?jql=" + encodedJql+"&maxResults=1000";
        int numberOfTasks = jiraCommentsPage.getIssueKeySpotIdWebsiteUrlFromApi(driver,apiQueryUrl,issueKeyCollection,spotIdCollection,websiteUrlCollection);
     
        jiraCommentsPage.goToWithResponseCode("https://spothopper.atlassian.net/issues/");
        Thread.sleep(1000);
       
        //int numberOfTasks = jiraCommentsPage.loadCollectionsCtaFromApi(driver,jql, spotIdCollection, issueKeyCollection, websiteUrlCollection);
        System.out.println("numberOfTasks: "+numberOfTasks);
        
        // domain      
        variablesAndUrlsPage.publishInfoLoginGoogle(driver);
        
        for (int j = 0; j<numberOfTasks; j++) {
        	List<FeaturePage> onlineOrderDropDownFaetures = new ArrayList<FeaturePage>();
        	List<FeaturePage> foodMenuDropDownFaetures = new ArrayList<FeaturePage>();
        	List<FeaturePage> drinkMenuDropDownFaetures = new ArrayList<FeaturePage>();
        	List<FeaturePage> privatePartiesDropDownFaetures = new ArrayList<FeaturePage>();
        	List<String> ctaLogMessage = new ArrayList<String>();
        	WebsiteFeaturesPage websiteFeaturesPageDesktop = new WebsiteFeaturesPage(driver);
        	//spotIdFromPopupOrJson = websites.get(j).get("spot_id");
        	spotIdFromPopupOrJson = spotIdCollection.get(j);
        	variablesAndUrlsPage.setUrls(driver,spotIdFromPopupOrJson); 
        	//websiteURL = ctaLinksPage.putHttpsOnUrlAndSlash(driver,websites.get(j).get("website_url"));
        	websiteURL = ctaLinksPage.putHttpsOnUrlAndSlash(driver,websiteUrlCollection.get(j));
        	websiteURL = ctaLinksPage.getDomainFromPublisInfo(driver,spotIdFromPopupOrJson,variablesAndUrlsPage.publishInfoUrl,websiteURL);
        	websiteURL = ctaLinksPage.putHttpsOnUrlAndSlash(driver,websiteURL);
        	//issueKey = websites.get(j).get("issue_key");
        	issueKey = issueKeyCollection.get(j);
        	String errorMessage = j+". issue key: "+issueKey+", spot id: "+spotIdFromPopupOrJson+", websiteURL: "+websiteURL;
        	System.out.println(errorMessage);
        	ctaLogMessage.add("\n"+errorMessage);
        	//Website desktop
 	        int responseCode = websiteFeaturesPageDesktop.goToWithResponseCode(websiteURL);
 	        Thread.sleep(2000);
 	        String currentUrl = driver.getCurrentUrl();
 	        if(!websiteURL.equals(currentUrl)) {
 	        	websiteURL = currentUrl;
 	        	System.out.println("websiteURL replaced current url: "+currentUrl);
 	        }
 	      
 	        if(responseCode==-1){
 	        	errorOrderNumber++;
 	        	errorMessage = errorMessage+", WEB PAGE CAN NOT BE OPENED!";
 	    		errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,errorOrderNumber);
 	    		continue;
 	        }        
 	        websiteFeaturesPageDesktop.closeNewsLetter(driver);
        	List<WebElementPage> navBarElements = websiteFeaturesPageDesktop.getAllementsFromNavBar(driver);
 	        if(navBarElements.isEmpty()) {
 	        	errorOrderNumber++;
 	        	errorMessage = errorMessage+", NO WEB ELEMENTS ON WEBSITE!";
 	        	errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,errorOrderNumber);
 	    		continue;}
 	        
 	        boolean hasDropDown = websiteFeaturesPageDesktop.hasDropDown(driver,navBarElements);
        	if(hasDropDown) {
        		websiteFeaturesPageDesktop.fillOrderDropDownList(driver,onlineOrderDropDownFaetures,navBarElements);
        		websiteFeaturesPageDesktop.fillFoodMenuDropDownList(driver,foodMenuDropDownFaetures,navBarElements);
        		websiteFeaturesPageDesktop.fillDrinkMenuDropDownList(driver, drinkMenuDropDownFaetures, navBarElements);
        		websiteFeaturesPageDesktop.fillPrivatePartiesDropDownList(driver, privatePartiesDropDownFaetures, navBarElements);
        		
            	System.out.println("onlineOrderDropDownFaetures: "+onlineOrderDropDownFaetures);
            	
            	if(onlineOrderDropDownFaetures.isEmpty()){ 
            		if(foodMenuDropDownFaetures.size()>1) {
            			errorOrderNumber++;
                		errorMessage = errorMessage+", FOOD DROP DOWN MENU IN NAV BAR!";
            			errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,errorOrderNumber);
            			continue;
        			}
            		if(drinkMenuDropDownFaetures.size()>1) {
            			errorOrderNumber++;
            			errorMessage = errorMessage+", DRINK DROP DOWN MENU IN NAV BAR!";
            			errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,errorOrderNumber);
            			continue;
            		}
            		
     	    	}            	
        	}
        
        	//check all links from home page
        	boolean linkProblem = websiteFeaturesPageDesktop.checkAllLinksFromHomePage(driver,navBarElements,ctaLogMessage); 
        	if(linkProblem){
        		errorOrderNumber++;
    			errorMessage = errorMessage+", PROBLEM WITH LINK ON HOME PAGE!";
    			errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,errorOrderNumber);
    			continue;
    		}
        	System.out.println("SH features from Home Page:");
    		List<FeaturePage> featurePageListOnWebsite = websiteFeaturesPageDesktop.updateFeatures(
    				driver,
    				navBarElements,
    				featurePageList);
   			List<FeaturePage> featuresNotOnNavBar = websiteFeaturesPageDesktop.getFeaturesNotOnNavBar(driver,featurePageListOnWebsite,featurePageList); 
   			System.out.println("Features which are not on the Nav bar:");
   			
   			websiteFeaturesPageDesktop.getFeaturesOnButtons(driver,featuresNotOnNavBar,featurePageListOnWebsite);
 	   		boolean giftCardsLinkTooLong = websiteFeaturesPageDesktop.checkGiftCardsFeature(driver,navBarElements);
	 	   	if(giftCardsLinkTooLong) {
 	   			errorOrderNumber++;
	    		errorMessage = errorMessage+", GIFT CARDS!";
	    		errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,errorOrderNumber);
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
 	    	boolean equalListsBeforeAndAfter = listOfAllFeaturesInSmartFooterBefore.equals(listOfAllFeaturesInSmartFooterAfter);
 	    	boolean conditionDrpoUp = websiteFeaturesPageIphone.hasDropUp(driver);
 	    	if(moreThanOneOrderLink && (onlineOrderDropDownFaetures.size() < numberOfOrderLinks)) {
 	    		errorOrderNumber++;
 	    		errorMessage = errorMessage+", INCOMPLETE NUMBER OF ORDER FOOTER LINKS!";
 	    		errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,errorOrderNumber);
 	 	    	continue;
 	    	}
 	    	if(conditionDrpoUp) {
 	    		errorOrderNumber++;
 	    		errorMessage = errorMessage+",DROP UP IN FOOTER LINKS!";
 	    		errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,errorOrderNumber);
 	 	    	continue;
 	    	}
 	    	if(!equalListsBeforeAndAfter && !moreThanOneOrderLink) {
 	    		if(listOfAllFeaturesInSmartFooterAfter.stream()
                        .map(String::trim)
                        .noneMatch("gift cards"::equals)) {
 	    			errorOrderNumber++;
 	    			errorMessage = issueKey+", NON TMT FEATURE IN FOOTER LINKS!";
 	 	    		errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,errorOrderNumber);
 	 	 	    	continue;
 	    		}
 	    	}
 	
    		setDesktopView(driver);
    		
	    	// CTA links
    		System.out.println("TMT features activation status:");
    		try{
    			ctaLinksPage.goTo(variablesAndUrlsPage.privatePartiesSettingsURL);
    		}catch (TimeoutException e) {
    			errorOrderNumber++;
    			errorMessage = errorMessage+", SH PAGE COULD NOT BE OPENED!";
 	 	    	errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,errorOrderNumber);
 	 	    	continue;
			}
	        websiteFeaturesPageDesktop.getActiveStatusTmtFeatures(driver,featurePageListOnWebsite,variablesAndUrlsPage,spotIdFromPopupOrJson);
		    ctaLinksPage.goTo(variablesAndUrlsPage.ctaLinksUrl);
	    	List<FeaturePage> featurePageListFDSE = websiteFeaturesPageDesktop.getFDSEFeatures(driver, featurePageListOnWebsite);
	        ctaLinksPage.enterFDSECtaLinks(driver, featurePageListFDSE,spotIdFromPopupOrJson,currentTimeString,websiteURL,websiteFeaturesPageDesktop,ctaLogMessage);
	        ctaLinksPage.saveChangesCtaLinks(driver);
	        Thread.sleep(1000);
	        ctaLinksPage.enterTmtServices(driver,featurePageListOnWebsite,websiteURL,spotIdFromPopupOrJson,ctaLogMessage);
	        Thread.sleep(1000);
	        ctaLinksPage.saveChangesCtaLinks(driver);
	        Thread.sleep(1000);
	        ctaLinksPage.removeCtaLinksFeaturesNotOnHomePage(driver,featuresNotOnNavBar,ctaLogMessage);
	        ctaLinksPage.saveChangesCtaLinks(driver);
	        Thread.sleep(1000);
	        String activeTmtDifferentLinks = ctaLinksPage.compareShAndWebsiteTmtLinks(driver,featurePageListOnWebsite,websiteURL,spotIdFromPopupOrJson);
	        if(activeTmtDifferentLinks=="domain") {
	        	errorOrderNumber++;
		    	errorMessage = errorMessage+", DIFFERENT LINKS WITH DOMAIN IN SH AND ON WEBSITE!";
		    	errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,errorOrderNumber);
 	    		continue;
		    }
	        if(activeTmtDifferentLinks == "spotapps") {
	        	errorOrderNumber++;
		    	errorMessage = errorMessage+", DIFFERENT LINKS WITH SPOTAPPS IN SH AND ON WEBSITE!";
		    	errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,errorOrderNumber);
 	    		continue;
		    }
	        
	        // website footer links in SH
		    boolean orderSideBySide = false;
		    if(!equalListsBeforeAndAfter && numberOfOrderLinks>1) {
		    	variablesAndUrlsPage.goTo(variablesAndUrlsPage.footerLinksURL);
		    	Thread.sleep(1000);
		    	ctaLinksPage.renameOrderLinks(driver,onlineOrderDropDownFaetures);
		    	orderSideBySide = ctaLinksPage.placeOredrsSideBySide(driver,numberOfOrderLinks);
		    	errorMessage = errorMessage + " ORDER FOOTER LINKS!";
		    	readWriteFilePage.createOrderDropDownFile(driver, currentTimeString, errorMessage);
		    }
		    if(orderSideBySide) {
		    	errorOrderNumber++;
		    	errorMessage = errorMessage+", ORDERS IN FOOTER SIDE BY SIDE!";
		    	errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,errorOrderNumber);
		    	continue;
		    }
		  
		    String featureName = websiteFeaturesPageDesktop.checkIfDeactivateTmtHasLink(driver,featurePageListOnWebsite);
		    if(!featureName.equals("")) {
		    	errorOrderNumber++;
		    	errorMessage = errorMessage+", "+featureName+" TOO LONG EXTERNAL LINK!";
		    	errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,errorOrderNumber);
		    	continue;
		    }
		    
		    // Activate Smart footer in SH
		    ctaLinksPage.goTo(variablesAndUrlsPage.websiteAdminPanelURL);
		    ctaLinksPage.activeWcache(driver);
		    boolean stillNotActiveSmartFooter = ctaLinksPage.clickIfNotActiveSmartFooter(driver);
		    if(stillNotActiveSmartFooter) {
		    	errorOrderNumber++;
		    	errorMessage = errorMessage+" STILL NOT ACTIVE SMART FOOTER!";
		    	errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,errorOrderNumber);
		    	continue;
		    }
		    Thread.sleep(1000);
		    try {
		    	boolean footerVersion1 = ctaLinksPage.footerVersion1(driver);
		    	if(footerVersion1) {
		    		ctaLinksPage.clickDropDownSmartFooterVersion(driver);
		    		boolean problemWithSmartFooterV2 = ctaLinksPage.click2SmartFooterVersion(driver);
		    		if(problemWithSmartFooterV2){
		    			errorOrderNumber++;
		    			errorMessage = errorMessage+" PROBLEM WITH V2 SMART FOOTER!";
				    	errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,errorOrderNumber);
				    	continue;
		    		}
		    	}
		    }catch(Exception e) {
		    	errorOrderNumber++;
		    	errorMessage = errorMessage+" PROBLEM WITH V2 SMART FOOTER!";
		    	errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,errorOrderNumber);
		    	continue;
		    }
	    	ctaLinksPage.saveAdminPanel(driver);
	    	Thread.sleep(1000);
	    	
	    	// Website smart footer
	    	websiteFeaturesPageDesktop.goToWithResponseCode(websiteURL);
	    	websiteFeaturesPageIphone.hardRefresh(websiteURL);
 	    	Thread.sleep(3000);
	    	setIphoneView(driver);
	    	//String targetError = websiteFeaturesPageIphone.checkTarget(driver,websiteURL);
	    	String targetError ="";
 	    	if(targetError.equals("self")) {
 	    		errorOrderNumber++;
 	 	    	errorMessage = errorMessage+", CHANGE TARGET TO SELF!";
 	 	    	errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,errorOrderNumber);
 	 	    	continue;
 	    	}
 	    	if(targetError.equals("spotapps")) {
 	    		errorOrderNumber++;
 	 	    	errorMessage = errorMessage+", SPOTAPPS TARGET LINK IN FOOTER!";
 	 	    	errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,errorOrderNumber);
 	 	    	continue;
 	    	}
 	    	if(targetError.equals("gift-cards")) {
 	    		errorOrderNumber++;
 	 	    	errorMessage = errorMessage+", GIFT CARDS TARGET LINK IN FOOTER!";
 	 	    	errorHandlingPage.addErrorWithBlockchain(driver, issueKey, errorMessage, readWriteFilePage, currentTimeString,blockchain,errorOrderNumber);
 	 	    	continue;
 	    	}
	    	
 	    	setDesktopView(driver);
	    	jiraCommentsPage.goToWithResponseCode("https://spothopper.atlassian.net/issues/"+issueKey);
	    	Thread.sleep(2000);
	    	jiraCommentsPage.clickAssignToMe(driver);
	    	Thread.sleep(1000);
	    	String ctaDoneComment = "CTA - Done\nSmart Footer - Done\nCTA done by Automation";
	    	jiraCommentsPage.enterComment(driver,ctaDoneComment);
	    	jiraCommentsPage.saveComment(driver);
	    	Thread.sleep(1000);
	    	System.out.println("<><><><><><> "+errorMessage+", SUCCESS! <><><><><><>");
        	readWriteFilePage.createCtaLinksFooterFile(driver, issueKey,currentTimeString,firtsEntering);
	    	jiraCommentsPage.clickTaskStatus(driver);
	    	Thread.sleep(1000);
	    	jiraCommentsPage.clickCloseStatus(driver);
	    
        	firtsEntering++;
        	readWriteFilePage.createCtaLogFile(driver,ctaLogMessage,currentTimeString);
         }// end of for loop
         
     }catch (Exception e) {
         e.printStackTrace();
     } //finally {
         //if (driver != null) {
             //driver.quit(); // Ensure the driver is closed gracefully
         //}
     //}
	     driver.close();
         
	     System.out.println("CTA links closing ***********");
     }// @Test

   }// class
