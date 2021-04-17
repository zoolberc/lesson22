import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

public class MyTest extends WebDriverFactory {

    @Test
    @Feature(value = "Заполнение")
    @Description("Заполнение странички профиля")
    @DisplayName("Заполнение профиля данными")
    public void fillMyProfileData() throws InterruptedException {
        mainPage
                .openMainPage()
                .openAuthPage();
        authPage.login(config.emailOtus(), config.passwordOtus());
        homePage.entranceToLK();

        myProfilePage.fillName(config.firstName())
                .fillNameLatin(config.firstNameLatin())
                .fillLastName(config.lastName())
                .fillLastNameLatin(config.lastNameLatin())
                .fillBlogName(config.blogName())
                .fillBirthday(config.DOB())
                .selectCountry(config.Country());
        Thread.sleep(500); // явное ожидание не помогает

        myProfilePage.selectCity(config.City())
                .selectEnglishLevel(config.englishLevel());

        Thread.sleep(500); // явное ожидание не помогает

        myProfilePage.addFirstContact(config.vkContact())
                .addSecondContact(config.skypeContact())
                .save();
    }

    @Test
    @Feature(value = "Проверка")
    @Description("Проверка заполенненных полей из предыдущего теста")
    @DisplayName("Проверка заполненных данных")
    public void checkMyProfileData() {
        mainPage
                .openMainPage()
                .openAuthPage();
        authPage.login(config.emailOtus(), config.passwordOtus());
        homePage.entranceToLK();
        myProfilePage.checkData().deleteAdditionalContacts();
    }

    @Test
    @Description("Просто тест")
    public void test1(){
        driver.get("https://vk.com");
        Allure.addAttachment("test1", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

    }

}
