package tests.US_006;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EbruPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class TC_01Footer {
    @Test

    public void FooterControlTesti() {


        Driver.getDriver().get(ConfigReader.getProperty("url"));
        ReusableMethods.bekle(2);

        EbruPage ebruPage = new EbruPage();
        ebruPage.HomePageSignButonu.click();

        ebruPage.email.sendKeys(ConfigReader.getProperty("userGecerliMail"));

        ebruPage.password.sendKeys(ConfigReader.getProperty("userGecerliPassword"));

        ebruPage.loginSignInButonu.click();

        Actions action = new Actions(Driver.getDriver());

        action.sendKeys(Keys.END).perform();
        Assert.assertTrue(ebruPage.footerArea.isDisplayed());

        Assert.assertTrue(ebruPage.logo.isDisplayed());

        String expectedSubstitle = "LoyalFriendCare - Pet Care & Hospital Management & Veterinary";
        String actualSubstitle = ebruPage.substitle.getText();

        Assert.assertEquals(expectedSubstitle, actualSubstitle);

        Driver.quitDriver();

    }
}