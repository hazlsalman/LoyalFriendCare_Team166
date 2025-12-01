package tests.US_001;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EnesPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC_03 {
@Test
   public void aramaKutusuTesti(){
       // Ziyaretçi, herhangi bir giriş veya kayıt yapmadan url ile siteye erişim sağlar.

//        Kayıtlı veya yönetici kullanıcı, url ile siteye erişim sağlar.
       Driver.getDriver().get(ConfigReader.getProperty("url"));

       // Siteye erişim sağlayan kullanıcı  arama kutusunu görür ve kutunun içine aramak için bir kelime yazar. (Rabies Vaccine)


       EnesPage enesPage =new EnesPage();
   WebElement aramaKutusuElement=enesPage.aramaKutusu;
   Assert.assertTrue(aramaKutusuElement.isDisplayed());
   enesPage.aramaKutusu.sendKeys("Rabies Vaccine");
  WebElement searchButtonElement=enesPage.searchButton;
  Assert.assertTrue(searchButtonElement.isEnabled());
   enesPage.searchButton.click();
   //sonuç bulunabildiği doğrulanr
   int actualListelenenSonuc=enesPage.listelenenSonuc.size();
    Assert.assertTrue(actualListelenenSonuc>0);
    Driver.quitDriver();




   }

}
