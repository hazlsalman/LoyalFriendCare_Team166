package tests.US_014;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.YaprakPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.time.Duration;

public class TC02_RandevuFormu extends YaprakPage {



    @BeforeClass
    public void setupSignIn() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        anasayfaSigninButonu.click();
        ReusableMethods.bekle(2);
        anasayfaEmailKutusu.sendKeys(ConfigReader.getProperty("userGecerliMail"));
        anasayfaPasswordKutusu.sendKeys(ConfigReader.getProperty("userGecerliPassword"));
        anasayfaSigninGirisButonu.click();
        ReusableMethods.bekle(3);
    }

    @Test(priority = 1)
    public void tC04_takvimIkonuTarihSecimi() {
        Driver.getDriver().get("https://qa.loyalfriendcare.com/en/Departments/vaccinations");
        ReusableMethods.bekle(1);
        vaccinationsRoomLink.click();
        ReusableMethods.bekle(1);
        Assert.assertTrue(appointmentDateInput.isDisplayed(), "tC04_Form görünmüyor!");
        appointmentDateInput.click();
        appointmentDateInput.clear();
        appointmentDateInput.sendKeys("12/12/2025", Keys.TAB);
        if (appointmentDateInput.getAttribute("value").isEmpty()) {
            throw new RuntimeException("HATA: Tarih inputu boş kaldı, tarih yazılamadı!");
        }
        System.out.println("Tarih girişi başarılı.");
    }

    @Test(priority = 3)
    public void tC05_negatifTestRandevuOlusturma() {
        Driver.getDriver().get("https://qa.loyalfriendcare.com/en/Beds/dermatology-room");
        ReusableMethods.bekle(2);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        Actions actions = new Actions(Driver.getDriver());
        appointmentPhoneBox.clear();
        appointmentPhoneBox.sendKeys("abcd123");
        appointmentDateInput.clear();
        appointmentDateInput.sendKeys("01/01/2023");
        ReusableMethods.bekle(1);
        wait.until(ExpectedConditions.elementToBeClickable(appointmentDepartmentDD)).click();
        WebElement deptOption = wait.until(ExpectedConditions.elementToBeClickable(dermatologyOption));
        deptOption.click();
        wait.until(ExpectedConditions.elementToBeClickable(appointmentDoctorDD)).click();
        WebElement doctor = wait.until(ExpectedConditions.visibilityOf(doctorIsabellaOption));
        actions.moveToElement(doctor).pause(300).click(doctor).perform();
        actions.sendKeys(Keys.TAB).sendKeys(Keys.TAB).perform();
        ReusableMethods.bekle(1);
        appointmentMessageArea.clear();
        appointmentMessageArea.sendKeys("tC05_Negatif Test 123");
        actions.moveToElement(appointmentSubmitBtn).click().perform();
        ReusableMethods.bekle(2);
        boolean success;
        try {
            success = appointmentSuccessMessage.isDisplayed();
        } catch (Exception e) {
            success = false;
        }
        if (success) {
            throw new RuntimeException("HATA: Geçersiz bilgiyle form gönderildi ve BAŞARI mesajı alındı! (Mesaj görünmemeliydi)");
        }
        System.out.println("TC_05 Negatif test geçti (Başarı mesajı çıkmadı, beklenen durum sağlandı).");

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver());
    }

    @Test(priority = 2)
    public void tC06_pozitifTestRandevuOlusturma() {
        SoftAssert softAssert = new SoftAssert();
        Driver.getDriver().get("https://qa.loyalfriendcare.com/en/Beds/vaccinations-room");
        ReusableMethods.bekle(2);
        Actions actions = new Actions(Driver.getDriver());
        appointmentPhoneBox.sendKeys("05322556677");
        appointmentDateInput.clear();
        appointmentDateInput.sendKeys("02/02/2025");
        appointmentDateInput.sendKeys(Keys.TAB);
        appointmentDepartmentDD.click();
        ReusableMethods.bekle(1);
        departmentOptionVaccinations.click();
        appointmentDoctorDD.click();
        ReusableMethods.bekle(1);
        actions.moveToElement(doctorOptionIsabella)
                    .click()
                    .perform();
        actions.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB).perform();
        appointmentMessageArea.sendKeys(" tC06_Pozitif Test - Başarılı randevu oluşturma");
        actions.moveToElement(appointmentSubmitBtn).click().perform();
        ReusableMethods.bekle(2);
        String expectedMessage = "Congratulations on your well-deserved success.";
        String actualMessage = successMessage.getText();
        if (!actualMessage.equals(expectedMessage)) {
            throw new RuntimeException("tC06_Başarı mesajı doğrulanamadı! Beklenen: " + expectedMessage + ", Ancak Gelen: " + actualMessage);
        }
        softAssert.assertAll();
    }

    @Test(priority = 3, dependsOnMethods = {"tC06_pozitifTestRandevuOlusturma"})
    public void tC07_anasayfayaYonlendirmeTest() {
        String expectedUrl = "https://qa.loyalfriendcare.com/en/Beds/vaccinations-room";
        String currentUrl = Driver.getDriver().getCurrentUrl();
        if (!currentUrl.equals(expectedUrl)) {
            throw new RuntimeException("tC07_Kullanıcı randevu sonrası doğru sayfaya yönlendirilmedi! " +
                    "\nBeklenen: " + expectedUrl +
                    "\nMevcut  : " + currentUrl);

        }
        System.out.println("tC07_anasayfayaYonlendirmeTest başarılı"+currentUrl);
    }

    @AfterClass
    public void tearDownClass() {
        Driver.quitDriver();
    }
}
