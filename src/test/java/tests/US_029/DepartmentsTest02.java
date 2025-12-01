package tests.US_029;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AtakanPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class DepartmentsTest02 {

    @Test
    public void DepartmentsTest06(){

        Driver.getDriver().get(ConfigReader.getProperty("url"));

        AtakanPage atakanPage = new AtakanPage();
        atakanPage.signInButonu.click();

        atakanPage.email.sendKeys(ConfigReader.getProperty("adminGecerliMail"));
        atakanPage.password.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));
        atakanPage.loginSignInButonu.click();
        Assert.assertTrue(atakanPage.adminAdıButonu.isDisplayed());
        atakanPage.adminAdıButonu.click();

        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(atakanPage.dashboardMenu).perform();
        Assert.assertTrue(atakanPage.anaDepartmentsButonu.isDisplayed());
        atakanPage.anaDepartmentsButonu.click();
        Assert.assertTrue(atakanPage.departmentsButonu.isDisplayed());
        atakanPage.departmentsButonu.click();
        atakanPage.editButonu.click();
        atakanPage.parentDepartments.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        atakanPage.parentDepartments.sendKeys("999");
        atakanPage.orderDepartments.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        atakanPage.orderDepartments.sendKeys("888");
        atakanPage.titleDepartments.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        atakanPage.titleDepartments.sendKeys("Ata26");
        atakanPage.departmentsColor.click();
        atakanPage.departmentsColor_colorSuccess.click();
        atakanPage.editSaveDepartmentsButonu.click();
        atakanPage.editDepartmentsUpdatedYazisi.isDisplayed();
    }

    @Test (dependsOnMethods ="DepartmentsTest06")
    public void DepartmentsTest07(){

        AtakanPage atakanPage= new AtakanPage();
        atakanPage.deleteButonu.click();
        atakanPage.deleteYazisi.isDisplayed();
    }

    @Test (dependsOnMethods ="DepartmentsTest07")
    public void DepartmentsTest08(){

        AtakanPage atakanPage= new AtakanPage();
        atakanPage.searchButonu.click();
        atakanPage.searchButonu.sendKeys("Ata");
        atakanPage.listeSonucYazisi.isDisplayed();

        Driver.quitDriver();
    }
}
