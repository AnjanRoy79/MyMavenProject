package com.BrowserStack.test;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.BrowserStack.Utils.WebDriverFactory;

import com.aventstack.extentreports.ExtentTest;


public class BaseTest {
    public static WebDriver driver;
    protected ExtentTest testLogger;   
    
    @BeforeSuite
    public void setup() {
        driver = WebDriverFactory.getDriver();
        driver.get("https://bstackdemo.com/signin");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterSuite
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
