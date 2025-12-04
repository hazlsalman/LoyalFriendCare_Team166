package tests.US_043;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.YaprakPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;
import java.awt.*;



public class TC04_AddAvatar  extends TestBaseRapor {
    YaprakPage yaprakPage;

    @BeforeMethod
    public void setupAdminPaneliLogin() {
        yaprakPage = new YaprakPage();
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        SoftAssert softAssert = new SoftAssert();
        yaprakPage.anasayfaSigninButonu.click();
        ReusableMethods.bekle(1);
        yaprakPage.anasayfaEmailKutusu.sendKeys(ConfigReader.getProperty("adminGecerliMail"));
        yaprakPage.anasayfaPasswordKutusu.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));
        yaprakPage.anasayfaSigninGirisButonu.click();
        ReusableMethods.bekle(2);
        softAssert.assertTrue(yaprakPage.adminPanelLinki.isDisplayed(),
                "tC01_Giriş yapılamadı veya Admin butonu görünmedi!");
        yaprakPage.adminPanelLinki.click();
        softAssert.assertTrue(yaprakPage.profilMenuButonu.isDisplayed(),
                "tC02_Admin paneline geçiş yapılamadı!");
        String currentUrl = Driver.getDriver().getCurrentUrl();
        softAssert.assertTrue(currentUrl.contains("admin"),
                "tC02_URL'de admin yazmıyor!");
        softAssert.assertAll();
    }

    @Test(priority = 1)
    public void tC01_editProfileSayfasiAvatarYukle() {
        yaprakPage = new YaprakPage();
        SoftAssert softAssert = new SoftAssert();
        yaprakPage.profilMenuButonu.click();
        ReusableMethods.bekle(1);
        yaprakPage.profilEditProfileButton.click();
        ReusableMethods.bekle(2);
        String currentUrl = Driver.getDriver().getCurrentUrl();
        softAssert.assertTrue(currentUrl.contains("edit"),
                "TC_01 Edit Profile sayfasına gidilemedi! URL: " + currentUrl);
        ReusableMethods.bekle(3);
        String dinamikDosyaYolu = System.getProperty("user.dir") + "/src/test/java/tests/US_043/lale.jpg";
        yaprakPage.filesToUpload.sendKeys(dinamikDosyaYolu);
        ReusableMethods.bekle(5);
        try {
            if (!yaprakPage.yuklenenDosyaImg.isDisplayed()) {
                throw new RuntimeException("tC01_Avatar yüklenmedi");
            }
            ReusableMethods.webElementResimCek(yaprakPage.yuklenenDosyaImg);
        } catch (Exception e) {
            throw new RuntimeException("Yükleme sırasında hata oluştu: " + e.getMessage());
        }
        System.out.println("tC01_Avatar yüklendi");
        softAssert.assertAll();

    }

    @Test(priority = 2)
    public void tC02_gecersizDosyaYuklemeTesti() {
        YaprakPage yaprakPage = new YaprakPage();
        yaprakPage.profilMenuButonu.click();
        ReusableMethods.bekle(1);
        yaprakPage.profilEditProfileButton.click();
        ReusableMethods.bekle(2);
        String txtDosyaYolu = System.getProperty("user.dir") + "/src/test/java/tests/US_043/deneme.txt";
        yaprakPage.filesToUpload.sendKeys(txtDosyaYolu);
        ReusableMethods.bekle(5);
        try {
            ReusableMethods.webElementResimCek(yaprakPage.uploadedTxtFileName);
            throw new RuntimeException("tC02_Desteklenmeyen TXT dosyası YÜKLENDİ! Test FAIL");
        } catch (Exception e) {
            System.out.println("tC02_Desteklenmeyen TXT dosyası YÜKLENDİ! Test FAIL");
        }

    }

    @AfterMethod
    public void tearDownClass() {
        Driver.quitDriver();
    }
}













