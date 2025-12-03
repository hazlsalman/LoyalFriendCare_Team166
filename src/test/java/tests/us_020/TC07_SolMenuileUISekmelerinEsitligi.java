package tests.us_020;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WadoudPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC07_SolMenuileUISekmelerinEsitligi {

    WadoudPage pages;
    @Test
    public void us_020_TC07_SolMenuileUISekmelerinEsitligi(){

        pages = new WadoudPage();
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        pages.mainSignInButton.click();

        pages.loginPageSignInEmail.sendKeys(ConfigReader.getProperty("adminGecerliMail"));

        pages.loginPageSignInPassword.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));

        pages.loginPageSignInButton.click();

        pages.mainSignInButton.click();

        Actions actions = new Actions(Driver.getDriver());
        actions.moveToLocation(10,540).perform();

        int sekmeSyisi = pages.uiSekmeSayisi.size();  // 5
        int menuSayisi = pages.uiMenuSayisi.size();   // 14

        Assert.assertNotEquals(sekmeSyisi,menuSayisi);  // it must be Equal

        System.out.println(pages.uiSekmeSayisi.size());
        System.out.println(pages.uiMenuSayisi.size());

        Driver.quitDriver();
    }
}
