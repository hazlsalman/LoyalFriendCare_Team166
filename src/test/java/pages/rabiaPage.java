package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class rabiaPage {
    public rabiaPage(){
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












}
