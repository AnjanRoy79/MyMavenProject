package com.BrowserStack.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By firstNameField = By.id("firstNameInput");
    private By lastNameField = By.id("lastNameInput");
    private By addressField = By.id("addressLine1Input");
    private By stateField = By.id("provinceInput");
    private By postalCodeField = By.id("postCodeInput");
    private By continueButton = By.id("checkout-shipping-continue");
    private By successMessage = By.id("confirmation-message");
    private By download= By.xpath("//*[@id=\"downloadpdf\"]");
    private By continueShopping = By.xpath("//*[@id=\"__next\"]/div[1]/div/h2/a");
    private By logout = By.xpath("//*[@id=\"signin\"]");

    
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Details of shipping
    public void enterShippingDetails(String firstName, String lastName,
                                     String address, String state, String postalCode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(stateField).sendKeys(state);
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }

    public void continueCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
        
    }
    
    public void downloadInvoice() {
		wait.until(ExpectedConditions.elementToBeClickable(download)).click();
	}
    
    public void continueShopping() {
		wait.until(ExpectedConditions.elementToBeClickable(continueShopping)).click();
	}
    
    public void logout() {
		wait.until(ExpectedConditions.elementToBeClickable(logout)).click();
	}
}
