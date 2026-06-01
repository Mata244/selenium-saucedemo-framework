package com.automation.saucedemo.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class TestDataReader {

    private static final Properties USERS = loadProperties("testdata/users.properties");
    private static final Properties PRODUCTS = loadProperties("testdata/products.properties");

    private TestDataReader() {
    }

    private static Properties loadProperties(String resourcePath) {
        Properties properties = new Properties();
        try (InputStream input = TestDataReader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (input == null) {
                throw new IllegalStateException(resourcePath + " not found on classpath");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load " + resourcePath, e);
        }
        return properties;
    }

    public static String getUser(String key) {
        return USERS.getProperty(key);
    }

    public static String getProduct(String key) {
        return PRODUCTS.getProperty(key);
    }
}
