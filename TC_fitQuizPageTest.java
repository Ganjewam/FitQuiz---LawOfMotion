package com.lawsofmotion.testcase;

import java.time.Duration;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lawsofmotion.pageobject.fitQuizPage;

import org.openqa.selenium.support.ui.WebDriverWait;

public class TC_fitQuizPageTest extends BaseClass {
	fitQuizPage fqp;

	@Test(priority = 0, groups = "smoke")
	public void verifyFitQuizPageTest1() {
		try {
			fqp = new fitQuizPage(driver);  // Local object

			// Popup handling
			try {
				fqp.closeKlaviyoPopup();
				System.out.println("Klaviyo popup closed");
			} catch (Exception popupEx) {
				System.out.println("No popup found - continuing");
			}

			// Main test flow
			fqp.clickMyFitBtn();
			fqp.clickFitQuizBtn();
			fqp.enterName("kalpana");
			fqp.clickOnNextBtn();      

			// Extra popup cleanup
			try { 
				fqp.closeKlaviyoPopup(); 
			} catch(Exception ignored) {}


			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(d -> fqp.feetSelect.isDisplayed());
			Assert.assertTrue(fqp.feetSelect.isDisplayed(), "Height selection visible");

			System.out.println("FitQuiz test PASSED - Height selection reached!");

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("FitQuiz test failed: " + e.getMessage());
		}
	}

	// Test 2: Name field validation
	@Test(priority = 1, groups = "validation")
	public void testNameFieldValidation() {
		fqp = new fitQuizPage(driver);
		fqp.closeKlaviyoPopup();
		fqp.clickMyFitBtn();
		fqp.clickFitQuizBtn();

		// Empty name - Next disabled
		fqp.myNameIsField.clear();
		Assert.assertFalse(fqp.nextButton.isEnabled(), "Next should be disabled for empty name");

		// Valid name - Next enabled
		fqp.enterName("kalpana");
		Assert.assertTrue(fqp.nextButton.isEnabled(), "Next should be enabled with valid name");
	}


	// Test 3:
	@Test(priority = 2, groups = "smoke")
	public void verifyFitQuizPageTest3() {
		try {
			fqp = new fitQuizPage(driver);

			// Popup handling
			try { fqp.closeKlaviyoPopup(); System.out.println("Klaviyo popup closed"); } 
			catch (Exception popupEx) { System.out.println("No popup found - continuing"); }

			// Main test flow
			fqp.clickMyFitBtn();
			fqp.clickFitQuizBtn();
			fqp.enterName("kalpana");
			fqp.clickOnNextBtn();

			// Height selection
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(d -> fqp.feetSelect.isDisplayed());
			Assert.assertTrue(fqp.feetSelect.isDisplayed(), "Height selection visible");
			fqp.selectHeight("5", "6");
			System.out.println("Height selection done!");

			// Weight selection
			fqp.selectWeight("140-145");
			System.out.println("Weight selection PASSED");

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("FitQuiz test failed: " + e.getMessage());
		}
	}

	//Test4:  Validation of page title
	@Test(priority = 3, groups = "validation") 
	public void testPageSourceAndTitle() {
	    try {
	        fqp = new fitQuizPage(driver);
	        fqp.clickMyFitBtn();
	        //fqp.clickFitQuizBtn();
	        
	        // Verify page title
	        String expectedTitle = "Fit Quiz | Laws of Motion";
	        Assert.assertEquals(driver.getTitle(), expectedTitle, "Page title mismatch");
	        
	        
	        System.out.println("Page title validated");
	    } catch (Exception e) {
	        Assert.fail("Page validation failed: " + e.getMessage());
	    }
	}
	// Test 5:
	@DataProvider(name = "heightData")
	public Object[][] getHeightData() {
	    return new Object[][] {
	        {"5", "6"},
	        {"4", "11"},
	        {"6", "0"}
	    };
	}
	
	@Test(priority = 4, dataProvider = "heightData", groups = "smoke")
	public void verifyFitQuizPageTest3(String feet, String inches) {
		try {
			fqp = new fitQuizPage(driver);

			// Popup handling
			try { 
				fqp.closeKlaviyoPopup(); 
				System.out.println("Klaviyo popup closed");
			} catch (Exception popupEx) { 
				System.out.println("No popup - continuing"); 
			}

			// Navigate to height screen
			fqp.clickMyFitBtn();
			fqp.clickFitQuizBtn();
			fqp.enterName("kalpana");
			fqp.clickOnNextBtn();

			// DATA-DRIVEN HEIGHT SELECTION
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(d -> fqp.feetSelect.isDisplayed());
			Assert.assertTrue(fqp.feetSelect.isDisplayed(), "Height selection visible");

			// Select height from DataProvider
			fqp.selectHeight(feet, inches);
			System.out.println("Height selection PASSED: " + feet + "' " + inches + "");

			// Weight selection (common for all)
			fqp.selectWeight("140-145");
			System.out.println("Weight selection PASSED");

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Height test failed for " + feet + "' " + inches + ": " + e.getMessage());
		}
	}
}

