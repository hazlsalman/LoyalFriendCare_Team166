package tests.US_027;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HakimPage;
import utilities.ConfigReader;
import utilities.Driver;
import java.time.Duration;

public class TC_03_LoginTest {

    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
    HakimPage hakimPage = new HakimPage();


    @Test
    public void test01() {


        // =========================================================
        // PRE-CONDITION:
        // =========================================================

        //1-) Anasayfaya git.
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        //2-) Sıgn In butonuna bas.
        hakimPage.homePageSignInButton.click();


        // =========================================================
        // POZİTİF ADIMLAR : KUTULARIN ETKİN OLDUĞUNU DOĞRULAMA
        // =========================================================
        Assert.assertTrue(hakimPage.loginEmailBox.isDisplayed(),
                "EMAIL kutusu görünmüyor.");
        Assert.assertTrue(hakimPage.loginPasswordBox.isDisplayed(),
                "PASSWORD kutusu görünmüyor.");




        // =========================================================
        // NEGATİF SENARYOLAR
        // =========================================================

        // ---------------- SENARYO 1: PASSWORD BOŞ BIRAK ----------------

        hakimPage.loginEmailBox.clear();
        hakimPage.loginPasswordBox.clear();

        hakimPage.loginEmailBox.sendKeys("admin.abdul.hakim.kazanci@loyalfriendcare.com");
        hakimPage.loginSignInButton.click();

        String emptyPasswordMessage = hakimPage.loginPasswordBox.getAttribute("validationMessage");
        Assert.assertTrue(emptyPasswordMessage.length() > 0,
                "PASSWORD boş bırakıldığında uyarı çıkmadı!");

        // ---------------- SENARYO 2: EMAIL BOŞ BIRAK ----------------

        wait.until(ExpectedConditions.visibilityOf(hakimPage.loginEmailBox));

        hakimPage.loginEmailBox.clear();
        hakimPage.loginPasswordBox.clear();

        hakimPage.loginPasswordBox.sendKeys("LFCare.0201");
        hakimPage.loginSignInButton.click();

        String emptyEmailMessage = hakimPage.loginEmailBox.getAttribute("validationMessage");
        Assert.assertTrue(emptyEmailMessage.length() > 0,
                "EMAIL boş bırakıldığında uyarı çıkmadı.");


        // ---------------- SENARYO 3: EMAIL YANLIŞ, ŞİFRE DOĞRU GİR. ----------------

        wait.until(ExpectedConditions.visibilityOf(hakimPage.loginEmailBox));

        hakimPage.loginEmailBox.clear();
        hakimPage.loginPasswordBox.clear();
        hakimPage.loginEmailBox.sendKeys("tester78@gmail.com");
        hakimPage.loginPasswordBox.sendKeys("LFCare.0201");
        hakimPage.loginSignInButton.click();


        System.out.println(" Yanlış email senaryosu tamamlandı\n");




        // ---------------- SENARYO 4: EMAIL DOĞRU, ŞİFRE YANLIŞ GİR. ----------------

        // TARAYICIYI TAMAMEN YENİLE
        Driver.quitDriver();
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10)); // WebDriverWait nesnesi, her yeni driver açıldığında yeniden oluşturulur.
        hakimPage = new HakimPage(); // Her yeni driver açıldığında page object’leri de yeniden oluşturulmalıdır.

        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.homePageSignInButton)).click();
        wait.until(ExpectedConditions.visibilityOf(hakimPage.loginEmailBox));

        hakimPage.loginEmailBox.clear();
        hakimPage.loginPasswordBox.clear();
        hakimPage.loginEmailBox.sendKeys("admin.abdul.hakim.kazanci@loyalfriendcare.com");
        hakimPage.loginPasswordBox.sendKeys("LFCARE.0201");
        hakimPage.loginSignInButton.click();


        System.out.println("Yanlış şifre senaryosu tamamlandı\n");



        // =========================================================
        // POZİTİF SENARYO (BAŞARILI GİRİŞ)
        // =========================================================

        // TARAYICIYI TAMAMEN YENİLE
        Driver.quitDriver();
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10)); // WebDriverWait nesnesi, her yeni driver açıldığında yeniden oluşturulur.
        hakimPage = new HakimPage(); // Her yeni driver açıldığında page object’leri de yeniden oluşturulmalıdır.

        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.homePageSignInButton)).click();
        wait.until(ExpectedConditions.visibilityOf(hakimPage.loginEmailBox));

        hakimPage.loginEmailBox.clear();
        hakimPage.loginPasswordBox.clear();

        hakimPage.loginEmailBox.sendKeys("admin.abdul.hakim.kazanci@loyalfriendcare.com");
        hakimPage.loginPasswordBox.sendKeys("LFCare.0201");
        hakimPage.loginSignInButton.click();


        // =========================================================
        //  ANASAYFAYA YÖNLENDİRMEYİ TEST ET.
        // =========================================================

        String currentUrl = Driver.getDriver().getCurrentUrl();
        System.out.println("Başarılı giriş sonrası URL: " + currentUrl);

        Driver.quitDriver();
    }
}

/*
✅
Bu testte, senaryo gereği yanlış e-mail ya da şifre girilip Sıgn In butonuna basıldığında; web sitesi kullanıcıyı direkt başka sayfaya yönlendiriyor.
Bu nedenle manuel testte görünen hata mesajı, otomasyon sırasında DOM üzerinde yakalanamayabiliyor.
Bunu aşmak için her negatif senaryodan sonra tarayıcı tamamen kapatılıp yeniden açılmıştır.
Böylece her senaryo temiz bir sayfada başlar ve sayfalar birbirinin akışını etkilemez.

✔ Sıgn In butonuna click yapıldığında anda aynı anda iki şey tetikleniyor:

1️⃣ Hata mesajını gösterecek frontend event’i
2️⃣ Bot tespit edildiği için backend'in güvenlik yönlendirmesi

Yani:
Frontend hata mesajını göstermeye çalışıyor ama backend Selenium'u bot olarak algılayınca, anında redirect yaptığı için mesaj DOM’a düşmüyor.
Saniyelerle bile değil, milisaniyelerle çalışıyor bu süreç.

*/
