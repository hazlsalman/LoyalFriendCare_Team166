package tests.US_027;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
        Actions actions = new Actions(Driver.getDriver());
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
            actions = new Actions(Driver.getDriver());
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
            // SENARYO: Resim yükleme
            // =========================================================


            // 1-) Sayfayı aşağı kaydır
            actions.sendKeys(Keys.PAGE_DOWN).perform();
            Thread.sleep(300);

            // 2-) Dosya yolu hazırla
            String filePath = System.getProperty("user.dir") + "/src/test/java/test_data/Image.png";
            File resimDosyası = new File(filePath);

            System.out.println("Dosya yolu: " + filePath);
            System.out.println("Dosya var mı? " + resimDosyası.exists());

            if (!resimDosyası.exists()) {
                Assert.fail("Dosya bulunamadı: " + filePath);
            }

            // 3-) File input'u bul (BU SATIR ÖNEMLİ - HİÇBİR YERE TIKLAMA!)
            WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("input[type='file']")
            ));

            // 4-) Doğrudan dosya yolunu gönder (pencere açılmaz!)
            fileInput.sendKeys(filePath);

            // 5-) Yükleme işleminin tamamlanmasını bekle
            Thread.sleep(300);

            // 6-) Yüklenen resmin sayfada göründüğünü kontrol et (opsiyonel)
            try {
                WebElement uploadedPreview = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector(".dz-preview, .dz-image, .dz-success")));
                System.out.println(" Resim başarıyla yüklendi.");
            } catch (Exception e) {
                System.out.println(" Resim önizlemesi bulunamadı ama devam ediliyor.");
            }

            // 7-) Save butonuna scroll yap ve tıkla
            actions.moveToElement(hakimPage.bedManagersSaveButton).perform();
            wait.until(ExpectedConditions.elementToBeClickable(hakimPage.bedManagersSaveButton));
            hakimPage.bedManagersSaveButton.click();

            // 8-) Success mesajını kontrol et
            wait.until(ExpectedConditions.visibilityOf(hakimPage.successMessage));
            Assert.assertTrue(hakimPage.successMessage.isDisplayed(),
                    "Success mesajı görünmüyor!");

            // 9-) Dashboard sayfasına dönüldüğünü kontrol et
            wait.until(ExpectedConditions.urlContains("/Dashboard/Posts"));

            Driver.quitDriver();
        }
    }
