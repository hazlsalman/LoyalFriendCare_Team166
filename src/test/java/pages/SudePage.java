package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;

import java.time.Duration;
import java.util.List;

public class SudePage {



    public SudePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }


//anasayfa icin
    @FindBy(xpath = "//img[@class='logo_normal']")
    public WebElement logo;

    @FindBy(xpath = "(//a[@class='btn_add'])[1]")
    public WebElement signInButton;

    @FindBy(xpath = "(//a[@class='btn_add'])[2]")
    public WebElement signUpButton;

    @FindBy (xpath = "//*[@id='menu']/ul/li[3]/span/a")
    public WebElement anaDepartmanMenusu;

    @FindBy (xpath = "//input[@name='email']")
    public WebElement GecerliMail;

    @FindBy (xpath = "//input[@name='password']")
    public WebElement GecerliSifre;

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement SubmitButton;

    @FindBy (xpath = "//*[@id='menu']/ul/li[4]/span/a")
    public WebElement anaDoktorMenusu;


     //US_015 için
    @FindBy (xpath = "//div[@class='collapse show']")
    public List<WebElement> solTarafDepartmentList;
    @FindBy(xpath = "(//div[@class='row'])[2]")
    public List<WebElement> DepartmanlarınTamamı;
    @FindBy(xpath = "(//div[@class='row'])[1]")
    public WebElement DepartmanDetay;
@FindBy (xpath = "//input[@id='Date']")
    public WebElement AppointmentTarih;
@FindBy (xpath = "//input[@id='serial']")
    public WebElement AppointmentTelNo;
@FindBy(xpath = "(//div[@class='nice-select wide'])[1]")
    public WebElement DepartmanSecimi;
@FindBy(xpath = "(//div[@class='nice-select wide'])[2]")
    public WebElement DoktorSecimi;
@FindBy(xpath = "//textarea[@name='problem']")
public WebElement CreateMessage;
@FindBy(xpath = "//input[@type='submit']")
public WebElement BookingButton;
@FindBy (xpath = "//div[@role='alert']")
public WebElement RandevuOnayMesajı;





}
