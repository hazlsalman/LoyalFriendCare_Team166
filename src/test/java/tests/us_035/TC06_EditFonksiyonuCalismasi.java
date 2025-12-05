package tests.us_035;

import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WadoudPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC06_EditFonksiyonuCalismasi {

    WadoudPage pages;
    @Test
    public void us_35_TC06_EditFonksiyonuCalismasi(){

        /**
         * Pet List’te bir kaydın “Edit” butonuna tıkla.
         * Formun yüklenmesini bekle.
         * Pet Name alanını güncelle.
         * "Save" butonuna tıkla.
         * Güncellenmiş kaydın listede doğru göründüğünü doğrula.
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

        pages.petSearchInput.sendKeys("a" + Keys.ENTER);

        pages.searchResultEditButton.click();

        Assert.assertTrue(pages.afterEditButtonPressResult.isDisplayed());

        Faker faker = new Faker();

        pages.afterEditButtonPetNameInput1.clear();
        pages.afterEditButtonPetNameInput1.sendKeys(faker.cat().name());

        pages.afterEditButtonClickSAVEButton.click();

        Assert.assertTrue(pages.afterSaveErrorMessage.isDisplayed());

        System.out.println(pages.afterSaveErrorMessage.getText());
        Driver.quitDriver();
    }
}
