package tests.US_012;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.EnesPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;

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
    public void doctorSayfasinaErisimTesti() {


        //Siteye erişim sağlayan kayıtlı kullanıcı, ana sayfanın body kısmında yer alan doktorların
        // birine cilck yaptığında seçtiği doktorun sayfasına ulaşır ve seçtiği doktorun ismini heading olarak sol üst köşede görür.
        EnesPage enesPage=new EnesPage();
        enesPage.bodyDrAlejandroMartinez.click();

        String expectedHeading= "Dr. Alejandro Martinez";
        String actualHeading=enesPage.doctorsIsmiHeading.getText();
        Assert.assertEquals(actualHeading,expectedHeading);

        //Kayıtlı kullanıcı, seçtiği doktorun sayfasına erişim sağladığında sayfada
        // "Appointment Booking" yazısını görünür olduğunu doğrular.

        String expectedYazi="Appointment Booking";
        String actualYazi=enesPage.randevuHeading.getText();
        Assert.assertEquals(actualHeading,expectedHeading);

        //Kayıtlı kullanıcı, seçtiği doktorun sayfasına erişim sağladığında randevu için gerekli olan kutuları (tarih, telefon, mesaj vb.)
        // ve "Appointment Booking" butonunu tıklanabilir olarak görür.

        WebElement doctorsTarihFormuElement=enesPage.doctorsTarihFormu;
        Assert.assertTrue(doctorsTarihFormuElement.isDisplayed());

        WebElement doctorsTelefonFormuElement=enesPage.doctorsTelefonFormu;
        Assert.assertTrue(doctorsTelefonFormuElement.isDisplayed());

        WebElement randevuDepartmansKategoriElement=enesPage.randevuDepartmansKategori;
        Assert.assertTrue(randevuDepartmansKategoriElement.isDisplayed());

        WebElement randevuDoctorsKategoriElement=enesPage.randevuDoctorsKategori;
        Assert.assertTrue(randevuDoctorsKategoriElement.isDisplayed());

        WebElement doctorsMesajFormuElement=enesPage.doctorsMesajFormu;
        Assert.assertTrue(doctorsMesajFormuElement.isDisplayed());

        Actions actions=new Actions(Driver.getDriver());
        actions.sendKeys(Keys.PAGE_DOWN).perform();

        WebElement randevuAlmaButonElement=enesPage.randevuAlmaButon;
        Assert.assertTrue(randevuAlmaButonElement.isDisplayed());

        Assert.assertTrue(randevuAlmaButonElement.isEnabled());




    }

}
