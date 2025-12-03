package tests.US_033;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ArdaPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class TC001_MedicinesSidebarVisibilityTest {

    ArdaPage ardaPage = new ArdaPage();

    @Test
    public void medicinesSidebarVisibilityTest() {

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

        // 4) Sidebar’ı hover ile aç
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(ardaPage.sidebarMenu).perform();
        ReusableMethods.bekle(1);

        // 5) Sidebar’da Medicines görünmeli
        Assert.assertTrue(ardaPage.sidebarMedicines.isDisplayed(),
                "HATA: Sidebar üzerinde 'Medicines' sekmesi görünmüyor!");

        Driver.quitDriver();
    }
}
