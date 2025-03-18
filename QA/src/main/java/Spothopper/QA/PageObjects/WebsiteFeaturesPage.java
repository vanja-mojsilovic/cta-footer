package Spothopper.QA.PageObjects;

import Spothopper.QA.AbstractComponents.AbstractComponent;
import freemarker.cache.StrongCacheStorage;

import org.bouncycastle.crypto.engines.ISAACEngine;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.IFactoryAnnotation;

import com.google.common.collect.MoreCollectors;
import com.mysql.cj.xdevapi.Result;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;

import java.util.AbstractMap.SimpleEntry;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class WebsiteFeaturesPage extends AbstractComponent {
	
	WebDriver driver;
	
	public int numberOfFoodMenuLinks = 0;
	
	
	public WebsiteFeaturesPage(WebDriver driver) 
		{
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
						
		}
	
	
	
	
	@FindBy(xpath ="//div[contains(@class,'modal-header recovery-user-info verify-number')]/button[@class = 'close']")
	WebElement closePopupVerifyPhoneNumberLocator;
	
	@FindBy(xpath ="//div[contains(@class, 'navbar-content')]/ul[contains(@class, 'nav navbar-nav')]/li")
	List <WebElement> navigationBarLocator;
	
	@FindBy(xpath ="//div[@id='navbar-collapse-1']/ul[contains(@class, 'nav navbar-nav')]/li")
	List <WebElement> navigationBarLocator_1;
	
	@FindBy(xpath ="//div[@id='navbar-collapse-1']/ul[contains(@class, 'nav navbar-nav')]/li//a")
	List <WebElement> aElementsInNavBarLocator;
	
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
	
	@FindBy(xpath ="//input[contains(@ng-click,'updateOrderCtaLinks')]")
	WebElement saveCtaOnlineOrdersLocator;
	
	@FindBy(xpath ="//a[contains(@class,'bottom-link')]")
	List <WebElement> smartFooterLinkLocator;
	
	@FindBy(xpath ="//a[contains(@class,'custom-temp-btn')]")
	List <WebElement> featureButtonLocator;
	
	@FindBy(xpath ="//a[contains(@id,'-gift-cards_link')]")
	List <WebElement> giftCardsLinkLocator;
	
	@FindBy(xpath ="//div[contains(@class,'gift-cards-content')]//p")
	List<WebElement> giftCardsContentLocator;
	
	@FindBy(xpath ="//li[contains(@class,'drop-down-link')]")
	List <WebElement> dropDownElementsLocator;
	
	@FindBy(xpath ="//div[@id='main-message']/h1/span")
	WebElement cantBeReachedMessageLocator;
	
	@FindBy(xpath ="//input[contains(@ng-model,'order_config.shopping_cart')]")
 	List<WebElement> activateOnlineOrdersCheckboxLocator;
	
	@FindBy(xpath ="//a[@class='custom-temp-btn hvr-fade']")
	WebElement okButtonLocator;
	
	
	
	// Methods
	public String getWebsitePageHtml(WebDriver driver,String testSiteUrl) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String result = "";
		result = (String) jsExecutor.executeScript("return document.documentElement.outerHTML;");
		System.out.println(result.substring(0,100)+" . . . ");
		return result;
	}
	
	public String removeLastCharacterIfMatch(WebDriver driver,String link, String text) {
	    if (link != null && link.endsWith(text)) {
	        return link.substring(0, link.length() - text.length());
	    }
	    return link;
	}
	
	public boolean cantBeReachedPage(WebDriver driver) {
		boolean result=false;
		WebElement element = cantBeReachedMessageLocator;
		String messageText = element.getText().trim().toLowerCase();
		if(messageText.equals("this site can’t be reached")) {
			result=true;
		}
		return result;
	}
	
	public boolean checkAllLinksFromHomePage(WebDriver driver,List<WebElementPage> navBarElements,List<String> ctaLogMessage) throws IOException {
		System.out.println("Checking all the links on home page:");
		boolean result=false;
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
		for(WebElementPage element:navBarElements) {
			List<String> substrings = Arrays.asList("chownow", "grubhub","tel:+","securebrygid");
	        boolean containsSubstring = substrings.stream().anyMatch(element.href::contains);
			if(containsSubstring) {
				 continue;
			}
			boolean openPageSuccess;
			try{
				ctaLogMessage.add("\nlink: "+element.href);
				 int responseCode = goToWithResponseCode(element.href);
				 if(responseCode==-1) {
					 System.out.println(element.href+" Can\'t opet the page, response code: "+responseCode);
					 result=true;
					 return result;
				 }
				 if(element.href.contains("spot-sample")) {
					 result=true;
					 return result;
				 }
			}catch(TimeoutException e) {
				 System.out.println("Page load timed out for URL: " + element.href);
				 result=true;
				 return result;
			}
		}
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		return result;
	}
	
	public boolean checkUrlPageSize(WebDriver driver,String href) throws IOException{
		boolean result = false;
		long pageSize=0;
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Object memoryUsage = jsExecutor.executeScript("return performance.memory.totalJSHeapSize;");
        pageSize = ((Number) memoryUsage).longValue();
        int responseCode = getResponseCode(driver, href);
        if(pageSize<5000000) {
        	
        	result = true;
        }
        System.out.println(href+", page size: "+pageSize+", responseCode: "+responseCode);
        return result;
	}	
	
	public List<FeaturePage> updateFeatures(
			WebDriver driver,
			List<WebElementPage> navBarElements,
			List<FeaturePage> featureListEntered) {
		List<FeaturePage> resultFeatureList = new ArrayList<FeaturePage>();
		int k=0;
		int numberOfParties=1;
		int numberOfCatering=1;
		int numberOfReservations=1;
		int numberOfJobs=1;
		int numberOfSpecials=1;
		for (int i = 0; i < featureListEntered.size(); i++) {
		    FeaturePage feature = featureListEntered.get(i);
		    for (int j = 0; j <= navBarElements.size()-1; j++) {
		    	WebElementPage element = navBarElements.get(j);
		    	if(feature.name.equals("parties") && element.className.contains("party-drop-option")) {
		    		//System.out.println("party-drop-option numberOfParties "+numberOfParties);
		    		//System.out.println(element.href);
		        	if(numberOfParties>1) {
		        		continue;
		        	}
		        	numberOfParties++;
			    }
			    if(feature.name.equals("reserv") && element.className.contains("reservation-drop-option")) {
			    	//System.out.println("reservation-drop-option numberOfReservations "+numberOfReservations);
			    	//System.out.println(element.href);
			    	if(numberOfReservations>1) {
	        			continue;
	        		}
	        		numberOfReservations++;
			    }
			    if(feature.name.equals("cater") && element.className.contains("catering-drop-option")) {
			    	//System.out.println("catering-drop-option numberOfCatering "+numberOfCatering);
			    	//System.out.println(element.href);
			    	if(numberOfCatering>1) {
	        			continue;
	        		}
			    	numberOfCatering++;
			    }
			    if(feature.name.equals("job") && element.className.contains("job-drop-option")) {
			    	//System.out.println("job-drop-option numberOfJobs "+numberOfJobs);
			    	//System.out.println(element.href);
			    	if(numberOfJobs>1) {
	        			continue;
	        		}
			    	numberOfJobs++;
				}
			    if(feature.name.equals("specials") && element.className.contains("custom-drop-option")&&feature.name.equals("specials")) {
			    	//System.out.println("custom-drop-option numberOfSpecials "+numberOfSpecials);
			    	//System.out.println(element.href);
			    	if(numberOfSpecials>1) {
	        			continue;
	        		}
			    	numberOfSpecials++;
				}
				boolean isHrefMatch = (element.href != null && feature.aHrefLink != null && 
						(element.href.contains(feature.aHrefLink)||element.href.contains(feature.smartFooterName)
						||element.href.contains(feature.endOfCtaLink)));
				boolean isIdMatch = (element.id != null && feature.featureElementId != null && 
						(element.href.contains(feature.featureElementId)));
	            boolean isClassMatch = (element.className != null && feature.className != null && element.className.equals(feature.className));
	            boolean ifCondition;
	            if(feature.tmtFeature) {
	            	ifCondition = isClassMatch;
	            	if(!ifCondition) {
	            		ifCondition = isIdMatch;
	            	}
	            	if(!ifCondition) {
	            		ifCondition = isHrefMatch;
	            	}
	            }else {
	            	if(feature.shCtaName.contains("food menu")) {
	            		boolean isIdCustomMatch = (element.href != null && element.href.contains("menus-custom"));
	            		ifCondition = isHrefMatch || isClassMatch || isIdCustomMatch;
	            	}else {
	            		ifCondition = isHrefMatch || isClassMatch;
	            	}
	            }
	            if (ifCondition){
	            	element.href = removeLastCharacterIfMatch(driver,element.href,"#");
	            	feature.href = element.href;
	            	if(feature.shCtaName.toLowerCase().trim().equals("private parties")) {
	            		if(element.href.contains("-party")) {
		            		feature.endOfCtaLink = "-party";
		            		System.out.println("endOfCtaLink changed to -party, "+element.href);
		            	}
	            		if(element.href.contains("-parties")) {
		            		feature.endOfCtaLink = "-parties";
		            		System.out.println("endOfCtaLink changed to -parties, "+element.href);
		            	}
		            	if(element.href.contains("private-parties")) {
		            		feature.endOfCtaLink = "-private-parties";
		            		System.out.println("endOfCtaLink changed to -private-parties, "+element.href);
		            	}		        
		            }
	            	if(feature.shCtaName.toLowerCase().trim().equals("reservations")) {
	            		if(element.href.contains("-reserve")) {
		            		feature.endOfCtaLink = "-reserve";
		            	}
	            		if(element.href.contains("-reservations")) {
		            		feature.endOfCtaLink = "-reservations";
		            	}
	            		
		            }
	            	if(feature.shCtaName.toLowerCase().trim().equals("catering")) {
	            		if(element.href.contains("-cater")) {
		            		feature.endOfCtaLink = "-cater";
		            	}
	            		if(element.href.contains("-catering")) {
		            		feature.endOfCtaLink = "-catering";
		            	}
	            		
		            }
	            	if(feature.shCtaName.toLowerCase().trim().equals("job listing")) {
	            		if(element.href.contains("jobs")) {
		            		feature.endOfCtaLink = "jobs";
		            	}
	            		if(element.href.contains("job-listings")) {
		            		feature.endOfCtaLink = "job-listings";
		            	}
		            }
					resultFeatureList.add(feature);
					k++;
					System.out.println((k)+". "+feature.shCtaName+" href: "+feature.href+", tmt: "+feature.tmtFeature);
					feature.navBarText = element.text;
				}
			}
		}
		return resultFeatureList;
	}
	
	public boolean allOrderDropDownFound(WebDriver driver,List<FeaturePage> onlineOrderDropDownFaetures) {
		boolean result=false;
		//pronaći drop down koji ima bar jedan order
		List<WebElement> dropDownElements = new ArrayList<WebElement>();
		dropDownElements = waitForVisibilityOfElements(driver, dropDownElementsLocator, 15);
		if(!dropDownElements.isEmpty()) {
			for(WebElement element:dropDownElements) {
				
				List<WebElement> allSubElements = element.findElements(By.xpath("//*[contains(@class, 'sub-menu-link')]"));

				List<WebElement> orderSubElements = element.findElements(By.className("sub-menu-link order-drop-option"));
				if (!orderSubElements.isEmpty() && orderSubElements.size() == allSubElements.size()) {
				    result = true;
				}
			}
		}
		return result;
	}
	
	public void fillFoodMenuDropDownList(WebDriver driver,List<FeaturePage> foodMenuDropDownFaetures,List<WebElementPage> navBarElements) {
		int foodMenuNumber = 1;
		String navBarText = "";
		for(WebElementPage element:navBarElements) {
			if(element.className.contains("food-drop-option")){
				String foodMenuName = "food menu link "+foodMenuNumber;
				foodMenuDropDownFaetures.add(new FeaturePage(driver, "menu",foodMenuName, "food-menu_link",false,"-food-menu","food-menu","-food-menu","food-menu","no_name"));
				foodMenuDropDownFaetures.get(foodMenuDropDownFaetures.size()-1).navBarText = element.text;
				int num = foodMenuDropDownFaetures.size() - 1;
				foodMenuDropDownFaetures.get(num).navBarText = navBarText;
				foodMenuDropDownFaetures.get(num).href = element.href;
				foodMenuNumber++;
			}
		}
	}
	
	public void fillDrinkMenuDropDownList(WebDriver driver,List<FeaturePage> drinkMenuDropDownFaetures,List<WebElementPage> navBarElements) {
		int drinkMenuNumber = 1;
		String navBarText = "";
		for(WebElementPage element:navBarElements) {
			if(element.className.contains("drink-drop-option")){
				String drinkMenuName = "food menu link "+drinkMenuNumber;
				drinkMenuDropDownFaetures.add(new FeaturePage(driver, "drinks",drinkMenuName, "drink-menu_link",false,"-drink-menu","drink-menu","-drink-menu","drink-menu","no_name"));
				drinkMenuDropDownFaetures.get(drinkMenuDropDownFaetures.size()-1).navBarText = element.text;
				int num = drinkMenuDropDownFaetures.size() - 1;
				drinkMenuDropDownFaetures.get(num).navBarText = navBarText;
				drinkMenuDropDownFaetures.get(num).href = element.href;
				drinkMenuNumber++;
			}
		}
	}
	
	public void fillPrivatePartiesDropDownList(WebDriver driver,List<FeaturePage> privatePartiesDropDownFaetures,List<WebElementPage> navBarElements) {
		int privatePartiesNumber = 1;
		String navBarText = "";
		for(WebElementPage element:navBarElements) {
			if(element.className.contains("party-drop-option")){
				privatePartiesDropDownFaetures.add(new FeaturePage(driver, "parties","private parties", "private-parties",true,"-party","private-parties","-party","parties","link-parties-sh"));
				privatePartiesDropDownFaetures.get(privatePartiesDropDownFaetures.size()-1).navBarText = element.text;
				int num = privatePartiesDropDownFaetures.size() - 1;
				privatePartiesDropDownFaetures.get(num).navBarText = navBarText;
				privatePartiesDropDownFaetures.get(num).href = element.href;
				System.out.println(privatePartiesNumber+". "+privatePartiesNumber+" "+element.href);
				privatePartiesNumber++;
			}
		}
	}
	
	public void fillOrderDropDownList(WebDriver driver,List<FeaturePage> onlineOrderDropDownFaetures,List<WebElementPage> navBarElements) {
		System.out.println("Drop down order links:");
		int orderNumber = 1;
		String navBarText = "";
		for(WebElementPage element:navBarElements) {
			boolean tmt = false;
			if(element.href.contains("https://tmt.spotapps.co/ordering-menu")) {
				tmt = true;
			}
			if(element.className.contains("link-order-sh")){
				String orderName = "order link "+orderNumber;
				onlineOrderDropDownFaetures.add(new FeaturePage(driver, "order",orderName, "ordering-menu",tmt,"ordering-menu","ordering-menu","ordering-menu","order","link-order-sh"));
				onlineOrderDropDownFaetures.get(onlineOrderDropDownFaetures.size()-1).navBarText = element.text;
			}else {
				if(element.className.contains("order") 
					|| orderingPlatforms.stream().anyMatch(element.href::contains) 
					|| element.parentClassName.contains("order")){
						String orderName = "order link "+orderNumber;
						onlineOrderDropDownFaetures.add(new FeaturePage(driver, "order",orderName, "ordering-menu",tmt,"ordering-menu","ordering-menu","ordering-menu","order","link-order-sh"));
						int num = onlineOrderDropDownFaetures.size() - 1;
						navBarText = element.text;
						if(navBarText.length()>10){
							if(navBarText.startsWith("order from")){
								navBarText=navBarText.substring(11);
							}
							if(navBarText.startsWith("order at")){
								navBarText=navBarText.substring(9);
							}
							if(navBarText.startsWith("delivery")){
								navBarText=navBarText.substring(9);
							}
							if(navBarText.endsWith("location")){
								navBarText=navBarText.substring(0,navBarText.length()-9);
							}
						}
						onlineOrderDropDownFaetures.get(num).navBarText = navBarText;
						onlineOrderDropDownFaetures.get(num).href = element.href;
						System.out.println(orderNumber+". "+orderName+" "+"navBarText: "+navBarText+"href: "+element.href);
						orderNumber++;
				}
			}
		}
	}
	
	public boolean hasDropUp(WebDriver driver){
		boolean result=false;
		List <WebElement> elements = smartFooterLinkLocator;
		for(WebElement element:elements) {
			String className = element.getAttribute("class");
			if(className.contains("sub-menu")) {
				result=true;
			}
		}
		return result;
	}
	
	public void getActiveStatusTmtFeatures(WebDriver driver,List<FeaturePage> featurePageListOnWebsite,VariablesAndUrlsPage variablesAndUrlsPage,
			String spotIdFromPopupOrJson) throws InterruptedException, IOException{
		int numberOfPrivateParties=1;
		int numberOfReservations=1;
		int numberOfCatering=1;
		int numberOfJobs=1;
		int numberOfOrders=1; 
		for(FeaturePage feature:featurePageListOnWebsite) {
			if(feature.tmtFeature) {
				if(feature.shCtaName.equals("private parties")) {
					
					if(numberOfPrivateParties>1) {
						//continue;
					}
					//goTo(variablesAndUrlsPage.privatePartiesSettingsURL);
					goToWithResponseCode("https://www.spothopperapp.com/api/spots/"+spotIdFromPopupOrJson+"/private_parties_settings");
        			Thread.sleep(1000);
        			WebElement apiPElement = waitForVisibilityOfElement(driver, apiPreLocator, 3);
        			String apiCode = apiPElement.getText();
        			boolean active = false;
        			if(apiCode.contains("\"tmt_private_parties_active\":true")) {
        				active = true;
        			}
        			feature.setIsActiveInFeature(driver,active);
        			numberOfPrivateParties++;
        		}
        		if(feature.shCtaName.equals("catering")) {
        			if(numberOfCatering>1) {
						//continue;
					}
        			//feature.goTo(variablesAndUrlsPage.cateringSettingsURL);
        			goToWithResponseCode("https://www.spothopperapp.com/api/spots/"+spotIdFromPopupOrJson+"/catering_settings");
        			Thread.sleep(1000);
        			WebElement apiPElement = waitForVisibilityOfElement(driver, apiPreLocator, 3);
        			String apiCode = apiPElement.getText();
        			boolean active = false;
        			if(apiCode.contains("\"tmt_catering_active\":true")) {
        				active = true;
        			}
        			feature.setIsActiveInFeature(driver,active);
        			numberOfCatering++;
        		}
        		if(feature.shCtaName.equals("reservations")) {
        			if(numberOfReservations>1) {
						//continue;
					}
        			//feature.goTo(variablesAndUrlsPage.reservationsSettingsURL);
        			goToWithResponseCode("https://www.spothopperapp.com/api/spots/"+spotIdFromPopupOrJson+"/reservation_settings");
        			Thread.sleep(1000);
        			WebElement apiPElement = waitForVisibilityOfElement(driver, apiPreLocator, 3);
        			String apiCode = apiPElement.getText();
        			boolean active = false;
        			if(apiCode.contains("\"tmt_reservations_active\":true")) {
        				active = true;
        			}
        			feature.setIsActiveInFeature(driver,active);
        			numberOfReservations++;
        		}
        		if(feature.shCtaName.equals("job listing")) {
        			if(numberOfJobs>1) {
						//continue;
					}
        			//feature.goTo(variablesAndUrlsPage.jobApplicationsSettingsURL);
        			goToWithResponseCode("https://www.spothopperapp.com/api/spots/"+spotIdFromPopupOrJson+"/job_listing_settings");
        			Thread.sleep(1000);
        			WebElement apiPElement = waitForVisibilityOfElement(driver, apiPreLocator, 3);
        			String apiCode = apiPElement.getText();
        			boolean active = false;
        			if(apiCode.contains("\"tmt_job_listing_active\":true")) {
        				active = true;
        			}
        			feature.setIsActiveInFeature(driver,active);
        			numberOfJobs++;
        		}
        		if(feature.shCtaName.equals("order link 1")) {
        			if(numberOfOrders>1) {
						//continue;
					}
        			//int responseCode = goToWithResponseCode(variablesAndUrlsPage.ordersSettingsURL);
        			goToWithResponseCode("https://www.spothopperapp.com/api/spots/"+spotIdFromPopupOrJson+"/order_configs");
        			Thread.sleep(1000);
        			WebElement apiPElement = waitForVisibilityOfElement(driver, apiPreLocator, 3);
        			String apiCode = apiPElement.getText();
        			boolean active = false;
        			if(apiCode.contains("\"shopping_cart\":true")) {
        				active = true;
        			}
            		feature.setIsActiveInFeature(driver,active); 
        			numberOfOrders++;
        		}
			}
		}
	}
	
	public String cutUsualExternalLinks(WebDriver driver,String href) {
		if(href.length()>250){
			int questionMarkIndex = href.indexOf('?');
		    if (questionMarkIndex > 0) {
		        href = href.substring(0, questionMarkIndex);
		    }
		}
		return href;
	}
	
	public String checkIfDeactivateTmtHasLink(WebDriver driver,List<FeaturePage> featurePageListOnWebsite) throws IOException {
		String result = "";
		for(FeaturePage feature:featurePageListOnWebsite) {
			if(!feature.activeInSh && !feature.href.equals("") && feature.tmtFeature && feature.href.length()>250) {
				String tempFeatureHref = cutUsualExternalLinks(driver,feature.href);
				int responseCode = goToWithResponseCode(tempFeatureHref);
				if(responseCode == -1) {
					result = feature.shCtaName.toUpperCase();
				}
				
			}
		}
		return result;
	}
	
	
	public String checkTarget(WebDriver driver,String domain) {
		String result = "";
		int i=0;
		System.out.println("Feature target:");
		List<WebElement> footerElements = smartFooterLinkLocator;
		List<String> externalPlatforms =  Arrays.asList("chownow.com", "resy.com","toasttab.com");
		for(WebElement element:footerElements) {
			String href = element.getAttribute("href");
			
			String target = element.getAttribute("target");
			JavascriptExecutor js = (JavascriptExecutor) driver;
            String text = (String) js.executeScript("return arguments[0].textContent;", element).toString().trim().toLowerCase();
			i++;
            System.out.println((i)+". "+text+" target: "+target);
			if(href.contains(domain) && externalPlatforms.stream().noneMatch(href::contains)) {
				if(!target.equals("_self")) {
					System.out.println("href: "+href);
					result = "self";
				}
			}
			if(href.contains("spotapps")) {
				if(!target.equals("_self")) {
					result = "spotapps";
				}
			}
			if(href.contains("/gift-cards")) {
				if(!target.equals("_self")) {
					result = "gift-cards";
				}
			}
		}
		return result;
	}
	
	public void getAllFooterFeatures(WebDriver driver,List<FeaturePage> featurePageListOnWebsite) {
		System.out.println("Footer links text: ");
		List<WebElement> footerElements = smartFooterLinkLocator;
		for(FeaturePage feature:featurePageListOnWebsite) {
			for(WebElement element:footerElements) {
				String featureEndOfTheLinkString = feature.endOfCtaLink.trim();
				String elementHrefString = element.getAttribute("href").trim();
				boolean containsInHref = elementHrefString.contains(featureEndOfTheLinkString);
				if(containsInHref) {
					feature.smartFooterName = feature.name;
					feature.smartFooterText = element.getText().trim().toLowerCase();
					System.out.println(" * "+feature.shCtaName+" footer-text: "+feature.smartFooterText);
				}
			}
		}
	}
	
	public List<String> getAllSmartFooterFeatureNames(WebDriver driver) {
		List<String> resultList = new ArrayList<>();
		List<WebElement> elements = waitForVisibilityOfElements(driver, smartFooterLinkLocator, 15);
		for(WebElement element:elements) {
			resultList.add(element.getText().toLowerCase().trim());
		}
		resultList.sort(null);
		return resultList;
	}
	
	public List<String> getTMTSmartFooterNames(WebDriver driver, List<FeaturePage> featuresPageList) {
		List<String> resultList = new ArrayList<>();
		List<WebElement> elements = waitForVisibilityOfElements(driver, smartFooterLinkLocator, 15);
		int k=0;
		for(FeaturePage feature:featuresPageList) {
			if(feature.tmtFeature) {
				for(WebElement element:elements) {
					String featureEndOfTheLink = feature.endOfCtaLink.trim();
					String elementHref = element.getAttribute("href").trim();
					JavascriptExecutor js = (JavascriptExecutor) driver;
		            String text = (String) js.executeScript("return arguments[0].textContent;", element).toString().trim().toLowerCase();
					boolean containsInHref = (elementHref.contains(featureEndOfTheLink) || text.contains(feature.name));
					if(containsInHref) {
						feature.smartFooterName = feature.name;
						feature.smartFooterText = element.getText().trim().toLowerCase();
						resultList.add(feature.smartFooterText);
						k++;
						System.out.println((k)+". navbar-text: "+feature.navBarText+" footer-text: "+feature.smartFooterText
								+" href:"+elementHref);				
					}
				}
			}
		}
		System.out.println("tmt features in smart footer: "+resultList);
		return resultList;
	}
	
	public boolean checkGiftCardsFeature(WebDriver driver,List<WebElementPage> navBarElements) throws InterruptedException, IOException{
		boolean result = false;
		for(WebElementPage element:navBarElements){
			
			if(element.className.equals("gift-cards-link")) {
				String urlGiftCards = element.href;
				goTo(urlGiftCards);
				Thread.sleep(2000);
			
				List<WebElement> contentElement = giftCardsContentLocator;
				String actualText="";
				if(!contentElement.isEmpty()) {
					actualText=contentElement.get(0).getText();
					String normalizedActualText = actualText.replaceAll("\\s+", "");
					String expectedText="We are working on enabling digital gift cards on our site.Please stay tuned.";
					String normalizedExpectedText=expectedText.replaceAll("\\s+", "");
					if(normalizedActualText.equals(normalizedExpectedText)) {
						System.out.println("Default Gift cards page!");
					}
				}else {
					String shorterLink = cutUsualExternalLinks(driver,urlGiftCards);
					int responseCode = goToWithResponseCode(urlGiftCards);
					if(shorterLink.length()>250 || responseCode==-1) {
						result = true;
						System.out.println("gift cards link too long "+shorterLink);
					}else {
						element.href = shorterLink; // ovo treba uneti u SH CTA 
					}
				}
			}
		}
		return result;
	}
	
	public List<FeaturePage> getFeaturesNotOnNavBar(WebDriver driver,List<FeaturePage> featurePageListOnWebsite,List<FeaturePage> featurePageList){
		List<FeaturePage> resultFeaturePages = new ArrayList<>();
	    Set<String> featureNamesOnWebsite = new HashSet<>();
	    for (FeaturePage featureOnWebsite : featurePageListOnWebsite) {
	        featureNamesOnWebsite.add(featureOnWebsite.shCtaName);
	    }
	    for (FeaturePage feature : featurePageList) {
	        if (!featureNamesOnWebsite.contains(feature.shCtaName)) {
	            resultFeaturePages.add(feature);
	        }
	    }
		return resultFeaturePages;
	}
	
	public void getFeaturesOnButtons(WebDriver driver,List<FeaturePage> featuresNotOnNavBar,List<FeaturePage> featurePageListOnWebsite) {
		boolean existOnButton = false;
		int k=1;
		for(FeaturePage feature:featuresNotOnNavBar) {
			existOnButton = searchFeatureOnButtons(driver,feature);
			if(existOnButton) {
				featurePageListOnWebsite.add(feature);
			}else {
				System.out.println((k)+". "+feature.shCtaName);
				k++;
			}
		}
	}
	
	
	
	public boolean searchFeatureOnButtons(WebDriver driver,FeaturePage feature) {
		boolean result=false;
		List <WebElement> dispalyedElements = featureButtonLocator;
		//List <WebElement> dispalyedElements = waitForVisibilityOfElements(driver, featureButtonLocator, 15);
		if(!dispalyedElements.isEmpty()) {
			for(WebElement element:dispalyedElements) {
				if(element.getAttribute("href") != null) {
					boolean hrefContains = element.getAttribute("href").contains(feature.buttonOnPageHref);
					boolean textOnButtonContains = element.getText().contains(feature.name);
					boolean hrefStartsWithHttp = element.getAttribute("href").substring(0, 4).equals("http");
					if((hrefContains || textOnButtonContains) && hrefStartsWithHttp) {
						result=true;
					}
				}
			}
		}
		return result;
	}

	public List<FeaturePage> getFDSEFeatures(WebDriver driver,List<FeaturePage> FDESEFeatures){
		List<FeaturePage> FDSEFeatures=new ArrayList<FeaturePage>();
		String[] fdseNameStrings = {"food menu","drink menu","specials","events","gift cards"};
		System.out.println("Features that should be entered in SH:");
		int k=1;
		for (int i = 0; i < FDESEFeatures.size(); i++) {
		    FeaturePage feature = FDESEFeatures.get(i);
			for(String name:fdseNameStrings) {
				if (name.equals(feature.shCtaName)) {
					FDSEFeatures.add(feature);
					System.out.println((k)+". FDSE "+feature.shCtaName+" href: "+feature.href);
					k++;
				}
			}
		}
		return FDSEFeatures;
	}
	
	
	
	public List<WebElementPage> getOrderWebElements(WebDriver driver,List<WebElementPage> navBarElements){
		List<WebElementPage> resultList = new ArrayList<WebElementPage>();
		for(WebElementPage element:navBarElements) {
			boolean conditionHref = element.href.contains("order")  && element.href != null;
			boolean conditionClass = element.className.contains("order")  && element.className != null;
			boolean conditionHttp = (element.href.substring(0, 4).equals("http")) && element.href != null;
			if((conditionHref && conditionHttp) || conditionClass ) {
				resultList.add(element);
			}
		}
		return resultList;
	}
	
	public String clickOkButton(WebDriver driver) {
		String result = "";
		try {
			WebElement element = okButtonLocator;
			result = element.getAttribute("href").trim();
			
		}catch(Exception e){
			
		}
		return result;
	}
	
	public List<WebElementPage> getAllementsFromNavBar(WebDriver driver) {
		List<WebElementPage> webElementObjectList = new ArrayList<WebElementPage>();
		List <WebElement> displayElements = aElementsInNavBarLocator;
		if(!aElementsInNavBarLocator.isEmpty()) {
			System.out.println("All features from nav bar:");
			for (int i = 0; i < displayElements.size(); i++) {
			    WebElement element = displayElements.get(i); 
			    String className = element.getAttribute("class");
			    String href = element.getAttribute("href");
			    
			    href = href != null ? href.trim() : "";
			    String id = element.getAttribute("id"); 
			    JavascriptExecutor js = (JavascriptExecutor) driver;
				//String text = (String) js.executeScript("return arguments[0].textContent;", element).toString().trim().toLowerCase(); 
			    Object scriptResult = js.executeScript(
			    	    "return Array.from(arguments[0].childNodes)" +
			    	    ".filter(node => node.nodeType === Node.TEXT_NODE && node.textContent.trim().length > 0)" +
			    	    ".map(node => node.textContent.trim())" +
			    	    ".join(' ');", element);
			    String text = scriptResult != null ? scriptResult.toString().trim().toLowerCase() : "";
				List<WebElement> parentElements 
					= element.findElements(By.xpath("./ancestor::li[contains(@class, 'drop-down-link')]/a[contains(@class, 'drop-down')]"));
				if(!href.equals("")) {
					webElementObjectList.add(new WebElementPage(
							driver,
							"a",
							className,
							href,
							id,
							text
							));
					if(!parentElements.isEmpty()) {
						webElementObjectList.get(webElementObjectList.size()-1).parentClassName 
							= parentElements.get(0).getAttribute("class") != null 
							? parentElements.get(0).getAttribute("class") : "no-class";
					}
					String parentClassName = webElementObjectList.get(webElementObjectList.size()-1).parentClassName;
					System.out.println((i+1)+". "+text
							+", href: "+href
							+", class: "+className
							+", parent: "+parentClassName);
				}
			}
		}
		return webElementObjectList;
	}
	
	public String determinateShCtaName(WebDriver driver,
			String text,
			String href,
			String id,
			String className) {
		String result="no_name";
		return result;
	}
	
	public boolean hasDropDown(WebDriver driver,List<WebElementPage> navBarElements) {
		boolean dropDown = false;
		for(WebElementPage element:navBarElements) {
			if(element.className.contains("drop-down")) {
				dropDown = true;
			}
		}
		return dropDown;
	}
	
	public List<FeaturePage> navBarFaeturesOnWebsite(WebDriver driver, List<FeaturePage> featurePageList){
		List <WebElement> displayElements = waitForVisibilityOfElements(driver, navigationBarLocator_1, 15);
		for (WebElement element : displayElements) {
			String element_class_name = element.getAttribute("class");
			if(!element_class_name.equals("logo-holder")) {
				WebElement anchorElement;
				String featureElementId;
				String hrefAttribute;
				boolean dropDown = false;
				int subNum=0;
				if(element_class_name.contains("drop-down-link")) {
					element.click();
					List <WebElement> dropDownElements = element.findElements(By.xpath("//div[@class='sub-menu']/a"));
					anchorElement = waitForVisibilityOfElement(driver, dropDownElements.get(0), 15);
					featureElementId = anchorElement.getAttribute("href");
					hrefAttribute = anchorElement.getAttribute("href");
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
		            	//feature.presentOnWebsite=true;
		            	feature.href = anchorElement.getAttribute("href");
		                break;
		            }
		        }
			}
		}
		return featurePageList;
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
				String hrefAttribute;
				boolean dropDown = false;
				int subNum=0;
				if(element_class_name.contains("drop-down-link")) {
					element.click();
					List <WebElement> dropDownElements = element.findElements(By.xpath("//div[@class='sub-menu']/a"));
					anchorElement = waitForVisibilityOfElement(driver, dropDownElements.get(0), 15);
					featureElementId = anchorElement.getAttribute("href");
					hrefAttribute = anchorElement.getAttribute("href");
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
		            	feature.href = anchorElement.getAttribute("href");
		                break;
		            }
		        }
			}
		}
		return featurePageList;
	}
	
	
	
	
	
	public void enterCtaLinks(WebDriver driver,FeaturePage feature,String spotId,String timeStamp) throws IOException {
		ReadWriteFilePage readWriteFilePage = new ReadWriteFilePage(driver);
		if(!feature.getName().trim().contains("order link")) {
			List <WebElement> displayElements = waitForVisibilityOfElements(driver,shCtaLinkNameLocator, 15);
			for(WebElement element : displayElements) {
				String cta_feature_name = element.getText();
				WebElement inputElement = element.findElement(By.xpath("./../../div[@class='input-group link']/input[@type='text']"));
				String ctaUrl = feature.endOfCtaLink;
		        if(cta_feature_name.toLowerCase().contains(feature.getName().toLowerCase())) {
		        	if(ctaUrl != null && !ctaUrl.trim().isBlank()) {
		        		System.out.println(cta_feature_name+": "+ctaUrl);
		        		clearAndSendKeys(driver,inputElement,feature.shCtaName+" inputElement",15,ctaUrl);
		        		readWriteFilePage.createMenuFile(driver, spotId+"_"+feature.getName(),timeStamp);
		        	}else {
		        		System.out.println(feature.getName().toLowerCase()+"ctaUrl is blank");
		        	}
		        }
			}
			clickElement(driver,saveCtaLinksButtonLocator,"saveCtaLinksButtonLocator",15);
		}else{
			// Online orders cta links
			System.out.println(feature.getName()+" razmatramo");
			String ctaUrl = feature.thirdPartyLink;
			if(ctaUrl != null && !ctaUrl.trim().isBlank()) {
				System.out.println(feature.getName().toLowerCase()+" postoji link");
				clearAndSendKeys(driver,onlineOrders2Locator,"onlineOrders2Locator input element",15,ctaUrl);
				
				readWriteFilePage.createMenuFile(driver, spotId+"_"+feature.getName(),timeStamp);
			}
			clickElement(driver,saveCtaOnlineOrdersLocator,"saveCtaOnlineOrdersLocator",15);
		}		
	}
	
	public void checkCtaLinks(WebDriver driver,List<FeaturePage> featurePageList) {
		List <WebElement> displayElements = waitForVisibilityOfElements(driver,shCtaLinkNameLocator, 15);
		for(FeaturePage feature : featurePageList ) {
			for (WebElement element : displayElements) {
				String cta_feature_name = element.getText();
				WebElement inputElement = element.findElement(By.xpath("./../../div[@class='input-group link']/input[@type='text']"));
				String ctaUrl = inputElement.getAttribute("value");
		        if(cta_feature_name.toLowerCase().contains(feature.getName().toLowerCase())) {
		        	if(!ctaUrl.trim().isBlank()) {
			        	feature.setPresentInSh(true);
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
		        String text = anchorElement.getText().trim().toLowerCase();
		        String feature_name = anchorElement.getAttribute("id");
		        System.out.println(feature_name);
		        featuresList.add(text);
			}
	        counter++;
	    }
		return featuresList;
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
	
	
	
	
		
}
	
	
	

