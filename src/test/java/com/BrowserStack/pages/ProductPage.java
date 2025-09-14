package com.BrowserStack.pages;

import com.BrowserStack.Utils.ScreenshotUtils;
import com.BrowserStack.Utils.TestListener;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductPage {

    private WebDriver driver;

    // Locator all the product titles
    private By productTitles = By.cssSelector(".shelf-item__title");

    
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to list all product names
    public void listAllProducts() {
        List<WebElement> products = driver.findElements(productTitles);

        TestListener.getTestLogger().log(Status.INFO, "Listing all products available on the page:");
        int count = 1;
        for (WebElement product : products) {
            String name = product.getText();
            TestListener.getTestLogger().log(Status.INFO, count++ + ". " + name);
        }

        String screenshotPath = ScreenshotUtils.captureScreenshot(driver, "all_products_listed");
        try {
            TestListener.getTestLogger().addScreenCaptureFromPath(screenshotPath, "Product List Screenshot");
        } catch (Exception e) {
            e.printStackTrace();
        }

        TestListener.getTestLogger().log(Status.PASS, "All products listed successfully.");
    }
}
