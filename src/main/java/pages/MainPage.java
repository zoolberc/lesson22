package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static junit.framework.TestCase.assertEquals;

public class MainPage extends PageObject{

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".header2__auth")
    private WebElement signIn;

    public MainPage openMainPage(){
        driver.get("https://otus.ru/");
        assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям",driver.getTitle());
        logger.info("Home page is open");
        return this;
    }

    //Переход на страницу авторизации
    public void openAuthPage(){
        isElementDisplayed(signIn);
        signIn.click();
        logger.info("Open authorization page");
    }


}
