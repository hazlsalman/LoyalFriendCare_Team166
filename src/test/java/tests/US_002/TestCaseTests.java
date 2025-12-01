package tests.US_002;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SudePage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class TestCaseTests {


SudePage sudePage;
    //1-LoyalFriendCare sitesine gidin
@Test
    public void TC_01Siteyegidilmesi(){
    Driver.getDriver().get(ConfigReader.getProperty("url"));
    Driver.quitDriver();
}
//2-Headerda logo,sign in ve sign up butonlarının gözüktüğünü test edin
@Test
 public void TC_02ButonlarinGozukmesi(){
   Driver.getDriver().get(ConfigReader.getProperty("url"));
   sudePage= new SudePage();
    ReusableMethods.bekle(3);
    Assert.assertTrue(sudePage.logo.isDisplayed());
    Assert.assertTrue(sudePage.signInButton.isDisplayed());
    Assert.assertTrue(sudePage.signUpButton.isDisplayed());
    Driver.quitDriver();
}
//3-Ziyaretçinin tıklanan butonlarla ("Logo","Sign In","Sign Up") doğru sayfaya yönlendirilmesi
@Test
    public void TC_03ButonlarinDogruYereYonlendirmesi(){
    Driver.getDriver().get(ConfigReader.getProperty("url"));
    sudePage=new SudePage();
    Assert.assertTrue(sudePage.logo.isEnabled());
    Assert.assertTrue(sudePage.signInButton.isEnabled());
    Assert.assertTrue(sudePage.signUpButton.isEnabled());
    Driver.quitDriver();
}
}
