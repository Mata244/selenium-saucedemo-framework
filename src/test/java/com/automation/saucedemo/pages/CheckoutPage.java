package com.automation.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    private static final By FIRST_NAME = By.cssSelector("[data-test='firstName']");
    private static final By LAST_NAME = By.cssSelector("[data-test='lastName']");
    private static final By POSTAL_CODE = By.cssSelector("[data-test='postalCode']");
    private static final By CONTINUE_BUTTON = By.cssSelector("[data-test='continue']");
    private static final By FINISH_BUTTON = By.cssSelector("[data-test='finish']");
    private static final By COMPLETE_HEADER = By.cssSelector(".complete-header");
    private static final By OVERVIEW_CONTAINER = By.id("checkout_summary_container");

    public CheckoutPage fillShippingInfo(String firstName, String lastName, String postalCode) {
        waitForVisible(FIRST_NAME);
        type(FIRST_NAME, firstName);
        type(LAST_NAME, lastName);
        type(POSTAL_CODE, postalCode);
        jsClick(CONTINUE_BUTTON);
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("checkout-step-two"),
                ExpectedConditions.visibilityOfElementLocated(FINISH_BUTTON)
        ));
        return this;
    }

    public CheckoutPage finishOrder() {
        waitForVisible(FINISH_BUTTON);
        jsClick(FINISH_BUTTON);
        waitForVisible(COMPLETE_HEADER);
        return this;
    }

    public String getConfirmationHeader() {
        return getText(COMPLETE_HEADER);
    }

    public boolean isOverviewDisplayed() {
        return isDisplayed(OVERVIEW_CONTAINER);
    }
}
