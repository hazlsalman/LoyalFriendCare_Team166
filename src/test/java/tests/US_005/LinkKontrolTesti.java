package tests.US_005;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FerhatPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.List;

public class LinkKontrolTesti {
    @Test
    public void tumLinkKontrolTesti(){
        //Tüm linklerin aktif olduğunu doğrulamak

        Driver.getDriver().get(ConfigReader.getProperty("url"));

        FerhatPage ferhatPage = new FerhatPage();

        int linksayisi = ferhatPage.tumLinklerList.size();
        for (int i = 0; i <linksayisi ; i++) {
            ferhatPage =new FerhatPage();

            WebElement linkler = ferhatPage.tumLinklerList.get(i);

            linkler.click();
            //ReusableMethods.bekle(2);
            Driver.getDriver().navigate().back();


        }
        Driver.quitDriver();
    }
}
