package tests.US_013;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EnesPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBaseRapor;

public class TC_05  extends TestBaseRapor {
    @Test
    public void dogruRandevuAlmaTesti() {

        Driver.getDriver().get(ConfigReader.getProperty("url"));

        EnesPage enesPage = new EnesPage();
        enesPage.signInButton.click();
        enesPage.userNameKutu.sendKeys(ConfigReader.getProperty("userGecerliMail"));
        enesPage.passwordKutu.sendKeys(ConfigReader.getProperty("userGecerliPassword"));
        enesPage.logInSignIn.click();
        enesPage.bodyDrAlejandroMartinez.click();

        extentTest = extentReports.createTest("Randevu testi",
                "Kullanici randevu almakiçin randevu formunu doldurur");

        //Randevu formunda tarih telefon ve mesaj bölümü boş bırakılmamalıdır.
        // Aksi taktirde "Congratulations on your well-deserved success." yazısı görünür olmamalıdır.

        enesPage.randevuAlmaButon.click();
        extentTest.info("Randevu formuun doğru çalışması kontrol edilmesi için tarih, " +
                "telefon ve mesaj formu boş bırakılır");


        String unexpectedrandevuOnayYazi="Congratulations on your well-deserved success.";
        String actualrandevuOnayYazi=enesPage.randevuOnay.getText();
        Assert.assertFalse(actualrandevuOnayYazi.equals(unexpectedrandevuOnayYazi),
                "Randevu formunda boş alanlar olmasına rağmen randevu onay mesajı alındı");


        extentTest.pass(" boş bırakılan randevu formunda başarısız randevu formunun görünür olduğunu test eder");











    }
}
