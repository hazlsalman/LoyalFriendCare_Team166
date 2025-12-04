package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class RabiaPage {
    public RabiaPage(){
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

    @FindBy (xpath = "//*[@class='form-control']")
    public WebElement  aramaKutusu;




    @FindBy (xpath = "(//*[@class='btn_add'])[1]")
    public WebElement signInButton;

    @FindBy (xpath = "(//*[@class=\"form-control \"])[1]")
    public WebElement  userNameKutu;

    @FindBy (xpath = "(//*[@class=\"form-control \"])[2]")
    public WebElement  passwordKutu;

    @FindBy (xpath = "//*[@class=\"btn btn-primary btn-cons m-t-10\"]")
    public WebElement  logInSignIn;

    @FindBy (xpath = "//*[@class='btn_add'][1]")
    public WebElement gorunenUserName;

    @FindBy (xpath = "/html/body/nav/div[2]/div[1]/ul/li[13]/a/span")
    public WebElement  deashboardVaccinations;

    @FindBy (xpath = "//*[@class=\"page-sidebar\"]")
    public WebElement  deashboardMenu;

    @FindBy (xpath = "//*[@class=\"card-body\"][1]")
    public WebElement galeriText;

    @FindBy (xpath = "//*[@id=\"tableWithSearch\"]/tbody")
    public List<WebElement> asiListesiBody;

    @FindBy (xpath = "//*[@class=\"form-control pull-right\"]")
    public WebElement  asiAramaKutusu;

    @FindBy (xpath = "/html/body/div[1]/div[2]/div[1]/div[2]/div/div[2]/nav/a")
    public WebElement  nextButton;

    @FindBy (xpath = "//*[@class=\"btn btn-danger btn-cons btn-animated from-top fa  fa-remove\"]\n")
    public WebElement  deleteButton;

    @FindBy (xpath = "/html/body/div[1]/div[2]/div[1]/div/div/span")
    public WebElement basariliSilme;

    @FindBy (xpath = "/html/body/div[1]/div[2]/div[1]/div[2]/div/div[1]/div/div[1]/div")
    public WebElement basariliAsiEklemeYazi;

    @FindBy (xpath = "//*[@class=\"btn btn-tag btn-success btn-tag-rounded\"]\n")
    public WebElement addVaccinationsButton;

    @FindBy (xpath = "//*[@class=\"btn btn-success btn-cons btn-animated from-left fa fa-save pull-right\"]\n")
    public WebElement saveButton;

    @FindBy (xpath = "/html/body/div[1]/div[2]/div[1]/div[2]/div/div[1]/div/div[1]/div")
    public WebElement asiEklemesayfasiText;

    @FindBy (xpath = "//*[@id=\"Title_en\"]\n")
    public WebElement petsTitleForm;

    @FindBy (xpath = "//*[@id=\"body_en\"]")
    public WebElement contentPetsForm;

    @FindBy (xpath = "//*[@id=\"price\"]")
    public WebElement petsPriceForm;

    @FindBy (xpath = "/html/body/div[1]/div[2]/div[1]/div/div/span")
    public WebElement asiEklemOnay;







}
