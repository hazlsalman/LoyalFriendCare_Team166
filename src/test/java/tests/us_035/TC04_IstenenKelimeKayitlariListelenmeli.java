package tests.us_035;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WadoudPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC04_IstenenKelimeKayitlariListelenmeli {

    WadoudPage pages;
    @Test
    public void us_35_TC04_IstenenKelimeKayitlariListelenmeli(){

        /**
         * Arama kutusunu bulun.
         * “Cat” yazın.
         * Search / Enter işlemini gerçekleştirin.
         * Liste sonuçlarını inceleyin.
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

        pages.petSearchInput.sendKeys("cat" + Keys.ENTER);

        Assert.assertTrue(pages.searchResultIlkRow.isDisplayed());

        Driver.quitDriver();
    }
}
