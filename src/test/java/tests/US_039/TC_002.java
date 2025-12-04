package tests.US_039;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.RabiaPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC_002 {
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
        Actions actions =new Actions(Driver.getDriver());
        actions.moveToElement(rabiaPage.deashboardMenu).perform();
        rabiaPage.deashboardVaccinations.click();






    }


    @Test (dependsOnMethods = "anaSayfayaErisimTesti")
    public void asiAramaTesti(){
        RabiaPage rabiaPage=new RabiaPage();

        rabiaPage.asiAramaKutusu.sendKeys("Rabies Vaccine");

        int bulunanAsi=rabiaPage.asiListesiBody.size();
        Assert.assertTrue(rabiaPage.asiListesiBody.size()>0);








    }

}
