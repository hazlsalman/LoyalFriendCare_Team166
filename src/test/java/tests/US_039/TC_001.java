package tests.US_039;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RabiaPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC_001 {

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
        String actualText=rabiaPage.galeriText.getText();
        Assert.assertTrue(actualText.contains("Vaccinations"));


        Driver.quitDriver();


    }




}
