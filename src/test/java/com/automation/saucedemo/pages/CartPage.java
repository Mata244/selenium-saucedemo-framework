package com.automation.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends BasePage {

    private static final By CART_LIST = By.cssSelector(".cart_list");
    private static final By CART_ITEM_NAMES = By.cssSelector(".inventory_item_name");
    private static final By CHECKOUT_BUTTON = By.cssSelector("[data-test='checkout']");
    private static final By CONTINUE_SHOPPING = By.cssSelector("[data-test='continue-shopping']");

    public boolean isLoaded() {
        return isDisplayed(CART_LIST);
    }

    public CartPage waitForPageLoad() {
        wait.until(ExpectedConditions.urlContains("cart.html"));
        waitForVisible(CART_LIST);
        waitForVisible(CHECKOUT_BUTTON);
        return this;
    }

    public List<String> getProductNames() {
        return waitForAllVisible(CART_ITEM_NAMES).stream()
                .map(WebElement::getText)
                .toList();
    }

    public CheckoutPage proceedToCheckout() {
        click(CHECKOUT_BUTTON);
        return new CheckoutPage();
    }

    public InventoryPage continueShopping() {
        click(CONTINUE_SHOPPING);
        return new InventoryPage();
    }
}
