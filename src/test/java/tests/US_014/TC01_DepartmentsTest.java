package tests.US_014;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.yaprakPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.List;

public class TC01_DepartmentsTest extends yaprakPage {

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
        //Departman listesindeki ilgili departman kategorilerin tıklanabilir olduğunu doğrulamak
        SoftAssert softAssert = new SoftAssert();

        anasayfaDepartmentsMenu.click();
        ReusableMethods.bekle(1);

        List<WebElement> departmanlar =
                Driver.getDriver().findElements(By.xpath("//label[@class='container_check']/a"));

        for (int i = 0; i < departmanlar.size(); i++) {
            departmanlar = Driver.getDriver().findElements(By.xpath("//label[@class='container_check']/a"));
            WebElement departman = departmanlar.get(i);
            String departmanAdi = departman.getText();

            softAssert.assertTrue(departman.isDisplayed(),
                    departmanAdi + " tC02_görünmüyor!");

            softAssert.assertTrue(departman.isEnabled(),
                    departmanAdi + " tC02_tıklanabilir değil!");

            departman.click();
            ReusableMethods.bekle(1);

            softAssert.assertTrue(Driver.getDriver().getCurrentUrl().contains("Departments"),
                    "HATA: " + departmanAdi + " tC02_sayfası açılmadı!");

            Driver.getDriver().navigate().back();
            ReusableMethods.bekle(1);
        }

        softAssert.assertAll();
    }


    @Test
    public void tC03_bedsDepartmanDetayKontrol() {
    //departmanlar sayfasında "Beds Department" alt kategorilerinin
    // tıklanabilir olduğunu ve tüm verilerin görünür ve doğru olduğunu doğrulamak.
    //( Department info, Doctor, Department, Medicines, Price, Appointment Booking.)

        anasayfaDepartmentsMenu.click();
        ReusableMethods.bekle(1);

        List<WebElement> departmanlar =
                Driver.getDriver().findElements(By.xpath("//label[@class='container_check']/a"));

        for (int i = 0; i < departmanlar.size(); i++) {

            departmanlar = Driver.getDriver().findElements(By.xpath("//label[@class='container_check']/a"));
            WebElement departman = departmanlar.get(i);
            String departmanAdi = departman.getText();

            departman.click();
            ReusableMethods.bekle(1);

            List<WebElement> bedsList =
                    Driver.getDriver().findElements(By.xpath("//a[contains(@href,'/Beds/')]"));

            if (bedsList.isEmpty()) {
                System.out.println(" 💛 " + departmanAdi + " tC03_ altında Beds Department yok.");
                Driver.getDriver().navigate().back();
                ReusableMethods.bekle(1);
                continue;
            }

            for (int j = 0; j < bedsList.size(); j++) {

                bedsList =
                        Driver.getDriver().findElements(By.xpath("//a[contains(@href,'/Beds/')]"));

                bedsList.get(j).click();
                ReusableMethods.bekle(1);

                List<String> info =
                        ReusableMethods.stringListeDondur(
                                Driver.getDriver().findElements(By.xpath("//ul/li")));

                String[] basliklar = {"Doctors", "Departments", "Medicines", "Price"};

                for (String baslik : basliklar) {
                    Assert.assertTrue(
                            info.stream().anyMatch(e -> e.toLowerCase().contains(baslik.toLowerCase())),
                            " tC03_'" + baslik + "'  bilgisi görünmüyor!"
                    );
                }

                Assert.assertTrue(
                        Driver.getDriver().findElement(By.xpath("//input[@value='Appointment Booking']")).isDisplayed(),
                        " tC03_Appointment Booking butonu bulunamadı!"
                );

                Driver.getDriver().navigate().back();
                ReusableMethods.bekle(1);
            }

            Driver.getDriver().navigate().back();
            ReusableMethods.bekle(1);
        }
    }

    @AfterClass
    public void tearDownClass() {
        Driver.quitDriver();
    }
}
