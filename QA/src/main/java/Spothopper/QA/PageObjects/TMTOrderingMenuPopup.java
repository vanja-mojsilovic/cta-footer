package Spothopper.QA.PageObjects;

import Spothopper.QA.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class TMTOrderingMenuPopup extends AbstractComponent {
	
	WebDriver driver;
	
	
	public TMTOrderingMenuPopup(WebDriver driver) {
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);		
	}
	
	
	
	@FindBy(xpath ="//input[@id='pickup']")
	List <WebElement> pickupRadioButton;
	
	@FindBy(xpath ="//input[@id='delivery']")
	List <WebElement> deliveryRadioButton;
	
	@FindBy(xpath ="//input[@id='orderNow']")
	List <WebElement> orderNowRadioButton;
	
	@FindBy(xpath ="//input[@id='schedule']")
	List <WebElement> scheduleForLaterRadioButton;
	
	@FindBy(xpath ="//input[@id='date-picker']")
	List <WebElement> datePickerPopupLocator;
	
	@FindBy(xpath ="//span[@class='vs__selected']")
	List <WebElement> timePickerPopupLocator;
	
	@FindBy(xpath ="//button[@type='button' and @data-dismiss='modal' and contains(@class, 'btn-success')]")
	WebElement startOrder;
	
	
	
	
	public void clickPickupDelivery(WebDriver driver,String insertedPickupDelivery){
		switch(insertedPickupDelivery){
		case "pickup":
			if (pickupRadioButton.size()!=0) 
			{
				clickJavascriptExecutor(driver, pickupRadioButton.get(0));
			}
			else 
			{ 
				System.out.println("pickupRadioButton not exist");
			}
			break;
		case "delivery":
			if (deliveryRadioButton.size()!=0) 
			{
				clickJavascriptExecutor(driver, deliveryRadioButton.get(0));
			}
			else 
			{ 
				System.out.println("deliveryRadioButton not exist");
			}
			break;
		
		}
	}
	
	public void clickOrderNow(WebDriver driver){
		
		if (orderNowRadioButton.size()!=0) 
		{
			clickJavascriptExecutor(driver, orderNowRadioButton.get(0));
		}
		else 
		{ 
			System.out.println("orderNowRadioButton not exist");
		}
		
	}
	
	public void clickScheduleForLater(WebDriver driver){
		
		if (scheduleForLaterRadioButton.size()!=0) 
		{
			clickJavascriptExecutor(driver, scheduleForLaterRadioButton.get(0));
		}
		else 
		{ 
			System.out.println("scheduleForLaterRadioButton not exist");
		}
	}
	
	public void clickNowLater(WebDriver driver,String insertedNowLater){
		switch(insertedNowLater){
		case "now":
			if (orderNowRadioButton.size()!=0) 
			{
				clickJavascriptExecutor(driver, orderNowRadioButton.get(0));
			}
			else 
			{ 
				System.out.println("orderNowRadioButton not exist");
			}
			break;
		case "later":
			if (scheduleForLaterRadioButton.size()!=0) 
			{
				clickJavascriptExecutor(driver, scheduleForLaterRadioButton.get(0));
			}
			else 
			{ 
				System.out.println("scheduleForLaterRadioButton not exist");
			}
			break;
		
		}
	}
	
	public String getOrderDatePopup(WebDriver driver){
		if (datePickerPopupLocator.size()!=0) 
		{
			String date = datePickerPopupLocator.get(0).getAttribute("value");
			return date;
		}
		else 
		{ 
			System.out.println("datePickerPopupLocator not exist");
			return null;
		}
	}
	
	public String getOrderTimePopup(WebDriver driver){
		if (timePickerPopupLocator.size()!=0) 
		{
			String orderTimePopup = timePickerPopupLocator.get(0).getText();
			if (orderTimePopup.charAt(0) == '0') {
	                orderTimePopup = orderTimePopup.substring(1);
	            }
			return orderTimePopup;
		}
		else 
		{ 
			System.out.println("timePickerPopupLocator not exist");
			return null;
		}
	}
	
	
	
	public void clickStartOrder(WebDriver driver){
		WebElement display_element = waitForVisibilityOfElement(driver, startOrder, 5);
		if (display_element.isEnabled()) 
		{
			clickJavascriptExecutor(driver, display_element);
		}
		else 
		{ 
			System.out.println("startOrder not exist");
		}
	}
	
}
	
	
	

