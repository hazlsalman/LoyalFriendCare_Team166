package tests.us_020;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WadoudPages;
import utilities.ConfigReader;
import utilities.Driver;

public class US_020 {

    WadoudPages pages;

    @Test
    public void us_020_TC01_GirisFormuAlanlariGorunurlugu(){

        pages = new WadoudPages();
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        pages.mainSignInButton.click();

        pages.loginPageSignInEmail.isDisplayed();

        pages.loginPageSignInPassword.isDisplayed();

        pages.loginPageSignInButton.isDisplayed();

        Driver.quitDriver();
    }

    @Test
    public void us_020_TC02_GecerliBilgilerBasariliGiris(){

        pages = new WadoudPages();
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        pages.mainSignInButton.click();

        pages.loginPageSignInEmail.sendKeys(ConfigReader.getProperty("adminGecerliMail"));

        pages.loginPageSignInPassword.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));

        pages.loginPageSignInButton.click();

        String expectedText = "admin.atakan.durman";
        String actualText = pages.mainSignInButton.getText();
        Assert.assertEquals(actualText,expectedText);

        Driver.quitDriver();
    }

    @Test
    public void us_020_TC03_GecerSIZBilgilerGirisEngellenmeli(){

        pages = new WadoudPages();
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        pages.mainSignInButton.click();

        pages.loginPageSignInEmail.sendKeys(ConfigReader.getProperty("adminGecerliMail") + "11");

        pages.loginPageSignInPassword.sendKeys(ConfigReader.getProperty("adminGecerliPassword") + "11");

        pages.loginPageSignInButton.click();

        String expectedText = "These credentials do not match our records.";
        String actualText = pages.invalidCredentialTextMessage.getText();

        Assert.assertEquals(actualText,expectedText);

        Driver.quitDriver();
    }

    @Test
    public void us_020_TC04_ZorunluAlanlarinBosBirakilmamasi(){

        pages = new WadoudPages();
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        pages.mainSignInButton.click();

        pages.loginPageSignInEmail.sendKeys("");

        pages.loginPageSignInPassword.sendKeys("");

        pages.loginPageSignInButton.click();

        Assert.assertTrue(pages.loginPageSignInEmail.isDisplayed());

        Driver.quitDriver();
    }

    @Test
    public void us_020_TC05_GirisSonrasiMenulerinGorunmesiErisilmesi(){

        pages = new WadoudPages();
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        pages.mainSignInButton.click();

        pages.loginPageSignInEmail.sendKeys(ConfigReader.getProperty("adminGecerliMail"));

        pages.loginPageSignInPassword.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));

        pages.loginPageSignInButton.click();

        pages.mainSignInButton.click();

        Actions actions = new Actions(Driver.getDriver());
        actions.moveToLocation(10,540).perform();


        pages.menuDepartmentButton.click();
        pages.menuDoctorsButton.click();
        pages.menuMedicineButton.click();
        pages.menuAdsenseButton.click();

        Driver.quitDriver();
    }

    @Test
    public void us_020_TC06_(){

        pages = new WadoudPages();
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        pages.mainSignInButton.click();

        pages.loginPageSignInEmail.sendKeys(ConfigReader.getProperty("adminGecerliMail"));

        pages.loginPageSignInPassword.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));

        pages.loginPageSignInButton.click();

        pages.mainSignInButton.click();

        // Failed Steps needed to complete

        Driver.quitDriver();
    }  //GirisSonrasiSekmelerinGorunmesiErisilmesi

    @Test
    public void us_020_TC07_SolMenuileUISekmelerinEsitligi(){

        pages = new WadoudPages();
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        pages.mainSignInButton.click();

        pages.loginPageSignInEmail.sendKeys(ConfigReader.getProperty("adminGecerliMail"));

        pages.loginPageSignInPassword.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));

        pages.loginPageSignInButton.click();

        pages.mainSignInButton.click();

        Actions actions = new Actions(Driver.getDriver());
        actions.moveToLocation(10,540).perform();

        int sekmeSyisi = pages.uiSekmeSayisi.size();  // 5
        int menuSayisi = pages.uiMenuSayisi.size();   // 14

        Assert.assertNotEquals(sekmeSyisi,menuSayisi);  // it must be Equal

        System.out.println(pages.uiSekmeSayisi.size());
        System.out.println(pages.uiMenuSayisi.size());

        Driver.quitDriver();
    }

    @Test
    public void us_020_TC08_AdminGuvenliSekildeCikisYapmasi(){

        pages = new WadoudPages();
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        pages.mainSignInButton.click();

        pages.loginPageSignInEmail.sendKeys(ConfigReader.getProperty("adminGecerliMail"));

        pages.loginPageSignInPassword.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));

        pages.loginPageSignInButton.click();

        String expectedText = "admin.atakan.durman";
        String actualText = pages.mainSignInButton.getText();
        Assert.assertEquals(actualText,expectedText);

        pages.mainSignOutButton.click();

        String expectedSignOutText = "Sign Up";
        String actualSignOutText = pages.mainSignOutButton.getText();
        Assert.assertEquals(actualSignOutText,expectedSignOutText);

        Driver.quitDriver();
    }


}
