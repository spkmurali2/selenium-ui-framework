package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginRedirectsToInventoryPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(loginPage.isOnInventoryPage(), "User was not redirected to inventory page");
    }

    @Test
    public void invalidPasswordShowsErrorMessage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "wrong_password");
        String actualError = loginPage.getErrorMessage();
        Assert.assertTrue(actualError.contains("Username and password do not match"),
                "Expected error message not shown");
    }
}
