// src/test/java/tests/US_006/TC_03_FollowUs.java
package tests.US_006;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EbruPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class TC_03_FollowUs extends TestBaseRapor {

    @Test
    public void followUsIconlarininGorunurlukVeCalismaTesti() {

        extentTest = extentReports.createTest(
                "US_006 - TC_03 Follow Us ikonlarının görünürlüğü ve tıklanabilirliği",
                "Footer’daki Follow Us başlığı altındaki sosyal medya ikonlarının görünür, tıklanabilir " +
                        "ve ilgili sayfayı yeni sekmede açtığı doğrulanır."
        );

        String url = ConfigReader.getProperty("url");
        WebDriver driver = Driver.getDriver();
        driver.get(url);
        extentTest.info("Kullanıcı siteye gider: " + url);

        // Footer’a scroll
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        ReusableMethods.bekle(2);
        extentTest.info("Kullanıcı sayfanın en altına scroll eder.");

        EbruPage ebruPage = new EbruPage();

        // Footer alanı görünür olmalı
        Assert.assertTrue(ebruPage.footerArea.isDisplayed(), "Footer alanı görünmüyor.");
        extentTest.pass("Footer alanı görünür.");

        // Follow Us başlığı görünür olmalı
        Assert.assertTrue(ebruPage.followUsBasligi.isDisplayed(), "\"Follow Us\" başlığı görünmüyor.");
        extentTest.pass("\"Follow Us\" başlığının göründüğü doğrulandı.");

        // İkon listesi → 5 adet olmalı
        List<String> expectedDomains = Arrays.asList(
                "facebook.com",
                "twitter.com",
                "youtube.com",
                "pinterest.com",
                "instagram.com"
        );

        Assert.assertEquals(ebruPage.followUsIconListesi.size(), expectedDomains.size(),
                "Follow Us altında beklenen sayıda sosyal medya ikonu yok.");
        extentTest.pass("Follow Us altında 5 adet sosyal medya ikonu bulundu.");

        // Her ikon görünür ve tıklanabilir mi
        ebruPage.followUsIconListesi.forEach(icon -> {
            Assert.assertTrue(icon.isDisplayed(), "Bir sosyal medya ikonu görünmüyor.");
            Assert.assertTrue(icon.isEnabled(), "Bir sosyal medya ikonu tıklanabilir değil.");
        });
        extentTest.pass("Tüm sosyal medya ikonlarının görünür ve tıklanabilir olduğu doğrulandı.");

        // Her ikonun href’inde doğru domain var mı
        List<String> actualDomains = new ArrayList<>();
        ebruPage.followUsIconListesi.forEach(icon -> {
            String href = icon.getAttribute("href");
            actualDomains.add(href);
        });

        for (String expectedDomain : expectedDomains) {
            boolean domainVar = actualDomains.stream().anyMatch(href -> href.contains(expectedDomain));
            Assert.assertTrue(domainVar, expectedDomain + " içeren bir link bulunamadı.");
        }
        extentTest.pass("Her bir sosyal medya için doğru domain’i içeren link bulundu.");

        // İkon tıklanınca yeni sekmede açılıyor mu?
        String anaWindow = driver.getWindowHandle();

        ebruPage.facebookIcon.click();
        ReusableMethods.bekle(2);

        Set<String> tumWindows = driver.getWindowHandles();
        Assert.assertTrue(tumWindows.size() > 1, "Yeni sekme açılmadı.");

        // Yeni sekmeye geç
        for (String wh : tumWindows) {
            if (!wh.equals(anaWindow)) {
                driver.switchTo().window(wh);
                break;
            }
        }

        String acilanUrl = driver.getCurrentUrl();
        Assert.assertTrue(acilanUrl.contains("facebook.com"),
                "Açılan sayfa facebook.com içermiyor. URL: " + acilanUrl);
        extentTest.pass("Facebook ikonu tıklandığında ilgili sosyal medya sayfası yeni sekmede açılıyor.");



    }
}
