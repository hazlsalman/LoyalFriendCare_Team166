package tests.US_022;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MelahatnurPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class TC_001_002_003 {

    //Admin hesabıyla giriş sonrası admin paneline erişimi doğrulamak
    //Sağ üstteki admin butonunun çalışmasını ve doğru kullanıcı adını doğrulamak
    //Sol menü başlıklarının ve alt seçeneklerin görünürlüğünü ve tıklanabilirliğini doğrulamak
    //Dashboard özet bilgilerinin görünürlüğünü ve “Learn More” linklerini doğrulamak
    //Dashboard erişiminin yalnızca admin kullanıcılar için çalıştığını doğrulamak

    MelahatnurPage melahatnurPage = new MelahatnurPage();

    //Admin hesabıyla giriş sonrası admin paneline erişimi doğrulamak
    @Test
    public void TC_01() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        melahatnurPage.signinButonu.click();
        melahatnurPage.mailKutusu
                .sendKeys(ConfigReader.getProperty("adminGecerliMail"));
        melahatnurPage.passwordKutusu
                .sendKeys(ConfigReader.getProperty("adminGecerliPassword"));
        melahatnurPage.girisButonu.click();
        ReusableMethods.bekle(2);

        //admin yazi butonu gorunuyor mu
        Assert.assertTrue(melahatnurPage.adminPaneliButonu.isDisplayed());

    }

    //Sağ üstteki admin butonunun çalışmasını ve doğru kullanıcı adını doğrulamak
    @Test (dependsOnMethods = "TC_01")
            public void TC_02() {
        //admin yazi butonundaki yazi dogrulugu
        String expectedAdminYazi = "admin.hazal.salman";
        String actualAdminYazi = melahatnurPage.adminPaneliButonu.getText();

        Assert.assertEquals(actualAdminYazi, expectedAdminYazi);

        //admin yazi butonu tiklanabilir
        melahatnurPage.adminPaneliButonu.click();
    }

    //Sol menü başlıklarının
    // ve alt seçeneklerin görünürlüğünü ve tıklanabilirliğini doğrulamak
    @Test(dependsOnMethods = "TC_02")
    public void TC_03(){

        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(melahatnurPage.solMenu).perform();

        Assert.assertTrue(melahatnurPage.rolesElementi.isDisplayed());
        melahatnurPage.rolesElementi.click();
        ReusableMethods.bekle(2);

        // alt secenegin gorunur ve tiklanabilirligi

        Driver.quitDriver();
    }


}
