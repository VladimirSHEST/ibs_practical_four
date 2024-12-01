package com.ibs.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddProductModal {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id='editModalLabel']")
    private WebElement addProductModalHeader;

    @FindBy(xpath = "//*[@id='name']")
    private WebElement productNameField;

    @FindBy(xpath = "//*[@id='type']")
    private WebElement typeDropdown;

    @FindBy(xpath = "//option[@value='FRUIT']")
    private WebElement fruitOption;

    @FindBy(xpath = "//*[@id='save']")
    private WebElement saveButton;

    @FindBy(xpath = "//th[.='5']/following-sibling::td[.='Яблоко']")
    private WebElement elementExists;

    public AddProductModal(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // проверка, что открылась вкладка
    public AddProductModal isAddProductModalDisplayed() {
        Assertions.assertTrue(addProductModalHeader.isDisplayed(), "вкладка не открылась");
        return this;
    }

    // Ввести значение в текстовое поле
    public AddProductModal setProductName(String productName) {
        productNameField.sendKeys(productName);
        return this;
    }

    //клик на поле тип
    public AddProductModal selectTypeFruit() {
        typeDropdown.click(); //клик на поле тип
        fruitOption.click(); //клик на поле тип
        return this;
    }

    // клик по кнопке сохранить
    public AddProductModal clickSaveButton() {
        saveButton.click();
        return this;
    }

    // проверка, что появился элемент в столбце
    public AddProductModal isElementExists() {
        Assertions.assertTrue(elementExists.isDisplayed(), "элемент не появился");
        return this;
    }
}
