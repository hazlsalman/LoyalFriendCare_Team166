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


public class TC_10_FranceLanguageTest extends TestBaseRapor {

    HakimPage hakimPage = new HakimPage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
    Actions actions = new Actions(Driver.getDriver());
    JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

    @Test
    public void test01() throws InterruptedException, IOException {

        extentTest = extentReports.createTest("TC_10 - France Language Button Test");

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

        // 2-) France Languange butonunun yanındaki + işaretine tıkla ve paneli aç, panel açıldıktan hemen sonra ekran görüntüsü al.
        By collapseALocator = By.cssSelector("a[data-toggle='collapse'][href='#collapseTwo']");
        WebElement franceLanguageCollapse = wait.until(ExpectedConditions.elementToBeClickable(collapseALocator));
        franceLanguageCollapse.click();

        ReusableMethods.bekle(1);

        // Ekran görüntüsü al.
        String resim1 = ReusableMethods.raporaResimEkle("I-)FranceLanguage_Panel_Opened");
        extentTest.info("1-)France Language panel açıldı ve panelin boş halinin ekran görüntüsü alındı.").addScreenCaptureFromPath(resim1);

        ReusableMethods.bekle(1);

        // 2-) Tıtle ve content alanlarına fransızca metin girilip girilmediğine test et ve ekran görüntüsü al.

        WebElement titleInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Title_fr")));
        WebElement contentInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("body_fr")));

        Assert.assertTrue(titleInput.isDisplayed(), "Fransızca Title alanı görünmüyor!");
        Assert.assertTrue(contentInput.isDisplayed(), "Fransızca Content alanı görünmüyor!");

        // Önce varsa eski metinleri temizle
        titleInput.clear();
        contentInput.clear();

        // Yeni metinleri gir
        titleInput.sendKeys("Test French Title");
        contentInput.sendKeys("Test French Content");

        ReusableMethods.bekle(1);

        String resim2 = ReusableMethods.raporaResimEkle("II-)FranceLanguage_Title_Content_Filled");
        extentTest.info("2-)Fransızca Title ve Content alanları dolduruldu ve bu haliyle ekran görüntüsü alındı.")
                .addScreenCaptureFromPath(resim2);


        // Son girilen metinleri de temizle.
        titleInput.clear();
        contentInput.clear();

        // 3-) SAVE VE SUCCESS MESSAGE
        ReusableMethods.bekle(1);
        actions.moveToElement(hakimPage.bedManagersSaveButton).click().perform();


        // 4-) DASHBOARD SAYFASINA DÖNÜlDÜĞÜNÜ TETS ET.
        wait.until(ExpectedConditions.urlContains("/Dashboard/Posts"));
        wait.until(ExpectedConditions.visibilityOf(hakimPage.successMessage));
        Assert.assertTrue(hakimPage.successMessage.isDisplayed(), "Success mesajı görünmüyor!");

        ReusableMethods.bekle(1);
        String resim3 = ReusableMethods.raporaResimEkle("III-) FranceLanguage_Save_Success");
        extentTest.pass("3-)France Language Title & Content alanların metin girildi ve save butonuna basıldı. " +
                        "Butona basıldıktan sonra dönülen sayfada, sağ üstteki çıkan 'başarılı kayıt' mesajının ekran görüntüsü alındı.")
                .addScreenCaptureFromPath(resim3);

        // =========================================================
        // TEST ÖZETİ
        // =========================================================
        extentTest.info("");
        extentTest.info("TOPLAM 3 FOTOĞRAF ÇEKİLDİ.");

    }
}