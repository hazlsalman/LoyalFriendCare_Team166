package tests.US_032;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DogukanPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class TC_002 {
    @Test
    public void negativeCreateDoctorTest(){
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
        dogukanPage.doctorsSaveButton.click();
        Assert.assertTrue(dogukanPage.doctorsSaveNegativeMessage.isDisplayed());
        ReusableMethods.bekle(1);
        dogukanPage.doctorsContentBox.sendKeys("Profession / Veterinarian");
        dogukanPage.doctorsSaveButton.click();
        Assert.assertTrue(dogukanPage.doctorsSaveNegativeMessage.isDisplayed());
        ReusableMethods.bekle(1);
        dogukanPage.doctorsContentBox.clear();
        dogukanPage.doctorsTitleBox.sendKeys("Ali Veli");
        dogukanPage.doctorsSaveButton.click();
        Assert.assertTrue(dogukanPage.doctorsSaveNegativeMessage.isDisplayed());
        Driver.quitDriver();
    }
}
