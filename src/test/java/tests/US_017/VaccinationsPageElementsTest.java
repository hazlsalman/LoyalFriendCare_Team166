package tests.US_017;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HazalPage;
import utilities.ConfigReader;
import utilities.Driver;

public class VaccinationsPageElementsTest {

    HazalPage hazalPage;

    @BeforeClass
    public void precoditionVaccinationsPage(){
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        hazalPage=new HazalPage();
        hazalPage.homePageSignInOrProfileButton.click();
        hazalPage.loginPageEmailKutusu.sendKeys(ConfigReader.getProperty("userGecerliMail"));
        hazalPage.loginPagePasswordKutusu.sendKeys(ConfigReader.getProperty("userGecerliPassword"));
        hazalPage.loginPageSignInButton.click();
        hazalPage.bodyVaccinations.click();

    }


    @Test
    public void vaccinationsPageElementsTest(){

        //1.Vaccinations Page’in sol tarafındaki “Vaccinations” başlığı altındaki
        // aşı isimlerinin görünürlüğü doğrulanır.

        SoftAssert softAssert=new SoftAssert();
        hazalPage=new HazalPage();

        for (WebElement eachBaslik: hazalPage.solListeAsiBasliklari){

            softAssert.assertTrue(eachBaslik.isDisplayed()
                    ,"Sol listedeki basliklardan "+eachBaslik.getText()+" basligi goruntulenemedi");
        }


        //2.Ana bölümdeki tüm aşı başlıkları ve oluşturulma tarihleri görünürlüğü doğrulanır.

        for (WebElement eachAnaBolumBaslik: hazalPage.anaBolumAsiBasliklari){

            softAssert.assertTrue(eachAnaBolumBaslik.isDisplayed(),
                    "Ana bolumdeki basliklardan "+eachAnaBolumBaslik.getText()+" basligi goruntulenemedi");
        }


        //3.Sol listedeki her bir aşı isminin tıklanabilirliği kontrol edilir.

        for (WebElement eachBaslik: hazalPage.solListeAsiBasliklari){

            softAssert.assertTrue(eachBaslik.isEnabled()
                    ,"Sol listedeki basliklardan "+eachBaslik.getText()+" basligi tiklanabilir degil");
        }


        //4.Ana bölümdeki her bir aşı başlığının tıklanabilirliği kontrol edilir.


        for (WebElement eachAnaBolumBaslik: hazalPage.anaBolumAsiBasliklari){

            softAssert.assertTrue(eachAnaBolumBaslik.isEnabled(),
                    "Ana bolumdeki basliklardan "+eachAnaBolumBaslik.getText()+" basligi tiklanabilir degil");
        }


        Driver.quitDriver();

        softAssert.assertAll();

    }
}
