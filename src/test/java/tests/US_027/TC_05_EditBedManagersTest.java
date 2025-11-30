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

public class TC_05_EditBedManagersTest {

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


        // --------------------------
        // ⚙ AYARLAR SİMGESİNE TIKLA
        // --------------------------
        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.SettingsButton)).click();


        // --------------------------
        // SOLDAN AÇILAN SİDEBAR MENÜYÜ, SOLLA HOVER İLE AÇ
        // --------------------------
        Actions actions = new Actions(Driver.getDriver());
        wait.until(ExpectedConditions.visibilityOf(hakimPage.bedManagersParent));
        actions.moveToElement(hakimPage.bedManagersParent).perform();


        // --------------------------
        // BED MANAGERS MENÜSÜNE TIKLA
        // --------------------------
        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.bedManagersParent)).click();


        // --------------------------
        // BED MANAGERS ALT MENÜSÜNE TIKLA
        // --------------------------
        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.subBedManagers)).click();


        // --------------------------
        // EDIT BUTONUNA TIKLA
        // --------------------------

        WebElement firstEditButton = Driver.getDriver()
                .findElement(By.xpath("//a[contains(@class,'fa-edit') and span[text()='Edit']]"));

        wait.until(ExpectedConditions.elementToBeClickable(firstEditButton)).click();

        // --------------------------
        // YATAK BİLGİLERİNİ GÜNCELLE
        // --------------------------
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Title_en")));
        title.clear();
        title.sendKeys("Mırr Haven");

        WebElement content = Driver.getDriver().findElement(By.xpath("//div[@class='note-editable']"));
        content.clear();
        content.sendKeys("Clean, safe, and well-managed beds for hospitalized pets.");

        // Dropdownlar: Departments, Created Doctor, Medicines (sadece seçim)
        WebElement departmentDropdown = Driver.getDriver().findElement(By.xpath("//span[@class='select2-selection__rendered']"));
        actions.moveToElement(departmentDropdown).click().perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Wellness']"))).click();

        WebElement doctorDropdown = Driver.getDriver().findElement(By.xpath("(//span[@class='select2-selection__rendered'])[2]"));
        actions.moveToElement(doctorDropdown).click().perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Dr. Marcus Rodriguez']"))).click();

        WebElement medicinesDropdown = Driver.getDriver().findElement(By.xpath("(//span[@class='select2-selection__rendered'])[3]"));
        actions.moveToElement(medicinesDropdown).click().perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'Baytril')]"))).click();

        // BED PRİCE ALANINA MİKTAR GİR.
        WebElement bedPrice = Driver.getDriver().findElement(By.name("Downloud"));
        bedPrice.clear();
        bedPrice.sendKeys("333");

        // --------------------------
        // SAVE BUTONUNA TIKLA
        // --------------------------
        actions = new Actions(Driver.getDriver());
        actions.moveToElement(hakimPage.bedManagersSaveButton).click().perform();

        // --------------------------
        // DASHBOARD SAYFASINA DÖNÜŞ VE SUCCESS MESAJI
        // --------------------------
        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Tracks Updated successfully.']")));
        Assert.assertTrue(successMsg.isDisplayed(), "Success mesajı görünmüyor!");

        // Dashboard URL kontrol
        wait.until(driver -> driver.getCurrentUrl().equals("https://qa.loyalfriendcare.com/en/Dashboard/Posts"));

        Driver.quitDriver();
    }
}