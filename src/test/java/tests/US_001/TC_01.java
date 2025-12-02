package tests.US_001;

import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Driver;

public class TC_01 {
   /* Ziyaretçi, herhangi bir giriş veya kayıt yapmadan url ile siteye erişim sağlar.
     Kayıtlı veya yönetici kullanıcı, url ile siteye erişim sağlar.
Kayıtlı veya yönetici kullanıcı, sign in butonuna click yapar.
Kayıtlı veya yönetici kullanıcı, "SIGN IN" kutusuna kullanıcı mailini doğru bir şekilde girer.
Kayıtlı veya yönetici kullanıcı, "PASSWORD" kutusuna kullanıcı şifresini doğru bir şekilde girer.
Kayıtlı veya yönetici kullanıcı "Sign In" butonuna click yapar.
Kayıtlı veya yönetici kullanıcı, username'sini sağ üst köşede görür ve siteye erişim sağlar.

    */

    @Test
    public void anaSayfayaErisimTesti(){
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        Driver.quitDriver();

    }



}
