package tests.US_001;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EnesPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC_01 {

   /*


Kayıtlı veya yönetici kullanıcı, "PASSWORD" kutusuna kullanıcı şifresini doğru bir şekilde girer.
Kayıtlı veya yönetici kullanıcı "Sign In" butonuna click yapar.
Kayıtlı veya yönetici kullanıcı, username'sini sağ üst köşede görür ve siteye erişim sağlar.

    */

    @Test
    public void anaSayfayaErisimTesti(){
         // Ziyaretçi, herhangi bir giriş veya kayıt yapmadan url ile siteye erişim sağlar.

//        Kayıtlı veya yönetici kullanıcı, url ile siteye erişim sağlar.
        Driver.getDriver().get(ConfigReader.getProperty("url"));

//Kayıtlı veya yönetici kullanıcı, sign in butonuna click yapar.
        EnesPage enesPage =new EnesPage();
        enesPage.signInButton.click();
       // Kayıtlı veya yönetici kullanıcı, "SIGN IN" kutusuna kullanıcı mailini doğru bir şekilde girer.
        enesPage.userNameKutu.sendKeys(ConfigReader.getProperty("userGecerliMail"));
        enesPage.passwordKutu.sendKeys(ConfigReader.getProperty("userGecerliPassword"));
        enesPage.logInSignIn.click();
       String expectedGorunenUsername="user.atakan.durman";
       String actualGorunenUsername=enesPage.gorunenUserName.getText();
        System.out.println(actualGorunenUsername);
       Assert.assertEquals(actualGorunenUsername,expectedGorunenUsername);
       Driver.quitDriver();

    }



}
