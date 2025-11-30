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

public class TC_04_ValidEmailTest {

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

        // =========================================================
        // 1. SENARYO: '@' OLMAYAN E-MAIL GİR.
        // =========================================================
        hakimPage.emailBox.clear();
        hakimPage.emailBox.sendKeys("tester78.gmail.com");
        hakimPage.registerPageSignUpButton.click();

        String uyarı1 = hakimPage.emailBox.getAttribute("validationMessage");
        Assert.assertTrue(uyarı1.length() > 0,
                "Beklenen '@ işareti eksik' uyarısı çıkmadı.");

        // =========================================================
        // 2. SENARYO: '@' SONRASI OLMAYAN E-MAIL GİR.
        // =========================================================
        hakimPage.emailBox.clear();
        hakimPage.emailBox.sendKeys("tester78@");
        hakimPage.registerPageSignUpButton.click();

        String uyarı2 = hakimPage.emailBox.getAttribute("validationMessage");
        Assert.assertTrue(uyarı2.length() > 0,
                "Beklenen '@ sonrası eksik' uyarısı çıkmadı.");

        // =========================================================
        // 3. SENARYO: E-MAIL ALANINI BOŞ BIRAK.
        // =========================================================
        hakimPage.emailBox.clear();
        hakimPage.registerPageSignUpButton.click();

        String uyarı3 = hakimPage.emailBox.getAttribute("validationMessage");
        Assert.assertTrue(uyarı3.length() > 0,
                "Email alanı boş bırakıldığında beklenen uyarı çıkmadı.");

        // =========================================================
        // 4. SENARYO: GEÇERLİ E-MAIL GİR.
        // =========================================================
        hakimPage.emailBox.clear();
        hakimPage.emailBox.sendKeys("tester78@gmail.com");
        hakimPage.registerPageSignUpButton.click();

        String uyarı4 = hakimPage.emailBox.getAttribute("validationMessage");
        Assert.assertEquals(uyarı4, "",
                "Geçerli email olmasına rağmen uyarı çıktı.");

        Driver.quitDriver();
    }
}
