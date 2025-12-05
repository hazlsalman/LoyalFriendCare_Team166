package tests.us_035;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WadoudPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class TC08_DeleteFonksiyonuCalismasi {

    WadoudPage pages;
    @Test
    public void us_35_TC07_DeleteFonksiyonuCalismasi(){

        /**
         * Pet List ekranında herhangi bir kaydın “Delete” butonuna tıkla.
         * "AdSense deleted successfully" mesajinin görrunduğunu kontrol et
         * Listenin güncellenmesini bekle
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

        pages.petsAdsenseAltSekmesi1.click();

        pages.adsenseDeleteButton.click();

        ReusableMethods.bekle(1);

        Assert.assertTrue(pages.addSenseSuccessfullyDeleteMessage.isDisplayed());

        Driver.quitDriver();
    }
}
