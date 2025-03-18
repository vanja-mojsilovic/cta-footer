package Spothopper.QA.PageObjects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import Spothopper.QA.AbstractComponents.AbstractComponent;

public class JiraCommentsPage extends AbstractComponent {
	
	WebDriver driver;
	
	
	public JiraCommentsPage(WebDriver driver) 
		{
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);			
		}
	
	//page factory locators
	
	@FindBy(xpath = "//button[contains(@id,'google-auth-button')]")
	WebElement signInGoogleLocator;
	
	@FindBy(xpath ="//div[@data-email='vanja.mojsilovic@spothopperapp.com']")
	WebElement chooseAccountLocator;

	@FindBy(xpath = "//button/span[contains(text(),'Continue')]")
	WebElement continueButtonLocator;
	
	@FindBy(xpath = "//a[contains(@id,'already-have-an-account')]")
	WebElement alreadyHaveAccountLocator;
	
	@FindBy(xpath = "//a[contains(@href,'spot-sample-')]")
	List<WebElement> testSiteUrlLocator;
	
	@FindBy(xpath = "//div[contains(@id,'jql-editor-input')]/p")
	WebElement searchJqlLocator;
	
	@FindBy(xpath = "//button[contains(@data-testid,'jql-editor-search')]")
	WebElement searchJqlButtonLocator;
	
	@FindBy(xpath = "//tr[contains(@data-testid,'native-issue-table.ui.issue-row')]")
	List<WebElement> taskRowLocator;
	
	@FindBy(xpath = "//table[contains(@aria-label,'Issues')]")
	List<WebElement> tableScrollLocator;
	
	@FindBy(xpath = "//tr[contains(@data-testid,'native-issue-table.ui.issue-row')]/td[4]//span")
	List<WebElement> spotIdLocator;
	
	@FindBy(xpath = "//tr[contains(@data-testid,'native-issue-table.ui.issue-row')]/td[2]//a")
	List<WebElement> issueKeyLocator;
	
	@FindBy(xpath = "//tr[contains(@data-testid,'native-issue-table.ui.issue-row')]/td[6]")
	List<WebElement> websiteUrlLocator;
	
	//@FindBy(xpath = "//div[contains(@data-testid,'issue-field-assignee-assign-to-me.ui.assign-to-me.link')]")
	@FindBy(xpath = "//div[text()='Assign to me']")
	WebElement assingToMeLinkLocator;
	
	@FindBy(xpath = "//div[@data-testid='read-view-container']//span[contains(text(),'Vanja Mojsilovic')]")
	List<WebElement> assignToVanjaLocator;

	//@FindBy(xpath = "//button[contains(@data-testid,'issue-view-activity-comment.comment-add-editor.textarea')]")
	@FindBy(xpath = "//button[normalize-space(text())='Add a comment…']")
	WebElement firstCommentButtonLocator;
	
	@FindBy(xpath = "//div[contains(@class,'ua-chrome ProseMirror pm-table-resizing-plugin')]/p")
	WebElement firstCommentPLocator;
	
	@FindBy(xpath = "//button[contains(@data-testid,'comment-save-button')]")
	WebElement saveCommentButtonLocator;
	
	@FindBy(xpath = "//button[contains(@id,'issue.fields.status-view.status-button')]")
	WebElement taskStatusButtonLocator;
	
	@FindBy(xpath = "//div[contains(@data-testid,'issue-field-status.ui.status-view.transition')]//span[text()='Close it']/parent::div")
	WebElement closedStatusLocator;
	
	@FindBy(xpath = "//div[contains(@data-editor-container-id,'issue-description-editor')]//a")
	WebElement descriptionGithubLinkLocator;
	
	
	
	//methods
	public int getIssueKeySpotIdFromApi(WebDriver driver,String apiUrl,List<String> issueKeyCollection,List<String> spotIdCollection) throws InterruptedException {
		int numberResult = 0;
		System.out.println(apiUrl);
		String script = 
				"return (async function() {" +
					    "  const url = '" + apiUrl + "';" +
					    "  const response = await fetch(url, {" +
					    "    method: 'GET'," +
					    "    headers: {" +
					    "      'Content-Type': 'application/json'," +
					    "    }" +
					    "  });" +
					    "  const data = await response.json();" +
					    "  if (data && data.issues) {" +
					    "    return data.issues.map(issue => ({" +
					    "      key: issue.key," +
					    "      spotId: issue.fields.customfield_10053" +
					    "    }));" +
					    "  } else {" +
					    "    return 'No issues found';" +
					    "  }" +
					    "})()";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object result = js.executeScript(script);
        int k = 0;
        if (result instanceof List) {
            List<Map<String, String>> issues = (List<Map<String, String>>) result;
            for(Map<String, String> issue:issues) {
            	String key = issue.get("key");
                issueKeyCollection.add(key);
                String spotId = issue.get("spotId");
                spotIdCollection.add(spotId);
                System.out.println(k+". Key: " + key + ", Spot ID: " + spotId);
                k++;
            }
            numberResult = k;
        } else {
            System.out.println(result);
        }
        return numberResult;
	}
	
	public int getIssueKeySpotIdWebsiteUrlFromApi(WebDriver driver,String apiUrl,List<String> issueKeyCollection,List<String> spotIdCollection,List<String> websiteUrlCollection) throws InterruptedException {
		int numberResult = 0;
		System.out.println(apiUrl);
		String script = 
				"return (async function() {" +
					    "  const url = '" + apiUrl + "';" +
					    "  const response = await fetch(url, {" +
					    "    method: 'GET'," +
					    "    headers: {" +
					    "      'Content-Type': 'application/json'," +
					    "    }" +
					    "  });" +
					    "  const data = await response.json();" +
					    "  if (data && data.issues) {" +
					    "    return data.issues.map(issue => ({" +
					    "      key: issue.key," +
					    "      spotId: issue.fields.customfield_10053," +
					    "      websiteUrl: issue.fields.customfield_10068" +
					    "    }));" +
					    "  } else {" +
					    "    return 'No issues found';" +
					    "  }" +
					    "})()";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object result = js.executeScript(script);
        int[] k = {0};
        if (result instanceof List) {
            List<Map<String, String>> issues = (List<Map<String, String>>) result;
             
            issues.forEach(issue -> {
            	
                String key = issue.get("key");
                issueKeyCollection.add(key);
                String spotId = issue.get("spotId");
                spotIdCollection.add(spotId);
                String websiteUrl = issue.get("websiteUrl");
                if(websiteUrl == null || websiteUrl.isEmpty()) {
                	websiteUrl="null";
                }
                websiteUrlCollection.add(websiteUrl);
                System.out.println(k[0]+". Key: " + key + ", Spot ID: " + spotId + ", Website URL: " + websiteUrl);
                k[0]++;
            });
        } else {
            System.out.println(result);
        }
        numberResult = k[0];
        return numberResult;
	}
		

	
	
	public int loadCollectionsCtaFromApi(WebDriver driver,
			String jql,
			//String enteredKeyIssues,
			List<String> spotIdCollection,
			List<String> issueKeyCollection,
			List<String> websiteUrlCollection) throws InterruptedException, IOException {
		String encodedJql = URLEncoder.encode(jql, "UTF-8");
        //String apiQueryUrl = "https://spothopper.atlassian.net/rest/api/2/search?jql=" + encodedJql;
        String apiQueryUrl = "https://spothopper.atlassian.net/rest/api/3/search?jql=" + encodedJql+"&maxResults=1000";
        System.out.println("apiQueryUrl: "+apiQueryUrl);
        goToWithResponseCode(apiQueryUrl);
        Thread.sleep(2000);
		String tasksInJson = getTasksInJson(driver);
		String keyIssueRegex = "\"key\"\\s*:\\s*\"(WEB-\\d+)\"\\s*,\\s*\"fields\"";
		Pattern keyIssuePattern = Pattern.compile(keyIssueRegex);
	    Matcher keyIssueMatcher = keyIssuePattern.matcher(tasksInJson);
	    //String spotIdRegex = "\"customfield_10053\":\"(\\d+)\"";
	    String spotIdRegex = "\"customfield_10053\"\\s*:\\s*\"(\\d+)\"";
	    //String spotIdRegex = "\"customfield_10053\"\\s*:\\s*\"(.*?)\"\\s*,";

		Pattern spotIdPattern = Pattern.compile(spotIdRegex);
	    Matcher spotIdMatcher = spotIdPattern.matcher(tasksInJson);
	    //String websitUrlRegex = "\"customfield_10068\":\"(https?://[^\"]+)\"";
	    String websitUrlRegex = "\"customfield_10068\"\\s*:\\s*\"(https?://[^\"]+)\"";
		Pattern websitUrlPattern = Pattern.compile(websitUrlRegex);
	    Matcher websitUrlMatcher = websitUrlPattern.matcher(tasksInJson);
		int numb = 0;
        while (keyIssueMatcher.find()) {
        	numb++;
        	//String keyIssueFeched = matcher.group().substring(9,matcher.group().length()-10);
        	String keyIssueFetched = keyIssueMatcher.group(1);
        	if(keyIssueFetched.isEmpty()) {
        		keyIssueFetched = "null";
        	}
        	System.out.println(numb+". keyIssueFetched: "+keyIssueFetched);
        	issueKeyCollection.add(keyIssueFetched);
        }
        numb = 0;
        while (spotIdMatcher.find()) {
        	numb++;
        	//String spotIdFetched = spotIdMatcher.group().substring(9,matcher.group().length()-1);
        	//String spotIdFetched = spotIdMatcher.group(1);
        	String spotIdFetched = spotIdMatcher.find() ? spotIdMatcher.group(1) : "null";
        	if(spotIdFetched.isEmpty()) {
        		spotIdFetched = "null";
        	}
        	System.out.println(numb+". spotIdFetched: "+spotIdFetched);
        	spotIdCollection.add(spotIdFetched);
        }
        numb = 0;
        while (websitUrlMatcher.find()) {
        	numb++;
        	//String websiteUrlFetched = spotIdMatcher.group().substring(9,matcher.group().length()-1);
        	//String websiteUrlFetched = websitUrlMatcher.group(1);
        	String websiteUrlFetched = websitUrlMatcher.find() ? websitUrlMatcher.group(1) : "null";
        	if(websiteUrlFetched.isEmpty()) {
        		websiteUrlFetched = "null";
        	}
        	System.out.println(numb+". websiteUrlFetched: "+websiteUrlFetched);
        	websiteUrlCollection.add(websiteUrlFetched);
        }
        return numb;
	}
	
	
	public String getKeyIssuesByApi(WebDriver driver,String jql,String enteredKeyIssues) throws InterruptedException, IOException {
		String encodedJql = URLEncoder.encode(jql, "UTF-8");
        //String apiQueryUrl = "https://spothopper.atlassian.net/rest/api/2/search?jql=" + encodedJql;
        String apiQueryUrl = "https://spothopper.atlassian.net/rest/api/3/search?jql=" + encodedJql+"&maxResults=1000";
        System.out.println("apiQueryUrl: "+apiQueryUrl);
        goToWithResponseCode(apiQueryUrl);
        Thread.sleep(2000);
		String tasksInJson = getTasksInJson(driver);
		//String regex = "\"key\"\\s*:\\s*\"WEB-\\d+\"";
		//String regex = "\"key\"\\s*:\\s*\"WEB-\\d+\"\\s*,\\s*\"fields\"";
		String regex = "\"key\"\\s*:\\s*\"(WEB-\\d+)\"\\s*,\\s*\"fields\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(tasksInJson);
        
		int numb = 0;
	        while (matcher.find()) {
	        	numb++;
	        	//System.out.println(numb+". "+matcher.group());
	        	//String keyIssueFeched = matcher.group().substring(9,matcher.group().length()-10);
	        	String keyIssueFetched = matcher.group(1);
	            if(numb == 1){
	            	enteredKeyIssues = keyIssueFetched;
	            }else {
	            	enteredKeyIssues += "," + keyIssueFetched;
	            }
	            //System.out.println(numb+". "+matcher.group());
	        }
	    System.out.println("Number of tasks: "+numb+", KeyIssues: "+enteredKeyIssues);
	    return enteredKeyIssues;
	}
	
	
	public String getTasksInJson(WebDriver driver) throws JsonMappingException, JsonProcessingException {
		String result = "";
		WebElement element = waitForVisibilityOfElement(driver, apiPreLocator, 3) ;
		String tasksInJson = element.getText();
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
		result = objectWriter.writeValueAsString(objectMapper.readTree(tasksInJson));
		return result;
	}
	
	
	
	public void checkJiraApiAccess(WebDriver driver,String jiraUrl,String username, String encodedAuth) throws IOException {
	 
	    try {
	        // Create a simple test API request
	        URL url = new URL("https://spothopper.atlassian.net/rest/api/2/myself");
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");
	        connection.setRequestProperty("Authorization", "Basic " + encodedAuth);
	        connection.setRequestProperty("Accept", "application/json");

	        int responseCode = connection.getResponseCode();
	        System.out.println("API Access Test Response Code: " + responseCode);
	        
	        String encodedAuthReceived = Base64.getEncoder().encodeToString((username + ":" + encodedAuth).getBytes());
	        
	        System.out.println("Encoded Token Length: " + encodedAuthReceived.length());
	        System.out.println("Encoded Token (first 10 chars): " + encodedAuthReceived.substring(0, Math.min(encodedAuth.length(), 10)));
	        
	        
	        // Successful response codes
	        if (responseCode == 200) {
	            System.out.println("API Access: Confirmed");
	            // Read response to get more details
	            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
	                String line;
	                StringBuilder response = new StringBuilder();
	                while ((line = reader.readLine()) != null) {
	                    response.append(line);
	                }
	                System.out.println("User Details: " + response.toString());
	            }
	        } else {
	            System.out.println("API Access: Potentially Restricted");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    try {
	        // Test multiple endpoints to isolate access issues
	        String[] testEndpoints = {
	            "https://spothopper.atlassian.net/rest/api/2/myself",  // User info
	            "https://spothopper.atlassian.net/rest/api/2/project", // Project list
	            "https://spothopper.atlassian.net/rest/api/2/issue/picker" // Issue picker
	        };

	        for (String endpoint : testEndpoints) {
	            URL url = new URL(endpoint);
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setRequestMethod("GET");
	            connection.setRequestProperty("Authorization", "Basic " + encodedAuth);
	            connection.setRequestProperty("Accept", "application/json");

	            int responseCode = connection.getResponseCode();
	            System.out.println("Endpoint: " + endpoint);
	            System.out.println("Response Code: " + responseCode);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	}
	
	public String fetchJiraData(WebDriver driver,String jiraUrl, String encodedAuth) throws IOException {
		URL url = new URL(jiraUrl);
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Basic " + encodedAuth);
            connection.setRequestProperty("Accept", "application/json");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    return response.toString();
                }
            } else {
                // Read error stream for more details
                try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()))) {
                    StringBuilder errorResponse = new StringBuilder();
                    String line;
                    while ((line = errorReader.readLine()) != null) {
                        errorResponse.append(line);
                    }
                    throw new IOException("Failed to fetch data. Response code: " + responseCode + 
                                          ". Error details: " + errorResponse.toString());
                }
            }
        } finally {
            if (connection != null) {
            	connection.disconnect();
            }
        }
    }
	
	public String getGithubIssueLink(WebDriver driver) {
		String result = "";
		WebElement element = waitForVisibilityOfElement(driver, descriptionGithubLinkLocator, 3);
		result = element.getAttribute("href"); 
		return result;
	}
	
	public String getAllKeyIssues(WebDriver driver) {
		String result = "";
		List<WebElement> elements = waitForVisibilityOfElements(driver, issueKeyLocator, 3);
		if(!elements.isEmpty()) {
			int i=0;
			for(WebElement element:elements) {
				if(i==0) {
					result += element.getText();
				}else {
					result += ","+element.getText();
				}
				i++;
			}
		}
		return result;
	}
	
	
	public String getDoneIssueKeys(WebDriver driver) {
		String result = "";
		List<WebElement> elements = waitForVisibilityOfElements(driver, issueKeyLocator, 3);
		if(!elements.isEmpty()) {
			int i=0;
			for(WebElement element:elements) {
				if(i==0) {
					result += element.getText();
				}else {
					result += ","+element.getText();
				}
				i++;
			}
		}
		return result;
	}
	
	public void clickCloseStatus(WebDriver driver) {
		WebElement element = waitForVisibilityOfElement(driver,closedStatusLocator, 15);
		element.click();
	}
	
	public void clickTaskStatus(WebDriver driver) {
		WebElement element = waitForVisibilityOfElement(driver,taskStatusButtonLocator, 15);
		element.click();
	}
	
	public void saveComment(WebDriver driver) {
		WebElement element = waitForVisibilityOfElement(driver, saveCommentButtonLocator, 15);
		element.click();
	}
	
	public void enterComment(WebDriver driver,String commentContent) throws InterruptedException {
		//WebElement commentButtonElement = waitForVisibilityOfElement(driver, firstCommentButtonLocator, 15);
		//Thread.sleep(1000);
		//commentButtonElement.click();
		Actions actions = new Actions(driver);
        actions.sendKeys("m").perform();
		WebElement commentSpanElement = waitForVisibilityOfElement(driver, firstCommentPLocator, 15);
		commentSpanElement.sendKeys(commentContent);
	}
	
	
	public void clickAssignToMe(WebDriver driver) {
		List<WebElement> vanjaElement = waitForVisibilityOfElements(driver,assignToVanjaLocator, 3);
		if(vanjaElement.isEmpty()) {
			WebElement element = waitForVisibilityOfElement(driver,assingToMeLinkLocator, 5);
			element.click();
		}
	}
	
	public void loadCollectionsBuild(WebDriver driver,
			List<String> spotIdCollection,
			List<String> issueKeyCollection,
			int numberOfTasks) {
		List<WebElement> spotIdElements = waitForVisibilityOfElements(driver, spotIdLocator, 15);
		List<WebElement> issueKeyElements = waitForVisibilityOfElements(driver, issueKeyLocator, 15);
		//List<WebElement> websiteUrlElements = waitForVisibilityOfElements(driver, websiteUrlLocator, 15);
		for (int j = 0; j<numberOfTasks; j++) {
			String spotId = spotIdElements.get(j).getText();
			System.out.println(j+". "+spotId);
			spotIdCollection.add(spotId); 
			String issueKey = issueKeyElements.get(j).getText();
			System.out.println(issueKey);
			issueKeyCollection.add(issueKey);
		}
	}
	
	public void loadCollectionsCta(WebDriver driver,
			List<String> spotIdCollection,
			List<String> issueKeyCollection,
			List<String> websiteUrlCollection,
			int numberOfTasks) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(2000);
		//js.executeScript("arguments[0].parentElement.scrollTop = arguments[0].parentElement.scrollHeight;", tableScrollLocator.get(0));
		List<WebElement> spotIdElements = waitForVisibilityOfElements(driver, spotIdLocator, 15);
		List<WebElement> issueKeyElements = waitForVisibilityOfElements(driver, issueKeyLocator, 15);
		List<WebElement> websiteUrlElements = waitForVisibilityOfElements(driver, websiteUrlLocator, 15);
		for (int j = 0; j<numberOfTasks; j++) {
			String spotId = spotIdElements.get(j).getText();
			System.out.println(j+". "+spotId);
			spotIdCollection.add(spotId); 
			String issueKey = issueKeyElements.get(j).getText();
			System.out.println(issueKey);
			issueKeyCollection.add(issueKey);
			List<WebElement> aElements = websiteUrlLocator.get(j).findElements(By.tagName("a"));
			String websiteUrl ="";
			if(!aElements.isEmpty()) {
				websiteUrl = aElements.get(0).getAttribute("href");
			}
			System.out.println(websiteUrl);
			websiteUrlCollection.add(websiteUrl);
		}
	}
	
	
	
	public String getSpotId(WebDriver driver,int orderNumber) {
		String result = "";
		List<WebElement> taskElements = spotIdLocator;
		if(!taskElements.isEmpty()) {
			result = taskElements.get(orderNumber).getText();
		}
		return result;
	}
	
	public int getNumberOfTasks(WebDriver driver) {
		int result = 0;
		List<WebElement> taskElements = issueKeyLocator;
		if(!taskElements.isEmpty()) {
			result = taskElements.size();
		}
		return result;
	}
	
	public void enterJql(WebDriver driver,String query) throws InterruptedException {
		//WebElement searchElement = searchJqlLocator;
		WebElement searchElement = waitForVisibilityOfElement(driver, searchJqlLocator, 15);
		searchElement.clear();
		Thread.sleep(1000);
		searchElement.sendKeys(query);
		WebElement searchButtonElement = searchJqlButtonLocator;
		searchButtonElement.click();
	}
	
	public void jiraSignIn(WebDriver driver) throws IOException, InterruptedException {
		goToWithResponseCode("https://spothopper.atlassian.net/");
    	Thread.sleep(3000);
    	clickSignInGoogle(driver);
    	clickGoogleAccunt(driver);
    	Thread.sleep(8000);
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
	
	public String getTestSiteUrl(WebDriver driver) {
		
		String result = "";
		List<WebElement> elements = waitForVisibilityOfElements(driver, testSiteUrlLocator, 15);
		if(elements.size()>0) {
			result = elements.get(0).getAttribute("href");
		}else {
			System.out.println("testSiteUrlL not found");
		}
		return result;
	}
	
	public String getBranchName(WebDriver driver,String testSiteUrl) {
		String result = "";
		int startIndex = testSiteUrl.indexOf("https://spot-sample-") + "https://spot-sample-".length();
        int endIndex = testSiteUrl.indexOf(".spotapps.co/");
        result = testSiteUrl.substring(startIndex, endIndex);
        //result = result.replace("-", "_");
		return result;
	}
	
	
	
} // end class
	
	
