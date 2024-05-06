package org.shopping.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShoppingMainPage extends BasePage {

    @FindBy(xpath = "//div[contains(@class, \"csvtPz\")]")
    private List<WebElement> products;

    @FindBy(css = "button.jCsgpZ")
    private List<WebElement> addToCartButtons;

    @FindBy(css = "div.hDmOrM img.jMMQhp")
    private List<WebElement> cartProducts;

    @FindBy(css = ".hcyKTa")
    private List<WebElement> sizeFilters;

    @FindBy(css = ".iliWeY p")
    private WebElement productsTotalAmountText;


    public ShoppingMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openUrl() {
        driver.get("https://react-shopping-cart-67954.firebaseapp.com/");
    }

    public List<String> getProductsNames() {
        return products.stream().map((p) -> p.getAttribute("alt")).toList();
    }

    public List<String> getCartProductsNames() {
        return cartProducts.stream().map((p) -> p.getAttribute("alt")).toList();
    }

    public void addFirstProductToCart() {
        clickWithJS(addToCartButtons.get(0));
    }

    private void clickWithJS(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void addAllProductsToCart() {
        for (WebElement button : addToCartButtons) {
            clickWithJS(button);
        }
    }

    public void chooseSize(int index) {
        sizeFilters.get(index).click();
    }

    public int getProductsTotalFromText() {
        String countText = productsTotalAmountText.getText();
        return Integer.parseInt(countText.substring(0, countText.indexOf(" ")));
    }
}
