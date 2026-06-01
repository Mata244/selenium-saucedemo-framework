package com.automation.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class InventoryPage extends BasePage {

    private static final By INVENTORY_CONTAINER = By.id("inventory_container");
    private static final By CART_BADGE = By.cssSelector(".shopping_cart_badge");
    private static final By SHOPPING_CART_LINK = By.cssSelector(".shopping_cart_link");
    private static final By MENU_BUTTON = By.id("react-burger-menu-btn");
    private static final By LOGOUT_LINK = By.cssSelector("[data-test='logout-sidebar-link']");
    private static final By INVENTORY_ITEM_NAME = By.cssSelector(".inventory_item_name");

    public boolean isLoaded() {
        return isDisplayed(INVENTORY_CONTAINER);
    }

    public InventoryPage waitForPageLoad() {
        waitForVisible(INVENTORY_CONTAINER);
        return this;
    }

    public InventoryPage addProductById(String productId) {
        click(By.cssSelector("[data-test='add-to-cart-" + productId + "']"));
        return this;
    }

    public int getCartBadgeCount() {
        List<WebElement> badges = driver.findElements(CART_BADGE);
        if (badges.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(badges.getFirst().getText());
    }

    public CartPage openCart() {
        click(SHOPPING_CART_LINK);
        return new CartPage().waitForPageLoad();
    }

    public LoginPage logout() {
        click(MENU_BUTTON);
        wait.until(ExpectedConditions.elementToBeClickable(LOGOUT_LINK)).click();
        return new LoginPage();
    }

    public List<String> getProductNames() {
        return waitForAllVisible(INVENTORY_ITEM_NAME).stream()
                .map(WebElement::getText)
                .toList();
    }
}
