package ru.stqa.maven;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class  Country_test {
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
    public void Country_test() {
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        //ЧАСТЬ А. список стран
        List<WebElement> elementList  = driver.findElements(By.cssSelector(".countries_form.row a"));
        //список, который наполним названиями стран
        List<String> listCountry = new ArrayList<String>();
        //для каждого элемента получаем название и добавляем в новый список
            for (int i = 0; i < elementList.size(); i++) {
                listCountry.add(elementList.get(i).getText());
            }
        //создаем новый список и сортируем его
        List<String> listCountryOrdered = listCountry;
        Collections.sort(listCountryOrdered);
        //сравниваем список стран и сортированный список
        assert listCountryOrdered.equals(listCountry);
        }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}


