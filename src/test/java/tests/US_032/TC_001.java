package tests.US_032;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DogukanPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC_001 {
    @Test
    public void createDoctorsFieldTest(){
        DogukanPage dogukanPage = new DogukanPage();
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        dogukanPage.signInButton.click();
        dogukanPage.loginPageEmailBox.sendKeys(ConfigReader.getProperty("adminGecerliMail"));
        dogukanPage.loginPagePasswordBox.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));
        dogukanPage.loginPageSignInButton.click();
        dogukanPage.profileSettingsButton.click();
        Actions actions = new Actions(Driver.getDriver());
        actions.moveByOffset(5, 200).perform();
        dogukanPage.dropDownMenuDoctorsButton.click();
        dogukanPage.createDoctorsMenuButton.click();
        Assert.assertTrue(dogukanPage.doctorsTitleBox.isDisplayed());
        Assert.assertTrue(dogukanPage.doctorsContentBox.isDisplayed());
        Assert.assertTrue(dogukanPage.dropFileBoxMessageArea.isDisplayed());

        Driver.quitDriver();
    }
}
