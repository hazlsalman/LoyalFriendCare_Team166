package tests.US_031;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DogukanPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.ArrayList;
import java.util.List;

public class TC_007 {

    @Test
    public void doctorsDeleteTest() {

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
        dogukanPage.doctorDeleteButton.click();
        Assert.assertTrue(dogukanPage.doctorDeleteSuccessMessage.isDisplayed());
        List<String> allDoctorNames = new ArrayList<>();
        for (WebElement w : dogukanPage.doctorsNameList) {
            allDoctorNames.add(w.getText().trim());
        }

        dogukanPage.doctorSearchingBox.sendKeys("Ali Veli");

        List<String> namesAfterFilter = new ArrayList<>();
        for (WebElement w : dogukanPage.doctorsNameList) {
            namesAfterFilter.add(w.getText().trim());
        }

        for (String name : namesAfterFilter) {
            Assert.assertFalse(
                    name.contains("Ali Veli"),
                    "Filtre sonrası listede Ali Veli içeren bir doktor var: " + name
            );
        }
        Driver.quitDriver();
    }
}
