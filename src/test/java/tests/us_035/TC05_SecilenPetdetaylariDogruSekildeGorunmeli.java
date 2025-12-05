package tests.us_035;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WadoudPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC05_SecilenPetdetaylariDogruSekildeGorunmeli {

    WadoudPage pages;
    @Test
    public void us_35_TC05_SecilenPetdetaylariDogruSekildeGorunmeli(){

        /**
         * Herhangi bir pet kaydının bulunduğu satırı seç.
         * Satırdaki “Edit” butonuna tıkla.
         * Detay sayfasının yüklenmesini bekle.
         * Görüntülenen bilgilerin doğru olduğunu kontrol et.
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

        Driver.quitDriver();
    }
}
