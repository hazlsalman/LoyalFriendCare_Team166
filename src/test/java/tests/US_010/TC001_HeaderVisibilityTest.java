package tests.US_010;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ArdaPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import static utilities.Driver.driver;

public class TC001_HeaderVisibilityTest {

    ArdaPage ardaPage = new ArdaPage();

    @Test
    public void headerVisibilityTest() {

        // 1) Siteye git
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        ReusableMethods.bekle(1);

        // 2) Header görünür mü?
        Assert.assertTrue(ardaPage.headerContainer.isDisplayed(), "Header görünmüyor!");

        // 3) Logo görünür ve tıklanabilir mi?
        Assert.assertTrue(ardaPage.logo.isDisplayed(), "Logo görünmüyor!");
        Assert.assertTrue(ardaPage.logo.isEnabled(), "Logo tıklanabilir değil!");

        // 4) Navbar itemları görünür mü?
        for (WebElement item : ardaPage.headerMenuItems) {
            Assert.assertTrue(item.isDisplayed(), "Menüde görünmeyen bir eleman var!");
        }

        // 5) Sign In görünür ve tıklanabilir mi?
        Assert.assertTrue(ardaPage.signInButton.isDisplayed(), "Sign In görünmüyor!");
        Assert.assertTrue(ardaPage.signInButton.isEnabled(), "Sign In tıklanabilir değil!");

        // 6) Sign Up görünür ve tıklanabilir mi?
        Assert.assertTrue(ardaPage.signUpButton.isDisplayed(), "Sign Up görünmüyor!");
        Assert.assertTrue(ardaPage.signUpButton.isEnabled(), "Sign Up tıklanabilir değil!");

        // 7) Test sonrası tarayıcıyı kapat
        driver.quit();
    }
}
