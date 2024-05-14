package org.shopping;

import org.openqa.selenium.WebDriver;
import org.shopping.listeners.TestListener;
import org.shopping.utils.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
    }

    @AfterMethod
    public void closeSession() {
        DriverManager.quitDriver();
    }
}
