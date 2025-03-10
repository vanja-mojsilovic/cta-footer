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





public class PlaceHolderTest extends BaseTest {
	
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
		
		
		
		

	    @Test()
	    public void getWebsiteFeatures() throws IOException, InterruptedException {
	    	
			System.out.println("*******Place Holder started!********");
					
			//Initial settings
			BuildPage buildPage = new BuildPage(driver);
			JiraCommentsPage jiraCommentsPage = new JiraCommentsPage(driver);
			VariablesAndUrlsPage variablesAndUrlsPage = new VariablesAndUrlsPage(driver);
			GithubIssuePage githubIssuePage = new GithubIssuePage(driver);
	    	String emailFromPopupOrJson = variablesAndUrlsPage.myEmail;
	    	//String emailFromPopupOrJson = variablesAndUrlsPage.email;
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
		    String spotId; 
		    //String gitId;
		    String websiteURL;
		    String issueKey;
		
		    // Google verification
	        variablesAndUrlsPage.googleVerification(driver, emailFromPopupOrJson);
	        Thread.sleep(2000);
	        currentTimeString = getStringLocalDateTime();
	        jiraCommentsPage.goToWithResponseCode(variablesAndUrlsPage.githubIssueUrl);
	        Thread.sleep(2000);
	        githubIssuePage.githubVerificationWithAuth(driver,emailFromPopupOrJson);
	        Thread.sleep(2000);
	      
	        int firtsEntering=1;
	        int errorOrderNumber=0;
	        List<String> testSiteUrls = new ArrayList<String>();
	        variablesAndUrlsPage.spothopperAppSignIn(driver);
	        jiraCommentsPage.jiraSignIn(driver);
	        jiraCommentsPage.goToWithResponseCode("https://spothopper.atlassian.net/issues/?jql=ORDER%20BY%20created%20DESC");
	        Thread.sleep(1000);
	        String changeDate = "2025-02-28";
	        String jqlBuildsFilter = " labels NOT IN (WordPress,LocationLanding,LocationPicker,LandingBuild) AND statusCategoryChangedDate >= \""+changeDate+" 00:00\"  AND statusCategoryChangedDate <= \""+changeDate+" 23:59\" AND issuetype in (Epic, LandingAG, Redesign) AND status = QA AND assignee not in (6201161deaf9e20070737924, 624ab599fd5e45007046851a, 63bbd7b824db796721235e13,63106e0e55b0a9e29f1ae60d, 6405a88c2847866310ffdcb1, 642ac9ce551f476a04685954, 638478162acfad92d7b2a41c, 712020:2ec53619-4525-4e3f-bbea-f57f209074ef,712020:94bcabc8-7a59-4228-b064-20fff37454d0, 712020:6cffa8dc-7b35-4c76-a7af-1b9816fd9dbc, 712020:f77ed01f-96d6-492b-a3a8-8bc5af83ea77, 6201161cf5d29a0068fa75b3,712020:cd832742-7b26-410d-8e24-63fb09a948e4, 642ac9ce9f314a0a30144195, 633aac93748d1bfcb85b0f7a, 633ff94e8b75455be459503a, 6368fdd911c69c741844dccb,712020:43b4ceca-2c92-4efa-b804-87c11ad183dc, 712020:cd92f95f-f13e-4334-a6e6-99e1385bbae7, 712020:024ca126-b2f8-4878-a896-c83c4aeeeb39, 712020:ff3bf219-07e4-48f2-bce7-90c84915e2fc,712020:d5d4d64f-73ab-4947-9664-3face76684af, 712020:8ee9b3a3-39c6-4c00-bc98-ba7a481838a1,712020:28f2889d-6895-472a-a1eb-cf5a61c975eb, 712020:6a18abe4-4aaa-4125-9a51-768090e8796e,712020:f8d0a823-a2b3-468e-bb8d-f3008a564be7)";System.out.println("jqlBuildsFilter: "+jqlBuildsFilter);
	        jiraCommentsPage.enterJql(driver, jqlBuildsFilter);
	        Thread.sleep(2000);
	        String allKeyIssues = "issue in ("+jiraCommentsPage.getAllKeyIssues(driver)+")";
	        System.out.println("allKeyIssues: " + allKeyIssues);
	        String listOfDoneIsuueKeys = "";
	        //String doneTasks = "comment ~ \"Done by automation.\" AND "+jqlBuildsFilter;
	        //System.out.println("doneTasks: "+doneTasks);
	        //jiraCommentsPage.enterJql(driver, doneTasks);
	        //Thread.sleep(2000);
	        //listOfDoneIsuueKeys = " AND issue not in (" + jiraCommentsPage.getDoneIssueKeys(driver)+")";
	        //System.out.println("listOfDoneIsuueKeys: "+listOfDoneIsuueKeys);
	        String resultTasks = allKeyIssues + listOfDoneIsuueKeys;
	        
	        
	        // comment out
	        //resultTasks = "labels NOT IN (WordPress) AND issue in (WEB-165121)";
	        
	        System.out.println("resultTasks: "+resultTasks);
	        jiraCommentsPage.enterJql(driver, resultTasks);
	        Thread.sleep(2000);
	        int numberOfTasks = jiraCommentsPage.getNumberOfTasks(driver);
	        System.out.println("numberOfTasks: "+numberOfTasks);
	        if(numberOfTasks==0){
	        	driver.close();
	        }	        
	        List<String> spotIdCollection = new ArrayList<>();
	        List<String> issueKeyCollection = new ArrayList<>();       
	        jiraCommentsPage.loadCollectionsBuild(driver, spotIdCollection, issueKeyCollection, numberOfTasks);
	        
	        int k = 0;
	        for (int j = 0; j<numberOfTasks; j++) {
	        	//spotId = websites.get(j).get("spot_id");
	        	spotId = spotIdCollection.get(j);
	        	//issueKey = websites.get(j).get("issue_key");
	        	issueKey = issueKeyCollection.get(j);
	        	jiraCommentsPage.goToWithResponseCode("https://spothopper.atlassian.net/issues/"+issueKey);
	        	Thread.sleep(4000);
	        	String githubIssueLink = jiraCommentsPage.getGithubIssueLink(driver);
	        	jiraCommentsPage.goToWithResponseCode(githubIssueLink);
	        	Thread.sleep(3000);
	        	boolean hasPlaceHolder = githubIssuePage.checkPlaceHolder(driver);
	        	String resultString =k+". "+ issueKey + ", " + spotId;
	        	if(hasPlaceHolder) {
	        		k++;
	        		System.out.println(k+". This spot has a placeholder!");
		        	jiraCommentsPage.goToWithResponseCode("https://spothopper.atlassian.net/issues/"+issueKey);
		        	Thread.sleep(4000);
		        	String buildComment = "Note for QA: \nThis spot has a PLACEHOLDER.";
		        	jiraCommentsPage.enterComment(driver,buildComment);
		        	jiraCommentsPage.saveComment(driver);
		        	readWriteFilePage.createPlaceholderSucessFile(driver,resultString,currentTimeString,firtsEntering);
	        	}else {
	        		readWriteFilePage.createNonPlaceholderFile(driver,resultString,currentTimeString,firtsEntering);
	        	}
	         }// end of for loop
	         
		     driver.close();
		     System.out.println("Place Holder closing ***********");
	     }// @Test
   }// class
