package Spothopper.QA.PageObjects;

import Spothopper.QA.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class TMTPaymentDetailsPage extends AbstractComponent {
	
	WebDriver driver;
	
	
	public TMTPaymentDetailsPage(WebDriver driver) {
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);		
	}
	
	
	@FindBy(xpath ="//input[@id = 'name_on_card']")
	WebElement nameOnCardLocator;
	
	@FindBy(xpath ="//input[@id = 'phone']")
	WebElement phoneOnCardLocator;
	
	@FindBy(xpath ="//input[@id = 'email']")
	WebElement emailOnCardLocator;
	
	@FindBy(xpath ="//input[@id = 'search-input']")
	WebElement billingAddressLocator;
	
	@FindBy(xpath ="//div[@class = 'input-group autocomplete-field']/div/ul/li[1]")
	WebElement billingAddressOnDropDoownLocator;
	
	@FindBy(xpath ="//iframe[@title = 'Secure card payment input frame']")
	WebElement iframeLocator;
	
	@FindBy(xpath ="//input[@name = 'cardnumber']")
	WebElement cardNumberLocator;
	
	@FindBy(xpath ="//input[@name = 'exp-date']")
	WebElement expirationDateLocator;
	
	@FindBy(xpath ="//input[@name = 'cvc']")
	WebElement cvcNumberLocator;
	
	@FindBy(xpath ="//input[@name = 'postal']")
	WebElement postalCodeLocator;
	
	@FindBy(xpath ="//button[@class = 'stripe-button btn-success']")
	WebElement payButtonLocator;
	
	@FindBy(xpath ="//div[@id='successModal']/div/div/div/h1[@id='exampleModalLabel']")
	WebElement successMessageLocator;
	
	@FindBy(xpath ="//div[@id='successModal']/div/div/div[2]/p[1]")
	WebElement orderNumberReceivedLocator;
	
	
	
	public void insertStricterDataAddress(
			WebDriver driver,
			String insertedCreditCollect,
			String insertedStricter,
			String street_address){
			if(insertedCreditCollect.equals("credit")) {
				if(insertedStricter.equals("TRUE")) {
					// Billing address
					clearAndSendKeys(driver, 
							billingAddressLocator, 
							"billingAddressLocator", 
							7, 
							street_address);
					// click Billing Address
					clickElement(driver, 
							billingAddressOnDropDoownLocator, 
							"billingAddressOnDropDoownLocator", 
							5);
				}
				else{
					System.out.println("No need for stricter");
				}
			}
			else{
				System.out.println("No need for payment details");
			}
	}
	
	public void insertStricterDataName(
			WebDriver driver,
			String insertedCreditCollect,
			String insertedStricter,
			String insertedFirstName){
			if(insertedCreditCollect.equals("credit")) {
				if(insertedStricter.equals("TRUE")) {
					
					// Name
					clearAndSendKeys(driver, 
							nameOnCardLocator, 
							"nameOnCardLocator", 
							5, 
							insertedFirstName);
					
				}
			}
	}
	
	public void insertStricterDataPhone(
			WebDriver driver,
			String insertedCreditCollect,
			String insertedStricter,
			String insertedPhoneNumber){
			if(insertedCreditCollect.equals("credit")) {
				if(insertedStricter.equals("TRUE")) {
					
					// Phone
					clearAndSendKeys(driver, 
							phoneOnCardLocator, 
							"phoneOnCardLocator", 
							5, 
							insertedPhoneNumber);
				}
			}
	}
	
	public void insertStricterDataEmail(
			WebDriver driver,
			String insertedCreditCollect,
			String insertedStricter,
			String insertedEmail){
			if(insertedCreditCollect.equals("credit")) {
				if(insertedStricter.equals("TRUE")) {
					// Email
					clearAndSendKeys(driver, 
							emailOnCardLocator, 
							"emailOnCardLocator", 
							5, 
							insertedEmail);
				}
			}
		}
	
	public void insertBillingAddress(WebDriver driver, String street_address) {
		clearAndSendKeys(driver, 
				billingAddressLocator, 
				"billingAddressLocator", 
				5, 
				street_address);
	}
	
	public void clickBillingAddress(WebDriver driver) {
		clickElement(driver, 
				billingAddressOnDropDoownLocator, 
				"billingAddressOnDropDoownLocator", 
				5);
	}
	
	public void insertCardNumber(
			WebDriver driver,
			String insertedCreditCollect,
			String insertedStricter,
			String insertedCardNumber,
			String insertedExpiryDate,
			String insertedCvcNumber,
			String insertedPostalCode) {
		if(insertedCreditCollect.equals("credit")) {
			WebElement display_element = waitForVisibilityOfElement(driver, iframeLocator, 7);
			if (display_element.isEnabled()) 
			{
				driver.switchTo().frame(display_element);
				System.out.println("switched to iframe");
				// Card number
				clearAndSendKeys(driver, 
						cardNumberLocator, 
						"cardNumberLocator", 
						5, 
						insertedCardNumber);
				// Expiration date
				clearAndSendKeys(driver, 
						expirationDateLocator, 
						"expirationDateLocator", 
						5, 
						insertedExpiryDate);
				// CVC number
				clearAndSendKeys(driver, 
						cvcNumberLocator, 
						"cvcNumberLocator", 
						7, 
						insertedCvcNumber);
				// Postal code
				if(insertedStricter.equals("FALSE")){
					clearAndSendKeys(driver, 
							postalCodeLocator, 
							"postalCodeLocator", 
							5, 
							insertedPostalCode);
				}
				driver.switchTo().defaultContent();
				System.out.println("switched to default - exit iframe");
			}
			else 
			{ 
				System.out.println("iframeLocator NOT EXIST!");
			}
			// pay button click
			clickElement(driver, 
					payButtonLocator, 
					"payButtonLocator", 
					7);
		}
	}
	
	public WebDriver switchToIFrame(WebDriver driver) {
			WebElement display_element = waitForVisibilityOfElement(driver, iframeLocator, 7);
			if (display_element.isEnabled()) 
			{
				driver.switchTo().frame(display_element);
				System.out.println("Switched to iframe!");
			}
			else 
			{ 
				System.out.println("iframeLocator NOT EXIST!");
			}
			return driver;
	}
	
	public void switchDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
		System.out.println("switched to default - exit iframe");
}
	
	public void insertCardNumber(WebDriver driver, String insertedCardNumber) {
		clearAndSendKeys(driver, 
				cardNumberLocator, 
				"cardNumberLocator", 
				7, 
				insertedCardNumber);
	}
	
	public void insertExpirationDate(WebDriver driver, String insertedExpiryDate) {
		clearAndSendKeys(driver, 
				expirationDateLocator, 
				"expirationDateLocator", 
				5, 
				insertedExpiryDate);
	}
	
	public void insertCvcNumber(WebDriver driver, String insertedCvcNumber) {
		clearAndSendKeys(driver, 
				cvcNumberLocator, 
				"cvcNumberLocator", 
				5, 
				insertedCvcNumber);
	}
	
	public void insertPostalCode(WebDriver driver, String insertedPostalCode) {
		clearAndSendKeys(driver, 
				postalCodeLocator, 
				"postalCodeLocator", 
				5, 
				insertedPostalCode);
	}
	
	public void clickPayButton(WebDriver driver) {
		clickElement(driver, 
				payButtonLocator, 
				"payButtonLocator", 
				7);
	}
	
	public boolean successMessageExist(WebDriver driver) {
		WebElement display_element = waitForVisibilityOfElement(driver, successMessageLocator, 7);
		if (display_element.isDisplayed()) 
		{
			System.out.println("Success message is shown!");
			return true;
		}
		else 
		{ 
			System.out.println("successMessageLocator NOT EXIST!");
			return false;
		}
	}
	
	public String getOrderNumberReceived(WebDriver driver) {
		WebElement display_element = waitForVisibilityOfElement(driver, orderNumberReceivedLocator, 10);
		if (display_element.isDisplayed()) 
		{
			String numberString = display_element.getText();
			return numberString;
		}
		else 
		{ 
			System.out.println("orderNumberReceivedLocator NOT EXIST!");
			return null;
		}
	}

	
	
	
}
	
	
	

