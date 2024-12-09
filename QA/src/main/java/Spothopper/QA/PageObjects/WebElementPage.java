package Spothopper.QA.PageObjects;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Spothopper.QA.AbstractComponents.AbstractComponent;

public class WebElementPage extends AbstractComponent {
	
	WebDriver driver;
	
	
	public WebElementPage(WebDriver driver) 
		{
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
						
		}
	
	
   
   
    
 // Page factory locators
 	@FindBy(xpath ="//input[@id='tmt_private_parties_active']")
 	WebElement activatePrivatePartiesSwitchLocator;
 	
    // Variables
 	public String type="no_type";
 	public String className="no_class";
 	public String href="no_href";
 	public String id="no_id";
 	public String text="no_text";
 	public String feature="";
 	public int numberOfOrderLinks=0;
 	public String shCtaName="";
 	public String parentClassName="";
    
    
    // Constructor
    public WebElementPage
    	(
    		WebDriver driver,
    		String type,
    		String className,
    		String href,
    		String id,
    		String text) 
    	{
			super(driver);
	        this.driver = driver;
	        this.type = type;
	        this.className=className;
	        this.href=href;
	        this.id=id;
	        this.text=text;
    	}
	
	// Methods
    
	
    
	
	
	
	
} // end class
	
