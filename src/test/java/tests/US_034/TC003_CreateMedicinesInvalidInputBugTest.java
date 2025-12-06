package tests.US_034;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import pages.ArdaPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

public class TC003_CreateMedicinesInvalidInputBugTest extends TestBaseRapor {

    ArdaPage ardaPage = new ArdaPage();

    @Test
    public void createMedicinesInvalidInputBugTest() {

        extentTest = extentReports.createTest("TC003_CreateMedicinesInvalidInputBugTest",
                "Geçersiz karakterlerle oluşturulan ilacın Edit ekranında 404 vermesi bug'ının doğrulanması");

        // 1) Login
        Driver.getDriver().get("https://qa.loyalfriendcare.com/en/login");
        ReusableMethods.bekle(1);

        ardaPage.emailBox.sendKeys(ConfigReader.getProperty("adminGecerliMail"));
        ardaPage.passwordBox.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));
        ardaPage.loginSubmitButton.click();
        ReusableMethods.bekle(2);

        // 2) Dashboard
        ardaPage.headerUserName.click();
        ReusableMethods.bekle(2);

        // 3) Sidebar → Medicines → Create Medicines
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(ardaPage.sidebarMenu).perform();
        ReusableMethods.bekle(1);

        ardaPage.sidebarMedicines.click();
        ReusableMethods.bekle(1);
        ardaPage.createMedicinesLink.click();
        ReusableMethods.bekle(2);

        extentTest.info("Create Medicines formu açıldı.");

        // 4) Geçersiz veri gir
        String invalidValue = "*<>%\"";
        ardaPage.createMedicineTitleInput.sendKeys(invalidValue);
        ardaPage.createMedicineContentInput.sendKeys(invalidValue);

        extentTest.info("Geçersiz karakterler Title ve Content alanlarına girildi.");

        ReusableMethods.bekle(1);

        // 5) Save Medicines
        ardaPage.createMedicineSaveButton.click();
        ReusableMethods.bekle(2);

        // 6) List sayfası → Next → Scroll → invalid row
        extentTest.info("List ekranına dönüldü. Geçersiz kayıt için Next sayfasına geçiliyor.");

        scrollToElement(ardaPage.nextButton);
        ReusableMethods.bekle(1);

        ardaPage.nextButton.click();
        ReusableMethods.bekle(2);

        scrollToElement(ardaPage.nextButton);
        ReusableMethods.bekle(1);

        scrollToElement(ardaPage.invalidMedicineRow);
        ReusableMethods.bekle(1);

        extentTest.info("Geçersiz karakterlerle oluşturulan kayıt bulundu. Edit butonuna tıklanıyor.");

        // 7) Edit’e tıkla
        ardaPage.invalidMedicineEditButton.click();
        ReusableMethods.bekle(2);

        // 8) 404 kontrolü (EN DOĞRU LOCATOR)
        boolean is404;

        try {
            is404 = ardaPage.error404Text.isDisplayed();
        } catch (Exception e) {
            is404 = false;
        }

        // Testin fail olması gerekiyor → 404 varsa FAIL
        Assert.assertFalse(is404,
                "BUG: Geçersiz veri ile oluşturulan kayıt Edit ekranında 404 hatası veriyor!");

        extentTest.fail("BUG doğrulandı: Geçersiz karakterlerle oluşturulan medicine edit ekranı 404 veriyor.");
    }


    // ******* LOCAL SCROLL METHOD *******
    // Takımdaki diğer testleri ETKİLEMEZ
    public void scrollToElement(WebElement element) {
        try {
            org.openqa.selenium.JavascriptExecutor js =
                    (org.openqa.selenium.JavascriptExecutor) Driver.getDriver();
            js.executeScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'center'});", element);
        } catch (Exception e) {
            System.out.println("scrollToElement çalışmadı → " + e.getMessage());
        }
    }
}
