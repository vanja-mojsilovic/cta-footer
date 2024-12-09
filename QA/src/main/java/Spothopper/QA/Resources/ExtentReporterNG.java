package Spothopper.QA.Resources;

import org.bouncycastle.oer.its.ieee1609dot2.basetypes.PublicVerificationKey;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
		
	public static ExtentReports getReportObject(){
			String path = System.getProperty("user.dir")+"/reports/index.html";
			ExtentSparkReporter reporter = new ExtentSparkReporter(path);
			ExtentReports extent = new ExtentReports();
			extent.attachReporter(reporter);
			extent.setSystemInfo("Test test", "Spothopper Spothopper");
			return extent;
		}
}
