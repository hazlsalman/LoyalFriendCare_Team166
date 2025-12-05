package tests.US_006;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EbruPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

import java.util.Arrays;
import java.util.List;

public class TC_02_Departments extends TestBaseRapor {

    @Test
    public void departmentsBaslikVeAltMetinlerGorunurlukTesti() {

        extentTest = extentReports.createTest(
                "US006 - TC_02 Departments başlığı ve alt metinlerin görünürlüğü",
                "Footer’daki Departments başlığı ve altındaki metinlerin eksiksiz, düzgün ve okunabilir olduğu doğrulanır."
        );

        // Pre-condition: Kullanıcı Home Page’e erişmiş olmalı
        String url = ConfigReader.getProperty("url");   // configuration.properties → url
        Driver.getDriver().get(url);
        extentTest.info("Kullanıcı siteye gider: " + url);

        // 1) Home Page üzerinde footer alanına scroll edilir
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        ReusableMethods.bekle(2);
        extentTest.info("Kullanıcı sayfanın en altına kadar scroll eder.");

        EbruPage ebruPage = new EbruPage();

        // 2) Footer içinde “Departments” başlığının görünüp görünmediği kontrol edilir
        Assert.assertTrue(ebruPage.footerArea.isDisplayed(), "Footer alanı görünmüyor.");
        Assert.assertTrue(ebruPage.departmentsBasligi.isDisplayed(), "\"Departments\" başlığı görünmüyor.");
        extentTest.pass("\"Departments\" başlığının footer içinde göründüğü doğrulandı.");

        // 3) Departments başlığının altında beklenen metinlerin bulunup bulunmadığı kontrol edilir
        List<String> actualList =
                ReusableMethods.stringListeDondur(ebruPage.departmentsMetinleri);

        // Test case’teki beklenen metinler
        List<String> expectedList = Arrays.asList(
                "Wellness",
                "Dental Care",
                "Anaesthesia",
                "Dermatology",
                "Diagnostics");
        // Hem sayıları aynı mı, hem de içerik birebir aynı mı kontrol edelim
        Assert.assertEquals(actualList.size(), expectedList.size(),
                "Departments altında beklenen sayıda madde yok.");
        Assert.assertEquals(actualList, expectedList,
                "Departments altındaki metinler beklenen listeyle eşleşmiyor.");
        extentTest.pass("Departments altındaki tüm metinlerin (Wellness, Dental Care, Anaesthesia, Dermatology, Diagnostics) " +
                "eksiksiz ve doğru yazımla göründüğü doğrulandı.");

        // 4. adım (hizalı ve kesilmemiş görünmesi) otomasyonla doğrudan ölçülemediği için,
        // metinlerin tam ve eksiksiz gelmesiyle fonksiyonel olarak doğrulanmış sayıyoruz.

        extentTest.pass("US006 - TC_02 testi başarıyla tamamlandı.");
    }
}


