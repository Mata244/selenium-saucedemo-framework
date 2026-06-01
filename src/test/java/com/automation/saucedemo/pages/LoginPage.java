package com.automation.saucedemo.pages;

import com.automation.saucedemo.config.ConfigReader;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private static final By USERNAME = By.cssSelector("[data-test='username']");
    private static final By PASSWORD = By.cssSelector("[data-test='password']");
    private static final By LOGIN_BUTTON = By.cssSelector("[data-test='login-button']");
    private static final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");

    public LoginPage open() {
        driver.get(ConfigReader.get("base.url"));
        return this;
    }

    public void submitCredentials(String username, String password) {
        type(USERNAME, username);
        type(PASSWORD, password);
        click(LOGIN_BUTTON);
    }

    public InventoryPage login(String username, String password) {
        submitCredentials(username, password);
        return new InventoryPage().waitForPageLoad();
    }

    public String getErrorMessage() {
        return getText(ERROR_MESSAGE);
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(ERROR_MESSAGE);
    }

    public boolean isLoaded() {
        return isDisplayed(USERNAME) && isDisplayed(LOGIN_BUTTON);
    }
}
