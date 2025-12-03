package tests.us_020;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WadoudPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC08_AdminGuvenliSekildeCikisYapmasi {

    WadoudPage pages;

    @Test
    public void us_020_TC08_AdminGuvenliSekildeCikisYapmasi(){

        pages = new WadoudPage();
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        pages.mainSignInButton.click();

        pages.loginPageSignInEmail.sendKeys(ConfigReader.getProperty("adminGecerliMail"));

        pages.loginPageSignInPassword.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));

        pages.loginPageSignInButton.click();

        String expectedText = "admin.atakan.durman";
        String actualText = pages.mainSignInButton.getText();
        Assert.assertEquals(actualText,expectedText);

        pages.mainSignOutButton.click();

        String expectedSignOutText = "Sign Up";
        String actualSignOutText = pages.mainSignOutButton.getText();
        Assert.assertEquals(actualSignOutText,expectedSignOutText);

        Driver.quitDriver();
    }

}
