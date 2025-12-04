package tests.US_001;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EnesPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC_02 {
    @Test
    public void islevselHeaderTesti(){
        // Ziyaretçi, herhangi bir giriş veya kayıt yapmadan url ile siteye erişim sağlar.

//        Kayıtlı veya yönetici kullanıcı, url ile siteye erişim sağlar.
        Driver.getDriver().get(ConfigReader.getProperty("url"));

//Siteye erişim sağlayan kullanıcı (ziyaretçi, kayıtlı, yönetici)
// header kısmındaki logo, sign in, sign up butonu ve Home, About Us,
// Departments vb. menü ögelerinin görünür olduğunu doğrular.
        EnesPage enesPage =new EnesPage();

        WebElement tumHeaderElement=enesPage.tumHeader;
        Assert.assertTrue(tumHeaderElement.isDisplayed());

        WebElement headerLogoElement=enesPage.headerLogo;
        Assert.assertTrue(headerLogoElement.isDisplayed());

        WebElement headerHomeElement=enesPage.headerHome;
        Assert.assertTrue(headerHomeElement.isDisplayed());

        WebElement headerAboutUsElement=enesPage.headerAboutUs;
        Assert.assertTrue(headerAboutUsElement.isDisplayed());

        WebElement headerDoctorsMenuElement=enesPage.headerDoctorsMenu;
        Assert.assertTrue(headerDoctorsMenuElement.isDisplayed());

        WebElement headerDeparmentsMenuElement=enesPage.headerDeparmentsMenu;
        Assert.assertTrue(headerDeparmentsMenuElement.isDisplayed());

        WebElement headerMedicinesMenuElement=enesPage.headerMedicinesMenu;
        Assert.assertTrue(headerMedicinesMenuElement.isDisplayed());

        WebElement headerVaccinationsMenuElement=enesPage.headerVaccinationsMenu;
        Assert.assertTrue(headerVaccinationsMenuElement.isDisplayed());

        WebElement signInButtonElement=enesPage.signInButton;
        Assert.assertTrue(signInButtonElement.isDisplayed());

        WebElement signUpButtonElement=enesPage.signUpButton;
        Assert.assertTrue(signUpButtonElement.isDisplayed());


        //Kullanıcı (ziyaretçi, kayıtlı, yönetici) header kısmındaki logo, sign in, sign up butonu ve Home, About Us,
        // Departments vb. menü ögelerini tıklanabildiğini doğrular.

        Assert.assertTrue(headerLogoElement.isEnabled());
        Assert.assertTrue(headerHomeElement.isEnabled());
        Assert.assertTrue(headerAboutUsElement.isEnabled());
        Assert.assertTrue(headerDeparmentsMenuElement.isEnabled());
        Assert.assertTrue(headerDoctorsMenuElement.isEnabled());
        Assert.assertTrue(headerMedicinesMenuElement.isEnabled());
        Assert.assertTrue(headerVaccinationsMenuElement.isEnabled());
        Assert.assertTrue(signInButtonElement.isEnabled());
        Assert.assertTrue(signUpButtonElement.isEnabled());



        Driver.quitDriver();






    }

}
