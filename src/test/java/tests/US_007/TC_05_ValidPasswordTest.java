package tests.US_007;

import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HakimPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;

public class TC_05_ValidPasswordTest {

    HakimPage hakimPage = new HakimPage();
    Faker faker = new Faker();

    @Test
    public void test01() throws InterruptedException {


        // =========================================================
        // PRE-CONDITION:
        // =========================================================

        //1-) Anasayfaya git.
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        //2-) Sıgn Up butonuna bas.
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.homePageSignUpButton)).click();

        //3-) Username alanını doldur.
        Assert.assertTrue(hakimPage.usernameBox.isDisplayed(), "USERNAME kutusu görünmüyor.");
        hakimPage.usernameBox.sendKeys(faker.name().username());

        //4-) Email alanını doldur.
        Assert.assertTrue(hakimPage.emailBox.isDisplayed(), "EMAİL kutusu görünmüyor.");
        hakimPage.emailBox.sendKeys(faker.internet().emailAddress());


        // =========================================================
        // 1. STEP: SADECE RAKAMLARDAN OLUŞAN ŞİFRE GİR.
        // =========================================================
        hakimPage.passwordBox.clear();
        hakimPage.confirmPasswordBox.clear();

        hakimPage.passwordBox.sendKeys("12345678" + Keys.TAB);
        hakimPage.confirmPasswordBox.sendKeys("12345678");

        String uyarı1 = hakimPage.passwordBox.getAttribute("validationMessage");
        Assert.assertTrue(uyarı1.length() > 0,
                "1) Sadece rakamlardan oluşan şifre için beklenen uyarı çıkmadı.");

        // =========================================================
        // 2. STEP: SADECE HARFLERDEN OLUŞAN ŞİFRE GİR.
        // =========================================================
        hakimPage.passwordBox.clear();
        hakimPage.confirmPasswordBox.clear();

        hakimPage.passwordBox.sendKeys("abcdefgh" + Keys.TAB);
        hakimPage.confirmPasswordBox.sendKeys("abcdefgh");

        String uyarı2 = hakimPage.passwordBox.getAttribute("validationMessage");
        Assert.assertTrue(uyarı2.length() > 0,
                "2) Sadece harf içeren şifre için beklenen uyarı çıkmadı.");

        // =========================================================
        // 3. STEP: SADECE ÖZEL KARAKTERLERDEN OLUŞAN ŞİFRE GİR.
        // =========================================================
        hakimPage.passwordBox.clear();
        hakimPage.confirmPasswordBox.clear();

        hakimPage.passwordBox.sendKeys("!@#$%^&*" + Keys.TAB);
        hakimPage.confirmPasswordBox.sendKeys("!@#$%^&*");

        String uyarı3 = hakimPage.passwordBox.getAttribute("validationMessage");
        Assert.assertTrue(uyarı3.length() > 0,
                "3) Sadece özel karakterlerden oluşan şifre için beklenen uyarı çıkmadı.");

        // =========================================================
        // 4. STEP: KRİTERLERE UYGUN AMA 8 KARAKTERDEN KISA ŞİFRE GİR.
        // =========================================================
        hakimPage.passwordBox.clear();
        hakimPage.confirmPasswordBox.clear();

        hakimPage.passwordBox.sendKeys("A1?!!" + Keys.TAB);
        hakimPage.confirmPasswordBox.sendKeys("A1?!!");

        String uyarı4 = hakimPage.passwordBox.getAttribute("validationMessage");
        Assert.assertTrue(uyarı4.length() > 0,
                "4) 8 karakterden kısa şifre için beklenen uyarı çıkmadı.");

        // =========================================================
        // 5. STEP: ŞİFRE KUTUSUNU BOŞ BIRAK.
        // =========================================================
        hakimPage.passwordBox.clear();
        hakimPage.confirmPasswordBox.clear();

        hakimPage.registerPageSignUpButton.click();

        String uyarı5 = hakimPage.passwordBox.getAttribute("validationMessage");
        Assert.assertTrue(uyarı5.length() > 0,
                "5) Şifre alanı boş bırakıldığında beklenen uyarı çıkmadı.");

        // =========================================================
        // 6. STEP: GEÇERLİ ŞİFRE GİR. (UYARI OLMAMALI)
        // =========================================================
        hakimPage.passwordBox.clear();
        hakimPage.confirmPasswordBox.clear();

        String validPassword = ConfigReader.getProperty("userGecerliPassword");
        hakimPage.passwordBox.sendKeys(validPassword + Keys.TAB);
        hakimPage.confirmPasswordBox.sendKeys(validPassword);

        String uyarı6 = hakimPage.passwordBox.getAttribute("validationMessage");
        Assert.assertEquals(uyarı6, "",
                "6) Geçerli şifre girilmesine rağmen uyarı verdi.");

        Driver.quitDriver();
    }
}
