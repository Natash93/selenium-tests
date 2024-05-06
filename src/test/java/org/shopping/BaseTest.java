package org.shopping;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.shopping.enums.Capability;
import org.shopping.listeners.ElementActionListener;
import org.shopping.listeners.TestListener;
import org.shopping.utils.DriverFactory;
import org.shopping.utils.PropertyReader;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.time.Duration;

@Listeners(TestListener.class)
public class BaseTest {
    protected WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = DriverFactory.createDriver(PropertyReader.getConfigProperty(Capability.BROWSER));

        EventFiringDecorator<WebDriver> decorator = new EventFiringDecorator(new ElementActionListener());
        driver = decorator.decorate(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterTest
    public void closeSession() {
        driver.quit();
    }
}
