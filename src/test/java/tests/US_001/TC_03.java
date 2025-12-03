package tests.US_001;

import org.testng.annotations.Test;
import pages.EnesPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC_03 {
@Test
   public void anaSayfayaErisimTesti(){
       // Ziyaretçi, herhangi bir giriş veya kayıt yapmadan url ile siteye erişim sağlar.

//        Kayıtlı veya yönetici kullanıcı, url ile siteye erişim sağlar.
       Driver.getDriver().get(ConfigReader.getProperty("url"));

       // Siteye erişim sağlayan kullanıcı  arama kutusunu görür ve kutunun içine aramak için bir kelime yazar. (Rabies Vaccine)


       EnesPage enesPage =new EnesPage();
   enesPage.aramaKutusu.isDisplayed();
   enesPage.aramaKutusu.sendKeys("Rabies Vaccine");
  enesPage.searchButton.isEnabled();
   enesPage.searchButton.click();




   }

}
