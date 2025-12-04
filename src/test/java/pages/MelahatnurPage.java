package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.logging.XMLFormatter;

public class MelahatnurPage {

    public MelahatnurPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }


    @FindBy(xpath = "//a[contains(., 'Sign In')]")
    public WebElement signinButonu;

    @FindBy(xpath="//*[@id='email']")
    public WebElement mailKutusu ;

    @FindBy(xpath="//*[@id='password']")
    public WebElement passwordKutusu ;

    @FindBy(xpath = "//button[@*='submit']")
    public WebElement girisButonu;

    @FindBy(xpath = "//h3[.='Wellness']" )
    public WebElement wellnessButonu ;

    @FindBy(xpath = "//h5[text()='Appointment Booking']")
    public WebElement randevuAlmaButon ;

    @FindBy(xpath = "//a[text() =' Sign Out']")
    public WebElement signOutButonu;

    @FindBy(xpath = "//*[contains(text(),'admin.hazal.salman')]")
    public WebElement adminPaneliButonu;

    @FindBy(xpath = "//span[.='Roles']")
    public WebElement rolesElementi;

    @FindBy(xpath = "//a[.='Create Role']")
    public WebElement createRolesElementi;

    @FindBy(xpath = "//label[.='Display Name']")
    public WebElement displayNameElementi;

    @FindBy(xpath = "//div[contains(@class,'sidebar')]")
    public WebElement solMenu;

    @FindBy(xpath = "//a[.='Learn More at Bed managers']")
    public WebElement ozetBilgiButonu;

    @FindBy(xpath = "//a[@class='btn btn-tag btn-success btn-tag-rounded']")
    public WebElement ozetBilgiTiklanabilirligi;

    @FindBy (xpath = "//*[@alt='Dr. Alejandro Martinez']")
    public WebElement doktorKartAlejandro;

    @FindBy (xpath = "//span[.='Wellness']")
    public WebElement departmanSecmeWellness;

    @FindBy(id = "Date")
    public WebElement randevuTarihKutusu;

    @FindBy(xpath = "//input[@id='serial']")
    public WebElement randevuTelefonKutusu;

    @FindBy(xpath = "//textarea[@placeholder='Create Message']")
    public WebElement randevuMesajKutusu;

    @FindBy(xpath = "//div[contains(text(),'well-deserved success')]")
    public WebElement randevuOnayMesaji;

    @FindBy(xpath = "//input[@id='submit-contact-detail']")
    public WebElement  appointmentBookingButonu;

    @FindBy (xpath = "//li[.='NO Bed Departments']")
    public WebElement NoDepartments;

    @FindBy (xpath = "//li[.='Dr. Alejandro Martinez']")
    public WebElement doctorSecme;









}
