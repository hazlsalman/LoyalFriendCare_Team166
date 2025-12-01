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

public class TC_13_BedManagersDeleteTest {

    HakimPage hakimPage = new HakimPage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
    Actions actions = new Actions(Driver.getDriver());

    @Test
    public void test01_DeleteBed() throws InterruptedException {

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

        // 1-) ⚙ Ayarlar simgesine tıkla
        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.SettingsButton)).click();

        // 2-) Soldan açılan menüye hover
        wait.until(ExpectedConditions.visibilityOf(hakimPage.bedManagersParent));
        actions.moveToElement(hakimPage.bedManagersParent).perform();

        // 3-) Bed Managers menüsüne tıkla
        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.bedManagersParent)).click();

        // 4-) Bed Managers alt menüsüne tıkla
        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.subBedManagers)).click();

        // =========================================================
        // SENARYO:
        // =========================================================

        // 1-) DELETE BUTONUNA TIKLA
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button.btn.btn-danger.btn-cons.btn-animated")));
        deleteButton.click();

        // Silme sonrası mesajın görünürlüğünü kontrol et
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOf(hakimPage.deleteSuccessMessage));
        Assert.assertTrue(successMessage.isDisplayed(), "Silme mesajı görünür değil.");


        Driver.quitDriver();

    }
}
