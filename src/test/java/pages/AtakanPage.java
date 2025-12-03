package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class AtakanPage {
    public AtakanPage(){
        PageFactory.initElements(Driver.getDriver(),this);}

    @FindBy(xpath = "(//a[@class='btn_add'])[1]")
    public WebElement signInButonu;

    @FindBy(id = "email")
    public WebElement email;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement password;

    @FindBy(xpath = "//*[@type='submit']")
    public WebElement loginSignInButonu;

    @FindBy(xpath = "//*[@class='text-info small']")
    public WebElement forgotPassword;

    @FindBy(xpath = "//*[@*='invalid-feedback']")
    public WebElement loginHataMesajı;

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    public WebElement passwordResetButonu;

    @FindBy(xpath = "(//a[@class='btn_add'])[1]")
    public WebElement adminAdıButonu;

}
