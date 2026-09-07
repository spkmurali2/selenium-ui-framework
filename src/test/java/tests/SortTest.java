package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

import java.util.Comparator;
import java.util.List;

/**
 * Verifies the product sort dropdown actually reorders the catalog
 * correctly, rather than just checking the dropdown selection itself.
 */
public class SortTest extends BaseTest {

    @Test
    public void sortingByPriceLowToHighOrdersCorrectly() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.sortBy("Price (low to high)");

        List<Double> prices = inventoryPage.getAllProductPrices();

        // Compare actual order against a sorted copy of itself, rather than
        // hardcoding expected prices — keeps the test valid even if the
        // product catalog changes later
        List<Double> sortedCopy = prices.stream().sorted(Comparator.naturalOrder()).toList();

        Assert.assertEquals(prices, sortedCopy, "Products were not sorted correctly by price ascending");
    }
}