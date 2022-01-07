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


public class ZonesMoreNull {
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
    //9 задание. 1 часть Б.
    public void ZonesMoreNull() {
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        //список рядов
        List<WebElement> rowList  = driver.findElements(By.xpath("//table[contains(@class, 'dataTable')]/tbody/tr[contains(@class, 'row')]"));
        for (int i = 0; i < rowList.size(); i++) {
            // ряд
            WebElement row = rowList.get(i);
            // в каждом ряду ищем зону
            WebElement zone = row.findElement(By.cssSelector("td:nth-of-type(6)"));
            // Извлекаем текст и приводим его к целому числу
            int index = Integer.valueOf(zone.getText());
            //ищем зоны больше нуля и переходим в страну
            if (index > 0) {
                row.findElement(By.tagName("a")).click();
                //составляем список (вебэлементов) стран на стране страны
                List<WebElement> InnerElementList  = driver.findElements(By.xpath("//table[contains(@class, 'dataTable')]/tbody/tr/td[3]"));
                //слздадим пустой список (названий) стран
                List<String> InnerlistCountry = new ArrayList<String>();
                //для каждого элемента получаем название и добавляем в новый список
                for (int j = 0; j < InnerElementList.size(); j++) {
                    InnerlistCountry.add(InnerElementList.get(j).getText());
                }

                //создаем новый список и сортируем его
                List<String> InnerlistCountryOrdered = InnerlistCountry;
                Collections.sort(InnerlistCountryOrdered);
                //сравниваем список стран и сортированный список
                assert InnerlistCountryOrdered.equals(InnerlistCountry);
                System.out.println("Сортировка сделана");
                // возвращаемся на страницу со странами
                driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
                //получаем новый список рядов
                List<WebElement> rowListNew  = driver.findElements(By.xpath("//table[contains(@class, 'dataTable')]/tbody/tr[contains(@class, 'row')]"));
                //переопределяем старый список новым, чтобы можно было снова идти по циклу с rowList элементами
                rowList = rowListNew;
            }
        }
    }
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}


