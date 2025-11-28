package tests.US_007;

import com.github.javafaker.Faker;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HakimPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC_03_RegistrationTest {

    @Test
    public void test01() throws InterruptedException {

        HakimPage hakimPage = new HakimPage();
        Faker faker = new Faker();
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

        // --------------------------
        // PRE-CONDITION: 1) ANASAYFAYA GİT
        // --------------------------
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        // --------------------------
        // PRE-CONDITION: 2) SIGN UP SAYFASINA GEÇ
        // --------------------------
        hakimPage.homeSignUpButton.click();



        // =========================================================
        // 1) POZİTİF ADIMLAR : SADECE KUTULARIN ETKİN OLDUĞUNU DOĞRULAMA
        // (Test case'te bu adımlar var)
        // =========================================================

        // ADIM 1 : USERNAME kutucuğuna isim girilir
        Assert.assertTrue(hakimPage.usernameBox.isDisplayed(), "USERNAME kutusu görünmüyor!");
        hakimPage.usernameBox.sendKeys("Abdül Hakim Kazancı");

        // ADIM 2 : EMAIL girilir
        Assert.assertTrue(hakimPage.emailBox.isDisplayed(), "EMAIL kutusu görünmüyor!");
        hakimPage.emailBox.sendKeys("user.abdul.hakim.kazanci@loyalfriendcare.com");

        // ADIM 3 : PASSWORD girilir
        hakimPage.passwordBox.sendKeys("LFCare.0201");

        // ADIM 4 : CONFIRM PASSWORD girilir
        hakimPage.confirmPasswordBox.sendKeys("LFCare.0201");

        Thread.sleep(1500);


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

        hakimPage.registersignUpButton.click();

        String usernameMessage = (String) js.executeScript(
                "return arguments[0].validationMessage;", hakimPage.usernameBox);
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

        hakimPage.registersignUpButton.click();

        String emailMessage = (String) js.executeScript(
                "return arguments[0].validationMessage;", hakimPage.emailBox);
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

        hakimPage.registersignUpButton.click();

        String passwordMessage = (String) js.executeScript(
                "return arguments[0].validationMessage;", hakimPage.passwordBox);
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

        hakimPage.registersignUpButton.click();

        String confirmMessage = (String) js.executeScript(
                "return arguments[0].validationMessage;", hakimPage.confirmPasswordBox);
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

        hakimPage.registersignUpButton.click();
        Thread.sleep(2000);

        Assert.assertEquals(
                Driver.getDriver().getCurrentUrl(),
                "https://qa.loyalfriendcare.com/en",
                "Kayıt sonrası anasayfa açılmadı!"
        );


        Driver.quitDriver();
    }
}
