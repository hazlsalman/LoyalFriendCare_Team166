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

    @FindBy (xpath = "//label[@class='container_check']")
    public List<WebElement> solListeAsiBasliklari;

    @FindBy (xpath = "(//div[@class='row'])[2]")
    public List<WebElement> anaBolumAsiBasliklari;

    @FindBy (xpath = "(//p)[1]")
    public WebElement kisaBilgiBolumu;

    @FindBy (xpath = "//h5[@class='d-inline']")
    public WebElement appointmentBooking;

    @FindBy (xpath = "//input[@id='Date']")
    public WebElement appointmentBookingTarih;

    @FindBy (xpath = "//input[@id='serial']")
    public WebElement appointmentBookingPhoneNumber;

    @FindBy (xpath = "(//div[@class='nice-select wide'])[1]")
    public WebElement departmentsDropdownMenu;

    @FindBy (xpath = "(//div[@class='nice-select wide'])[2]")
    public WebElement doctorsDropdownMenu;

    @FindBy (xpath = "//textarea[@name='problem']")
    public WebElement appointmentBookingCreateMessage;

    @FindBy (xpath = "//input[@type='submit']")
    public WebElement appointmentBookingButton;

    @FindBy (xpath = "//div[@role='alert']")
    public WebElement randevuMessage;

    @FindBy (xpath = "//li[text()='Dental Care']")
    public WebElement departmentsVaccinations;

    @FindBy (xpath = "//li[text()='Dr. Olivia Bennett']")
    public WebElement doctorsVaccinations;

    @FindBy (xpath = "(//a[text()='Medicines'])[3]")
    public WebElement headerMedicines;

    @FindBy(xpath = "//label[@class='container_check']")
    public List<WebElement> solListeIlacBasliklari;

    @FindBy (xpath = "//div[@class='wrapper']")
    public List<WebElement> anaBolumIlacBasliklari;

    @FindBy (xpath = "//li[text()='Wellness']")
    public WebElement departmentsMedicines;

    @FindBy (xpath = "//li[text()='Dr. Alejandro Martinez']")
    public WebElement doctorsMedicines;

}
