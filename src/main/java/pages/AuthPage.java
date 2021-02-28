package pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AuthPage extends PageObject {
    public Logger logger = LogManager.getLogger(AuthPage.class);

    @FindBy(xpath = "//*[contains(text(), 'Авторизуйтесь чтобы продолжить')]")
    private WebElement checkAuthpage;

    @FindBy(css = "div.new-input-line_slim:nth-child(3) > input:nth-child(1)")
    private WebElement login;

    @FindBy(css = ".js-psw-input")
    private WebElement password;

    @FindBy(xpath = "//*[contains(text(),'Войти')]")
    private WebElement submit;

    public AuthPage(WebDriver driver) {

        super(driver);
    }

    private void checkAuthPage() {

        isElementDisplayed(checkAuthpage);
        logger.debug("Checking whether it is on the authorization pages");
    }

    private void fillEmail(String text) {
        isElementDisplayed(login);
        clearAndSendKeys(login, text);
        logger.debug("Filling in the E-mail field");
    }

    private void fillPassword(String text) {
        isElementDisplayed(password);
        clearAndSendKeys(password, text);
        logger.debug("Filling in the password field");
    }

    private void submitAuth() {
        submit.click();
    }

    private void checkSignIn() {
        String iconLocator = ".header2-menu__item_dropdown_no-border";
        WebElement icon = driver.findElement(By.cssSelector(iconLocator));
        Actions action = new Actions(driver);
        action.moveToElement(icon).build().perform();
        driver.findElement(By.xpath("//*[contains(text(),'Турал Алиев')]"));
        logger.debug("Checking the login to my profile");
    }

    public void login(String email, String password) {
        checkAuthPage();
        fillEmail(email);
        fillPassword(password);
        submitAuth();
        checkSignIn();
        logger.info("Authorization passed");
    }
}

