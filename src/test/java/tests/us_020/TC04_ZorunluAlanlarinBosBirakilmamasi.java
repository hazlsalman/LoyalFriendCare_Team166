package tests.us_020;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WadoudPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC04_ZorunluAlanlarinBosBirakilmamasi {

    WadoudPage pages;
    @Test
    public void us_020_TC04_ZorunluAlanlarinBosBirakilmamasi(){

        pages = new WadoudPage();
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        pages.mainSignInButton.click();

        pages.loginPageSignInEmail.sendKeys("");

        pages.loginPageSignInPassword.sendKeys("");

        pages.loginPageSignInButton.click();

        Assert.assertTrue(pages.loginPageSignInEmail.isDisplayed());

        Driver.quitDriver();
    }
}
