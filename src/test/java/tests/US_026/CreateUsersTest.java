package tests.US_026;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FerhatPage;
import utilities.Driver;

public class CreateUsersTest {
    @Test
    public void test01(){
        Driver.getDriver().get("https://qa.loyalfriendcare.com/en");

        FerhatPage ferhatPage = new FerhatPage();

        ferhatPage.signinButton.click();

        ferhatPage.aramaKutusuId.sendKeys("admin.ferhat.yilmaz@loyalfriendcare.com");

        ferhatPage.aramaKutusuPassword.sendKeys("LFCare.0201");

        ferhatPage.girisEkraniButton.click();

        ferhatPage.signinButton.click();

        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(ferhatPage.userMenu).perform();

        ferhatPage.dropdown.click();

        ferhatPage.createUsersLink.click();
        ferhatPage.createUsersId.sendKeys("Ferhat");

        ferhatPage.createUsersPhone.sendKeys("543455");

        ferhatPage.checkBoxButton.click();

        ferhatPage.passwordKutusu.sendKeys("12345.");

        ferhatPage.ikinciPasswordKutusu.sendKeys("12345.");

        ferhatPage.emailKutusu.sendKeys("ferhatyilmaz123@gmail.com");

        ferhatPage.saveButton.click();

        Assert.assertTrue(ferhatPage.basariMesaji.isDisplayed());

        Driver.quitDriver();


    }
}
