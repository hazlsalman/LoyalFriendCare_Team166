package tests.US_025;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FerhatPage;
import utilities.Driver;
import utilities.ReusableMethods;

public class DeleteUserAccount {
    @Test
    public void test01(){
        //Kullanıcı delete butonuna bastıgında kullanıcıyı silebilmeli.

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

        for (int i = 0; i < 4; i++) {
            ferhatPage.nextButton.click();
            ReusableMethods.bekle(1);

        }
        ferhatPage.deleteButton.click();
        Assert.assertTrue(ferhatPage.basariText.isDisplayed());

        Driver.quitDriver();
    }
}
