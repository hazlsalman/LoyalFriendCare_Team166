package tests.US_016;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SudePage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class DoktorGoruntulemeVeRandevuTests extends SudePage {
   SudePage sudePage;
   @Test
public void Precondition(){
       Driver.getDriver().get(ConfigReader.getProperty("url"));
       sudePage= new SudePage();
       sudePage.signInButton.click();
       sudePage.GecerliMail.sendKeys(ConfigReader.getProperty("userGecerliMail"));
       sudePage.GecerliSifre.sendKeys(ConfigReader.getProperty("userGecerliPassword"));
       sudePage.SubmitButton.click();
       sudePage.anaDoktorMenusu.click();
   }
@Test
 public void DoktorMenusuGoruntulume(){
sudePage =new SudePage();
    for (int i = 0; i <sudePage.DoktorlarinMenusununTamami.size() ; i++) {
        sudePage.DoktorlarinMenusununTamami.get(i).click();
        Assert.assertTrue(sudePage.DoktorDescription.isDisplayed());
        Assert.assertTrue(sudePage.DoktorReview.isDisplayed());

        Driver.quitDriver();
    }
   }

   @Test
   public void DoktorAppointmentBooking(){

       sudePage=new SudePage();
       for (int i = 0; i < sudePage.DoktorlarinMenusununTamami.size(); i++) {
           sudePage.DoktorlarinMenusununTamami.get(i).click();
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
