package tests.US_017;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HazalPage;
import utilities.ConfigReader;
import utilities.Driver;

public class VaccinationsDetailsPageTest {

    HazalPage hazalPage;

    @BeforeClass
    public void preconditionVaccinationsPage(){
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        hazalPage=new HazalPage();
        hazalPage.homePageSignInOrProfileButton.click();
        hazalPage.loginPageEmailKutusu.sendKeys(ConfigReader.getProperty("userGecerliMail"));
        hazalPage.loginPagePasswordKutusu.sendKeys(ConfigReader.getProperty("userGecerliPassword"));
        hazalPage.loginPageSignInButton.click();
        hazalPage.bodyVaccinations.click();

    }

    @Test
    public void vaccinationsDetailsPageTest(){

        //1.Vaccionations sayfasındaki sol listedeki veya ana bölümdeki
        // aşı başlıklarından seçtiği başlığa  tıklar.
        //2.Kısa bilgi bölümünün ve Appointment Booking formunun ekranda görünür olduğu doğrulanır.

        hazalPage=new HazalPage();
        SoftAssert softAssert=new SoftAssert();

        for (int i = 0; i <hazalPage.solListeAsiBasliklari.size() ; i++) {

            hazalPage.solListeAsiBasliklari.get(i).click();

            softAssert.assertTrue(hazalPage.kisaBilgiBolumu.isDisplayed());

            softAssert.assertTrue(hazalPage.appointmentBooking.isDisplayed());

            Driver.getDriver().navigate().back();

        }

        Driver.quitDriver();

        softAssert.assertAll();

    }
}
