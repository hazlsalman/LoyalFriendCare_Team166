package tests.US_027;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HakimPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;

public class TC_04_BedManagersMenuAndSearchTest {

    HakimPage hakimPage = new HakimPage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

    @Test
    public void test01() throws InterruptedException {


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

        // 2-) SOLDAN AÇILAN SİDEBAR MENÜYÜ, SOLLA HOVER İLE AÇ

        Actions actions = new Actions(Driver.getDriver());
        wait.until(ExpectedConditions.visibilityOf(hakimPage.bedManagersParent));
        actions.moveToElement(hakimPage.bedManagersParent).perform();

        // 3-) BED MANAGERS MENÜSÜNE TIKLA

        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.bedManagersParent)).click();

        // 4-) ALT MENÜLERİN AÇILDIĞINI DOĞRULA

        wait.until(ExpectedConditions.visibilityOf(hakimPage.subBedManagers));
        wait.until(ExpectedConditions.visibilityOf(hakimPage.createBedManagers));

        Assert.assertTrue(hakimPage.subBedManagers.isDisplayed(), "Sub Bed Managers menüsü görünmüyor!");
        Assert.assertTrue(hakimPage.createBedManagers.isDisplayed(), "Create Bed Managers menüsü görünmüyor!");

        // 5-) BED MANAGERS ALT MENÜSÜNE TIKLA
        hakimPage.subBedManagers.click();

        // 6-) SEARCH KUTUSUNU TEST ET
        wait.until(ExpectedConditions.visibilityOf(hakimPage.SearchBox));
        hakimPage.SearchBox.clear();
        hakimPage.SearchBox.sendKeys("Cat Grooming Room");

        Assert.assertTrue(hakimPage.SearchBox.isDisplayed(),
                "Search kutusu görüntülenemedi veya etkileşimli değil.");

        Driver.quitDriver();
    }
}
