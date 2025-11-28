package tests.US_007;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Driver;
public class TC_02_SignUpPageAccess {

    @Test
    public void test01() {

        // 1- URL'e git.
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        // 2- Anasayfadaki "Sign Up" butonuna tıkla.
        pages.HakimPage hakimPage = new pages.HakimPage();
        hakimPage.homeSignUpButton.click();

        // 3- Kayıt sayfasına gidildiğini doğrula (URL kontrolü).
        String expectedUrl = "https://qa.loyalfriendcare.com/en/register";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        Assert.assertEquals(actualUrl, expectedUrl,
                "Kayıt (register) sayfasına ulaşılamadı!");

        // 4- Driver'ı kapat.
        Driver.quitDriver();
    }
}