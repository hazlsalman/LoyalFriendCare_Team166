package tests.US_022;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MelahatnurPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class TC_004 {

    //Dashboard özet bilgilerinin görünürlüğünü ve “Learn More” linklerini doğrulamak
    MelahatnurPage melahatnurPage = new MelahatnurPage();

    @Test
    public void dashboardTC_004(){
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        melahatnurPage.signinButonu.click();
        melahatnurPage.mailKutusu
                .sendKeys(ConfigReader.getProperty("adminGecerliMail"));
        melahatnurPage.passwordKutusu
                .sendKeys(ConfigReader.getProperty("adminGecerliPassword"));
        melahatnurPage.girisButonu.click();
        ReusableMethods.bekle(2);
        melahatnurPage.adminPaneliButonu.click();

        Assert.assertTrue(melahatnurPage.ozetBilgiButonu.isDisplayed());
        melahatnurPage.ozetBilgiButonu.click();

        String expectedUrl = "https://qa.loyalfriendcare.com/en/Dashboard/Posts";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        Assert.assertEquals(actualUrl,expectedUrl);
        Driver.quitDriver();

    }


}
