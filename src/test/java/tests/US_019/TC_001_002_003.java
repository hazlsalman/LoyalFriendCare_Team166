package tests.US_019;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MelahatnurPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class TC_001_002_003 {

    //Sign Out seçeneğinin görünürlüğünü ve çalışmasını doğrulamak
    //Sign Out sonrası yönlendirmeyi doğrulamak
    //Çıkış yapıldıktan sonra geri tuşu/yenileme ile sisteme geri dönülememesini doğrulamak
    //Sign Out işleminin farklı roller (kullanıcı & admin) için sorunsuz çalıştığını doğrulamak

    MelahatnurPage melahatnurPage = new MelahatnurPage();

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

    @Test(dependsOnMethods = "signoutTesti")
            public void signoutTest01(){
        //signout butonu tiklanabilirligi
        melahatnurPage.signOutButonu.click();

        // signout yapmis sign ine donmus basarili sekilde cikti
        Assert.assertTrue(melahatnurPage.signinButonu.isDisplayed());

    }

    @Test(dependsOnMethods = "signoutTesti")
    public void signoutTest02(){
        // sayfayi yenile
        Driver.getDriver().navigate().refresh();
        Assert.assertFalse(melahatnurPage.signOutButonu.isDisplayed());

        // Geri tuşu
        Driver.getDriver().navigate().back();
        Assert.assertFalse(melahatnurPage.signOutButonu.isDisplayed());

        //sayfasyi yenileyince ya da geri yapinca kullanici hesabindan cikmiyor
        Driver.quitDriver();
    }


        }
