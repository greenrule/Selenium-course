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

public class  Sticker_test {
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

    public void Sticker_test() {
        driver.navigate().to("http://localhost/litecart/en/");
        //ищем товары в блоке
        List<WebElement> elementList  =driver.findElements(By.cssSelector(".listing-wrapper a"));
        //цикл для каждого товара
        for (int i = 0; i < elementList.size(); i++){
            //для каждого товара получаем список стикеров
            List<WebElement>stickers = elementList.get(i).findElements(By.cssSelector(".sticker"));
            //проверяем, что у товара один стикер
            assert stickers.size() == 1;
        }
    }
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
