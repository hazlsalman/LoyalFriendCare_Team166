package tests.US_017;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HazalPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.time.Duration;

public class VaccinationsAppointmentTest {

    HazalPage hazalPage;

    @BeforeClass
    public void preconditionVaccinationsDetailsPage(){
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        hazalPage=new HazalPage();
        hazalPage.homePageSignInOrProfileButton.click();
        hazalPage.loginPageEmailKutusu.sendKeys(ConfigReader.getProperty("userGecerliMail"));
        hazalPage.loginPagePasswordKutusu.sendKeys(ConfigReader.getProperty("userGecerliPassword"));
        hazalPage.loginPageSignInButton.click();
        hazalPage.bodyVaccinations.click();

    }

    @Test
    public void vaccinationsAppointmentTest(){

        hazalPage=new HazalPage();
        SoftAssert softAssert=new SoftAssert();


        for (int i = 0; i <hazalPage.solListeAsiBasliklari.size() ; i++) {

            hazalPage.solListeAsiBasliklari.get(i).click();

            hazalPage.appointmentBookingTarih.sendKeys("28.11.2026");
            hazalPage.appointmentBookingPhoneNumber.sendKeys("05551111111");

            hazalPage.departmentsDropdownMenu.click();
            hazalPage.departmentsVaccinations.click();

            ReusableMethods.bekle(1);

            hazalPage.doctorsDropdownMenu.click();
            hazalPage.doctorsVaccinations.click();

            ReusableMethods.bekle(1);

            hazalPage.appointmentBookingCreateMessage.sendKeys("Randevu Basarili");

            hazalPage.appointmentBookingButton.click();

            softAssert.assertTrue(hazalPage.randevuMessage.isDisplayed(),"Randuvu onaylanmadi");

            Driver.getDriver().navigate().back();
            Driver.getDriver().navigate().back();

        }

        Driver.quitDriver();
        softAssert.assertAll();

    }
}
