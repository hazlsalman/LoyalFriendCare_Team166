package tests.US_001;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EnesPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC_05 {

    @Test
    public void islevselFooterTesti() {
        // Ziyaretçi, herhangi bir giriş veya kayıt yapmadan url ile siteye erişim sağlar.

        //  Kayıtlı veya yönetici kullanıcı, url ile siteye erişim sağlar.
        Driver.getDriver().get(ConfigReader.getProperty("url"));
       // Siteye erişim sağlayan kullanıcı, home page sayfasının footer bölümündeki tüm textleri ve logoları/linkleri görür.

        EnesPage enesPage=new EnesPage();

        WebElement tumFooterElement=enesPage.tumFooter;
        Assert.assertTrue(tumFooterElement.isDisplayed());

        WebElement footerLogoElement=enesPage.footerLogo;
        Assert.assertTrue(footerLogoElement.isDisplayed());

        WebElement footerWellnesElement=enesPage.footerWellnes;
        Assert.assertTrue(footerWellnesElement.isDisplayed());

        WebElement footerDeparmentsYaziElement=enesPage.footerDeparmentsYazi;
        Assert.assertTrue(footerDeparmentsYaziElement.isDisplayed());

        WebElement footerFollowUsYaziElement=enesPage.footerFollowUsYazi;
        Assert.assertTrue(footerFollowUsYaziElement.isDisplayed());

        WebElement footerFacebookElement=enesPage.footerFacebook;
        Assert.assertTrue(footerFacebookElement.isDisplayed());


        //Kullanıcı home page sayfasının footer bölümündeki tüm logoların/linklerin tıklanabilir olduğunu doğrular.

        Assert.assertTrue(footerLogoElement.isEnabled());
        Assert.assertTrue(footerFacebookElement.isEnabled());
        Assert.assertTrue(footerWellnesElement.isEnabled());


        Driver.quitDriver();














    }
    }




