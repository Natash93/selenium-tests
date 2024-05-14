package org.shopping;

import org.shopping.pages.ShoppingMainPage;
import org.shopping.utils.ExcelDataProvider;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ShoppingTest extends BaseTest {
    @Test
    public void addToCartTest() {
        ShoppingMainPage page = new ShoppingMainPage(driver);
        page.openUrl();

        String firstProductName = page.getProductsNames().get(0);
        page.addFirstProductToCart();
        String cartFirstProductName = page.getCartProductsNames().get(0);

        Assert.assertEquals(cartFirstProductName, firstProductName);
    }

    @Test
    public void addAllToCartTest() {
        ShoppingMainPage page = new ShoppingMainPage(driver);
        page.openUrl();

        List<String> allProductsNames = page.getProductsNames();
        page.addAllProductsToCart();

        List<String> cartAllProductsNames = page.getCartProductsNames();

        Assert.assertEquals(cartAllProductsNames, allProductsNames);
    }

    @Test
    public void sizeFilter() throws InterruptedException {
        ShoppingMainPage page = new ShoppingMainPage(driver);
        page.openUrl();

        List<String> allProductsNames = page.getProductsNames();
        page.chooseSize(0);
        Thread.sleep(500);
        List<String> filteredProducts = page.getProductsNames();
        Assert.assertTrue(allProductsNames.size() > filteredProducts.size(), "All: " + allProductsNames.size() + ", filtered: " + filteredProducts.size());
    }

    @DataProvider(name = "excelData")
    public Object[][] dataProvider () throws IOException {
        ExcelDataProvider provider = new ExcelDataProvider();
        return provider.excelDataProvider();
    }

    @Test(dataProvider = "excelData")
    public void sizeFilterBySizeName(String key) throws InterruptedException {
        ShoppingMainPage page = new ShoppingMainPage(driver);
        page.openUrl();

        List<String> allProductsNames = page.getProductsNames();
        page.chooseSizeByName(key);
        Thread.sleep(600);
        List<String> filteredProducts = page.getProductsNames();
        Assert.assertTrue(allProductsNames.size() > filteredProducts.size(), "All: " + allProductsNames.size() + ", filtered: " + filteredProducts.size());
    }

    @Test
    public void sizeFilterParseNumber() throws InterruptedException {
        ShoppingMainPage page = new ShoppingMainPage(driver);
        page.openUrl();

        page.getProductsNames(); // to wait when the products are loaded
        int allProductsNumber = page.getProductsTotalFromText();
        page.chooseSize(0);
        Thread.sleep(500);
        int filteredProductsNumber = page.getProductsTotalFromText();

        Assert.assertTrue(allProductsNumber > filteredProductsNumber, "All: " + allProductsNumber + ", filtered: " + filteredProductsNumber);
    }

}
