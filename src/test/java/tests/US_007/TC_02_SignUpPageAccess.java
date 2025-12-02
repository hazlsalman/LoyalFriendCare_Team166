package tests.US_007;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HakimPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;

public class TC_02_SignUpPageAccess {

    HakimPage hakimPage = new HakimPage();

    @Test
    public void test01() throws InterruptedException {

        // =========================================================
        // PRE-CONDITION:
        // =========================================================

        //1-) Anasayfaya git.
        Driver.getDriver().get(ConfigReader.getProperty("url"));


        // =========================================================
        // SENARYO: KAYIT SAYFASINA ULAŞILIR.
        // =========================================================

        // 1-) Anasayfadaki "Sign Up" butonuna tıkla.
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.homePageSignUpButton)).click();


        // 2-) Kayıt sayfasına gidildiğini doğrula (URL kontrolü).
        String expectedUrl = "https://qa.loyalfriendcare.com/en/register";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        Assert.assertEquals(actualUrl, expectedUrl,
                "Kayıt (register) sayfasına ulaşılamadı.");

        // 3-) Driver'ı kapat.
        Driver.quitDriver();
    }

}