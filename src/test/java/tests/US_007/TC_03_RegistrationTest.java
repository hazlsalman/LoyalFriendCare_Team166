package tests.US_007;

import com.github.javafaker.Faker;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HakimPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;

public class TC_03_RegistrationTest {

    HakimPage hakimPage = new HakimPage();
    Faker faker = new Faker();

    @Test
    public void test01() throws InterruptedException {


        // =========================================================
        // PRE-CONDITION:
        // =========================================================

        //1) Anasayfaya git.
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        //2) Sıgn Up butonuna bas.
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.homePageSignUpButton)).click();


        // =========================================================
        // KUTULARIN ETKİN OLDUĞUNU DOĞRULAMA
        // =========================================================

        // ADIM 1 : USERNAME girilir
        Assert.assertTrue(hakimPage.usernameBox.isDisplayed(), "USERNAME kutusu görünmüyor!");
        hakimPage.usernameBox.sendKeys("Abdül Hakim Kazancı");

        // ADIM 2 : EMAIL girilir
        Assert.assertTrue(hakimPage.emailBox.isDisplayed(), "EMAIL kutusu görünmüyor!");
        hakimPage.emailBox.sendKeys("user.abdul.hakim.kazanci@loyalfriendcare.com");

        // ADIM 3 : PASSWORD girilir
        hakimPage.passwordBox.sendKeys("LFCare.0201");

        // ADIM 4 : CONFIRM PASSWORD girilir
        hakimPage.confirmPasswordBox.sendKeys("LFCare.0201");

        // =========================================================
        // NEGATİF SENARYOLAR (CASE’TEKİ SIRA İLE)
        // =========================================================

        // ---------------- SENARYO 1: USERNAME ALANINI BOŞ BIRAK ----------------
        hakimPage.usernameBox.clear();
        hakimPage.emailBox.clear();
        hakimPage.passwordBox.clear();
        hakimPage.confirmPasswordBox.clear();

        hakimPage.emailBox.sendKeys("user.abdul.hakim.kazanci@loyalfriendcare.com");
        hakimPage.passwordBox.sendKeys("LFCare.0201");
        hakimPage.confirmPasswordBox.sendKeys("LFCare.0201");

        hakimPage.registerPageSignUpButton.click();

        String usernameMessage = hakimPage.usernameBox.getAttribute("validationMessage");
        Assert.assertTrue(usernameMessage.length() > 0,
                "USERNAME boş bırakıldığında uyarı çıkmadı!");

        // ---------------- SENARYO 2: EMAIL ALANINI BOŞ BIRAK  ----------------
        hakimPage.usernameBox.clear();
        hakimPage.emailBox.clear();
        hakimPage.passwordBox.clear();
        hakimPage.confirmPasswordBox.clear();

        hakimPage.usernameBox.sendKeys("Abdül Hakim Kazancı");
        hakimPage.passwordBox.sendKeys("LFCare.0201");
        hakimPage.confirmPasswordBox.sendKeys("LFCare.0201");

        hakimPage.registerPageSignUpButton.click();

        String emailMessage = hakimPage.emailBox.getAttribute("validationMessage");
        Assert.assertTrue(emailMessage.length() > 0,
                "EMAIL boş bırakıldığında uyarı çıkmadı!");

        // ---------------- SENARYO 3: PASSWORD ALANINI BOŞ BIRAK ----------------
        hakimPage.usernameBox.clear();
        hakimPage.emailBox.clear();
        hakimPage.passwordBox.clear();
        hakimPage.confirmPasswordBox.clear();

        hakimPage.usernameBox.sendKeys("Abdül Hakim Kazancı");
        hakimPage.emailBox.sendKeys("user.abdul.hakim.kazanci@loyalfriendcare.com");
        hakimPage.confirmPasswordBox.sendKeys("LFCare.0201");

        hakimPage.registerPageSignUpButton.click();

        String passwordMessage = hakimPage.passwordBox.getAttribute("validationMessage");
        Assert.assertTrue(passwordMessage.length() > 0,
                "PASSWORD boş bırakıldığında uyarı çıkmadı!");

        // ------------- SENARYO 4: CONFIRM PASSWORD ALANINI BOŞ BIRAK -------------
        hakimPage.usernameBox.clear();
        hakimPage.emailBox.clear();
        hakimPage.passwordBox.clear();
        hakimPage.confirmPasswordBox.clear();

        hakimPage.usernameBox.sendKeys("Abdül Hakim Kazancı");
        hakimPage.emailBox.sendKeys("user.abdul.hakim.kazanci@loyalfriendcare.com");
        hakimPage.passwordBox.sendKeys("LFCare.0201");

        hakimPage.registerPageSignUpButton.click();

        String confirmMessage = hakimPage.confirmPasswordBox.getAttribute("validationMessage");
        Assert.assertTrue(confirmMessage.length() > 0,
                "CONFIRM PASSWORD boş bırakıldığında uyarı çıkmadı!");

        // =========================================================
        // POZİTİF SENARYO : BAŞARILI KAYIT (FAKER CLASS'lı)
        // =========================================================

        hakimPage.usernameBox.clear();
        hakimPage.emailBox.clear();
        hakimPage.passwordBox.clear();
        hakimPage.confirmPasswordBox.clear();

        String fakeName = faker.name().fullName();
        String fakeEmail = faker.internet().emailAddress();

        hakimPage.usernameBox.sendKeys(fakeName);
        hakimPage.emailBox.sendKeys(fakeEmail);
        hakimPage.passwordBox.sendKeys("LFCare.0201");
        hakimPage.confirmPasswordBox.sendKeys("LFCare.0201");

        hakimPage.registerPageSignUpButton.click();

        Assert.assertEquals(
                Driver.getDriver().getCurrentUrl(),
                "https://qa.loyalfriendcare.com/en",
                "Kayıt sonrası anasayfa açılmadı."
        );

        Driver.quitDriver();
    }
}
