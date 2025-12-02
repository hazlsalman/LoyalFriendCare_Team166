package tests.US_031;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DogukanPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

import java.time.Duration;

public class TC_004 extends TestBaseRapor {
    @Test
    public void doctorsViewTest(){
        DogukanPage dogukanPage = new DogukanPage();
        extentTest = extentReports.createTest("Liste üzerinde doktora tıklayarak detay sayfa açma testi",
                "Admin listeden herhangi bir doktora tıklayarak sadece o doktorla ilgili sayfaya gidebilmeli");
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        extentTest.info("Admin, Loyal Friend Care anasayfaya gider");
        dogukanPage.signInButton.click();
        extentTest.info("Sign in butonuna tiklar");
        dogukanPage.loginPageEmailBox.sendKeys(ConfigReader.getProperty("adminGecerliMail"));
        extentTest.info("Gecerli mail adres bilgisini girer");
        dogukanPage.loginPagePasswordBox.sendKeys(ConfigReader.getProperty("adminGecerliPassword"));
        extentTest.info("Gecerli sifre bilgisini girer");
        dogukanPage.loginPageSignInButton.click();
        extentTest.info("Sign in butonuna tiklar");
        dogukanPage.profileSettingsButton.click();
        extentTest.info("Kendi kullanici adina ait butona tiklar");
        Actions actions = new Actions(Driver.getDriver());
        actions.moveByOffset(5, 200).perform();
        extentTest.info("Açılır menüyü görmek için fareyi sayfanın sol tarafına götürür ");
        dogukanPage.dropDownMenuDoctorsButton.click();
        extentTest.info("Acilan menude Doctors secenegine tiklar");
        dogukanPage.doctorsSubMenuButton.click();
        extentTest.info("Acilan alt menudeki seceneklerden Doctors secenegine tiklar");
        String ilkUrl = Driver.getDriver().getCurrentUrl();
        dogukanPage.doctorMartinexText.click();
        extentTest.info("Acilan sayfada ilk doktorun ismine tiklar");
        String sonUrl = Driver.getDriver().getCurrentUrl();

        Assert.assertNotEquals(sonUrl, ilkUrl,
                "Doktor ismine tıklayınca detay sayfasına gidilmeli, URL değişmedi."
        );
    }
}
