package tests.US_017;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HazalPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

public class VaccinationsNegativeAppointmentTest extends TestBaseRapor {

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
    public void vaccinationsNegativeAppointmentTest(){

        hazalPage=new HazalPage();
        SoftAssert softAssert=new SoftAssert();

        extentTest=extentReports.createTest("Negative Appointment Booking Test"
                ,"Kayitli kullanici yanlis bilgilerle randevu formunu doldurdugunda " +
                        "randevu olusturulmamali ve hata mesajini görmeli");

        for (int i = 0; i <hazalPage.solListeAsiBasliklari.size() ; i++) {

            hazalPage.solListeAsiBasliklari.get(i).click();
            extentTest.info("Kayitli kullanici asi sayfasinda istedigi asiyi secer");

            hazalPage.appointmentBookingTarih.sendKeys("20.11.2024");

            extentTest.info("Kayitli kullanici randevu formunda tarih kismina gecersiz bilgi girer");

            hazalPage.appointmentBookingPhoneNumber.sendKeys("12345678989");

            extentTest.info("Kayitli kullanici telefon numarasini kismina gecersiz bilgi girer");

            extentTest.info("Kayitli kullanici departments kisminda herhangi bir secim yapmaz");

            extentTest.info("Kayitli kullanici doctors kisminda herhangi bir secim yapmaz");

            extentTest.info("Kayitli kullanici create message bolumunu bos birakir");

            hazalPage.appointmentBookingButton.click();

            extentTest.info("Kayitli kullanici appointment booking butonuna basar");

            String expectedYazi="Girdiğiniz bilgiler geçersiz, lütfen tekrar kontrol edin";
            String actualYazi=hazalPage.randevuMessage.getText();

            softAssert.assertEquals(actualYazi,expectedYazi,"Randevu mesaji dogru degil");

            extentTest.pass("Kayitli kullanici gecersiz bilgilerle randevu olusturamadigini" +
                    " ve hata mesaji aldigini test eder");

            Driver.getDriver().navigate().back();
            Driver.getDriver().navigate().back();

        }

        softAssert.assertAll();

    }
}
