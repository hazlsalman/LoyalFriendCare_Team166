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
    public WebElement appointmentBookingYazisi ;

    @FindBy(xpath = "//a[text() =' Sign Out']")
    public WebElement signOutButonu;








}
