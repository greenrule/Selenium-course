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


public class GeoZones {
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
    public void GeoZones() {
        driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        //список стран
        List<WebElement> countries  = driver.findElements(By.xpath("//table[contains(@class, 'dataTable')]/tbody/tr[contains(@class, 'row')]/td[3]/a"));
        for (int i = 0; i < countries.size(); i++) {
            // переходим на страницу страны
            countries.get(i).click();
            // в каждом ряду ищем зону
            List<WebElement> zones  = driver.findElements(By.xpath("//table[contains(@class, 'dataTable')]/tbody/tr[not(contains(@class, 'header'))]/td[3]/select/option[@selected]"));
            //для каждой зоны извлекаем текст и кладем его в специально созданный список
            List<String> zonesNames = new ArrayList<String>();
            zonesNames.add(zones.get(i).getText());

            //создаем новый список и сортируем его
            List<String> zonesNamesOrdered = zonesNames;
            Collections.sort(zonesNamesOrdered);
            //сравниваем список стран и сортированный список
            assert zonesNamesOrdered.equals(zonesNames);
            System.out.println("Сортировка зон сделана");

            // возвращаемся на страницу со странами
            driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
            //получаем новый список со странами
            List<WebElement> countriesNew  = driver.findElements(By.xpath("//table[contains(@class, 'dataTable')]/tbody/tr[contains(@class, 'row')]/td[3]/a"));
            //переопределяем старый список новым, чтобы можно было снова идти по циклу
            countries = countriesNew;
        }
    }
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}





