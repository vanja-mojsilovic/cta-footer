package Spothopper.QA.PageObjects;
import java.util.List;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.openqa.selenium.support.PageFactory;

public class AiIntegrationPage {
	public static String HUGGING_FACE_API_TOKEN = "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";
	private WebDriver driver;

	// Locators
	@FindBy(xpath ="//div[@class = 'mw-content-ltr mw-parser-output']/p[3]")
	List <WebElement> paragraphElementsLocator;
	
	
    // Constructor
    public AiIntegrationPage(WebDriver driver) {
    	 this.driver = driver;
    	 PageFactory.initElements(driver, this); 
    }
    
    

    
    public void navigateTo(String url) {
        driver.get(url);
    }

    
    public String getElementText(By locator) {
        WebElement element = driver.findElement(locator);
        return element.getText();
    }

    
    public void close() {
        driver.quit();
    }
    
 
    public static String analyzeTextSentiment(String inputText,String HUGGING_FACE_API_URL) throws Exception {
        OkHttpClient client = new OkHttpClient();
        JSONObject json = new JSONObject();
        json.put("inputs", inputText);
        Request request = new Request.Builder()
                .url(HUGGING_FACE_API_URL)
                .addHeader("Authorization", "Bearer " + HUGGING_FACE_API_TOKEN)
                .post(RequestBody.create(json.toString(), MediaType.parse("application/json")))
                .build();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        System.out.println("Response Code: " + response.code());
        System.out.println("Response Body: " + responseBody);
        if (response.isSuccessful() && response.body() != null) {
            
            JSONObject jsonResponse = new JSONObject(responseBody);
            return jsonResponse.getJSONArray("labels").getString(0);
        } else {
            throw new Exception("Failed to analyze sentiment: " + response.code());
        }
    }
    
    public String getFirstParagraphText(WebDriver driver) {
        if (paragraphElementsLocator != null && !paragraphElementsLocator.isEmpty()) {
            return paragraphElementsLocator.get(0).getText();
        } else {
            throw new IllegalStateException("No elements found for paragraph locator.");
        }
    }
    
    
    
}
