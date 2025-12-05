package tests.US_011;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MelahatnurPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

public class pozıtıfSenaryo extends TestBaseRapor {


    @Test
    public void pozitifRandevuAlmaTesti() {

        extentTest = extentReports.createTest("Pozitif Randevu Testi",
                "Kullanıcı geçerli bilgilerle randevu oluşturur.");

        // 1) URL'e git
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        extentTest.info("Kullanıcı ana sayfaya gider.");
        ReusableMethods.bekle(4);

        MelahatnurPage melahatnurPage = new MelahatnurPage();

        // 2) Sign in butonuna tıkla
        melahatnurPage.signinButonu.click();
        extentTest.info("Kullanıcı sign in butonuna tıklar.");
        ReusableMethods.bekle(3);

        // 3) Geçerli kullanıcı bilgilerini gir
        melahatnurPage.mailKutusu.sendKeys(ConfigReader.getProperty("userGecerliMail"));
        extentTest.info("Kullanıcı geçerli e-mail adresini girer.");
        ReusableMethods.bekle(3);

        melahatnurPage.passwordKutusu.sendKeys(ConfigReader.getProperty("userGecerliPassword"));
        extentTest.info("Kullanıcı geçerli şifreyi girer.");
        ReusableMethods.bekle(3);

        // 4) Login
        melahatnurPage.girisButonu.click();
        extentTest.info("Kullanıcı başarılı şekilde giriş yapar.");
        ReusableMethods.bekle(3);

        Actions actions= new Actions(Driver.getDriver());
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        ReusableMethods.bekle(4);

        // 5) Doktor seçimi
        melahatnurPage.doktorKartAlejandro.click();
        extentTest.info("Kullanıcı randevu almak istediği doktoru seçer.");
        ReusableMethods.bekle(3);

        // 6) Randevu formunu doğru doldur
         melahatnurPage.randevuTarihKutusu.sendKeys("02/02/2026");
        extentTest.info("Kullanıcı geçerli bir tarih girer.");
        ReusableMethods.bekle(4);

        melahatnurPage.randevuTelefonKutusu.sendKeys("5551234567");
        extentTest.info("Kullanıcı geçerli bir telefon girer.");
        ReusableMethods.bekle(4);

        melahatnurPage.randevuMesajKutusu.sendKeys("Randevu talep ediyorum.");
        extentTest.info("Kullanıcı mesaj alanını doldurur.");
        ReusableMethods.bekle(3);

        melahatnurPage.appointmentBookingButonu.click();
        extentTest.info("Kullanıcı randevu alma butonuna tıklar.");
        ReusableMethods.bekle(5);

        String expected = "Congratulations on your well-deserved success.";
        String actual = melahatnurPage.randevuOnayMesaji.getText();

        Assert.assertEquals(actual, expected,
                "Pozitif randevu testinde başarı mesajı doğrulandı.");
        ReusableMethods.bekle(4);

        extentTest.pass("Randevu formu doğru çalıştı ve başarı mesajı gösterildi.");

        extentTest.pass("sayfa kapatilir");
    }
}
