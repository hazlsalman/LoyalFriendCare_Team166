package tests.US_040;

import org.openqa.selenium.WebElement;
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
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(rabiaPage.deashboardMenu).perform();
        rabiaPage.deashboardVaccinations.click();
        rabiaPage.addVaccinationsButton.click();


    }
    @Test (dependsOnMethods = "anaSayfayaErisimTesti")
    public void dogruAsiEklemeTesti(){

        RabiaPage rabiaPage=new RabiaPage();

        rabiaPage.saveButton.click();
        WebElement asiEklemesayfasiTextElement=rabiaPage.asiEklemesayfasiText;
        Assert.assertTrue(asiEklemesayfasiTextElement.isDisplayed());






    }
}
