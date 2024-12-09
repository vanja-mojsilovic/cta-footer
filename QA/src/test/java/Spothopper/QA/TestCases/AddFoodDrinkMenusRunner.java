package Spothopper.QA.TestCases;

import org.testng.TestNG;
//import Spothopper.QA.TestCases.*;

public class AddFoodDrinkMenusRunner {

	public static void main(String[] args) {
		TestNG testng = new TestNG();
        testng.setTestSuites(java.util.Collections.singletonList("testSuites/sh_add_menus.xml"));
        testng.run();

	}

}
