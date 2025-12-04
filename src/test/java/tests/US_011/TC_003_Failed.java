package tests.US_011;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.MelahatnurPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

import java.time.Duration;

public class TC_003_Failed extends TestBaseRapor {

    @Test
    public void departmanVeDoctorBos() {

        // Rapor oluşturma
        extentTest = extentReports.createTest("Negatif Randevu Testi",
                "Departman ve doktor seçilmeden randevu oluşturulamadığı ve hata mesajı görüntülenir.");

        MelahatnurPage melahatnurPage = new MelahatnurPage();

        extentTest.info("Login sayfasına gidiliyor.");
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        melahatnurPage.signinButonu.click();
        extentTest.info("Signin butonuna tıklandı.");

        melahatnurPage.mailKutusu.sendKeys(ConfigReader.getProperty("userGecerliMail"));
        melahatnurPage.passwordKutusu.sendKeys(ConfigReader.getProperty("userGecerliPassword"));
        melahatnurPage.girisButonu.click();
        extentTest.pass("Geçerli kullanıcı bilgileri ile başarılı şekilde login olundu.");

        ReusableMethods.bekle(2);

        extentTest.info("Wellness bölümüne geçiş yapılıyor.");
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(melahatnurPage.wellnessButonu).perform();
        melahatnurPage.wellnessButonu.click();
        ReusableMethods.bekle(2);
        extentTest.pass("Wellness sayfası başarıyla açıldı.");

        extentTest.info("Tarih ve telefon bilgileri dolduruluyor.");
        melahatnurPage.randevuTarihKutusu.sendKeys("02/02/2026");
        melahatnurPage.randevuTelefonKutusu.sendKeys("5551234567");

        extentTest.info("Departman dropdown açılıyor ve 'No Departments' seçiliyor.");
        melahatnurPage.departmanSecmeWellness.click();
        ReusableMethods.bekle(1);
        melahatnurPage.NoDepartments.click();
        extentTest.pass("Departman alanı boş bırakıldı.");

        extentTest.info("Doctor dropdown açılıyor ve uygun 'No Doctor' seçeneği bekleniyor.");
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

        js.executeScript("arguments[0].click();", melahatnurPage.doctorSecme);
        ReusableMethods.bekle(1);

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));

        WebElement noDoctor = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//li[contains(text(),'No') or contains(text(),'Doctor') or contains(text(),'Created')]")
                )
        );

        js.executeScript("arguments[0].click();", noDoctor);
        ReusableMethods.bekle(1);
        extentTest.pass("Doktor alanı boş bırakıldı.");

        extentTest.info("Mesaj kutusu dolduruluyor ve randevu oluşturma işlemi tetikleniyor.");
        melahatnurPage.randevuMesajKutusu.sendKeys("Randevu talep ediyorum.");
        melahatnurPage.appointmentBookingButonu.click();
        ReusableMethods.bekle(2);

        extentTest.info("Uyarı mesajı doğrulaması yapılıyor.");
        String expected = "Please select your doctor and department.";
        String actual = melahatnurPage.randevuOnayMesaji.getText();

        Assert.assertEquals(actual, expected,
                "Departman ve doktor seçilmediği halde başarı mesajı görüntülendi!");
        extentTest.pass("Beklenen uyarı mesajı görüntülendi ve doğrulandı.");

        Driver.quitDriver();
        extentTest.info("Test başarıyla sonlandırıldı, tarayıcı kapatıldı.");
    }
}
