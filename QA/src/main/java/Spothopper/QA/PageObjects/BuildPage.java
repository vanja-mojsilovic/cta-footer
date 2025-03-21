package Spothopper.QA.PageObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Spothopper.QA.AbstractComponents.AbstractComponent;
import software.amazon.awssdk.services.secretsmanager.endpoints.internal.Substring;

public class BuildPage extends WebsiteFeaturesPage {
	
	WebDriver driver;
	
	
	public BuildPage(WebDriver driver) 
		{
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);			
		}
	
	//page factory locators
	
	@FindBy(xpath = "//button[contains(@id,'google-auth-button')]")
	WebElement signInGoogleLocator;
	
	@FindBy(xpath ="//div[@data-email='automation.qa@spothopperapp.com']")
	WebElement chooseAccountLocator;

	@FindBy(xpath = "//button/span[contains(text(),'Continue')]")
	WebElement continueButtonLocator;
	
	@FindBy(xpath = "//a[contains(@id,'already-have-an-account')]")
	WebElement alreadyHaveAccountLocator;
	
	@FindBy(xpath = "//input[contains(@name,'test_website_number')]")
	List<WebElement> testWebsiteNumberLocator;
	
	@FindBy(xpath = "//button[contains(@ng-click,'submitWebsite();')]")
	List<WebElement> saveButtonWebsiteSectionLocator;
	
	@FindBy (xpath = "//input[contains(@ng-class,'website.is_wcache')]") 
	List<WebElement> wcasheCheckboxLocator;
	
	@FindBy (xpath = "//input[contains(@ng-class,'website.is_wcache_test_location')]") 
	List<WebElement> wcasheTestLocationCheckboxLocator;
	
	@FindBy(xpath = "//a[contains(@ng-click,'invalidateWebsiteCache')]")
	WebElement invalidateCashLinkLocator;
	
	@FindBy(xpath = "//button[contains(@ng-if,'buttons.ok')]")
	WebElement invalidateCashOkButtonElement;
	
	@FindBy(xpath = "//input[contains(@ng-model,'spot.website_url')]")
	WebElement domainInputLocator;
	
	@FindBy(xpath = "(//input[contains(@ng-model, 'website.is_real_website')])[1]")
	WebElement temporaryLandingPageRadioButtonLocator;

	@FindBy(xpath = "(//input[contains(@ng-model, 'website.is_real_website')])[2]")
	WebElement realWebsiteRadioButtonLocator;
	
	@FindBy(xpath = "//a[contains(@ng-click,'websiteInProgress')]")
	List<WebElement> startBuildButtonLocator;
	
	@FindBy(xpath = "//a[not(@role='button') and not(contains(@class,'slidenav'))]")
	List<WebElement> allAElementsInLocator;

	@FindBy(xpath = "//p[@dir='auto']/a")
	List<WebElement> currentSiteLocator;
	
	@FindBy(xpath ="//a[contains(@class,'custom-temp-btn')]")
	List<WebElement> buttonVisuallyHiddenLocator;
	
	@FindBy(xpath = "(//a[contains(@id, 'phone')])")
	List<WebElement> phoneNumberLocator;
	
	//methods
	public String checkPhoneNumber(WebDriver driver) {
		String result = "";
		List<WebElement> elements = waitForVisibilityOfElements(driver, phoneNumberLocator, 3);
		for(WebElement element:elements) {
			if(!elements.isEmpty()) {
				String href = element.getAttribute("href").replaceAll("[^\\d]", "").substring(1);
				String aText = element.getText().replaceAll("[^\\d]", "");
				System.out.println("Compare phone numbers: "+href+" : "+aText);
				if(!href.equals(aText)) {
					result ="Phone number link should be corrected.";
				}
			}
		}
		return result;
	}
	
	public String validateVisually(WebDriver driver,WebElement aElement,String spanText,String fullText){
		String result = "";
		String href = aElement.getAttribute("href");
		if(!(href== null) && !href.isEmpty()) {
			if(href.contains("about")) {
				if(!spanText.equals("about us")) {
					result +="About Us visually hidden should be corrected: "+fullText+" "+spanText;
				}
			}
			if(href.contains("cater")) {
				if(!spanText.equals("about catering")) {
					result +="Catering visually hidden should be corrected: "+fullText+" "+spanText;
				}
			}
			if(href.contains("parties")||href.contains("party")) {
				if(!spanText.equals("a private party")) {
					result +="Private parties visually hidden should be corrected: "+fullText+" "+spanText;
				}
			}
			if(href.contains("reserv")) {
				if(!spanText.equals("a table")) {
					result +="Reservations visually hidden should be corrected: "+fullText+" "+spanText;
				}
			}
			if(href.contains("job")) {
				if(!spanText.equals("for a job")) {
					result +="Jobs visually hidden should be corrected: "+fullText+" "+spanText;
				}
			}
		}
		return result;
	}
	
	public String checkVissualyHidden(WebDriver driver) {
		String result="";
		List<WebElement> elements = buttonVisuallyHiddenLocator;
		List<String> visuallyHiddenTexts = Arrays.asList("read more", "inquire", "show details", "learn more","book now","apply");
		if(!elements.isEmpty()) {
			for(WebElement aElement:elements) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
	            String fullText = ((String) js.executeScript("return arguments[0].textContent;", aElement)).toLowerCase().trim();
				boolean isVisuallyHidden = visuallyHiddenTexts.stream().anyMatch(fullText::contains);
				String message = "";
				if(isVisuallyHidden) {
	                try {
	                    WebElement spanElement = aElement.findElement(By.xpath(".//span[contains(@class,'visuallyhidden')]"));
	                    String spanText = ((String) js.executeScript("return arguments[0].textContent;", spanElement)).toLowerCase().trim();
	                    fullText = fullText.replace(spanText, "").trim();
	                    message = fullText + ", visually hidden: " + spanText;
	                    result += validateVisually(driver,aElement,spanText,fullText);
	                } catch (NoSuchElementException e) {
	                	message = fullText+" : " + "No span visually hidden element found inside!";
	                    result += message;
	                }
	                System.out.println(message);
	             }
			}
		}
		return result;
	}

	public boolean hasPromotionPopUpParent(WebElement element) {
	    // Get the parent element
	    WebElement parent = element.findElement(By.xpath("./parent::*"));
	    
	    // Check if the parent is a div with the specific class
	    if (parent.getTagName().equalsIgnoreCase("div") && 
	        parent.getAttribute("class") != null && 
	        parent.getAttribute("class").contains("promotion_pop_up_content")) {
	        return true;
	    }
	    
	    // If not direct parent, try to find any ancestor div with that class
	    try {
	        WebElement ancestor = element.findElement(By.xpath("./ancestor::div[contains(@class,'promotion_pop_up_content')]"));
	        return ancestor != null;
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	}
	
	public String getAllHrefLinks(WebDriver driver,String oldDomain) {
		String result = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		List<WebElement> elements = allAElementsInLocator;
		//List<WebElement> elements = waitForVisibilityOfElements(driver, aElementsInNavBarLocator, 15);
		if(!elements.isEmpty()) {
			int i=1;
			for(WebElement element:elements){
				String aHref = element.getAttribute("href");
				String className = element.getAttribute("class");
				Object scriptResult = js.executeScript(
			    	    "return Array.from(arguments[0].childNodes)" +
			    	    ".filter(node => node.nodeType === Node.TEXT_NODE && node.textContent.trim().length > 0)" +
			    	    ".map(node => node.textContent.trim())" +
			    	    ".join(' ');", element);
			    String aElementText = scriptResult != null ? scriptResult.toString().trim().toLowerCase() : "";
				if(aHref == null || aHref.isEmpty() || aElementText.equals("")) {
					System.out.println("> This href is empty!");
				}else {
					System.out.println("   "+i+". "+aHref+"; "+aElementText+"; "+className);
					if(aHref.contains(oldDomain) && !oldDomain.trim().isEmpty() && !aHref.contains("@")) {
					System.out.println(">>>>> Href contains old domain!");
					result += "\n"+aHref+" Href contains old domain!";
					}
				}
				i++;
			}
		}
		return result;
	}
	
	public String clickRealWebsiteOrTemporaryLandingPage(WebDriver driver,String branchName) {
		String result = "";
		if(branchName.contains("-v1") || branchName.contains("-v2")) {
			WebElement element = waitForVisibilityOfElement(driver, realWebsiteRadioButtonLocator, 15);
			element.click();
			System.out.println("Real Website radio button clicked");
			result += "\nReal Website radio button clicked";
		}
		else {
			if(branchName.contains("-v0")) {
				WebElement element = waitForVisibilityOfElement(driver, temporaryLandingPageRadioButtonLocator, 15);
				element.click();
				System.out.println("Temporary Landing Page radio button clicked");
				result += "\nTemporary Landing Page radio button clicked";
			}
		}
		return result;
	}
	
	public String clickStartBuildButton(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String result = "";
		List<WebElement> elements = waitForVisibilityOfElements(driver, startBuildButtonLocator, 2);
		if(!elements.isEmpty()) {
			WebElement button = elements.get(0);
		    js.executeScript("arguments[0].click();", button);
			//elements.get(0).click();
			System.out.println("Start Build button clicked");
			result = "\nStart Build button clicked";
		}
		return result;
	}
	
	
	
	public void aHrefOnButtons(WebDriver driver) {
		//List <WebElement> dispalyedElements = featureButtonLocator;
		List <WebElement> dispalyedElements = waitForVisibilityOfElements(driver, featureButtonLocator, 15);
		if(!dispalyedElements.isEmpty()) {
			int i=1;
			for(WebElement element:dispalyedElements) {
				String aHref = element.getAttribute("href");
				String aText = element.getText();
				System.out.println("   "+i+". "+aText+": "+aHref);
				i++;
			}
		}
	}
	
	public void getAHrefLinksInNavBar(WebDriver driver,String oldDomain) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		List<WebElement> elements = aElementsInNavBarLocator;
		//List<WebElement> elements = waitForVisibilityOfElements(driver, aElementsInNavBarLocator, 15);
		if(!elements.isEmpty()) {
			int i=1;
			for(WebElement element:elements){
				String aHref = element.getAttribute("href");
				Object scriptResult = js.executeScript(
			    	    "return Array.from(arguments[0].childNodes)" +
			    	    ".filter(node => node.nodeType === Node.TEXT_NODE && node.textContent.trim().length > 0)" +
			    	    ".map(node => node.textContent.trim())" +
			    	    ".join(' ');", element);
			    String text = scriptResult != null ? scriptResult.toString().trim().toLowerCase() : "";
				System.out.println("   "+i+". "+text+": "+aHref);
				if(aHref.contains(oldDomain) && !oldDomain.trim().isEmpty()) {
					System.out.println(">>>>> Href contains old domain!");
				}
				if(aHref.trim().isEmpty()) {
					System.out.println(">>>>> Href is empty!");
				}
				i++;
			}
		}
	}
	
	public String getOldDomain(WebDriver driver) {
		String result = "";
		List<WebElement> elements = waitForVisibilityOfElements(driver, currentSiteLocator, 3);
		if(elements.size()>1) {
			result = elements.get(2).getAttribute("href");
			result = clearUrlFromHttp(driver, result);
			if (result.startsWith("www.")) {
		        result = result.substring(4);  // Remove "www."
		    }
		    if (result.endsWith("/")) {
		        result = result.substring(0, result.length() - 1);  // Remove the last character
		    }
		}
		
		return result;
	}
	
	public String invalidateCashWithWcasheUrl(WebDriver driver,String testSiteUrl) throws IOException, InterruptedException {
		String result = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int startIndex = testSiteUrl.indexOf("https://") + "https://spot-sample-".length();
        int endIndex = testSiteUrl.length();
		String testSiteDomain = testSiteUrl.substring(startIndex, endIndex);
		if (testSiteDomain.endsWith("/")) {
	        testSiteDomain = testSiteDomain.substring(0, testSiteDomain.length() - 1);
	    }
		goToWithResponseCode("https://wcache.spotapps.co/?domain="+testSiteDomain+"/&clear_cache=yes");
		Thread.sleep(2000);
		goToWithResponseCode(testSiteUrl);
		js.executeScript("location.reload(true);");
		Thread.sleep(1000);
	    String headContent = (String) js.executeScript("return document.head.innerHTML;");
	    Pattern pattern = Pattern.compile("<!-- wcache [\\d\\-:.Z ]+ -->");
	    Matcher matcher = pattern.matcher(headContent);
	    if (matcher.find()) {
	    	String invalidateCashtimeStamp = matcher.group().substring(12,31);
	        System.out.println("Cashe invalidated: " + invalidateCashtimeStamp);
	        result = "\nCashe invalidated: " + invalidateCashtimeStamp;
	    } else {
	        System.out.println("Wcashe comment not found");
	    }
	    return result;
	}
	
	public void clickInvalidateCashOkButton(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = invalidateCashLinkLocator;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		//try catch
		jsExecutor.executeScript("arguments[0].click();", element);
		System.out.println("Cash invalidated");
	}
	
	public void clickInvalidateCash(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = invalidateCashLinkLocator;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		//try catch
		jsExecutor.executeScript("arguments[0].click();", element);
	}
	
	public void clickSignInGoogle(WebDriver driver) {
		WebElement element = waitForVisibilityOfElement(driver, signInGoogleLocator, 15);
		element.click();
	}
	
	public void clickGoogleAccunt(WebDriver driver) {
		WebElement element = waitForVisibilityOfElement(driver, chooseAccountLocator, 15);
		element.click();
	}
	
	public void clickContinueButton(WebDriver driver) {
		WebElement element = waitForVisibilityOfElement(driver, continueButtonLocator, 15);
		element.click();
	}
	
	public void alreadyHaveAccountButton(WebDriver driver) {
		WebElement element = waitForVisibilityOfElement(driver, alreadyHaveAccountLocator, 15);
		element.click();
	}
	
	public String enterTestSiteNumber(WebDriver driver,String testSiteNumber) throws InterruptedException {
		String result = "";
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		List<WebElement> inputElements = testWebsiteNumberLocator;
		if(!inputElements.isEmpty()) {
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", inputElements.get(0));
			String elementText = inputElements.get(0).getAttribute("value");
			System.out.println("testSiteNumber in SH: " + elementText);
			if(elementText.trim().equals("")||elementText==null) {
				inputElements.get(0).sendKeys(testSiteNumber);
				System.out.println(testSiteNumber + " entered");
				result += "\n"+testSiteNumber + " entered";
			}else {
				System.out.println("testSiteNumber already exist");
			}
		}
		return result;
	}
	public String activateWcashe(WebDriver driver) throws InterruptedException {
		String result = "";
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		List<WebElement> elements = wcasheCheckboxLocator;
		if(!elements.isEmpty()) {
			Thread.sleep(2000);
			WebElement checkbox = elements.get(0);
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", checkbox);
			boolean isActive = elements.get(0).isSelected();
			if(!isActive) {
				jsExecutor.executeScript("arguments[0].click();", checkbox);
				result += "\nWcashe activated";
				System.out.println("Wcashe activated");
				
			}else {
				//result += "\nWcashe already activated";
				System.out.println("Wcashe already activated");
			}
		}
		return result;
	}
	
	public String activateWcasheTestLocation(WebDriver driver) throws InterruptedException {
		String result = "";
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		List<WebElement> elements = wcasheTestLocationCheckboxLocator;
		if(!elements.isEmpty()) {
			Thread.sleep(2000);
			WebElement checkbox = elements.get(0);
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", checkbox);
			boolean isActive = elements.get(0).isSelected();
			if(!isActive) {
				jsExecutor.executeScript("arguments[0].click();", checkbox);
				System.out.println("Wcashe Test Location activated");
				result += "\nWcashe Test Location activated";
			}else {
				System.out.println("Wcashe Test Location already activated");
			}
		}
		return result;
	}
	
	public void saveWebsiteSection(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement buttonElement = waitForVisibilityOfElement(driver, saveButtonWebsiteSectionLocator.get(0), 15);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", buttonElement);
		buttonElement.click();
	}
	
	
} // end class
	
	
