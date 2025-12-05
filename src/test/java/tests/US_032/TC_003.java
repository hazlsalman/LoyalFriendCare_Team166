package tests.US_032;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DogukanPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class TC_003 {
    @Test
    public void positiveCreateDoctorTest(){
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
        String home = System.getProperty("user.home");
        String filePath = home + "/Desktop/test.jpeg";
        dogukanPage.dropFileBox.sendKeys(filePath);
        ReusableMethods.bekle(1);
        Assert.assertTrue(dogukanPage.uploadSuccessMark.isDisplayed());
        Assert.assertTrue(dogukanPage.uploadedImage.isDisplayed());
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
