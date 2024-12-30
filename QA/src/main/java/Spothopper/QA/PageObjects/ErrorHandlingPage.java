package Spothopper.QA.PageObjects;

import Spothopper.QA.AbstractComponents.AbstractComponent;
import Spothopper.QA.Resources.*;


import org.bouncycastle.crypto.engines.ISAACEngine;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.PublicVerificationKey;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.IFactoryAnnotation;
import org.openqa.selenium.JavascriptExecutor;
import java.util.AbstractMap.SimpleEntry;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ErrorHandlingPage extends AbstractComponent {
	
	WebDriver driver;
	public List<String> errorMessages = new ArrayList<String>();
	private static final String BLOCKS_DIR = "error_blocks"; 
	
	public ErrorHandlingPage(WebDriver driver) 
		{
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
						
		}
	
	
	@FindBy(xpath ="//button[@class = 'close']")
	List <WebElement> closeNewsLetterLocator;
	
	

	
	// Methods
	public void addError(WebDriver driver,
			String issueKey,
			String errorMessage,
			ReadWriteFilePage readWriteFilePage,
			String currentTimeString) throws IOException {
		errorMessages.add(errorMessage);
		if (!errorMessages.isEmpty()) {
			readWriteFilePage.createCtaLinksFooterErrorsFile(driver,currentTimeString,errorMessage);
			
		}
		System.out.println("<><> <><> "+errorMessage+" <><> <><>");
		
	}
	
	public void addErrorWithBlockchain(WebDriver driver,
			String issueKey,
            String errorMessage,
            ReadWriteFilePage readWriteFilePage,
            String currentTimeString,
            Blockchain blockchain,
            int websiteNumber) throws IOException 
			{
				errorMessages.add(errorMessage);
				if (!errorMessages.isEmpty()) {
					
					blockchain.addBlock(errorMessage);
					
					Block latestBlock = blockchain.getChain().get(blockchain.getChain().size() - 1);
					String blockData = String.format(websiteNumber+". %s, Previous Hash: %s, Hash: %s",
		                latestBlock.getData(),
		                latestBlock.getPreviousHash(),
		                latestBlock.getHash());
					readWriteFilePage.createCtaLinksFooterErrorsFile(driver,currentTimeString,blockData);
				}
				System.out.println("<><><> " + errorMessage + " <><><>");
	}	
}
	
	
	
	
		

	
	

