package tests.US_033;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ArdaPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class TC002_MedicinesSubmenuVisibilityTest {

    ArdaPage ardaPage = new ArdaPage();

    @Test
    public void medicinesSubmenuVisibilityTest() {

        // 1) Login sayfasına git
        Driver.getDriver().get("https://qa.loyalfriendcare.com/en/login");
        ReusableMethods.bekle(1);

        // 2) Admin kullanıcı bilgilerini gir
        ardaPage.emailBox.sendKeys(ConfigReader.getProperty("adminGecerliMail"));
        ardaPage.passwordBox.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));
        ardaPage.loginSubmitButton.click();
        ReusableMethods.bekle(2);

        // 3) Admin paneline geç
        ardaPage.headerUserName.click();
        ReusableMethods.bekle(2);

        // 4) Sidebar hover ile aç
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(ardaPage.sidebarMenu).perform();
        ReusableMethods.bekle(1);

        // 5) Sidebar’dan Medicines’e tıklanır
        ardaPage.sidebarMedicines.click();
        ReusableMethods.bekle(1);

        // 6) Medicines alt menüleri görünür olmalı
        Assert.assertTrue(ardaPage.medicinesSubmenuList.size() > 0,
                "HATA: Medicines alt menüleri görünmüyor!");

        // 7) Alt menüdeki her item görünür mü?
        for (WebElement submenu : ardaPage.medicinesSubmenuList) {
            Assert.assertTrue(submenu.isDisplayed(),
                    "HATA: Medicines alt menüsünde görünmeyen bir item var!");
        }

        // 8) Test sonunda tarayıcıyı kapat
        Driver.quitDriver();
    }
}
