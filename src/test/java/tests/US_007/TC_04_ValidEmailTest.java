package tests.US_007;


import com.github.javafaker.Faker;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HakimPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC_04_ValidEmailTest {

    @Test
    public void GecersizVeGecerliEmailKontrolleri() throws InterruptedException {

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

        // --------------------------
        // PRE-CONDITION: 3) USERNAME DOLDUR
        // --------------------------
        Assert.assertTrue(hakimPage.usernameBox.isDisplayed(), "USERNAME kutusu görünmüyor!");
        hakimPage.usernameBox.sendKeys(faker.name().username());


        // =========================================================
        // 1. SENARYO : '@' OLMAYAN E-MAIL
        // =========================================================
        hakimPage.emailBox.clear();
        hakimPage.emailBox.sendKeys("tester78.gmail.com");   // '@' yok

        hakimPage.registersignUpButton.click();

        String uyari1 = (String) js.executeScript(
                "return arguments[0].validationMessage;", hakimPage.emailBox);

        Assert.assertTrue(
                uyari1.length() > 0,
                "Beklenen '@ işareti eksik' uyarısı çıkmadı!"
        );


        // =========================================================
        // 2. SENARYO : '@' SONRASI OLMAYAN E-MAIL
        // =========================================================
        hakimPage.emailBox.clear();
        hakimPage.emailBox.sendKeys("tester78@");  // '@' sonrası yok

        hakimPage.registersignUpButton.click();

        String uyari2 = (String) js.executeScript(
                "return arguments[0].validationMessage;", hakimPage.emailBox);

        Assert.assertTrue(
                uyari2.length() > 0,
                "Beklenen '@ sonrası eksik' uyarısı çıkmadı!"
        );


        // =========================================================
        // 3. SENARYO : E-MAIL ALANI TAMAMEN BOŞ
        // =========================================================
        hakimPage.emailBox.clear();

        hakimPage.registersignUpButton.click();

        String uyari3 = (String) js.executeScript(
                "return arguments[0].validationMessage;", hakimPage.emailBox);

        // Türkçe + İngilizce tüm browser ihtimalleri
        Assert.assertTrue(
                uyari3.toLowerCase().contains("doldurun") ||
                        uyari3.toLowerCase().contains("fill") ||
                        uyari3.toLowerCase().contains("please") ||
                        uyari3.length() > 0,
                "Boş bırakıldığında zorunlu alan uyarısı çıkmadı!"
        );


        // =========================================================
        // 4. SENARYO : GEÇERLİ E-MAIL (Uyarı çıkmamalı)
        // =========================================================
        hakimPage.emailBox.clear();
        hakimPage.emailBox.sendKeys("tester78@gmail.com");

        hakimPage.registersignUpButton.click();

        String uyari4 = (String) js.executeScript(
                "return arguments[0].validationMessage;", hakimPage.emailBox);

        Assert.assertEquals(
                uyari4,
                "",
                "Geçerli email olmasına rağmen uyarı çıktı!"
        );

        Thread.sleep(1500);
        Driver.quitDriver();
    }
}
