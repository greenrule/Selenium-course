package ru.stqa.maven;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Edge_test {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new EdgeDriver();
        //ждем 10 секунд, чтобы найти элемент
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //todo горор
        wait = new WebDriverWait(driver, 10);

    }

    @Test
    // ввод логина / пароля на форме авторизации
    public void myFirstTest() {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}