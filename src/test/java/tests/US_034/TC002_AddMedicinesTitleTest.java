package tests.US_034;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ArdaPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class TC002_AddMedicinesTitleTest {

    ArdaPage ardaPage = new ArdaPage();

    @Test
    public void verifyAddMedicinesTitleTest() {

        // 1) Login sayfasına git
        Driver.getDriver().get("https://qa.loyalfriendcare.com/en/login");
        ReusableMethods.bekle(1);

        // 2) Admin giriş bilgilerini gir
        ardaPage.emailBox.sendKeys(ConfigReader.getProperty("adminGecerliMail"));
        ardaPage.passwordBox.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));
        ardaPage.loginSubmitButton.click();
        ReusableMethods.bekle(2);

        // 3) Dashboard’a geç
        ardaPage.headerUserName.click();
        ReusableMethods.bekle(2);

        // 4) Sidebar’ı hover ile aç (TC001 ile aynı)
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(ardaPage.sidebarMenu).perform();
        ReusableMethods.bekle(1);

        // 5) Sidebar → Medicines tıkla
        ardaPage.sidebarMedicines.click();
        ReusableMethods.bekle(1);

        // 6) Create Medicines alt menüye tıkla
        ardaPage.createMedicinesLink.click();
        ReusableMethods.bekle(2);

        // 7) Add Medicines başlığı görünür mü?
        Assert.assertTrue(ardaPage.addMedicinesPageTitle.isDisplayed(),
                "HATA: Add Medicines başlığı görünmüyor!");

        // 8) Başlık metni kontrolü
        String expectedTitle = "Add Medicines";
        String actualTitle = ardaPage.addMedicinesPageTitle.getText().trim();

        Assert.assertEquals(actualTitle, expectedTitle,
                "HATA: Başlık metni Add Medicines değil!");

        Driver.quitDriver();
    }
}
