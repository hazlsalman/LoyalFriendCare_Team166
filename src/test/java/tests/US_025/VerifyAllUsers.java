package tests.US_025;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FerhatPage;
import utilities.Driver;
import utilities.ReusableMethods;

public class VerifyAllUsers {
    //Yönetici "Users" bölümüne tıkladığında tüm kullanıcıların görüntülenebildiğini doğrulamak.
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

        ferhatPage.usersDropdown.click();
        int kullaniciListesi= ferhatPage.kullaniciList.size();

        for (int i = 0; i <kullaniciListesi ; i++) {
            ferhatPage = new FerhatPage();

            WebElement kullanicilar = ferhatPage.kullaniciList.get(i);

            Assert.assertTrue(kullanicilar.isDisplayed());

        }

        Driver.quitDriver();

    }
}
