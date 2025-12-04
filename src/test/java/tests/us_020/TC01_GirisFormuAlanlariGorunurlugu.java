package tests.us_020;

import org.testng.annotations.Test;
import pages.WadoudPages;
import utilities.ConfigReader;
import utilities.Driver;

public class TC01_GirisFormuAlanlariGorunurlugu {

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


}
