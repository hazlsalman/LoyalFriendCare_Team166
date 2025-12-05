package tests.US_032;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DogukanPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class TC_004 {
    @Test
    public void createDoctorWithoutImageTest(){
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
        dogukanPage.doctorsTitleBox.sendKeys("Ali Veli");
        dogukanPage.doctorsContentBox.sendKeys("Profession / Veterinarian");
        dogukanPage.doctorsSaveButton.click();
        Assert.assertFalse(dogukanPage.doctorsInformationTableList.isEmpty());
        Assert.assertTrue(dogukanPage.doctorsCreateSuccessMessage.isDisplayed());
        actions = new Actions(Driver.getDriver());
        actions.sendKeys(Keys.END).perform();
        ReusableMethods.bekle(1);
        Assert.assertTrue(dogukanPage.newDoctorNameText.isDisplayed());
        Driver.quitDriver();
    }
}
