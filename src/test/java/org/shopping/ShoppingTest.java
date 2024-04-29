package org.shopping;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class ShoppingTest {
    private final static String PRODUCT_NAME_ELEMENT = "//div[contains(@class, \"csvtPz\")]";
    private final static String PRODUCT_NAME_ATTR = "alt";

    private WebDriver driver;

    @BeforeTest
    public void setup() {
        System.setProperty(Constants.WEB_DRIVER_NAME, Constants.WEB_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @BeforeMethod
    public void openSite() {
        driver.get(Constants.URL);
    }

    @Test
    public void addToCartTest() {
        WebElement firstProduct = driver.findElements(By.xpath(PRODUCT_NAME_ELEMENT)).get(0);
        String firstProductName = firstProduct.getAttribute(PRODUCT_NAME_ATTR);
        driver.findElements(By.cssSelector("button.jCsgpZ")).get(0).click();

        String cartFirstProductName = driver.findElement(By.cssSelector("div.hDmOrM img.jMMQhp")).getAttribute("alt");

        Assert.assertEquals(cartFirstProductName, firstProductName);
    }

    @Test
    public void addAllToCartTest() throws InterruptedException {
        List<String> allProductsNames = driver.findElements(By.xpath(PRODUCT_NAME_ELEMENT))
                .stream().map((p) -> p.getAttribute(PRODUCT_NAME_ATTR)).toList();

        WebElement closeButton = driver.findElement(By.cssSelector("button.gFkyvN"));

        for (WebElement button : driver.findElements(By.cssSelector("button.jCsgpZ"))) {
            button.click();
            Thread.sleep(350);
            closeButton.click();
            Thread.sleep(350);
        }

        driver.findElement(By.cssSelector("div.fMOJZp button")).click();

        List<String> cartAllProductsNames = driver.findElements(By.cssSelector("div.hDmOrM img.jMMQhp"))
                .stream().map((p) -> p.getAttribute("alt")).toList();

        Assert.assertEquals(cartAllProductsNames, allProductsNames);
    }

    @Test
    public void sizeFilter() throws InterruptedException {
        List<WebElement> allProducts = driver.findElements(By.xpath(PRODUCT_NAME_ELEMENT));

        driver.findElements(By.cssSelector(".hcyKTa")).get(0).click();
        Thread.sleep(350);

        List<WebElement> allFilteredProducts = driver.findElements(By.xpath(PRODUCT_NAME_ELEMENT));
        Assert.assertTrue(allProducts.size() > allFilteredProducts.size(), "All: " + allProducts.size() + ", filtered: " + allFilteredProducts.size());
    }

    @Test
    public void sizeFilterParseNumber() throws InterruptedException {
        driver.findElements(By.xpath(PRODUCT_NAME_ELEMENT));

        String countText = driver.findElement(By.cssSelector(".iliWeY p")).getText();
        int allProductsNumber = Integer.parseInt(countText.substring(0, countText.indexOf(" ")));

        driver.findElements(By.cssSelector(".hcyKTa")).get(0).click();
        Thread.sleep(500);

        String filteredCountText = driver.findElement(By.cssSelector(".iliWeY p")).getText();
        int filteredProductsNumber = Integer.parseInt(filteredCountText.substring(0, filteredCountText.indexOf(" ")));

        Assert.assertTrue(allProductsNumber > filteredProductsNumber, "All: " + allProductsNumber + ", filtered: " + filteredProductsNumber);
    }

    @AfterTest
    public void close() {
        driver.quit();
    }
}
