package com.lawsofmotion.pageobject;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class fitQuizPage {
	WebDriver driver;
	//private PopupHandler popupHandler;

	public fitQuizPage(WebDriver driver)
	{
		this.driver= driver;
		//this.popupHandler= new PopupHandler(driver); // wrapper per pge
		PageFactory.initElements(driver,this);
	}
	

	@FindBy(xpath ="//a[@href=\"/account?view=your-fit\" and text()=\"Account\"]") WebElement myAccount;
	public void clickOnAccount() {
		myAccount.click();
	}
	@FindBy(xpath = "//span[text()=\"Find my Fit\"]") WebElement myFitBtn;
	//	@FindBy(css="span.button__el") WebElement myFitBtn;
	public void clickMyFitBtn() {
		myFitBtn.click();
	}
	@FindBy(id = "fitquizbtn") WebElement fitQuizBtn;
	public void clickFitQuizBtn() {
		fitQuizBtn.click();
	}
	@FindBy(xpath = "//input[@name=\"name\" and @required]")
	public WebElement myNameIsField;

	//  @FindBy(xpath= "//button[@class="btn btn--next" and text()="Next "]") WebElement nextBtn;
	@FindBy(css= "button.btn.btn--next")
	public WebElement nextButton;

	@FindBy(css = ".btn--next:not([disabled])")
	public WebElement nextButtonDisabled;

	public void enterName(String name) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(myNameIsField));
		myNameIsField.clear();
		myNameIsField.sendKeys(name);

		// Trigger input event + close popup
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", myNameIsField);
		closeKlaviyoPopup();
	}

	public void clickOnNextBtn() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
	}

	public void closeKlaviyoPopup() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
			wait.until(ExpectedConditions.elementToBeClickable(
					By.cssSelector("button[aria-label='Close dialog']"))).click();
		} catch (Exception ignored) {
			}
		}
		
		@FindBy(name = "feet")
		public WebElement feetSelect;
		
		@FindBy(name = "inches")
		public WebElement inchesSelect;
		
		// Height Selection
	    public void selectHeight(String feet, String inches) {
	        new Select(feetSelect).selectByValue(feet);
	        new Select(inchesSelect).selectByValue(inches);
	        //clickOnNextBtn();
	    }

	 // Weight Selection
	    @FindBy(name = "weight")
		public WebElement weightSelect;
	    
	    public void selectWeight(String weightRange) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOf(weightSelect));
	        new Select(weightSelect).selectByVisibleText(weightRange);
	        //clickOnNextBtn();
	    }
	}

