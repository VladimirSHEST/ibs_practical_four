package com.ibs.base_test;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver = new ChromeDriver();

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chromedriver.driver", "src/test/resources/chromedriver.exe");// подключение драйвера
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().getScriptTimeout();
        driver.manage().window().maximize();// дисплей на максимум

        driver.get("http://localhost:8080/");  // открыли страницу
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
