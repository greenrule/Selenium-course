package ru.stqa.maven;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;

public class PageForProduct {
    private WebDriver driver;
    private WebDriverWait wait;
    String numbersR;
    String numbersG;
    String numbersB;
    String stringForFont;

    //методы, которые из rgba() формата извлекают r, g, b.
    public static int GetR(String numbersR) {
        //проверка, что цвет серый: в строке удаляем скобки и rgba, split - строку разделил на подстроку и положил в массив
        String[] numbersR_st = numbersR.replace("rgba(", "").replace(")", "").split(",");
        //trim - удалил пробелы в начале и конце строки, и преобразовали строку в число
        return Integer.parseInt(numbersR_st[0].trim());
    }

    public static int GetG(String numbersG) {
        String[] numbersG_st = numbersG.replace("rgba(", "").replace(")", "").split(",");
        return Integer.parseInt(numbersG_st[1].trim());
    }

    public static int GetB(String numbersB) {
        String[] numbersB_st = numbersB.replace("rgba(", "").replace(")", "").split(",");
        return Integer.parseInt(numbersB_st[2].trim());
    }

    //метод, который строку с размером шрифта преобразует в число, 14.4px = 14.4
    public static float fontSize(String stringForFont) {
        //удаляем в конце px
        String stringFontWithOutPixels = stringForFont.replaceAll("[^0-9?!\\.]", "");
        // приводим к числовому формату
        return Float.valueOf(stringFontWithOutPixels);
    }

    @Before
    public void start() {
        driver = new ChromeDriver();
        //ждем 10 секунд, чтобы найти элемент
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }
    @Test

    public void PageForProduct() {
        driver.navigate().to("http://localhost/litecart/en/");
        //название товара на главной странице
        WebElement nameMain = driver.findElement(By.cssSelector("#box-campaigns .name"));
        String nameMain_st = nameMain.getText();

        //обычная цена товара на главной странице
        WebElement normalPriceMain = driver.findElement(By.cssSelector("#box-campaigns .regular-price"));
        String normalPriceMain_st = normalPriceMain.getText();

        //цвет обычной цены на главной странице
        String colorNormalPriceMain = normalPriceMain.getCssValue("color");
        int RColorNormalPriceMain = GetR(colorNormalPriceMain);
        int GColorNormalPriceMain = GetG(colorNormalPriceMain);
        int BColorNormalPriceMain = GetB(colorNormalPriceMain);
        //проверка, что цвет серый: R = G = B
        assert (RColorNormalPriceMain == GColorNormalPriceMain && GColorNormalPriceMain == BColorNormalPriceMain);

        //получаем свойство text-decoration
        String attr = normalPriceMain.getCssValue("text-decoration");
        //проверяем, что нормальная цена зачеркнута
        assert attr.contains("line-through");

       //определяем размер шрифта нормальной цены в пикселях, например, 14.4px и переводим в число
        String attrFontSizeNormal = normalPriceMain.getCssValue("font-size");
        Float fontNormalPrice = fontSize(attrFontSizeNormal);

        //скидочная цена на главной странице
        WebElement salePriceMain = driver.findElement(By.cssSelector("#box-campaigns .campaign-price"));
        String salePriceMain_st = salePriceMain.getText();

        //цвет скидочной цены на главной странице
        String colorSalePriceMain = salePriceMain.getCssValue("color");
        int RColorSalePriceMain = GetR(colorSalePriceMain);
        int GColorSalePriceMain = GetG(colorSalePriceMain);
        int BColorSalePriceMain = GetB(colorSalePriceMain);
        //проверка, что цвет G = B =0
        assert (GColorSalePriceMain == 0 && BColorSalePriceMain == 0);

        //получаем свойство font-weight
        String attrForSale = salePriceMain.getAttribute("tagName");
        //проверяем, что скидочная цена жирная
        assert attrForSale.contains("STRONG");

        //определяем размер скидочной цены и приводим его к числовому виду
        String attrFontSizeSale = salePriceMain.getCssValue("font-size");
        Float fontSalePrice = fontSize(attrFontSizeSale);

        //сравниваем, что размер обычной цены меньше акционной на главной
        assert fontSalePrice > fontNormalPrice;

        //переходим на страницу товара
        nameMain.click();
        WebElement namePage = driver.findElement(By.cssSelector(".content h1"));
        String namePage_st = namePage.getText();

        // проверяем, что названия товаров на главной и на странице товара совпадают
        assert namePage_st.equals(nameMain_st);

        WebElement normalPricePage = driver.findElement(By.cssSelector(".content .regular-price"));
        String normalPricePage_st = normalPricePage.getText();

        // проверяем, что обычная цены товаров на главной и на странице товара совпадают
        assert normalPricePage_st.equals(normalPriceMain_st);

        WebElement salePricePage = driver.findElement(By.cssSelector(".content .campaign-price"));
        String salePricePage_st = salePricePage.getText();

        // проверяем, что скидочные цены товаров на главной и на странице товара совпадают
        assert salePricePage_st.equals(salePriceMain_st);

        //цвет обычной цены на странице товара
        String colorNormalPricePage = normalPricePage.getCssValue("color");
        int RColorNormalPricePage = GetR(colorNormalPricePage);
        int GColorNormalPricePage = GetG(colorNormalPricePage);
        int BColorNormalPricePage = GetB(colorNormalPricePage);
        //проверка, что цвет серый: R = G = B
        assert (RColorNormalPricePage == GColorNormalPricePage && GColorNormalPricePage == BColorNormalPricePage);

        //цвет скидочной цены на странице товара
        String colorSalePricePage = salePricePage.getCssValue("color");
        int RColorSalePricePage = GetR(colorSalePricePage);
        int GColorSalePricePage = GetG(colorSalePricePage);
        int BColorSalePricePage = GetB(colorSalePricePage);
        //проверка, что цвет красный: G = B =0
        assert (GColorSalePricePage == 0 && BColorSalePricePage == 0);

        //определяем размер шрифта нормальной цены на странице товара
        String attrFontSizeNormalPage = normalPricePage.getCssValue("font-size");
        Float fontNormalPricePage = fontSize(attrFontSizeNormalPage);

        //определяем размер скидочной на странице товара
        String attrFontSizeSalePage = salePricePage.getCssValue("font-size");
        Float fontSalePricePage = fontSize(attrFontSizeSalePage);

        //сравниваем, что размер обычной цены меньше акционной на главной
        assert fontSalePricePage > fontNormalPricePage;


    }
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

