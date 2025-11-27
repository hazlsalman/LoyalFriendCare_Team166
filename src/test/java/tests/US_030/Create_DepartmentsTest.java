package tests.US_030;

import net.bytebuddy.build.ToStringPlugin;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AtakanPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class Create_DepartmentsTest {

    @Test
    public void CreateDepartmentsTest01(){

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
        Assert.assertTrue(atakanPage.createDepartmentsButonu.isEnabled());
    }

    @Test (dependsOnMethods ="CreateDepartmentsTest01")
    public void CreateDepartmentsTest02(){
        AtakanPage atakanPage= new AtakanPage();

        atakanPage.createDepartmentsButonu.click();
        Assert.assertTrue(atakanPage.createYourDepartmentsYazisi.isDisplayed());
    }

    @Test (dependsOnMethods ="CreateDepartmentsTest02")
    public void CreateDepartmentsTest03(){

        AtakanPage atakanPage= new AtakanPage();

        Assert.assertTrue(atakanPage.parentDepartments.isDisplayed());
        Assert.assertTrue(atakanPage.orderDepartments.isDisplayed());
        Assert.assertTrue(atakanPage.titleDepartments.isDisplayed());
        Assert.assertTrue(atakanPage.departmentsColor.isDisplayed());
    }

    @Test (dependsOnMethods ="CreateDepartmentsTest03")
    public void CreateDepartmentsTest04(){

        AtakanPage atakanPage= new AtakanPage();
        atakanPage.crateDepartmentsSaveButonu.click();

        String actualMessage = atakanPage.parentDepartments.getAttribute("validationMessage");
        String expectedMessage = "Lütfen bu alanı doldurun.";
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    @Test (dependsOnMethods ="CreateDepartmentsTest04")
    public void CreateDepartmentsTest05(){

        AtakanPage atakanPage= new AtakanPage();
        atakanPage.parentDepartments.click();
        atakanPage.parentDepartments.sendKeys("5");
        atakanPage.orderDepartments.click();
        atakanPage.orderDepartments.sendKeys("4");
        atakanPage.titleDepartments.click();
        atakanPage.titleDepartments.sendKeys("Ata");
        atakanPage.departmentsColor.click();
        atakanPage.departmentsColor_colorInfo.click();
        atakanPage.crateDepartmentsSaveButonu.click();

        Assert.assertTrue(atakanPage.createDepartmentsMesajı.isDisplayed());
    }

    @Test (dependsOnMethods ="CreateDepartmentsTest05")
    public void CreateDepartments06(){

        AtakanPage atakanPage= new AtakanPage();

        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(atakanPage.dashboardMenu).perform();

        Assert.assertTrue(atakanPage.anaDepartmentsButonu.isDisplayed());
        atakanPage.anaDepartmentsButonu.click();
        atakanPage.departmentsButonu.click();
        atakanPage.searchButonu.click();
        atakanPage.searchButonu.sendKeys("Ata");
        atakanPage.editButonu.click();

        String value= atakanPage.parentDepartments.getAttribute("value");
        int actualParentDepartments= Integer.parseInt(value);
        int expectedParentDepartments= 5;
        Assert.assertEquals(actualParentDepartments,expectedParentDepartments);

        String value2= atakanPage.orderDepartments.getAttribute("value");
        int actualOrderDepartments= Integer.parseInt(value2);
        int expectedOrderDepartments= 4;
        Assert.assertEquals(actualOrderDepartments,expectedOrderDepartments);

        String actualTitleDepartments=atakanPage.titleDepartments.getAttribute("value");
        String expectedTitleDepartments="Ata";
        Assert.assertEquals(actualTitleDepartments,expectedTitleDepartments);

        String actualDepartmentsColor= atakanPage.departmentsColor.getText();
        String expectedDepartmentsColor="info";
        Assert.assertEquals(actualDepartmentsColor,expectedDepartmentsColor);

        Driver.quitDriver();
    }
}
