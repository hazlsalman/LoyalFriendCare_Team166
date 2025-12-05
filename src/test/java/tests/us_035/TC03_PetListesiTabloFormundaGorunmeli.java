package tests.us_035;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WadoudPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC03_PetListesiTabloFormundaGorunmeli {

    WadoudPage pages;
    @Test
    public void us_35_TC03_PetListesiTabloFormundaGorunmeli(){

        /**
         * Sol menüden Pets adsense menüsünü genişlet.
         * “Pet List” alt menüsüne tıkla.
         * Sayfanın yüklenmesini bekle.
         * Tablo yapısının görünür olduğunu kontrol et.
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

        Assert.assertTrue(pages.petManagementYazisi.isDisplayed());

        Driver.quitDriver();
    }
}
