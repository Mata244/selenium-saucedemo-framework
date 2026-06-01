package com.automation.saucedemo.tests;

import com.automation.saucedemo.config.TestDataReader;
import com.automation.saucedemo.pages.InventoryPage;
import com.automation.saucedemo.pages.LoginPage;
import org.testng.annotations.BeforeMethod;

public abstract class LoggedInBaseTest extends BaseTest {

    protected InventoryPage inventoryPage;

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        inventoryPage = new LoginPage()
                .open()
                .login(
                        TestDataReader.getUser("standard.username"),
                        TestDataReader.getUser("standard.password")
                );
    }
}
