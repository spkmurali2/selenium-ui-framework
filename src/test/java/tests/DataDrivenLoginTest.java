package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

/**
 * Runs the same negative-login check against multiple credential
 * combinations using a TestNG DataProvider, instead of writing a
 * near-duplicate test method per scenario.
 */
public class DataDrivenLoginTest extends BaseTest {

    // Each row: username, password, expected error message substring
    @DataProvider(name = "loginData")
    public Object[][] loginCredentials() {
        return new Object[][] {
                { "standard_user", "wrong_password", "Username and password do not match" },
                { "locked_out_user", "secret_sauce", "Sorry, this user has been locked out" },
                { "", "secret_sauce", "Username is required" },
                { "standard_user", "", "Password is required" }
        };
    }

    @Test(dataProvider = "loginData")
    public void invalidLoginShowsCorrectError(String username, String password, String expectedError) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        String actualError = loginPage.getErrorMessage();
        Assert.assertTrue(actualError.contains(expectedError),
                "Expected error '" + expectedError + "' but got '" + actualError + "'");
    }
}