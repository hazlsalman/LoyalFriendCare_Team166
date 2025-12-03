package tests.US_011;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MelahatnurPage;
import utilities.ConfigReader;
import utilities.Driver;


public class TC_001_002 {

    //Body bölümündeki departman kartlarının görünürlüğünü
    // ve tıklanabilirliğini doğrulamak

    @Test
    public void siteyegirisTest() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        MelahatnurPage melahatnurPage = new MelahatnurPage();


        melahatnurPage.signinButonu.click();

        melahatnurPage.mailKutusu
                .sendKeys(ConfigReader.getProperty("userGecerliMail"));

        melahatnurPage.passwordKutusu
                .sendKeys(ConfigReader.getProperty("userGecerliPassword"));

        melahatnurPage.girisButonu.click();

        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(melahatnurPage.wellnessButonu).perform();


        Assert.assertTrue(melahatnurPage.wellnessButonu.isDisplayed());
        //Departman Detay sayfasi acildi
        melahatnurPage.wellnessButonu.click();

        Assert.assertTrue(melahatnurPage.randevuAlmaButon.isDisplayed());
        // randevu olusturma sayfasindayiz


        Driver.quitDriver();
    }
       // Departman detay sayfasında randevu alanlarının görünürlüğünü ve formun eksik doldurulduğunda uyarı verdiğini doğrulamak

        @Test (dependsOnMethods = "siteyegirisTest")
        public void randevuolusturmaTesti() {











        }


    }


