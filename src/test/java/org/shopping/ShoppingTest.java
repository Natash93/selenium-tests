package org.shopping;

import org.shopping.pages.ShoppingMainPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class ShoppingTest extends BaseTest {
    private ShoppingMainPage page;

    @BeforeTest
    @Override
    public void setUp() {
        super.setUp();

        page = new ShoppingMainPage(driver);
    }

    @BeforeMethod
    public void openSite() {
        page.openUrl();
    }

    @Test
    public void addToCartTest() {
        String firstProductName = page.getProductsNames().get(0);
        page.addFirstProductToCart();
        String cartFirstProductName = page.getCartProductsNames().get(0);

        Assert.assertEquals(cartFirstProductName, firstProductName);
    }

    @Test
    public void addAllToCartTest() {
        List<String> allProductsNames = page.getProductsNames();
        page.addAllProductsToCart();

        List<String> cartAllProductsNames = page.getCartProductsNames();

        Assert.assertEquals(cartAllProductsNames, allProductsNames);
    }

    @Test
    public void sizeFilter() throws InterruptedException {
        List<String> allProductsNames = page.getProductsNames();
        page.chooseSize(0);
        Thread.sleep(350);
        List<String> filteredProducts = page.getProductsNames();
        Assert.assertTrue(allProductsNames.size() > filteredProducts.size(), "All: " + allProductsNames.size() + ", filtered: " + filteredProducts.size());
    }

    @Test
    public void sizeFilterParseNumber() throws InterruptedException {
        page.getProductsNames(); // to wait when the products are loaded
        int allProductsNumber = page.getProductsTotalFromText();
        page.chooseSize(0);
        Thread.sleep(350);
        int filteredProductsNumber = page.getProductsTotalFromText();

        Assert.assertTrue(allProductsNumber > filteredProductsNumber, "All: " + allProductsNumber + ", filtered: " + filteredProductsNumber);
    }

}
