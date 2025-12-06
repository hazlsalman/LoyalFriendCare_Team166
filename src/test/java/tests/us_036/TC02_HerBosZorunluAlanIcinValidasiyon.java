package tests.us_036;

import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WadoudPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class TC02_HerBosZorunluAlanIcinValidasiyon {

    WadoudPage pages;
    @Test
    public void us_36_TC02_HerBosZorunluAlanIcinValidasiyon(){

        /**
         * "Create Pets adsense" tiklandiginda,Pets adsense formunu aç.
         * Formdaki zorunlu alanları kontrol et (LOCATION, PETS TITLE, YOUR DISPLAY NAME, TYPE).
         * Zorunlu alanları boş bırakıp Save butonuna tıkla.
         * adsense Pets listesinde eklenmedigini kontrol et
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

        pages.petsAdsenseAltSekmesi2.click();

        Faker faker = new Faker();

        pages.petCreateInput1.sendKeys("");
        pages.petCreateInput2.sendKeys("");
        pages.controlPetActivePetButton.click();
        pages.typeCodeImageButton.click();
        pages.petCreateImageUrl.sendKeys(faker.internet().url());
        pages.typeCodeAdsenseButton.click();
        pages.petCreateAdsenseTextInput.sendKeys(faker.funnyName().name());

        Actions actions1 = new Actions(Driver.getDriver());
        actions1.sendKeys(Keys.PAGE_DOWN);

        ReusableMethods.bekle(1);
        pages.afterEditButtonClickSAVEButton.click();
        ReusableMethods.bekle(1);
        Assert.assertTrue(pages.petCreateInput1.isDisplayed());

        Driver.quitDriver();
    }
}
