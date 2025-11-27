package tests.US_017;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HazalPage;
import utilities.ConfigReader;
import utilities.Driver;

public class UserLoginTest {


    @Test
    public void userLoginTest(){

        //1.Kayıtlı kullanıcı web tarayıcısında Home Page URL’e(https://qa.loyalfriendcare.com/en) gider.
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        SoftAssert softAssert=new SoftAssert();

        //2.Home Page’in başarılı şekilde yüklendiği doğrulanır.
        String expectedUrl="https://qa.loyalfriendcare.com/en";
        String actualUrl=Driver.getDriver().getCurrentUrl();

        softAssert.assertEquals(actualUrl,expectedUrl,"Home page sayfasi basarili sekilde yuklenmedi");

        //3.Home Page ekranında Sign In butonunu görür ve tıklar.
        HazalPage hazalPage=new HazalPage();
         hazalPage.homePageSignInOrProfileButton.click();

        //4.Kayıtlı kullanıcı geçerli e-posta ve şifreyi girer.

        hazalPage.loginPageEmailKutusu.sendKeys(ConfigReader.getProperty("userGecerliMail"));
        hazalPage.loginPagePasswordKutusu.sendKeys(ConfigReader.getProperty("userGecerliPassword"));

        //5.Sign In butonuna tıklar.

        hazalPage.loginPageSignInButton.click();

        //6.Kayıtlı kullanıcının başarılı şekilde giriş yaptığı
        // ve Home Page sayfasına yönlendirildiği doğrulanır.

        String unExpectedProfileYazisi="Sign In";
        String actualProfileYazisi=hazalPage.homePageSignInOrProfileButton.getText();

        softAssert.assertNotEquals(actualProfileYazisi,unExpectedProfileYazisi
                ,"Kayitli kullanici basarili sekilde giris yapamadi");

        softAssert.assertTrue(hazalPage.homePageAramaKutusu.isDisplayed()
                ,"Kayitli kullanici home page sayfasina yonlendirilmedi");

        Driver.quitDriver();

        softAssert.assertAll();


    }
}
