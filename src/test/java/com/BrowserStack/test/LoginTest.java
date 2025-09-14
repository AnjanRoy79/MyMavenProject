package com.BrowserStack.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.BrowserStack.pages.LoginPage;



public class LoginTest extends BaseTest {

    @Test(priority = 2)
    public void loginTest() {
     
        LoginPage loginPage = new LoginPage(driver);
        loginPage.selectUsername();
        loginPage.selectPassword();   
        loginPage.clickLogin();  
        Assert.assertTrue(driver.getCurrentUrl().contains("signin"),
                "Login did not succeed!");
      
    }

    @Test(priority = 1)
    public void invalidLoginTest() {
       
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLogin();
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid Username");
     
    }
}
