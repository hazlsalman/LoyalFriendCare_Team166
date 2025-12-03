package tests.US_033;

import org.testng.Assert;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import pages.ArdaPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class TC004_MedicinesSearchTest {

    ArdaPage ardaPage = new ArdaPage();

    @Test
    public void medicinesSearchTest() {

        // 1) Login sayfasına git
        Driver.getDriver().get("https://qa.loyalfriendcare.com/en/login");
        ReusableMethods.bekle(1);

        // 2) Admin giriş yapılır
        ardaPage.emailBox.sendKeys(ConfigReader.getProperty("adminGecerliMail"));
        ardaPage.passwordBox.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));
        ardaPage.loginSubmitButton.click();
        ReusableMethods.bekle(2);

        // 3) Admin profiline geçiş
        ardaPage.headerUserName.click();
        ReusableMethods.bekle(2);

        // 4) Sidebar açılır
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(ardaPage.sidebarMenu).perform();
        ReusableMethods.bekle(1);

        // 5) Medicines → Medicines List'e git
        ardaPage.sidebarMedicines.click();
        ReusableMethods.bekle(1);

        ardaPage.submenuMedicines.click();
        ReusableMethods.bekle(2);

        // 6) Arama kutusunun görüntülendiğini doğrula
        Assert.assertTrue(ardaPage.medicinesSearchBox.isDisplayed(),
                "HATA: Arama kutusu görüntülenemiyor!");

        // 7) Arama kutusuna mevcut bir ilaç ismi yazılır
        String searchKeyword = "Rimadyl";  // Tabloya göre mevcut
        ardaPage.medicinesSearchBox.sendKeys(searchKeyword);
        ReusableMethods.bekle(2);

        // 8) Arama sonrası tablo satırlarının geldiğini doğrula
        Assert.assertTrue(ardaPage.medicinesTableRows.size() > 0,
                "HATA: Arama sonucunda hiç satır görüntülenmedi!");

        // 9) İlk satırın aranan ilaçla ilgili olup olmadığı kontrol edilir
        String firstRowText = ardaPage.firstMedicineName.getText().toLowerCase();
        Assert.assertTrue(firstRowText.contains(searchKeyword.toLowerCase()),
                "HATA: Aranan ilaç filtrelenmiş sonuçlarda bulunamadı!");

        // 10) Tarayıcı kapatılır
        Driver.quitDriver();
    }
}
