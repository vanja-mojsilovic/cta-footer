package Spothopper.QA.PageObjects;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.Cookie;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Spothopper.QA.AbstractComponents.AbstractComponent;

public class MetroAreasPage extends AbstractComponent{
	
	// Variables
	WebDriver driver;
	
	// Constructor
	public MetroAreasPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);			
	}
	
	@FindBy(xpath ="//input[@ng-model = 'spot.latitude']")
	WebElement latitudeLocator;
	
	@FindBy(xpath ="//button[@ng-disabled = 'invalid()']")
	List<WebElement> saveButtonLocator;
	
	@FindBy(xpath ="//button[@data-testid = 'login-button']")
	WebElement chatGptLoginLocator;
	
	@FindBy(xpath ="//button[@class = 'social-btn']")
	WebElement chatGptLoginWithGoogleLocator;
	
	
	// Methods
	
	public void clickLoginChatGpt(WebDriver driver) {
		WebElement element = chatGptLoginLocator;
		element.click();
	}
	
	public void enterZero(WebDriver driver) {
		WebElement element = latitudeLocator;
		element.sendKeys("0");
	}
	
	public void saveChanges(WebDriver driver) {
		List<WebElement> elements = saveButtonLocator;
		elements.get(0).click();
	}
	
	public void loginWithGoogleChatGpt(WebDriver driver) {
		WebElement element = chatGptLoginWithGoogleLocator;
		element.click();
	}
	
	public void skipCaptcha(WebDriver driver) throws IOException {
	    String currentDomain = driver.getCurrentUrl().split("/")[2]; // Extract domain from current URL
	    String cookieJson = new String(Files.readAllBytes(Paths.get("data/cookies.json")));
	    JSONArray cookies = new JSONArray(cookieJson);

	    for (Object obj : cookies) {
	        JSONObject cookie = (JSONObject) obj;

	        // Adjust the domain if necessary
	        String cookieDomain = cookie.optString("domain", currentDomain);
	        if (!cookieDomain.equals(currentDomain)) {
	            cookieDomain = currentDomain; // Ensure domain matches
	        }

	        try {
	            // Build and add the cookie
	            Cookie seleniumCookie = new Cookie.Builder(cookie.getString("name"), cookie.getString("value"))
	                    .domain(cookieDomain)
	                    .path(cookie.optString("path", "/"))
	                    .isSecure(cookie.optBoolean("secure", false))
	                    .isHttpOnly(cookie.optBoolean("httpOnly", false))
	                    .build();
	            driver.manage().addCookie(seleniumCookie);
	        } catch (Exception e) {
	            System.err.println("Error adding cookie: " + cookie.getString("name") + " - " + e.getMessage());
	        }
	    }

	    driver.navigate().refresh();
	    System.out.println("Cookies added and page refreshed!");
	}

	
	public void saveCookies(WebDriver driver, String filePath) throws IOException {
	    JSONArray cookiesArray = new JSONArray();
	    for (Cookie cookie : driver.manage().getCookies()) {
	        JSONObject jsonCookie = new JSONObject();
	        jsonCookie.put("name", cookie.getName());
	        jsonCookie.put("value", cookie.getValue());
	        jsonCookie.put("domain", cookie.getDomain());
	        jsonCookie.put("path", cookie.getPath());
	        jsonCookie.put("secure", cookie.isSecure());
	        jsonCookie.put("httpOnly", cookie.isHttpOnly());
	        cookiesArray.put(jsonCookie);
	    }
	    Files.write(Paths.get(filePath), cookiesArray.toString(4).getBytes());
	    System.out.println("Cookies saved to " + filePath);
	}

}
