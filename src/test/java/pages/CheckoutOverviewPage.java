package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object for the final checkout step — order summary and
 * order confirmation.
 */
public class CheckoutOverviewPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By finishButton = By.id("finish");
    private final By confirmationHeader = By.className("complete-header");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void finishOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }

    // Returns the "Thank you for your order!" message shown after a successful checkout
    public String getConfirmationMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationHeader)).getText();
    }
}