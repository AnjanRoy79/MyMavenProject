package com.BrowserStack.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CartPage {
    private WebDriver driver;

    // Locators
    private By addItemButtons = By.xpath("//div[contains(@class,'shelf-item__buy-btn') and text()='Add to cart']");
    private By cartIcon = By.xpath("//*[@id=\"__next\"]/div/div/div[2]/div[2]/div[1]/span[1]/span");
    private By cartItems = By.cssSelector(".float-cart__shelf-container .shelf-item");
    private By checkoutButton = By.xpath("//*[@id=\"__next\"]/div/div/div[2]/div[2]/div[3]/div[3]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Add item by index
    public void addItemToCart(int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> addButtons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(addItemButtons));

        if (index < addButtons.size()) {
            WebElement addButton = addButtons.get(index);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addButton);
        } else {
            throw new IllegalArgumentException("No item available " + index);
        }
    }

    public void goToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
    }

    public int getCartItemCount() {
        List<WebElement> items = driver.findElements(cartItems);
        System.out.println("Cart items found = " + items.size());
        for (WebElement item : items) {
            System.out.println("Cart item -> " + item.getText());
        }
        return items.size();
    }

    public void removeItem(int index) {
        List<WebElement> items = driver.findElements(cartItems);
        if (index < items.size()) {
            WebElement removeBtn = items.get(index).findElement(By.cssSelector(".shelf-item__del"));
            removeBtn.click();
        } else {
            throw new IllegalArgumentException("No cart item at index " + index);
        }
    }

    public void proceedToCheckout() {
        driver.findElement(checkoutButton).click();
    }
}
