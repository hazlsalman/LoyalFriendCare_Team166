package tests.us_020;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WadoudPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC02_GecerliBilgilerBasariliGiris {

    WadoudPage pages;

    @Test
    public void us_020_TC02_GecerliBilgilerBasariliGiris(){

        pages = new WadoudPage();
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        pages.mainSignInButton.click();

        pages.loginPageSignInEmail.sendKeys(ConfigReader.getProperty("adminGecerliMail"));

        pages.loginPageSignInPassword.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));

        pages.loginPageSignInButton.click();

        String expectedText = "admin.atakan.durman";
        String actualText = pages.mainSignInButton.getText();
        Assert.assertEquals(actualText,expectedText);

        Driver.quitDriver();
    }
}
