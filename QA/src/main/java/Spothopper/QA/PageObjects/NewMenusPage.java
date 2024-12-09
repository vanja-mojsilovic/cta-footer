package Spothopper.QA.PageObjects;

import Spothopper.QA.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class NewMenusPage extends AbstractComponent {
	
	WebDriver driver;
	
	
	public NewMenusPage(WebDriver driver) {
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);		
	}
	
	
	
	@FindBy(xpath ="//input[@ng-model='newFoodMenu.name']")
	WebElement newMenuName;
	
	@FindBy(xpath ="//textarea[@ng-model='newFoodMenu.description']")
	WebElement newMenuDescriptionLocator;
	
	@FindBy(xpath ="//textarea[@ng-model='newFoodMenuSection.description']")
	WebElement newMenuSectionDescriptionLocator;
	
	@FindBy(xpath ="//textarea[@ng-model='newMenuItem.description']")
	WebElement newMenuItemDescriptionLocator;
	
	@FindBy(xpath ="//button[@ng-click='saveNewFoodMenu();']")
	WebElement saveNewMenu;
	
	@FindBy(xpath ="//input[@ng-model='newFoodMenuSection.name']")
	WebElement newMenuSectionName;
	
	@FindBy(xpath ="//div[contains(@ng-if,'addingSection')]/button[contains(@class,'dropdown-toggle')]")
	WebElement menuSectionDropdownLocator;
	
	@FindBy(xpath ="//a[contains(@ng-click, 'prepareAddFoodMenuSection')]")
	WebElement createNewMenuSectionLocator;
	
	@FindBy(xpath ="//button[@ng-click='saveNewFoodMenuSection();']")
	WebElement saveNewMenuSection;

	@FindBy(xpath ="//input[@ng-model='newMenuItem.name']")
	WebElement newMenuItemName;
	
	@FindBy(xpath ="//input[@ng-model='price.cents']")
	WebElement newMenuItemPrice;
	
	@FindBy(xpath ="//button[@ng-click='saveMenuItem();']")
	WebElement saveNewMenuItem;
	
	public void insertNewMenuName(WebDriver driver, String inserted_name){
		WebElement display_element = waitForVisibilityOfElement(driver, newMenuName, 15);
		if (display_element.isEnabled()) 
		{
			display_element.clear();
			display_element.sendKeys(inserted_name);;
		}
		else 
		{ 
			System.out.println("newMenuName NOT EXIST!");
			driver.quit();
		}
	}
	
	public void insertNewMenuDescription(WebDriver driver, String inserted_name){
		WebElement display_element = waitForVisibilityOfElement(driver, newMenuDescriptionLocator, 15);
		if (display_element.isEnabled()) 
		{
			display_element.clear();
			display_element.sendKeys(inserted_name);;
		}
		else 
		{ 
			System.out.println("newMenuDescriptionLocator NOT EXIST!");
			driver.quit();
		}
	}
	
	public void clickSaveButtonNewMenu(WebDriver driver){
		WebElement display_element = waitForVisibilityOfElement(driver, saveNewMenu, 15);
		if (display_element.isEnabled()) 
		{
			clickJavascriptExecutor(driver, display_element);
		}
		else 
		{ 
			System.out.println("saveNewMenu NOT EXIST!");
			driver.quit();
		}
	}
	
	public void insertNewMenuSectionName(WebDriver driver, String inserted_name){
		WebElement display_element = waitForVisibilityOfElement(driver, newMenuSectionName,15);
		if (display_element.isEnabled()) 
		{
			display_element.clear();
			display_element.sendKeys(inserted_name);
		}
		else 
		{ 
			System.out.println("newMenuSectionName NOT EXIST!");
			driver.quit();
		}
	}
	
	public void insertNewMenuSectionDescription(WebDriver driver, String inserted_name){
		WebElement display_element = waitForVisibilityOfElement(driver, newMenuSectionDescriptionLocator, 15);
		if (display_element.isEnabled()) 
		{
			display_element.clear();
			display_element.sendKeys(inserted_name);;
		}
		else 
		{ 
			System.out.println("newMenuName NOT EXIST!");
			driver.quit();
		}
	}
	
	public void clickMenuSectionDropDown(WebDriver driver){
		WebElement display_element = waitForVisibilityOfElement(driver, menuSectionDropdownLocator, 15);
		if (display_element.isEnabled()) 
		{
			clickJavascriptExecutor(driver, display_element);
		}
		else 
		{ 
			System.out.println("menuSectionDropdownLocator NOT EXIST!");
		}
	}
	
	
	public void clickcreateNewMenuSection(WebDriver driver){
		WebElement display_element = waitForVisibilityOfElement(driver, createNewMenuSectionLocator, 15);
		if (display_element.isEnabled()) 
		{
			clickJavascriptExecutor(driver, display_element);
		}
		else 
		{ 
			System.out.println("createNewMenuSectionLocator NOT EXIST!");
		}
	}
	
	
	
	public void clickSaveButtonNewMenuSection(WebDriver driver){
		WebElement display_element = waitForVisibilityOfElement(driver, saveNewMenuSection, 15);
		if (display_element.isEnabled()) 
		{
			clickJavascriptExecutor(driver, display_element);
		}
		else 
		{ 
			System.out.println("saveNewMenuSection NOT EXIST!");
		}
	}
	
	public WebElement insertNewMenuItemName(WebDriver driver, String inserted_name){
		WebElement display_element = waitForVisibilityOfElement(driver, newMenuItemName, 15);
		if (display_element.isEnabled()) 
		{
			display_element.clear();
			display_element.sendKeys(inserted_name);
			System.out.println("newMenuItemName inserted");
			return newMenuItemName;
		}
		else 
		{ 
			System.out.println("newMenuItemName NOT EXIST!");
			driver.quit();
			return null;
		}
	}
	
	public void insertNewMenuItemPrice(WebDriver driver, String inserted_price){
		WebElement display_element = waitForVisibilityOfElement(driver, newMenuItemPrice, 15);
		if (display_element.isEnabled()) 
		{
			display_element.clear();
			display_element.sendKeys(inserted_price);
			System.out.println("newMenuItemPrice inserted");
		}
		else 
		{ 
			System.out.println("newMenuItemPrice NOT EXIST!");
			driver.quit();
		}
	}
	
	public void insertNewMenuItemDescription(WebDriver driver, String inserted_name){
		WebElement display_element = waitForVisibilityOfElement(driver, newMenuItemDescriptionLocator, 15);
		if (display_element.isEnabled()) 
		{
			display_element.clear();
			display_element.sendKeys(inserted_name);;
		}
		else 
		{ 
			System.out.println("newMenuName NOT EXIST!");
			driver.quit();
		}
	}
	
	public void clickSaveButtonNewMenuItem(WebDriver driver){
		WebElement display_element = waitForVisibilityOfElement(driver, saveNewMenuItem, 15);
		if (display_element.isEnabled()) 
		{
			clickJavascriptExecutor(driver, display_element);
			System.out.println("saveNewMenuItem clicked!");
		}
		else 
		{ 
			System.out.println("saveNewMenuItem NOT EXIST!");
			driver.quit();
		}
	}
	
	public String getMenuNumber(String insertedString) {
        String[] parts = insertedString.split("/");
        if (parts.length >= 8) {
            String result = parts[7];
            if (result.length() >= 4) {
                result = result.substring(0, 4);
                return result;
            } else {
                return "Error: String behind the seventh backslash is less than four characters";
            }
        } else {
            return "Error: Not enough parts in the URL";
        }
    }
}
	
	
	

