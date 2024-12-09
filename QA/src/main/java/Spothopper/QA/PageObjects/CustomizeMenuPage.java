package Spothopper.QA.PageObjects;

import Spothopper.QA.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class CustomizeMenuPage extends AbstractComponent {
	
	WebDriver driver;
	
	
	public CustomizeMenuPage(WebDriver driver) 
		{
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
						
		}
	
	//page factory locators
	//@FindBy(xpath ="//div[@class='menu-item ng-scope']//p[@class='ng-binding']")
	@FindBy(xpath ="//div[contains(@ng-repeat,'menu in menuSections')]//p[contains(@ng-click,'openMenuEditPage')]")
	List <WebElement> foodAndDrinkMenuNames;
	
	@FindBy(xpath ="//button[@id='dropDownMenuFoodMenus']")
	WebElement menusDropDown;
	
	@FindBy(xpath ="//a[contains(@ng-click, 'food')]")
	WebElement addAnotherFoodMenu;  
	
	@FindBy(xpath ="//a[contains(@ng-click, 'drink')]")
	WebElement addAnotherDrinkMenu;
	
	@FindBy(xpath ="//button[text()='Configure/Set Hours']")
	WebElement configureSetHours;
	
	@FindBy(xpath ="//button[@class='btn btn-delete ng-scope' and @ng-click='deleteFoodMenu(activeFoodMenu);']")
	WebElement deleteMenuTrashProduction;
	
	@FindBy(xpath ="//button[@class='btn btn-delete ng-scope' and @ng-click='deleteFoodMenu(activeFoodMenu);']")
	WebElement deleteMenuTrashStaging;

	@FindBy(xpath ="//button[@class='btn btn-success' and @ng-click='deleteFoodMenuConfirmed(food_menu_to_delete.id);']")
	WebElement yesDeleteMenu;
	
	
	
	//methods
	public ArrayList<String> getFoodAndDrinkMenusFromApp(WebDriver driver){
		 ArrayList<String> resultList = new ArrayList<>();
		 List <WebElement> display_elements = waitForVisibilityOfElements(driver, foodAndDrinkMenuNames, 15);
		 for(WebElement foodOrDrinkMenu : display_elements) {
			 resultList.add(foodOrDrinkMenu.getText());
		 }
		 return resultList;
	}
	
	public void clickPencilButton(WebDriver driver, String menuName){
		By pencilButtonBy = By.xpath("//p[contains(text(),'"+menuName+"')]/parent::div/parent::div/div[@class='btn-holder']/a[contains(@ng-click,'openMenuEditPage')]");
		WebElement pencilElement = driver.findElement(pencilButtonBy);
        clickElement(driver, pencilElement, "pencil_element", 15);
	}
	
	public void clickAddNewFoodMenu(WebDriver driver){
		clickElement(driver, addAnotherFoodMenu, "addAnotherFoodMenu", 15);
	}
	
	public void clickAddNewDrinkMenu(WebDriver driver){
		clickElement(driver, addAnotherDrinkMenu, "addAnotherDrinkMenu", 15);
	}
	
	public void clickMenusDropDown(WebDriver driver){
		clickElement(driver, menusDropDown, "menusDropDown", 15);
	}
	
	public void clickMenuName(WebDriver driver, String m_name){
		WebElement menuNameElement = driver.findElement(By.xpath("//a[@class='ng-binding' and text()='" + m_name + "']"));
		clickElement(driver, menuNameElement, "menuNameElement", 15);
	}
	
	public void clickConfigureSetHours(WebDriver driver){
		clickElement(driver, configureSetHours, "configureSetHours", 15);
	}
	

	public void clickDeleteMenuTrash(WebDriver driver, String production_staging){
		WebElement display_element=null;
		if(production_staging.equals("production")) {
			display_element = waitForClickabilityOfElement(driver, deleteMenuTrashProduction, 15);
		}else {
			display_element = waitForClickabilityOfElement(driver, deleteMenuTrashStaging, 15);
		}
		if (display_element.isEnabled()) 
		{
			display_element.click();
			System.out.println("deleteMenuTrash clicked!");

		}
		else 
		{ 
			System.out.println("deleteMenuTrash NOT EXIST!");
		}
	}
	
	public void clickYesDeleteMenu(WebDriver driver){
		clickElement(driver, yesDeleteMenu, "yesDeleteMenu", 15);
	}
	
	



	
} // end class
	
	
	

