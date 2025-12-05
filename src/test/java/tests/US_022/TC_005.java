package tests.US_022;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MelahatnurPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class TC_005 {

    @BeforeMethod
    public void setUp() {
        Driver.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        Driver.quitDriver();
    }

    @Test
    public void dashboardAdminTC_005() {

        MelahatnurPage melahatnurPage = new MelahatnurPage();

        Driver.getDriver().get(ConfigReader.getProperty("url"));
        melahatnurPage.signinButonu.click();

        melahatnurPage.mailKutusu.sendKeys(ConfigReader.getProperty("adminGecerliMail"));
        melahatnurPage.passwordKutusu.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));
        melahatnurPage.girisButonu.click();

        melahatnurPage.adminPaneliButonu.click();
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(melahatnurPage.solMenu).perform();
        Assert.assertTrue(melahatnurPage.rolesElementi.isDisplayed());
    }

    @Test
    public void dashboardUserTC_005() {

        MelahatnurPage page = new MelahatnurPage();

        Driver.getDriver().get(ConfigReader.getProperty("url"));
        page.signinButonu.click();

        page.mailKutusu.sendKeys(ConfigReader.getProperty("userGecerliMail"));
        page.passwordKutusu.sendKeys(ConfigReader.getProperty("userGecerliPassword"));
        page.girisButonu.click();

        boolean adminVisible = Driver.getDriver()
                .findElements(By.xpath("//*[contains(text(),'admin.hazal.salman')]"))
                .size() > 0;

        Assert.assertFalse(adminVisible);
    }
}


