package tests.US_029;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AtakanPage;
import utilities.ConfigReader;
import utilities.Driver;

public class DepartmentsTest {

    @Test
    public void DepartmentsTest01(){

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
    }

    @Test (dependsOnMethods ="DepartmentsTest01")
    public void DepartmentsTest02(){

        AtakanPage atakanPage= new AtakanPage();
        atakanPage.anaDepartmentsButonu.click();
        Assert.assertTrue(atakanPage.departmentsButonu.isDisplayed());
        Assert.assertTrue(atakanPage.createDepartmentsButonu.isEnabled());
    }

    @Test (dependsOnMethods ="DepartmentsTest02")
    public void DepartmentsTest03(){

        AtakanPage atakanPage= new AtakanPage();
        atakanPage.departmentsButonu.click();
        Assert.assertTrue(atakanPage.tableDepartments.isDisplayed());
    }

    @Test (dependsOnMethods ="DepartmentsTest03")
    public void DepartmentsTest04(){

        AtakanPage atakanPage= new AtakanPage();
        atakanPage.searchButonu.click();
        atakanPage.searchButonu.sendKeys("Ata");
        Assert.assertTrue(atakanPage.listeSonucYazisi.isDisplayed());
    }

    @Test (dependsOnMethods ="DepartmentsTest04")
    public void DepartmentsTest05(){

        AtakanPage atakanPage= new AtakanPage();
        atakanPage.searchButonu.click();
        atakanPage.searchButonu.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);

        String expectedWellnessUrl= "https://qa.loyalfriendcare.com/en/Departments/wellness";
        String actualWellnessUrl= Driver.getDriver().getCurrentUrl();

        Assert.assertEquals(actualWellnessUrl,expectedWellnessUrl,"Tıklama sonrası beklenen url açılmadı");

        Driver.quitDriver();
    }
}
