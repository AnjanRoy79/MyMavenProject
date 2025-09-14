package com.BrowserStack.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.BrowserStack.pages.CartPage;
import com.BrowserStack.pages.CheckoutPage;


public class CheckoutTest extends BaseTest {

    @Test(dependsOnMethods = "addMultipleItemsAndRemoveTest")
    public void completeCheckoutTest() {
   

        CartPage cartPage = new CartPage(driver);
        cartPage.proceedToCheckout();
    

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterShippingDetails(
                "Anjan", "Roy", "188/4, Picnic Garden Road", "Kolkata", "700039"
        );
  

        checkoutPage.continueCheckout();
        String actualMessage = checkoutPage.getSuccessMessage();
        Assert.assertTrue(actualMessage.contains("successfully"),
                "Checkout success message mismatch. " + actualMessage);
    

        checkoutPage.downloadInvoice();    
        checkoutPage.continueShopping();
        checkoutPage.logout();
   
    }
}
