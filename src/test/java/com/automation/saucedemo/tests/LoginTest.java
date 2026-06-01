package com.automation.saucedemo.tests;

import com.automation.saucedemo.config.TestDataReader;
import com.automation.saucedemo.pages.InventoryPage;
import com.automation.saucedemo.pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("SauceDemo")
@Feature("Login")
public class LoginTest extends BaseTest {

    @Test(description = "Valid login redirects to inventory page")
    @Description("standard_user can log in and see the product inventory")
    public void validLoginShowsInventory() {
        InventoryPage inventoryPage = new LoginPage()
                .open()
                .login(
                        TestDataReader.getUser("standard.username"),
                        TestDataReader.getUser("standard.password")
                );

        Assert.assertTrue(inventoryPage.isLoaded(), "Inventory page should be displayed after login");
    }

    @Test(description = "Invalid login shows error message")
    @Description("Wrong credentials display an error on the login page")
    public void invalidLoginShowsError() {
        LoginPage loginPage = new LoginPage().open();
        loginPage.submitCredentials(
                TestDataReader.getUser("invalid.username"),
                TestDataReader.getUser("invalid.password")
        );

        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message should be visible");
        Assert.assertTrue(
                loginPage.getErrorMessage().contains("do not match"),
                "Unexpected error message: " + loginPage.getErrorMessage()
        );
    }

    @Test(description = "Logout returns to login page")
    @Description("User can log out from inventory and return to login screen")
    public void logoutReturnsToLoginPage() {
        new LoginPage()
                .open()
                .login(
                        TestDataReader.getUser("standard.username"),
                        TestDataReader.getUser("standard.password")
                )
                .logout();

        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.isLoaded(), "Login page should be displayed after logout");
    }
}
