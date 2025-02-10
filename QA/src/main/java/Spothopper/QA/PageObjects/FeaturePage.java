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

public class FeaturePage extends AbstractComponent {
	
	WebDriver driver;
	
	
	public FeaturePage(WebDriver driver) 
		{
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
	        this.hasThirdParty = false;
						
		}
	
	// Page factory locators
	 	@FindBy(xpath ="//input[@id='tmt_private_parties_active']")
	 	WebElement activatePrivatePartiesSwitchLocator;
	 	
	 	@FindBy(xpath ="//input[@id='tmt_catering_active']")
	 	WebElement activateCateringSwitchLocator;
	 	
	 	@FindBy(xpath ="//input[@id='switch']")
	 	WebElement activateReservationsSwitchLocator;
	 	
	 	@FindBy(xpath ="//input[contains(@ng-model,'order_config.shopping_cart')]")
	 	List<WebElement> activateOnlineOrdersCheckboxLocator;
	 	
	 	@FindBy(xpath ="//input[contains(@ng-model,'tmt_job_listing_active')]")
	 	WebElement activateJobsApplicatonsCheckboxLocator;
	 	
	 	@FindBy(xpath ="//button[@id='saveChangesButton']")
	 	WebElement saveChangesLocator;
	 	
	 	@FindBy(xpath ="//button[contains(@ng-click,'submitOrderConfig')]")
	 	WebElement saveOrdersButtonLocator;
	 	
	 	@FindBy(xpath ="//button[contains(@ng-click,'submitJobListingSetting')]")
	 	WebElement saveJobsButtonLocator;
	 	
	 	@FindBy(xpath ="//a[contains(@ng-click,'Activate Now')]")
	 	WebElement activateNowPrivatePartiesLocator;
	 	
	 	@FindBy(xpath ="//button[contains(@class,'do-it-here')]")
	 	List <WebElement> doItHereLocator;
	 	
	 	@FindBy(xpath ="//input[@id='qa-testing']")
	 	List <WebElement> isThisQaTestLocator;
	 	
	 	@FindBy(xpath ="//input[contains(@ng-click,'submitJobListingSetting')]")
	 	WebElement enableEventPlannerLocator;
	
	// Variables
	public String name;
	public String shCtaName;
    public String featureElementId;
    public boolean activeInSh;
    
    public String thirdPartyLink;
    public boolean hasThirdParty;
    public boolean shouldDeactivate;
    public boolean tmtFeature;
    
    public String endOfCtaLink;
    public String aHrefLink;
    public String smartFooterName;
    public String buttonOnPageHref;
    public String className;
    public String smartFooterText;
    public String navBarText;
    public String href;
    public String ctaShUrl;
    
    // Constructor
    public FeaturePage(
    		WebDriver driver,
    		String name,
    		String shCtaName, 
    		String featureElementId, 
    		boolean tmtFeature,
    		String endOfCtaLink,
    		String aHrefLink,
    		String buttonOnPageHref,
    		String smartFooterName,
    		String className) {
		super(driver);
        this.driver = driver;
        this.name = name;
        this.shCtaName = shCtaName;
        this.featureElementId = featureElementId;
        this.tmtFeature = tmtFeature;
        this.endOfCtaLink = endOfCtaLink;
        this.aHrefLink = aHrefLink;
        this.buttonOnPageHref = buttonOnPageHref;
        this.smartFooterName = smartFooterName;
        this.className = className;
  
        this.activeInSh = false;
        this.shouldDeactivate = false;
        this.navBarText = "";
        this.href="";
        this.ctaShUrl="";
        
    }
	
	// Methods
    public void fillfeaturePageList(List<FeaturePage> featurePageList) {
		featurePageList.add(new FeaturePage(driver, "specials","specials", "specials",false,"-specials","specials","-specials","none","no_name"));
        featurePageList.add(new FeaturePage(driver, "parties","private parties", "private-parties",true,"-party","private-parties","-party","parties","link-parties-sh"));
        featurePageList.add(new FeaturePage(driver, "events","events", "events_link",false,"-events","events","-events","events","no_name"));
        featurePageList.add(new FeaturePage(driver, "drinks","drink menu", "drink-menu_link",false,"-drink-menu","drink-menu","-drink-menu","drink-menu","no_name"));
        featurePageList.add(new FeaturePage(driver, "menu","food menu", "food-menu_link",false,"-food-menu","food-menu","-food-menu","food-menu","no_name"));
        featurePageList.add(new FeaturePage(driver, "reserv","reservations", "reservations",true,"-reservations","reservations","-reservations","reserve","link-reservations-sh"));
        featurePageList.add(new FeaturePage(driver, "cater","catering", "catering",true,"-catering","catering","-catering","catering","link-catering-sh"));
        featurePageList.add(new FeaturePage(driver, "gift cards","gift cards", "giftcards_link",false,"-gift-cards","gift-cards","no_link","giftcards","gift-cards-link"));
        featurePageList.add(new FeaturePage(driver, "job","job listing", "job-listings",true,"job-listings","job-listings","job-listings","jobs","link-jobs-sh"));
        featurePageList.add(new FeaturePage(driver, "directions","directions", "directions",false,"no_link","no_link","no_link","none","no_name"));
        featurePageList.add(new FeaturePage(driver, "order","order link 1", "ordering-menu",true,"ordering-menu","ordering-menu","ordering-menu","order","link-order-sh"));
	}
    
    
			
	
    public boolean isActiveTmt(WebDriver driver,WebElement activationElement) {
		WebElement element = activationElement;
		boolean isActive = element.getAttribute("class").contains("ng-not-empty");
		return isActive;
	}
	
	public void setIsActiveInFeature(WebDriver driver) {
		if(this.shCtaName.equals("private parties") ) {
			this.activeInSh=false;
			boolean isChecked = activatePrivatePartiesSwitchLocator.isSelected();
			if(isChecked) {
				this.activeInSh=true;
			}
		}
		if(this.shCtaName.equals("catering")) {
			this.activeInSh=false;
			boolean isChecked = activateCateringSwitchLocator.isSelected();
			if(isChecked) {
				this.activeInSh=true;
			}
		}
		if(this.shCtaName.equals("reservations")) {
			this.activeInSh=false;
			boolean isChecked = activateReservationsSwitchLocator.isSelected();
			if(isChecked){
				this.activeInSh=true;
			}
		}
		if(this.shCtaName.equals("job listing")) {
			this.activeInSh=false;
			boolean isChecked = activateJobsApplicatonsCheckboxLocator.isSelected();
			if(isChecked){
				this.activeInSh=true;
			}
		}
		if(this.shCtaName.equals("order link 1")) {
			this.activeInSh = false;
			WebElement element =  activateOnlineOrdersCheckboxLocator.get(0);
			boolean isChecked = element.isSelected();
			if(isChecked){
				this.activeInSh=true;
			}
		}
		System.out.println(" * "+this.shCtaName+" active: "+activeInSh);
	}
    
    
	public List<FeaturePage> getTmtFeatures(WebDriver driver){
		List<FeaturePage> allFeatures = new ArrayList<>();
		fillfeaturePageList(allFeatures);
		List<FeaturePage> tmtFeatures = new ArrayList<>();
		for(FeaturePage feature:allFeatures) {
			if(feature.tmtFeature=true) {
				tmtFeatures.add(feature);
			}
		}
		return tmtFeatures;
	}
	
	public void clickSaveJobsChanges(WebDriver driver) {
		clickElement(driver, saveJobsButtonLocator, "saveJobsButtonLocator", 15);
	}
	
	public void clickSaveOrdersChanges(WebDriver driver) {
		clickElement(driver, saveOrdersButtonLocator, "saveOrdersButtonLocator", 15);
	}
	
	
	public void deactivateTmtFeature(WebDriver driver,String featureName){
		Map<String, WebElement> featureLocators = new HashMap<>();
	    featureLocators.put("activatePrivateParties", activatePrivatePartiesSwitchLocator);
	    featureLocators.put("activateCatering", activateCateringSwitchLocator);
	    featureLocators.put("activateReservations", activateReservationsSwitchLocator);
	    featureLocators.put("activateOnlineOrders", activateOnlineOrdersCheckboxLocator.get(0));
	    featureLocators.put("activateJobsApplications", activateJobsApplicatonsCheckboxLocator);
	    System.out.println(featureLocators);	    
	    WebElement element = featureLocators.get(featureName);
	    System.out.println("izabrani lokator "+element);
	    if(element.isSelected()) {
	    	clickJavascriptExecutor(driver,element);
	    }
	};
	
	public void confirmDeactivation(WebDriver driver){
		if(!doItHereLocator.isEmpty()) {
			clickElement(driver, doItHereLocator.get(0), "doItHereLocator.get(0)", 15);
		}
		
		if(!isThisQaTestLocator.isEmpty()) {
			clickElement(driver, isThisQaTestLocator.get(0), "isThisQaTestLocator", 15);
		}
		if(!doItHereLocator.isEmpty()) {
			clickElement(driver, doItHereLocator.get(1), "doItHereLocator.get(1)", 15);
		}
	}
	
	public void clickSaveChanges(WebDriver driver){
		clickElement(driver,saveChangesLocator, "saveChangesLocator", 15);
	}
	
	
	
	public void activateJobsApplications(WebDriver driver,
			String spotId,
			ReadWriteFilePage readWriteFilePage,
			String personalName,
			String timeStamp) throws IOException {
		WebElement element = activateJobsApplicatonsCheckboxLocator;
		boolean isActive = element.getAttribute("class").contains("ng-not-empty");
		if(!isActive) {
			System.out.println("activateJobsApplicatonsCheckboxLocator is not active!");
			clickJavascriptExecutor(driver,element);	
			clickJavascriptExecutor(driver,saveJobsButtonLocator);
			System.out.println("activateJobsApplicatonsCheckboxLocator activated");
		}else {
			System.out.println("activateJobsApplicatonsCheckboxLocator is already active");
		}
		String file_name = spotId+"_activated_job-listings"+"_"+personalName;
		readWriteFilePage.createTmtMenuFile(driver,file_name,timeStamp);
		readWriteFilePage.createTmtMenuFile(driver,file_name,String.valueOf(isActive));
	}
	
	public void activateOnlineOrders(WebDriver driver,
			String spotId,
			ReadWriteFilePage readWriteFilePage,
			String personalName,
			String timeStamp) throws IOException {
		WebElement element = activateOnlineOrdersCheckboxLocator.get(0);
		boolean isActive = element.getAttribute("class").contains("ng-not-empty");
		if(!isActive) {
			System.out.println("activateOnlineOrdersCheckboxLocator is not active!");
			clickJavascriptExecutor(driver,element);	
			clickJavascriptExecutor(driver,saveOrdersButtonLocator);
			System.out.println("activateOnlineOrdersCheckboxLocator activated");
		}else {
			System.out.println("activateOnlineOrdersCheckboxLocator is already active");
		}
		String file_name = spotId+"_activated_ordering-menu"+"_"+personalName;
		readWriteFilePage.createTmtMenuFile(driver,file_name,timeStamp);
		readWriteFilePage.createTmtMenuFile(driver,file_name,String.valueOf(isActive));
	}	
	
	public void activateReservations(WebDriver driver,
			String spotId,
			ReadWriteFilePage readWriteFilePage,
			String personalName,
			String timeStamp) throws IOException {
		WebElement element = activateReservationsSwitchLocator;
		boolean isActive = element.getAttribute("class").contains("ng-not-empty");
		if(!isActive) {
			System.out.println("activateReservationsSwitchLocator is not active!");
			clickJavascriptExecutor(driver,element);	
			clickJavascriptExecutor(driver,saveChangesLocator);
			System.out.println("activateReservationsSwitchLocator activated");
		}else {
			System.out.println("activateReservationsSwitchLocator is already active");
		}
		String file_name = spotId+"_activated_reservations#_link"+"_"+personalName;
		readWriteFilePage.createTmtMenuFile(driver,file_name,timeStamp);
		readWriteFilePage.createTmtMenuFile(driver,file_name,String.valueOf(isActive));
	}
	
	public void activateCatering(WebDriver driver,
			String spotId,
			ReadWriteFilePage readWriteFilePage,
			String personalName,
			String timeStamp) throws IOException, InterruptedException {
		WebElement element = activateCateringSwitchLocator;
		boolean isActive = element.isSelected();
		if(!isActive) {
			System.out.println("activateCateringSwitchLocator is not active!");
			clickJavascriptExecutor(driver,element);
			clickJavascriptExecutor(driver,saveChangesLocator);
			System.out.println("activateCateringSwitchLocator activated");
		}else {
			System.out.println("activateCateringSwitchLocator is already active");
		}
		String file_name = spotId+"_activated_catering#_link"+"_"+personalName;
		readWriteFilePage.createTmtMenuFile(driver,file_name,timeStamp);
		readWriteFilePage.createTmtMenuFile(driver,file_name,String.valueOf(isActive));
	}
	
	
	
	public void activatePrivateParties(WebDriver driver,
			String spotId,
			ReadWriteFilePage readWriteFilePage,
			String personalName,
			String timeStamp) throws IOException {
		WebElement element = activatePrivatePartiesSwitchLocator;
		boolean isActive = element.getAttribute("class").contains("ng-not-empty");
		if(!isActive) {
			System.out.println("activatePrivatePartiesSwitch is not active!");
			clickJavascriptExecutor(driver,element);	
			clickJavascriptExecutor(driver,saveChangesLocator);
			System.out.println("activatePrivatePartiesSwitch activated");
		}else {
			System.out.println("activatePrivatePartiesSwitch is already active");
		}
		String file_name = spotId+"_activated_party#_link"+"_"+personalName;
		readWriteFilePage.createTmtMenuFile(driver,file_name,timeStamp);
		readWriteFilePage.createTmtMenuFile(driver,file_name,String.valueOf(isActive));
	}
	
	
	
	
	
	public void setHasThirdParty(boolean hasThirdPartyEntered) {
		hasThirdParty = hasThirdPartyEntered;
	}
	
	public void setThirdPartyLink(String thirdPartyLinkEntered) {
		thirdPartyLink = thirdPartyLinkEntered;
	}
	
	
	
	
	public String getName() {
        return shCtaName;
    }

    public void setName(String name) {
        this.shCtaName = name;
    }
   
    
    public boolean getPresentInSh() {
        return activeInSh;
    }

    public void setPresentInSh(boolean presentInSh) {
        this.activeInSh = presentInSh;
    }
    
    

    
	
	
	
} // end class
	
