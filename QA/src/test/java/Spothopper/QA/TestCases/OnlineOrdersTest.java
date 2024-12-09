package Spothopper.QA.TestCases;


import Spothopper.QA.PageObjects.*;

import Spothopper.QA.TestComponents.BaseTest;
import Spothopper.QA.TestComponents.Retry;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.index.qual.SubstringIndexFor;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;





public class OnlineOrdersTest extends BaseTest {
	
	// Variables
	public String spotID = System.getenv("SPOT_ID");
	public String loginToken = System.getenv("LOGIN_TOKEN");
	public int dataProviderCounter;
	public int currentCounter = 0;
	public String menuName = "Dinner";
	public String menuItemName_1 = "Chicken Soup";
	public String menuItemPrice_1 = "35.00";
	public String menuItemName_2 = "Beef Soup";
	public String menuItemPrice_2 = "38.00";
	public String menuNumber = "0";
	public String creditCardNumber = "4242424242424242";
	public String expiryDate = "12/31";
	public String cvcNumber = "242";
	public String postalCode = "42424";
	public String orderNumber = "0";
	public List<String> parent_child_add_menus = new ArrayList<>(); 
	// URLs
	public String hoursURL = getHoursURL(spotID, loginToken);
	public String ordersSettingsURL = domainURL + spotID + "/order_inquiries/settings?login_token=" + loginToken;
	public String menusURL = domainURL + spotID + "/food_menus_landing?login_token=" + loginToken;
	public String addNewMenusURL = domainURL + spotID + "/food_menus#/new?menu_type=food&login_token=" + loginToken;
	public String orderingMenuPopupTMT = tmtDomainURL + "ordering-menu" + "/?spot_id=" + spotID
			+ "&accordion=true&images=yes&login_token=" + loginToken;
	public String deleteMenuURL = domainURL + spotID + "/food_menus#/" + menuNumber + "?login_token=" + loginToken;
	public String orderDetailsURL = domainURL + spotID + "/inquiry_details/" + orderNumber + "?login_token="
			+ loginToken;
	
	
	// Methods
	public int setDataProviderCounter(String num) {
		dataProviderCounter = Integer.parseInt(num);
		System.out.println("dataProviderCounter is: " + dataProviderCounter);
		return dataProviderCounter;
	}

	public int getCurrentCounter() {
		return currentCounter;
	}

	public int increaseCurrentCounter() {
		currentCounter++;
		return currentCounter;
	}
	
	
	public void setOrderNumber(String orderNumberReceivedString) {
		System.out.println(orderNumberReceivedString);
		int indexOfHash = orderNumberReceivedString.indexOf("#");
		orderNumber = orderNumberReceivedString.substring(indexOfHash+1);
		orderDetailsURL = domainURL + spotID + "/inquiry_details/" + orderNumber + "?login_token=" + loginToken;
	}
	
	public String countSubtotal(String str1, String str2) {
		double num1 = Double.parseDouble(str1);
		double num2 = Double.parseDouble(str2);
		double result = num1 + num2;
		String sum = String.format("%.2f", result);
		return sum;
	}
	
	public void setMenuName(String insertedMenuName) {
		menuName = insertedMenuName;
	}

	public void setMenuItemName_1(String insertedMenuItemString) {
		menuItemName_1 = insertedMenuItemString;
	}

	public void setMenuItemName_2(String insertedMenuItemName) {
		menuItemName_2 = insertedMenuItemName;
	}

	public void setMenuItemPrice_1(String insertedMenuItemPrice) {
		menuItemPrice_1 = insertedMenuItemPrice;
	}

	public void setMenuItemPrice_2(String insertedMenuItemPrice) {
		menuItemPrice_2 = insertedMenuItemPrice;
	}

	public void setMenuNumberString(String insertedMenuNum) {
		menuNumber = insertedMenuNum;
	}

	
	@DataProvider
	public Object[][] getData() throws IOException {

		System.out.println("*********getting data from json_" + getCurrentCounter());
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "/src/test/java/Spothopper/QA/data/csvjson.json");
		if(production_staging.equals("staging")) {
			int numOfSpots = 6;
			Object[][] dataArray = new Object[numOfSpots][1]; 
	        for (int i = 0; i < numOfSpots; i++) {
	            dataArray[i][0] = data.get(i);}
	        return dataArray;
			//return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)},{data.get(3)},{data.get(4)},{data.get(5)},{data.get(6)}};
		}else {
			return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)},{data.get(3)}};
		}
	}
	
    
    @Test
    public void setMenus() throws IOException, InterruptedException {
    	System.out.println("*******Menus started!********");
        OrdersSettingsPage ordersSettingsPage = new OrdersSettingsPage(driver);
        CustomizeMenuPage customizeMenusPage = new CustomizeMenuPage(driver);
        NewMenusPage newMenusPage = new NewMenusPage(driver);
        ordersSettingsPage.goTo(ordersSettingsURL);
        Assert.assertTrue(ordersSettingsPage.checkResponseCode(driver, ordersSettingsURL),"LOADING PAGE ERROR!");
        System.out.println(ordersSettingsPage.getCompanyName(driver));
        ordersSettingsPage.clickCloseRecoveryPhoneButton(driver,production_staging);
        //hoursPage.clickCloseDelinquentPopupButton(driver);
        ordersSettingsPage.activateOnlineOrders(driver);
        ordersSettingsPage.activatePickup(driver);
        ordersSettingsPage.activateDelivery(driver);
        ordersSettingsPage.clickSaveSettingsButton(driver);
        Thread.sleep(1000);
        customizeMenusPage.goTo(menusURL);
        Assert.assertTrue(customizeMenusPage.checkResponseCode(driver, menusURL),"LOADING PAGE ERROR!");
        Thread.sleep(2000);
        customizeMenusPage.clickAddNewFoodMenu(driver);   
        //Set<String> set_of_windows = driver.getWindowHandles();
        //Iterator<String> it = set_of_windows.iterator();
        //String parent_id = it.next();
        //String child_id = it.next();
        parent_child_add_menus = customizeMenusPage.switchToChild(driver, parent_child_add_menus);
        //System.out.println("parent_child"+parent_child_add_menus.get(1));
        driver.switchTo().window(parent_child_add_menus.get(1));
        currentTimeString = getStringLocalDateTime();
        String menu_name = "Dinner " + currentTimeString;
        setMenuName(menu_name);
        String menu_section_name = "Soups " + currentTimeString;
        String menu_item_1_name = "Chicken Soup " + currentTimeString;
        String menu_item_2_name = "Beef Soup " + currentTimeString;
        setMenuItemName_1(menu_item_1_name);
        setMenuItemName_2(menu_item_2_name); 
        newMenusPage.insertNewMenuName(driver, menu_name);
        newMenusPage.clickSaveButtonNewMenu(driver);
        newMenusPage.insertNewMenuSectionName(driver, menu_section_name);
        newMenusPage.clickSaveButtonNewMenuSection(driver);
        String currentUrl = driver.getCurrentUrl();
        String menu_num = newMenusPage.getMenuNumber(currentUrl);
        setMenuNumberString(menu_num);
        newMenusPage.insertNewMenuItemPrice(driver, menuItemPrice_1);
        newMenusPage.insertNewMenuItemName(driver, menuItemName_1);
        newMenusPage.clickSaveButtonNewMenuItem(driver);
        Thread.sleep(4000);
        newMenusPage.insertNewMenuItemPrice(driver, menuItemPrice_2);
        newMenusPage.insertNewMenuItemName(driver, menuItemName_2);
        //newMenusPage.screenshotElement(
    	//		driver,
    	//		newMenusPage.insertNewMenuItemName(driver, menuItemName_2),
    	//		local_github, 
    	//		currentTimeString, 
    	//		screenshot_files_path);
        newMenusPage.clickSaveButtonNewMenuItem(driver);
        Thread.sleep(4000);
        driver.close();
        driver.switchTo().window(parent_child_add_menus.get(0));
        System.out.println(menu_name+" is set ***********");
        driver.close();
    }

    
   
    @Parameters({"dataProviderCounter"})
    @Test(dataProvider = "getData", retryAnalyzer = Retry.class)
    public void setOnlineOrders(HashMap<String, String> input) throws IOException, InterruptedException {
    	increaseCurrentCounter();
    	System.out.println(">>>>>>>>Order number "+currentCounter+" started!>>>>>>>>>>>");
        OrdersSettingsPage ordersSettingsPage = new OrdersSettingsPage(driver);
        ordersSettingsPage.goTo(ordersSettingsURL);
        Assert.assertTrue(ordersSettingsPage.checkResponseCode(driver, ordersSettingsURL),"LOADING PAGE ERROR!");
        ordersSettingsPage.clickCloseRecoveryPhoneButton(driver,production_staging);
        //hoursPage.clickCloseDelinquentPopupButton(driver);
        ordersSettingsPage.activateOnlineOrders(driver);
        ordersSettingsPage.activatePickup(driver);
        ordersSettingsPage.activateDelivery(driver);
        String pickupDelivery = input.get("pickup_delivery");
        System.out.println("pickup_delivery_" + getCurrentCounter() + " is:" + pickupDelivery);
        ordersSettingsPage.activateOnlineOrders(driver);
        ordersSettingsPage.activatePickup(driver);
        ordersSettingsPage.activateDelivery(driver);
        String creditCollect = input.get("credit_collect");
        System.out.println("credit_collect_" + getCurrentCounter() + " is:" + creditCollect);
        if (creditCollect.equals("credit")) {
            //System.out.println("check credit_" + getCurrentCounter());
            ordersSettingsPage.chooseCredit(driver);
        }
        if (creditCollect.equals("collect")) {
            //System.out.println("check collect_" + getCurrentCounter());
            ordersSettingsPage.chooseCollect(driver);
        }
        String deliveryFee = input.get("delivery_fee");
        ordersSettingsPage.insertDeliveryFee(driver, deliveryFee);
        String taxRate = input.get("tax_rate");
        ordersSettingsPage.insertTaxRate(driver, taxRate);
        String stricterAuthorization = input.get("stricter_authorization");
        System.out.println("stricterAuthorization" + getCurrentCounter() + " is:" + stricterAuthorization);
        if (stricterAuthorization.equals("TRUE")) {
            ordersSettingsPage.activateStricterAuthorization(driver);
        }
        if (stricterAuthorization.equals("FALSE")) {
            ordersSettingsPage.deactivateStricterAuthorization(driver);
        }
        String processingCost = input.get("processing_cost");
        System.out.println("processingCost" + getCurrentCounter() + " is:" + processingCost);
        if (processingCost.equals("TRUE")) {
            ordersSettingsPage.activateProcessingCost(driver);
        }
        if (processingCost.equals("FALSE")) {
            ordersSettingsPage.deactivateProcessingCost(driver);
        }
        if (pickupDelivery.equals("pickup")) {
            String hour = input.get("turnaround_pickup_hours");
            String minute = input.get("turnaround_pickup_minutes");
            //System.out.println("turnaround_pickup_hours and turnaround_pickup_minutes " + hour + " " + minute);
            ordersSettingsPage.setPickupTurnaroundTime(driver, hour, minute);
        }
        if (pickupDelivery.equals("delivery")) {
            String hour = input.get("turnaround_delivery_hours");
            String minute = input.get("turnaround_delivery_minutes");
            ordersSettingsPage.setDeliveryTurnaroundTime(driver, hour, minute);
        }
        ordersSettingsPage.emailInserted(email);
        ordersSettingsPage.clickSaveSettingsButton(driver);
        Thread.sleep(1000);

        // TMT ordering popup
        TMTOrderingMenuPopup orderingMenuPopup = new TMTOrderingMenuPopup(driver);
        orderingMenuPopup.goTo(orderingMenuPopupTMT);
        Assert.assertTrue(orderingMenuPopup.checkResponseCode(driver, orderingMenuPopupTMT),"LOADING PAGE ERROR!");
        orderingMenuPopup.clickPickupDelivery(driver, pickupDelivery);
        String nowLater = input.get("now_later");
        System.out.println("now_later " + getCurrentCounter() + " is:" + nowLater);
        orderingMenuPopup.clickNowLater(driver, nowLater);
        String orderDateAndTimePopup = "";
        String orderDatePopup = "";
        if (nowLater.equals("later")) {
            orderDatePopup = orderingMenuPopup.getOrderDatePopup(driver);
            String orderTimePopup = orderingMenuPopup.getOrderTimePopup(driver);
            orderDateAndTimePopup = orderDatePopup + " at " + orderTimePopup;
        }
        orderingMenuPopup.clickStartOrder(driver);

        // TMTOrderingMenuPage
        TMTOrderingMenuPage tmtOrderingMenuPage = new TMTOrderingMenuPage(driver);
        Thread.sleep(1000);
        tmtOrderingMenuPage.clickMenuName(driver, menuName);
        tmtOrderingMenuPage.clickMenuItemName(driver, menuItemName_1, menuItemPrice_1);
        Thread.sleep(1000);
        tmtOrderingMenuPage.clickMenuItemName(driver, menuItemName_2, menuItemPrice_2);
        String subtotal = tmtOrderingMenuPage.getSubtotal(driver);
        String expectedSubtotal = countSubtotal(menuItemPrice_1, menuItemPrice_2);
        if (subtotal.equals(expectedSubtotal)) {
            System.out.println("The subtotals are equal!");
        } else {
            System.out.println("SUBTOTALS ARE NOT EQUAL!");
        }
        Assert.assertEquals(subtotal, expectedSubtotal, "The subtotals are not equal!");
        Thread.sleep(1000);
        tmtOrderingMenuPage.clickProceedToCheckout(driver);
        Thread.sleep(3000);
        
        // Checkout page
        tmtOrderingMenuPage.insertFirstName(driver, customersFirstName);
        tmtOrderingMenuPage.insertLastName(driver, customersLastName);
        tmtOrderingMenuPage.insertPhoneNumber(driver, customersPhoneNumber);
        Thread.sleep(1000);
        tmtOrderingMenuPage.insertEmail(driver, email);
       
        //tmtOrderingMenuPage.screenshotElement(
    	//		driver,
    	//		tmtOrderingMenuPage.insertEmail(driver, email),
    	//		local_github, 
    	//		currentTimeString, 
    	//		screenshot_files_path);
        
        if(pickupDelivery.equals("delivery")) {
        	tmtOrderingMenuPage.insertStreetAddress(driver,fullStreetAddress);
        	Thread.sleep(2000);
            tmtOrderingMenuPage.clickStreetAddres_1_FromList(driver);
        }
        String restaurantTip = input.get("restaurant_tip");
        tmtOrderingMenuPage.insertRestaurantTip(driver, restaurantTip);
        String discountPercentageAmountNone = input.get("discount_percentage_amount_none");
        String discountCode = input.get("discount_code");
        tmtOrderingMenuPage.insertDiscountCode(driver, discountPercentageAmountNone, discountCode);
        boolean errorMessageShown = tmtOrderingMenuPage.errorMessageExist(driver, discountPercentageAmountNone);
        String totalOrderCheckout = tmtOrderingMenuPage.getTotalOrderCheckout(driver);
        String totalOrderCheckoutTrimmed = totalOrderCheckout.substring(1);
        if (!discountPercentageAmountNone.equals("none")) {
            if (errorMessageShown) {
                System.out.println("VOUCHER IS NOT VALID!");
            } else {
                System.out.println("Voucher is valid");
            }
            Assert.assertEquals(errorMessageShown, false, "VOUCHER IS NOT VALID!");
        }
        tmtOrderingMenuPage.clickAddPayment(driver);
        Thread.sleep(3000);

        //TMTPaymentDetailsPage
        TMTPaymentDetailsPage tmtPaymentDetailsPage = new TMTPaymentDetailsPage(driver);
        tmtPaymentDetailsPage.insertStricterDataAddress(
                driver,
                creditCollect,
                stricterAuthorization,
                fullStreetAddress);
        //Thread.sleep(3000);
        tmtPaymentDetailsPage.insertStricterDataName(
                driver,
                creditCollect,
                stricterAuthorization,
                customersFirstName);
        //Thread.sleep(1000);
        tmtPaymentDetailsPage.insertStricterDataPhone(
                driver,
                creditCollect,
                stricterAuthorization,
                customersPhoneNumber);
        //Thread.sleep(1000);
        tmtPaymentDetailsPage.insertStricterDataEmail(
                driver,
                creditCollect,
                stricterAuthorization,
                email);
        //Thread.sleep(2000);
        if(creditCollect.equals("credit")) {
        	WebDriver iFrameDriver = tmtPaymentDetailsPage.switchToIFrame(driver);
        	Thread.sleep(7000);
        	tmtPaymentDetailsPage.insertCardNumber(iFrameDriver, creditCardNumber);
        	tmtPaymentDetailsPage.insertExpirationDate(iFrameDriver, expiryDate);
        	tmtPaymentDetailsPage.insertCvcNumber(iFrameDriver, cvcNumber);
        	if(stricterAuthorization.equals("FALSE")){tmtPaymentDetailsPage.insertPostalCode(iFrameDriver, postalCode);}
        	Thread.sleep(1000);
        	tmtPaymentDetailsPage.switchDefaultContent(iFrameDriver);
        	Thread.sleep(1000);
        	tmtPaymentDetailsPage.clickPayButton(driver);
        }
        
        
        
        
        // TMTReceivedOrderPopupPage
        String orderNumberReceivedString = tmtPaymentDetailsPage.getOrderNumberReceived(driver);
        setOrderNumber(orderNumberReceivedString);
        System.out.println("orderNumber is: " + orderNumber);
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        orderDetailsPage.goTo(orderDetailsURL);
        Thread.sleep(3000);
        String orderDetailsTimeAndDate=orderDetailsPage.getTimeAndDateFromOrderDetails(driver,nowLater);
        System.out.println("orderDetailsTimeAndDate is "+orderDetailsTimeAndDate);
        if (nowLater.equals("later")) {
            if (!orderDateAndTimePopup.equals(orderDetailsTimeAndDate)) {
            	System.out.println("orderDateAndTime is "+orderDateAndTimePopup);
            	System.out.println("orderDetailsTimeAndDate is "+orderDetailsTimeAndDate);
                System.out.println("TIME AND DATE ARE NOT EQUAL!");
            } else {
                System.out.println("time and date are equal!");
            }
            Assert.assertEquals(orderDateAndTimePopup, orderDetailsTimeAndDate, "TIME AND DATE ARE NOT EQUAL!");
        }
        String orderDetailsDate = "";
        if (nowLater.equals("now")) {
            orderDatePopup = getStringLocalDateTime();
            String formattedOrderDatePopup = orderDatePopup.substring(0, 10);
            int indexOfEnd = orderDetailsTimeAndDate.indexOf("at");
            orderDetailsDate = orderDetailsTimeAndDate.substring(0, indexOfEnd - 1);
            String formatedDetailsDate = orderDetailsPage.getFormatedDateFromDetails(driver, orderDetailsDate);
           
            if (!formattedOrderDatePopup.equals(formatedDetailsDate)) {
            	System.out.println("ORDER POPUP DATE " + formattedOrderDatePopup);
            	System.out.println("FORMATED ORDER DETAILS DATE " + formatedDetailsDate);
                System.out.println("TIME AND DATE ARE NOT EQUAL!");
            } else {
                System.out.println("time and date are equal!");
            }
        }
        String totalOrderDetails = orderDetailsPage.getOrderDetailsTotal(driver);
        totalOrderDetails = totalOrderDetails.substring(1);
        if (totalOrderCheckoutTrimmed.equals(totalOrderDetails)) {
            System.out.println("Totals are equal!");
        } else {
            System.out.println("TOTALS ARE NOT EQUAL!");
        }
        Assert.assertEquals(totalOrderCheckoutTrimmed, totalOrderDetails, "Totlas are not equal!");
        orderDetailsPage.clickDeleteInquiry(driver);
        orderDetailsPage.clickDeleteYesInquiry(driver);
        System.out.println("<<<<<<<<<<<<Order number " + currentCounter + " completed!<<<<<<<<<<<");
        // next spot
        
        driver.close();
    }

    @Test
    public void deleteMenus() throws IOException, InterruptedException {
    	System.out.println("***********Deleting started - Test is lounched from "+local_github);
    	CustomizeMenuPage customizeMenuPage = new CustomizeMenuPage(driver);
    	customizeMenuPage.goTo(menusURL);
    	Assert.assertTrue(customizeMenuPage.checkResponseCode(driver, menusURL),"LOADING PAGE ERROR!");
    	customizeMenuPage.clickCloseRecoveryPhoneButton(driver,production_staging);
    	customizeMenuPage.clickPencilButton(driver, menuName);
    	//customizeMenuPage.screenshotPage(
    	//		driver, 
    	//		local_github, 
    	//		currentTimeString, 
    	//		screenshot_files_path);
        //customizeMenuPage.goTo(deleteMenuURL);
        //Assert.assertTrue(customizeMenuPage.checkResponseCode(driver, deleteMenuURL),"LOADING PAGE ERROR!");
        Thread.sleep(2000);
        customizeMenuPage.clickConfigureSetHours(driver);
        customizeMenuPage.clickDeleteMenuTrash(driver, production_staging);
        customizeMenuPage.clickYesDeleteMenu(driver);
        System.out.println("menu " + menuName + " deleted ***********");
        driver.close();
    }


}//close class
