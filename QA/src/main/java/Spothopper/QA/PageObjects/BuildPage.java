package Spothopper.QA.PageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Spothopper.QA.AbstractComponents.AbstractComponent;

public class BuildPage extends AbstractComponent {
	
	WebDriver driver;
	
	
	public BuildPage(WebDriver driver) 
		{
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);			
		}
	
	//page factory locators
	
	@FindBy(xpath = "//button[contains(@id,'google-auth-button')]")
	WebElement signInGoogleLocator;
	
	@FindBy(xpath ="//div[@data-email='automation.qa@spothopperapp.com']")
	WebElement chooseAccountLocator;

	@FindBy(xpath = "//button/span[contains(text(),'Continue')]")
	WebElement continueButtonLocator;
	
	
	@FindBy(xpath = "//a[contains(@id,'already-have-an-account')]")
	WebElement alreadyHaveAccountLocator;
	
	
	//methods
	public void clickSignInGoogle(WebDriver driver) {
		WebElement element = waitForVisibilityOfElement(driver, signInGoogleLocator, 15);
		element.click();
	}
	
	public void clickGoogleAccunt(WebDriver driver) {
		WebElement element = waitForVisibilityOfElement(driver, chooseAccountLocator, 15);
		element.click();
	}
	
	public void clickContinueButton(WebDriver driver) {
		WebElement element = waitForVisibilityOfElement(driver, continueButtonLocator, 15);
		element.click();
	}
	
	public void alreadyHaveAccountButton(WebDriver driver) {
		WebElement element = waitForVisibilityOfElement(driver, alreadyHaveAccountLocator, 15);
		element.click();
	}
	
	
	
} // end class
	
	
