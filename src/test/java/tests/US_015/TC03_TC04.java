package tests.US_015;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SudePage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class TC03_TC04 extends SudePage {
  SudePage sudePage;
  //Pre condition
    @Test
    public void Precondition(){
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        sudePage= new SudePage();
        sudePage.signInButton.click();
        sudePage.GecerliMail.sendKeys(ConfigReader.getProperty("userGecerliMail"));
        sudePage.GecerliSifre.sendKeys(ConfigReader.getProperty("userGecerliPassword"));
        sudePage.SubmitButton.click();
        sudePage.anaDepartmanMenusu.click();
    }
@Test
  public void DepartmanGoruntuleme(){
        sudePage= new SudePage();
    for (int i = 0; i <sudePage.DepartmanlarınTamamı.size() ; i++) {
        sudePage.DepartmanlarınTamamı.get(i).click();
        Assert.assertTrue(sudePage.DepartmanDetay.isDisplayed());
        Assert.assertTrue(sudePage.DepartmanDetay.isEnabled());
        Driver.quitDriver();
    }
}
@Test
   public void DepartmanlarıYonlendirme(){
        sudePage=new SudePage();
    for (int i = 0; i < sudePage.DepartmanlarınTamamı.size(); i++) {
        sudePage.DepartmanlarınTamamı.get(i).click();
        sudePage.AppointmentTarih.sendKeys("11.11.2027");
        sudePage.AppointmentTelNo.sendKeys("544875634");
        ReusableMethods.bekle(2);
        sudePage.DepartmanSecimi.click();
        sudePage.DoktorSecimi.click();
        sudePage.RandevuOnayMesajı.sendKeys("Randevunuz başarıyla onaylanmıştır :)");
        sudePage.BookingButton.click();
        Assert.assertTrue(sudePage.RandevuOnayMesajı.isDisplayed(),"Randevunuz maalesef ki onaylanmadı");

    }
    Driver.quitDriver();

    }




}
