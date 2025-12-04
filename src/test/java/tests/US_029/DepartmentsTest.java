package tests.US_029;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AtakanPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBaseRapor;

public class DepartmentsTest extends TestBaseRapor {

    @Test
    public void DepartmentsTest01(){

        extentTest = extentReports.createTest("DepartmentsTest01",
                "Admin login sonrası Departments menüsüne erişim testi");

        Driver.getDriver().get(ConfigReader.getProperty("url"));
        extentTest.info("URL'e gidildi");

        AtakanPage atakanPage = new AtakanPage();
        atakanPage.signInButonu.click();
        extentTest.info("Sign In butonuna tıklandı");

        atakanPage.email.sendKeys(ConfigReader.getProperty("adminGecerliMail"));
        atakanPage.password.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));
        extentTest.info("Geçerli bilgiler girildi");

        atakanPage.loginSignInButonu.click();
        extentTest.info("Login butonuna tıklandı");

        Assert.assertTrue(atakanPage.adminAdıButonu.isDisplayed());
        extentTest.pass("Admin adı butonu görüntülendi");

        atakanPage.adminAdıButonu.click();
        extentTest.info("Admin menüsü açıldı");

        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(atakanPage.dashboardMenu).perform();
        extentTest.info("Dashboard menüsü üzerine gidildi");

        Assert.assertTrue(atakanPage.anaDepartmentsButonu.isDisplayed());
        extentTest.pass("Departments ana butonu görüntülendi");


        //DepartmentsTest02
        extentTest = extentReports.createTest("DepartmentsTest02",
                "Departments ekranındaki butonların görünürlüğü");

        atakanPage.anaDepartmentsButonu.click();
        extentTest.info("Ana Departments butonuna tıklandı");

        Assert.assertTrue(atakanPage.departmentsButonu.isDisplayed());
        Assert.assertTrue(atakanPage.createDepartmentsButonu.isEnabled());
        extentTest.pass("Departments butonları görünür ve aktif");


        //DepartmentsTest03
        extentTest = extentReports.createTest("DepartmentsTest03",
                "Departments tablosu görüntülenmeli");

        atakanPage.departmentsButonu.click();
        extentTest.info("Departments butonuna tıklandı");

        Assert.assertTrue(atakanPage.tableDepartments.isDisplayed());
        extentTest.pass("Departments tablosu görüntülendi");


        //DepartmentsTest04
        extentTest = extentReports.createTest("DepartmentsTest04",
                "Search alanı çalışmalı");

        atakanPage.searchButonu.click();
        atakanPage.searchButonu.sendKeys("Ata");
        extentTest.info("Search alanına 'Ata' yazıldı");

        Assert.assertTrue(atakanPage.listeSonucYazisi.isDisplayed());
        extentTest.pass("Arama sonucu görüntülendi");


        //DepartmentsTest05
        extentTest = extentReports.createTest("DepartmentsTest05",
                "Departments URL doğrulama testi");

        atakanPage.searchButonu.click();
        atakanPage.searchButonu.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);

        String expectedWellnessUrl= "https://qa.loyalfriendcare.com/en/Departments/wellness";
        String actualWellnessUrl= Driver.getDriver().getCurrentUrl();

        Assert.assertEquals(actualWellnessUrl,expectedWellnessUrl);
        extentTest.pass("Wellness URL doğrulandı");
    }
}
