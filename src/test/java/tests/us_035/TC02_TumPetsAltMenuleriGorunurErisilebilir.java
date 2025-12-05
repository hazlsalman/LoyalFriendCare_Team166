package tests.us_035;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WadoudPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC02_TumPetsAltMenuleriGorunurErisilebilir {

    WadoudPage pages;
    @Test
    public void us_35_TC02_TumPetsAltMenuleriGorunurErisilebilir(){

        /**
         * “Pets adsense” menüsünü genişlet.
         * Alt menülerin listelenmesini bekle.
         * Pet List, Add Pet vb. tüm alt menülerin görünürlüğünü doğrula.
         * Her alt menünün tıklanabilir olduğunu kontrol et.
         */

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
        Assert.assertTrue(pages.petsAdsenseAltSekmesi1.isDisplayed());
        Assert.assertTrue(pages.petsAdsenseAltSekmesi2.isDisplayed());

        Driver.quitDriver();
    }
}
