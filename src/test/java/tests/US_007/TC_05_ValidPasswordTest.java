package tests.US_007;

import com.github.javafaker.Faker;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HakimPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC_05_ValidPasswordTest {

    @Test
    public void test01() throws InterruptedException {

        HakimPage hakimPage = new HakimPage();
        Faker faker = new Faker();
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

        // --------------------------
        // PRE-CONDITION: 1) ANASAYFAYA GİT.
        // --------------------------
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        // --------------------------
        // PRE-CONDITION: 2) SIGN UP SAYFASINA GEÇİŞ
        // --------------------------
        hakimPage.homeSignUpButton.click();

        // ----------------------------------------
        // PRE-CONDITION: 3) USERNAME & EMAIL (Ön Şart)
        // ----------------------------------------
        String fakeUsername = faker.name().fullName();
        hakimPage.usernameBox.sendKeys(fakeUsername);
        hakimPage.emailBox.sendKeys(ConfigReader.getProperty("userGecerliMail"));


        // ------------------------------------------------

        // 1.STEP: SADECE RAKAMLARDAN OLUŞAN ŞİFRE
        hakimPage.passwordBox.clear();
        hakimPage.confirmPasswordBox.clear();

        hakimPage.passwordBox.sendKeys("12345678" + Keys.TAB);
        hakimPage.confirmPasswordBox.sendKeys("12345678");

        String msg1 = (String) js.executeScript("return arguments[0].validationMessage;", hakimPage.passwordBox);
        Assert.assertTrue(msg1.length() > 0,
                "1) Sadece rakamlardan oluşan şifre için beklenen native uyarı çıkmadı! (msg: " + msg1 + ")");


        // 2. STEP: SADECE HARFLERDEN OLUŞAN ŞİFRE
        hakimPage.passwordBox.clear();
        hakimPage.confirmPasswordBox.clear();

        hakimPage.passwordBox.sendKeys("abcdefgh" + Keys.TAB);
        hakimPage.confirmPasswordBox.sendKeys("abcdefgh");

        String msg2 = (String) js.executeScript("return arguments[0].validationMessage;", hakimPage.passwordBox);
        Assert.assertTrue(msg2.length() > 0,
                "2) Sadece harf içeren şifre için beklenen native uyarı çıkmadı! (msg: " + msg2 + ")");


        // 3.STEP: SADECE ÖZEL KARAKTERLERDEN OLUŞAN ŞİFRE
        hakimPage.passwordBox.clear();
        hakimPage.confirmPasswordBox.clear();

        hakimPage.passwordBox.sendKeys("!@#$%^&*" + Keys.TAB);
        hakimPage.confirmPasswordBox.sendKeys("!@#$%^&*");

        String msg3 = (String) js.executeScript("return arguments[0].validationMessage;", hakimPage.passwordBox);
        Assert.assertTrue(msg3.length() > 0,
                "3) Sadece özel karakterlerden oluşan şifre için beklenen native uyarı çıkmadı! (msg: " + msg3 + ")");


        // 4.STEP: Kriterleri içeriyor ama 8 karakterden kısa
        hakimPage.passwordBox.clear();
        hakimPage.confirmPasswordBox.clear();

        hakimPage.passwordBox.sendKeys("A1?!!" + Keys.TAB); // 5 karakter örnek
        hakimPage.confirmPasswordBox.sendKeys("A1?!!");

        String msg4 = (String) js.executeScript("return arguments[0].validationMessage;", hakimPage.passwordBox);
        Assert.assertTrue(msg4.length() > 0,
                "4) 8 karakterden kısa şifre için beklenen native uyarı çıkmadı! (msg: " + msg4 + ")");


        // 5. STEP: ŞİFRE KUTUSU BOŞ BIRAKILDIĞINDA
        hakimPage.passwordBox.clear();
        hakimPage.confirmPasswordBox.clear();

        hakimPage.registersignUpButton.click(); // submit etmeye çalış

        String msg5 = (String) js.executeScript("return arguments[0].validationMessage;", hakimPage.passwordBox);
        // hem Türkçe hem İngilizce kontrolü için genişçe bakıyoruz
        Assert.assertTrue(
                msg5.toLowerCase().contains("fill") ||
                        msg5.toLowerCase().contains("doldurun") ||
                        msg5.length() > 0,
                "5) Şifre alanı boş bırakıldığında beklenen 'fill out' uyarısı çıkmadı! (msg: " + msg5 + ")"
        );


        // 6.STEP: GEÇERLİ ŞİFRE (UYARI OLMAMALI)
        hakimPage.passwordBox.clear();
        hakimPage.confirmPasswordBox.clear();

        String validPwd = ConfigReader.getProperty("userGecerliPassword"); // örn: LFCare.0201
        hakimPage.passwordBox.sendKeys(validPwd + Keys.TAB);
        hakimPage.confirmPasswordBox.sendKeys(validPwd);

        String msg6 = (String) js.executeScript("return arguments[0].validationMessage;", hakimPage.passwordBox);
        Assert.assertEquals(msg6, "",
                "6) Geçerli şifre girilmesine rağmen native uyarı döndü! (msg: " + msg6 + ")");

        Driver.quitDriver();
    }
}

