package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class WadoudPage {

        public WadoudPage() { PageFactory.initElements(Driver.getDriver(), this); }

        @FindBy(xpath = "(//*[@class='btn_add'])[1]")
        public WebElement mainSignInButton;

        @FindBy(xpath = "(//*[@class='btn_add'])[2]")
        public WebElement mainSignOutButton;

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

        @FindBy(xpath = "//*[@*='fab fa-facebook-square']")
        public WebElement facebookLogo;

        @FindBy (xpath = "(//span[@class='title'])[5]")
        public WebElement menuDepartmentButton;

        @FindBy (xpath = "(//span[@class='title'])[6]")
        public WebElement menuDoctorsButton;

        @FindBy (xpath = "(//span[@class='title'])[7]")
        public WebElement menuMedicineButton;

        @FindBy (xpath = "(//span[@class='title'])[8]")
        public WebElement menuAdsenseButton;

        @FindBy (xpath = "//div[@class='pull-bottom bottom-left bottom-right padding-25']")
        public List<WebElement> uiSekmeSayisi;

        @FindBy (xpath = "//span[@class='title']")
        public List<WebElement> uiMenuSayisi;


}
