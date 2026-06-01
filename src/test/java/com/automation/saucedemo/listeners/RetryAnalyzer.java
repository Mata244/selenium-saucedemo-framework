package com.automation.saucedemo.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static final int MAX_RETRIES = 2;
    private int attempt = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (attempt < MAX_RETRIES) {
            attempt++;
            return true;
        }
        return false;
    }
}
