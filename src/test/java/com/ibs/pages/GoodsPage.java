package com.ibs.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoodsPage {

    private WebDriver driver;

    @FindBy(xpath = "//h5[text()='Список товаров']")
    private WebElement listProductsHeader;

    @FindBy(xpath = "//button[@class='btn btn-primary' and @data-toggle='modal']")
    private WebElement addButton;

    public GoodsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public GoodsPage isListProductsHeaderDisplayed() {
        Assertions.assertTrue(listProductsHeader.isDisplayed(), " вкладка не открылась"); // проверка, что открылась вкладка
        return this;
    }

    public GoodsPage clickAddButton() {
        addButton.click();
        return this; //клик на кнопку добавить
    }
}
