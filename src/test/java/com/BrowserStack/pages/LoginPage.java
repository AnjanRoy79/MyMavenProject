package com.BrowserStack.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    // Locators
    private By usernameDropdown = By.xpath("(//*[name()='svg'])[2]");
    private By selectUsername = By.xpath("//div[text()='demouser']");
    private By passwordDropdown = By.xpath("(//*[name()='svg'])[3]");
    private By selectPassword = By.xpath("//div[text()='testingisfun99']");
    private By loginButton = By.id("login-btn");
    private By errorMessage = By.xpath("//*[@id=\"__next\"]/div[2]/div/form/div[2]/h3");


    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions for predefined dropdown selection
    public void selectUsername() {
        driver.findElement(usernameDropdown).click();
        driver.findElement(selectUsername).click();
    }

    public void selectPassword() {
        driver.findElement(passwordDropdown).click();
        driver.findElement(selectPassword).click();
    }



    public void enterPassword(String password) {
        driver.findElement(passwordDropdown).click();
        
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
