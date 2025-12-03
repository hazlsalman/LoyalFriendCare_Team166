package tests.US_043;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.YaprakPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class TC01_ImageCheckbox extends YaprakPage {

    @BeforeClass
    public void setupAdminPaneliLogin() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        SoftAssert softAssert = new SoftAssert();

        anasayfaSigninButonu.click();
        ReusableMethods.bekle(1);
        anasayfaEmailKutusu.sendKeys(ConfigReader.getProperty("adminGecerliMail"));
        anasayfaPasswordKutusu.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));
        anasayfaSigninGirisButonu.click();
        ReusableMethods.bekle(2);


        softAssert.assertTrue(adminPanelLinki.isDisplayed(),
                "tC01_Giriş yapılamadı veya Admin butonu görünmedi!");

        adminPanelLinki.click();
        ReusableMethods.bekle(2);

        softAssert.assertTrue(profilMenuButonu.isDisplayed(),
                "tC02_Admin paneline geçiş yapılamadı!");

        String currentUrl = Driver.getDriver().getCurrentUrl();
        softAssert.assertTrue(currentUrl.contains("admin"),
                "tC02_URL'de admin yazmıyor!");

        System.out.println("tC02 Admin paneline geçiş başarılı.");
        softAssert.assertAll();
    }


    @Test(priority = 1)
    public void tC01_editProfileSayfasinaYonlendirme() {
        SoftAssert softAssert = new SoftAssert();
        profilMenuButonu.click();
        ReusableMethods.bekle(1);
        profilEditProfileButton.click();
        ReusableMethods.bekle(2);
        String currentUrl = Driver.getDriver().getCurrentUrl();
        softAssert.assertTrue(currentUrl.contains("edit"),
                "TC_01 Edit Profile sayfasına gidilemedi! URL: " + currentUrl);
        softAssert.assertAll();
        ReusableMethods.bekle(2);
    }


    @Test(priority = 2, dependsOnMethods = "tC01_editProfileSayfasinaYonlendirme")
    public void tC02_dontChangeImageCheckbox(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(dontChangeImageCheckbox.isEnabled(),
                "tC02_Checkbox tıklanabilir değil (Enabled değil)!");
        dontChangeImageCheckbox.click();
        ReusableMethods.bekle(1);
        softAssert.assertTrue(dontChangeImageCheckbox.isSelected(),
                "tC02_Checkbox tıklandı ama seçili hale gelmedi!");
        System.out.println("dontChangeImageCheckbox tıklanabilir.");
        ReusableMethods.webElementResimCek(dontChangeImageCheckbox, "US_43_tC02_Checkbox_Secili_Hali");
        softAssert.assertAll();
    }

    @AfterClass
    public void tearDownClass() {
        Driver.quitDriver();
    }
}