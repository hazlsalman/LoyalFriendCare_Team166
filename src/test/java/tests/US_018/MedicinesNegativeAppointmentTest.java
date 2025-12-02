package tests.US_018;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HazalPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBaseRapor;

public class MedicinesNegativeAppointmentTest extends TestBaseRapor {

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
    public void medicinesNegativeAppointmentTest(){

        extentTest=extentReports.createTest("Negative Appointment Booking Test"
                ,"Kayitli kullanici gecersiz bilgilerle randevu talebi olusturamamali ve hata mesaji almali");

        hazalPage=new HazalPage();
        SoftAssert softAssert=new SoftAssert();

        for (int i = 0; i <5 ; i++) {

            hazalPage.solListeIlacBasliklari.get(i).click();

            extentTest.info("Kayitli kullanici sectigi ilac sayfasina gider");

            hazalPage.appointmentBookingTarih.sendKeys("29.11.2024");

            extentTest.info("Kayitli kullanici randevu formunda gecersiz tarih bilgisini secer");

            hazalPage.appointmentBookingPhoneNumber.sendKeys("12345678990");

            extentTest.info("Kayitli kullanici gecersiz telefon numarasi girer");

            extentTest.info("Kayitli kullanici departments kisminda herhangi bir secim yapmaz");
            extentTest.info("Kayitli kullanici doctors kismini herhangi bir secim yapmaz");
            extentTest.info("Kayitli kullanici create message kismini bos birakir");


            hazalPage.appointmentBookingButton.click();
            extentTest.info("Kayitli kullanici appointment booking butonuna basar");

           String expectedMessage="Girdiğiniz bilgiler geçersiz, lütfen tekrar kontrol edin";
           String actualMessage=hazalPage.randevuMessage.getText();

           softAssert.assertEquals(actualMessage,expectedMessage);

           extentTest.pass("Kayitli kullanici randevu talebi olusturamadığını ve hata mesaji aldigini test eder");

           Driver.getDriver().navigate().back();
           Driver.getDriver().navigate().back();


        }

        extentTest.info("Kayitli kullanici sayfayi kapatir");

        softAssert.assertAll();

    }
}
