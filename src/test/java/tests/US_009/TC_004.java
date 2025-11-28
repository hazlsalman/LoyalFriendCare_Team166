package tests.US_009;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DogukanPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC_004 {
    @Test
    public void gecersizEmailTest(){
        DogukanPage dogukanPage = new DogukanPage();
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        dogukanPage.signInButton.click();
        dogukanPage.forgotMyPasswordButton.click();
        dogukanPage.emailBox.sendKeys("sanane123@gmail.com");
        dogukanPage.emailSendButton.click();
        Assert.assertTrue(dogukanPage.yanlisEmailMessage.isDisplayed());
        Driver.quitDriver();
    }
}
