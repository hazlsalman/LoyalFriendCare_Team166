package tests.US_010;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ArdaPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class TC004_SignOutRedirectTest {

    ArdaPage ardaPage = new ArdaPage();

    @Test
    public void signOutRedirectTest() {

        // 1) Home Page'e git
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        ReusableMethods.bekle(1);

        // 2) Sign In butonuna tıkla
        ardaPage.signInButton.click();
        ReusableMethods.bekle(1);

        // 3) Geçerli kullanıcı ile Login ol
        ardaPage.emailBox.sendKeys(ConfigReader.getProperty("userGecerliMail"));
        ardaPage.passwordBox.sendKeys(ConfigReader.getProperty("userGecerliPassword"));
        ardaPage.loginSubmitButton.click();
        ReusableMethods.bekle(2);

        // 4) Header’da Sign Out butonu görünmeli
        Assert.assertTrue(ardaPage.signOutButton.isDisplayed(),
                "Sign Out butonu header'da görünmüyor!");

        // 5) Sign Out butonuna tıkla
        ardaPage.signOutButton.click();
        ReusableMethods.bekle(2);

        // 6) Logout sonrası Home Page URL kontrolü
        String actualUrl = Driver.getDriver().getCurrentUrl();
        String expectedHomeUrl = "https://qa.loyalfriendcare.com/en";

        Assert.assertTrue(actualUrl.equals(expectedHomeUrl) || actualUrl.equals(expectedHomeUrl + "/"),
                "Logout sonrası Home Page'e yönlendirme başarısız! URL: " + actualUrl);

        // 7) Header'da kullanıcı adı artık görünmemeli
        Assert.assertTrue(ardaPage.signInButton.isDisplayed(),
                "Logout sonrası Sign In butonu görünmedi → kullanıcı hala login olabilir!");

        // 8) Tarayıcıyı kapat
        Driver.getDriver().quit();
    }
}
