package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

/**
 * End-to-end checkout journey: login -> add item -> cart -> checkout info
 * -> order confirmation. Chains multiple Page Objects to model a realistic
 * multi-step user flow, rather than testing each page in isolation.
 */
public class CheckoutTest extends BaseTest {

    @Test
    public void completeCheckoutShowsConfirmation() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addBackpackToCart();
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.goToCheckout();

        CheckoutInfoPage checkoutInfoPage = new CheckoutInfoPage(driver);
        checkoutInfoPage.fillInfoAndContinue("Pri", "Kumari", "D02");

        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        overviewPage.finishOrder();

        String confirmation = overviewPage.getConfirmationMessage();
        Assert.assertEquals(confirmation, "Thank you for your order!", "Checkout confirmation not shown");
    }
}