package tests.us_035;

import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WadoudPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class TC07_CreateNewPetFonksiyonuCalismasi {

    WadoudPage pages;
    @Test
    public void us_35_TC07_DeleteFonksiyonuCalismasi(){

        /**
         * Yeni Pet Listeye Ekle
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

        pages.petCreateInput1.sendKeys(faker.cat().name());
        pages.petCreateInput2.sendKeys(faker.cat().name());
        pages.controlPetActivePetButton.click();
        pages.typeCodeImageButton.click();
        pages.petCreateImageUrl.sendKeys(faker.internet().url());
        pages.typeCodeAdsenseButton.click();
        pages.petCreateAdsenseTextInput.sendKeys(faker.funnyName().name());
        pages.afterEditButtonClickSAVEButton.click();

        Actions actions1 = new Actions(Driver.getDriver());
        actions1.sendKeys(Keys.PAGE_DOWN);
        ReusableMethods.bekle(1);
        Assert.assertTrue(pages.successMessageNewPetAdded.isDisplayed());

        Driver.quitDriver();
    }
}
