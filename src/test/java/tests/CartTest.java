package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

/**
 * Covers cart behaviour: adding and removing an item, and verifying
 * the cart badge reflects the correct state at each step.
 */
public class CartTest extends BaseTest {

    @Test
    public void addingItemToCartUpdatesCartBadge() {
        // Cart features sit behind login, so every cart test logs in first
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addBackpackToCart();

        String actualCount = inventoryPage.getCartItemCount();
        Assert.assertEquals(actualCount, "1", "Cart badge did not update after adding item");
    }

    @Test
    public void removingItemClearsCartBadge() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addBackpackToCart();
        inventoryPage.removeBackpackFromCart();

        // Badge should disappear entirely once the cart is empty, not just show "0"
        Assert.assertFalse(inventoryPage.isCartBadgeVisible(), "Cart badge still visible after removing only item");
    }
}