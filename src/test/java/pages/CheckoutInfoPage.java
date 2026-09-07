package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object for checkout step one — customer info form
 * (first name, last name, zip/postal code).
 */
public class CheckoutInfoPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By zipCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");

    public CheckoutInfoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Fills the required checkout fields and proceeds to the order overview
    public void fillInfoAndContinue(String firstName, String lastName, String zip) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(zipCodeField).sendKeys(zip);
        driver.findElement(continueButton).click();
    }
}