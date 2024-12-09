package Spothopper.QA.PageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Spothopper.QA.AbstractComponents.AbstractComponent;

public class BusinessInfoPage extends AbstractComponent {
	
	WebDriver driver;
	
	
	public BusinessInfoPage(WebDriver driver) 
		{
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
						
		}
	
	//page factory locators
	
	
	@FindBy(xpath ="//input[contains(@name,'twitter')]")
	WebElement twitterUserNameLocator;
	
	@FindBy(xpath ="//input[contains(@name,'instagram')]")
	WebElement instagramUserNameLocator;
	
	@FindBy(xpath ="//button[contains(@class,'btn btn-primary  ng-scope ng-isolate-scope')]")
	WebElement saveBusinessInfoLocator;
	
	
	//methods
	
	public void enterTwitterUserName(WebDriver driver) {
		WebElement element = waitForVisibilityOfElement(driver, twitterUserNameLocator, 15);
		clickElement(driver, element, "twitterUserNameLocator", 15);
		clearAndSendKeys(driver,element,"twitterUserNameLocator",15,"automation_test");
		System.out.println("entered name in twitterUserNameLocator!");
	}
	
	public void enterInstagramUserName(WebDriver driver) {
		WebElement element = waitForVisibilityOfElement(driver, instagramUserNameLocator, 15);
		clearAndSendKeys(driver,element,"instagramUserNameLocator",15,"instagram_automation_test");
		System.out.println("entered name in instagramUserNameLocator!");
	}
	
	public boolean isEmptyTwitterUserName(WebDriver driver) {
	    WebElement element = waitForVisibilityOfElement(driver, twitterUserNameLocator, 15);
	    System.out.println("twitter user name is empty = "+element.getAttribute("value").isEmpty());
	    return (element.getAttribute("value").isEmpty() || element.getAttribute("value").trim()=="");
	}
	
	public boolean isEmptyInstagramUserName(WebDriver driver) {
	    WebElement element = waitForVisibilityOfElement(driver, instagramUserNameLocator, 15);
	    System.out.println("instagram user name is empty = "+element.getAttribute("value").isEmpty());
	    return (element.getAttribute("value").isEmpty() || element.getAttribute("value").trim()=="");
	}
	
	public void clickSaveBusinessInfo(WebDriver driver) {
		WebElement element = waitForVisibilityOfElement(driver, saveBusinessInfoLocator, 15);
		clickElement(driver,element,"saveBusinessInfoLocator",15);
		System.out.println("saveBusinessInfoLocator clicked!");
	}
	
	public void eraseTwitterUserName(WebDriver driver) {
		WebElement element = waitForVisibilityOfElement(driver, twitterUserNameLocator, 15);
		element.clear();
	}
	
	public void eraseInstagramUserName(WebDriver driver) {
		WebElement element = waitForVisibilityOfElement(driver, instagramUserNameLocator, 15);
		element.clear();
	}
	
	public String getTextFromTwitterUserName(WebDriver driver) {
		WebElement element = waitForVisibilityOfElement(driver, twitterUserNameLocator, 15);
		return element.getAttribute("value");
	}

	public String getTextFromInstagramUserName(WebDriver driver) {
		WebElement element = waitForVisibilityOfElement(driver, instagramUserNameLocator, 15);
		return element.getAttribute("value");
	}
	
	
	
} // end class
	
	
