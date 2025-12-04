package tests.us_020;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WadoudPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC03_GecerSIZBilgilerGirisEngellenmeli {

    WadoudPage pages;

    @Test
    public void us_020_TC03_GecerSIZBilgilerGirisEngellenmeli(){

        pages = new WadoudPage();
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        pages.mainSignInButton.click();

        pages.loginPageSignInEmail.sendKeys(ConfigReader.getProperty("adminGecerliMail") + "11");

        pages.loginPageSignInPassword.sendKeys(ConfigReader.getProperty("adminGecerliPassword") + "11");

        pages.loginPageSignInButton.click();

        String expectedText = "These credentials do not match our records.";
        String actualText = pages.invalidCredentialTextMessage.getText();

        Assert.assertEquals(actualText,expectedText);

        Driver.quitDriver();
    }
}
