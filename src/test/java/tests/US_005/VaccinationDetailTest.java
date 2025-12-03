package tests.US_005;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FerhatPage;
import utilities.ConfigReader;
import utilities.Driver;

public class VaccinationDetailTest {
    @Test
    public void test01(){
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        FerhatPage ferhatPage = new FerhatPage();
        int linkSayisi = ferhatPage.tumLinklerList.size();


        for (int i = 17; i <linkSayisi; i++) {

            WebElement linkler= ferhatPage.tumLinklerList.get(i);
            linkler.click();

            Assert.assertTrue(ferhatPage.vaccinationParagraf.isDisplayed());
            Driver.getDriver().navigate().back();

        }
    }
}
