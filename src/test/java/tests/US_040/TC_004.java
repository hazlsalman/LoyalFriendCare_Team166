package tests.US_040;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.RabiaPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBaseRapor;

public class TC_004 extends TestBaseRapor {


    @AfterClass
    public void teardown(){
        Driver.quitDriver();
    }
    @Test
    public void anaSayfayaErisimTesti() {



        Driver.getDriver().get(ConfigReader.getProperty("url"));

        RabiaPage rabiaPage = new RabiaPage();
        rabiaPage.signInButton.click();
        rabiaPage.userNameKutu.sendKeys(ConfigReader.getProperty("adminGecerliMail"));
        rabiaPage.passwordKutu.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));
        rabiaPage.logInSignIn.click();
        rabiaPage.gorunenUserName.click();
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(rabiaPage.deashboardMenu).perform();
        rabiaPage.deashboardVaccinations.click();
        rabiaPage.addVaccinationsButton.click();


    }
    @Test (dependsOnMethods = "anaSayfayaErisimTesti")
    public void dogruAsiEklemeTesti() {

        extentTest = extentReports.createTest("Dogru Asi Ekleme Testi",
                "Kullanici randevu almakiçin randevu formunu doldurur");

        RabiaPage rabiaPage = new RabiaPage();
        rabiaPage.petsTitleForm.sendKeys("Rabies Vaccine");


        extentTest.info("yonetici asi adini dogru bir sekilde girer");

        rabiaPage.contentPetsForm.sendKeys("RAbies Vaccine");

        extentTest.info("yonetici asi aciklamasini dogru bir sekilde girer");
        rabiaPage.petsPriceForm.sendKeys("550$");
        extentTest.info("yonetici asi fiyatini dogru bir sekilde girer");
        rabiaPage.saveButton.click();
        extentTest.info("yonetici save butonuna tiklar");
        String expectedYazi="Pets Store successfully.";
        String actualYazi=rabiaPage.asiEklemOnay.getText();
        Assert.assertFalse(actualYazi.equals(expectedYazi),
                "Mevcut bir aşı ismiyle aynı isimde yeni bir asi eklenmesine rağmen başarılı aşı akleme mesajı alındı.");

        extentTest.pass("asi onay mesjinin görünür olmadigi test edilir");


    }
}
