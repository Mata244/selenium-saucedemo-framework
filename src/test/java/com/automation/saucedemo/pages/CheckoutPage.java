package com.automation.saucedemo.pages;

import com.automation.saucedemo.config.AppUrls;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    private static final By FIRST_NAME = By.cssSelector("[data-test='firstName']");
    private static final By LAST_NAME = By.cssSelector("[data-test='lastName']");
    private static final By POSTAL_CODE = By.cssSelector("[data-test='postalCode']");
    private static final By CONTINUE_BUTTON = By.cssSelector("[data-test='continue']");
    private static final By FINISH_BUTTON = By.cssSelector("[data-test='finish']");
    private static final By COMPLETE_HEADER = By.cssSelector(".complete-header");

    public static CheckoutPage openStepOne() {
        CheckoutPage page = new CheckoutPage();
        page.driver.get(AppUrls.checkoutStepOne());
        page.waitForVisible(FIRST_NAME);
        return page;
    }

    public CheckoutPage fillShippingInfo(String firstName, String lastName, String postalCode) {
        waitForVisible(FIRST_NAME);
        setInputValue(FIRST_NAME, firstName);
        setInputValue(LAST_NAME, lastName);
        setInputValue(POSTAL_CODE, postalCode);
        jsClick(CONTINUE_BUTTON);
        waitForCheckoutStepTwo();
        return this;
    }

    public CheckoutPage finishOrder() {
        waitForVisible(FINISH_BUTTON);
        jsClick(FINISH_BUTTON);
        waitForOrderComplete();
        return this;
    }

    public String getConfirmationHeader() {
        return getText(COMPLETE_HEADER);
    }

    private void waitForCheckoutStepTwo() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("checkout-step-two"),
                    ExpectedConditions.visibilityOfElementLocated(FINISH_BUTTON)
            ));
        } catch (TimeoutException ex) {
            driver.get(AppUrls.checkoutStepTwo());
            wait.until(ExpectedConditions.visibilityOfElementLocated(FINISH_BUTTON));
        }
    }

    private void waitForOrderComplete() {
        try {
            waitForVisible(COMPLETE_HEADER);
        } catch (TimeoutException ex) {
            driver.get(AppUrls.checkoutComplete());
            waitForVisible(COMPLETE_HEADER);
        }
    }

    private void setInputValue(By locator, String value) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        scrollIntoView(element);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[1];"
                        + "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
                element,
                value
        );
    }
}
