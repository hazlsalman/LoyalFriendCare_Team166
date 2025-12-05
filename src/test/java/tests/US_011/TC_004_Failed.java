package tests.US_011;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MelahatnurPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

public class TC_004_Failed extends TestBaseRapor {

    //Geçersiz ve eksik Phone Number girişlerini doğrulamak failed
    @Test
    public void GecersizPhoneTest (){
        extentTest = extentReports.createTest("Negatif Randevu Testi",
                "Kullanıcı geçersiz telefon numarası ile randevu oluşturamaz ve hata mesajı görüntülenir.");

        // 1) URL'e git
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        extentTest.info("Kullanıcı ana sayfaya gider.");

        MelahatnurPage melahatnurPage = new MelahatnurPage();

        // 2) Sign in butonuna tıkla
        melahatnurPage.signinButonu.click();
        extentTest.info("Kullanıcı sign in butonuna tıklar.");

        // 3) Geçerli kullanıcı bilgilerini gir
        melahatnurPage.mailKutusu.sendKeys(ConfigReader.getProperty("userGecerliMail"));
        extentTest.info("Kullanıcı geçerli e-mail adresini girer.");

        melahatnurPage.passwordKutusu.sendKeys(ConfigReader.getProperty("userGecerliPassword"));
        extentTest.info("Kullanıcı geçerli şifreyi girer.");

        // 4) Login
        melahatnurPage.girisButonu.click();
        extentTest.info("Kullanıcı başarılı şekilde giriş yapar.");
        ReusableMethods.bekle(2);

        // 5) Doktor seçimi
        melahatnurPage.doktorKartAlejandro.click();
        extentTest.info("Kullanıcı randevu almak istediği doktoru seçer.");
        ReusableMethods.bekle(2);

        // 6) Randevu formuna geçersiz telefon numarası gir
        melahatnurPage.randevuTarihKutusu.sendKeys("02/02/2026");
        extentTest.info("Kullanıcı geçerli bir tarih girer.");
        ReusableMethods.bekle(1);

        melahatnurPage.randevuTelefonKutusu.sendKeys("0000@!abhg*");
        extentTest.info("Kullanıcı geçersiz bir telefon numarası girer.");
        ReusableMethods.bekle(1);

        melahatnurPage.randevuMesajKutusu.sendKeys("Randevu talep ediyorum.");
        extentTest.info("Kullanıcı mesaj alanını doldurur.");

        melahatnurPage.appointmentBookingButonu.click();
        extentTest.info("Kullanıcı randevu alma butonuna tıklar.");
        ReusableMethods.bekle(2);

        // 7) Beklenen hata mesajını doğrula
        String expected = "The phone number must consist of 10 digits.";
        String actual = melahatnurPage.randevuOnayMesaji.getText();

        extentTest.info("UI üzerinde görünen hata mesajı: " + actual);

        Assert.assertTrue(actual.contains(expected),
                "Hata mesajı beklenen formatta gelmelidir.");

        extentTest.pass("Geçersiz telefon numarası doğru şekilde yakalandı ve hata mesajı görüntülendi.");

        extentTest.pass("Sayfa kapatılır.");
    }
}