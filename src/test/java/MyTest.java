import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class MyTest extends WebDriverFactory {

    @Test
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
    @DisplayName("Проверка заполненных данных")
    public void checkMyProfileData() {
        mainPage
                .openMainPage()
                .openAuthPage();
        authPage.login(config.emailOtus(), config.passwordOtus());
        homePage.entranceToLK();
        myProfilePage.checkData().deleteAdditionalContacts();

    }


}
