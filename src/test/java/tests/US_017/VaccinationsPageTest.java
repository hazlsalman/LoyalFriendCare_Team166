package tests.US_017;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HazalPage;
import utilities.ConfigReader;
import utilities.Driver;




public class VaccinationsPageTest {

    HazalPage hazalPage;

    @BeforeClass
    public void preconditionLogin(){
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        hazalPage=new HazalPage();
        hazalPage.homePageSignInOrProfileButton.click();
        hazalPage.loginPageEmailKutusu.sendKeys(ConfigReader.getProperty("userGecerliMail"));
        hazalPage.loginPagePasswordKutusu.sendKeys(ConfigReader.getProperty("userGecerliPassword"));
        hazalPage.loginPageSignInButton.click();
    }

    @Test
    public void vaccinationsPageTest(){

        //1.Home Page’de header bölümünde veya body bölümünde
        // “Vaccinations”  bağlantısını görünür oldugu dogrulanır.

        hazalPage=new HazalPage();

        SoftAssert softAssert=new SoftAssert();

        softAssert.assertTrue(hazalPage.headerVaccinations.isDisplayed()
                ,"Header bolumunde vaccionations baglantısı gorunmuyor");

        softAssert.assertTrue(hazalPage.bodyVaccinations.isDisplayed()
                ,"Body bolumunde vaccinations baglantısı gorunmuyor");


        //2.Header veya body’deki “Vaccinations” bağlantısınının tıklanabilirligi dogrulanir ve birine tıklanır.

        softAssert.assertTrue(hazalPage.headerVaccinations.isEnabled()
        ,"Header bolumundeki vaccination baglantisi tiklanabilir degil");

        softAssert.assertTrue(hazalPage.bodyVaccinations.isEnabled()
        ,"Body bolumundeki vaccination baglantisi tiklanabilir degil");

        hazalPage.bodyVaccinations.click();

        //3.Kullanıcı Vaccinations sayfasına erişebildiğini doğrular.

        String expectedUrl="https://qa.loyalfriendcare.com/en/Pets";
        String actualUrl=Driver.getDriver().getCurrentUrl();

        softAssert.assertEquals(actualUrl,expectedUrl);

        Driver.quitDriver();

        softAssert.assertAll();

    }

}
