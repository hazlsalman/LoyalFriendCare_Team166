package tests.US_044;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HazalPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBaseRapor;

public class AdminLogoutTest extends TestBaseRapor {

    HazalPage hazalPage;

    @BeforeClass
    public void preconditionLoginDashboard(){

        Driver.getDriver().get(ConfigReader.getProperty("url"));

        hazalPage=new HazalPage();

        hazalPage.homePageSignInOrProfileButton.click();

        hazalPage.loginPageEmailKutusu.sendKeys(ConfigReader.getProperty("adminGecerliMail"));
        hazalPage.loginPagePasswordKutusu.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));

        hazalPage.loginPageSignInButton.click();

        hazalPage.homePageSignInOrProfileButton.click();

    }


    @Test
    public void adminLogoutTest(){

        extentTest=extentReports.createTest("Admin Logout Test"
                ,"Admin kullanici logout butonunu kullanarak sistemden güvenli bir şekilde cikis yapabilmeli");

        //2.Admin adının sağ tarafında profil menüsü ikonu görünür ve tıklanabilirliği kontrol edilir.

        SoftAssert softAssert=new SoftAssert();
        hazalPage=new HazalPage();


        boolean clickable=hazalPage.profilMenuIkonu.isDisplayed() && hazalPage.profilMenuIkonu.isEnabled();

        softAssert.assertTrue(clickable,"Profil menusu ikonu goruntulenebilir veya tiklanabilir değil");

        extentTest.pass("Admin kullanici admin adinin sağ tarafinda profil menusu ikonunun görünür " +
                "ve tıkalanabilir oldugunu kontrol eder");

        //3.Profil menüsü ikonuna tıklanır.

        hazalPage.profilMenuIkonu.click();

        extentTest.info("Profil menusu ikonuna tiklar");

        //4.Açılan menüde Logout seçeneği görüntülenir.
        //Admin Logout seçeneğine tıklar.

        hazalPage.logoutButton.click();

        extentTest.info("Açılan menüde Logout seçeneği görüntülenir." +
                "Admin kullanici Logout seçeneğine tıklar.");

        //6.Admin kullanıcının oturumu sonlandırılır ve Home Page sayfasına yönlendirilir.
        //Home Page yüklenir ve çıkış işleminin tamamlandığı doğrulanır.

        String expectedProfileYaziElementi="Sign In";

        String actualProfileYaziElementi= hazalPage.homePageSignInOrProfileButton.getText();

        softAssert.assertEquals(actualProfileYaziElementi,expectedProfileYaziElementi,"Cikis islemi basariyla tamamlanmadi");


       extentTest.pass("Admin kullanıcının oturumu sonlandırılır ve Home Page sayfasına yönlendirilir." +
               "Home Page yüklenir ve çıkış işleminin tamamlandığı doğrulanır.");

        softAssert.assertAll();


    }

}
