package com.lawsofmotion.testcase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.PopupHandler;


public class BaseClass {
    public WebDriver driver;
    public PopupHandler popupHandler;  // Wrapper instance handeled
    
    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://lawsofmotion.com/");
        
        // Initialize wrapper
        //popupHandler = new PopupHandler(driver);
        //System.out.println("PopupHandler initialized");
    }
    
    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}

