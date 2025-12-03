package tests.US_025;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FerhatPage;
import utilities.Driver;
import utilities.ReusableMethods;

public class UserVisibiltyTest {
    @Test
    public void test01(){
        //Yönetici "Search" kısmına geçerli kullanıcı adı yazdığında o kullanıcının görüntülendiğini doğrulamak.
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

        ferhatPage.searchKutusu.sendKeys("LocalFriendCare");
        ReusableMethods.bekle(1);

        Assert.assertTrue(ferhatPage.aramaSonucUser.isDisplayed());

        Driver.quitDriver();

    }
}
