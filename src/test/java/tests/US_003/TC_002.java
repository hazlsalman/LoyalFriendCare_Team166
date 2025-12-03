package tests.US_003;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RabiaPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC_002 {


    @Test
    public void islevselMenüOgeleriTesti() {

        Driver.getDriver().get(ConfigReader.getProperty("url"));



        //TC-02
        //1. Menüdeki her öğeye sırayla tıkla.
        // 2. Açılan sayfanın doğru olup olmadığını kontrol et.

        RabiaPage rabiaPage=new RabiaPage();
        rabiaPage.headerHome.click();
        WebElement aramaKutusuElement=rabiaPage.aramaKutusu;
        Assert.assertTrue(aramaKutusuElement.isDisplayed());

        rabiaPage.headerAboutUs.click();
        String actualUrl=Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(actualUrl.contains("about"));


        rabiaPage.headerDeparmentsMenu.click();
        String actualUrl2=Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(actualUrl2.contains("Departments"));

        rabiaPage.headerDoctorsMenu.click();
        String actualUrl3=Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(actualUrl3.contains("Doctors"));

        rabiaPage.headerMedicinesMenu.click();
        String actualUrl4=Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(actualUrl4.contains("Medicines"));


        rabiaPage.headerVaccinationsMenu.click();
        String actualUrl5=Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(actualUrl5.contains("Pets"));


        Driver.quitDriver();



    }
}
