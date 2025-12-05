package tests.US_006;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EbruPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

public class TC_04_Contacts extends TestBaseRapor {

    @Test
    public void contactsBolumuTelefonVeEmailLinkleriTesti() {

        extentTest = extentReports.createTest(
                "US_006 - TC_04 Contacts bölümü, telefon ve e-posta linkleri",
                "Contacts başlığı, adres, telefon ve e-posta bilgileri ile tel:/mailto: linklerinin doğrulanması."
        );

        String url = ConfigReader.getProperty("url");
        Driver.getDriver().get(url);
        extentTest.info("Kullanıcı siteye gider: " + url);

        // Footer’a scroll
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        ReusableMethods.bekle(2);
        extentTest.info("Kullanıcı sayfanın en altına scroll eder.");

        EbruPage ebruPage = new EbruPage();

        // Footer ve Contacts başlığı görünür mü?
        Assert.assertTrue(ebruPage.footerArea.isDisplayed(), "Footer alanı görünmüyor.");
        Assert.assertTrue(ebruPage.contactsBasligi.isDisplayed(), "\"Contacts\" başlığı görünmüyor.");
        extentTest.pass("\"Contacts\" başlığı footer içinde görünüyor.");

        // Adres, telefon ve e-posta satırları görünür mü?
        Assert.assertTrue(ebruPage.contactsAdres.isDisplayed(), "Adres bilgisi görünmüyor.");
        Assert.assertTrue(ebruPage.phoneLink.isDisplayed(), "Telefon linki görünmüyor.");
        Assert.assertTrue(ebruPage.emailLink.isDisplayed(), "E-posta linki görünmüyor.");
        extentTest.pass("Contacts altında adres, telefon ve e-posta bilgilerinin göründüğü doğrulandı.");

        // Telefon numarası text kontrolü
        String expectedPhone = "+15596938754";
        String actualPhoneText = ebruPage.phoneLink.getText().trim();
        Assert.assertEquals(actualPhoneText, expectedPhone, "Telefon numarası beklenen formatta değil.");
        extentTest.pass("Telefon numarası +" + expectedPhone + " formatında görüntüleniyor.");

        // Telefon linkinin href kontrolü (tel: tetikleniyor mu?)
        String phoneHref = ebruPage.phoneLink.getText();
        Assert.assertTrue(phoneHref.contains("tel:+15596938754") || phoneHref.contains("tel:%2B15596938754"),
                "Telefon linki tel:+15596938754 içermiyor. href=" + phoneHref);
        extentTest.pass("Telefon linkinin tel:+15596938754 ile tanımlandığı doğrulandı.");

        // E-posta text kontrolü
        String expectedEmail = "info@loyalfriendcare.com";
        String actualEmailText = ebruPage.emailLink.getText().trim();
        Assert.assertEquals(actualEmailText, expectedEmail, "E-posta adresi beklenen şekilde değil.");
        extentTest.pass("E-posta adresi " + expectedEmail + " olarak görüntüleniyor.");

        // E-posta linkinin href kontrolü
        String emailHref = ebruPage.emailLink.getAttribute("href");
        Assert.assertTrue(emailHref.contains("mailto:info@loyalfriendcare.com"),
                "E-posta linki mailto:info@loyalfriendcare.com içermiyor. href=" + emailHref);
        extentTest.pass("E-posta linkinin mailto:info@loyalfriendcare.com ile tanımlandığı doğrulandı.");

        extentTest.pass("US_006 - TC_04 testi başarıyla tamamlandı.");
    }
}