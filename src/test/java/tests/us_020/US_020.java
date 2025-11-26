package tests.us_020;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WadoudPages;
import utilities.ConfigReader;
import utilities.Driver;

public class US_020 {

    WadoudPages pages;

    @Test
    public void us_020_TC01_GirisFormuAlanlariGorunurlugu(){

        pages = new WadoudPages();
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        pages.mainSignInButton.click();

        pages.loginPageSignInEmail.isDisplayed();

        pages.loginPageSignInPassword.isDisplayed();

        pages.loginPageSignInButton.isDisplayed();

        Driver.quitDriver();
    }

    @Test
    public void us_020_TC02_GecerliBilgilerBasariliGiris(){

        pages = new WadoudPages();
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        pages.mainSignInButton.click();

        pages.loginPageSignInEmail.sendKeys(ConfigReader.getProperty("userGecerliMail"));

        pages.loginPageSignInPassword.sendKeys(ConfigReader.getProperty("userGecerliPassword"));

        pages.loginPageSignInButton.click();

        String expectedText = "user.atakan.durman";
        String actualText = pages.mainSignInButton.getText();
        Assert.assertEquals(actualText,expectedText);

        Driver.quitDriver();
    }

    @Test
    public void us_020_TC03_GecerSIZBilgilerGirisEngellenmeli(){

        pages = new WadoudPages();
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        pages.mainSignInButton.click();

        pages.loginPageSignInEmail.sendKeys(ConfigReader.getProperty("userGecerliMail") + "11");

        pages.loginPageSignInPassword.sendKeys(ConfigReader.getProperty("userGecerliPassword") + "11");

        pages.loginPageSignInButton.click();

        String expectedText = "These credentials do not match our records.";
        String actualText = pages.invalidCredentialTextMessage.getText();

        Assert.assertEquals(actualText,expectedText);

        Driver.quitDriver();
    }
}
