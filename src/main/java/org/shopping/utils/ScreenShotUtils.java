package org.shopping.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ScreenShotUtils {

    public static void takeScreenshot(WebDriver driver) {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        File screenshot = new File(date.getTime() + ".png");
        try {
            FileUtils.copyFile(file, screenshot);
        } catch (IOException e) {
            System.out.println("Screenshot wasn't saved");
        }
    }
}
