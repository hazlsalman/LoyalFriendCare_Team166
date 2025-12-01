package tests.US_019;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MelahatnurPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class TC_001_002_003_004 {

    //Sign Out seçeneğinin görünürlüğünü ve çalışmasını doğrulamak
    //Sign Out sonrası yönlendirmeyi doğrulamak
    //Çıkış yapıldıktan sonra geri tuşu/yenileme ile sisteme geri dönülememesini doğrulamak
    //Sign Out işleminin farklı roller (kullanıcı & admin) için sorunsuz çalıştığını doğrulamak

    MelahatnurPage melahatnurPage = new MelahatnurPage();


    //Sign Out seçeneğinin görünürlüğünü doğrulamak
    @Test
    public void signoutTesti() {

        Driver.getDriver().get(ConfigReader.getProperty("url"));

        melahatnurPage.signinButonu.click();

        melahatnurPage.mailKutusu
                .sendKeys(ConfigReader.getProperty("userGecerliMail"));

        melahatnurPage.passwordKutusu
                .sendKeys(ConfigReader.getProperty("userGecerliPassword"));

        melahatnurPage.girisButonu.click();
        ReusableMethods.bekle(2);

        //signout butonu gorunurlugu
        Assert.assertTrue(melahatnurPage.signOutButonu.isDisplayed());

    }


    //Sign Out butonu tiklanabilirligini ve sign out sonrası yönlendirmeyi ve doğrulamak
    @Test(dependsOnMethods = "signoutTesti")
            public void signoutTest01(){
        //signout butonu tiklanabilirligi
        melahatnurPage.signOutButonu.click();

        // signout yapmis sign ine donmus basarili sekilde cikti
        Assert.assertTrue(melahatnurPage.signinButonu.isDisplayed());

    }


    //Çıkış yapıldıktan sonra geri tuşu/yenileme ile sisteme geri dönülememesini doğrulamak

    @Test(dependsOnMethods = "signoutTest01")
    public void signoutTest02(){

        // sayfayı yenile
        Driver.getDriver().navigate().refresh();
        ReusableMethods.bekle(1);

        // gerçek logout kontrolü:
        Assert.assertTrue(melahatnurPage.signinButonu.isDisplayed());


        // geri tuşu
        Driver.getDriver().navigate().back();
        ReusableMethods.bekle(1);

        // geri gelince yine login ekranı görünmeli
        Assert.assertTrue(melahatnurPage.signinButonu.isDisplayed());

    }



    //Sign Out işleminin farklı roller (kullanıcı & admin) için sorunsuz çalıştığını doğrulamak
    @Test (dependsOnMethods = "signoutTest02")
    public void signoutTesti03() {


        Assert.assertTrue(melahatnurPage.signinButonu.isDisplayed());

        ReusableMethods.bekle(2);
            melahatnurPage.signinButonu.click();

            melahatnurPage.mailKutusu
                    .sendKeys(ConfigReader.getProperty("adminGecerliMail"));

            melahatnurPage.passwordKutusu
                    .sendKeys(ConfigReader.getProperty("adminGecerliPassword"));

            melahatnurPage.girisButonu.click();
            melahatnurPage.signOutButonu.click();
        Driver.quitDriver();

        }

    }


