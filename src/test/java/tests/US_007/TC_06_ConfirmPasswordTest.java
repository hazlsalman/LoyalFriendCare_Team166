package tests.US_007;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.Assert;
import pages.HakimPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;

import static utilities.Driver.driver;

public class TC_06_ConfirmPasswordTest {


    HakimPage hakimPage = new HakimPage();
    Faker faker = new Faker();

    @Test
    public void test01() throws InterruptedException {

        // =========================================================
        // PRE-CONDITION:
        // =========================================================

        //1-) Anasayfaya git.
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        //2-) Sıgn Up butonuna bas.
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.homePageSignUpButton)).click();

        //3-) Username alanını doldur.
        Assert.assertTrue(hakimPage.usernameBox.isDisplayed(), "USERNAME kutusu görünmüyor.");
        hakimPage.usernameBox.sendKeys(faker.name().username());

        //4-) Email alanını doldur.
        Assert.assertTrue(hakimPage.emailBox.isDisplayed(), "EMAİL kutusu görünmüyor.");
        hakimPage.emailBox.sendKeys(faker.internet().emailAddress());

        //5-) Geçerli Password yaz.
        hakimPage.passwordBox.sendKeys("A1?!!!99" + Keys.TAB);



        // =========================================================
        // SENARYO 1: CONFIRM PASSWORD'Ü, PASSWORD'DEN FARKLI YAZ.
        // =========================================================

        hakimPage.confirmPasswordBox.sendKeys("a9?**2222" + Keys.TAB);

        hakimPage.registerPageSignUpButton.click();

        String uyarı1 = "";
        try {
            uyarı1 = hakimPage.passwordNotMatchText.getText();
            System.out.println("Uyarı 1: " + uyarı1);
        } catch (Exception e) {
            System.out.println("Senaryo 1: passwordNotMatchText görünmedi veya zaman aşımına uğradı.");
        }

        Assert.assertTrue(
                 uyarı1.length() > 0,
                "Farklı şifre yazıldığında 'eşleşmiyor' uyarısı çıkmadı."
        );



        // =========================================================
        // SENARYO 2: CONFIRM PASSWORD ALANINI BOŞ BIRAK.
        // =========================================================

        hakimPage.confirmPasswordBox.clear();

        hakimPage.registerPageSignUpButton.click();


        // Browser uyarısını al (validationMessage)
        String uyarı2 = hakimPage.confirmPasswordBox.getAttribute("validationMessage");

        System.out.println("Uyarı 2: " + uyarı2);

        Assert.assertTrue(
                 uyarı2.length() > 0,
                "Boş bırakıldığında 'Please fill out this field' uyarısı çıkmadı."
        );



        // =========================================================
        // SENARYO 3: CONFIRM PASSWORD'Ü,PASSWORD İLE AYNEN YAZ.
        // =========================================================


        // Tüm alanları temizle
        hakimPage.usernameBox.clear();
        hakimPage.emailBox.clear();
        hakimPage.passwordBox.clear();
        hakimPage.confirmPasswordBox.clear();

        // Yeni verilerle doldur.
        hakimPage.usernameBox.sendKeys(faker.name().username());
        hakimPage.emailBox.sendKeys(faker.internet().emailAddress());
        hakimPage.passwordBox.sendKeys("a9?**2222" + Keys.TAB);
        hakimPage.confirmPasswordBox.sendKeys("a9?**2222" + Keys.TAB);

        // Sign Up butonuna tıkla.
        hakimPage.registerPageSignUpButton.click();

        // Sayfada uyarı mesajı var mı kontrol et.
        System.out.println("\nSayfada uyarı mesajı kontrol ediliyor.");
        if(!Driver.getDriver().findElements(By.id("passwordNotMatch")).isEmpty()){
            System.out.println("Uyarı var.");
        } else {
            System.out.println("Uyarı yok.");
        }


        // Sayfa yönlendirmesini kontrol et.
        String expectedUrl = "https://qa.loyalfriendcare.com/en"; // Anasayfa URL'si
        String actualUrl = driver.getCurrentUrl(); // Şu anki sayfanın URL'si


        Assert.assertEquals(actualUrl, expectedUrl,
                "Şifreler aynı olmasına rağmen kayıt gerçekleşmedi veya URL yanlış.");


        Driver.quitDriver();
    }
}
