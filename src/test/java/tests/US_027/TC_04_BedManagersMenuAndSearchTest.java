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

        // --------------------------
        // PRE-CONDITION : LOGIN
        // --------------------------
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        // 1-) Login sayfasına geçiş
        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.homePageSignInButton)).click();

        // 2-) Email ve password gir
        wait.until(ExpectedConditions.visibilityOf(hakimPage.loginEmailBox))
                .sendKeys("admin.abdul.hakim.kazanci@loyalfriendcare.com");
        hakimPage.loginPasswordBox.sendKeys("LFCare.0201");

        // 3-) Sign In butonuna tıkla ve login sonrası anasayfaya yönlendirilmeyi bekle.
        hakimPage.loginSignInButton.click();
        wait.until(driver -> driver.getCurrentUrl().equals("https://qa.loyalfriendcare.com/en"));

        // --------------------------
        //  ⚙ AYARLAR SİMGESİNE TIKLA
        // --------------------------
        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.SettingsButton)).click();

        // --------------------------
        // SOL MENÜYÜ AÇMAK İÇİN HOVER
        // --------------------------
        Actions actions = new Actions(Driver.getDriver());

        // Sidebar menüyü sola hover ile aç
        wait.until(ExpectedConditions.visibilityOf(hakimPage.bedManagersParent));
        actions.moveToElement(hakimPage.bedManagersParent).perform();


        // --------------------------
        // BED MANAGERS MENÜSÜNE TIKLA
        // --------------------------
        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.bedManagersParent)).click();

        // --------------------------
        // ALT MENÜLERİN AÇILDIĞINI DOĞRULA
        // --------------------------
        wait.until(ExpectedConditions.visibilityOf(hakimPage.subBedManagers));
        wait.until(ExpectedConditions.visibilityOf(hakimPage.createBedManagers));

        Assert.assertTrue(hakimPage.subBedManagers.isDisplayed(), "Sub Bed Managers menüsü görünmüyor!");
        Assert.assertTrue(hakimPage.createBedManagers.isDisplayed(), "Create Bed Managers menüsü görünmüyor!");

        // --------------------------
        // BED MANAGERS ALT MENÜSÜNE TIKLA
        // --------------------------
        hakimPage.subBedManagers.click();

        // --------------------------
        // SEARCH KUTUSUNU TEST ET
        // --------------------------
        wait.until(ExpectedConditions.visibilityOf(hakimPage.SearchBox));
        hakimPage.SearchBox.clear();
        hakimPage.SearchBox.sendKeys("Cat Grooming Room");

        Assert.assertTrue(hakimPage.SearchBox.isDisplayed(),
                "Search kutusu görüntülenemedi veya etkileşimli değil.");

        Driver.quitDriver();
    }
}
