package ru.aplana.cucumberallure.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.aplana.cucumberallure.Framework;
import ru.aplana.cucumberallure.Init;

public class BasePage {
    WebDriver driver;
    Framework fw;

    public BasePage(){
        this.driver = Init.getDriver();
        this.fw = Init.getFramework();
        PageFactory.initElements(driver, this);
    }
}
