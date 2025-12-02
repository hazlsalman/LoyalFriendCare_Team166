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
import pages.yaprakPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.time.Duration;

public class TC02_RandevuFormu extends yaprakPage {



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
        ReusableMethods.bekle(2);

        WebElement vaccinationsRoomLink = Driver.getDriver()
                .findElement(By.xpath("//h3[text()='Vaccinations Room']"));
        vaccinationsRoomLink.click();
        ReusableMethods.bekle(2);

        Assert.assertTrue(appointmentDateInput.isDisplayed(), "tC04_Form görünmüyor!");
        appointmentDateInput.click();
        appointmentDateInput.clear();
        appointmentDateInput.sendKeys("12/12/2025", Keys.TAB);

        Assert.assertFalse(
                appointmentDateInput.getAttribute("value").isEmpty(),
                "tC04_Tarih seçimi yapılamadı!");
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
        WebElement deptOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='nice-select wide open']//li[text()='Dermatology']")));
        deptOption.click();

        wait.until(ExpectedConditions.elementToBeClickable(appointmentDoctorDD)).click();
        WebElement doctor = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='nice-select wide open']//li[contains(.,'Dr. Isabella Wong')]")));

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

        Assert.assertFalse(success, "tC05 💛Geçersiz bilgiyle gönderilen formda başarı mesajı göründü!");
        System.out.println("✅ TC_05 Negatif test geçti (hata mesajı bekleniyordu).");

        ReusableMethods.tarihliTumSayfaResimCek(Driver.getDriver());
    }

    @Test(priority = 2)
    public void tC06_pozitifTestRandevuOlusturma() {

        SoftAssert softAssert = new SoftAssert();

        Driver.getDriver().get("https://qa.loyalfriendcare.com/en/Beds/vaccinations-room");
        ReusableMethods.bekle(2);

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        Actions actions = new Actions(Driver.getDriver());

        appointmentPhoneBox.sendKeys("05322556677");

        appointmentDateInput.clear();
        appointmentDateInput.sendKeys("02/02/2025");
        appointmentDateInput.sendKeys(Keys.TAB);

        wait.until(ExpectedConditions.elementToBeClickable(appointmentDepartmentDD)).click();
        WebElement dept = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='nice-select wide open']//li[text()='Vaccinations']")));
        dept.click();

        wait.until(ExpectedConditions.elementToBeClickable(appointmentDoctorDD)).click();
        WebElement doctor = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='nice-select wide open']//li[contains(.,'Dr. Isabella Wong')]")));
        actions.moveToElement(doctor).pause(Duration.ofMillis(300)).click(doctor).perform();

        actions.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB).perform();

        appointmentMessageArea.sendKeys(" tC06_Pozitif Test - Başarılı randevu oluşturma");

        actions.moveToElement(appointmentSubmitBtn).click().perform();
        ReusableMethods.bekle(3);

        String expectedMessage = "Congratulations on your well-deserved success.";
        String actualMessage = successMessage.getText();

        softAssert.assertEquals(actualMessage, expectedMessage,
                " tC06_Başarı mesajı doğrulanamadı!");

        softAssert.assertAll();
    }

    @Test(priority = 3, dependsOnMethods = {"tC06_pozitifTestRandevuOlusturma"})
    public void tC07_anasayfayaYonlendirmeTest() {
        SoftAssert softAssert = new SoftAssert();

        String expectedUrl = "https://qa.loyalfriendcare.com/en/Beds/vaccinations-room";
        String currentUrl = Driver.getDriver().getCurrentUrl();

        softAssert.assertEquals(currentUrl, expectedUrl,
                " tC07_Kullanıcı randevu sonrası yönlendirilmedi! Mevcut URL: " + currentUrl);

        softAssert.assertAll();
    }

    @AfterClass
    public void tearDownClass() {
        Driver.quitDriver();
    }
}
