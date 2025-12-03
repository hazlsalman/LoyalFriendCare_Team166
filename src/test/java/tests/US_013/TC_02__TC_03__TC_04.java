package tests.US_013;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.EnesPage;
import utilities.ConfigReader;
import utilities.Driver;

import static utilities.Driver.driver;

public class TC_02__TC_03__TC_04 {

    @AfterClass
    public void teardown(){
        driver.quit();
    }

    @Test
    public void logInOlarakanaSayfayaErisimTesti() {



        Driver.getDriver().get(ConfigReader.getProperty("url"));

        EnesPage enesPage = new EnesPage();
        enesPage.signInButton.click();
        enesPage.userNameKutu.sendKeys(ConfigReader.getProperty("userGecerliMail"));
        enesPage.passwordKutu.sendKeys(ConfigReader.getProperty("userGecerliPassword"));
        enesPage.logInSignIn.click();

    }


        @Test (dependsOnMethods = "logInOlarakanaSayfayaErisimTesti")
        public void asiSayfasinaErisimTesti() {

        //Siteye erişim sağlayan kayıtlı kullanıcı, ana sayfanın body kısmında yer alan "Vaccinations for Pets"
        // başlığı altındaki aşı figürünün birine click yaptığında seçtiği aşının sayfasına erişir ve seçtiği açının ismini
        // heading olarak sol üst köşede görür.
            EnesPage enesPage=new EnesPage();

        enesPage.bodyRabiesVaccine.click();

        String expectedHeading= "Rabies Vaccine";
        String actualHeading=enesPage.rabiesVaccineText.getText();
        Assert.assertEquals(actualHeading,expectedHeading);

        //Kayıtlı kullanıcı, seçtiği aşının sayfasına erişim sağladığında sayfada
        // "Appointment Booking" yazısını görünür olduğunu doğrular.

        String expectedYazi="Appointment Booking";
        String actualYazi=enesPage.randevuHeading.getText();
        Assert.assertEquals(actualHeading,expectedHeading);

       // Kayıtlı kullanıcı, seçtiği doktorun sayfasına erişim sağladığında randevu için gerekli olan kutuları
        // (tarih, telefon, mesaj vb.) ve "Appointment Booking" butonunu işlevsel olarak görür.



        WebElement asiTarihFormuTarihFormuElement=enesPage.asiTarihFormu;
        Assert.assertTrue(asiTarihFormuTarihFormuElement.isDisplayed());

        WebElement asiTelefonFormuElement=enesPage.asiTelefonFormu;
        Assert.assertTrue(asiTelefonFormuElement.isDisplayed());

        WebElement randevuDepartmansKategoriElement=enesPage.randevuDepartmansKategori;
        Assert.assertTrue(randevuDepartmansKategoriElement.isDisplayed());

        WebElement randevuDoctorsKategoriElement=enesPage.randevuDoctorsKategori;
        Assert.assertTrue(randevuDoctorsKategoriElement.isDisplayed());

        WebElement asiMesajFormuElement=enesPage.asiMesajFormu;
        Assert.assertTrue(asiMesajFormuElement.isDisplayed());

        Actions actions=new Actions(Driver.getDriver());
        actions.sendKeys(Keys.PAGE_DOWN).perform();

        WebElement randevuAlmaButonElement=enesPage.randevuAlmaButon;
        Assert.assertTrue(randevuAlmaButonElement.isDisplayed());

        Assert.assertTrue(randevuAlmaButonElement.isEnabled());



    }

}
