package tests.US_018;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HazalPage;
import utilities.ConfigReader;
import utilities.Driver;

public class MedicinesPageElementsTest {

    HazalPage hazalPage;

    @BeforeClass
    public void preconditionUserLogin(){

        Driver.getDriver().get(ConfigReader.getProperty("url"));
        hazalPage=new HazalPage();
        hazalPage.homePageSignInOrProfileButton.click();
        hazalPage.loginPageEmailKutusu.sendKeys(ConfigReader.getProperty("userGecerliMail"));
        hazalPage.loginPagePasswordKutusu.sendKeys(ConfigReader.getProperty("userGecerliPassword"));
        hazalPage.loginPageSignInButton.click();

    }


    @Test
    public void medicinesPageElementsTest(){

        hazalPage=new HazalPage();
        SoftAssert softAssert=new SoftAssert();

        //1.Kayıtlı kullanıcı Home Page sayfasındaki header kısmındaki “Medicines” seçeneğini görüntüler.

        softAssert.assertTrue(hazalPage.headerMedicines.isDisplayed());

        //2.“Medicines” seçeneğine tıklar.

        hazalPage.headerMedicines.click();

        // Medicines Page’in başarılı şekilde yüklendiği doğrulanır.

        String expectedUrl="https://qa.loyalfriendcare.com/en/Medicines";
        String actualUrl=Driver.getDriver().getCurrentUrl();

        softAssert.assertEquals(actualUrl,expectedUrl);

        //4.Sol tarafta “Medicines” başlığı altında ilaç listesinin görüntülendiği doğrulanır.


        for (WebElement eachSolListe: hazalPage.solListeIlacBasliklari){

            softAssert.assertTrue(eachSolListe.isDisplayed()
                    ,eachSolListe.getText()+" basligi sol listede goruntulenebilir degil");

        }


        //5.Ana bölümde ilaç başlıkları ve oluşturulma tarihlerinin görüntülediği doğrulanır.

        for (WebElement eachAnaBolum: hazalPage.anaBolumIlacBasliklari){

            softAssert.assertTrue(eachAnaBolum.isDisplayed()
                    ,eachAnaBolum.getText()+" basligi ana bolumde goruntulenebilir degil");

        }



        //6.Hem sol taraftaki liste öğelerinin hem de ana bölümdeki başlıkların
        // aktif olarak tıklanabilir olduğu doğrulanır.

        for (WebElement eachSolListe: hazalPage.solListeIlacBasliklari){

            softAssert.assertTrue(eachSolListe.isEnabled()
                    ,eachSolListe.getText()+" basligi sol listede tiklanabilir degil");

        }


        for (WebElement eachAnaBolum: hazalPage.anaBolumIlacBasliklari){

            softAssert.assertTrue(eachAnaBolum.isEnabled()
                    ,eachAnaBolum.getText()+" basligi ana bolumde tiklanabilir degil");

        }

        Driver.quitDriver();

        softAssert.assertAll();


    }

}
