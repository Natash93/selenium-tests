package org.shopping.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import java.util.List;

public class ElementActionListener implements WebDriverListener {
    private static final Logger LOGGER = LogManager.getLogger(Constants.LISTENER_LOGGER_NAME);
    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        LOGGER.info(String.valueOf(keysToSend) + "was typed into " + element.toString());
    }

    @Override
    public void afterClick(WebElement element) {
        LOGGER.info(element.getTagName() + " was clicked");
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        LOGGER.info ("Loaded url:" + url);
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        LOGGER.info("Found by " + locator.toString() + " found elements: " + (result != null));
    }

    @Override
    public void afterFindElements(WebDriver driver, By locator, List<WebElement> result) {
        LOGGER.info("Found by " + locator.toString() + " found elements: " + result.size());
    }

    @Override
    public void afterGetText(WebElement element, String result) {
        LOGGER.info("Get text from " + element.getTagName() + ": '" + result + "'");
    }
}
