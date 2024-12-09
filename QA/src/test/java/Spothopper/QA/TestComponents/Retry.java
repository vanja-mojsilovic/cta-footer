package Spothopper.QA.TestComponents;

import static org.testng.Assert.assertTrue;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	
	int count = 0;
	int maxTry = 0; //change if want to retry
	public Retry() {
		
	}

	@Override
	public boolean retry(ITestResult result) {
		if(count<maxTry) {
			count++;
			return true;
		}
		return false;
	}

}
