package tests.US_012;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EnesPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

public class TC_05 extends TestBaseRapor {
    @Test
    public void dogruRandevuAlmaTesti() {
        extentTest = extentReports.createTest("Randevu testi",
                "Kullanici randevu almakiçin randevu formunu doldurur");

        Driver.getDriver().get(ConfigReader.getProperty("url"));
        ReusableMethods.bekle(4);
        extentTest.info("Kullanıcı url ile ansayfaya erişim sağlar.");
        EnesPage enesPage = new EnesPage();
        enesPage.signInButton.click();
        ReusableMethods.bekle(3);
        extentTest.info("Kullanıcı sign in butonuna click yapar.");
        enesPage.userNameKutu.sendKeys(ConfigReader.getProperty("userGecerliMail"));
        ReusableMethods.bekle(3);
        extentTest.info("Kullanıcı, username'ini doğru bir şekilde girer.");
        enesPage.passwordKutu.sendKeys(ConfigReader.getProperty("userGecerliPassword"));
        ReusableMethods.bekle(3);
        extentTest.info("Kullanıcı, şifresini doğru bir şekilde girer.");
        enesPage.logInSignIn.click();
        ReusableMethods.bekle(4);
        extentTest.info("Kullanıcı, log in olmak için sign in butonuna click yapar.");

        Actions actions= new Actions(Driver.getDriver());
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        ReusableMethods.bekle(4);

        enesPage.bodyDrAlejandroMartinez.click();
        ReusableMethods.bekle(4);
        extentTest.info("Kullanıcı, randevu almak istediği doktoru seçer.");

        //Randevu formunda tarih telefon ve mesaj bölümü boş bırakılmamalıdır.
        // Aksi taktirde "Congratulations on your well-deserved success." yazısı görünür olmamalıdır.

        enesPage.randevuAlmaButon.click();
        ReusableMethods.bekle(9);
        extentTest.info("Randevu formuun doğru çalışması kontrol edilmesi için tarih, " +
                "telefon ve mesaj formu boş bırakılırarak randevu alma butonuna click yapılır");

        String expectedrandevuOnayYazi=enesPage.randevuOnay.getText();
        String actualrandevuOnayYazi="Congratulations on your well-deserved success.";
        Assert.assertFalse(actualrandevuOnayYazi.equals(expectedrandevuOnayYazi),
                "Randevu formunda boş alanlar olmasına rağmen randevu onay mesajı alındı");

        extentTest.pass(" boş bırakılan randevu formunda başarısız randevu formunun görünür olduğunu test eder");

        extentTest.pass(" sayfa kapatılır");
    }
}
