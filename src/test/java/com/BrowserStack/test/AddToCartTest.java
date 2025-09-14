package com.BrowserStack.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.BrowserStack.pages.CartPage;


public class AddToCartTest extends BaseTest {
    protected CartPage cartPage;

    @Test(priority = 2, dependsOnMethods = "com.BrowserStack.test.LoginTest.loginTest")
    public void addMultipleItemsAndRemoveTest() {
        cartPage = new CartPage(driver);

      
        cartPage.addItemToCart(0);
        cartPage.addItemToCart(1);
        cartPage.addItemToCart(2);   
        cartPage.goToCart();
        int itemCount = cartPage.getCartItemCount();
        Assert.assertEquals(itemCount, 3, "Expected 3 items in cart but found " + itemCount);    
        cartPage.removeItem(0);
        int updatedCount = cartPage.getCartItemCount();
        Assert.assertEquals(updatedCount, 2, "Expected 2 items after removal but found " + updatedCount);
        cartPage.proceedToCheckout();
      
    }
}
