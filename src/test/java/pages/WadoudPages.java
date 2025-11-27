package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class WadoudPages {

        public WadoudPages() { PageFactory.initElements(Driver.getDriver(), this); }

        @FindBy(xpath = "(//*[@class='btn_add'])[1]")
        public WebElement mainSignInButton;

        @FindBy(id = "email")
        public WebElement loginPageSignInEmail;

        @FindBy(id = "password")
        public WebElement loginPageSignInPassword;

        @FindBy (xpath = "//*[@type='submit']")
        public WebElement  loginPageSignInButton;

        @FindBy(xpath = "//*[@class='fas fa-cog']")
        public WebElement loginText;

        @FindBy (className = "invalid-feedback")
        public WebElement invalidCredentialTextMessage;

        /* @FindBy()
        public WebElement a; **/

}
