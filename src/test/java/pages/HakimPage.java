package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HakimPage {

    public HakimPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    // ANASAYFADAKİ SIGN UP BUTONU
    @FindBy(xpath = "//a[text()=' Sign Up']")
    public WebElement homeSignUpButton;

    // REGISTER SAYFASI — USERNAME TEXTBOX
    @FindBy(id = "name")
    public WebElement usernameBox;

    // REGISTER SAYFASI — EMAIL TEXTBOX
    @FindBy(id = "email")
    public WebElement emailBox;

    // REGISTER SAYFASI — PASSWORD TEXTBOX
    @FindBy(id = "password")
    public WebElement passwordBox;

    // REGISTER SAYFASI — CONFIRM PASSWORD TEXTBOX
    @FindBy(id = "password-confirm")
    public WebElement confirmPasswordBox;

    // CONFIRM PASSWORD custom validation mesajı
    @FindBy(xpath = "//strong[contains(text(),'The password confirmation does not match')]")
    public WebElement passwordNotMatchText;

    // REGISTER SAYFASI — SIGN UP (SUBMIT) BUTONU
    @FindBy(xpath = "//button[text()='Sign Up']")
    public WebElement registersignUpButton;

}
