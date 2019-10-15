package ru.aplana.cucumberallure.pages;

import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MortgagePage extends BasePage {

    String frameXpath = "//iframe[@id='iFrameResizer0']";

    @FindBy(xpath = "//input[@id='estateCost']")
    WebElement estateCost;

    @FindBy(xpath = "//input[@id='initialFee']")
    WebElement initialFee;

    @FindBy(xpath = "//input[@id='creditTerm']")
    WebElement creditTerm;

    @FindBy(xpath = "//input[@data-test-id='paidToCard']//ancestor::div[@class='dcCalc_switch-tablet__switch']")
    WebElement sberCardSwitch;

    @FindBy(xpath = "//input[@data-test-id='canConfirmIncome']//ancestor::div[@class='dcCalc_switch-tablet__switch']")
    WebElement canConfirmSwitch;

    @FindBy(xpath = "//input[@data-test-id='youngFamilyDiscount']//ancestor::div[@class='dcCalc_switch-tablet__switch']")
    WebElement youngFamilySwitch;

    @FindBy(xpath = "//span[@data-test-id='amountOfCredit']")
    WebElement amountOfCredit;

    @FindBy(xpath = "//span[@data-test-id='monthlyPayment']")
    WebElement monthlyPayment;

    @FindBy(xpath = "//span[@data-test-id='requiredIncome']")
    WebElement requiredIncome;

    @FindBy(xpath = "//span[@data-test-id='rate']")
    WebElement interestRate;

    public void switchFrame() {
        fw.waitAndSwitchToFrame(frameXpath);
    }

    @Когда ("пользователь меняет значение поля Стоимость недвижимости на \"(.*)\"")
    public void changeEstateCost(String key) {
        String oldValue = monthlyPayment.getText();
        fw.waitAndSendKey(estateCost, key);
        fw.waitForChangeAndStop(monthlyPayment, oldValue);
    }

    @Когда ("пользователь меняет значение поля Первоначальный взнос на \"(.*)\"")
    public void changeInitialFee(String key) {
        String oldValue = monthlyPayment.getText();
        fw.waitAndSendKey(initialFee, key);
        fw.waitForChangeAndStop(monthlyPayment, oldValue);
    }

    @Когда ("пользователь меняет значение поля Срок кредита на \"(.*)\"")
    public void changeCreditTerm(String key) {
        String oldValue = monthlyPayment.getText();
        fw.waitAndSendKey(creditTerm, key);
        fw.waitForChangeAndStop(monthlyPayment, oldValue);
    }

    @Когда ("пользователь нажимает на переключатель Есть зарплатная карта Сбербанка")
    public void clickSberCardSwitch() {
        String oldValue = monthlyPayment.getText();
        fw.waitAndClick(sberCardSwitch);
        fw.waitForChangeAndStop(monthlyPayment, oldValue);
        waitCanConfirmSwitch();
    }

    public void waitCanConfirmSwitch() {
        fw.wait(canConfirmSwitch);
    }

    @Когда ("пользователь нажимает на переключатель Молодая семья")
    public void clickYoungFamilySwitch() {
        String oldValue = monthlyPayment.getText();
        fw.waitAndClick(youngFamilySwitch);
        fw.waitForChangeAndStop(monthlyPayment, oldValue);
    }

//    public void switchToMainWindow() {
//        String currentWindow = driver.getWindowHandle();
//        driver.switchTo().window(currentWindow);
//    }

//    public void clickYoungFamilySwitch() {
//        String oldValue = monthlyPayment.getText();
//        fw.waitAndClickClickable(youngFamilySwitch);
//        fw.waitForChangeAndStop(monthlyPayment, oldValue);
//    }

//    public void clickYoungFamilySwitch() {
//        Actions actions = new Actions(driver);
//        String oldValue = monthlyPayment.getText();
//        WebElement element = driver.findElement(By.xpath("//input[@data-test-id='youngFamilyDiscount']//ancestor::div[@class='dcCalc_switch-tablet__switch']"));
//        actions.moveToElement(element).click().build().perform();
//        fw.waitForChangeAndStop(monthlyPayment, oldValue);
//    }
//
//    public void clickYoungFamilySwitch() {
//        Actions actions = new Actions(driver);
//        String oldValue = monthlyPayment.getText();
//        JavascriptExecutor jse2 = (JavascriptExecutor)driver;
//        jse2.executeScript("arguments[0].scrollIntoView()", youngFamilySwitch);
//        fw.waitAndClick(youngFamilySwitch);
//        fw.waitForChangeAndStop(monthlyPayment, oldValue);
//    }

//    public void clickYoungFamilySwitch() {
//        JavascriptExecutor executor = (JavascriptExecutor) driver;
//        String oldValue = monthlyPayment.getText();
//        executor.executeScript("arguments[0].click();", youngFamilySwitch);
//        fw.waitForChangeAndStop(monthlyPayment, oldValue);
//    }

    @Тогда ("выполнеяется сравнение поля Сумма кредита с параметром \"(.*)\"")
    public void checkAmountOfCredit(String expected) {
        Assert.assertEquals(expected, amountOfCredit.getText());
    }

    @Тогда ("выполнеяется сравнение поля Ежемесячный платеж с параметром \"(.*)\"")
    public void checkMonthlyPayment(String expected) {
        Assert.assertEquals(expected, monthlyPayment.getText());
    }

    @Тогда ("выполнеяется сравнение поля Необходимый доход с параметром \"(.*)\"")
    public void checkRequiredIncome(String expected) {
        Assert.assertEquals(expected, requiredIncome.getText());
    }

    @Тогда ("выполнеяется сравнение поля Процентная ставка с параметром \"(.*)\"")
    public void checkInterestRate(String expected) {
        Assert.assertEquals(expected, interestRate.getText());
    }
}
