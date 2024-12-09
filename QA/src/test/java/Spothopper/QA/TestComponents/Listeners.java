package Spothopper.QA.TestComponents;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Test;

import Spothopper.QA.Resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("test succeeded! ");
		extentTest.get().log(Status.PASS, "test passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("TEST FAILED!");
		extentTest.get().fail(result.getThrowable());
	}
	
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}