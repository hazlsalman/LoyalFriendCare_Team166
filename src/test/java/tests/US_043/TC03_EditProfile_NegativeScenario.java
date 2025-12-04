package tests.US_043;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.YaprakPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

import java.io.IOException;

public class TC03_EditProfile_NegativeScenario extends TestBaseRapor {

    YaprakPage yaprakPage;

    @BeforeClass
    //admin paneline giriş yapılmış olmalı
    public void setupAdminPaneliLogin() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        yaprakPage = new YaprakPage();
        yaprakPage.anasayfaSigninButonu.click();
        ReusableMethods.bekle(1);
        yaprakPage.anasayfaEmailKutusu.sendKeys(ConfigReader.getProperty("adminGecerliMail"));
        yaprakPage.anasayfaPasswordKutusu.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));
        yaprakPage.anasayfaSigninGirisButonu.click();
        ReusableMethods.bekle(2);
        if (yaprakPage.adminPanelLinki.isDisplayed()) {
            yaprakPage.adminPanelLinki.click();
            ReusableMethods.bekle(2);
            System.out.println("Admin paneline giriş başarılı.");
        } else {
            throw new RuntimeException("Admin paneline giriş yapılamadı!");
        }
    }

    @Test
    public void test02_EditProfile_NegativeScenario() throws IOException {
        extentTest = extentReports.createTest("Edit Profile Negatif Senaryo",
                "Hatalı bilgiler girildiğinde profil güncelleme işleminin yapılmaması doğrulanır.");
        SoftAssert softAssert = new SoftAssert();
        yaprakPage.profilMenuButonu.click();
        ReusableMethods.bekle(1);
        yaprakPage.profilEditProfileButton.click();
        ReusableMethods.bekle(1);
        yaprakPage.editPhoneBox.clear();
        yaprakPage.editPhoneBox.sendKeys("123abc");
        //NOT:👀 Negatif Test yanlış veriler ile test edilmiş ve failed oldugu doğrulanmıştır.
        //Ancak diğer arkadaslar ile ortak kullanılan admin bilgileri oldugu için;
        // sorun yaşanmaması adına aşağıdaki doğru şifreler  tekrar geri yazılmıştır.
        yaprakPage.editPasswordBox.clear();
        yaprakPage.editPasswordBox.sendKeys("LFCare.0201"); //aaaa
        yaprakPage.editPasswordConfirmBox.clear();
        yaprakPage.editPasswordConfirmBox.sendKeys("LFCare.0201");//0000
        yaprakPage.editEmailBox.clear();
        yaprakPage.editEmailBox.sendKeys("admin.hazal.salman@loyalfriendcare.com");
        yaprakPage.saveButton.click();
        ReusableMethods.bekle(2);
        String actualUrl = Driver.getDriver().getCurrentUrl();
        if (actualUrl.contains("login")) {
            extentTest.fail(" Hatalı bilgilerle SAVE yapıldı ve login sayfasına yönlendirildi!");
            Assert.fail("Negatif senaryo failed !  Hatalı bilgilerle SAVE yapıldı ve login sayfasına yönlendirildi! HATA Uyarısı VERMEDİ! URL: " + actualUrl);
        } else {
            extentTest.pass("Negatif senaryo doğru: Hatalı bilgilerle işlem yapılmadı, yönlendirme yok.");
        }
    }
    @AfterClass
    public void tearDownClass() {
        Driver.quitDriver();
    }

}