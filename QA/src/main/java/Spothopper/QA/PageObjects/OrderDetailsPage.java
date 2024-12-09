package Spothopper.QA.PageObjects;

import Spothopper.QA.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class OrderDetailsPage extends AbstractComponent {
	
	WebDriver driver;
	
	
	public OrderDetailsPage(WebDriver driver) {
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);		
	}
		
	
	
	@FindBy(xpath ="//div[@ng-if='!inquiry.is_job && !inquiry.is_other']")
	WebElement timeAndDateLocator;

	@FindBy(xpath ="//div[@class='order-details ng-scope']/table/tbody/tr[last()]/td[2]")
	List <WebElement> totalOrderDetailsLocator;
	
	@FindBy(xpath ="//input[@value='Delete Order']")
	List <WebElement> deleteInquiryLocator;
	
	@FindBy(xpath ="//input[@value='Yes']")
	List <WebElement> deleteYesInquiryLocator;
	
	
	
	public void clickDeleteInquiry(WebDriver driver){
		if (deleteInquiryLocator.size()!=0) 
		{
			deleteInquiryLocator.get(0).click();
		}
		else 
		{ 
			System.out.println("deleteInquiryLocator NOT EXIST!");
		}
	}
	
	public void clickDeleteYesInquiry(WebDriver driver){
		if (deleteYesInquiryLocator.size()!=0) 
		{
			deleteYesInquiryLocator.get(0).click();
		}
		else 
		{ 
			System.out.println("deleteYesInquiryLocator NOT EXIST!");
		}
	}
	
	public String getOrderDetailsTimeAndDate(WebDriver driver){
		WebElement display_element = waitForVisibilityOfElement(driver, timeAndDateLocator, 5);
		if (display_element.isDisplayed()) 
		{
			return display_element.getText();
		}
		else 
		{ 
			System.out.println("timeAndDateLocator NOT EXIST!");
			return null;
		}
	}
	
	public String getOrderDetailsTotal(WebDriver driver){
		if (totalOrderDetailsLocator.size()!=0) 
		{
			return totalOrderDetailsLocator.get(0).getText();
		}
		else 
		{ 
			System.out.println("totalOrderDetailsLocator NOT EXIST!");
			return null;
		}
	}
	
	public String getFormatedDateFromDetails(WebDriver driver,String InsertedDate){
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM d['th']['st']['nd']['rd'], yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy MM dd");
        LocalDate parsedDate = LocalDate.parse(InsertedDate, inputFormatter);
        String transformedDate = parsedDate.format(outputFormatter);
        return transformedDate;
	}
	
	public String getTimeAndDateFromOrderDetails(WebDriver driver, String nowLater) {
		String orderDetailsTimeAndDateFull = getOrderDetailsTimeAndDate(driver);
		int indexOfFirstComma = orderDetailsTimeAndDateFull.indexOf(',');
		String orderDetailsTimeAndDate = orderDetailsTimeAndDateFull.substring(indexOfFirstComma + 2);
		return orderDetailsTimeAndDate;
	}
	
	
	
	
	
	
}
	
	
	

