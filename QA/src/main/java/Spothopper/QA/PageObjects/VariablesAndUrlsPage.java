package Spothopper.QA.PageObjects;

import static org.testng.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import Spothopper.QA.AbstractComponents.AbstractComponent;
import Spothopper.QA.Resources.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.thirdparty.jackson.core.type.TypeReference;
import org.openqa.selenium.Cookie;
import javax.swing.*;
import org.jboss.aerogear.security.otp.Totp;
import javax.swing.JPasswordField;


public class VariablesAndUrlsPage extends AbstractComponent {
	
	WebDriver driver;
	
	
	public VariablesAndUrlsPage(WebDriver driver) 
		{
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
						
		}
	//variables
	public String publishInfoUrl = "https://publish-info.spotapps.co/dashboard/credentials/";
	public String myEmail = "vanja.mojsilovic@spothopperapp.com";
	public String domainURL = "https://www.spothopperapp.com/admin/spots/";
	public String tmtDomainURL = "https://tmt-front-staging.spotapps.co/";
	public String production_staging = "production";
	public String email = "automation.qa@spothopperapp.com";
	public String spotID="321387";
	public String loginToken;
	public String websiteURL;
	public String personalName;
	public int dataProviderCounter;
	public int currentCounter = 0;
	public String menuName = "Dinner";
	public String menuItemName_1 = "Chicken Soup";
	public String menuItemPrice_1 = "35.00";
	public String menuItemName_2 = "Beef Soup";
	public String menuItemPrice_2 = "38.00";
	public String menuNumber = "0";
	public String creditCardNumber = "4242424242424242";
	public String expiryDate = "04/24";
	public String cvcNumber = "242";
	public String postalCode = "42424";
	public String orderNumber = "0";
	public List<String> parent_child_add_menus = new ArrayList<>();
	public String twitterUserNameFile ;
	public String instagramUserNameFile;
	public String listOfQaMembersNames = "6201161deaf9e20070737924, "
			+ "624ab599fd5e45007046851a, "
			+ "63bbd7b824db796721235e13,"
			+ "63106e0e55b0a9e29f1ae60d, "
			+ "6405a88c2847866310ffdcb1, "
			+ "642ac9ce551f476a04685954, "
			+ "638478162acfad92d7b2a41c, "
			+ "712020:2ec53619-4525-4e3f-bbea-f57f209074ef,"
			+ "712020:94bcabc8-7a59-4228-b064-20fff37454d0, "
			+ "712020:6cffa8dc-7b35-4c76-a7af-1b9816fd9dbc, "
			+ "712020:f77ed01f-96d6-492b-a3a8-8bc5af83ea77, "
			+ "6201161cf5d29a0068fa75b3,"
			+ "712020:cd832742-7b26-410d-8e24-63fb09a948e4, "
			+ "642ac9ce9f314a0a30144195, "
			+ "633aac93748d1bfcb85b0f7a, "
			+ "633ff94e8b75455be459503a, "
			+ "6368fdd911c69c741844dccb,"
			+ "712020:43b4ceca-2c92-4efa-b804-87c11ad183dc, "
			+ "712020:cd92f95f-f13e-4334-a6e6-99e1385bbae7, "
			+ "712020:024ca126-b2f8-4878-a896-c83c4aeeeb39, "
			+ "712020:ff3bf219-07e4-48f2-bce7-90c84915e2fc,"
			+ "712020:d5d4d64f-73ab-4947-9664-3face76684af, "
			+ "712020:8ee9b3a3-39c6-4c00-bc98-ba7a481838a1,"
			+ "712020:28f2889d-6895-472a-a1eb-cf5a61c975eb, "
			+ "712020:6a18abe4-4aaa-4125-9a51-768090e8796e,"
			+ "712020:f8d0a823-a2b3-468e-bb8d-f3008a564be7";
	

	
	//urls
	public String ctaLinksUrl;
	public String privatePartiesSettingsURL; //= domainURL + spotID + "/private_parties_inquiries/settings?login_token=" + loginToken;
	public String cateringSettingsURL; //= domainURL + spotID + "/catering_inquiries/settings?login_token=" + loginToken;
	public String reservationsSettingsURL;
	public String eventTicketsURL;// = domainURL + spotID + "/tickets?login_token=" + loginToken;
	public String hoursURL;// = domainURL + spotID + "/hours?login_token=" + loginToken;
	public String ordersSettingsURL;// = domainURL + spotID + "/order_inquiries/settings?login_token=" + loginToken;
	public String jobApplicationsSettingsURL;
	public String menusLandingURL;// = domainURL + spotID + "/food_menus_landing?login_token=" + loginToken;
	public String menusLandingURL1;// = domainURL + spotID + "/food_menus_landing";
	public String addNewFoodMenusURL;// = domainURL + spotID + "/food_menus#/new?menu_type=food&login_token=" + loginToken;
	public String orderingMenuPopupTMT;// = tmtDomainURL + "ordering-menu" + "/?spot_id=" + spotID+ "&accordion=true&images=yes&login_token=" + loginToken;
	public String deleteMenuURL;// = domainURL + spotID + "/food_menus#/" + menuNumber + "?login_token=" + loginToken;
	public String orderDetailsURL;// = domainURL + spotID + "/inquiry_details/" + orderNumber + "?login_token="+ loginToken;
	public String specialsUrl;// = domainURL + spotID + "/specials?login_token=" + loginToken;
	public String eventsUrl;// = domainURL + spotID + "/events?login_token=" + loginToken;
	public String businessInfoUrl;// = domainURL + spotID + "/business_info?login_token=" + loginToken;
	public String websiteAdminPanelURL;
	public String footerLinksURL;
	public String githubIssueUrl = "https://github.com/SpotHopperLLC/content/issues/";
	
	
	
	//page factory locators
	@FindBy(xpath ="//input[@name='username']")
	List <WebElement> userNameLocator;
	
	@FindBy(xpath ="//input[@name='password']")
	List <WebElement> passwordLocator;
	
	@FindBy(xpath ="//input[@name='signInSubmitButton']")
	List <WebElement> signInButtonLocator;
	
	@FindBy(xpath ="//a[contains(@href, 'https://accounts.google.com/ServiceLogin')]")
	WebElement googleLoginLocator;
	
	@FindBy(xpath ="//input[@id='identifierId']")
	WebElement googleUserNameLocator;
	
	@FindBy(xpath ="//div[@id='identifierNext']/div/button")
	WebElement googleNextButtonLocator;
	
	@FindBy(xpath ="//input[@name='Passwd']")
	WebElement googlePasswordLocator;
	
	@FindBy(xpath ="//div[@id='passwordNext']/div/button")
	WebElement googlePasswordNextButtonLocator;
	
	@FindBy(xpath ="//input[@id='phoneNumberId']")
	WebElement googlePhoneNumberLocator;
	
	@FindBy(xpath ="//div[@data-send-method='SMS']/div[3]/div/div/div/div/button")
	WebElement googlePhoneNextButtonLocator;
	
	@FindBy(xpath ="//input[@id='idvPin']")
	WebElement googleVerificationCodeLocator;
	
	@FindBy(xpath ="//input[@id='totpPin']")
	WebElement googleAuthenticatorCodeLocator;
	
	@FindBy(xpath ="//div[@id='idvPreregisteredPhoneNext']/div/button")
	WebElement googleVerificationCodeNextButtonLocator;
	
	@FindBy(xpath ="//div[@id='totpNext']/div/button")
	WebElement googleAuthenticatorCodeNextButtonLocator;
	
	@FindBy(xpath ="//button[@name='googleSignUpButton']")
	List <WebElement> googleContinueWithGoogleLocator;
	
	@FindBy(xpath ="//div[@data-email='automation.qa@spothopperapp.com']")
	List <WebElement> googleAccountLocator;
	
	@FindBy(xpath ="//button//span[contains(text(),'Continue')]")
	WebElement googleAccountContinueLocator;
	
	@FindBy(xpath ="//button[contains(@class,'google-login-button')]")
	WebElement publisInfoLoginLocator;
	
	

	//methods
	
	
	
	public void publishInfoLoginGoogle(WebDriver driver) throws InterruptedException, IOException {
		goToWithResponseCode(publishInfoUrl);
		WebElement element = waitForVisibilityOfElement(driver, publisInfoLoginLocator, 15);
		element.click();
		Thread.sleep(1000);
	}
	
	
	public void clickContinueWithGoogleButton(WebDriver driver){
		clickElement(driver, googleContinueWithGoogleLocator.get(1), "googleContinueWithGoogleLocator", 15);
		List<WebElement> elements = waitForVisibilityOfElements(driver, googleAccountLocator, 15);
		if(!elements.isEmpty()) {
			clickElement(driver, elements.get(0), "googleAccountLocator", 15);
			clickElement(driver, googleAccountContinueLocator, "googleAccountContinueLocator", 15);
			clickElement(driver, googleContinueWithGoogleLocator.get(1), "googleContinueWithGoogleLocator", 15);
		}
	}
	
	public void googleVerification(WebDriver driver, String emailFromPopupOrJson) throws InterruptedException {
		
		goTo("https://www.google.com");
        clickGoogleLogin(driver);
        enterGoogleUserName(driver, emailFromPopupOrJson);
        clickGoogleNextButton(driver);
        //String googlePasswordFromPopupOrJson = JOptionPane.showInputDialog(null,
        //		"Enter Google password: ", "Google password: ",JOptionPane.PLAIN_MESSAGE);
        //int option = JOptionPane.showConfirmDialog(null, passwordField, "Enter Google password:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        //if (option == JOptionPane.OK_OPTION) {
            //String googlePasswordFromPopupOrJson = new String(passwordField.getPassword());
        	String googlePasswordFromPopupOrJson = System.getenv("GOOGLE_PASSWORD_VANJA");
	        enterGooglePassword(driver, googlePasswordFromPopupOrJson);
	        clickGooglePasswordNextButton(driver);
	        JPasswordField passwordField = new JPasswordField();
			String googleSecretKey = System.getenv("GOOGLE_SECRET_KEY_VANJA");
		    Totp totp = new Totp(googleSecretKey);
		    String verificationCodeFromPopupOrJson = totp.now();
		    
		    
	        //String verificationCodeFromPopupOrJson = JOptionPane.showInputDialog(null,"Enter verification code:", 
	        // 	"Verification code input",JOptionPane.PLAIN_MESSAGE);
		    Thread.sleep(2000);
	        enterGoogleAuthenticatiorCode(driver, verificationCodeFromPopupOrJson);
	        //enterGoogleAuthenticatiorCode(driver, verificationCode);
	        clickGoogleAuthenticatorCodeNextButton(driver);
        //}
	}
	
	
	public void setUrls(WebDriver driver,String spotIdFromPopupOrJson) {
		spotID = spotIdFromPopupOrJson;
		ctaLinksUrl = domainURL + spotIdFromPopupOrJson + "/cta_links";
		privatePartiesSettingsURL = domainURL + spotIdFromPopupOrJson + "/private_parties_inquiries/settings";
		cateringSettingsURL = domainURL + spotIdFromPopupOrJson + "/catering_inquiries/settings";
		reservationsSettingsURL = domainURL + spotIdFromPopupOrJson + "/reservation_inquiries/settings";
		ordersSettingsURL = domainURL + spotIdFromPopupOrJson + "/order_inquiries/settings";
		jobApplicationsSettingsURL = domainURL + spotIdFromPopupOrJson + "/job_listings_inquiries/settings";
		eventTicketsURL = domainURL + spotIdFromPopupOrJson + "/tickets";
		hoursURL = domainURL + spotIdFromPopupOrJson + "/hours";
		
		menusLandingURL = domainURL + spotIdFromPopupOrJson + "/food_menus_landing";
		addNewFoodMenusURL = domainURL + spotIdFromPopupOrJson + "/food_menus#/new?menu_type=food";
		orderingMenuPopupTMT = tmtDomainURL + "ordering-menu" + "/?spot_id=" + spotIdFromPopupOrJson
				+ "&accordion=true&images=yes";
		deleteMenuURL = domainURL + spotIdFromPopupOrJson + "/food_menus#/" + menuNumber;
		orderDetailsURL = domainURL + spotIdFromPopupOrJson + "/inquiry_details/" + orderNumber;
		specialsUrl = domainURL + spotID + "/specials";
		eventsUrl = domainURL + spotID + "/events";
		businessInfoUrl = domainURL + spotID + "/business_info";
		websiteAdminPanelURL = domainURL + spotID + "/website";
		footerLinksURL = domainURL + spotID + "/website_footer_links";
		githubIssueUrl = "https://github.com/SpotHopperLLC/content/issues/";
	}
	
	public void spothopperAppSignIn(WebDriver driver) throws InterruptedException, IOException {
		goToWithResponseCode("https://www.spothopperapp.com/admin/");
		Thread.sleep(1000);
    	clickContinueWithGoogleButton(driver);
 	    Thread.sleep(2000);
	}
	
	public void setTwitterUserName(WebDriver driver,
		String insertedTwitterUserNameFile) {
		twitterUserNameFile = insertedTwitterUserNameFile;
		
	}
	
	public void setInstagramUserName(WebDriver driver,
		String insertedTwitterUserNameFile) {
		instagramUserNameFile = insertedTwitterUserNameFile;
	}
	
	public void enterUserName(WebDriver driver, String userName){
		clearAndSendKeys(
				driver, 
				userNameLocator.get(1), "userNameLocator", 15, userName);
	}
	
	public void enterPassword(WebDriver driver, String enterdPassword){
		clearAndSendKeys(
				driver, 
				passwordLocator.get(1), "passwordLocator", 15, enterdPassword);
	}
	
	public void clickSignIn(WebDriver driver){
		clickElement(driver, signInButtonLocator.get(1), "signInButtonLocator", 15);
	}
	
	public void logIn(WebDriver driver,String insertedUsername, String insertedPassword) {
		enterUserName(driver,insertedUsername);
		enterPassword(driver,insertedPassword);
		clickSignIn(driver);
	}
	
	public void clickGoogleLogin(WebDriver driver) {
		clickElement(driver, googleLoginLocator, "googleLoginLocator", 15);
	}
	
	public void enterGoogleUserName(WebDriver driver, String userName){
		clearAndSendKeys(
				driver, 
				googleUserNameLocator, "googleUserNameLocator", 15, userName);
	}
	
	public void clickGoogleNextButton(WebDriver driver){
		clickElement(driver, googleNextButtonLocator, "googleNextButtonLocator", 15);
	}
	
	public void enterGooglePassword(WebDriver driver, String password){
		clearAndSendKeys(
				driver, 
				googlePasswordLocator, "googlePasswordLocator", 15, password);
	}
	
	public void clickGooglePasswordNextButton(WebDriver driver){
		clickElement(driver, googlePasswordNextButtonLocator, "googlePasswordNextButtonLocator", 15);
	}
	
	public void enterGooglePhoneNumber(WebDriver driver, String password){
		clearAndSendKeys(
				driver, 
				googlePhoneNumberLocator, "googlePhoneNumberLocator", 15, password);
	}
	
	public void clickGooglePhoneNextButton(WebDriver driver){
		clickElement(driver, googlePhoneNextButtonLocator, "googlePhoneNextButtonLocator", 15);
	}
	
	public void enterGoogleVerificationCode(WebDriver driver, String password){
		clearAndSendKeys(
				driver, 
				googleVerificationCodeLocator, "googleVerificationCodeLocator", 15, password);
	}
	
	public void enterGoogleAuthenticatiorCode(WebDriver driver, String password){
		clearAndSendKeys(
				driver, 
				googleAuthenticatorCodeLocator, "googleAuthenticatorCodeLocator", 15, password);
	}
	
	public void clickGoogleVerificationCodeNextButton(WebDriver driver){
		clickElement(driver, googleVerificationCodeNextButtonLocator, "googleVerificationCodeNextButtonLocator", 15);
	}
	
	public void clickGoogleAuthenticatorCodeNextButton(WebDriver driver){
		clickElement(driver, googleAuthenticatorCodeNextButtonLocator, "googleAuthenticatorCodeNextButtonLocator", 15);
	}
	
	
	
	
	
	
	
	public List<String> getSecret() {
	    String secretName = "vanja-java";
	    Region region = Region.of("us-east-1");
	    SecretsManagerClient client = SecretsManagerClient.builder()
	            .region(region)
	            .build();
	    GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
	            .secretId(secretName)
	            .build();
	    GetSecretValueResponse getSecretValueResponse;

	    try {
	        getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	    if (getSecretValueResponse != null && getSecretValueResponse.secretString() != null) {
	        String secret = getSecretValueResponse.secretString();
	        ObjectMapper objectMapper = new ObjectMapper();
	        try {
	        	SecretData secretData = objectMapper.readValue(secret, SecretData.class);
                System.out.println("Username: " + secretData.getShUsername());
                System.out.println("Password: " + secretData.getShPassword());
                List<String> credentials = new ArrayList<>();
                credentials.add(secretData.getShUsername());
                credentials.add(secretData.getShPassword());
                return credentials;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            System.out.println("No secret value found for: " + secretName);
            return null;
        }
	}   
	
	public void saveCookiesToFile() {
        File file = new File("data/cookies.data");
        try (FileWriter fileWriter = new FileWriter(file);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            for (Cookie cookie : driver.manage().getCookies()) {
                printWriter.println(cookie.getName() + ";" + cookie.getValue() + ";" + cookie.getDomain() + ";" +
                        cookie.getPath() + ";" + cookie.getExpiry() + ";" + cookie.isSecure());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void loadCookiesFromFile() {
	    File file = new File("cookies.data");
	    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] cookieData = line.split(";");
	            Cookie cookie = new Cookie(cookieData[0], cookieData[1], cookieData[2], cookieData[3], null, Boolean.parseBoolean(cookieData[5]));
	            driver.manage().addCookie(cookie);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	
	

   

	
} // end class
	
	
	

