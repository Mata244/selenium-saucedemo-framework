package com.automation.saucedemo.config;

public final class AppUrls {

    private AppUrls() {
    }

    public static String cart() {
        return base() + "cart.html";
    }

    public static String checkoutStepOne() {
        return base() + "checkout-step-one.html";
    }

    public static String checkoutStepTwo() {
        return base() + "checkout-step-two.html";
    }

    public static String checkoutComplete() {
        return base() + "checkout-complete.html";
    }

    private static String base() {
        String baseUrl = ConfigReader.get("base.url");
        return baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
    }
}
