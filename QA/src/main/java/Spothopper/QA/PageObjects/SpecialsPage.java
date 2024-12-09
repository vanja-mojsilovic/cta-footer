package Spothopper.QA.PageObjects;




import Spothopper.QA.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;


public class SpecialsPage extends AbstractComponent {
	
	WebDriver driver;
	
	
	public SpecialsPage(WebDriver driver) 
		{
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
						
		}
	
	//page factory locators
	@FindBy(xpath ="//button[contains(@ng-click,'newSpecial')]")
	List <WebElement> newSpecialLocator;
	
	@FindBy(xpath ="//textarea[contains(@ng-if,'special.edit')]")
	WebElement newSpecialTextareaLocator;
	
	@FindBy(xpath ="//button[contains(@ng-if,'special.edit')]")
	List <WebElement> saveNewSpecialLocator;
	
	@FindBy(xpath ="//tr/td[3]/span")
	List <WebElement> specialsNamesLocator;
	
	@FindBy(xpath =("//button[@class='btn btn-success ng-binding ng-scope']"))
	WebElement yesDeleteLocator;
	
	@FindBy(xpath ="//button[contains(@ng-click,'deleteSpecial')]")
	WebElement deleteSpecialsLocator;
	
	
	
	
	
	
	
	//methods
	public void insertNewSpecialName(WebDriver driver, String specName) throws InterruptedException{
		WebElement element = waitForVisibilityOfElement(driver, newSpecialTextareaLocator, 15);
		element.sendKeys(specName);
		
		Actions actions = new Actions(driver);
        
		WebElement saveElement =element.findElement(By.xpath("//tr[contains(@class,'special-desktop')][1]//button[contains(@ng-click, 'save')][1]"));
		actions.sendKeys(Keys.HOME).perform();
		clickElement(driver,saveNewSpecialLocator.get(0),"saveNewSpecialLocator",15);
	}
	
	public void clickNewSaturdaySpecial(WebDriver driver){
		List <WebElement> button_elements = waitForVisibilityOfElements(driver, newSpecialLocator, 15);
		WebElement saturdayElement = button_elements.stream()
		        .filter(element -> element.getText().contains("Saturday"))
		        .findFirst()  
		        .orElse(null);
		saturdayElement.click();
	}
	
	public void clickAddNewSpecial(WebDriver driver){
		List <WebElement> button_elements = waitForVisibilityOfElements(driver, newSpecialLocator, 15);
		button_elements.get(0).click();
		
	}
	
	public void saveNewSpecial(WebDriver driver){
		WebElement element = waitForVisibilityOfElement(driver, saveNewSpecialLocator.get(0), 15);
		clickElement(driver,element,"saveNewSpecialLocator",15);
	}
	
	public ArrayList<String> getSpecialsListFromApp(WebDriver driver){
		
		 ArrayList<String> resultList = new ArrayList<>();
		 List <WebElement> display_elements 
		 	= waitForVisibilityOfElements(driver, specialsNamesLocator, 15);
		 for(WebElement special : display_elements) {
			 resultList.add(special.getText());
		 }
		 return resultList;
	}
	
	
	public List <WebElement> getTestSpecialElements(WebDriver driver, String spotId){
		List <WebElement> display_elements 
	 	= waitForVisibilityOfElements(driver, specialsNamesLocator, 15);
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
	
	public List <WebElement> getAllSpecialElements(WebDriver driver){
		List <WebElement> display_elements 
	 	= waitForVisibilityOfElements(driver, specialsNamesLocator, 15);
		List <WebElement> resultElements = new ArrayList<>();;
	    for (WebElement specialElement : display_elements) {
		    WebElement trParentElement = specialElement.findElement(By.xpath("./parent::td/parent::tr"));
		    resultElements.add(trParentElement);	
	    }
		return resultElements;
	}
	
	
	
	public String getSpecialName(WebDriver driver, WebElement trElement){
		WebElement spanElement = trElement.findElement(By.xpath("./td[3]/span"));
		System.out.println(spanElement.getText()+" should be deleted");
		return spanElement.getText();
	}
	
	public void clickDeleteButton(WebDriver webDriver, WebElement specialElement, String spotId) throws InterruptedException{
			WebElement deleteElement = specialElement.findElement(By.xpath("./td[7]/div/button[2]"));
			deleteElement.click();
			Thread.sleep(2000);
	}
	
	public void clickYesDelete(WebDriver driver){
		WebElement display_element = waitForVisibilityOfElement(driver, yesDeleteLocator, 15);
		clickJavascriptExecutor(driver, display_element);
		System.out.println("yesDeleteLocator clicked");
	}
	
	
	



	
} // end class
	
	
	

