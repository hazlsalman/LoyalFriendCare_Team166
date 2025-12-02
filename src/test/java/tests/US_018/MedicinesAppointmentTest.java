package tests.US_018;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HazalPage;
import utilities.ConfigReader;
import utilities.Driver;

public class MedicinesAppointmentTest {

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
    public void medicinesAppointmentTest(){

        hazalPage=new HazalPage();
        SoftAssert softAssert=new SoftAssert();

        for (int i = 0; i <5 ; i++) {

            hazalPage.solListeIlacBasliklari.get(i).click();

            hazalPage.appointmentBookingTarih.sendKeys("29.11.2026");

            hazalPage.appointmentBookingPhoneNumber.sendKeys("05551111111");

            hazalPage.departmentsDropdownMenu.click();
            hazalPage.departmentsMedicines.click();

            hazalPage.doctorsDropdownMenu.click();
            hazalPage.doctorsMedicines.click();

            hazalPage.appointmentBookingCreateMessage.sendKeys("Randevu Basarili");

            hazalPage.appointmentBookingButton.click();

            softAssert.assertTrue(hazalPage.randevuMessage.isDisplayed(),"Randevu onaylanmadi");

            Driver.getDriver().navigate().back();
            Driver.getDriver().navigate().back();

        }

        Driver.quitDriver();
        softAssert.assertAll();

    }
}
