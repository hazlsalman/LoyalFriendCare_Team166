package tests.US_042;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.YaprakPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class TC01_AdminPanelTest extends YaprakPage {

    @BeforeClass
    public void setupAdminLogin() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        anasayfaSigninButonu.click();
        ReusableMethods.bekle(1);

        anasayfaEmailKutusu.sendKeys(ConfigReader.getProperty("adminGecerliMail"));
        anasayfaPasswordKutusu.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));
        anasayfaSigninGirisButonu.click();

        ReusableMethods.bekle(3);
    }

    @Test(priority = 1)
    public void tC01_adminGirisDogrulama() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(adminPanelLinki.isDisplayed(),
                "Giriş yapılamadı veya Admin butonu görünmedi!");

        adminPanelLinki.click();
        ReusableMethods.bekle(2);

        softAssert.assertTrue(profilMenuButonu.isDisplayed(),
                "Admin paneline geçiş yapılamadı!");

        String currentUrl = Driver.getDriver().getCurrentUrl();
        softAssert.assertTrue(currentUrl.contains("admin"),
                "URL'de admin yazmıyor!");

        System.out.println("Admin paneline geçiş başarılı.");
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void tC02_adminProfileMenuErisim() {

        SoftAssert softAssert = new SoftAssert();

        profilMenuButonu.click();
        ReusableMethods.bekle(3);

        softAssert.assertTrue(profilMenuButonu.isDisplayed(),
                "tC02_Admin paneline geçiş yapılamadı!");

        String currentUrl = Driver.getDriver().getCurrentUrl();
        softAssert.assertTrue(currentUrl.contains("admin"),
                "tC02_URL'de admin yazmıyor!");

        System.out.println("tC02 Admin paneline geçiş başarılı.");

        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void tC03_adminProfilMenuResmiGorunurlukTesti() {

        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.bekle(3);

        boolean profilResmiGorunuyor = false;
        try {
            profilResmiGorunuyor = profilResmi.isDisplayed();
        } catch (Exception e) {

        }

        softAssert.assertTrue(profilResmiGorunuyor,
                "tC03_ AdminProfil menü icon/resmi sayfada BULUNAMADI! (Locator hatası veya resim yüklenmedi)");

        if (profilResmiGorunuyor) {
            System.out.println("tC03_ Profil resmi başarıyla doğrulandı.");
            ReusableMethods.webElementResimCek(profilResmi, "tC03_ ProfilResmi_Kanit");
        }

        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void tC04_profileMenusuGorunurMu() {
        profilMenuButonu.click();
        ReusableMethods.bekle(1);

        Assert.assertTrue(profilDropdownMenu.isDisplayed(),
                "tC04_Profil dropdown menüsü açılmadı!");

        System.out.println("tC04_AdminProfil menüsü çalışıyor.");
    }

    @Test(priority = 5)
    public void tC05_adminProfileAltMenuVeLogout() {
        // Menü kapalıysa aç
        if (!profilDropdownMenu.isDisplayed()) {
            profilMenuButonu.click();
            ReusableMethods.bekle(1);
        }

        // 1. Settings
        profilSettingsButton.click();
        ReusableMethods.bekle(3);
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("setting"),
                "tC05_Settings sayfasına gidilemedi! yanlış sayfa yönlendirilmesi + Geri tuşu aktif değil");
        Driver.getDriver().navigate().back();
        ReusableMethods.bekle(2);

        // 2. Edit Profile
        profilMenuButonu.click();
        ReusableMethods.bekle(1);
        profilEditProfileButton.click();
        ReusableMethods.bekle(3);
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("profile"),
                "tC05_ Edit Profile sayfasına gidilemedi!");
        Driver.getDriver().navigate().back();
        ReusableMethods.bekle(2);

        // 3. Logout
        profilMenuButonu.click();
        ReusableMethods.bekle(1);
        profilLogoutButton.click();
        ReusableMethods.bekle(3);

        Assert.assertTrue(anasayfaSigninButonu.isDisplayed(),
                "tC05_Logout işlemi başarısız!");

        System.out.println("Profil seçenekleri ve Logout başarılı.");
    }

    @AfterClass
    public void tearDownClass() {
        Driver.quitDriver();
    }
}
