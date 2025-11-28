package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class EnesPage {
    public EnesPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(xpath = "//*[@class='header mm-slideout']")
    public WebElement tumHeader;

    @FindBy (xpath = "//*[@id='logo']")
    public WebElement headerLogo;

    @FindBy (xpath = "//*[@class='main-menu']")
    public WebElement mainMenu;

    @FindBy (xpath = "(//*[@class='btn_add'])[2]")
    public WebElement  signUpButton;

    @FindBy (xpath = "(//*[@target='_self'])[3]")
    public WebElement  headerHome;

    @FindBy (xpath = "(//*[@target='_self'])[4]")
    public WebElement  headerAboutUs;

    @FindBy (xpath = "//*[@id=\"menu\"]/ul/li[3]/span/a")
    public WebElement  headerDeparmentsMenu;

    @FindBy (xpath = "//*[@id=\"menu\"]/ul/li[4]/span/a")
    public WebElement  headerDoctorsMenu;

    @FindBy (xpath = "//*[@id=\"menu\"]/ul/li[5]/span/a")
    public WebElement  headerMedicinesMenu;

    @FindBy (xpath = "//*[@id=\"menu\"]/ul/li[6]/span/a")
    public WebElement  headerVaccinationsMenu;

    @FindBy (xpath = "//*[@class='form-control']")
    public WebElement  aramaKutusu;

    @FindBy (xpath = "//*[@class='form-control']")
    public WebElement  searchButton;

    @FindBy (xpath = "//*[@id=\"page\"]/main/div/div[1]/a\n")
    public WebElement  bodyDeparments;

    @FindBy (xpath = "//*[@id=\"page\"]/main/div/div[2]/div[1]/a/figure/div\n")
    public WebElement  bodyWellnes;

    @FindBy (xpath = "//*[@id=\"page\"]/main/div/div[3]/a\n")
    public WebElement  bodyDoctors;

    @FindBy (xpath = "//*[@alt='Dr. Alejandro Martinez']")
    public WebElement  bodyDrAlejandroMartinez;

    @FindBy (xpath = "//*[@id=\"page\"]/main/div/div[5]/a")
    public WebElement  bodyVaccinations;

    @FindBy (xpath = "(//*[@alt='Rabies Vaccine'])[1]")
    public WebElement  bodyRabiesVaccine;

    @FindBy (xpath = "(//*[@class='container margin_60_35'])[1]")
    public WebElement  tumBody;

    @FindBy (xpath = "(//*[@class='container margin_60_35'])[2]")
    public WebElement  tumFooter;

    @FindBy (xpath = "(//*[@class='logo_sticky'])[1]")
    public WebElement  footerLogo;

    @FindBy (xpath = "//*[@id=\"collapse_ft_2\"]/ul/li[1]/a")
    public WebElement  footerWellnes;

    @FindBy (xpath = "//*[@class='fab fa-facebook-square']")
    public WebElement  footerFacebook;

    @FindBy (xpath = "//*[@data-target='#collapse_ft_2']")
    public WebElement  footerDeparmentsYazi;

    @FindBy (xpath = "//*[@data-target='#collapse_ft_3']")
    public WebElement  footerContactsYazi;

    @FindBy (xpath = "//*[@data-target='#collapse_ft_4']")
    public WebElement  footerFollowUsYazi;

    @FindBy (xpath = "(//*[@class=\"form-control \"])[1]")
    public WebElement  userNameKutu;

    @FindBy (xpath = "(//*[@class=\"form-control \"])[2]")
    public WebElement  passwordKutu;

    @FindBy (xpath = "//*[@class=\"btn btn-primary btn-cons m-t-10\"]")
    public WebElement  logInSignIn;

    @FindBy (xpath = "(//*[@class=\"form-control\"])[2]")
    public WebElement  doctorsTarihFormu;

    @FindBy (xpath = "(//*[@class=\"form-control\"])[3]")
    public WebElement  doctorsTelefonFormu;

    @FindBy (xpath = "(//*[@class=\"form-control\"])[4]")
    public WebElement  doctorsMesajFormu;

    @FindBy (xpath = "//*[@type=\"submit\"]")
    public WebElement  randevuAlmaButon;

    @FindBy (xpath = "//*[@class=\"current\"][1]")
    public WebElement  randevuDepartmansKategori;

    @FindBy (xpath = "//*[@id=\"sidebar\"]/div/div[1]/form/div[4]/div/div")
    public WebElement  randevuDoctorsKategori;

    @FindBy (xpath = "(//*[@class=\"form-control\"])[1]")
    public WebElement  asiTarihFormu;

    @FindBy (xpath = "(//*[@class=\"form-control\"])[2]")
    public WebElement  asiTelefonFormu;

    @FindBy (xpath = "(//*[@class=\"form-control\"])[3]")
    public WebElement  asiMesajFormu;

    @FindBy (xpath = "//*[@role=\"alert\"]\n")
    public WebElement  randevuOnay;

    @FindBy (xpath = "(//*[@class='btn_add'])[1]")
    public WebElement signInButton;

}
