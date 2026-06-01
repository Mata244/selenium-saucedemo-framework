package com.automation.saucedemo.listeners;

import com.automation.saucedemo.driver.DriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class TestListener implements ITestListener {

    private static final Logger LOG = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onTestFailure(ITestResult result) {
        attachScreenshot(result.getName());
        LOG.error("Test failed: {} - {}", result.getName(), result.getThrowable().getMessage());
    }

    private void attachScreenshot(String name) {
        try {
            WebDriver driver = DriverManager.getDriver();
            if (driver instanceof TakesScreenshot screenshotDriver) {
                byte[] screenshot = screenshotDriver.getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment(name + " - failure screenshot", "image/png",
                        new ByteArrayInputStream(screenshot), "png");
            }
        } catch (Exception e) {
            LOG.warn("Could not capture screenshot: {}", e.getMessage());
        }
    }
}
