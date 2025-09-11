package com.OrangeHRM;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class OrangeHRMPage {
    WebDriver driver;
    WebDriverWait wait;

    // Login using data from excel and click submit
    
    @FindBy(name = "username") WebElement usernameField;
    @FindBy(name = "password") WebElement passwordField;
    @FindBy(xpath = "//button[@type='submit']") WebElement loginButton;
    
      
    // Successful login and then clicking on links as required in SRC and logout.
    
    @FindBy(xpath = "//a[@href='/web/index.php/admin/viewAdminModule']") WebElement adminMenu;
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[1]") WebElement userManagement;
    @FindBy(xpath = "//a[text()='Users']")WebElement usersLink;
    @FindBy(xpath = "//span[@class='oxd-userdropdown-tab']")WebElement profileDropdown;
    @FindBy(xpath = "//a[text()='Logout']")WebElement logoutButton;
 

    public OrangeHRMPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void login(String uname, String pwd) throws InterruptedException {
        
        usernameField.sendKeys(uname);
        passwordField.sendKeys(pwd);
        loginButton.click();
        Thread.sleep(1000);
    }
 
    public void goToUsers() throws InterruptedException{
        
    	adminMenu.click();
        userManagement.click();
        usersLink.click();
    }

 
    public void logout() {
        profileDropdown.click();
        logoutButton.click();
    }
}
