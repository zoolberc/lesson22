package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MyProfilePage extends PageObject {

    public MyProfilePage(WebDriver driver) {
        super(driver);
    }
    public Logger logger = LogManager.getLogger(MyProfilePage.class);


    @FindBy(css = "#id_fname")
    private WebElement name;

    @FindBy(css = "#id_fname_latin")
    private WebElement nameLatin;

    @FindBy(css = "#id_lname")
    private WebElement lastName;

    @FindBy(css = "#id_lname_latin")
    private WebElement lastNameLatin;

    @FindBy(css = "#id_blog_name")
    private WebElement blogName;

    @FindBy(css = ".input-icon > input:nth-child(1)")
    private WebElement birthday;

    @FindBy(css = "button.button_md-4:nth-child(2)")
    private WebElement saveButton;

    @FindBy(id = "id_contact-0-value")
    private WebElement firstContact;

    @FindBy(id = "id_contact-1-value")
    private WebElement secondContact;

    @FindBy(xpath = "//*[@name ='city']/../..")
    private WebElement chooseCity;

    @FindBy(xpath = "//*[@data-ajax-slave = '/lk/biography/cv/lookup/cities/by_country/']")
    private WebElement chooseCountry;




    public MyProfilePage fillName(String text) {
        isElementDisplayed(name);
        clearAndSendKeys(name, text);
        logger.debug("Filing in the name field");
        return this;
    }

    public MyProfilePage fillNameLatin(String text) {
        isElementDisplayed(nameLatin);
        clearAndSendKeys(nameLatin, text);
        logger.debug("Filing in the latin name field");
        return this;
    }

    public MyProfilePage fillLastName(String text) {
        isElementDisplayed(lastName);
        clearAndSendKeys(lastName, text);
        logger.debug("Filing in the last name field");
        return this;
    }

    public MyProfilePage fillLastNameLatin(String text) {
        isElementDisplayed(lastNameLatin);
        clearAndSendKeys(lastNameLatin, text);
        logger.debug("Filing in the last name latin field");
        return this;
    }

    public MyProfilePage fillBlogName(String text) {
        isElementDisplayed(blogName);
        clearAndSendKeys(blogName, text);
        logger.debug("Filing in the blog name field");
        return this;
    }

    public MyProfilePage fillBirthday(String text) {
        isElementDisplayed(birthday);
        clearAndSendKeys(birthday, text);
        logger.debug("Filing in the birthday field");
        return this;
    }

    public MyProfilePage selectCountry(String text) {
        chooseCountry.click(); // выбор страны
        driver.findElement(By.xpath("//*[@title = '" + text + "']")).click(); // выбор страны
        logger.debug("Country selection");
        return this;
    }

    public MyProfilePage selectCity(String text) {
        chooseCity.click(); // выбор города
        driver.findElement(By.xpath("//*[@title = '" + text + "']")).click(); // выбор города
        logger.debug("City selection");
        return this;
    }

    public MyProfilePage selectEnglishLevel(String text) {
        driver.findElement(By.xpath("//*[@data-title = 'Уровень знания английского языка']/following::div[1]")).click(); // уровень английского
        driver.findElement(By.xpath("//*[@title = '" + text + "']")).click(); // выбор уровня
        logger.debug("Choice of English level");
        return this;
    }

    public MyProfilePage addFirstContact(String text) {
        driver.findElement(By.xpath("//*[@class ='placeholder']//..")).click();
        driver.findElement(By.xpath("//*[@title = 'VK']")).click(); // выбор способа связи ВК
        isElementDisplayed(firstContact);
        firstContact.sendKeys(text);
        logger.debug("Adding an additional contact");
        return this;
    }

    public MyProfilePage addSecondContact(String text) {
        driver.findElement(By.xpath("//*[contains(@class,'js-lk-cv-custom-select-add')]")).click();
        driver.findElements(By.xpath("//*[@class ='placeholder']//..")).get(0).click();
        driver.findElements(By.xpath("//*[@title = 'Skype']")).get(1).click();
        isElementDisplayed(secondContact);
        secondContact.sendKeys(text);
        logger.debug("Adding an additional contact");
        return this;
    }

    public MyProfilePage save() {
        isElementDisplayed(saveButton);
        saveButton.click();
        driver.findElement(By.xpath("//*[@title = 'Редактировать резюме']")).isDisplayed(); // проверка что данные сораннены
        logger.debug("Checking data storage");
        logger.info("Data added successfully");
        return this;
    }

    public MyProfilePage checkData() {
        assertEquals("Персональные данные", driver.findElement(By.cssSelector("h3.text")).getText());
        assertEquals(config.firstName(), name.getAttribute("value"));
        assertEquals(config.firstNameLatin(), nameLatin.getAttribute("value"));
        assertEquals(config.lastName(), lastName.getAttribute("value"));
        assertEquals(config.lastNameLatin(), lastNameLatin.getAttribute("value"));
        assertEquals(config.blogName(), blogName.getAttribute("value"));
        assertEquals(config.DOB(), birthday.getAttribute("value"));
        assertEquals(config.Country(), chooseCountry.getText());
        assertEquals(config.City(), chooseCity.getText());
        assertEquals(config.skypeContact(), firstContact.getAttribute("value"));
        assertEquals(config.vkContact(), secondContact.getAttribute("value"));
        logger.info("Data verify successfully");
        return this;
    }
    //удаление контактов
    public void deleteAdditionalContacts() {
        List<WebElement> conts = driver.findElements(By.xpath("//*[contains(text(), 'Удалить')]"));
        conts.get(1).click();
        conts.get(3).click();
        driver.findElement(By.cssSelector("button.button_md-4:nth-child(2)")).click(); // сохранение данных
        driver.findElement(By.xpath("//*[@title = 'Редактировать резюме']")).isDisplayed(); // проверка что данные сораннены
    }
}
