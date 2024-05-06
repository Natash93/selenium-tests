package org.shopping.listeners;

import org.openqa.selenium.WebDriver;
import org.shopping.utils.ScreenShotUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("=========================================================");
        System.out.println("Test " + result.getMethod().getMethodName() +  " started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test " + result.getMethod().getMethodName() +  " success");
        System.out.println("=========================================================");
    }

    @Override
    public void onTestFailure(ITestResult result) {
//        WebDriver driver;
        System.out.println("=========================================================");
        System.out.println("Test " + result.getMethod().getMethodName() +  " failed");
        System.out.println("=========================================================");
        //ScreenShotUtils.takeScreenshot(driver);
//        System.out.println(driver.getPageSource());
    }
}
