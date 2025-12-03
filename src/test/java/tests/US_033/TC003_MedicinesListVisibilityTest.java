package tests.US_033;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ArdaPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import org.openqa.selenium.WebElement;

public class TC003_MedicinesListVisibilityTest {

    ArdaPage ardaPage = new ArdaPage();

    @Test
    public void medicinesListVisibilityTest() {

        // 1) Login
        Driver.getDriver().get("https://qa.loyalfriendcare.com/en/login");
        ReusableMethods.bekle(1);

        ardaPage.emailBox.sendKeys(ConfigReader.getProperty("adminGecerliMail"));
        ardaPage.passwordBox.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));
        ardaPage.loginSubmitButton.click();
        ReusableMethods.bekle(2);

        // 2) Admin dashboard’a geçiş
        ardaPage.headerUserName.click();
        ReusableMethods.bekle(2);

        // 3) Sidebar hover
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(ardaPage.sidebarMenu).perform();
        ReusableMethods.bekle(1);

        // 4) Sidebar → Medicines
        ardaPage.sidebarMedicines.click();
        ReusableMethods.bekle(1);

        // 5) Alt menü Medicines
        ardaPage.submenuMedicines.click();
        ReusableMethods.bekle(2);

        // 6) Tablonun görünür olması için scroll
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", ardaPage.medicinesTableArea);
        ReusableMethods.bekle(1);

        // 7) Satır sayısını kontrol et
        Assert.assertTrue(ardaPage.medicinesTableRows.size() > 0,
                "HATA: Medicines tablosunda hiç ilaç kaydı bulunmuyor!");

        // 8) Tarayıcı kapat
        Driver.quitDriver();
    }
}
