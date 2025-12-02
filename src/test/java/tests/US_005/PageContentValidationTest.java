package tests.US_005;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FerhatPage;
import utilities.ConfigReader;
import utilities.Driver;

public class PageContentValidationTest {

    @Test
    public void departmentsLinkTest(){

        //Ziyareçi olarak herhangi bir departmana tıkladığımda
        // o departman hakkında yataklı bölümün görüntülenebildiğini doğrulamak.

        Driver.getDriver().get(ConfigReader.getProperty("url"));

        FerhatPage ferhatPage = new FerhatPage();
        int linkSayisi = ferhatPage.tumLinklerList.size();
        int limit = Math.min(linkSayisi,9);

        for (int i = 0; i <limit ; i++) {

            WebElement linkler= ferhatPage.tumLinklerList.get(i);
            linkler.click();

            Assert.assertTrue(ferhatPage.bedsDepartments.isDisplayed());
            Driver.getDriver().navigate().back();

        }


        Driver.quitDriver();

    }
}
