package tests.US_012;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EnesPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC_01 {
    @Test
    public void anaSayfayaErisimTesti() {

        Driver.getDriver().get(ConfigReader.getProperty("url"));

        EnesPage enesPage =new EnesPage();
        enesPage.signInButton.click();
        enesPage.userNameKutu.sendKeys(ConfigReader.getProperty("userGecerliMail"));
        enesPage.passwordKutu.sendKeys(ConfigReader.getProperty("userGecerliPassword"));
        enesPage.logInSignIn.click();
        String expectedGorunenUsername="user.atakan.durman";
        String actualGorunenUsername=enesPage.gorunenUserName.getText();
        System.out.println(actualGorunenUsername);
        Assert.assertEquals(actualGorunenUsername,expectedGorunenUsername);
        //Kayıtlı kullanıcı, login olduğunda ana sayfanın body kısmında yer alan "Popular Doctors" textinin
        // ve "Doctors" butonunun görebilir olduğunu doğrular.
        WebElement populerDoctorsTextElement= enesPage.populerDoctorsText;
        Assert.assertTrue(populerDoctorsTextElement.isDisplayed());

        WebElement bodyDoctorsElement=enesPage.bodyDoctors;
        Assert.assertTrue(bodyDoctorsElement.isDisplayed());

        //Kayıtlı kullanıcı,  ana sayfanın body bölümünde yer alan "Doctors" butonunun tıklanabilir olduğunu doğrular.

        Assert.assertTrue(bodyDoctorsElement.isEnabled());

        //Kayıtlı kullanıcı,  ana sayfanın body bölümünde yer alan "Doctors" butonuna click yapar
        // ve doktorlar sayfasına erişim sağladığını "Doctors" heading'inin görülür olması ile doğrular.

        enesPage.bodyDoctors.click();

        WebElement doctorsHeadingElement=enesPage.doctorsHeading;
        Assert.assertTrue(doctorsHeadingElement.isDisplayed());





        Driver.quitDriver();



    }
    }


