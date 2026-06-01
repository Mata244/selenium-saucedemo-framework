package com.automation.saucedemo.tests;

import com.automation.saucedemo.config.TestDataReader;
import com.automation.saucedemo.pages.CheckoutPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("SauceDemo")
@Feature("Checkout")
public class CheckoutTest extends LoggedInBaseTest {

    private CheckoutPage checkoutPage;

    @BeforeMethod(alwaysRun = true)
    public void prepareCartForCheckout() {
        checkoutPage = inventoryPage
                .addProductById(TestDataReader.getProduct("backpack.id"))
                .waitForCartBadgeCount(1)
                .openCart()
                .proceedToCheckout();
    }

    @Test(description = "Checkout happy path completes order")
    @Description("User can fill shipping info and complete purchase with confirmation")
    public void checkoutHappyPathCompletesOrder() {
        checkoutPage
                .fillShippingInfo("John", "Doe", "12345")
                .finishOrder();

        Assert.assertEquals(
                checkoutPage.getConfirmationHeader(),
                "Thank you for your order!",
                "Order confirmation message should be displayed"
        );
    }
}
