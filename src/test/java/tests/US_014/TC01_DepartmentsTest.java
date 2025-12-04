package tests.US_014;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.YaprakPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.List;

public class TC01_DepartmentsTest extends YaprakPage {

    @BeforeClass
    public void setupSignIn() {
        //Pre-Condition Kullanıcı sisteme giriş yapmış olmalı
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        anasayfaSigninButonu.click();
        ReusableMethods.bekle(1);
        anasayfaEmailKutusu.sendKeys(ConfigReader.getProperty("userGecerliMail"));
        anasayfaPasswordKutusu.sendKeys(ConfigReader.getProperty("userGecerliPassword"));
        anasayfaSigninGirisButonu.click();
        ReusableMethods.bekle(1);
    }

    @Test
    public void tC01_departmentsMenuTest() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(anasayfaDepartmentsMenu.isDisplayed(),
                "tC01_Departments menüsü görünmüyor!");
        softAssert.assertTrue(anasayfaDepartmentsMenu.isEnabled(),
                "tC01_Departments menüsü tıklanabilir değil!");
        anasayfaDepartmentsMenu.click();
        ReusableMethods.bekle(1);
        softAssert.assertAll();
    }

    @Test
    public void tC02_departmanlarTiklanabilirMi() {
        SoftAssert softAssert = new SoftAssert();
        anasayfaDepartmentsMenu.click();
        ReusableMethods.bekle(1);
        int departmanSayisi = departmanListesi.size();
        for (int i = 0; i < departmanSayisi; i++) {
            WebElement departman = departmanListesi.get(i);
            String departmanAdi = departman.getText();
            System.out.println("Kontrol edilen: " + departmanAdi);
            softAssert.assertTrue(departman.isDisplayed(),
                    departmanAdi + " görünmüyor!");
            softAssert.assertTrue(departman.isEnabled(),
                    departmanAdi + " tıklanabilir değil!");
            departman.click();
            ReusableMethods.bekle(1);
            String currentUrl = Driver.getDriver().getCurrentUrl();
            softAssert.assertTrue(currentUrl.contains("Departments") || currentUrl.contains("department"),
                    "HATA: " + departmanAdi + " sayfası açılmadı! URL: " + currentUrl);
            Driver.getDriver().navigate().back();
            ReusableMethods.bekle(1);
        }
        softAssert.assertAll();
    }

    @Test
    public void tC03_bedsDepartmanDetayKontrol() {
        SoftAssert softAssert = new SoftAssert();
        anasayfaDepartmentsMenu.click();
        ReusableMethods.bekle(1);
        for (int i = 0; i < departmanListesi.size(); i++) {
            WebElement departman = departmanListesi.get(i);
            String departmanAdi = departman.getText();
            System.out.println("Kontrol ediliyor: " + departmanAdi);
            departman.click();
            ReusableMethods.bekle(1);
            if (bedsLinks.isEmpty()) {
                String hataMesaji = "HATA: '" + departmanAdi + "' departmanı altında Beds kategorisi BULUNAMADI!";
                System.out.println(hataMesaji);
                softAssert.fail(hataMesaji);
                Driver.getDriver().navigate().back();
                ReusableMethods.bekle(1);
                continue;
            }
            int yatakSayisi = bedsLinks.size();
            for (int j = 0; j < yatakSayisi; j++) {
                bedsLinks.get(j).click();
                ReusableMethods.bekle(1);
                List<String> info = ReusableMethods.stringListeDondur(detayBilgiListesi);
                String[] basliklar = {"Doctors", "Departments", "Medicines", "Price"};
                for (String baslik : basliklar) {
                    boolean veriVar = info.stream().anyMatch(e -> e.toLowerCase().contains(baslik.toLowerCase()));
                    softAssert.assertTrue(veriVar, "HATA: " + departmanAdi + " içinde '" + baslik + "' bilgisi eksik!");
                }
                softAssert.assertTrue(bookingButton.isDisplayed(), "HATA: " + departmanAdi + " içinde Randevu butonu yok!");
                Driver.getDriver().navigate().back();
                ReusableMethods.bekle(1);
            }
            Driver.getDriver().navigate().back();
            ReusableMethods.bekle(1);
        }
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDownClass() {
        Driver.quitDriver();
    }
}
