package ru.aplana.cucumberallure.pages;

import cucumber.api.java.ru.Когда;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
    @FindBy(xpath = "//*[@class='lg-menu__text'][text()='Ипотека']")
    WebElement mortgageButton;

    @FindBy(xpath = "//a[@class='lg-menu__sub-link'][text()='Ипотека на готовое жильё']")
    WebElement housingMortgageButton;

    @Когда ("пользователь наводит на пункт меню Ипотека")
    public void hoverMenuItem () {
        String xpath = "//*[@class='lg-menu__text'][text()='Ипотека']";
//        WebElement element = driver.findElement(By.xpath(xpath));
        fw.waitAndHover(xpath);
    }

//    public void hoverMenuItem () {
//        WebElement item = mortgageButton;
//        fw.waitAndHover(item);
//    }

    @Когда ("пользователь нажимает на подпункт меню Ипотека на готовое жильё")
    public MortgagePage clickSubmenuItem () {
        WebElement item = housingMortgageButton;
        fw.waitAndClick(item);
        MortgagePage mortgagePage = new MortgagePage();
        mortgagePage.switchFrame();
        return mortgagePage;
    }
}
