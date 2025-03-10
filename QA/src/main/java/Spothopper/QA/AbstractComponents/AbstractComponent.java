package Spothopper.QA.AbstractComponents;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.apache.commons.io.FileUtils;
import org.apache.hc.core5.http.nio.support.AbstractAsyncServerAuthFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.openqa.selenium.support.locators.RelativeLocator.*;
import Spothopper.QA.Resources.*;

public class AbstractComponent {
	
	protected WebDriver driver;
	protected List<String> orderingPlatforms = Arrays.asList("chownow","toasttab", "grubhub", "doordash", "ubereats","spoton","clove");
	
	public AbstractComponent(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath ="//button[@ng-click='cancel()']")
	List <WebElement> closeRecoveryPhoneNumber;
	
	@FindBy(xpath ="//button[contains(@class, 'btn') and contains(text(), 'Remind Me Later')]")
	WebElement clickRecoveryPhoneNumber;
	
	@FindBy(xpath ="//button[@ng-click='modal_instagram_not_connected.dismiss()']")
	WebElement closeInstagramPopupButton;
	
	@FindBy(xpath ="//button[@ng-click='$dismiss()']")
	WebElement closeHolidayPopupButton;
	
	@FindBy(xpath ="//button[@class='close']")
	WebElement delinquentPopupLocator;
	
	@FindBy(xpath ="//button[@ng-click='change_phone_number()']")
	WebElement changePhoneNumber;
	
	@FindBy(xpath ="//span[@class='spot-name']")
	WebElement spotNameLocator;
	
	@FindBy(xpath ="//button[contains(text(), 'care about')]")
	WebElement closeBetterSearchResultsButton;
	
	@FindBy(xpath ="//button[contains(@ng-click, 'modularPromotionPopUp')]")
	WebElement closemodularPromotionPopUpLocator;
	
	@FindBy(xpath ="//button[@class='close']")
	List <WebElement> closeNewsLetterLocator;
	
	@FindBy(xpath ="//div[@class='modal-header']/button[@class='close']/span[@aria-hidden='true' and text()='×']")
	List <WebElement> closePopupButtonLocator;
	
	@FindBy(xpath ="//button[@class='close-button']")
	List <WebElement> closePopupLocator;
	
	@FindBy(xpath = "//pre")
	protected
	WebElement apiPreLocator;
	
	//Methods
	public int goToWithResponseCode(String url) throws IOException { 
	    int responseCode = -1; 
	    try {
	        driver.get(url);
	        hardRefresh(url);
	        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	        jsExecutor.executeScript(
	                "localStorage.setItem('SafetyUpdatePopupShow', new Date().toLocaleDateString('en-US'));");
	        responseCode = getResponseCode(driver, url); 
	    } catch (UnknownHostException e) {
	        System.err.println("Unknown host: " + url + ". Cannot resolve the URL.");
	        return responseCode; 
	    } catch (Exception e) {
	        System.err.println("An error occurred while accessing the URL: " + url);
	        e.printStackTrace();
	        return responseCode;
	    }
	    System.out.println(url + ", response code: " + responseCode);
	    return responseCode;
	}
	
	public List<WebElement> waitForVisibilityOfElements(WebDriver driver, List<WebElement> elements, int numOfSeconds)
	{
		List<WebElement> result = new ArrayList<WebElement>();
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(numOfSeconds)); 
		for (WebElement element : elements) {
			try {
				w.until(ExpectedConditions.visibilityOf(element));
				result.add(element);
			}catch(org.openqa.selenium.TimeoutException e){
				System.out.println("Element was not visible within the timeout: " + element);
			}
	    }
		return result;
	}
	
	public void closeNewsLetter(WebDriver driver){
		List <WebElement> display_elements = closeNewsLetterLocator;
		for(int i=0;i<display_elements.size();i++) {
			try {
				WebElement display_element = waitForVisibilityOfElement(driver, closeNewsLetterLocator.get(i), 1);
				clickJavascriptExecutor(driver, closeNewsLetterLocator.get(i));
				System.out.println("closeNewsLetterLocator");
			}
			catch(Exception  e){
				//System.out.println("skip closeNewsLetterLocator");
			}
		}	
	}
	
	public void closePopup(WebDriver driver){
		List <WebElement> display_elements = closeNewsLetterLocator;
		System.out.println("Popupovi: "+display_elements);
		for(int i=0;i<display_elements.size();i++) {
			try {
				clickJavascriptExecutor(driver, closeNewsLetterLocator.get(i));
				System.out.println("closePopup");
			}
			catch(Exception  e){
				//System.out.println("skip closeNewsLetterLocator");
			}
		}	
	}
	
	public String clearUrlFromHttp(WebDriver driver,String url) {
		String result = url;
		if(result.startsWith("https")) {
			result = result.substring(8);
		}
		if(result.startsWith("http")) {
			result = result.substring(7);
		}
		
		return result;
	}
	
	public void closemodularPromotionPopUp(WebDriver driver){
		try {
			WebElement display_element = waitForVisibilityOfElement(driver, closemodularPromotionPopUpLocator, 15);
			if (display_element.isEnabled()) 
			{
				display_element.click();
			}
			else 
			{ 
				System.out.println("no modularPromotionPopUpLocator");
			}
		}	
		catch(Exception  e){
			System.out.println("no modularPromotionPopUpLocator");
		}
}
	
	public void clickJavascriptExecutor(WebDriver driver, WebElement element)
	{
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	    jsExecutor.executeScript("arguments[0].click();", element);
	}
	
	
	public boolean goTo(String url) 
	{
		boolean result=true;
		try {
			driver.get(url);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		    jsExecutor.executeScript(
		    		"localStorage.setItem('SafetyUpdatePopupShow', new Date().toLocaleDateString('en-US'));");
		}
		catch (Exception e) {
			result = false;
		}
		return result;
	}
	
	

	
	public void hardRefresh(String url) 
	{
		driver.get(url);		
		
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	    jsExecutor.executeScript(
	    		"localStorage.setItem('SafetyUpdatePopupShow', new Date().toLocaleDateString('en-US'));");
	    jsExecutor.executeScript("location.reload(true);");
	}
	
	
	
	public void clickElement(WebDriver driver, WebElement element, String element_name,int numOfSeconds) {
		waitForClickabilityOfElement(driver, element, numOfSeconds);
		if (element.isEnabled()) 
		{
			scrollPageUncoverElement(driver, element);
			element.click();
		}
		else 
		{ 
			System.out.println(element_name+" NOT EXIST!");
			driver.quit();
		}
	}
	
	public void clearAndSendKeys(
			WebDriver driver, 
			WebElement element, 
			String element_name, 
			int numOfSeconds, 
			String inserted_text) {
		waitForVisibilityOfElement(driver, element, numOfSeconds);
		if (element.isEnabled()) 
		{
			
			element.clear();
			element.sendKeys(inserted_text);
		}
		else 
		{ 
			System.out.println(element_name+" NOT EXIST!");
			driver.quit();
		}
	}
	
	public WebElement waitForVisibilityOfElement(WebDriver driver, WebElement element, int numOfSeconds)
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(numOfSeconds)); 
		WebElement webElement = w.until(ExpectedConditions.visibilityOf(element));
		return webElement;
	}
	
	
	
	
	public WebElement waitForClickabilityOfElement(WebDriver driver, WebElement element, int numOfSeconds)
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(numOfSeconds)); 
		WebElement webElement = w.until(ExpectedConditions.elementToBeClickable(element));
		return webElement;
	}
	
	
	
	
	
	public void finshTest(WebDriver driver)
	{
		System.out.println("The test is over!");
		driver.close();
	}
	
	
	
	public void clickCloseRecoveryPhoneButton(WebDriver driver, String production_staging){
			int index = 0;
			if(production_staging.equals("staging")) {
				index = 1;
			}
			try {
				WebElement display_element = waitForVisibilityOfElement(driver, closeRecoveryPhoneNumber.get(index), 15);
				if (display_element.isEnabled()) 
				{
					display_element.click();
				}
				else 
				{ 
					System.out.println("no recovery phone popup");
				}
			}
			catch(Exception  e){
				System.out.println("no recovery phone popup - close button");
			}
	}
	
	public boolean isElementPresent(WebDriver driver, By by) {
		try {
	        driver.findElement(by);
	        return true;
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	}
	
	
	public void clickReminLaterRecoveryPhoneButton(WebDriver driver, String production_staging){
		try {
			WebElement display_element = waitForVisibilityOfElement(driver, clickRecoveryPhoneNumber, 15);
			if (display_element.isEnabled()) 
			{
				display_element.click();
			}
			else 
			{ 
				System.out.println("no recovery phone popup");
			}
		}
		catch(Exception  e){
			System.out.println("no recovery phone popup - click remind later");
		}
	}
	
	public void clickCloseInstagramPopupButton(WebDriver driver){
			try {
				WebElement display_element = waitForVisibilityOfElement(driver, closeInstagramPopupButton, 5);
				display_element.click();
			}	
			catch(Exception  e){
				System.out.println("no instagram popup");
			}
	}
	
	
	
	
	public void closeHolidayPopupButton(WebDriver driver){
		try {
				WebElement display_element = waitForVisibilityOfElement(driver, closeHolidayPopupButton, 5);
				if (display_element.isDisplayed()) 
				{
					display_element.click();
				}
				else 
				{ 
					System.out.println("no holiday popup");
				}
			}
			catch(Exception e) {
				System.out.println("no holiday popup");
			}
	}
	
	
	
	public void clickCloseDelinquentPopupButton(WebDriver driver){ 
		WebElement display_element = waitForVisibilityOfElement(driver, delinquentPopupLocator, 15);
		if (display_element.isDisplayed()) 
		{
			display_element.click();
		}
		else 
		{ 
			System.out.println("no delinquent popup");
		}
	}
	
	public boolean checkResponseCode(WebDriver driver,  String url_string) throws IOException {
		URL url_objectUrl = new URL(url_string);
        HttpURLConnection connection =(HttpURLConnection) url_objectUrl.openConnection();
        connection.setRequestMethod("HEAD");
        connection.connect();
        int responseCode = connection.getResponseCode();
        System.out.println("Response code is "+responseCode);
        if(responseCode>400) {
        	return false;
        }
        else {
        	return true;
        }
	}
	
	public int getResponseCode(WebDriver driver,  String url_string) throws IOException {
		URL url_objectUrl = new URL(url_string);
        HttpURLConnection connection =(HttpURLConnection) url_objectUrl.openConnection();
        connection.setRequestMethod("HEAD");
        connection.connect();
        int responseCode = connection.getResponseCode();
        return responseCode;
	}
	
	public void screenshotPage(
			WebDriver driver, 
			String local_github, 
			String local_date_time, 
			String screenshot_files_path) throws IOException {
				if(local_github.equals("local")) {
		    		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		    		Random random = new Random();
		    		int randomNumber = random.nextInt(1001);
		        	String screenshot_file = "screenshot_page_"+Integer.toString(randomNumber)+" "+local_date_time+"_.png";
		            FileUtils.copyFile(src, new File(screenshot_files_path+screenshot_file));
    	}else {
    		 System.out.println("no screenshot page");
    	}
	}
	
	public void screenshotElement(
			WebDriver driver,
			WebElement element,
			String local_github, 
			String local_date_time, 
			String screenshot_files_path) throws IOException {
				if(local_github.equals("local")) {
		    		File src = element.getScreenshotAs(OutputType.FILE);
		    		Random random = new Random();   
		    	    int randomNumber = random.nextInt(1001);
		        	String screenshot_file = "screenshot_element "+Integer.toString(randomNumber)+" "+local_date_time+".png";
		            FileUtils.copyFile(src, new File(screenshot_files_path+screenshot_file));
    	}else {
   		 System.out.println("no screenshot element");
    	}
		System.out.println("The dimensions of the element are: "+
    	element.getRect().getDimension().getHeight()+" and "+
    	element.getRect().getDimension().getWidth());
	}
	
	public void clickChangePhoneNumberButton(WebDriver driver, String production_staging){ 
		if(production_staging.equals("production")) {
		
			WebElement display_element = waitForVisibilityOfElement(driver, changePhoneNumber, 15);
			if (display_element.isEnabled()) 
			{
				display_element.click();
			}
			else 
			{ 
				System.out.println("no delinquent popup");
			}
		}
	}
	
	public List<String> switchToChild(WebDriver driver, List<String> parentChild) {
	    Set<String> setOfWindows = driver.getWindowHandles();
	    Iterator<String> it = setOfWindows.iterator();
	    parentChild.clear(); // Clear the list before adding new handles
	    parentChild.add(it.next()); // Add parent window handle
	    parentChild.add(it.next()); // Add child window handle
	    return parentChild;
	}
	
	public void scrollPageUncoverElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -100);", element);
	}
	
	
	
	public void clickElementWithWait(WebDriver driver, WebElement element, String element_name, int numOfSeconds) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(numOfSeconds));

	    try {
	        // Wait for the element to be clickable
	        wait.until(ExpectedConditions.elementToBeClickable(element));
	        
	        // Scroll the element into view using JavaScript if it's not already in the viewport
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	        
	        // Check if the element is displayed and enabled
	        if (element.isDisplayed() && element.isEnabled()) {
	            System.out.println(element_name + " displayed");
	            element.click();
	        } else {
	            System.out.println(element_name + " NOT EXIST!");
	            driver.quit();
	        }
	    } catch (ElementNotInteractableException e) {
	        System.out.println(element_name + " NOT INTERACTABLE!");
	        driver.quit();
	    } catch (Exception e) {
	        System.out.println("Unexpected error: " + e.getMessage());
	        driver.quit();
	    }
	}
		
	public String getCompanyName(WebDriver driver) {
			WebElement display_element = waitForVisibilityOfElement(driver, spotNameLocator, 15);
			if (display_element.isEnabled()) 
			{
				String company_name = display_element.getText();
				return company_name;
				
			}
			else 
			{ 
				System.out.println(display_element+" NOT EXIST!");
				driver.quit();
				return null;
			}
	}
	
	public void closeBetterSearchResultsButton(WebDriver driver){
		try {
				WebElement display_element = waitForVisibilityOfElement(driver, closeBetterSearchResultsButton, 15);
				if (display_element.isDisplayed()) 
				{
					display_element.click();
				}
				else 
				{ 
					System.out.println("no BetterSearchResults popup");
				}
			}
			catch(Exception e) {
				System.out.println("no BetterSearchResults popup");
			}
	}
	
	
	
	
}
