package tests.US_033;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ArdaPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

public class TC005_InvalidMedicineEditBugTest extends TestBaseRapor {

    ArdaPage ardaPage = new ArdaPage();

    @Test
    public void invalidMedicineEditBugTest() {

        extentTest = extentReports.createTest("TC005_InvalidMedicineEditBugTest",
                "Edit ekranında geçersiz veri kabul edilmemesi gerekirken kabul edilmesi bug’ının doğrulanması");

        // 1) Login
        extentTest.info("Login sayfasına gidiliyor.");
        Driver.getDriver().get("https://qa.loyalfriendcare.com/en/login");
        ReusableMethods.bekle(1);

        ardaPage.emailBox.sendKeys(ConfigReader.getProperty("adminGecerliMail"));
        ardaPage.passwordBox.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));
        ardaPage.loginSubmitButton.click();
        ReusableMethods.bekle(2);

        // 2) Admin Dashboard’a geç
        ardaPage.headerUserName.click();
        ReusableMethods.bekle(2);

        // 3) Sidebar → Medicines → Medicines List
        extentTest.info("Sidebar üzerinden Medicines → Medicines List açılıyor.");
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(ardaPage.sidebarMenu).perform();
        ReusableMethods.bekle(1);

        ardaPage.sidebarMedicines.click();
        ReusableMethods.bekle(1);

        ardaPage.submenuMedicines.click();
        ReusableMethods.bekle(2);

        // 4) İlk ilacın Edit butonuna tıkla
        extentTest.info("İlk ilacın Edit ekranı açılıyor.");
        ardaPage.firstMedicineEditButton.click();
        ReusableMethods.bekle(2);

        // 5) Geçersiz veri girişi
        extentTest.info("Geçersiz karakterler ('*<>%\"') form alanlarına giriliyor.");

        String invalidValue = "*<>%\"";

        ardaPage.editMedicineTitle.clear();
        ardaPage.editMedicineTitle.sendKeys(invalidValue);

        ardaPage.editMedicineContent.clear();
        ardaPage.editMedicineContent.sendKeys(invalidValue);

        ReusableMethods.bekle(1);

        // 6) Save butonuna bas
        extentTest.info("Save butonuna tıklanıyor.");
        ardaPage.saveMedicineButton.click();
        ReusableMethods.bekle(2);

        // 7) Behavior kontrol → sistem GEÇERSİZ veriyi reddetmeli (edit sayfasında kalmalı)
        boolean halaEditSayfasindaMi;

        try {
            halaEditSayfasindaMi = ardaPage.editMedicineTitle.isDisplayed();
        } catch (Exception e) {
            halaEditSayfasindaMi = false;  // edit ekranından çıkmış → BUG
        }


        Assert.assertTrue(halaEditSayfasindaMi,
                "BUG: Sistem geçersiz veriyi kabul etti, edit ekranından çıkıp kaydı güncelledi!");

        extentTest.fail("BUG doğrulandı: Sistem geçersiz karakterlere izin veriyor.");
    }
}
