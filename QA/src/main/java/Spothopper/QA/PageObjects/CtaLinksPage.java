package Spothopper.QA.PageObjects;

import Spothopper.QA.AbstractComponents.AbstractComponent;

import org.bouncycastle.crypto.engines.ISAACEngine;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.PublicVerificationKey;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.IFactoryAnnotation;
import org.openqa.selenium.JavascriptExecutor;
import java.util.AbstractMap.SimpleEntry;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


public class CtaLinksPage extends AbstractComponent {
	
	WebDriver driver;
	
	
	public CtaLinksPage(WebDriver driver) 
		{
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);		
		}
	
	
	@FindBy(xpath ="//button[@class = 'close']")
	List <WebElement> closeNewsLetterLocator;
	
	@FindBy(xpath ="//div[contains(@class,'modal-header recovery-user-info verify-number')]/button[@class = 'close']")
	WebElement closePopupVerifyPhoneNumberLocator;
	
	@FindBy(xpath ="//div[contains(@class, 'navbar-content')]/ul[contains(@class, 'nav navbar-nav')]/li")
	List <WebElement> navigationBarLocator;
	
	@FindBy(xpath ="//div[@id='navbar-collapse-1']/ul[contains(@class, 'nav navbar-nav')]/li")
	List <WebElement> navigationBarLocator_1;
	
	@FindBy(xpath ="//input[@id = 'signInFormUsername' and @name='username']")
	List <WebElement> shUserNameLocator;
	
	@FindBy(xpath ="//input[@id = 'signInFormPassword' and @name='password']")
	List <WebElement> shPasswordLocator;
	
	@FindBy(xpath ="//input[@name = 'signInSubmitButton' and @value='Sign in']")
	List <WebElement> shSubmitLocator;
	
	@FindBy(xpath ="//div[@class = 'col-xs-12 link-col']")
	List <WebElement> shCtaLinkLocator;
	
	@FindBy(xpath ="//div[@class = 'col-xs-12 link-col']//p")
	List <WebElement> shCtaLinkNameLocator;
	
	@FindBy(xpath ="//div[@class = 'col-xs-12 link-col']//span")
	List <WebElement> shOrdersCtaLinkNameLocator;
	
	@FindBy(xpath ="//div[@class = 'min-height-content']")
	WebElement VerifyPhoneNumberCloseButtonLocator;
	
	@FindBy(xpath ="//input[contains(@ng-click,'updateCtaLinks')]")
	WebElement saveCtaLinksButtonLocator;
	
	@FindBy(xpath ="//input[contains(@ng-change,'order_cta_links.order2')]")
	WebElement onlineOrders2Locator;
	
	@FindBy(xpath ="//input[contains(@ng-change,'order_cta_links.order')]")
	List <WebElement> onlineOrdersLinksLocator;
	
	@FindBy(xpath ="//input[contains(@ng-click,'updateOrderCtaLinks')]")
	WebElement saveCtaOnlineOrdersLocator;
	
	@FindBy(xpath ="//input[contains(@ng-model,'smart_footer')]")
	List<WebElement> smartFooterLocator;
	
	@FindBy(xpath ="//button[contains(@ng-click,'submitWebsite')]")
	List <WebElement> saveAdminPanelLocator;
	
	@FindBy(xpath ="//select[contains(@ng-model,'smart_footer_version')]")
	WebElement smartFooterVersionLocator;
	
	@FindBy(xpath ="//option[@value='number:2']")
	WebElement option2SmartFooterVersionLocator;
	
	@FindBy(xpath ="//input[contains(@ng-model,'is_wcache')]")
	List <WebElement> wcacheLocator;
	
	@FindBy(xpath ="//input[contains(@name,'footer_link')]")
	List <WebElement> footerLinkNameLocator;
	
	@FindBy(xpath ="//input[contains(@ng-click,'updateWebsiteFooterLinks')]")
	WebElement footerLinkSaveButtonLocator;
	
	@FindBy(xpath = "//span[contains(@ng-click,'moveLink') and contains(@ng-click,'up')]")
	List<WebElement> moveUpLocator;
	
	@FindBy(xpath = "//span[contains(text(),'order2')]/../span[contains(@ng-click,'moveLink') and contains(@ng-click,'up')]")
	WebElement order2ParentElementLocator;
	
	@FindBy(xpath ="//input[contains(@id,'search_param')]")
	WebElement spotIdInputLocator;
	
	@FindBy(xpath ="//button[contains(@class,'button-style') and text()='Search']")
	WebElement submitSpotIdButtonLocator;
	
	@FindBy(xpath ="//button[contains(@onclick,'window.location.href') and text()='View credentials']")
	WebElement viewCredentialsButtonLocator;
	
	@FindBy(xpath ="//table[contains(@class,'clients-table')]//td")
	List<WebElement> domainTdLocator;
	
	@FindBy(xpath ="//div[contains(@class,'message-container')]/p")
	List<WebElement> notFoundMessageLocator;
	
	@FindBy(xpath = "//p[contains(text(),'No credentials available for this client.')]")
	List<WebElement> noCredentialsMessageLocator;

	
	// Methods
	public String getDomainFromPublisInfo(WebDriver driver,String spotIdFromPopupOrJson,String publishInfo,String websiteUrl) throws IOException, InterruptedException{
		String result = websiteUrl;
		goToWithResponseCode(publishInfo + spotIdFromPopupOrJson+"/");
		Thread.sleep(1000);
		//WebElement inputElement = waitForVisibilityOfElement(driver, spotIdInputLocator, 15);
		//inputElement.clear();
		//inputElement.sendKeys(spotIdFromPopupOrJson);
		//Thread.sleep(1000);
		//WebElement submitElement = waitForVisibilityOfElement(driver, submitSpotIdButtonLocator, 15);
		//submitElement.click();
		//Thread.sleep(1000);
		List<WebElement> notFoundElement = waitForVisibilityOfElements(driver, noCredentialsMessageLocator, 3);
		if(notFoundElement.isEmpty()){
			//WebElement viewElement = waitForVisibilityOfElement(driver, viewCredentialsButtonLocator, 15);
			//viewElement.click();
			//Thread.sleep(1000);
			List<WebElement> domainElements = waitForVisibilityOfElements(driver, domainTdLocator,15);
			result = domainElements.get(1).getText();
		}
		return result;
	}
	
	public void removeCtaLinksFeaturesNotOnHomePage(WebDriver driver,List<FeaturePage> featuresNotOnNavBar) {
		List <WebElement> displayElements = shCtaLinkNameLocator;
		for(WebElement element:displayElements) {	
			for(FeaturePage feature:featuresNotOnNavBar) {
				String cta_feature_name = element.getText();
				WebElement inputElement = element.findElement(By.xpath("./../../div[@class='input-group link']/input[@type='text']"));
				String textInInputElement = inputElement.getAttribute("value").trim();
		        if(cta_feature_name.toLowerCase().equals(feature.shCtaName.toLowerCase()) && 
	        		!cta_feature_name.toLowerCase().equals("order link 1")) {
		        		if(!textInInputElement.equals("")) {
		        			inputElement.clear();
		        			System.out.println(textInInputElement+" removed!");
		        	}
		        }
	        }
		}
	}
	
	public String getEndOfHref(WebDriver driver,String hrefFromWebsite) {
		int indexOfLastDash = hrefFromWebsite.lastIndexOf("/");
	    if (indexOfLastDash != -1) {
	        return hrefFromWebsite.substring(indexOfLastDash);
	    } else {
	        return hrefFromWebsite;
	    }
	}
	
	public void enterTmtServices(WebDriver driver,List<FeaturePage> featurePageListOnWebsite,String domain,String spotIdFromPopupOrJson) {		System.out.println("entering Tmt Services in CTA links");
		List <WebElement> displayElements = shCtaLinkNameLocator;
		int counter = 0;
		// enter only first private parties drop down link
		int numberOfParties = 1;
		int numberOfReservations = 1;
		int numberOfCatering = 1;
		int numberOfJobs = 1;
		for(FeaturePage feature:featurePageListOnWebsite)
		{
			if(feature.name.equals("parties") && feature.className.contains("party-drop-option")) {
				if(numberOfParties>1) {
					continue;
				}
				numberOfParties++;
			}
			if(feature.name.equals("reserv") && feature.className.contains("reservation-drop-option")) {
				if(numberOfReservations>1) {
					continue;
				}
				numberOfReservations++;
			}
			if(feature.name.equals("cater") && feature.className.contains("catering-drop-option")) {
				if(numberOfCatering>1) {
					continue;
				}
				numberOfCatering++;
			}
			if(feature.name.equals("job") && feature.className.contains("job-drop-option")) {
				if(numberOfJobs>1) {
					continue;
				}
				numberOfJobs++;
			}
			for(WebElement element:displayElements) {
				//System.out.println("entering Tmt Services in CTA links " + counter);
				counter++;
				domain = putDashOnEndOfUrl(driver,domain);
				String domainWithoutHttp = clearUrlFromHttp(driver,domain);
				if(feature.tmtFeature && (Stream.of(domainWithoutHttp, "tmt.spotapps.co").anyMatch(feature.href::contains))) {
					String cta_feature_name = element.getText();
					WebElement inputElement = element.findElement(By.xpath("./../../div[@class='input-group link']/input[@type='text']"));
					String textInInputElement = inputElement.getAttribute("value").trim();
					if(cta_feature_name.toLowerCase().equals(feature.shCtaName.toLowerCase()) && 
		        		!cta_feature_name.toLowerCase().equals("order link 1") ) {	
							if(feature.href.contains("tmt.spotapps.co")) {
								if(!(textInInputElement.equals("https://tmt.spotapps.co/"+feature.endOfCtaLink+"?spot_id="+spotIdFromPopupOrJson))) {
									inputElement.clear();
									inputElement.sendKeys("https://tmt.spotapps.co/"+feature.endOfCtaLink+"?spot_id="+spotIdFromPopupOrJson);
									System.out.println(textInInputElement+" replaced with: "+"https://tmt.spotapps.co/"+feature.endOfCtaLink+"?spot_id="+spotIdFromPopupOrJson);
								}
							}else {
								if(!textInInputElement.equals(domain+feature.endOfCtaLink)) {					
									inputElement.clear();
									inputElement.sendKeys(domain + feature.endOfCtaLink);
									System.out.println(textInInputElement+" replaced with: "+domain+feature.endOfCtaLink);
								}
							}
					}
				}
			}
		}
	}
	
	public void enterFDSECtaLinks(WebDriver driver, 
			List<FeaturePage> featurePageListFDSE,
			String spotId,
			String timeStamp,
			String domain,
			WebsiteFeaturesPage websiteFeaturesPage) throws IOException, InterruptedException {
		List <WebElement> displayElements = waitForVisibilityOfElements(driver,shCtaLinkNameLocator, 15);
		System.out.println("Entering CTA links in SH:");
		int k=0;
		int numberOfFoodMenus=1;
		int numberOfDrinkMenus=1;
		int numberOfEvents=1;
		int numberOfSpecials=1;
		for(FeaturePage feature:featurePageListFDSE) {
			if(feature.name.equals("menu") && feature.className.contains("food-drop-option")) {
				if(numberOfFoodMenus>1) {
					continue;
				}
				numberOfFoodMenus++;
			}
			if(feature.name.equals("drinks") && feature.className.contains("drink-drop-option")) {
				if(numberOfDrinkMenus>1) {
					continue;
				}
				numberOfDrinkMenus++;
			}
			if(feature.name.equals("events") && feature.className.contains("custom-drop-option")) {
				if(numberOfEvents>1) {
					continue;
				}
				numberOfEvents++;
			}
			if(feature.name.equals("specials") && feature.className.contains("custom-drop-option")) {
				if(numberOfSpecials>1) {
					continue;
				}
				numberOfSpecials++;
			}
			for(WebElement element:displayElements) {	
				boolean enterLink = true;
				String cta_feature_name = element.getText();
				WebElement inputElement = element.findElement(By.xpath("./../../div[@class='input-group link']/input[@type='text']"));
				String textInInputElement = inputElement.getAttribute("value");
		        if(cta_feature_name.toLowerCase().equals(feature.shCtaName.toLowerCase())) {
		        	String navBarLink = feature.href;	        	
		        	if(!navBarLink.equals("")) {
	        			k++;
	        			System.out.println((k)+". "+feature.shCtaName+" entered!");
	        			String shorterLink; 
	        			if(feature.shCtaName.equals("food menu") && (websiteFeaturesPage.numberOfFoodMenuLinks>1)) {
	        				shorterLink = feature.href;
	        			}else {
	        				if(feature.shCtaName.equals("gift cards")
	        					&&!(feature.href.contains(clearUrlFromHttp(driver,domain)))
	        					&&!(feature.href.contains("spotapps"))){
	        					shorterLink = feature.href;
	        				}else {
	        					shorterLink = domain+feature.endOfCtaLink;
	        				}
	        			}
	        			clearAndSendKeys(driver,inputElement,feature.shCtaName+" inputElement",15,shorterLink);
		        		Thread.sleep(1000);
		        	}
		        }
			}
		}
	}
	
	public boolean placeOredrsSideBySide(WebDriver driver,long numberOfOrderLinks) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		boolean result = false;
		int numInt = (int) numberOfOrderLinks;
		System.out.println(">>>>>>>> Number of order elements in footer: "+numInt);
		if(numInt>1) {
			for(int i=2;i<=numInt;i++) {
				String locator = "//span[contains(text(),'order"+i+"')]/../span[contains(@ng-click,'moveLink') and contains(@ng-click,'up')]";
				System.out.println(">>>>>>>>  i in loop: "+i);
				for(int j=0;j<7;j++) {
					Thread.sleep(1000);
					List<WebElement> moveUpElementLocator = driver.findElements(By.xpath(locator));
					List<WebElement> moveUpElement = waitForVisibilityOfElements(driver, moveUpElementLocator, 3);
					if(moveUpElement.isEmpty()) {
						System.out.println("moveUpElement not exist");
						result = true;
					}else {
						System.out.println(">>>>>>>> j in loop: "+j);
						Thread.sleep(2000);
						WebElement element = moveUpElement.get(0);
						js.executeScript("arguments[0].scrollIntoView(true);", element);
						clickJavascriptExecutor(driver,element);
						Thread.sleep(1000);
					}
				}
			}
		}
		Thread.sleep(1000);
		clickElement(driver,footerLinkSaveButtonLocator,"footerLinkSaveButtonLocator",15);
		return result;
	}
	
	
	public void renameOrderLinks(WebDriver driver,List<FeaturePage> onlineOrderDropDownFaetures) throws InterruptedException {
		System.out.println(">>>>>>>>>>>>>onlineOrderDropDownFaetures: "+onlineOrderDropDownFaetures.size());
		int numberOfOrdersIndropDown = onlineOrderDropDownFaetures.size();
		int[] indices = {0, 8, 9, 10}; 
		int limit = Math.min(numberOfOrdersIndropDown, indices.length); 
		for (int i = 0; i < limit; i++) {
			Thread.sleep(1000);
		    int index = indices[i];
		    List<WebElement> elements = waitForVisibilityOfElements(driver, footerLinkNameLocator, 15);
		    String orderName = elements.get(index).getAttribute("value").trim().toLowerCase();
		    String navbarText = onlineOrderDropDownFaetures.get(i).navBarText.trim().toLowerCase();
		    if(orderName.equals("order")) {
		    	if(!navbarText.equals("directly from us") ){
		    		clearAndSendKeys(driver, elements.get(index), "onlineOrderDropDown "+i, 15, onlineOrderDropDownFaetures.get(i).navBarText);
		    	}		    	
		    	System.out.println(onlineOrderDropDownFaetures.get(i).navBarText+" entered!");
		    }
		}
		footerLinkSaveButtonLocator.click();
	}
	
	public void enterOrderLinks(WebDriver driver,List<FeaturePage> onlineOrderDropDownFaetures){
		List<WebElement> elements = waitForVisibilityOfElements(driver, onlineOrdersLinksLocator, 15);
		int numberOfOrders = Math.min(5,onlineOrderDropDownFaetures.size());
		for(int i=0;i<numberOfOrders;i++){
			String text = elements.get(i).getAttribute("value").trim();
			if(text.equals("")) {
				elements.get(i).sendKeys(onlineOrderDropDownFaetures.get(i).href);
				System.out.println("online orders "+i+" link entered");
			}else {
				System.out.println("online orders "+i+" already entered");
			}
		}
		saveCtaOnlineOrdersLocator.click();
	} 
	
	public String putDashOnEndOfUrl(WebDriver driver,String url) {
		String result = url;
		if(!result.endsWith("/")) {
			result = result+"/";
		}
		return result;
	}
	
	public String putHttpsOnUrlAndSlash(WebDriver driver,String url) {
		String result = clearUrlFromHttp(driver,url);
		String httpsResult = "https://"+result;
		result = putDashOnEndOfUrl(driver,httpsResult);
		return result;
	}
	
	
	
	public String compareShAndWebsiteTmtLinks(WebDriver driver,List<FeaturePage> featurePageList,String websiteUrl,String spotIdFromPopupOrJson) throws InterruptedException{
		String result = "";
		List <WebElement> displayElements = waitForVisibilityOfElements(driver,shCtaLinkNameLocator, 15);
		for(FeaturePage feature:featurePageList) {
			String cta_feature_name ="";
			for(WebElement element:displayElements) {
				cta_feature_name = element.getText();
				Thread.sleep(1000);
				WebElement inputElement = element.findElement(By.xpath("./../../div[@class='input-group link']/input[@type='text']"));
				String textInInputElement = inputElement.getAttribute("value");
				if(cta_feature_name.toLowerCase().equals(feature.shCtaName.toLowerCase())) {
					feature.ctaShUrl = textInInputElement;
					String clearedInputElement = clearUrlFromHttp(driver,textInInputElement);
					String expectedCtaLinkWithDomain = clearUrlFromHttp(driver,websiteUrl+feature.endOfCtaLink.trim());
					String expectedCtaLinkWithSpotapps = clearUrlFromHttp(driver,"https://tmt.spotapps.co/"+feature.endOfCtaLink+"?spot_id="+spotIdFromPopupOrJson);
		        	boolean equalToExpectedLinkWithDomain = clearedInputElement.equals(expectedCtaLinkWithDomain);
		        	boolean equalToCtaLinkWithSpotapps = clearedInputElement.equals(expectedCtaLinkWithSpotapps);
		        	if(feature.tmtFeature && feature.activeInSh) {
		        		if(!(equalToExpectedLinkWithDomain || equalToCtaLinkWithSpotapps)){
		        			System.out.println("CTA link in SH: "+clearedInputElement);
		        			if(feature.href.contains("tmt.spotapps")) {
			        			System.out.println("expected link with spotapps: "+expectedCtaLinkWithSpotapps);
			        			result = "spotapps";
			        		}else {
			        			System.out.println("expected link with domain: "+expectedCtaLinkWithDomain);
			        			result = "domain";
			        		}
		        		}
		        	}
				}
	        }
		}
		return result;
	}
	
	public boolean footerVersion1(WebDriver driver) {
		boolean result = false;
		WebElement element = smartFooterVersionLocator;
		Select select = new Select(element);
	    String selectedValue = select.getFirstSelectedOption().getAttribute("value");
		if(selectedValue.equals("number:1")) {
			result = true;
		}
		return result;
	}
	
	
	public void activeWcache(WebDriver driver) {
		if(!wcacheLocator.get(0).isSelected()) {
			clickJavascriptExecutor(driver, wcacheLocator.get(0));
			System.out.println("wcacheLocator clicked!");
		}
	}
	
	
	
	public void enterCtaLinks(WebDriver driver,FeaturePage feature,String spotId,String timeStamp,String domain) 
			throws IOException, InterruptedException {
			ReadWriteFilePage readWriteFilePage = new ReadWriteFilePage(driver);
			String navBarLink = feature.href;
			if(!feature.getName().trim().contains("order link")) {
				List <WebElement> displayElements = waitForVisibilityOfElements(driver,shCtaLinkNameLocator, 15);
				for(WebElement element : displayElements) {
					String cta_feature_name = element.getText();
					WebElement inputElement = element.findElement(By.xpath("./../../div[@class='input-group link']/input[@type='text']"));
					String textInInputElement = inputElement.getAttribute("value");
			        if(cta_feature_name.toLowerCase().contains(feature.getName().toLowerCase())) {
			        	System.out.println(feature.shCtaName+" tekst: "+textInInputElement+" u input elementu: "+element.getText());
			        	System.out.println("tekst "+navBarLink+" ako postoji treba da se upiše ");
			        	System.out.println("ctaUrl != null "+navBarLink != null);
			        	System.out.println("!textInInputElement.trim().isBlank()"+!textInInputElement.trim().isBlank());
			        	if(!navBarLink.equals("")) {
			        		if(textInInputElement.trim().equals("")) {
			        			String shorterLink = domain+feature.endOfCtaLink;
				        		clearAndSendKeys(driver,inputElement,feature.shCtaName+" inputElement",15,shorterLink);
				        		Thread.sleep(1000);
				        		readWriteFilePage.createMenuFile(driver, spotId+"_cta_"+feature.featureElementId,timeStamp);
			        		}
			        	}else {
			        		System.out.println(feature.getName().toLowerCase()+"ctaUrl is blank");
			        	}
			        }
				}
				
			}else{
				// Online orders cta links
				System.out.println(feature.getName()+" razmatramo");
				if(!navBarLink.equals("")) {
					System.out.println(feature.getName().toLowerCase()+" postoji third party link");
					//clearAndSendKeys(driver,onlineOrders2Locator,"onlineOrders2Locator input element",15,navBarLink);
					
					Thread.sleep(1000);
					readWriteFilePage.createMenuFile(driver, spotId+"_cta_"+feature.featureElementId,timeStamp);
				}
				
			}	
			
		}
	
	public boolean click2SmartFooterVersion(WebDriver driver) {
		boolean result = false;
		try {
			option2SmartFooterVersionLocator.click();
		}catch(Exception e) {
			result = true;
		}
		return result;
	}
	
	public void clickDropDownSmartFooterVersion(WebDriver driver) {
		clickElement(driver, smartFooterVersionLocator, "smartFooterVersionLocator", 15);
	}
	
	public void saveAdminPanel(WebDriver driver) {
		clickElement(driver, saveAdminPanelLocator.get(0), "saveAdminPanelLocator.get(0)", 15);
	}
	
	public boolean clickIfNotActiveSmartFooter(WebDriver driver) {
		boolean result = true;
		List<WebElement> elements = smartFooterLocator;
		if(!elements.isEmpty()) {
			if(!elements.get(0).isSelected()) {
				clickJavascriptExecutor(driver, elements.get(0));
			}
			System.out.println("Active status smart footer: "+elements.get(0).isSelected());
			if(elements.get(0).isSelected()) {
				result = false;
			}
		}
		return result;
	}
	
	
	
	public void saveChangesCtaLinks(WebDriver driver) {
		clickElement(driver,saveCtaLinksButtonLocator,"saveCtaLinksButtonLocator",15);
		clickElement(driver,saveCtaOnlineOrdersLocator,"saveCtaOnlineOrdersLocator",15);
	}
	
	public void deleteCtaLink(WebDriver driver,
							FeaturePage feature,
							ReadWriteFilePage readWriteFilePage,
							String spotId) throws IOException {
		List <WebElement> displayElements = waitForVisibilityOfElements(driver,shCtaLinkNameLocator, 15);
		if(feature.getName().equals("order link 1")) {
			System.out.println("online orders cta link se briše");
			clearAndSendKeys(driver, onlineOrders2Locator, "onlineOrders2Locator", 15, "");
			clickElement(driver, saveCtaOnlineOrdersLocator, "saveCtaOnlineOrdersLocator", 15);
			readWriteFilePage.writeDeleteStatusInFile(driver,spotId+"_cta_"+feature.featureElementId);
		}else {
			for (WebElement element : displayElements) {
				String cta_feature_name = element.getText();
				if(cta_feature_name.toLowerCase().contains(feature.getName().toLowerCase())) {
		        	if(feature.hasThirdParty==true) {
		        		WebElement inputElement = element.findElement(By.xpath("./../../div[@class='input-group link']/input[@type='text']"));
		        		clearAndSendKeys(driver, inputElement, "inputElement", 15, "");
		        		clickElement(driver, saveCtaLinksButtonLocator, "saveCtaLinksButtonLocator", 15);
		        		readWriteFilePage.writeDeleteStatusInFile(driver,spotId+"_cta_"+feature.featureElementId);
					}
		        }
			}
		}
	}

	
	
	
	public List <String> getNavigationBarFaetures(WebDriver driver){
		//List <WebElement> displayElements = waitForVisibilityOfElements(driver, navigationBarLocator, 7);
		List <WebElement> displayElements = navigationBarLocator;
		if(displayElements.isEmpty()) {
			displayElements = waitForVisibilityOfElements(driver, navigationBarLocator_1, 5);
		}
		List <String> featuresList = new ArrayList<>();
		int counter = 1;
		for (WebElement element : displayElements) {
			String element_class_name = element.getAttribute("class");
			if(!element_class_name.equals("logo-holder")) {
				WebElement anchorElement = element.findElement(By.tagName("a"));
		        String text = anchorElement.getText().trim();
		        String feature_name = anchorElement.getAttribute("id");
		        System.out.println(feature_name);
		        featuresList.add(text);
			}
	        counter++;
	    }
		return featuresList;
	}
	
	public List<FeaturePage> checkNavBarFaeturesOnWebsite(WebDriver driver, List<FeaturePage> featurePageList){
		List <WebElement> displayElements = navigationBarLocator;
		if(displayElements.isEmpty()) {
			displayElements = waitForVisibilityOfElements(driver, navigationBarLocator_1, 15);
		}
		for (WebElement element : displayElements) {
			String element_class_name = element.getAttribute("class");
			if(!element_class_name.equals("logo-holder")) {
				WebElement anchorElement;
				String featureElementId;
				boolean dropDown = false;
				int subNum=0;
				if(element_class_name.contains("drop-down-link")) {
					element.click();
					List <WebElement> dropDownElements = element.findElements(By.xpath("//div[@class='sub-menu']/a"));
					anchorElement = waitForVisibilityOfElement(driver, dropDownElements.get(0), 15);
					featureElementId = anchorElement.getAttribute("href"); 
					subNum=7;
					dropDown = true;
				} else {
					anchorElement = element.findElement(By.tagName("a"));
					featureElementId = anchorElement.getAttribute("id");
				}	
		       
		        for (FeaturePage feature : featurePageList) {
		            String featureNameInList = feature.featureElementId;
		            if(dropDown) {
		            	if(featureNameInList.contains("reservations")) {
		            		featureNameInList = "reservations";
		            	}
		            	if(featureNameInList.contains("orders")) {
		            		featureNameInList = "orders";
		            	}
		            }
		            boolean isFeaturePresent = featureElementId.contains(featureNameInList);
		            if (isFeaturePresent) {
		            	//feature.setPresentOnWebsite(true);
		                break;
		            }
		        }
			}
		}
		return featurePageList;
	}
	
	
	
	
	public void checkOrdersCtaLinks(WebDriver driver,List<FeaturePage> featurePageList) {
		List <WebElement> displayElements = waitForVisibilityOfElements(driver,shOrdersCtaLinkNameLocator, 15);
		for(FeaturePage feature : featurePageList ) {
			for (WebElement element : displayElements) {
				String cta_feature_name = element.getText();
				WebElement inputElement = element.findElement(By.xpath("./../../div[@class='input-group link'][2]/input[@type='text']"));
				String ctaUrl = inputElement.getAttribute("value");
				
		        if(cta_feature_name.toLowerCase().contains(feature.getName().toLowerCase())) {
		        	if(!ctaUrl.trim().isBlank()) {
			        	feature.setPresentInSh(true);
					}
		        }
		    }
		}
	}
	
	
	public void insertShUserName(WebDriver driver,String ShUserName) {
		List <WebElement> displayElements = shUserNameLocator;
		if(displayElements.get(0).isEnabled()) {
			System.out.println("element enabled!");
			System.out.println(displayElements);
			//System.out.println(displayElements.get(0).getAttribute("placeholder"));
//			displayElements.get(0).sendKeys(ShUserName); //ovde je greška
			//((JavascriptExecutor) driver).executeScript("arguments[0].value = 'aaaaaaaa'", displayElements.get(0));
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].value='newValue';", displayElements.get(0));
            WebElement  element = (WebElement) jsExecutor.executeScript("return document.querySelector(arguments[0]);", "#signInFormUsername");
//          //WebElement element = (WebElement) jsExecutor.executeScript("return document.querySelector(arguments[0]);", "signInFormUsername");
            //jsExecutor.executeScript("arguments[0] = arguments[1];", element, "DEDA");

             String outerHtml = (String) jsExecutor.executeScript("return arguments[0].outerHTML;", element);
			 System.out.println("Outer HTML: " + outerHtml);
			}
		else {
			System.out.println("element not enabled!");
		}
	}
	
	public void insertShPassword(WebDriver driver,String ShUserName) {
		List <WebElement> displayElements = shPasswordLocator;
		if(displayElements.get(0).isEnabled()) {
			displayElements.get(0).sendKeys(ShUserName); //ovde je greška
			}
		else {
			System.out.println("element not enabled!");
		}
	}
	
	public void clickSubmitLogin(WebDriver driver) {
		List <WebElement> displayElements = shSubmitLocator;
		if(displayElements.get(0).isEnabled()) {
			displayElements.get(0).click();
			}
		else {
			System.out.println("element not enabled!");
		}
	}
	
	
	
	public void closeVerifyPhoneNumber(WebDriver driver){
		try {
			WebElement display_element = waitForVisibilityOfElement(driver, closePopupVerifyPhoneNumberLocator, 3);
			if (display_element.isDisplayed()) 
			{
				System.out.println("closePopupVerifyPhoneNumberLocator displayed");
				display_element.click();
			}
			else 
			{ 
				System.out.println("closePopupVerifyPhoneNumberLocator NOT EXIST!");
				driver.quit();
			}
		}
		catch(Exception  e){
			System.out.println("no closePopupVerifyPhoneNumberLocator");
		}
	}
	
	public void printFeaturesList(List<FeaturePage> featurePageList) {
		for(FeaturePage feature : featurePageList) {
	    	System.out.println("("
	    			+feature.getName()
	    			+", website: "
	    			//+feature.getPresentOnWebsite()
	    			+", sh app: "
	    			+feature.getPresentInSh()
	    			+")"
			);
	    }
	}
	
	public void printTmtFeaturesList(List<FeaturePage> featurePageList) {
		List<String> filterValues = Arrays.asList("private parties", "reservations", "catering", "job listing", "order link 1");
		for(FeaturePage feature : featurePageList) {
	    	for(String filterValue : filterValues){
	    		if(feature.getName().equals(filterValue) ) {
	    			//System.out.println(feature.getName()+" website: "+feature.getPresentOnWebsite());
	    		}
	    	}			
	    }
	}
	
	public void closeNewsLetter(WebDriver driver){
		if (closeNewsLetterLocator != null) {
			for(WebElement element : closeNewsLetterLocator) {
				try {
					if (element.isDisplayed()) 
					{
						System.out.println("closeNewsLetterLocator displayed");
						element.click();
					}
				}
				catch(Exception  e){
					System.out.println("no closeNewsLetterLocator");
					}
			}
		}
	}
	
	
		
}
	
	
	

