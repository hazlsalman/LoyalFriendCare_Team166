package tests.US_027;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Driver;

public class TC_01_HomePageAccess {

        @Test
        public void test01() {

            // 1- URL'e git.
            Driver.getDriver().get(ConfigReader.getProperty("url"));

            // 2- Anasayfa açıldığını doğrula.
            Assert.assertTrue(
                    Driver.getDriver().getCurrentUrl().contains("loyalfriendcare"),
                    "Anasayfa görüntülenemedi."
            );

            // 3- Driver'ı kapat.
            Driver.quitDriver();
        }
    }


