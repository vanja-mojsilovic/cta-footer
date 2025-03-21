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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
		    	
				System.out.println("*******Builds Stage Version started!********");
						
				//Initial settings
				BuildPage buildPage = new BuildPage(driver);
				JiraCommentsPage jiraCommentsPage = new JiraCommentsPage(driver);
				VariablesAndUrlsPage variablesAndUrlsPage = new VariablesAndUrlsPage(driver);
				GithubIssuePage githubIssuePage = new GithubIssuePage(driver);
				WebsiteFeaturesPage websiteFeaturesPage = new WebsiteFeaturesPage(driver);
		    	String emailFromPopupOrJson = variablesAndUrlsPage.myEmail;
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
			    String websiteURL;
			    String issueKey;
			
			    // Google verification
		        variablesAndUrlsPage.googleVerification(driver, emailFromPopupOrJson);
		        Thread.sleep(2000);
		        variablesAndUrlsPage.spothopperAppSignIn(driver);
		        Thread.sleep(2000);
		      
		        jiraCommentsPage.goToWithResponseCode(variablesAndUrlsPage.githubIssueUrl);
		        Thread.sleep(2000);
		        githubIssuePage.githubVerificationWithAuth(driver,emailFromPopupOrJson);
		        Thread.sleep(2000);
		        currentTimeString = getStringLocalDateTime();
		        
		        int firtsEntering=1;
		        int errorOrderNumber=0;
		        List<String> testSiteUrls = new ArrayList<String>();
		        List<String> issueKeyCollection = new ArrayList<>();
		        List<String> spotIdCollection = new ArrayList<>();
		        List<String> websiteUrlCollection = new ArrayList<>();
		        jiraCommentsPage.jiraSignIn(driver);
		       
		        String jqlBuildsFilter = " labels NOT IN (WordPress,LocationLanding,LocationPicker,LandingBuild)"
		        		+ " AND issuetype in (Epic, LandingAG, Redesign) AND status = QA AND assignee not in (membersOF(\"QA\"))";
		        System.out.println("jqlBuildsFilter: " + jqlBuildsFilter);
		        
		        // all tasks	        
		        String allKeyIssues = jiraCommentsPage.getKeyIssuesByApi(driver,jqlBuildsFilter,"");
		        allKeyIssues = "issue in ("+allKeyIssues+")";
		        System.out.println("allKeyIssues: " + allKeyIssues);	        
		        // done tasks
		        String doneTasks = "comment ~ \"Done by automation.\" AND " + jqlBuildsFilter;	        
		        String doneIssuesSeparatedWithCommas = jiraCommentsPage.getKeyIssuesByApi(driver,doneTasks,"");
		        System.out.println("doneIssuesSeparatedWithCommas: " + doneIssuesSeparatedWithCommas);
		        if(!doneIssuesSeparatedWithCommas.equals("")) {
		        	doneIssuesSeparatedWithCommas = " AND issue not in (" + doneIssuesSeparatedWithCommas + ")";
		        }
		        String resultTasks = allKeyIssues + doneIssuesSeparatedWithCommas;
		       	String apiUrl = "https://spothopper.atlassian.net/rest/api/3/search?jql=issue%20in%20%28WEB-171414%2CWEB-171412%2CWEB-171411%2CWEB-171366%2CWEB-171353%29&maxResults=1000";
		        
		        // comment out
		        resultTasks = "labels NOT IN (WordPress) AND issue in (WEB-174356)";
		        
		       	String encodedJql = URLEncoder.encode(resultTasks, "UTF-8");
		        String apiQueryUrl = "https://spothopper.atlassian.net/rest/api/3/search?jql=" + encodedJql+"&maxResults=1000";
		        //int numberOfTasks = jiraCommentsPage.getIssueKeySpotIdWebsiteUrlFromApi(driver,apiQueryUrl,issueKeyCollection,spotIdCollection,websiteUrlCollection);
		        int numberOfTasks = jiraCommentsPage.getIssueKeySpotIdFromApi(driver,apiQueryUrl,issueKeyCollection,spotIdCollection);
		        System.out.println("numberOfTasks: "+numberOfTasks+", resultTasks: "+resultTasks);
		        if(numberOfTasks==0){
		        	driver.close();
		        }	        
		              
		        //jiraCommentsPage.loadCollectionsBuild(driver, spotIdCollection, issueKeyCollection, numberOfTasks);
		        int k = 0;
		        int nonPlaceholder=0;
		        String placeholderResultString ="";
		        
		        for (int j = 0; j<numberOfTasks; j++) {
		        	String successLog = "";
		        	String buildLogComment = "";
		        	spotId = spotIdCollection.get(j);
		        	issueKey = issueKeyCollection.get(j);
		        	
		        	// Jira
		        	jiraCommentsPage.goToWithResponseCode("https://spothopper.atlassian.net/issues/"+issueKey);
		        	Thread.sleep(4000);
		        	String testSiteUrl = jiraCommentsPage.getTestSiteUrl(driver);
		        	if(testSiteUrl.equals("")) {
		        		String errorMessage = issueKey+" "+spotId+" test site url not found";
		        		buildLogComment = "\n"+j+". "+errorMessage;
		        		continue;
		        	}
		        	String branchName = jiraCommentsPage.getBranchName(driver,testSiteUrl);
		        	buildLogComment = "\n"+j+". issueKey "+issueKey+", spotId "+spotId+", testSiteUrl "+testSiteUrl+", branchName "+branchName;
		        	System.out.println(buildLogComment);
		        	
		        	// github issue
		        	jiraCommentsPage.goToWithResponseCode("https://spothopper.atlassian.net/issues/"+issueKey);
		        	Thread.sleep(4000);
		        	String githubIssueLink = jiraCommentsPage.getGithubIssueLink(driver);
		        	jiraCommentsPage.goToWithResponseCode(githubIssueLink);
		        	Thread.sleep(3000);
		        	String oldDomain = buildPage.getOldDomain(driver);
		        	System.out.println("oldDomain "+oldDomain);
		        	boolean hasLanding = githubIssuePage.checkLanding(driver);   
		        	
		        	// website elements
		        	buildPage.goToWithResponseCode(testSiteUrl);
		        	Thread.sleep(2000);
		        	buildPage.closeNewsLetter(driver);
		        	successLog += buildPage.getAllHrefLinks(driver,oldDomain);
		        	buildLogComment += successLog;
		        	successLog += buildPage.checkVissualyHidden(driver);
		        	successLog += buildPage.checkPhoneNumber(driver);
		        	System.out.println(successLog);
		        	
		        	
		        	
		        	
		        	
		        	
		        	
		        
		         }// end of for loop 
			     
			     System.out.println("Builds Stage Version closing ***********");
			     driver.close();
		     }// @Test
   }// class
