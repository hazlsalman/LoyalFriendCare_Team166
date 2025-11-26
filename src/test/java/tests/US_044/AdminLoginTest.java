package tests.US_044;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HazalPage;
import utilities.ConfigReader;
import utilities.Driver;

public class AdminLoginTest {

    @Test
    public void adminLoginTest(){

        //1. Admin kullanıcı web tarayıcıyı kullanarak ilgili URL’e (https://qa.loyalfriendcare.com/en) gider.
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        //2. Home Page ekranının başarılı şekilde yüklendiği doğrulanır.
        HazalPage hazalPage=new HazalPage();

        Assert.assertTrue(hazalPage.homePageAramaKutusu.isDisplayed());

        //3. Home Page ekranında Sign In butonuna tıklanır ve kullanıcı Sign In sayfasına yönlendirilir.
        hazalPage.homePageSignInOrProfileButton.click();

        //4. Admin kullanıcının e-posta ve şifresi doğru şekilde girilir.

        hazalPage.loginPageEmailKutusu.sendKeys(ConfigReader.getProperty("adminGecerliMail"));

        hazalPage.loginPagePasswordKutusu.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));

        //5. Sign in butonuna tıklanır.

        hazalPage.loginPageSignInButton.click();

        //6. Admin kullanıcının  başarılı şekilde giriş yaptığı ve
        // Home Page sayfasına yönlendirildiği doğrulanır.

        String unExpectedProfileYazisi="Sign In";

        Assert.assertNotEquals(hazalPage.homePageSignInOrProfileButton.getText(),unExpectedProfileYazisi);

        Assert.assertTrue(hazalPage.homePageAramaKutusu.isDisplayed());

        Driver.quitDriver();


    }
}
