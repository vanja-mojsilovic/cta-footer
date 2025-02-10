package Spothopper.QA.TestComponents;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;	



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.PublicVerificationKey;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v128.emulation.Emulation;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.AbstractMap.SimpleEntry;

import javax.swing.JOptionPane;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import org.openqa.selenium.chrome.ChromeOptions;


public class BaseTest {
	
	
	public static WebDriver driver;
	
	
	//Variables
	
	public String domainURL = "https://www.spothopperapp.com/admin/spots/";
	public String tmtDomainURL = "https://tmt-front-staging.spotapps.co/";
	public String production_staging = "production";
	public String currentTimeString = "1978 11 15";
	public String email = "automation.qa@spothopperapp.com";
	public String customersFirstName = "Vanja";
	public String customersLastName = "Mojsilovic";
	public String customersPhoneNumber = "0141112222";
	public String fullStreetAddress = "3800 East Layton Avenue, Cudahy, Wisconsin 53110, United States";
	public String latitude = "42.95939860";
	public String longitude = "-87.85486040";
	public String local_github ="local";
	public String screenshot_files_path ="C:\\Users\\Public\\Pictures\\Screenshots\\"; 
	protected ExtentReports extent;
	
	
	// Methods
	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/Spothopper/QA/Resources/GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			//WebDriverManager.chromedriver().browserVersion("129.0.6668.100").setup();
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			 
			//options.addArguments("--headless");
			options.addArguments("--ignore-certificate-errors");
			driver = new ChromeDriver(options);
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			// Firefox
		}
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		return driver;
	}
	
	public WebDriver switchToIphoneSEView() throws IOException {
		
		if (driver == null) {
		    initializeDriver();
		}
		DevTools devTools = ((ChromeDriver) driver).getDevTools();
		devTools.createSession();
		devTools.send(Emulation.setDeviceMetricsOverride(
		375, // width
		667, // height
		100, // device scale factor
		true, // mobile
		Optional.empty(), // scale
		Optional.empty(), // screen width
		Optional.empty(), // screen height
		Optional.empty(), // position X
		Optional.empty(), // position Y
		Optional.empty(), // screen orientation
		Optional.empty(), // viewport
		Optional.empty(), // display feature
		Optional.empty(), 
		Optional.empty()
		));
		driver.manage().window().maximize();
		return driver;
	}
	
	public void setIphoneView(WebDriver driver) {
	    ((ChromeDriver) driver).executeCdpCommand("Emulation.setDeviceMetricsOverride", 
	        Map.of("width", 375, "height", 667, "deviceScaleFactor", 2, "mobile", true, "fitWindow", false));
	}
	
	public void setDesktopView(WebDriver driver) {
	    ((ChromeDriver) driver).executeCdpCommand("Emulation.setDeviceMetricsOverride", 
	        Map.of("width", 1920, "height", 1080, "deviceScaleFactor", 1, "mobile", false, "fitWindow", false));
	}
	
    public WebDriver switchToDesktopView() {
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;  
    }
	
	public String getHoursURL(String spotID, String loginToken) {
		String result = domainURL + spotID + "/hours?login_token=" + loginToken;
		return result;
	}
	
	
	

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	public String getStringLocalDateTime() {
		LocalDateTime curentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
		String formattedDateTime = curentTime.format(formatter);
		return formattedDateTime;
	}

	public String getDayOfTheWeek() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE");
		String day = now.format(formatter);
		return day;
	}
	
	public static void scrollDown(WebDriver driver) {
        Actions actions = new Actions(driver);
        // Scroll down by performing a key press of the DOWN arrow key
        actions.sendKeys(Keys.ARROW_DOWN).build().perform();
    }
	
	public class JsonUtils {
	    public HashMap<String, String> readJsonFile(String filePath) throws IOException {
	        ObjectMapper objectMapper = new ObjectMapper();
	        return objectMapper.readValue(new File(filePath), HashMap.class);
	    }
	}
	
	

	
	@BeforeSuite
	public void bSuite() throws IOException {
		 //String beforeSuite = "beforeSuite test";
	}

	
	
	@BeforeMethod(alwaysRun = true)
	public void launchApplication() throws IOException {
		
        driver = initializeDriver();
		
	}

	

	@AfterMethod(alwaysRun = true)
	public void closeWindow() {
		//driver.close();
	}
	
	
    
}
