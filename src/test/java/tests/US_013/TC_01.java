package tests.US_013;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EnesPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

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

        //Kayıtlı kullanıcı, login olduğunda ana sayfanın body kısmında yer alan "Vaccinations for Pets " textinin
        // ve "Vaccinations" butonunun görebilir olduğunu doğrular.
        WebElement vaccinationsForPetsBaslikElement= enesPage.vaccinationsForPetsBaslik;
        Assert.assertTrue(vaccinationsForPetsBaslikElement.isDisplayed());

        WebElement bodyVaccinationsElement=enesPage.bodyVaccinations;
        Assert.assertTrue(bodyVaccinationsElement.isDisplayed());

        //Kayıtlı kullanıcı,  ana sayfanın body bölümünde yer alan "Vaccinations" butonunun tıklanabilir olduğunu doğrular.

        Assert.assertTrue(bodyVaccinationsElement.isEnabled());

        //Kayıtlı kullanıcı,  ana sayfanın body bölümünde yer alan "Vaccinations" butonuna click yapar
        // ve doktorlar sayfasına erişim sağladığını "Vaccinations" heading'inin görülür olması ile doğrular.


        enesPage.bodyVaccinations.click();


        WebElement vaccinationsTextElement= enesPage.vaccinationsText;
        Assert.assertTrue(vaccinationsTextElement.isDisplayed());





        Driver.quitDriver();



    }

}
