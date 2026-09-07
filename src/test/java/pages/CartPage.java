package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object for the Cart page — the step between the product
 * listing and the checkout flow.
 */
public class CartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By checkoutButton = By.id("checkout");
    private final By cartItemName = By.className("inventory_item_name");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Confirms the correct item made it into the cart before checkout
    public String getFirstItemName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartItemName)).getText();
    }

    public void goToCheckout() {
        driver.findElement(checkoutButton).click();
    }
}