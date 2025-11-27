package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class DogukanPage {

    public DogukanPage (){
            PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//*[@class='fas fa-sign-in-alt']")
    public WebElement signInButton;

    @FindBy(xpath = "//*[@class='text-info small']")
    public WebElement forgotMyPasswordButton;

    @FindBy(xpath = "//*[@id='email']")
    public WebElement emailBox;

    @FindBy(xpath = "//*[@class='btn btn-primary']")
    public WebElement emailSendButton;

    @FindBy(xpath = "//*[@class='invalid-feedback']")
    public WebElement yanlisEmailMessage;
}
