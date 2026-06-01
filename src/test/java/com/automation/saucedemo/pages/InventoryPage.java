package com.automation.saucedemo.pages;

import com.automation.saucedemo.config.ConfigReader;
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
        By addToCart = By.cssSelector("[data-test='add-to-cart-" + productId + "']");
        By removeFromCart = By.cssSelector("[data-test='remove-" + productId + "']");
        click(addToCart);
        wait.until(ExpectedConditions.visibilityOfElementLocated(removeFromCart));
        return this;
    }

    public int getCartBadgeCount() {
        List<WebElement> badges = driver.findElements(CART_BADGE);
        if (badges.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(badges.getFirst().getText());
    }

    public InventoryPage waitForCartBadgeCount(int expectedCount) {
        wait.until(driver -> getCartBadgeCount() == expectedCount);
        return this;
    }

    public CartPage openCart() {
        click(SHOPPING_CART_LINK);
        return new CartPage().waitForPageLoad();
    }

    public LoginPage logout() {
        click(MENU_BUTTON);
        wait.until(ExpectedConditions.elementToBeClickable(LOGOUT_LINK)).click();
        wait.until(ExpectedConditions.urlToBe(ConfigReader.get("base.url")));
        return new LoginPage().waitForPageLoad();
    }

    public List<String> getProductNames() {
        return waitForAllVisible(INVENTORY_ITEM_NAME).stream()
                .map(WebElement::getText)
                .toList();
    }
}
