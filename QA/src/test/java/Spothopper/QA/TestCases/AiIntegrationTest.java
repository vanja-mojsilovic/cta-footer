package Spothopper.QA.TestCases;
import okhttp3.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.AbstractDocument.Content;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import Spothopper.QA.PageObjects.AiIntegrationPage;
import Spothopper.QA.TestComponents.BaseTest;
import io.restassured.http.ContentType;




public class AiIntegrationTest extends  BaseTest{
	
	

    @Test
    public void mainMethod() throws IOException, InterruptedException{
    	try {
          
            String apiUrl = "https://api-inference.huggingface.co/models/gpt2";
            		
            String apiKey = "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"; 

            // Input text for text generation
            String inputJson = "{\"inputs\":\"Hello, world! Let's explore\",\"parameters\":{\"max_length\":50}}";


            // Setup connection
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Send request
            OutputStream os = connection.getOutputStream();
            os.write(inputJson.getBytes(StandardCharsets.UTF_8));
            os.close();

            // Read response
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                System.out.println(new String(connection.getInputStream().readAllBytes()));
            } else {
                System.out.println("Error: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
