package tests.US_009;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DogukanPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC_002 {
    @Test
    public void emailBoxTest(){
        DogukanPage dogukanPage = new DogukanPage();
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        dogukanPage.signInButton.click();
        dogukanPage.forgotMyPasswordButton.click();
        Assert.assertTrue(dogukanPage.emailBox.isEnabled());
        Assert.assertTrue(dogukanPage.emailSendButton.isDisplayed());
        Driver.quitDriver();
    }
}
