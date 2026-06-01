package com.automation.saucedemo.pages;

import org.openqa.selenium.By;

public class CheckoutPage extends BasePage {

    private static final By FIRST_NAME = By.cssSelector("[data-test='firstName']");
    private static final By LAST_NAME = By.cssSelector("[data-test='lastName']");
    private static final By POSTAL_CODE = By.cssSelector("[data-test='postalCode']");
    private static final By CONTINUE_BUTTON = By.cssSelector("[data-test='continue']");
    private static final By FINISH_BUTTON = By.cssSelector("[data-test='finish']");
    private static final By COMPLETE_HEADER = By.cssSelector(".complete-header");
    private static final By OVERVIEW_CONTAINER = By.id("checkout_summary_container");

    public CheckoutPage fillShippingInfo(String firstName, String lastName, String postalCode) {
        type(FIRST_NAME, firstName);
        type(LAST_NAME, lastName);
        type(POSTAL_CODE, postalCode);
        click(CONTINUE_BUTTON);
        return this;
    }

    public CheckoutPage finishOrder() {
        waitForVisible(OVERVIEW_CONTAINER);
        click(FINISH_BUTTON);
        return this;
    }

    public String getConfirmationHeader() {
        return getText(COMPLETE_HEADER);
    }

    public boolean isOverviewDisplayed() {
        return isDisplayed(OVERVIEW_CONTAINER);
    }
}
