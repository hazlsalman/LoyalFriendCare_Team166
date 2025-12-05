package tests.US_027;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HakimPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class TC_07_BedManagersVideoUploadTest extends TestBaseRapor {

    HakimPage hakimPage = new HakimPage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
    Actions actions = new Actions(Driver.getDriver());
    JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

    @Test
    public void test01() throws InterruptedException, IOException {

        extentTest = extentReports.createTest("TC_07", "Bed Manager Video Upload Test");

        // =========================================================
        // PRE-CONDITION:
        // =========================================================

        // 1-) Anasayfaya git
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        extentTest.info("Anasayfaya gidildi");

        // 2-) Sign In butonuna tıkla
        hakimPage.homePageSignInButton.click();
        extentTest.info("Sign In butonuna tıklandı");

        // 3-) Email ve password gir
        wait.until(ExpectedConditions.visibilityOf(hakimPage.loginEmailBox))
                .sendKeys("admin.abdul.hakim.kazanci@loyalfriendcare.com");
        hakimPage.loginPasswordBox.sendKeys("LFCare.0201");
        extentTest.info("Kullanıcı bilgileri girildi");

        // 4-) Sign In butonuna tıkla
        hakimPage.loginSignInButton.click();
        extentTest.pass("Giriş başarılı");

        // =========================================================
        // STEPS:
        // =========================================================

        // 1-) ⚙ AYARLAR SİMGESİNE TIKLA
        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.SettingsButton)).click();
        extentTest.info("Ayarlar butonuna tıklandı");

        // 2-) Soldan açılan sidebar menüyü hover ile aç
        wait.until(ExpectedConditions.visibilityOf(hakimPage.bedManagersParent));
        actions.moveToElement(hakimPage.bedManagersParent).perform();
        extentTest.info("Bed Managers ana menüsü üzerinde hover yapıldı");

        // 3-) Bed Managers menüsüne tıkla
        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.bedManagersParent)).click();
        extentTest.info("Bed Managers ana menüsüne tıklandı");

        // 4-) Bed Managers alt menüsüne tıkla
        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.subBedManagers)).click();
        extentTest.info("Bed Managers alt menüsüne tıklandı");

        // 5-) İlk yatak için Edit butonuna tıkla
        WebElement firstEditButton = Driver.getDriver()
                .findElement(By.xpath("//a[contains(@class,'fa-edit') and span[text()='Edit']]"));
        wait.until(ExpectedConditions.elementToBeClickable(firstEditButton)).click();
        extentTest.pass("İlk yatak için Edit butonuna tıklandı");

        // =========================================================
        // SENARYO: Video Yükleme ve Backend Fail Kontrolü
        // =========================================================

        try {
            // 1-) Video dosyası hazırla
            String filePath = System.getProperty("user.dir") + "/src/test/java/test_data/Video.mp4";
            File videoDosyasi = new File(filePath);
            extentTest.info("Video yolu: " + filePath);
            extentTest.info("Video mevcut mu? " + videoDosyasi.exists());

            if (!videoDosyasi.exists()) {
                extentTest.fail("Video dosyası bulunamadı: " + filePath);
                Assert.fail("Video dosyası bulunamadı: " + filePath);
            }

            // 2-) Video upload formunu bul
            WebElement videoUploadForm = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("form[action*='Audio/upload/store']")
            ));
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", videoUploadForm);
            extentTest.info("Video upload formu bulundu ve scroll yapıldı");

            // 3-) File input bul veya oluştur
            WebElement videoFileInput;
            List<WebElement> fileInputList = videoUploadForm.findElements(By.cssSelector("input[type='file']"));

            if (fileInputList.isEmpty()) {
                js.executeScript(
                        "var form = arguments[0];" +
                                "var input = document.createElement('input');" +
                                "input.type='file'; input.name='file'; input.accept='video/*';" +
                                "input.className='dz-hidden-input'; input.style.visibility='visible';" +
                                "input.style.display='block'; input.id='customVideoInput';" +
                                "form.appendChild(input);",
                        videoUploadForm
                );
                videoFileInput = Driver.getDriver().findElement(By.id("customVideoInput"));
                extentTest.info("Video file input JavaScript ile oluşturuldu");
            } else {
                videoFileInput = fileInputList.get(0);
                js.executeScript(
                        "arguments[0].style.visibility='visible'; arguments[0].style.display='block'; arguments[0].style.position='relative'; arguments[0].style.opacity='1';",
                        videoFileInput
                );
                extentTest.info("Mevcut video file input bulundu ve görünür yapıldı");
            }

            // 4-) Video dosyasını input'a gönder
            videoFileInput.sendKeys(filePath);
            extentTest.pass("Video dosyası gönderildi");

            // 5-) Save butonuna tıkla
            actions.moveToElement(hakimPage.bedManagersSaveButton).click().perform();
            extentTest.info("Save butonuna tıklandı");

            // 6-) Dashboard sayfasına dönüş ve success mesajı
            wait.until(ExpectedConditions.urlContains("/Dashboard/Posts"));
            extentTest.info("Dashboard sayfasına dönüldü");

            // 7-) BACKEND fail kontrolü
            ReusableMethods.tarihliTumSayfaResmiCek(Driver.getDriver(), "Video_Upload_Failed");
            Assert.fail("Video yükleme otomasyon testi tamamlandı. Başarılı kayıt mesajına rağmen " +
                    "video yüklenemedi. Başarılı kayıt mesajının ekran fotoğrafı alındı ve rapora eklendi. Test sonucu: FAILED");
            extentTest.warning("Video yükleme otomasyon testi tamamlandı. Başarılı kayıt mesajına rağmen " +
                    "video yüklenemedi. Başarılı kayıt mesajının ekran fotoğrafı alındı ve rapora eklendi. Test sonucu: FAILED");

        } catch (Exception e) {
            extentTest.fail("Video yükleme ve save sürecinde hata: " + e.getMessage());
            ReusableMethods.tarihliTumSayfaResmiCek(Driver.getDriver(), "Video_Upload_Exception");
            throw new AssertionError("Video upload hatası: " + e.getMessage());
        }

    }
}
