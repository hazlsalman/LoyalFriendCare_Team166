package tests.US_027;

import pages.HakimPage;
import utilities.ConfigReader;
import utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_02_LoginPageAccess {

    HakimPage hakimPage = new HakimPage();

    @Test
    public void test01() {


        // =========================================================
        // PRE-CONDITION:
        // =========================================================

        //1-) Anasayfaya git.
        Driver.getDriver().get(ConfigReader.getProperty("url"));


        // =========================================================
        // SENARYO: LOGİN SAYFASINA ULAŞILIR.
        // =========================================================

        //1-) Anasayfadaki "Sign In" butonuna tıkla
        Assert.assertTrue(hakimPage.homePageSignInButton.isDisplayed(),
                "\"Sign In\" butonu görünmüyor.");
        hakimPage.homePageSignInButton.click();

        //2-) Login sayfasına gidildiğini doğrula (URL Kontrolü)
        String expectedUrl = "https://qa.loyalfriendcare.com/en/login";
        String actualUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl,
                "Login sayfasına yönlendirme başarısız.");

        //3-) Driver’ı kapat
        Driver.quitDriver();
    }
}

