package tests.US_034;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ArdaPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class TC001_AccessCreateMedicinesPage {

    ArdaPage ardaPage = new ArdaPage();

    @Test
    public void accessCreateMedicinesPageTest() {

        // 1) Login sayfasına git
        Driver.getDriver().get("https://qa.loyalfriendcare.com/en/login");
        ReusableMethods.bekle(1);

        // 2) Admin giriş bilgilerini gir
        ardaPage.emailBox.sendKeys(ConfigReader.getProperty("adminGecerliMail"));
        ardaPage.passwordBox.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));
        ardaPage.loginSubmitButton.click();
        ReusableMethods.bekle(2);

        // 3) Admin Dashboard’a geç
        ardaPage.headerUserName.click();
        ReusableMethods.bekle(2);

        // 4) Sol sidebar’ı hover ile aç
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(ardaPage.sidebarMenu).perform();   // <-- sidebar’ın tamamı
        ReusableMethods.bekle(1);

        // 5) Sidebar’dan Medicines’e tıkla (alt menüyü açsın)
        ardaPage.sidebarMedicines.click();
        ReusableMethods.bekle(1);

        // 6) Alt menüden Create Medicines’e tıkla
        ardaPage.createMedicinesLink.click();
        ReusableMethods.bekle(2);

        // 7) URL kontrolü → Create Medicines sayfasına gidildi mi?
        String currentUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("Dashboard/Instagrams/create"),
                "HATA: Create Medicines sayfasına gidilmedi!");

        Driver.quitDriver();
    }
}
