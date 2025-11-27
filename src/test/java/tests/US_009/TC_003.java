package tests.US_009;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DogukanPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

public class TC_003 extends TestBaseRapor {
    @Test
    public void gecerliEmailTest() {
        DogukanPage dogukanPage = new DogukanPage();
        extentTest = extentReports.createTest("Gecerli email ile şifre sıfırlama bağlantısı testi",
                "Kullanici gecerli bilgilerle şifre sıfırlama bağlantısı alabilmeli");
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        extentTest.info("Kullanici Loyal Friend Care anasayfaya gider");

        dogukanPage.signInButton.click();
        extentTest.info("Sign in butonuna tiklar");

        dogukanPage.forgotMyPasswordButton.click();
        extentTest.info("Sifremi unuttum baglantisina tiklar");

        dogukanPage.emailBox.sendKeys("user.dogukan.sancaktar@loyalfriendcare.com");
        extentTest.info("Email kutusuna gecerli email adresini girer");

        dogukanPage.emailSendButton.click();
        extentTest.info("Sifre sifirlama baglantisi gonder butonuna tiklar");
        ReusableMethods.bekle(2);

        try {
            Alert alert = Driver.getDriver().switchTo().alert();
            String mesaj = alert.getText();

            alert.accept();

            Assert.fail("TEST FAIL - Sistem hata pop-up'ı verdi: " + mesaj);

        } catch (NoAlertPresentException e) {

        }
    }
}