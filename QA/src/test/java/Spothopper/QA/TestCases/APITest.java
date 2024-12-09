package Spothopper.QA.TestCases;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Spothopper.QA.PageObjects.VariablesAndUrlsPage;
import Spothopper.QA.TestComponents.BaseTest;

public class APITest extends BaseTest{
	 @BeforeClass
	    public void setup() throws InterruptedException {
		 	System.out.println("*******setup started!********");
	        RestAssured.baseURI = ("https://reqres.in"); // Rest Api Test Site
	    }
	 
	 @Test
	 public void testGetEndpoint() throws InterruptedException {
		 System.out.println("*******testGetEndpoint started!********");
		 Response response = RestAssured.get("/api/users?page=2");
		 System.out.println("Response code "+response.getStatusLine());
         System.out.println("Response Body: " + response.getBody().asString()); 
         System.out.println("Response Cookies: " + response.getCookies());
	 }
	 
	 @Test
	    public void testPostEndpoint() {
	        System.out.println("*******testPostEndpoint started!********");
	        Map<String, String> requestBody = new HashMap<>();
	        requestBody.put("name", "John Doe");
	        requestBody.put("job", "Software Developer");
	        Response response = RestAssured
	                .given()
	                .contentType(ContentType.JSON)  
	                .body(requestBody)              
	                .post("/api/users");            
	        System.out.println("Response code "+response.getStatusLine());
	        System.out.println("Response Body: " + response.getBody().asString());
	        
	    }
	 
	 @BeforeMethod
	    public void endMethod() throws InterruptedException {
		 	System.out.println("*******end method!********");
		 	driver.close();
	    }
}
