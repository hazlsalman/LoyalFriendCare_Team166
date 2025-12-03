package tests.US_031;

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

public class TC_005 {
    @Test
    public void searchingBoxFilterTest(){
        DogukanPage dogukanPage = new DogukanPage();
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        dogukanPage.signInButton.click();
        dogukanPage.loginPageEmailBox.sendKeys(ConfigReader.getProperty("adminGecerliMail"));
        dogukanPage.loginPagePasswordBox.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));
        dogukanPage.loginPageSignInButton.click();
        dogukanPage.profileSettingsButton.click();
        Actions actions = new Actions(Driver.getDriver());
        actions.moveByOffset(5, 200).perform();
        ReusableMethods.bekle(1);
        dogukanPage.dropDownMenuDoctorsButton.click();
        dogukanPage.doctorsSubMenuButton.click();

        List<String> allDoctorNames = new ArrayList<>();
        for (WebElement w : dogukanPage.doctorsNameList) {
            allDoctorNames.add(w.getText().trim());
        }

        dogukanPage.doctorSearchingBox.sendKeys("Dr. Alejandro");

        List<String> namesAfterFilter = new ArrayList<>();
        for (WebElement w : dogukanPage.doctorsNameList) {
            namesAfterFilter.add(w.getText().trim());
        }

        for (String name : namesAfterFilter) {
            Assert.assertTrue(
                    name.contains("Dr. Alejandro"),
                    "Filtre sonrası listede Dr. Alejandro içermeyen bir doktor var: " + name
            );
        }
        Driver.quitDriver();
    }
}
