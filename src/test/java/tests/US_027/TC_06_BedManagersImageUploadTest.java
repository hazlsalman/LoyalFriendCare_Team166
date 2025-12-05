package tests.US_027;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import utilities.ReusableMethods;
import utilities.TestBaseRapor;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class TC_06_BedManagersImageUploadTest extends TestBaseRapor {

    HakimPage hakimPage = new HakimPage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
    Actions actions = new Actions(Driver.getDriver());
    JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

    @Test

    public void test01() throws InterruptedException, IOException {

        extentTest = extentReports.createTest("TC_06", "Bed Manager Image Upload Test");

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
        wait.until(ExpectedConditions.visibilityOf(hakimPage.bedManagersParent));
        actions.moveToElement(hakimPage.bedManagersParent).perform();


        // 3-) Bed Managers menüsüne tıkla
        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.bedManagersParent)).click();
        extentTest.info("Clicked Bed Managers menu");

        // 4-) Bed Managers alt menüsüne tıkla
        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.subBedManagers)).click();
        extentTest.info("Clicked Bed Managers sub menu");

        // 5-) EDİT ÖNCESİ, İLK YATAĞA AİT RESMİ KAYDET (ÖNCEKİ HALİ)
        try {
            WebElement firstBedThumbnailBefore = Driver.getDriver().findElement(
                    By.cssSelector("span.thumbnail-wrapper.d48.circul2.inline")
            );
            ReusableMethods.webElementResimCek(firstBedThumbnailBefore, "First_Bed_BEFORE_Upload");
            extentTest.info("Screenshot taken: First bed BEFORE image upload");
        } catch (Exception e) {
            extentTest.warning("Could not capture BEFORE screenshot: " + e.getMessage());
        }

        // 6-) EDİT ÖNCESİ, İLK YATAĞIN BULUNDUĞU SAYFANIN EKRAN GÖRÜNTÜSÜNÜ AL VE RAPORA EKLE.
        String resim1 = ReusableMethods.raporaResimEkle("1) DashboardPage_FirstBed_ImageBefore");
        extentTest.info("FOTOĞRAF 1/2: Dashboard Sayfası 1.Yatak: Resim editinden önceki hali.")
                .addScreenCaptureFromPath(resim1);

        // 7-) İLK YATAK İÇİN EDİT BUTONUNA TIKLA
        WebElement firstEditButton = Driver.getDriver()
                .findElement(By.xpath("//a[contains(@class,'fa-edit') and span[text()='Edit']]"));
        wait.until(ExpectedConditions.elementToBeClickable(firstEditButton)).click();
        extentTest.pass("Clicked Edit button for first bed");

        // =========================================================
        // SENARYO: Resim yükleme
        // =========================================================

        // 1-) Sayfayı aşağı kaydır
        actions.sendKeys(Keys.PAGE_DOWN).perform();

        // 2-) Dosya yolu hazırla
        String dosyaYolu = System.getProperty("user.dir") + "/src/test/java/test_data/Image.png";
        File resimDosyasi = new File(dosyaYolu);

        System.out.println("Dosya yolu: " + dosyaYolu);
        System.out.println("Dosya var: " + resimDosyasi.exists());

        if (!resimDosyasi.exists()) {
            extentTest.fail("Image file not found at: " + dosyaYolu);
            Assert.fail("Dosya bulunamadı: " + dosyaYolu);
        }
        extentTest.info("Image file found: " + dosyaYolu);

        try {
            // 3-) IMAGE UPLOAD FORMUNU BUL
            WebElement imageUploadFormu = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("form[action*='image/upload/store']")
            ));
            System.out.println("Image upload formu bulundu");
            extentTest.info("Image upload form located");

            // Forma scroll yap
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", imageUploadFormu);

            // 4-) FILE INPUT'U BUL VEYA OLUŞTUR
            WebElement imageFileInput = null;
            List<WebElement> fileInputListesi = imageUploadFormu.findElements(By.cssSelector("input[type='file']"));

            if (fileInputListesi.isEmpty()) {
                // JavaScript ile oluştur
                System.out.println("File input JavaScript ile oluşturuluyor.");

                js.executeScript(
                        "var form = arguments[0];" +
                                "var input = document.createElement('input');" +
                                "input.type = 'file';" +
                                "input.name = 'file';" +
                                "input.accept = 'image/*';" +
                                "input.style.visibility = 'visible';" +
                                "input.style.position = 'relative';" +
                                "input.style.display = 'block';" +
                                "input.id = 'customImageInput';" +
                                "form.appendChild(input);",
                        imageUploadFormu
                );


                Thread.sleep(500);
                imageFileInput = Driver.getDriver().findElement(By.id("customImageInput"));
                System.out.println("File input oluşturuldu");
                extentTest.info("File input created via JavaScript");
            } else {
                // Mevcut input'u kullan
                imageFileInput = fileInputListesi.get(0);
                System.out.println("Mevcut file input bulundu");
                extentTest.info("Existing file input found");

                // Görünür yap
                js.executeScript(
                        "arguments[0].style.visibility = 'visible';" +
                                "arguments[0].style.display = 'block';" +
                                "arguments[0].style.position = 'relative';",
                        imageFileInput
                );
            }

            // 5-) RESMİ YÜKLE
            imageFileInput.sendKeys(dosyaYolu);
            System.out.println("Resim dosyası gönderildi");
            extentTest.pass("Image file sent via sendKeys()");

            // Upload için bekle
           Thread.sleep(500);

        } catch (Exception e) {
            System.err.println("Image upload hatası: " + e.getMessage());
            e.printStackTrace();
            extentTest.fail("Image upload process failed: " + e.getMessage());
            ReusableMethods.tarihliTumSayfaResmiCek(Driver.getDriver(), "Image_Upload_Process_Failed");
            throw new AssertionError("Image upload başarısız: " + e.getMessage());
        }

        // 6-) SAVE BUTONUNA TIKLA
        actions.moveToElement(hakimPage.bedManagersSaveButton).click().perform();
        System.out.println("Save butonuna tıklandı");
        extentTest.info("Clicked Save button");

        // 7-) DASHBOARD SAYFASINA DÖNÜŞÜ VE SUCCESS MESAJINI TEST ET.
        wait.until(ExpectedConditions.urlToBe("https://qa.loyalfriendcare.com/en/Dashboard/Posts"));
        wait.until(ExpectedConditions.visibilityOf(hakimPage.successMessage));
        Assert.assertTrue(hakimPage.successMessage.isDisplayed(), "Success mesajı görünmüyor!");
        System.out.println("Dashboard'a dönüldü ve success mesajı görüldü");
        extentTest.pass("Returned to Dashboard and success message displayed");


        // =========================================================
        // DOĞRULAMA: DASHBOARD'DA İLK BED'İN RESMİNİ KONTROL ET
        // =========================================================


        // 8-) EDİT SONRASI, İLK YATAĞIN BULUNDUĞU SAYFANIN EKRAN GÖRÜNTÜSÜNÜ AL ve RAPORA EKLE.
        String resim2 = ReusableMethods.raporaResimEkle("2) DashboardPage_FirstBed_ImageAfter");
        extentTest.info("FOTOĞRAF 2/2: Dashboard Sayfası 1.Yatak: Resim editinden sonraki hali.")
                .addScreenCaptureFromPath(resim2);

        try {
            // İlk bed'in thumbnail wrapper'ını bul
            WebElement firstBedThumbnail = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("span.thumbnail-wrapper.d48.circul2.inline")
            ));
            System.out.println("İlk bed'in thumbnail wrapper'ı bulundu");

            // İçindeki img'yi al
            WebElement firstBedImage = firstBedThumbnail.findElement(By.tagName("img"));

            // Resmin src'sini kontrol et
            String imageSrc = firstBedImage.getAttribute("src");
            String dataSrc = firstBedImage.getAttribute("data-src");

            System.out.println("İlk bed resim src: " + imageSrc);
            System.out.println("İlk bed resim data-src: " + dataSrc);

            extentTest.info("Image src after upload: " + imageSrc);

            // resim yükleme editinden sonra, ilk yatağa ait resmi al ve rapora kaydet.
            ReusableMethods.webElementResimCek(firstBedThumbnail, "First_Bed_AFTER_Upload");
            extentTest.info("Screenshot taken: First bed AFTER image upload attempt");

            // Resim yüklü mü kontrol et
            if (imageSrc != null && !imageSrc.isEmpty() &&
                    !imageSrc.contains("placeholder") &&
                    !imageSrc.contains("default") &&
                    !imageSrc.contains("apple-icon") &&
                    !imageSrc.contains("Favicon") &&
                    imageSrc.contains("/images/")) {

                System.out.println(" RESİM SİSTEME BAŞARIYLA YÜKLENDİ!");
                System.out.println("Yüklenen resim URL'i: " + imageSrc);

                extentTest.pass("IMAGE SUCCESSFULLY UPLOADED TO SYSTEM!");
                extentTest.pass("Uploaded image URL: " + imageSrc);


                Assert.assertTrue(true, "Resim başarıyla sisteme yüklendi!");

            } else {
                System.err.println("RESİM YÜKLENEMEDİ!");
                System.err.println(" Bulunan src: " + imageSrc);
                System.err.println("Bu resim web sitesine ait, default placeholder resmidir. " +
                        "Dolayısıyla yüklenmek istenen resim, yüklenememiştir. Test failed'dır.");

                extentTest.warning("IMAGE UPLOAD FAILED - Backend Issue");
                extentTest.warning("Expected: Image from /images/ folder");
                extentTest.warning("Actual: " + imageSrc);
                extentTest.warning("Note: This is a BACKEND ISSUE. Manual upload works but automation upload does not.");
                extentTest.warning("Developer action required: Backend must handle Dropzone file uploads correctly.");
                extentTest.fail("Image not uploaded - Backend does not process file from automation");

                Assert.fail(" BACKEND ISSUE: Resim yüklenmedi! Manuel test başarılı ama otomasyon başarısız. Developer backend'i düzeltmeli. Src: " + imageSrc);
            }

        } catch (Exception e) {
            System.err.println("Dashboard'da resim kontrolü başarısız.");
            e.printStackTrace();
            extentTest.fail("Image verification failed: " + e.getMessage());
            Assert.fail("Resim doğrulama hatası: " + e.getMessage());
        }



        // =========================================================
        // TEST ÖZETİ
        // =========================================================
        extentTest.info("");
        extentTest.info("TOPLAM 2 FOTOĞRAF ÇEKİLDİ VE MANUEL OLARAK YÜKLENEN RESMİN, OTOMASYON İLE YÜKLENEMEDİĞİ TESPİT EDİLDİ. " +
                "OTOMASYON TEST SONUCU: FAILED");



    }
}
