import config.VariableConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.PageFactory;
import pages.AuthPage;
import pages.HomePage;
import pages.MainPage;
import pages.MyProfilePage;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {
    public WebDriver driver;
    private String browser;
    public MainPage mainPage;
    public AuthPage authPage;
    public HomePage homePage;
    public MyProfilePage myProfilePage;
    public Logger logger = LogManager.getLogger(WebDriverFactory.class);
    public static VariableConfig config = ConfigFactory.create(VariableConfig.class);


    @Before
    public void SetUp() {
        if (System.getProperty("Browser") == null) {
            browser = "";
        } else {
            browser = System.getProperty("Browser").toLowerCase();
        }

        switch (browser) {
            case ("\'opera\'"):
                WebDriverManager.operadriver().setup();
                driver = new OperaDriver();
                break;

            case ("\'firefox\'"):
                WebDriverManager.firefoxdriver().setup();
                break;

            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }
        logger.info("Browser driver open");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        mainPage = PageFactory.initElements(driver,MainPage.class);
        authPage = PageFactory.initElements(driver,AuthPage.class);
        homePage = PageFactory.initElements(driver,HomePage.class);
        myProfilePage = PageFactory.initElements(driver,MyProfilePage.class);
    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.manage().deleteAllCookies();
            driver.quit();
            logger.info("Browser driver closed");
        }
    }
}
