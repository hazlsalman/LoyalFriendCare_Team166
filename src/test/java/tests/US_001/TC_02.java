package tests.US_001;

import org.testng.annotations.Test;
import pages.EnesPage;
import utilities.ConfigReader;
import utilities.Driver;

public class TC_02 {
    @Test
    public void anaSayfayaErisimTesti(){
        // Ziyaretçi, herhangi bir giriş veya kayıt yapmadan url ile siteye erişim sağlar.

//        Kayıtlı veya yönetici kullanıcı, url ile siteye erişim sağlar.
        Driver.getDriver().get(ConfigReader.getProperty("url"));

//Siteye erişim sağlayan kullanıcı (ziyaretçi, kayıtlı, yönetici)
// header kısmındaki logo, sign in, sign up butonu ve Home, About Us,
// Departments vb. menü ögelerinin görünür olduğunu doğrular.
        EnesPage enesPage =new EnesPage();
        enesPage.tumHeader.isDisplayed();
        enesPage.headerLogo.isDisplayed();
        enesPage.headerHome.isDisplayed();
        enesPage.headerAboutUs.isDisplayed();
        enesPage.headerDoctorsMenu.isDisplayed();
        enesPage.headerDeparmentsMenu.isDisplayed();
        enesPage.headerMedicinesMenu.isDisplayed();
        enesPage.headerVaccinationsMenu.isDisplayed();
        enesPage.signInButton.isDisplayed();
        enesPage.signUpButton.isDisplayed();


        //Kullanıcı (ziyaretçi, kayıtlı, yönetici) header kısmındaki logo, sign in, sign up butonu ve Home, About Us,
        // Departments vb. menü ögelerini tıklanabildiğini doğrular.
        enesPage.headerLogo.isEnabled();
        enesPage.headerHome.isEnabled();
        enesPage.headerAboutUs.isEnabled();
        enesPage.headerDeparmentsMenu.isEnabled();
        enesPage.headerDoctorsMenu.isEnabled();
        enesPage.headerMedicinesMenu.isEnabled();
        enesPage.headerVaccinationsMenu.isEnabled();
        enesPage.signInButton.isEnabled();
        enesPage.signUpButton.isEnabled();


        Driver.quitDriver();






    }

}
