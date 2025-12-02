package tests.US_005;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FerhatPage;
import utilities.Driver;
import utilities.ReusableMethods;

import java.time.Duration;

public class AmountTest {
    @Test
    public void tutarTesti(){
        //Ziyaretçi "Beds Departments" bölümünde herhangi bir odaya tıkladığında oradaki doktor,
        //verilecek ilaç ve tutar doğru şekilde görüntülendiğini doğrulamak

        Driver.getDriver().get("https://qa.loyalfriendcare.com/en");

        FerhatPage ferhatPage =new FerhatPage();
        int elementList = ferhatPage.tumLinklerList.size();

        for (int i = 0; i <elementList ; i++) {
            ferhatPage = new FerhatPage();
            
            WebElement linkler = ferhatPage.tumLinklerList.get(i);
            linkler.click();


            ferhatPage = new FerhatPage();
            int listIcindeList = ferhatPage.bedsDepartmentsLinkList.size();

            for (int j = 0; j < listIcindeList; j++) {
                ferhatPage = new FerhatPage();

                WebElement linkIcindeLink = ferhatPage.bedsDepartmentsLinkList.get(j);
                linkIcindeLink.click();
                JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
                js.executeScript("arguments[0].scrollIntoView(true);", ferhatPage.fiyatBilgisi);
                ReusableMethods.bekle(2);

                WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOf(ferhatPage.fiyatBilgisi));


                Assert.assertTrue(ferhatPage.fiyatBilgisi.isDisplayed());
                Driver.getDriver().navigate().back();
                ReusableMethods.bekle(2);

            }
            
        }
        Driver.quitDriver();
            
        
    }

}
