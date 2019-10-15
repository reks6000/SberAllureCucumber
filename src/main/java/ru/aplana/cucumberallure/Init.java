package ru.aplana.cucumberallure;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Init {
    private static WebDriver driver;
    private static Wait<WebDriver> wait;
    private static Wait<WebDriver> verifyWait;
    private static Framework fw;

    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 5, 500);
        verifyWait = new WebDriverWait(driver, 1, 500);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get("https://www.sberbank.ru/person");
        fw = new Framework(driver, wait, verifyWait);
    }

    public static Framework getFramework() {
        return fw;
    }

    public static WebDriver getDriver(){
        return driver;
    }

    public static void tearDown() {
        driver.quit();
    }

}
