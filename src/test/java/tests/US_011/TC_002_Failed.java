package tests.US_011;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MelahatnurPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

public class TC_002_Failed extends TestBaseRapor {

    @Test
    public void eksikFormIleRandevuAlmaTesti() {

        extentTest = extentReports.createTest("Eksik Form ile Randevu Alma Testi",
                "Kullanıcının zorunlu alanları doldurmadan randevu alamadığının doğrulanması");

        MelahatnurPage melahatnurPage = new MelahatnurPage();

        // 1. Siteye git
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        extentTest.info("Kullanıcı siteye gider.");

        // 2. Giriş ekranı
        melahatnurPage.signinButonu.click();
        extentTest.info("Kullanıcı 'Sign In' butonuna tıklar.");

        melahatnurPage.mailKutusu.sendKeys(ConfigReader.getProperty("userGecerliMail"));
        extentTest.info("Kullanıcı geçerli mail adresini girer.");

        melahatnurPage.passwordKutusu.sendKeys(ConfigReader.getProperty("userGecerliPassword"));
        extentTest.info("Kullanıcı geçerli şifreyi girer.");

        melahatnurPage.girisButonu.click();
        extentTest.info("Kullanıcı sisteme giriş yapar.");

        // 3. Wellness → Doctor seçimi
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(melahatnurPage.wellnessButonu).perform();
        extentTest.info("Kullanıcı Wellness menüsünün üzerine gelir.");

        melahatnurPage.doktorKartAlejandro.click();
        extentTest.info("Kullanıcı Dr. Alejandro kartına tıklar.");

        // 4. Eksik form doldurma → Tarih alanı boş bırakılacak
        melahatnurPage.randevuTelefonKutusu.sendKeys("5551234567");
        extentTest.info("Kullanıcı telefon numarasını girer.");

        melahatnurPage.randevuMesajKutusu.sendKeys("Randevu talep ediyorum.");
        extentTest.info("Kullanıcı mesaj alanına metin girer (Tarih boş bırakıldı).");

        melahatnurPage.appointmentBookingButonu.click();
        extentTest.info("Kullanıcı randevu alma butonuna tıklar.");

        ReusableMethods.bekle(2);

        // 5. Beklenen hata mesajını doğrula
        String expected = "Please fill out the form completely.";
        String actual = melahatnurPage.randevuOnayMesaji.getText();

        Assert.assertEquals(actual, expected,
                "Eksik form ile randevu alınamaması gerekirken başarı mesajı görüldü!");
        extentTest.pass("Eksik form doldurulduğunda hata mesajının geldiği doğrulandı.");

    }
}
