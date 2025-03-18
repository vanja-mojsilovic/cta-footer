package Spothopper.QA.PageObjects;

import Spothopper.QA.AbstractComponents.AbstractComponent;
import Spothopper.QA.PageObjects.*;
import net.bytebuddy.description.ModifierReviewable.OfAbstraction;
import software.amazon.awssdk.regions.servicemetadata.S3OutpostsServiceMetadata;

import org.bouncycastle.crypto.engines.ISAACEngine;
import org.jboss.aerogear.security.otp.Totp;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.IFactoryAnnotation;
import org.openqa.selenium.JavascriptExecutor;
import java.util.AbstractMap.SimpleEntry;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.xml.xpath.XPath;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GithubIssuePage extends AbstractComponent {
	
	WebDriver driver;
	
	// Constructors
	public GithubIssuePage(WebDriver driver) 
		{
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
						
		}
	
	// Locators
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
	
	@FindBy(xpath ="//input[@id = 'login_field' and @name='login']")
	WebElement githubIssueUserNameLocator;
	
	@FindBy(xpath ="//input[@id = 'password' and @name='password']")
	WebElement githubIssuePasswordLocator;
	
	@FindBy(xpath ="//input[@value = 'Sign in' and @name='commit' and @type='submit']")
	WebElement githubIssueSignInLocator;
	
	@FindBy(xpath ="//button[@class = 'js-octocaptcha-form-submit Button--primary Button--medium Button width-full' ]")
	WebElement githubIssueSendSmsLocator;
	
	@FindBy(xpath ="//input[@id = 'sms_totp' and @name='sms_otp']")
	WebElement githubIssueAuthenticationCodeLocator;
	
	@FindBy(xpath ="//input[@id = 'app_totp' and @name='app_otp']")
	WebElement githubIssueAuthenticatiorSixDigitsCodeLocator;
	
	@FindBy(xpath ="//button[@type = 'submit']")
	WebElement githubIssueVerifyButonLocator;
	
	@FindBy(xpath ="//div[@data-testid='markdown-body']//a")
	List <WebElement> gitCommentAElementLocator;
	
	@FindBy(xpath ="//div[@data-testid='markdown-body']//p")
	List <WebElement> gitCommentPElementLocator;
	
	
	// Methods
	public boolean checkLanding(WebDriver driver) {
		boolean result = false;
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		//String html = "";
		//html = (String) jsExecutor.executeScript("return document.documentElement.outerHTML;");
		//System.out.println(html.substring(0,100)+" . . . ");
		List <WebElement> elements = waitForVisibilityOfElements(driver, gitCommentPElementLocator, 5);
		String[] names = {"temporary landing","placeholder","place holder","coming soon","splach","-v0"};
		boolean containsKeyWords = false;
		for(WebElement element:elements) {
			result = Arrays.stream(names).map(String::toLowerCase).anyMatch(element.getText().toLowerCase()::contains);
			if(result) {
				return result;
			}
		}
		return result;
	}
	
	public boolean checkPlaceHolder(WebDriver driver) {
		boolean result = false;
		List <WebElement> elements = waitForVisibilityOfElements(driver, gitCommentAElementLocator, 15);
		if(!elements.isEmpty()) {
			for(WebElement element:elements) {
				if(element.getAttribute("href").contains("-website-v0")) {
					result = true;
				}
			}
		}
		return result;
	}
	
	
	public void githubVerificationWithAuth(WebDriver driver,String email) throws InterruptedException {
		WebElement emailElement = waitForVisibilityOfElement(driver, githubIssueUserNameLocator, 3);
		emailElement.clear();
		emailElement.sendKeys(email);
		
		String githubPassword = System.getenv("GITHUB_PASSWORD_VANJA");
		WebElement passwordElement = waitForVisibilityOfElement(driver, githubIssuePasswordLocator, 3);
		passwordElement.clear();
		passwordElement.sendKeys(githubPassword);
		WebElement signInElement = waitForVisibilityOfElement(driver, githubIssueSignInLocator, 3);
		signInElement.click();
		String githubSecretKey = System.getenv("GITHUB_SECRET_KEY_VANJA");
	    Totp githubTotp = new Totp(githubSecretKey);
	    String githubVerificationCode = githubTotp.now();
		WebElement authenticatorCodeElement = waitForVisibilityOfElement(driver, githubIssueAuthenticatiorSixDigitsCodeLocator, 3);
		authenticatorCodeElement.clear();
		//System.out.println(githubVerificationCode);
		authenticatorCodeElement.sendKeys(githubVerificationCode);
		//Thread.sleep(7000);
		//WebElement verifyElement = waitForVisibilityOfElement(driver, githubIssueVerifyButonLocator, 3);
		//verifyElement.click();
	}
	
	

	public List<String> extractFeatureLinks(String elementText, String featureName) {
	    String featurePrefix = featureName;
	    List <String> resultLinks=new ArrayList<>();
	    int startIndex = 0;
	    while (startIndex != -1) {
	        startIndex = elementText.indexOf(featurePrefix, startIndex);
	        if (startIndex != -1) {
	            startIndex += featurePrefix.length();
	            int endIndex = elementText.indexOf("\n", startIndex);
	            if (endIndex == -1) {
	                endIndex = elementText.indexOf(" ", startIndex);
	            }
	            if (endIndex == -1) {
	                endIndex = elementText.length();
	            }
	            String extractedText = elementText.substring(startIndex, endIndex).trim();
	            if (extractedText.startsWith("http")) {
	                resultLinks.add(extractedText);
	            }
	            startIndex = endIndex;  
	        }
	    }
	    return resultLinks;
	}
	
	public void githubVerification(WebDriver driver, String emailFromPopupOrJson) {
    	enterGithubUserName(driver, emailFromPopupOrJson);
    	enterGithubPassword(driver);
    	clickGithubSignIn(driver);
    	clickGithubSendSms(driver);
    	enterAuthencitationCode(driver);
	}
	
	public void enterAuthencitationCode(WebDriver driver) {
		String verificationCodeFromPopupOrJson = JOptionPane.showInputDialog(null,"Enter Github Authetication code:", 
        		"Verification code input",JOptionPane.PLAIN_MESSAGE);
		clearAndSendKeys(driver,githubIssueAuthenticationCodeLocator,"githubIssueAuthenticationCodeLocator",15,verificationCodeFromPopupOrJson);
	}

	public void enterGithubUserName(WebDriver driver,String userName) {
		clearAndSendKeys(driver,githubIssueUserNameLocator,"githubIssueUserNameLocator",15,userName);
	}
	
	public void enterGithubPassword(WebDriver driver) {
		JPasswordField passwordField = new JPasswordField();
		//int option = JOptionPane.showConfirmDialog(null, passwordField, "Enter Github password:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		//if (option == JOptionPane.OK_OPTION) {
			String googlePasswordFromPopupOrJson = System.getenv("GIT_PASSWORD");
			//String googlePasswordFromPopupOrJson = new String(passwordField.getPassword());
			clearAndSendKeys(driver,githubIssuePasswordLocator,"githubIssuePasswordLocator",15,googlePasswordFromPopupOrJson);
		//}
	}
	
	public void clickGithubSignIn(WebDriver driver) {
		clickElement(driver, githubIssueSignInLocator, "githubIssueSignInLocator", 15);
	}
	
	public void clickGithubSendSms(WebDriver driver) {
		clickElement(driver, githubIssueSendSmsLocator, "githubIssueSendSmsLocator", 15);
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
	
	
	

