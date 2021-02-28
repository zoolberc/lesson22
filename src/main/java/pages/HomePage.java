package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends PageObject{

    public Logger logger = LogManager.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {

        super(driver);
    }
    public  void entranceToLK(){
        String iconLocator = ".header2-menu__item_dropdown_no-border";
        WebElement icon = driver.findElement(By.cssSelector(iconLocator));
        Actions action = new Actions(driver);
        action.moveToElement(icon).build().perform();
        driver.findElement(By.cssSelector(".header2-menu__dropdown-text")).click();
        logger.info("Go to my profile");
    }
}
