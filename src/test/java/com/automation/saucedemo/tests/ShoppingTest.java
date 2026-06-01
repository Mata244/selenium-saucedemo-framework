package com.automation.saucedemo.tests;

import com.automation.saucedemo.config.TestDataReader;
import com.automation.saucedemo.pages.CartPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Epic("SauceDemo")
@Feature("Shopping Cart")
public class ShoppingTest extends LoggedInBaseTest {

    @Test(description = "Adding one product updates cart badge to 1")
    @Description("Cart badge shows correct count after adding a single item")
    public void addOneProductUpdatesCartBadge() {
        inventoryPage
                .addProductById(TestDataReader.getProduct("backpack.id"))
                .waitForCartBadgeCount(1);

        Assert.assertEquals(inventoryPage.getCartBadgeCount(), 1, "Cart badge should show 1 item");
    }

    @Test(description = "Adding two products updates cart badge to 2")
    @Description("Cart badge shows correct count after adding two items")
    public void addTwoProductsUpdatesCartBadge() {
        inventoryPage
                .addProductById(TestDataReader.getProduct("backpack.id"))
                .addProductById(TestDataReader.getProduct("bike.light.id"))
                .waitForCartBadgeCount(2);

        Assert.assertEquals(inventoryPage.getCartBadgeCount(), 2, "Cart badge should show 2 items");
    }

    @Test(description = "Cart page lists added products")
    @Description("Cart contains the products that were added from inventory")
    public void cartShowsAddedProducts() {
        inventoryPage
                .addProductById(TestDataReader.getProduct("backpack.id"))
                .addProductById(TestDataReader.getProduct("bike.light.id"));

        CartPage cartPage = inventoryPage.openCart();

        Assert.assertTrue(cartPage.isLoaded(), "Cart page should be loaded");
        List<String> cartItems = cartPage.getProductNames();
        Assert.assertEquals(cartItems.size(), 2, "Cart should contain 2 items");
        Assert.assertTrue(
                cartItems.contains(TestDataReader.getProduct("backpack.name")),
                "Cart should contain backpack"
        );
        Assert.assertTrue(
                cartItems.contains(TestDataReader.getProduct("bike.light.name")),
                "Cart should contain bike light"
        );
    }
}
