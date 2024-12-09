package Spothopper.QA.PageObjects;

import Spothopper.QA.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class TMTOrderingMenuPage extends AbstractComponent {
	
	WebDriver driver;
	
	
	public TMTOrderingMenuPage(WebDriver driver) {
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);		
	}
	
	
	@FindBy(xpath = "//*[contains(text(),'Total:')]")
	List <WebElement> subtotalPrice;
	
	@FindBy(xpath = "//button[@class='btn btn-success']")
	WebElement proceedToCheckoutButton;
	
	@FindBy(xpath = "//h1[contains(text(), 'Order checkout')]")
	WebElement checkoutTitle;
	
	@FindBy(xpath = "//input[@id='first_name']")
	List <WebElement> firstNameLocator;
	
	@FindBy(xpath = "//input[@id='last_name']")
	List <WebElement> lastNameLocator;
	
	@FindBy(xpath = "//input[@id='phone']")
	List <WebElement> phoneNumberLocator;
	
	@FindBy(xpath = "//input[@id='email']")
	WebElement emailLocator;
	
	@FindBy(xpath = "//input[@placeholder='Address']")
	WebElement streetAddressLocator;
	
	@FindBy(xpath = "//div[@class='input-group autocomplete-field']/div/ul/li[1]")
	List <WebElement> streetAddress_1_FromListLocator;
	
	@FindBy(xpath = "//div[@id='tip_0']")
	List <WebElement> restaurantTip_0_Locator;
	
	@FindBy(xpath = "//div[@id='tip_10']")
	List <WebElement> restaurantTip_10_Locator;
	
	@FindBy(xpath = "//div[@id='tip_15']")
	List <WebElement> restaurantTip_15_Locator;
	
	@FindBy(xpath = "//div[@id='tip_20']")
	List <WebElement> restaurantTip_20_Locator;
	
	@FindBy(xpath = "//input[@placeholder='Discount code']")
	List <WebElement> discountCodeLocator;
	
	@FindBy(xpath = "//button[contains(text(), 'Apply')]")
	List <WebElement> applyDiscountLocator;
	
	@FindBy(xpath = "//label[@class='text-danger']")
	List <WebElement> errorMessageLocator;
	
	@FindBy(xpath = "//div[@class='total-price-holder border-top pt-3']/p/strong[contains(text(),'Total:')]")
	List <WebElement> totalOrderCheckoutLocator;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement nextAddPaymentLocator;
	
		
	
	
	public void clickMenuName(WebDriver driver, String menuName){
		String xpathExpression = "//*[contains(text(), '" + menuName + "')]";
		List <WebElement> menu = driver.findElements(By.xpath(xpathExpression));
		if (menu.size()!=0) 
		{
			clickJavascriptExecutor(driver, menu.get(0));
		}
		else 
		{ 
			System.out.println("MENU NOT EXIST!");
			driver.close();
		}
	}
	
	public void clickMenuItemName(WebDriver driver, String menuItemName, String menuItemPrice){
		String ariaLabelString = "Add "+menuItemName+", $"+menuItemPrice+" to cart";
		String xpathExpression = "//button[@aria-label='"+ariaLabelString+"']";
		List <WebElement> menuItem = driver.findElements(By.xpath(xpathExpression));
		if (menuItem.size()!=0) 
		{
			clickJavascriptExecutor(driver, menuItem.get(0));
		}
		else 
		{ 
			System.out.println("MENU ITEM NOT EXIST!");
			driver.close();
		}
	}
	
	public String getSubtotal(WebDriver driver){
		String result = null;
		if (subtotalPrice.size()!=0) 
		{
			String subtotalString = subtotalPrice.get(0).getText();
			int indexOfDollar = subtotalString.indexOf('$');
	        if (indexOfDollar != -1) {
	            result = subtotalString.substring(indexOfDollar + 1);
	        }
		}
		else 
		{ 
			System.out.println("SUBTOTAL PRICE NOT EXIST!");
		}
		return result;
	}
	
	public void clickProceedToCheckout(WebDriver driver){
		WebElement display_element = waitForVisibilityOfElement(driver, proceedToCheckoutButton, 5);
		if (display_element.isEnabled()) 
		{
			clickJavascriptExecutor(driver, display_element);
		}
		else 
		{ 
			System.out.println("PROCEED BUTTON NOT EXIST!");
		}
	}
	
	public boolean checkoutPageDisplayed(WebDriver driver) {
		WebElement display_element = waitForVisibilityOfElement(driver, checkoutTitle, 5);
		if (display_element.isDisplayed()) 
		{
			return true;
		}
		else 
		{ 
			System.out.println("TITLE NOT EXIST!");
			return false;
		}
	}
	
	public void insertFirstName(WebDriver driver, String insertedFirstName){
		if (firstNameLocator.size()!=0) 
		{
			firstNameLocator.get(0).clear();
			firstNameLocator.get(0).sendKeys(insertedFirstName);
		}
		else 
		{ 
			System.out.println("FIRST NAME NOT EXIST!");
		}
	}
	
	public void insertLastName(WebDriver driver, String insertedLastName){
		if (lastNameLocator.size()!=0) 
		{
			lastNameLocator.get(0).clear();
			lastNameLocator.get(0).sendKeys(insertedLastName);
		}
		else 
		{ 
			System.out.println("lastName NOT EXIST!");
		}
	}
	
	public void insertPhoneNumber(WebDriver driver, String insertedPhoneNumber){
		if (phoneNumberLocator.size()!=0) 
		{
			phoneNumberLocator.get(0).clear();
			phoneNumberLocator.get(0).sendKeys(insertedPhoneNumber);
		}
		else 
		{ 
			System.out.println("phoneNumberLocator NOT EXIST!");
		}
	}
	
	public void insertEmail(WebDriver driver, String insertedEmail){
		WebElement display_element = waitForVisibilityOfElement(driver, emailLocator, 3);
		if (display_element.isEnabled()) 
		{
			display_element.clear();
			display_element.sendKeys(insertedEmail);
			System.out.println(display_element.getAttribute("value"));
		}
		else 
		{ 
			System.out.println("emailLocator NOT EXIST!");
		}
	}
	
	public void insertStreetAddress(WebDriver driver,String fullStreetAddress) {
		WebElement display_element = waitForVisibilityOfElement(driver, streetAddressLocator, 5);
		
	    	if (display_element.isEnabled()) 
			{	
	    		display_element.clear();
	    		display_element.sendKeys(fullStreetAddress);
			}
			else 
			{ 
				System.out.println("streetAddressLocator NOT EXIST!"); 
			}
	}
	
	
	public void clickStreetAddres_1_FromList(WebDriver driver){
		WebElement display_element_from_dropdown = waitForVisibilityOfElement(driver, streetAddress_1_FromListLocator.get(0), 5);
		
			if (display_element_from_dropdown.isEnabled()) 
			{
				display_element_from_dropdown.click();
			}
			else 
			{ 
				System.out.println("streetAddress_1_FromListLocator NOT EXIST!");
			}
	}
	
	public void insertRestaurantTip(WebDriver driver, String insertedRestaurantTip) {
		switch (insertedRestaurantTip) {
	    case "0":
	    	if (restaurantTip_0_Locator.size()!=0) 
			{	
				restaurantTip_0_Locator.get(0).click();
			}
			else 
			{ 
				System.out.println("restaurantTip_0_Locator NOT EXIST!"); 
			}
	        break;
	    case "10":
	    	if (restaurantTip_10_Locator.size()!=0) 
			{	
				restaurantTip_10_Locator.get(0).click();
			}
			else 
			{ 
				System.out.println("restaurantTip_10_Locator NOT EXIST!"); 
			}
	        break;
	    case "15":
	    	if (restaurantTip_15_Locator.size()!=0) 
			{	
				restaurantTip_15_Locator.get(0).click();
			}
			else 
			{ 
				System.out.println("restaurantTip_15_Locator NOT EXIST!"); 
			}
	    	break;
	    case "20":
	    	if (restaurantTip_20_Locator.size()!=0) 
			{	
				restaurantTip_20_Locator.get(0).click();
			}
			else 
			{ 
				System.out.println("restaurantTip_20_Locator NOT EXIST!"); 
			}
	        break;
		}
	}
	
	public void insertDiscountCode(WebDriver driver, String insertedDiscountType, String insertedDiscountCode) {
		if (insertedDiscountType.equals("amount") || insertedDiscountType.equals("percentage")) {
	    	if (discountCodeLocator.size()!=0) 
			{	
				discountCodeLocator.get(0).clear();
				discountCodeLocator.get(0).sendKeys(insertedDiscountCode);
				if (applyDiscountLocator.size()!=0) 
				{
					applyDiscountLocator.get(0).click();
				}
				else 
				{ 
					System.out.println("applyDiscountLocator NOT EXIST!");
				}
			}
			else 
			{ 
				System.out.println("discountCodeLocator NOT EXIST!"); 
			}
		} 
	}
	
	public void clickApplyDiscount(WebDriver driver){
		if (applyDiscountLocator.size()!=0) 
		{
			applyDiscountLocator.get(0).click();
		}
		else 
		{ 
			System.out.println("applyDiscountLocator NOT EXIST!");
		}
	}
	
	public boolean errorMessageExist(WebDriver driver, String insertedDiscountType) {
		if (insertedDiscountType.equals("amount") || insertedDiscountType.equals("percentage")) {
			if(errorMessageLocator.size()!=0){
				System.out.println("Voucher NOT EXIST!!");
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	public void clickAddPayment(WebDriver driver){
		WebElement display_element = waitForVisibilityOfElement(driver, nextAddPaymentLocator, 7);
		if (display_element.isEnabled()) 
		{
			display_element.click();
			System.out.println("nextAddPaymentLocator clicked");
		}
		else 
		{ 
			System.out.println("nextAddPaymentLocator NOT EXIST!");
		}
	}
	
	public String getTotalOrderCheckout(WebDriver driver){
		if (totalOrderCheckoutLocator.size()!=0) 
		{	
			WebElement valueElement = totalOrderCheckoutLocator.get(0).findElement(By.xpath("./following-sibling::span/strong"));
			String total = valueElement.getText();
			return total;
		}
		else 
		{ 
			System.out.println("totalOrderCheckoutLocator NOT EXIST!");
			return null;
		}
	}
}
	
	
	

