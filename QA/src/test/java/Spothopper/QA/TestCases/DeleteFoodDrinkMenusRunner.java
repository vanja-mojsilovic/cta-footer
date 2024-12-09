package Spothopper.QA.TestCases;

import org.testng.TestNG;

public class DeleteFoodDrinkMenusRunner {

	public static void main(String[] args) {
		TestNG testng = new TestNG();
        testng.setTestSuites(java.util.Collections.singletonList("testSuites/deleting_food_drink_menus.xml"));
        testng.run();
	}
}
