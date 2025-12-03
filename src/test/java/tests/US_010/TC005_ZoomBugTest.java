package tests.US_010;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ArdaPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TC005_ZoomBugTest extends TestBaseRapor {

    ArdaPage ardaPage = new ArdaPage();

    @Test
    public void zoomBugTest() throws Exception {

        // Rapor başlat
        extentTest = extentReports.createTest("TC005_ZoomBugTest",
                "Zoom %200 yapıldığında NAVBAR kaybolma bug'ı kontrolü");

        // 1) Siteye git
        extentTest.info("Kullanıcı siteye gider");
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        ReusableMethods.bekle(1);

        // 2) Robot ile %200 zoom
        extentTest.info("Sayfa Robot Class ile %200 zoom yapılır (5 kez CTRL + '+')");
        Robot robot = new Robot();
        for (int i = 0; i < 5; i++) {
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ADD);
            robot.keyRelease(KeyEvent.VK_ADD);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(400);
        }
        ReusableMethods.bekle(2);

        // 3) NAVBAR (menü elemanları) görünürlüğünü kontrol et
        extentTest.info("Navbar menü elemanları kontrol edilir — BUG BEKLENİYOR");

        boolean navbarGorunurMu = true;

        try {
            for (var item : ardaPage.headerMenuItems) {
                if (!item.isDisplayed()) {
                    navbarGorunurMu = false;
                    break;
                }
            }
        } catch (Exception e) {
            navbarGorunurMu = false;
        }

        //  Sign In / Sign Up da checkk
        boolean signInVisible = false;
        boolean signUpVisible = false;

        try { signInVisible = ardaPage.signInButton.isDisplayed(); } catch (Exception ignored) {}
        try { signUpVisible = ardaPage.signUpButton.isDisplayed(); } catch (Exception ignored) {}

        boolean tumHeaderElemanlariGorunurMu = navbarGorunurMu && signInVisible && signUpVisible;

        //  Test failss
        extentTest.info("Navbar ve butonlar görünmedi → BU BİR BUG → Test FAIL olacak");

        Assert.assertTrue(tumHeaderElemanlariGorunurMu,
                "BUG TESPİT EDİLDİ: Zoom %200 yapıldığında NAVBAR (menü + sign in/up) kayboluyor!");


    }
}
