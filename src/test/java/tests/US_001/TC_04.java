package tests.US_001;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EnesPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC_04 {
    @Test
    public void aramaKutusuTesti() {
        // Ziyaretçi, herhangi bir giriş veya kayıt yapmadan url ile siteye erişim sağlar.

       //  Kayıtlı veya yönetici kullanıcı, url ile siteye erişim sağlar.
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        //Siteye erişim sağlayan kullanıcı, body kısmındaki deparments, doctors, vaccinations bölümlerini ve her bölümün altındaki figürleri görür.

        EnesPage enesPage=new EnesPage();

        WebElement tumBodyElement=enesPage.tumBody;
        Assert.assertTrue(tumBodyElement.isDisplayed());

        WebElement bodyDeparmentsElement=enesPage.bodyDeparments;
       Assert.assertTrue(bodyDeparmentsElement.isDisplayed());

       Actions actions=new Actions(Driver.getDriver());
        actions.sendKeys(Keys.PAGE_DOWN).perform();

        WebElement bodyWellnessElement=enesPage.bodyWellness;
        Assert.assertTrue(bodyWellnessElement.isDisplayed());

        actions.sendKeys(Keys.PAGE_DOWN).perform();

        WebElement bodyDoctorsElement=enesPage.bodyDoctors;
        Assert.assertTrue(bodyDoctorsElement.isDisplayed());

        WebElement bodyDrAlejandroMartinezElement=enesPage.bodyDrAlejandroMartinez;
        Assert.assertTrue(bodyDrAlejandroMartinezElement.isDisplayed());

        actions.sendKeys(Keys.PAGE_DOWN).perform();

        WebElement bodyVaccinationsElement= enesPage.bodyVaccinations;
       Assert.assertTrue(bodyVaccinationsElement.isDisplayed());

       WebElement bodyRabiesVaccineElement=enesPage.bodyRabiesVaccine;
        Assert.assertTrue(bodyRabiesVaccineElement.isDisplayed());

        //Kullanıcı body kısmındaki deparments, doctors, vaccinations bölümlerinin
        // ve her bölümün altındaki figürlerin tıklanabilir olduğunu doğrular.


        Assert.assertTrue(bodyDeparmentsElement.isEnabled());
        Assert.assertTrue(bodyWellnessElement.isEnabled());
        Assert.assertTrue(bodyDoctorsElement.isEnabled());
        Assert.assertTrue(bodyDrAlejandroMartinezElement.isEnabled());
        Assert.assertTrue(bodyVaccinationsElement.isEnabled());
        Assert.assertTrue(bodyRabiesVaccineElement.isEnabled());



        Driver.quitDriver();









    }
}
