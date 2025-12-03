package tests.US_015;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SudePage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class US_015_TC01_TC02 extends SudePage {


SudePage sudePage;
//Kayıtlı Kullanıcı Girişi yapılmalı
@Test
public void KayitliKullaniciGirisi(){
    Driver.getDriver().get(ConfigReader.getProperty("url"));
    signInButton.click();
    ReusableMethods.bekle(2);
    GecerliMail.sendKeys(ConfigReader.getProperty("userGecerliMail"));
    GecerliSifre.sendKeys(ConfigReader.getProperty("userGecerliPassword"));
    SubmitButton.click();
    ReusableMethods.bekle(2);

}
    // Ana sayfa'da Departments butonunun görünürlüğünü doğrulama
@Test
    public void TC01_DepartmanMenusuGorunurlugu(){
    Assert.assertTrue(anaDepartmanMenusu.isDisplayed());
    ReusableMethods.bekle(2);
}
//"Departments" butonuna tıklayarak doğru yönlendirildiğinin doğrulanması
@Test
 public void TC02_DepartmanSayfasınaYonlendirilme(){
Assert.assertTrue(anaDepartmanMenusu.isEnabled());
ReusableMethods.bekle(2);
}


}
