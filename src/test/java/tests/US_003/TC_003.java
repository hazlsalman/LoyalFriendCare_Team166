package tests.US_003;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RabiaPage;
import utilities.ConfigReader;
import utilities.Driver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class TC_003 {


    // Fare Departments, Doctors, Medicines, Vaccinations menulerin üzerine gotürülür,
    // her menüye ait alt menülerin görünür oldugunu dogrular.

    @Test
    public void islevselMenüOgeleriTesti() {

        RabiaPage rabiaPage=new RabiaPage();
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        Actions actions=new Actions(Driver.getDriver());
actions.moveToElement(rabiaPage.headerDeparmentsMenu).perform();
        WebElement menü=Driver.getDriver().findElement(By.xpath("//*[@id=\"menu\"]/ul/li[3]/span"));
        Assert.assertTrue(menü.isDisplayed());


        actions.moveToElement(rabiaPage.headerDoctorsMenu).perform();
        WebElement menü2=Driver.getDriver().findElement(By.xpath("//*[@id=\"menu\"]/ul/li[4]/span"));
        Assert.assertTrue(menü2.isDisplayed());


        actions.moveToElement(rabiaPage.headerVaccinationsMenu).perform();
        WebElement menü3=Driver.getDriver().findElement(By.xpath("//*[@id=\"menu\"]/ul/li[6]/span"));
        Assert.assertTrue(menü3.isDisplayed());


        actions.moveToElement(rabiaPage.headerMedicinesMenu).perform();
        WebElement menü4=Driver.getDriver().findElement(By.xpath("//*[@id=\"menu\"]/ul/li[5]/span"));
        Assert.assertTrue(menü4.isDisplayed());


      Driver.quitDriver();




    }

    }
