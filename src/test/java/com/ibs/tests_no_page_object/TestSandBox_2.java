package com.ibs.tests_no_page_object;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.*;
import java.util.concurrent.TimeUnit;

public class TestSandBox_2 {

    @Test
    void AddingExistingProduct() throws SQLException {
        // Настройка WebDriver
        WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chromedriver.driver", "src/test/resources/chromedriver.exe");// подключение драйвера
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().getScriptTimeout();
        driver.manage().window().maximize();// дисплей на максимум

        driver.get("http://localhost:8080/");  // открыли страницу

        /**
         * Добавление товара уже существующего
         */
        WebElement sandBox = driver.findElement(By.xpath("//a[@class ='nav-link dropdown-toggle']"));
        sandBox.click(); // клик на песочницу

        WebElement goods = driver.findElement(By.xpath("//a[text()='Товары']"));
        goods.click(); // клик на товары

        WebElement listProducts = driver.findElement(By.xpath("//h5[text()='Список товаров']"));
        listProducts.isDisplayed(); // проверка, что открылась вкладка

        WebElement buttonAdd = driver.findElement
                (By.xpath("//button[@class='btn btn-primary' and @data-toggle='modal']"));
        buttonAdd.click(); //клик на кнопку добавить

        WebElement addProduct = driver.findElement(By.xpath("//*[@id='editModalLabel']"));
        addProduct.isDisplayed(); // проверка, что открылась вкладка

        WebElement productName = driver.findElement(By.xpath("//*[@id='name']"));
        productName.sendKeys("Яблоко");// Ввести значение в текстовое поле

        WebElement type = driver.findElement(By.xpath("//*[@id='type']"));
        type.click();  //клик на поле тип

        WebElement typeFruit = driver.findElement(By.xpath("//option[@value='FRUIT']"));
        typeFruit.click(); // клик по типу фрукт

        WebElement buttonSave = driver.findElement(By.xpath("//*[@id='save']"));
        buttonSave.click(); // клик по кнопке сохранить

        // проверка, что появился элемент в столбце
        driver.findElement(By.xpath("//th[.='5']/following-sibling::td[.='Яблоко']")).isDisplayed();

        // устанавливаем соединение с БД, передали УРЛ, логин и пароль
        Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/mem:testdb",
                "user", "pass");

        // Проверка количества товаров в БД
        String query = "SELECT FOOD_NAME, COUNT(FOOD_NAME) AS COUNT FROM food " +
                "WHERE food_name = 'Яблоко' AND FOOD_TYPE = 'FRUIT' AND FOOD_EXOTIC = '0' GROUP BY FOOD_NAME";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            String name = resultSet.getString("FOOD_NAME");
            int count = resultSet.getInt("COUNT");
            System.out.printf("%s, %d%n", name, count);
        }

        // Удаление товара через БД
        String deleteQuery = "DELETE FROM food WHERE food_name = 'Яблоко';";
        int rowsDeleted = statement.executeUpdate(deleteQuery);
        System.out.println("Удалено строк: " + rowsDeleted);


        // Проверка количества товаров после удаления

        resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            int count = resultSet.getInt("COUNT");
            System.out.println("Кол-во яблок после удаления: " + count);
        } else {
            System.out.println("Кол-во яблок после удаления: " + 0);
        }

        connection.close();
        driver.quit();

    }
}

