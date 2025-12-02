package tests.US_003;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RabiaPage;
import utilities.ConfigReader;
import utilities.Driver;

import javax.swing.*;

public class TC_001 {



    @Test
    public void sabitHeaderTesti(){

        Driver.getDriver().get(ConfigReader.getProperty("url"));


//TC-01
//1. Sayfayi asagi kaydir
//2. Header bölümünün görünür oldugunu gözlemle

        Actions actions=new Actions(Driver.getDriver());
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        RabiaPage rabiaPage=new RabiaPage();
        WebElement tumHeaderElement=rabiaPage.tumHeader;
        Assert.assertTrue(tumHeaderElement.isDisplayed());

        Driver.quitDriver();




    }



}
