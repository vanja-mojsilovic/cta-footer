package Spothopper.QA.PageObjects;

import Spothopper.QA.AbstractComponents.AbstractComponent;
import freemarker.core.ParseException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
//import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;

public class ReadWriteFilePage extends AbstractComponent {
	
	WebDriver driver;
	
	
	public ReadWriteFilePage(WebDriver driver) 
		{
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
						
		}
	
	
	
	//Methods
	public void createBuildsLogFile(WebDriver driver,String timeStamp,String errorMessage) throws IOException{
		String file_name = timeStamp+"_build_log_file.txt";
        String filePath = System.getProperty("user.dir") + "/created-log-files/" + file_name;
        String text_to_append = errorMessage;
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(text_to_append + "\n");      	
        }
	}
	
	public void createCtaLogFile(WebDriver driver, List<String> entered_text,String timeStamp) throws IOException{
		String file_name = timeStamp+"_cta_log.txt";
        String filePath = System.getProperty("user.dir") + "/created-log-files/" + file_name;
        String text_to_append ="";
        for(String line:entered_text) {
        	text_to_append += line;
        }
        try (FileWriter writer = new FileWriter(filePath, true)) {
        	writer.write(text_to_append + "\n");	
        }
	}
	
	public void createNonPlaceholderFile(WebDriver driver, String entered_text,String timeStamp,int firtsEntering) throws IOException{
		String file_name = timeStamp+"_non_placeholder.txt";
        String filePath = System.getProperty("user.dir") + "/created-log-files/" + file_name;
        String text_to_append = entered_text;
        try (FileWriter writer = new FileWriter(filePath, true)) {
        	writer.write(text_to_append + "\n");    	
        }
	}
	
	public void createPlaceholderSucessFile(WebDriver driver, String entered_text,String timeStamp,int firtsEntering) throws IOException{
		String file_name = timeStamp+"_placeholder_success.txt";
        String filePath = System.getProperty("user.dir") + "/created-log-files/" + file_name;
        String text_to_append = entered_text;
        try (FileWriter writer = new FileWriter(filePath, true)) {
        	writer.write(text_to_append + "\n");    	
        }
	}
	
	public void createOrderDropDownFile(WebDriver driver,String timeStamp,String errorMessage) throws IOException{
		String file_name = timeStamp+"_order_drop_down.txt";
        String filePath = System.getProperty("user.dir") + "/created-log-files/" + file_name;
        String text_to_append = errorMessage;
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(text_to_append + "\n");      	
        }
	}
	
	public void createBuildsErrorFile(WebDriver driver,String timeStamp,String errorMessage) throws IOException{
		String file_name = timeStamp+"_build_errors.txt";
        String filePath = System.getProperty("user.dir") + "/created-log-files/" + file_name;
        String text_to_append = errorMessage;
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(text_to_append + "\n");      	
        }
	}
	
	public void createBuildSucessFile(WebDriver driver, String entered_text,String timeStamp,int firtsEntering) throws IOException{
		String file_name = timeStamp+"_bulid_success.txt";
        String filePath = System.getProperty("user.dir") + "/created-log-files/" + file_name;
        String text_to_append = entered_text;
        try (FileWriter writer = new FileWriter(filePath, true)) {
        	writer.write(text_to_append + "\n");    	
        }
	}
	
	public void createCtaLinksFooterErrorsFile(WebDriver driver,String timeStamp,String errorMessage) throws IOException{
		String file_name = timeStamp+"_cta_links_smart_footer_errors.txt";
        String filePath = System.getProperty("user.dir") + "/created-log-files/" + file_name;
        String text_to_append = errorMessage;
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(text_to_append + "\n");      	
        }
	}
	
	public void createCtaLinksFooterFile(WebDriver driver, String entered_text,String timeStamp,int firtsEntering) throws IOException{
		String file_name = timeStamp+"_cta_links_smart_footer_success.txt";
        String filePath = System.getProperty("user.dir") + "/created-log-files/" + file_name;
        String text_to_append = entered_text;
        try (FileWriter writer = new FileWriter(filePath, true)) {
        	if(firtsEntering==1) {
        		writer.write(text_to_append);
        	}else {
        		writer.write(","+text_to_append);
        	}        	
        }
	}
	
	public void createMenuFile(WebDriver driver, String entered_text,String timeStamp) throws IOException{
		String file_name = timeStamp+"_cta_links_smart_footer_automation.txt";
        String filePath = System.getProperty("user.dir") + "/created-log-files/" + file_name;
        String text_to_append = entered_text;
        try (FileWriter writer = new FileWriter(filePath, true)) {
        	//writer.write(timeStamp + "\n");
            //writer.write(text_to_append + "\n");
        	writer.write(text_to_append+",");
        }
	}
	
	public String shouldDeactivateTmt(WebDriver driver,String featureName,String spotId) {
		Map<String, String> featureLocators = new HashMap<>();
		String directoryPath = System.getProperty("user.dir") + "/created-log-files/";
		File folder = new File(directoryPath);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
            	System.out.println(file.getName()+spotId);
                if (file.getName().trim().startsWith(spotId) && file.getName().contains("_activated_")) {
                    if(file.getName().contains("party#_link")) {
                    	System.out.println("contains( party#_link )");
                    	featureLocators.put("activatePrivateParties",file.getName());
                    }
                    if(file.getName().contains("catering#_link")) {
                    	featureLocators.put("activateCatering",file.getName());
                    }
                    if(file.getName().contains("reservations#_link")) {
                    	featureLocators.put("activateReservations",file.getName());
                    }
                    if(file.getName().contains("ordering-menu")) {
                    	featureLocators.put("activateOnlineOrders",file.getName());
                    }
                    if(file.getName().contains("job-listings")) {
                    	featureLocators.put("activateJobsApplications",file.getName());
                    }
                }
            }
        }
        System.out.println("featureLocators "+featureLocators);// ovde je problem
	    String shouldDeactivate=readLastLine(featureLocators.get(featureName),directoryPath);
	    System.out.println(featureLocators.get(featureName)+" Pročitali smo poslednju liniju "+shouldDeactivate);
		return shouldDeactivate;
	}
	
	public List<FeaturePage> getFeaturesActiveList(WebDriver driver,
			ReadWriteFilePage readWriteFilePage,
			String spotId) {
		    FeaturePage featurePage = new FeaturePage(driver);
			List<FeaturePage> tmtFeatures=new ArrayList<FeaturePage>();
			featurePage.fillfeaturePageList(tmtFeatures);
			List<String> filteredFiles = new ArrayList<>();
            String directoryPath = System.getProperty("user.dir") + "/created-log-files/";
            File folder = new File(directoryPath);
            File[] listOfFiles = folder.listFiles();
            if (listOfFiles != null) {
                for (File file : listOfFiles) {
                    if (file.isFile() && file.getName().startsWith(spotId) && file.getName().trim().contains("_activated_")) {
                        filteredFiles.add(file.getName());
                    }
                }
            }
            System.out.println(filteredFiles);
			for(FeaturePage feature:tmtFeatures) {
				if(feature.tmtFeature==true) {
		            for(String filteredFile : filteredFiles) {
		            	String lastLine = readLastLine(filteredFile,directoryPath);
		            	System.out.println(filteredFile+" last line is "+lastLine);
		            	if(readLastLine(filteredFile,directoryPath).equals("false")) {
		            		feature.shouldDeactivate=true;
		            	}else {
		            		feature.shouldDeactivate=false;
		            	}
		            } 
				}
			}
			return tmtFeatures;
	}
	
	public String readLastLine(String particularFile,String directoryPath) {
        String lastLine = "";
        try {
            List<String> lines = Files.readAllLines(Paths.get(directoryPath + particularFile));
            if (!lines.isEmpty()) {
            	System.out.println(lines);                
            	lastLine = lines.get(lines.size() - 1).trim();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastLine;
    }
	
	
	public void createTmtMenuFile(WebDriver driver, String menu_name,String text_to_append) throws IOException{
		String file_name = menu_name + ".txt";
        String filePath = System.getProperty("user.dir") + "/created-log-files/" + file_name;
        System.out.println(filePath);
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(text_to_append + "\n");
        }
	}
	
	
	
	public void writeDeleteStatusInFile(WebDriver driver, String file_name) throws IOException{
		String filePath = System.getProperty("user.dir") + "/created-log-files/" + file_name + ".txt";
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write("deleted\n");
        }
	}
	
	public List<String> getFilteredLogFiles(String spotId, String searchString) {
        List<String> filteredFiles = new ArrayList<>();
        String directoryPath = System.getProperty("user.dir") + "/created-log-files/";
        File folder = new File(directoryPath);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile() && file.getName().startsWith(spotId) && file.getName().contains(searchString)) {
                    filteredFiles.add(file.getName());
                }
            }
        }
        return filteredFiles;
    }
	
	
	
	

	
}
	
	
	

