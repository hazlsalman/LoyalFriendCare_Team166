package tests.US_010;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ArdaPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class TC002_LogoRedirectTest {

    ArdaPage ardaPage = new ArdaPage();

    @Test
    public void logoRedirectTest() {

        // 1) Siteye git
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        ReusableMethods.bekle(1);

        // 2) Doctors sayfasına tıkla
        ardaPage.doctorsLink.click();
        ReusableMethods.bekle(1);

        // 3) Doctors sayfasına geçildiğini doğrula
        String currentUrl = Driver.getDriver().getCurrentUrl().toLowerCase();
        Assert.assertTrue(currentUrl.contains("doctors"),
                "Doctors sayfasına geçilemedi!");

        // 4) Logo görünür mü?
        Assert.assertTrue(ardaPage.logo.isDisplayed(), "Logo görünmüyor!");

        // 5) Logo tıklanabilir mi?
        Assert.assertTrue(ardaPage.logo.isEnabled(), "Logo tıklanabilir değil!");

        // 6) Logo’ya tıklayınca Home Page'e dönmeli
        ardaPage.logo.click();
        ReusableMethods.bekle(1);

        String actualUrl = Driver.getDriver().getCurrentUrl();
        String expectedUrl = "https://qa.loyalfriendcare.com/en";

        Assert.assertEquals(actualUrl, expectedUrl,
                "Logo tıklanınca Home Page'e yönlendirme başarısız!");

        // 7) Tarayıcıyı kapat
        Driver.getDriver().quit();
    }
}
