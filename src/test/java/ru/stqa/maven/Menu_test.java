package ru.stqa.maven;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Menu_test {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        //ждем 10 секунд, чтобы найти элемент
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test

    public void myFirstTest() {
        driver.navigate().to("http://localhost/litecart/admin/");
        // ввод логина / пароля на форме авторизации
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        //прокликиваем элементы меню
        List<WebElement> elementList  =driver.findElements(By.cssSelector("#box-apps-menu a"));
        int numberOfListElements = elementList.size();
        for (int i = 0; i < numberOfListElements ; i++){
            elementList = driver.findElements(By.cssSelector("#box-apps-menu a"));
            elementList.get(i).click();
            //проверяем наличие заголовка на странице
            Boolean isPresent = driver.findElements(By.cssSelector("h1")).size() > 0;
        }
    }
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}