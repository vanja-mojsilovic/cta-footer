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

public class EventsPage extends AbstractComponent {
	
	WebDriver driver;
	
	
	public EventsPage(WebDriver driver) 
		{
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
						
		}
	
	//page factory locators
	
	@FindBy(xpath ="//button[contains(@ng-click,'newEvent')]")
	WebElement addNewEventButtonLocator;
	
	@FindBy(xpath ="//input[contains(@ng-model,'newEvent')]")
	WebElement eventNameLocator;
	
	@FindBy(xpath ="//div[contains(@id,'taTextElement')]")
	WebElement eventDescriptionLocator;
	
	@FindBy(xpath ="//button[contains(@ng-click,'dateCtrl')]")
	WebElement eventStartDatePickerLocator;
	
	@FindBy(xpath ="//button[contains(@class,'btn btn-default btn-sm active')]")
	WebElement activeInDatePickerLocator;
	
	@FindBy(xpath ="//button[contains(@ng-click,'cancel')]")
	WebElement closeGalleryLocator;
	
	@FindBy(xpath ="//button[contains(@class,'btn-success ')]")
	WebElement createEventLocator;
	
	@FindBy(xpath ="//tr/td[2]/h3")
	List <WebElement> eventNamesLocator;
	
	@FindBy(xpath =("//button[@class='btn btn-success ng-binding ng-scope']"))
	WebElement yesDeleteLocator;
	
	//methods
	
	public void clickAddNewEvenet(WebDriver driver) {
		WebElement element = waitForVisibilityOfElement(driver, addNewEventButtonLocator, 15);
		element.click();
	}
	
	public void insertNewEventName(WebDriver driver, String eventName){
		WebElement element = waitForVisibilityOfElement(driver, eventNameLocator, 15);
		element.sendKeys(eventName);
	}
	
	public void insertNewEventDescription(WebDriver driver,int i){
		WebElement element = waitForVisibilityOfElement(driver, eventDescriptionLocator, 15);
		element.click();
		element.sendKeys("event_desription_"+i);
	}
	
	public void clickEventStartDatePicker(WebDriver driver){
		WebElement element = waitForVisibilityOfElement(driver, eventStartDatePickerLocator, 15);
		element.click();
	}
	
	public void clickActiveDateInDatePicker(WebDriver driver){
		WebElement element = waitForVisibilityOfElement(driver, activeInDatePickerLocator, 15);
		element.click();
	}
	
	public void clickEventDateInCalendar(WebDriver driver, String dateNum){
		
		String dateInCaledarElement = "//button/span[text()='"+dateNum+"']";
		
		List <WebElement> elements = waitForVisibilityOfElements(
				driver, 
				driver.findElements(By.xpath(dateInCaledarElement)), 
				15);
		System.out.println(elements.size());
		clickElement(driver
				,elements.get(elements.size()-1)
				,"EventDateInCalendar"
				,15);
	}
	
	public void clickCloseGallery(WebDriver driver){
		 try {
		        WebElement element = waitForVisibilityOfElement(driver, closeGalleryLocator, 15);
		        clickElement(driver,element,"closeGalleryLocator",15);
		    } catch (NoSuchElementException | TimeoutException e) {
		        System.out.println("Element not found or not visible within the specified time.");
		    }
	}
	
	
	public void clickCreateEvent(WebDriver driver) {
		WebElement element = waitForVisibilityOfElement(driver, createEventLocator, 15);
		clickElement(driver,element,"createEventLocator",15);
	}
	
	public List <WebElement> getTestEventElements(WebDriver driver, String spotId){
		List <WebElement> display_elements 
	 	= waitForVisibilityOfElements(driver, eventNamesLocator, 15);
		List <WebElement> resultElements = new ArrayList<>();;
	    for (WebElement specialElement : display_elements) {
	    	 if(specialElement.getText().length()>=spotId.length()) {
	    		 if(specialElement.getText().substring(0,spotId.length()).equals(spotId)) {
		    		 WebElement trParentElement = specialElement.findElement(By.xpath("./parent::td/parent::tr"));
		    		 resultElements.add(trParentElement);
		    		 }
	    		 }
	    	 }
		return resultElements;
	}
	
	public String getEventName(WebDriver driver, WebElement trElement){
		WebElement h3Element = trElement.findElement(By.xpath("./td[2]/h3"));
		System.out.println(h3Element.getText()+" should be deleted");
		return h3Element.getText();
	}
	
	public void clickDeleteButton(WebDriver webDriver, WebElement eventlElement, String spotId) throws InterruptedException{
		WebElement deleteElement = eventlElement.findElement(By.xpath("./td[4]/button[2]"));
		deleteElement.click();
		Thread.sleep(2000);
	}
	
	public void clickYesDelete(WebDriver driver){
		WebElement display_element = waitForVisibilityOfElement(driver, yesDeleteLocator, 15);
		clickJavascriptExecutor(driver, display_element);
		System.out.println("yesDeleteLocator clicked");
	}
	
} // end class
	
	
