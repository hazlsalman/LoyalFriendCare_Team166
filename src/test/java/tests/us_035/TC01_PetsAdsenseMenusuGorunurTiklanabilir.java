package tests.us_035;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WadoudPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class TC01_PetsAdsenseMenusuGorunurTiklanabilir {

    WadoudPage pages;
    @Test
    public void us_35_TC01_PetsAdsenseMenusuGorunurTiklanabilir(){

        /**
         * Dashboard ekranının tamamen yüklenmesini bekle.
         * Sol açılır menüye bak.
         * “Pets adsense” menüsünün görünür olup olmadığını kontrol et.
         * Menüye tıklayarak erişilebilir olup olmadığını doğrula.
         * */

        pages = new WadoudPage();
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        pages.mainSignInButton.click();

        pages.loginPageSignInEmail.sendKeys(ConfigReader.getProperty("adminGecerliMail"));

        pages.loginPageSignInPassword.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));

        pages.loginPageSignInButton.click();

        pages.mainSignInButton.click();

        Actions actions = new Actions(Driver.getDriver());
        actions.moveToLocation(10,540).perform();

        pages.menuAdsenseButton.click();

        Assert.assertTrue(pages.petsAdsenseAltSekmeleri.isDisplayed());

        Driver.quitDriver();
    }
}
