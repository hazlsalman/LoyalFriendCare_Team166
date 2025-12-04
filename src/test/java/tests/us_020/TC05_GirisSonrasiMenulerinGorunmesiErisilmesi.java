package tests.us_020;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WadoudPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC05_GirisSonrasiMenulerinGorunmesiErisilmesi {

    WadoudPage pages;

    @Test
    public void us_020_TC05_GirisSonrasiMenulerinGorunmesiErisilmesi(){

        pages = new WadoudPage();
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
}
