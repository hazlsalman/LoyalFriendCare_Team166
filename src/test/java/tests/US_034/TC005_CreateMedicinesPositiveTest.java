package tests.US_034;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ArdaPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class TC005_CreateMedicinesPositiveTest {

    ArdaPage ardaPage = new ArdaPage();

    @Test
    public void createMedicinesPositiveTest() {

        Actions actions = new Actions(Driver.getDriver());
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

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

        // Sayfanın en yukarısına çık  sidebar görünür olsun
        actions.sendKeys(Keys.HOME).perform();
        ReusableMethods.bekle(1);

        // Sidebar’ı görünür alana getir
        js.executeScript("arguments[0].scrollIntoView(true);", ardaPage.sidebarMenu);
        ReusableMethods.bekle(1);

        // Hover yap
        actions.moveToElement(ardaPage.sidebarMenu).perform();
        ReusableMethods.bekle(1);

        // 3) Sidebar - Medicines - Create Medicines
        ardaPage.sidebarMedicines.click();
        ReusableMethods.bekle(1);

        ardaPage.createMedicinesLink.click();
        ReusableMethods.bekle(2);

        // 4) Valid Title & Content
        String medicineName = "AutoMed_" + System.currentTimeMillis();
        String medicineContent = "Otomasyon açıklama metni.";

        ardaPage.createMedicineTitleInput.sendKeys(medicineName);
        ardaPage.createMedicineContentInput.sendKeys(medicineContent);

        // 5) Save
        ardaPage.createMedicineSaveButton.click();
        ReusableMethods.bekle(2);

        // 6) Medicines List sayfasına dön
        Assert.assertTrue(
                Driver.getDriver().getCurrentUrl().contains("/Dashboard/Instagrams"),
                "HATA: Save sonrası Medicines List sayfasında değil!"
        );

        //  AŞAĞI İNME (1. kez)
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        ReusableMethods.bekle(1);

        // NEXT’e tıkla
        if (ardaPage.nextButton.isDisplayed()) {
            ardaPage.nextButton.click();
            ReusableMethods.bekle(2);
        }

        // AŞAĞI İNME (2. kez)
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        ReusableMethods.bekle(2);

        // 7) Yeni eklenen ilacı bul
        boolean isFound = false;

        for (WebElement row : ardaPage.medicinesTableRows) {
            if (row.getText().contains(medicineName)) {
                isFound = true;
                break;
            }
        }

        Assert.assertTrue(isFound,
                "HATA: Yeni eklenen ilaç Medicines List'te görünmüyor!");

        Driver.quitDriver();
    }
}
