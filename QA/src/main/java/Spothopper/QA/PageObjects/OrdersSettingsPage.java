package Spothopper.QA.PageObjects;

import Spothopper.QA.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;




public class OrdersSettingsPage extends AbstractComponent {
	
	WebDriver driver;
	
	
	public OrdersSettingsPage(WebDriver driver) 
		{
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
						
		}
	
	@FindBy(xpath ="//input[@ng-model='order_config.shopping_cart']")
	WebElement activeCheckboxLocator;
	
	@FindBy(xpath ="//input[@ng-model='order_config.has_pick_up']")
	WebElement pickupCheckbox;
	
	@FindBy(xpath ="//input[@ng-model='order_config.has_deliveries']")
	WebElement deliveryCheckbox;
	
	@FindBy(xpath ="//input[@id='in-house-delivery']")
	WebElement inHouseDrivers;
	
	@FindBy(xpath ="//input[@name='CardPayments']")
	WebElement creditCardPayments;
	
	@FindBy(xpath ="//input[@name='CollectPayment']")
	WebElement collectPayment;
	
	@FindBy(xpath ="//input[@ng-model='order_config.delivery_fee']")
	List <WebElement> deliveryFee;
		
	@FindBy(xpath ="//input[@ng-model='order_config.tax_rate']")
	List <WebElement> taxRate;
	
	@FindBy(xpath ="//input[@ng-model='order_config.strict_card_authorisation']")
	List <WebElement> stricterAuthorization;
	
	@FindBy(xpath ="//input[@ng-model='order_config.pass_processing_fee_to_consumer']")
	List <WebElement> processingCost;
	
	@FindBy(xpath ="//input[@ng-model='order_config.pick_up_turnaround_hours']")
	List <WebElement> pickupTurnaroundHours;
	
	@FindBy(xpath ="//select[@ng-model='order_config.pick_up_turnaround_minutes']")
	List <WebElement> pickupTurnaroundMinutes;
	
	@FindBy(xpath ="//input[@ng-model='order_config.delivery_turnaround_hours']")
	List <WebElement> deliveryTurnaroundHours;
	
	@FindBy(xpath ="//select[@ng-model='order_config.delivery_turnaround_minutes']")
	List <WebElement> deliveryTurnaroundMinutes;
	
	@FindBy(xpath ="//input[@name='email_address_for_orders_1']")
	List <WebElement> emailAddress_1;
	
	
	@FindBy(xpath ="//button[@ng-click='submitOrderConfig()']")
	List <WebElement> saveSettingsButton;
	
	
	public void activateOnlineOrders(WebDriver driver){
		
		WebElement display_element = activeCheckboxLocator; 
		if (display_element.isEnabled()) 
		{	
			try {
				boolean value = display_element.isSelected();
				if (!value) {
					clickJavascriptExecutor(driver, display_element);
					//activeCheckboxLocator.get(0).click();
				} 
			    value = display_element.isSelected();
			    //System.out.println("active online orders in app is: "+value);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		else 
		{ 
			System.out.println("activeCheckboxLocator NOT EXIST!");
		}
	}



	public void activatePickup(WebDriver driver){
		WebElement display_element = pickupCheckbox;
		if (display_element.isEnabled()) 
		{
			boolean value = display_element.isSelected();
			if (!value) {
				clickJavascriptExecutor(driver, display_element);
			} 
		    value = display_element.isSelected();
		    //System.out.println("pickup in app is: "+value);
		}
		else 
		{ 
			System.out.println("pickupCheckbox NOT EXIST!");
		}
	}
	
	public void deactivatePickup(WebDriver driver){
		WebElement display_element = pickupCheckbox;
		if (display_element.isEnabled()) 
		{
			boolean value = display_element.isSelected();
			if (value) {
				clickJavascriptExecutor(driver, display_element);
			} 
		    value = display_element.isSelected();
		    //System.out.println("pickupCheckbox is selected " + value);
		    
			
		}
		else 
		{ 
			System.out.println("pickupCheckbox NOT EXIST!");
		}
	}
	
	public void activateDelivery(WebDriver driver){
		WebElement display_element = deliveryCheckbox;
		if (display_element.isEnabled()) 
		{
			boolean value = display_element.isSelected();
			if (!value) {
				clickJavascriptExecutor(driver, display_element);
			} 
		    value = display_element.isSelected();
		    //System.out.println("delivery in app is: "+value);
		}
		else 
		{ 
			System.out.println("deliveryCheckbox NOT EXIST!");
		}
	}
	
	public void deactivateDelivery(WebDriver driver){
		WebElement display_element = deliveryCheckbox;
		if (display_element.isEnabled()) 
		{
			boolean value = display_element.isSelected();
			if (value) {
				clickJavascriptExecutor(driver, display_element);
			} 
		    value = display_element.isSelected();			
		}
		else 
		{ 
			System.out.println("deliveryCheckbox NOT EXIST!");
		}
	}
	
	public void chooseInhouseDrivers(WebDriver driver){
		WebElement display_element = inHouseDrivers;
		if (display_element.isEnabled()) 
		{
			boolean value = display_element.isSelected();
			if (!value) {
				clickJavascriptExecutor(driver, display_element);
			} 
		    value = display_element.isSelected();  
		}
		else 
		{ 
			System.out.println("inHouseDrivers NOT EXIST!");
		}
		
	}
	
	public void chooseCredit(WebDriver driver){
		WebElement display_element = creditCardPayments;
		if (display_element.isEnabled()) 
		{
			clickJavascriptExecutor(driver, display_element);
			boolean value = display_element.isSelected();
		}
		else 
		{ 
			System.out.println("creditCardPayments NOT EXIST!");
		}
	}
	
	public void chooseCollect(WebDriver driver){
		WebElement display_element = collectPayment;
		if (display_element.isEnabled()) 
		{
			clickJavascriptExecutor(driver, display_element);
			boolean value = display_element.isSelected();
		}
		else 
		{ 
			System.out.println("creditCardPayments NOT EXIST!");
		}
	}
	
	public void insertDeliveryFee(WebDriver driver, String num){
		if (deliveryFee.size()!=0) 
		{
			deliveryFee.get(0).clear();
			deliveryFee.get(0).sendKeys(num);
		}
		else 
		{ 
			System.out.println("deliveryFee NOT EXIST!");
		}
	}
	
	
	public void insertTaxRate(WebDriver driver, String num){
		if (taxRate.size()!=0) 
		{
			taxRate.get(0).clear();
			taxRate.get(0).sendKeys(num);
		}
		else 
		{ 
			System.out.println("taxRate NOT EXIST!");
		}
	}
	
	public void activateStricterAuthorization(WebDriver driver){
		if (stricterAuthorization.size()!=0) 
		{
			boolean value = stricterAuthorization.get(0).isSelected();
			if (!value) {
				clickJavascriptExecutor(driver, stricterAuthorization.get(0));
			} 
		    value = stricterAuthorization.get(0).isSelected();
		}
		else 
		{ 
			System.out.println("stricterAuthorization NOT EXIST!");
		}
	}
	
	public void deactivateStricterAuthorization(WebDriver driver){
		if (stricterAuthorization.size()!=0) 
		{
			boolean value = stricterAuthorization.get(0).isSelected();
			if (value) {
				clickJavascriptExecutor(driver, stricterAuthorization.get(0));
			} 
		    value = stricterAuthorization.get(0).isSelected();
		}
		else 
		{ 
			System.out.println("stricterAuthorization NOT EXIST!");
		}
	}
	
	public void activateProcessingCost(WebDriver driver){
		if (processingCost.size()!=0) 
		{
			boolean value = processingCost.get(0).isSelected();
			if (!value) {
				clickJavascriptExecutor(driver, processingCost.get(0));
			} 
		    value = processingCost.get(0).isSelected();
		}
		else 
		{ 
			System.out.println("processingCost NOT EXIST!");
		}
	}
	
	public void deactivateProcessingCost(WebDriver driver){
		if (processingCost.size()!=0) 
		{
			boolean value = processingCost.get(0).isSelected();
			if (value) {
				clickJavascriptExecutor(driver, processingCost.get(0));
			} 
		    value = processingCost.get(0).isSelected();
		}
		else 
		{ 
			System.out.println("processingCost NOT EXIST!");
		}
	}
	
	public void setPickupTurnaroundTime(WebDriver driver, String hour, String minute){
		if (pickupTurnaroundHours.size()!=0) 
		{
			pickupTurnaroundHours.get(0).clear();
			pickupTurnaroundHours.get(0).sendKeys(hour);
		} 
		else { 
			System.out.println("pickupTurnaroundHours NOT EXIST!");
		}
		if (pickupTurnaroundMinutes.size()!=0) {
			pickupTurnaroundMinutes.get(0).click();
			String min = "0";
			switch (minute) {
            case "0":
                min = "1";
                break;
            case "15":
            	min = "2";
                break;
            case "30":
            	min = "3";
                break;
            case "45":
            	min = "4";
                break;
			}
			String element = "//select[@ng-model='order_config.pick_up_turnaround_minutes']/option["+min+"]";
			List <WebElement> pickupTurnaroundMinutes_choose = driver.findElements(By.xpath(element));
			if (pickupTurnaroundMinutes_choose.size()!=0) {
				//System.out.println("pickupTurnaroundMinutes_choose exist");
				pickupTurnaroundMinutes_choose.get(0).click();
			}
		} 
		else { 
			System.out.println("pickupTurnaroundMinutes NOT EXIST!");
		}
	}
	
	public void setDeliveryTurnaroundTime(WebDriver driver, String hour, String minute){
		if (deliveryTurnaroundHours.size()!=0) 
		{
			deliveryTurnaroundHours.get(0).clear();
			deliveryTurnaroundHours.get(0).sendKeys(hour);
		} 
		else { 
			System.out.println("deliveryTurnaroundHours NOT EXIST!");
		}
		if (deliveryTurnaroundMinutes.size()!=0) 
		{
			deliveryTurnaroundMinutes.get(0).click();
			String min = "0";
			switch (minute) {
            case "0":
                min = "1";
                break;
            case "15":
            	min = "2";
                break;
            case "30":
            	min = "3";
                break;
            case "45":
            	min = "4";
                break;
        }
			
		String element = "//select[@ng-model='order_config.delivery_turnaround_minutes']/option["+min+"]";
		List <WebElement> deliveryTurnaroundMinutes_choose = driver.findElements(By.xpath(element));
		if (deliveryTurnaroundMinutes_choose.size()!=0) {
			deliveryTurnaroundMinutes_choose.get(0).click();
		}
		
		} 
		else { 
			System.out.println("deliveryTurnaroundMinutes NOT EXIST!");
		}
	}
	
	public void emailInserted(String email) {
		if (emailAddress_1.size()!=0){
			 String elementText = emailAddress_1.get(0).getAttribute("value"); 
		     if(elementText.isEmpty()) {
		    	 emailAddress_1.get(0).clear();
		    	 emailAddress_1.get(0).sendKeys(email);
		     };
		}
	}
	
	public void clickSaveSettingsButton(WebDriver driver){
		if (saveSettingsButton.size()!=0) 
		{
			clickJavascriptExecutor(driver, saveSettingsButton.get(0));
			System.out.println("saveSettingsButton clicked!");
		}
		else 
		{ 
			System.out.println("saveSettingsButton NOT EXIST!");
		}
	}
	
	
}
