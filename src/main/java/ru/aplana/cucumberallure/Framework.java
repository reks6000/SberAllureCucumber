package ru.aplana.cucumberallure;

import junit.framework.ComparisonFailure;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.not;


public class Framework {
    private WebDriver driver;
    private Wait<WebDriver> wait;
    private Wait<WebDriver> verifyWait;


    public Framework(WebDriver driver, Wait<WebDriver> wait, Wait<WebDriver> verifyWait) {
        this.driver = driver;
        this.wait = wait;
        this.verifyWait = verifyWait;
    }

    public WebElement wait(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public WebElement wait(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public WebElement waitForChange(String xpath, String oldValue) {
        WebElement element = driver.findElement(By.xpath(xpath));
        wait.until(not(ExpectedConditions.attributeToBe(element, "innerText", oldValue)));
        return element;
    }

    public WebElement waitForChange(WebElement element, String oldValue) {
        wait.until(not(ExpectedConditions.attributeToBe(element, "innerText", oldValue)));
        return element;
    }

    public WebElement waitForChangeAndStop(String xpath, String oldValue) {
        boolean flag = false;
        WebElement element = driver.findElement(By.xpath(xpath));
        while (true) {
            if(!flag) {
                waitForChange(element, oldValue);
                flag = true;
            }
            if (flag) {
                oldValue = element.getText();
                try {
                    verifyWait.until(not(ExpectedConditions.attributeToBe(element, "innerText", oldValue)));
                } catch (Exception e) {
                    return element;
                }
            }
        }
    }

    public WebElement waitForChangeAndStop(WebElement element, String oldValue) {
        boolean flag = false;
        while (true) {
            if(!flag) {
                waitForChange(element, oldValue);
                flag = true;
            }
            if (flag) {
                oldValue = element.getText();
                try {
                    verifyWait.until(not(ExpectedConditions.attributeToBe(element, "innerText", oldValue)));
                } catch (Exception e) {
                    return element;
                }
            }
        }
    }

    public boolean waitForDelete(String xpath) {
        WebElement element = null;
        boolean deleted = false;
        try {
            element = driver.findElement(By.xpath(xpath));
            wait.until(not(ExpectedConditions.visibilityOf(element)));
        } catch (StaleElementReferenceException e) {
            deleted = true;
        }
        return deleted;
    }

    public boolean waitForDelete(WebElement element) {
        boolean deleted = false;
        try {
            wait.until(not(ExpectedConditions.visibilityOf(element)));
        } catch (StaleElementReferenceException e) {
            deleted = true;
        }
        return deleted;
    }

    public boolean verify(WebElement element) {
        boolean res = true;
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            verifyWait.until(ExpectedConditions.visibilityOf(element));
        } catch (org.openqa.selenium.TimeoutException e) {
            res = false;
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return res;
    }

    public String getText(String xpath) {
        WebElement element = wait(xpath);
        return element.getText();
    }

    public String getText(WebElement element) {
        wait(element);
        return element.getText();
    }

    public WebElement waitAndClick(String xpath) {
        WebElement element = wait(xpath);
        element.click();
        return element;
    }

    public WebElement waitAndClick(WebElement element) {
        wait(element);
        element.click();
        return element;
    }

    public void waitAndClickClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public WebElement waitAndHover(String xpath) {
        Actions action = new Actions(driver);
        WebElement element = wait(xpath);
        action.moveToElement(element).build().perform();
        return element;
    }

    public WebElement waitAndHover(WebElement element) {
        Actions action = new Actions(driver);
        wait(element);
        action.moveToElement(element).build().perform();
        return element;
    }

    public WebElement waitAndSendKey(String xpath, String key) {
        WebElement element = wait(xpath);
        element.clear();
        element.sendKeys(key);
        return element;
    }

    public WebElement waitAndSendKey(WebElement element, String key) {
        wait(element);
        element.clear();
        element.sendKeys(key);
        return element;
    }

    public WebElement waitAndSendKeysArray(String xpath, ArrayList<String> keys) {
        WebElement element = wait(xpath);
        List<WebElement> elements = driver.findElements(By.xpath(xpath));
        Iterator<String> iterator = keys.iterator();
        for (WebElement webElement : elements) {
            if (iterator.hasNext()) {
                webElement.clear();
                webElement.sendKeys(iterator.next());
            }
        }
        return element;
    }

    public void check(String xpath, String expected) {
        WebElement element = wait(xpath);
        try {
            assertEquals(expected, element.getText());
        } catch (ComparisonFailure e) {
            System.err.println("Error: wrong check");
            driver.quit();
            throw new Error();
        }
    }

    public void check(WebElement element, String expected) {
        wait(element);
        try {
            assertEquals(expected, element.getText());
        } catch (ComparisonFailure e) {
            System.err.println("Error: wrong check");
            driver.quit();
            throw new Error();
        }
    }

    public void checkArray(String xpath, ArrayList<String> keys) {
        List<WebElement> elements = driver.findElements(By.xpath(xpath));
        Iterator<String> iterator = keys.iterator();
        for (WebElement webElement : elements) {
            if (iterator.hasNext()) {
                try {
                    assertEquals(iterator.next(), webElement.getAttribute("value"));
                } catch (ComparisonFailure e) {
                    System.err.println("Error: wrong check");
                    driver.quit();
                    throw new Error();
                }
            }
        }
    }

    public Select select(String xpath, String text) {
        Select region = new Select(wait(xpath));
        region.selectByVisibleText(text);
        return region;
    }

    public Select select(WebElement element, String text) {
        Select region = new Select(wait(element));
        region.selectByVisibleText(text);
        return region;
    }

    public String getSelectText(String xpath) {
        Select region = new Select(wait(xpath));
        String text = region.getAllSelectedOptions().get(0).getText();
        return text;
    }

    public String getSelectText(WebElement element) {
        Select region = new Select(wait(element));
        String text = region.getAllSelectedOptions().get(0).getText();
        return text;
    }

    public void switchToNextWindow() {
        String currentWindow = driver.getWindowHandle();
        ArrayList<String> handles = new ArrayList<>(driver.getWindowHandles());
        for (String windowHandle : handles) {
            if (!windowHandle.equals(currentWindow)) {
                driver.switchTo().window(windowHandle);
                driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            }
        }
    }

    public void waitAndSwitchToFrame(String xpath) {
        WebElement element = wait(xpath);
        driver.switchTo().frame(element);
    }
}
