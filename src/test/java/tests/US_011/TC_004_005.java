package tests.US_011;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MelahatnurPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class TC_004_005 {

    MelahatnurPage melahatnurPage = new MelahatnurPage();

    //Geçersiz ve eksik Phone Number girişlerini doğrulamak failed
    @Test
    public void GecersizPhoneTestFailed(){
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        melahatnurPage.signinButonu.click();
        melahatnurPage.mailKutusu.sendKeys(ConfigReader.getProperty("userGecerliMail"));
        melahatnurPage.passwordKutusu.sendKeys(ConfigReader.getProperty("userGecerliPassword"));
        melahatnurPage.girisButonu.click();

        melahatnurPage.doktorKartAlejandro.click();
        melahatnurPage.randevuTarihKutusu.sendKeys("02/02/2026");
        melahatnurPage.randevuTelefonKutusu.sendKeys("0000@!abhg*");
        melahatnurPage.randevuMesajKutusu.sendKeys("Randevu talep ediyorum.");
        melahatnurPage.appointmentBookingButonu.click();

        ReusableMethods.bekle(2);

        String expected = "The phone number must consist of 10 digits.";
        String actual = melahatnurPage.randevuOnayMesaji.getText();

        Assert.assertTrue(actual.contains(expected));

}

    //Randevu bilgileri doğru ve eksiksiz girildiğinde başarıyla oluşturulmasını doğrulamak
    @Test
    public void RandevuAlmaTestiPass() {

        Driver.getDriver().get(ConfigReader.getProperty("url"));

        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(melahatnurPage.wellnessButonu).perform();


        melahatnurPage.doktorKartAlejandro.click();
        melahatnurPage.randevuTarihKutusu.sendKeys("02/02/2026");
        melahatnurPage.randevuTelefonKutusu.sendKeys("5551234567");
        melahatnurPage.randevuMesajKutusu.sendKeys("Randevu talep ediyorum.");
        melahatnurPage.appointmentBookingButonu.click();

        String expected = "Congratulations on your well-deserved success.";
        String actual = melahatnurPage.randevuOnayMesaji.getText();
        Assert.assertEquals(actual, expected);

        Driver.quitDriver();

    }

}

