package ru.stqa.maven;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
// тест для старой мозилы 45 версии, тест не работает, оставлю для инфы. Надеюсь это старье безнадобья.
public class FF_old_test {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        //DesiredCapabilities caps = new DesiredCapabilities();
       // caps.setCapability(FirefoxDriver.MARIONETTE, true);
        /*FirefoxBinary binary = new FirefoxBinary(new File("C:\\Program Files\\Firefox Nightly\\firefox.exe"));
        FirefoxOptions options = new FirefoxOptions().setLegacy(false);
        options.setBinary(binary);
        WebDriver driver = new FirefoxDriver(options);*/

        //ждем 10 секунд, чтобы найти элемент
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);

    }

    @Test
    // ввод логина / пароля на форме авторизации
    public void myFirstTest() {
        driver.get("http://localhost/litecart/admin/");
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