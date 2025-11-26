package tests.US_044;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HazalPage;
import utilities.ConfigReader;
import utilities.Driver;

public class AdminDashboardTest {

    HazalPage hazalPage;

    @BeforeClass
    public void preconditionLogin(){

        Driver.getDriver().get(ConfigReader.getProperty("url"));

        hazalPage=new HazalPage();

        hazalPage.homePageSignInOrProfileButton.click();

        hazalPage.loginPageEmailKutusu.sendKeys(ConfigReader.getProperty("adminGecerliMail"));
        hazalPage.loginPagePasswordKutusu.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));

        hazalPage.loginPageSignInButton.click();

    }


    @Test
    public void adminDashboardGoruntulemeTest(){

        // 1.Sağ üst köşede ayar/ikon simgesi ve admin adı görünür olup olmadığı doğrulanır.
        hazalPage=new HazalPage();

        SoftAssert softAssert=new SoftAssert();

        softAssert.assertTrue(hazalPage.homePageSignInOrProfileButton.isDisplayed());

        //2.Admin adı tıklanabilirliği kontrol edilir ve tıklanır.

        softAssert.assertTrue(hazalPage.homePageSignInOrProfileButton.isEnabled());

        hazalPage.homePageSignInOrProfileButton.click();
        //3.Admin adına tıklanması sonrası kullanıcı Dashboard sayfasına yönlendirilir.
        //Dashboard sayfasının yüklenmesi ve admin adının burada görüntülenmesi doğrulanır.

        String expectedUrl="https://qa.loyalfriendcare.com/en/admin";
        String actualUrl=Driver.getDriver().getCurrentUrl();

        softAssert.assertEquals(actualUrl,expectedUrl);

        softAssert.assertTrue(hazalPage.dashboardPageAdminYaziElementi.isDisplayed());

        softAssert.assertAll();

        Driver.quitDriver();

    }

}
