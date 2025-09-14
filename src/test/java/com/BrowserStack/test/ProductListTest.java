package com.BrowserStack.test;

import com.BrowserStack.pages.ProductPage;
import org.testng.annotations.Test;

public class ProductListTest extends BaseTest {

    @Test(priority = 3, dependsOnMethods = "com.BrowserStack.test.LoginTest.loginTest")
    public void listAllProductsTest() {
        ProductPage productPage = new ProductPage(driver);
        productPage.listAllProducts();
    }
}
