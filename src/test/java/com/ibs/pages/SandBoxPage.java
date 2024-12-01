package com.ibs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SandBoxPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//a[@class ='nav-link dropdown-toggle']")
    private WebElement sandBoxLink;

    @FindBy(xpath = "//a[text()='Товары']")
    private WebElement goodsLink;

    public SandBoxPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 секунд ожидания
        PageFactory.initElements(driver, this);
    }

    // клик на песочницу
    public SandBoxPage clickSandBoxLink() {
        sandBoxLink.click();
        return this;
    }

    // клик на товары
    public GoodsPage clickGoodsLink() {
        goodsLink.click();
        return new GoodsPage(driver);
    }
}
