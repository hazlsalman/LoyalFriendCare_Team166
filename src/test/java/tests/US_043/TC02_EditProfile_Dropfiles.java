    package tests.US_043;

    import org.openqa.selenium.support.ui.ExpectedConditions;
    import org.openqa.selenium.support.ui.WebDriverWait;
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
    import java.time.Duration;

    public class TC02_EditProfile_Dropfiles extends TestBaseRapor {

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
        public void test01_EditProfileAndVerifyRedirect() throws IOException {
            extentTest = extentReports.createTest("Profil Güncelleme ve Yönlendirme",
                    "Kullanıcı bilgileri günceller, kaydeder ve login sayfasına yönlendirildiğini doğrular.");
            SoftAssert softAssert = new SoftAssert();
            yaprakPage.profilMenuButonu.click();
            ReusableMethods.bekle(1);
            yaprakPage.profilEditProfileButton.click();
            ReusableMethods.bekle(2);
            extentTest.info("Edit Profile sayfasına gidildi.");
            yaprakPage.editPhoneBox.clear();
            yaprakPage.editPhoneBox.sendKeys("05322222222");
            yaprakPage.editPasswordBox.clear();
            yaprakPage.editPasswordBox.sendKeys("LFCare.0201");
            yaprakPage.editPasswordConfirmBox.clear();
            yaprakPage.editPasswordConfirmBox.sendKeys("LFCare.0201");
            yaprakPage.editEmailBox.clear();
            yaprakPage.editEmailBox.sendKeys("admin.hazal.salman@loyalfriendcare.com");
            extentTest.info("Form bilgileri  test01_EditProfileAndVerifyRedirect başarıyla dolduruldu.");
            ReusableMethods.bekle(2);
            yaprakPage.saveButton.click();
            ReusableMethods.bekle(2);
            String actualUrl = Driver.getDriver().getCurrentUrl();
            String expectedUrlPart = "login";
            softAssert.assertTrue(actualUrl.contains(expectedUrlPart),
                    " test01_EditProfileAndVerifyRedirect HATA: Profil güncellendikten sonra kullanıcı Login sayfasına yönlendirilmedi! \n" +
                            "Mevcut URL: " + actualUrl);
            if (actualUrl.contains("login")) {
                System.out.println("test01_EditProfileAndVerifyRedirect : Kullanıcı Login sayfasına yönlendirildi.");
                extentTest.pass("test01_EditProfileAndVerifyRedirect : Login sayfasına yönlendirme doğrulandı.");
            }
            softAssert.assertAll();
        }

        @AfterClass
        public void tearDownClass() {
            Driver.quitDriver();
        }
    }