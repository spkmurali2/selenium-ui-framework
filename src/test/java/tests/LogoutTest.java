package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

/**
 * Confirms logout correctly ends the session and returns the user
 * to the login page.
 */
public class LogoutTest extends BaseTest {

    @Test
    public void logoutRedirectsToLoginPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.logout();

        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/"),
                "User was not redirected to login page after logout");
    }
}