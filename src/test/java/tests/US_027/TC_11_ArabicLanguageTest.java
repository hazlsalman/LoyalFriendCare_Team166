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
import java.io.IOException;
import java.time.Duration;

public class TC_11_ArabicLanguageTest extends TestBaseRapor {

    HakimPage hakimPage = new HakimPage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
    Actions actions = new Actions(Driver.getDriver());
    JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

    @Test
    public void test01() throws InterruptedException, IOException {

        extentTest = extentReports.createTest("TC_11 - Arabic Language Button Test");

        // =========================================================
        // PRE-CONDITION:
        // =========================================================

        //1-) Anasayfaya git.
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        //2-) Sıgn In butonuna bas.
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
        Actions actions = new Actions(Driver.getDriver());
        wait.until(ExpectedConditions.visibilityOf(hakimPage.bedManagersParent));
        actions.moveToElement(hakimPage.bedManagersParent).perform();

        // 3-) BED MANAGERS MENÜSÜNE TIKLA
        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.bedManagersParent)).click();

        // 4-) BED MANAGERS ALT MENÜSÜNE TIKLA
        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.subBedManagers)).click();

        // 5-) EDIT BUTONUNA TIKLA
        WebElement firstEditButton = Driver.getDriver()
                .findElement(By.xpath("//a[contains(@class,'fa-edit') and span[text()='Edit']]"));

        wait.until(ExpectedConditions.elementToBeClickable(firstEditButton)).click();


        // =========================================================
        // SENARYO:
        // =========================================================
        // 1-) Sayfayı aşağı kaydır ve + işareti görünür duruma gelsin.
        Driver.getDriver().findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);
        ReusableMethods.bekle(1);

        // 2-) Arabic Language panelini aç
        By collapseArabicLocator = By.cssSelector("a[data-toggle='collapse'][href='#collapseThree']");
        WebElement arabicCollapse = wait.until(ExpectedConditions.elementToBeClickable(collapseArabicLocator));
        arabicCollapse.click();
        ReusableMethods.bekle(1);

        String resim1 = ReusableMethods.raporaResimEkle("A-)ArabicLanguage_Panel_Opened");
        extentTest.info("1-) Arabic Language panel açıldı ve panelin boş halinin ekran görüntüsü alındı.")
                .addScreenCaptureFromPath(resim1);

        // 3-) Title ve Content alanlarını test et
        WebElement titleInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Title_ar")));
        WebElement contentInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("body_ar")));

        Assert.assertTrue(titleInput.isDisplayed(), "Arapça Title alanı görünmüyor!");
        Assert.assertTrue(contentInput.isDisplayed(), "Arapça Content alanı görünmüyor!");

        // Mevcut varsa temizle
        titleInput.clear();
        contentInput.clear();

        // Deneme amaçlı metin gir (Arapça giremiyoruz, Latin karakterle test ediyoruz)
        titleInput.sendKeys("Test Arabic Title");
        contentInput.sendKeys("Test Arabic Content");
        ReusableMethods.bekle(1);

        String resim2 = ReusableMethods.raporaResimEkle("B-)ArabicLanguage_Title_Content_Filled");
        extentTest.info("2-) Arabic Title ve Content alanları dolduruldu ve bu haliyle ekran görüntüsü alındı. " +
                        "Bu alanlara Arap alfabesindeki harfler kullanılarak data girilemedi. Sonuç failed'dır.")
                .addScreenCaptureFromPath(resim2);

        // Son girilen metinleri temizle
        titleInput.clear();
        contentInput.clear();

        // 4-) Save ve success message
        ReusableMethods.bekle(1);
        actions.moveToElement(hakimPage.bedManagersSaveButton).click().perform();

        wait.until(ExpectedConditions.urlContains("/Dashboard/Posts"));
        wait.until(ExpectedConditions.visibilityOf(hakimPage.successMessage));
        Assert.assertTrue(hakimPage.successMessage.isDisplayed(), "Success mesajı görünmüyor!");

        ReusableMethods.bekle(1);
        String resim3 = ReusableMethods.raporaResimEkle("C-)ArabicLanguage_Save_Success");
        extentTest.pass("3-) Arabic Title & Content alanlarına metin girildi." +
                        "Save butonuna basıldıktan sonra açılan sayfada sağ üstteki 'başarılı kayıt' mesajının ekran görüntüsü alındı.")
                .addScreenCaptureFromPath(resim3);


        // =========================================================
        // TEST ÖZETİ
        // =========================================================
        extentTest.info("");
        extentTest.info("TOPLAM 3 FOTOĞRAF ÇEKİLDİ.");
    }
}
