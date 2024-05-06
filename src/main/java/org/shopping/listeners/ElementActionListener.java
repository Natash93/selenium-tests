package org.shopping.listeners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import java.util.List;

public class ElementActionListener implements WebDriverListener {
    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        System.out.println(String.valueOf(keysToSend) + "was typed into " + element.toString());
    }

    @Override
    public void afterClick(WebElement element) {
        System.out.println(element.getTagName() + " was clicked");
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        System.out.println ("Loaded url:" + url);
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        System.out.println("Found by " + locator.toString() + " found elements: " + (result != null));
    }

    @Override
    public void afterFindElements(WebDriver driver, By locator, List<WebElement> result) {
        System.out.println("Found by " + locator.toString() + " found elements: " + result.size());
    }

    @Override
    public void afterGetText(WebElement element, String result) {
        System.out.println("Get text from " + element.getTagName() + ": '" + result + "'");
    }
}
