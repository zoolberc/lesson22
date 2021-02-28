package pages;

import config.VariableConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;

import static org.junit.Assert.assertTrue;

public class PageObject {

    public WebDriver driver;
    public WebDriverWait wait;
    public static VariableConfig config = ConfigFactory.create(VariableConfig.class);
    // public VariableConfig cnf = ConfigFactory.create(VariableConfig.class);
    public Logger logger = LogManager.getLogger(PageObject.class);

    public PageObject(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    //Wait Wrapper Method
    public void waitVisibility(WebElement webElement){
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    //Is element displayed
    public void isElementDisplayed(WebElement webElement){
        waitVisibility(webElement);
        assertTrue(webElement.isDisplayed());
    }

    public void clearAndSendKeys(WebElement webElement, String text){
        webElement.clear();
        webElement.sendKeys(text);

    }
}
