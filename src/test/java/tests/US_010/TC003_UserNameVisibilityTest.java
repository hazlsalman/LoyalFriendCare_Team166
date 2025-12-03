package tests.US_010;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ArdaPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class TC003_UserNameVisibilityTest {

    ArdaPage ardaPage = new ArdaPage();

    @Test
    public void userNameVisibilityTest() {

        // 1) Siteye git
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        ReusableMethods.bekle(1);

        // 2) Sign In butonuna tıkla
        ardaPage.signInButton.click();
        ReusableMethods.bekle(1);

        // 3) Geçerli admin maili ve şifresi ile giriş yap
        ardaPage.emailBox.sendKeys(ConfigReader.getProperty("adminGecerliMail"));
        ardaPage.passwordBox.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));

        // 4) Login Submit
        ardaPage.loginSubmitButton.click();
        ReusableMethods.bekle(2);

        // 5) Header'da kullanıcı adı görünür mü?
        Assert.assertTrue(ardaPage.headerUserName.isDisplayed(),
                "Header kullanıcı adı görünmüyor!");

        // 6) Header’da görünen kullanıcı adı doğru mu?
        String actualUserName = ardaPage.headerUserName.getText().trim();
        String expectedUserName = "admin.hazal.salman";

        Assert.assertEquals(actualUserName, expectedUserName,
                "Görünen kullanıcı adı yanlış!");

        // 7) Browser kapat
        Driver.getDriver().quit();
    }
}
