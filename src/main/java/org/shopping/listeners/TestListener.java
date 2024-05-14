package org.shopping.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static final Logger LOGGER = LogManager.getLogger(Constants.LISTENER_LOGGER_NAME);

    @Override
    public void onTestStart(ITestResult result) {
        LOGGER.info("=========================================================");
        LOGGER.info("Test " + result.getMethod().getMethodName() +  " started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOGGER.info("Test " + result.getMethod().getMethodName() +  " success");
        LOGGER.info("=========================================================");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.info("=========================================================");
        LOGGER.info("Test " + result.getMethod().getMethodName() +  " failed");
        LOGGER.info("=========================================================");
    }
}
