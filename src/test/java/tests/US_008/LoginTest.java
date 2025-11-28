package tests.US_008;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AtakanPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
public class LoginTest {

    @BeforeMethod
    public void setup(){
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        AtakanPage atakanPage = new AtakanPage();
        atakanPage.signInButonu.click();
    }

    @Test
    public void emailvePasswordAlanları(){

        AtakanPage atakanPage= new AtakanPage();

        Assert.assertTrue(atakanPage.email.isDisplayed());
        Assert.assertTrue(atakanPage.password.isDisplayed());
    }

    @Test
    public void positiveLoginTesti(){

        AtakanPage atakanPage= new AtakanPage();
        atakanPage.email.sendKeys(ConfigReader.getProperty("adminGecerliMail"));
        atakanPage.password.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));
        atakanPage.loginSignInButonu.click();
        Assert.assertTrue(atakanPage.adminAdıButonu.isDisplayed());
    }

    @Test
    public void negativeLoginTesti(){

        AtakanPage atakanPage= new AtakanPage();
        atakanPage.email.sendKeys(ConfigReader.getProperty("gecersizMail"));
        atakanPage.password.sendKeys(ConfigReader.getProperty("gecersizPassword"));
        atakanPage.loginSignInButonu.click();
        Assert.assertTrue(atakanPage.loginHataMesajı.isDisplayed());
    }

    @Test
    public void forgotPasswordTesti(){

        AtakanPage atakanPage= new AtakanPage();

        Assert.assertTrue(atakanPage.forgotPassword.isDisplayed());
        atakanPage.forgotPassword.click();
        Assert.assertTrue(atakanPage.passwordResetButonu.isDisplayed());

    }

    @AfterMethod
    public void tearDown(){
        Driver.quitDriver();
    }
}
