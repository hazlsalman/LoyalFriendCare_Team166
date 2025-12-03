package tests.us_020;

import org.testng.annotations.Test;
import pages.WadoudPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC06_GirisSonrasiSekmelerinGorunmesiErisilmesi {

    WadoudPage pages;
    @Test
    public void us20_TC06_GirisSonrasiSekmelerinGorunmesiErisilmesi(){
        pages = new WadoudPage();
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        pages.mainSignInButton.click();

        pages.loginPageSignInEmail.sendKeys(ConfigReader.getProperty("adminGecerliMail"));

        pages.loginPageSignInPassword.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));

        pages.loginPageSignInButton.click();

        pages.mainSignInButton.click();

        // Failed Steps needed to complete

        Driver.quitDriver();
    }

}
