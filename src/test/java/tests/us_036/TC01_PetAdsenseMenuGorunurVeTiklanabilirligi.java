package tests.us_036;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WadoudPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC01_PetAdsenseMenuGorunurVeTiklanabilirligi {

    WadoudPage pages;

    @Test
    public void us_36_TC01_PetAdsenseMenuGorunurVeTiklanabilirligi (){
        /**
         * Sol menüde “Pets adsense” menüsünü görüntüle.
         * Pets menüsünü tıkla ve alt menülerin açıldığını doğrula.
         * “Create Pets adsense” alt menüsünün görünür ve tıklanabilir olduğunu doğrula.
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

        pages.petsAdsenseAltSekmesi2.click();

        Assert.assertTrue(pages.petCreateInput1.isDisplayed());

        Driver.quitDriver();
    }

}
