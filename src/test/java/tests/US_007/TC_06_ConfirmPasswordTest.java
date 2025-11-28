package tests.US_007;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.Assert;
import pages.HakimPage;
import utilities.ConfigReader;
import utilities.Driver;

import static utilities.Driver.driver;

public class TC_06_ConfirmPasswordTest {

    @Test
    public void test01() {

        HakimPage hakimPage = new HakimPage();
        Faker faker = new Faker();
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();


        // --------------------------
        // PRE-CONDITION: 1) ANASAYFAYA GİT
        // --------------------------
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        // --------------------------
        // PRE-CONDITION: 2) SIGN UP SAYFASINA GEÇ
        // --------------------------
        hakimPage.homeSignUpButton.click();

        // --------------------------
        // PRE-CONDITION: 3) USERNAME VE EMAIL ALANLARINI DOLDUR
        // --------------------------
        String username = faker.name().username();
        String uniqMail = faker.internet().emailAddress();

        hakimPage.usernameBox.sendKeys(username);
        hakimPage.emailBox.sendKeys(uniqMail);


        // --------------------------
        // PRE-CONDITION: 4) GEÇERLİ PASSWORD YAZ
        // --------------------------
        String validPassword = ConfigReader.getProperty("userGecerliPassword");


        // Normal sendKeys bazen çalışmıyor; güvenli olması için JS ile value set edelim ve event tetikleyelim
        js.executeScript("arguments[0].value = arguments[1];", hakimPage.passwordBox, validPassword);
        js.executeScript("arguments[0].dispatchEvent(new Event('input'));", hakimPage.passwordBox);
        js.executeScript("arguments[0].dispatchEvent(new Event('change'));", hakimPage.passwordBox);



        // =========================================================
        // SENARYO 1: CONFIRM PASSWORD FARKLI YAZILIR → UYARI ALINIR
        // =========================================================

        hakimPage.confirmPasswordBox.clear();

        // confirm'a farklı bir değer yaz (JS ile)
        String wrongPassword = "a9?**2222";
        js.executeScript("arguments[0].value='" + wrongPassword + "';", hakimPage.confirmPasswordBox);
        js.executeScript("arguments[0].dispatchEvent(new Event('input'));", hakimPage.confirmPasswordBox);
        js.executeScript("arguments[0].dispatchEvent(new Event('change'));", hakimPage.confirmPasswordBox);


        hakimPage.registersignUpButton.click();

        String uyarı1 = "";
        try {
            uyarı1 = hakimPage.passwordNotMatchText.getText();
            System.out.println("Uyarı 1: " + uyarı1);
        } catch (Exception e) {
            System.out.println("Senaryo 1: passwordNotMatchText görünmedi veya zaman aşımına uğradı.");
        }


        Assert.assertTrue(
                uyarı1.toLowerCase().contains("match") || uyarı1.length() > 0,
                "Farklı şifre yazıldığında 'eşleşmiyor' uyarısı çıkmadı!"
        );

        // =========================================================
        // SENARYO 2: CONFIRM PASSWORD BOŞ BIRAKILIR → BROWSER UYARISI
        // =========================================================

        hakimPage.confirmPasswordBox.clear();

        hakimPage.registersignUpButton.click();


        // Browser required uyarısını al (validationMessage)
        String uyari2 = (String) js.executeScript(
                "return arguments[0].validationMessage;", hakimPage.confirmPasswordBox);

        System.out.println("Uyarı 2: " + uyari2);

        Assert.assertTrue(
                uyari2.toLowerCase().contains("fill") || uyari2.length() > 0,
                "Boş bırakıldığında 'Please fill out this field' uyarısı çıkmadı!"
        );


        // =========================================================
        // SENARYO 3: CONFIRM PASSWORD AYNI YAZILIR → KAYIT BAŞARILI
        // =========================================================


        // Tüm alanları temizle
        hakimPage.usernameBox.clear();
        hakimPage.emailBox.clear();
        hakimPage.passwordBox.clear();
        hakimPage.confirmPasswordBox.clear();

        // Yeni verilerle doldur
        String username3 = faker.name().username();
        String uniqMail3 = faker.internet().emailAddress();

        hakimPage.usernameBox.sendKeys(username3);
        hakimPage.emailBox.sendKeys(uniqMail3);


        // Password alanını doldur
        js.executeScript("arguments[0].value = arguments[1];", hakimPage.passwordBox, validPassword);
        js.executeScript("arguments[0].dispatchEvent(new Event('input'));", hakimPage.passwordBox);
        js.executeScript("arguments[0].dispatchEvent(new Event('change'));", hakimPage.passwordBox);
        System.out.println("Password alanı dolduruldu: " + validPassword);

        // confirm'ı JS ile doldur (AYNI şifre)
        js.executeScript("arguments[0].value = arguments[1];", hakimPage.confirmPasswordBox, validPassword);
        js.executeScript("arguments[0].dispatchEvent(new Event('input'));", hakimPage.confirmPasswordBox);
        js.executeScript("arguments[0].dispatchEvent(new Event('change'));", hakimPage.confirmPasswordBox);
        System.out.println("Confirm Password alanı dolduruldu: " + validPassword);

        // Sign Up butonuna tıkla.
        hakimPage.registersignUpButton.click();



        // Sayfada uyarı mesajı var mı kontrol et.
        System.out.println("\nSayfada uyarı mesajı kontrol ediliyor.");
        if(!Driver.getDriver().findElements(By.id("passwordNotMatch")).isEmpty()){
            System.out.println("Uyarı var.");
        } else {
            System.out.println("Uyarı yok.");
        }



        // Sayfa yönlendirmesini kontrol ediyoruz
        String expectedUrl = "https://qa.loyalfriendcare.com/en"; // Anasayfa URL'si
        String actualUrl = driver.getCurrentUrl(); // Şu anki sayfanın URL'si


        Assert.assertEquals(actualUrl, expectedUrl,
                "Şifreler aynı olmasına rağmen kayıt gerçekleşmedi veya URL yanlış!");


        Driver.quitDriver(); // Testin sonunda driver'ı kapatıyoruz
    }
}