package Spothopper.QA.TestCases;



import Spothopper.QA.PageObjects.*;
import Spothopper.QA.TestComponents.BaseTest;
import Spothopper.QA.TestComponents.Retry;
import net.bytebuddy.description.ModifierReviewable.OfAbstraction;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.module.Input;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;





public class VariablesAndUrlsTest extends BaseTest {
	
	public VariablesAndUrlsTest(WebDriver driver) 
	{
		this.driver = driver;			
	}
	
	// Variables
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
	
	// URLs
	public String ctaLinksUrlTest = domainURL + spotID + "/cta_links?login_token=" + loginToken;
	public String privatePartiesSettingsURL = domainURL + spotID + "/private_parties_inquiries/settings?login_token=" + loginToken;
	public String eventTicketsURL = domainURL + spotID + "/tickets?login_token=" + loginToken;
	public String hoursURL = domainURL + spotID + "/hours?login_token=" + loginToken;
	public String ordersSettingsURL = domainURL + spotID + "/order_inquiries/settings?login_token=" + loginToken;
	public String menusLandingURL = domainURL + spotID + "/food_menus_landing?login_token=" + loginToken;
	public String menusLandingURL1 = domainURL + spotID + "/food_menus_landing";
	public String addNewFoodMenusURL = domainURL + spotID + "/food_menus#/new?menu_type=food&login_token=" + loginToken;
	public String orderingMenuPopupTMT = tmtDomainURL + "ordering-menu" + "/?spot_id=" + spotID
			+ "&accordion=true&images=yes&login_token=" + loginToken;
	public String deleteMenuURL = domainURL + spotID + "/food_menus#/" + menuNumber + "?login_token=" + loginToken;
	public String orderDetailsURL = domainURL + spotID + "/inquiry_details/" + orderNumber + "?login_token="
			+ loginToken;
	public String specialsUrl = domainURL + spotID + "/specials?login_token=" + loginToken;
	public String eventsUrl = domainURL + spotID + "/events?login_token=" + loginToken;
	public String businessInfoUrl = domainURL + spotID + "/business_info?login_token=" + loginToken;
	public String loginPageUrl = domainURL + spotID + "/business_info";
	
	// Methods
	public int setDataProviderCounter(String num) {
		dataProviderCounter = Integer.parseInt(num);
		System.out.println("dataProviderCounter is: " + dataProviderCounter);
		return dataProviderCounter;
	}

	public int getCurrentCounter() {
		return currentCounter;
	}

	public int increaseCurrentCounter() {
		currentCounter++;
		return currentCounter;
	}
	
	public void setSpotIdAndLoginToken(WebDriver driver,String spotIdFromPopupOrJson,String loginTokenFromPopupOrJson) {
		spotID = spotIdFromPopupOrJson;
		loginToken = loginTokenFromPopupOrJson;
		ctaLinksUrlTest = domainURL + spotIdFromPopupOrJson + "/cta_links?login_token=" + loginToken;
		privatePartiesSettingsURL = domainURL + spotIdFromPopupOrJson + "/private_parties_inquiries/settings?login_token=" + loginTokenFromPopupOrJson;
		eventTicketsURL = domainURL + spotIdFromPopupOrJson + "/tickets?login_token=" + loginTokenFromPopupOrJson;
		hoursURL = domainURL + spotIdFromPopupOrJson + "/hours?login_token=" + loginTokenFromPopupOrJson;
		ordersSettingsURL = domainURL + spotIdFromPopupOrJson + "/order_inquiries/settings?login_token=" + loginTokenFromPopupOrJson;
		menusLandingURL = domainURL + spotIdFromPopupOrJson + "/food_menus_landing?login_token=" + loginTokenFromPopupOrJson;
		menusLandingURL1 = domainURL + spotID + "/food_menus_landing";
		addNewFoodMenusURL = domainURL + spotIdFromPopupOrJson + "/food_menus#/new?menu_type=food&login_token=" + loginTokenFromPopupOrJson;
		orderingMenuPopupTMT = tmtDomainURL + "ordering-menu" + "/?spot_id=" + spotIdFromPopupOrJson
				+ "&accordion=true&images=yes&login_token=" + loginTokenFromPopupOrJson;
		deleteMenuURL = domainURL + spotIdFromPopupOrJson + "/food_menus#/" + menuNumber + "?login_token=" + loginTokenFromPopupOrJson;
		orderDetailsURL = domainURL + spotIdFromPopupOrJson + "/inquiry_details/" + orderNumber + "?login_token="
				+ loginTokenFromPopupOrJson;
		specialsUrl = domainURL + spotID + "/specials?login_token=" + loginToken;
		eventsUrl = domainURL + spotID + "/events?login_token=" + loginToken;
		businessInfoUrl = domainURL + spotID + "/business_info?login_token=" + loginToken;
		loginPageUrl = domainURL + spotID + "/business_info";
	}
	
	public void setWebsiteUrl(WebDriver driver) {
		String websiteUrlFromPopup = JOptionPane.showInputDialog(null,"Enter website URL:", "Website URL input",JOptionPane.PLAIN_MESSAGE);
		websiteURL = websiteUrlFromPopup;
	}
	
	public void setTwitterUserName(WebDriver driver,
		String insertedTwitterUserNameFile) {
		twitterUserNameFile = insertedTwitterUserNameFile;
		
	}
	
	public void setInstagramUserName(WebDriver driver,
		String insertedTwitterUserNameFile) {
		instagramUserNameFile = insertedTwitterUserNameFile;
	}
	
	
	
    
	
}//close class
