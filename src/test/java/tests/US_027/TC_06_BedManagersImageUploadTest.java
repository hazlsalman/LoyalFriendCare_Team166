package tests.US_027;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HakimPage;
import utilities.ConfigReader;
import utilities.Driver;
import java.time.Duration;
import java.io.File;


    public class TC_06_BedManagersImageUploadTest {

        HakimPage hakimPage = new HakimPage();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

        @Test
        public void test01() throws InterruptedException {

            // =========================================================
            // PRE-CONDITION:
            // =========================================================

            // 1-) Anasayfaya git
            Driver.getDriver().get(ConfigReader.getProperty("url"));

            // 2-) Sign In butonuna tıkla
            hakimPage.homePageSignInButton.click();

            // 3-) Email ve password gir
            wait.until(ExpectedConditions.visibilityOf(hakimPage.loginEmailBox))
                    .sendKeys("admin.abdul.hakim.kazanci@loyalfriendcare.com");
            hakimPage.loginPasswordBox.sendKeys("LFCare.0201");

            // 4-) Sign In butonuna tıkla
            hakimPage.loginSignInButton.click();

            // =========================================================
            // STEPS:
            // =========================================================

            // 1-) ⚙ AYARLAR SİMGESİNE TIKLA
            wait.until(ExpectedConditions.elementToBeClickable(hakimPage.SettingsButton)).click();

            // 2-) Soldan açılan sidebar menüyü, hover ile aç
            Actions actions = new Actions(Driver.getDriver());
            wait.until(ExpectedConditions.visibilityOf(hakimPage.bedManagersParent));
            actions.moveToElement(hakimPage.bedManagersParent).perform();

            // 3-) Bed Managers menüsüne tıkla
            wait.until(ExpectedConditions.elementToBeClickable(hakimPage.bedManagersParent)).click();

            // 4-) Bed Managers alt menüsüne tıkla
            wait.until(ExpectedConditions.elementToBeClickable(hakimPage.subBedManagers)).click();

            // 5-) Edit butonuna tıkla (ilk yatak için)
            WebElement firstEditButton = Driver.getDriver()
                    .findElement(By.xpath("//a[contains(@class,'fa-edit') and span[text()='Edit']]"));
            wait.until(ExpectedConditions.elementToBeClickable(firstEditButton)).click();

            // =========================================================
            // SENARYO: Resim yükleme (Bed Image Upload)
            // =========================================================

            // 1-) Edit sayfasının yüklenmesini bekle
            Thread.sleep(200);

            // 2-) Resim yükleme alanını bul
            WebElement bedImageUploadArea = Driver.getDriver()
                    .findElement(By.cssSelector("div.dz-default.dz-message"));

            // 3-) Resim dosyasını hazırla
            File imageFile = new File("C:\\Users\\HP\\IdeaProjects\\LoyalFriendCare_Team166\\src\\test\\java\\test_data\\Image(1).png");

            // 4-) Resmi yükle
            bedImageUploadArea.sendKeys(imageFile.getAbsolutePath());

            // 5-) Save butonuna tıkla
            actions.moveToElement(hakimPage.bedManagersSaveButton).click().perform();

            // 6-) Success mesajını kontrol et
            wait.until(ExpectedConditions.visibilityOf(hakimPage.successMessage));
            Assert.assertTrue(hakimPage.successMessage.isDisplayed(), "Success mesajı görünmüyor!");

            // 7-) Dashboard sayfasına dönüldüğünü kontrol et
            wait.until(ExpectedConditions.urlToBe("https://qa.loyalfriendcare.com/en/Dashboard/Posts"));

            // Testi bitir, driver kapat
            Driver.quitDriver();
        }
    }
