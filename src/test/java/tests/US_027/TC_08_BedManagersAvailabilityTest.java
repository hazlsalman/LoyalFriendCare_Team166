package tests.US_027;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.HakimPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;
import java.io.IOException;
import java.time.Duration;

public class TC_08_BedManagersAvailabilityTest extends TestBaseRapor {

    HakimPage hakimPage = new HakimPage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
    Actions actions = new Actions(Driver.getDriver());
    @Test
    public void test01() throws InterruptedException, IOException {

        extentTest = extentReports.createTest("TC_08 - Bed Managers Availability Toggle Test");

        // =========================================================
        // PRE-CONDITION: LOGIN VE BED MANAGERS SAYFASINA GİTME
        // =========================================================

        //1-) Anasayfaya git.
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        //2-) Sign In butonuna bas.
        hakimPage.homePageSignInButton.click();

        //3-) Email ve password gir
        wait.until(ExpectedConditions.visibilityOf(hakimPage.loginEmailBox))
                .sendKeys("admin.abdul.hakim.kazanci@loyalfriendcare.com");
        hakimPage.loginPasswordBox.sendKeys("LFCare.0201");

        //4-) Sign In butonuna tıkla.
        hakimPage.loginSignInButton.click();


        // =========================================================
        // STEPS:
        // =========================================================

        // 1-) ⚙ AYARLAR SİMGESİNE TIKLA
        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.SettingsButton)).click();

        // 2-) SOLDAN AÇILAN SİDEBAR MENÜYÜ, SOLA HOVER İLE AÇ
        actions = new Actions(Driver.getDriver());
        wait.until(ExpectedConditions.visibilityOf(hakimPage.bedManagersParent));
        actions.moveToElement(hakimPage.bedManagersParent).perform();

        //3-) BED MANAGERS MENÜSÜNE TIKLA
        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.bedManagersParent)).click();

        //4-) BED MANAGERS ALT MENÜSÜNE TIKLA
        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.subBedManagers)).click();

        // Dashboard yüklenene kadar bekle
        wait.until(ExpectedConditions.urlContains("/Dashboard/Posts"));
        Thread.sleep(1500);

        // =========================================================
        // 1. YATAK İÇİN TEST - 4 FOTOĞRAF
        // =========================================================
        extentTest.info("═══════════════════════════════════════");
        extentTest.info("1. YATAK TESTİ BAŞLADI");
        extentTest.info("═══════════════════════════════════════");


        // FOTOĞRAF 1: Dashboard'da 1. yatağın durumu (Edit Butonuna Tıklamadan Öncesi)
        // Önce Dashboard sayfasında fotoğraf çek.
        String resim1 = ReusableMethods.raporaResimEkle("1-)Dashboard_FirstBed_BeforeEdit");
        extentTest.info("FOTOĞRAF 1/8: Dashboard Sayfası - 1.Yatak: AVAILIBLITY DURUM (Edit Öncesi)")
                .addScreenCaptureFromPath(resim1);



        // FOTOĞRAF 2: Birinci yatağa ait Edit butonuna bas ve edit sayfasında, önce Availiblity toggle butonununu mevcut halinin fotoğrafını çek.
        WebElement firstEditButton = Driver.getDriver()
                .findElement(By.xpath("(//a[contains(@class,'fa-edit') and .//span[text()='Edit']])[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(firstEditButton)).click(); //Edit butonuna bas.

        Thread.sleep(1500);// Edit sayfası yüklenene kadar bekle.


        WebElement firstAvailabilityToggle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("span.switchery.switchery-default"))); // Toggle'ı bul

        // Toggle'ın mevcut durumunu kontrol et (yeşil = açık)
        boolean isFirstOn = firstAvailabilityToggle.getAttribute("style").contains("rgb(16, 207, 189)");

        // Toggle'ın mevcut halinin fotoğrafını çek.
        String resim2 = ReusableMethods.raporaResimEkle("2-)EditPage_FirstBed_Toggle_Before");
        extentTest.info("FOTOĞRAF 2/8: Edit Sayfası - 1.Yatak: Toggle Butonun Mevcut Durumu")
                .info("Toggle Durumu: " + (isFirstOn ? "AÇIK (Yeşil) DURUMDA" : "KAPALI (Gri) DURUMDA"))
                .addScreenCaptureFromPath(resim2);



        // FOTOĞRAF 3: Edit sayfası - Toggle değiştirilmiş hali
        // Toogle'a tıkla ve butonun durumunu değiştir, sonra biraz bekle.
        firstAvailabilityToggle.click();
        Thread.sleep(1500);

        // Bu değişikliğin fotoğrafını çek.
        String resim3 = ReusableMethods.raporaResimEkle("3-)EditPage_FirstBed_Toggle_After");
        extentTest.info("FOTOĞRAF 3/8: Edit Sayfası - 1.Yatak: Toggle Butonun Durumu Değiştirildi")
                .info("Toggle Durumu: " + (isFirstOn ? "KAPALI yapıldı" : "AÇIK yapıldı"))
                .addScreenCaptureFromPath(resim3);

        // FOTOĞRAF 4: Dashboard'da 1. yatağın edit sonrası durumu
        // Save butonuna tıkla.
        actions.moveToElement(hakimPage.bedManagersSaveButton).click().perform();
        wait.until(ExpectedConditions.urlContains("/Dashboard/Posts"));
        Thread.sleep(1500);

        // Dashboard sayfasının fotoğrafını çek.
        String resim4 = ReusableMethods.raporaResimEkle("4-)Dashboard_FirstBed_AfterEdit");
        extentTest.info("FOTOĞRAF 4/8: Dashboard Sayfası - 1.Yatak: AVAILIBLITY DURUM (Edit Sonrası)")
                .info("Toggle değişikliği kaydedildi")
                .addScreenCaptureFromPath(resim4);

        extentTest.pass("1. Yatak: Toggle değişikliği tamamlandı.");

        extentTest.info("═══════════════════════════════════════");
        extentTest.info("1. YATAK TESTİ TAMAMLANDI");
        extentTest.info("═══════════════════════════════════════");

        // =========================================================
        // 2. YATAK İÇİN TEST - 4 FOTOĞRAF
        // =========================================================
        extentTest.info("");
        extentTest.info("═══════════════════════════════════════");
        extentTest.info("2. YATAK TESTİ BAŞLADI");
        extentTest.info("═══════════════════════════════════════");


        // FOTOĞRAF 5: Dashboard'da 2. yatağın durumu (Edit Butonuna Tıklamadan Öncesi)
        // Önce Dashboard sayfasında fotoğraf çek.
        String resim5 = ReusableMethods.raporaResimEkle("5-)Dashboard_SecondBed_BeforeEdit");
        extentTest.info("FOTOĞRAF 5/8: Dashboard Sayfası - 2.Yatak: AVAILIBLITY DURUM (Edit Öncesi)")
                .addScreenCaptureFromPath(resim5);

        // FOTOĞRAF 6: İkinci yatağa ait Edit butonuna bas ve edit sayfasında, önce Availiblity toggle butonununu mevcut halinin fotoğrafını çek.
        WebElement secondEditButton = Driver.getDriver()
                .findElement(By.xpath("(//a[contains(@class,'fa-edit') and .//span[text()='Edit']])[2]"));
        wait.until(ExpectedConditions.elementToBeClickable(secondEditButton)).click();
        Thread.sleep(1500);

        WebElement secondAvailabilityToggle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("span.switchery.switchery-default")));    // Toggle'ı bul

        boolean isSecondOn = secondAvailabilityToggle.getAttribute("style").contains("rgb(16, 207, 189)");

        // Toggle'ın mevcut halinin fotoğrafını çek.
        String resim6 = ReusableMethods.raporaResimEkle("6-)EditPage_SecondBed_Toggle_Before");
        extentTest.info("FOTOĞRAF 6/8: Edit Sayfası - 2.Yatak: Toggle Butonun Mevcut Durumu")
                .info("Toggle Durumu: " + (isSecondOn ? "AÇIK (Yeşil) DURUMDA" : "KAPALI (Gri) DURUMDA"))
                .addScreenCaptureFromPath(resim6);


        // FOTOĞRAF 7: Edit sayfası - Toggle değiştirilmiş hali
        // Toogle'a tıkla ve butonun durumunu değiştir, sonra biraz bekle.
        secondAvailabilityToggle.click();
        Thread.sleep(1500);

        // Bu değişikliğin fotoğrafını çek.
        String resim7 = ReusableMethods.raporaResimEkle("7-)EditPage_SecondBed_Toggle_After");
        extentTest.info("FOTOĞRAF 7/8: Edit Sayfası - 2.Yatak: Toggle Butonun Durumu Değiştirildi")
                .info("Toggle Durumu: " + (isSecondOn ? "KAPALI yapıldı" : "AÇIK yapıldı"))
                .addScreenCaptureFromPath(resim7);


        // FOTOĞRAF 8: Dashboard'da ikinci yatağın edit sonrası durumu
        // Save butonuna tıkla.
        actions.moveToElement(hakimPage.bedManagersSaveButton).click().perform();
        wait.until(ExpectedConditions.urlContains("/Dashboard/Posts"));
        Thread.sleep(1500);

        // Dashboard sayfasının fotoğrafını çek.
        String resim8 = ReusableMethods.raporaResimEkle("8-)Dashboard_SecondBed_AfterEdit");
        extentTest.info("FOTOĞRAF 8/8: Dashboard Sayfası: - 2.Yatak: AVAILIBLITY DURUM (Edit Sonrası)")
                .info("Toggle değişikliği kaydedildi")
                .addScreenCaptureFromPath(resim8);

        extentTest.pass("2. Yatak: Toggle değişikliği tamamlandı!");

        extentTest.info("═══════════════════════════════════════");
        extentTest.info("2. YATAK TESTİ TAMAMLANDI");
        extentTest.info("═══════════════════════════════════════");

        // =========================================================
        // TEST ÖZETİ
        // =========================================================
        extentTest.info("");
        extentTest.info("TOPLAM 8 FOTOĞRAF ÇEKİLDİ");
        extentTest.info("1. Yatak: 4 fotoğraf");
        extentTest.info("2. Yatak: 4 fotoğraf");

    }
}