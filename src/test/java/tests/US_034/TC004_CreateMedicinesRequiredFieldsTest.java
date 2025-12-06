package tests.US_034;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ArdaPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class TC004_CreateMedicinesRequiredFieldsTest {

    ArdaPage ardaPage = new ArdaPage();

    @Test
    public void createMedicinesRequiredFieldsTest() {

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

        // 4) Title & Content boş bırak
        ardaPage.createMedicineTitleInput.clear();
        ardaPage.createMedicineContentInput.clear();
        ReusableMethods.bekle(1);

        // 5) Save tıkla
        ardaPage.createMedicineSaveButton.click();
        ReusableMethods.bekle(2);

        // 6) FORM SUBMIT OLMAMALI → URL aynı kalmalı
        String expectedUrl = "https://qa.loyalfriendcare.com/en/Dashboard/Instagrams/create";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        Assert.assertEquals(actualUrl, expectedUrl,
                "HATA: Boş alanlara rağmen Save tıklanınca form submit oldu, required çalışmadı!");

        // 7) Test bitti → driver kapat
        Driver.quitDriver();
    }
}
