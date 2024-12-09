package Spothopper.QA.TestCases;

import org.testng.TestNG;

public class WebsiteNavBarFeaturesRunner {

	public static void main(String[] args) {
		TestNG testng = new TestNG();
        testng.setTestSuites(java.util.Collections.singletonList("testSuites/website_nav_bar_features.xml"));
        testng.run();

	}

}
