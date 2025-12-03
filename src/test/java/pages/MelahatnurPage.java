package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

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



    @FindBy (xpath = "//*[@alt='Dr. Alejandro Martinez']")
    public WebElement doktorKartAlejandro;

    @FindBy (xpath = "//*[@alt='Wellness']")
    public WebElement bolumKartWelness;

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






}
