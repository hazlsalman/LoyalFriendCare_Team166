package tests.US_018;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HazalPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class MedicinesDetailsPageTest {

    HazalPage hazalPage;

    @BeforeClass
    public void preconditionMedicinesPage(){

        Driver.getDriver().get(ConfigReader.getProperty("url"));
        hazalPage=new HazalPage();
        hazalPage.homePageSignInOrProfileButton.click();
        hazalPage.loginPageEmailKutusu.sendKeys(ConfigReader.getProperty("userGecerliMail"));
        hazalPage.loginPagePasswordKutusu.sendKeys(ConfigReader.getProperty("userGecerliPassword"));
        hazalPage.loginPageSignInButton.click();
        hazalPage.headerMedicines.click();

    }


    @Test
    public void medicinesDetailsPageTest(){

        hazalPage=new HazalPage();
        SoftAssert softAssert=new SoftAssert();

        //1.Medicines sayfasının sol tarafında veya ana bölümde listelenen
        // bir “Medicine” başlığından seçtiği başlığa tıklar.
        //2.Kısa bilgi bölümünün ve Appointment Booking formunun ekranda görünür olduğu doğrulanır.

        for (int i = 0; i <5 ; i++) {

            WebElement solListeIlacBasliklariElement = hazalPage.solListeIlacBasliklari.get(i);

            Actions actions = new Actions(Driver.getDriver());
            actions.moveToElement(solListeIlacBasliklariElement).click().perform();


            softAssert.assertTrue(hazalPage.kisaBilgiBolumu.isDisplayed()
                    ,"Kisa bilgi bolumu goruntulenmedi");

            ReusableMethods.bekle(1);

            softAssert.assertTrue(hazalPage.appointmentBooking.isDisplayed()
                    ,"Appointment Booking bolumu goruntulenemedi");

            Driver.getDriver().navigate().back();

            hazalPage=new HazalPage();

        }

        Driver.quitDriver();

        softAssert.assertAll();

    }
}
