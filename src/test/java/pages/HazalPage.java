package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class HazalPage {

    public HazalPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }


    @FindBy(xpath = "//input[@name='search']")
    public WebElement homePageAramaKutusu;

    @FindBy (xpath = "(//a[@class='btn_add'])[1]")
    public WebElement homePageSignInOrProfileButton;

    @FindBy (xpath = "//input[@name='email']")
    public WebElement loginPageEmailKutusu;

    @FindBy (xpath = "//input[@name='password']")
    public WebElement loginPagePasswordKutusu;

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement loginPageSignInButton;

    @FindBy (xpath = "//span[@class='semi-bold']")
    public WebElement dashboardPageAdminYaziElementi;

    @FindBy (xpath = "//span[@class='thumbnail-wrapper d32 border-5  inline']")
    public WebElement profilMenuIkonu;

    @FindBy (xpath = "//span[text()='Logout']")
    public WebElement logoutButton;

    @FindBy (xpath = "(//a[text()='Vaccinations'])[4]")
    public WebElement headerVaccinations;

    @FindBy (xpath = "(//a[text()='Vaccinations'])[3]")
    public WebElement bodyVaccinations;

    @FindBy (xpath = "//div[@class='filter_type']")
    public List<WebElement> solListeAsiBasliklari;

    @FindBy (xpath = "(//div[@class='row'])[2]")
    public List<WebElement> anaBolumAsiBasliklari;




}
