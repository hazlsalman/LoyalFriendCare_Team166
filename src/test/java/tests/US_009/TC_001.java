package tests.US_009;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DogukanPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC_001 {
    @Test
    public void forgotPasswordEnabledTest(){
        DogukanPage dogukanPage = new DogukanPage();
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        Assert.assertTrue(dogukanPage.signInButton.isDisplayed());
        Assert.assertTrue(dogukanPage.signInButton.isEnabled());
        Driver.quitDriver();
    }
}
