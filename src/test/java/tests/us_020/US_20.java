package tests.us_020;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class US_20 {

    @Test
    public void us_20_01(){
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        WebElement loginButton = Driver.getDriver().findElement(By.xpath("(//*[@class='btn_add'])[1]"));
        loginButton.click();

        WebElement email = Driver.getDriver().findElement(By.id("email"));
        email.sendKeys(ConfigReader.getProperty("userGecerliMail"));

        WebElement password = Driver.getDriver().findElement(By.id("password"));
        password.sendKeys(ConfigReader.getProperty("userGecerliPassword"));

        Driver.getDriver().findElement(By.xpath("//*[@type='submit']")).click();

        ReusableMethods.bekle(3);
        Driver.quitDriver();
    }
}
