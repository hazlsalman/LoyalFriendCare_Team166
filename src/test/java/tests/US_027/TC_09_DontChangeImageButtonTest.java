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

public class TC_09_DontChangeImageButtonTest {

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
            // SOLDAN AÇILAN SİDEBAR MENÜYÜ, SOLA HOVER İLE AÇ
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


            // =========================================================
            // "Don't Change Image" butonunun varlığını ve işlevselliğini kontrol et
            // =========================================================



            // 1-) Edit sayfası yüklenene kadar bekle
            Thread.sleep(1000);

            // 2-) "Don't Change Image" checkbox elementini al
            WebElement dontChangeImageCheckbox = Driver.getDriver().findElement(By.id("button"));

            // 3-) Checkbox görüntüleniyor mu kontrol et
            Assert.assertTrue(dontChangeImageCheckbox.isDisplayed(), "'Don't Change Image' checkbox görüntülenemedi!");

            // 4-) Checkbox'a tıkla (Don't Change Image)
            if (!dontChangeImageCheckbox.isSelected()) { // Eğer seçili değilse tıkla
                  dontChangeImageCheckbox.click();
            }
                  // 5-) Save butonuna tıkla
                  actions.moveToElement(hakimPage.bedManagersSaveButton).click().perform();

                  // 6-) Success mesajını kontrol et
                  wait.until(ExpectedConditions.visibilityOf(hakimPage.successMessage));
                  Assert.assertTrue(hakimPage.successMessage.isDisplayed(), "Success mesajı görünmüyor!");

                  // 7-) Dashboard sayfasına dönüldüğünü kontrol et
                  wait.until(ExpectedConditions.urlToBe("https://qa.loyalfriendcare.com/en/Dashboard/Posts"));


                  Driver.quitDriver();

      }}
