package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Page Object for the Inventory (Products) page.
 * Handles cart actions, sorting, and logout — all actions available
 * to a logged-in user browsing the product catalog.
 */
public class InventoryPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators — kept private so tests never touch raw selectors directly
    private final By backpackAddToCartButton = By.id("add-to-cart-sauce-labs-backpack");
    private final By removeBackpackButton = By.id("remove-sauce-labs-backpack");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartIcon = By.className("shopping_cart_link");
    private final By sortDropdown = By.className("product_sort_container");
    private final By productPrices = By.className("inventory_item_price");
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Adds the Sauce Labs Backpack to the cart from the product listing
    public void addBackpackToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(backpackAddToCartButton)).click();
    }

    // Removes the same item directly from the inventory page (button toggles to "Remove" after adding)
    public void removeBackpackFromCart() {
        driver.findElement(removeBackpackButton).click();
    }

    // Returns the number shown on the cart badge, e.g. "1"
    public String getCartItemCount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).getText();
    }

    // Used to confirm the badge disappears entirely when the cart is empty
    public boolean isCartBadgeVisible() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(cartBadge));
            return false; // badge successfully disappeared within the wait time
        } catch (org.openqa.selenium.TimeoutException e) {
            return true; // badge never disappeared — still visible
        }
    }
    public void goToCart() {
        driver.findElement(cartIcon).click();
    }

    // Applies a sort option using the dropdown's visible text, e.g. "Price (low to high)"
    public void sortBy(String visibleText) {
        Select dropdown = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(sortDropdown)));
        dropdown.selectByVisibleText(visibleText);
    }

    // Reads all currently displayed prices, in DOM order, for sort-order verification
    public List<Double> getAllProductPrices() {
        return driver.findElements(productPrices).stream()
                .map(el -> Double.parseDouble(el.getText().replace("$", "")))
                .collect(Collectors.toList());
    }

    // Logout lives behind the hamburger menu — open it before clicking the link
    public void logout() {
        driver.findElement(menuButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }
}