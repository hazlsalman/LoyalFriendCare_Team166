package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class ArdaPage {

    public ArdaPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    // ***** HEADER LOCATORS ***** //

    @FindBy(xpath = "//img[contains(@class,'logo_normal')]")
    public WebElement logo;

    @FindBy(xpath = "//a[@class='btn_add' and contains(text(),'Sign In')]")
    public WebElement signInButton;

    @FindBy(xpath = "//a[contains(@href,'register') and contains(@class,'btn_add')]")
    public WebElement signUpButton;


    // SADECE ÜST MENÜDEKİ GÖRÜNÜR ITEM'LAR
    @FindBy(xpath = "//nav[@id='menu']/ul/li/a")
    public List<WebElement> headerMenuItems;

    @FindBy(xpath = "//nav[@id='menu']//a[text()='Home']")
    public WebElement homeLink;

    @FindBy(xpath = "//nav[@id='menu']//a[text()='About Us']")
    public WebElement aboutUsLink;

    @FindBy(xpath = "//nav[@id='menu']//a[text()='Departments']")
    public WebElement departmentsLink;

    @FindBy(xpath = "//nav[@id='menu']//a[text()='Doctors']")
    public WebElement doctorsLink;

    @FindBy(xpath = "//nav[@id='menu']//a[text()='Medicines']")
    public WebElement medicinesLink;

    @FindBy(xpath = "//nav[@id='menu']//a[text()='Vaccinations']")
    public WebElement vaccinationsLink;

    @FindBy(xpath = "//header[contains(@class,'header')]")
    public WebElement headerContainer;

    @FindBy(xpath = "//a[contains(@href,'/admin')]")
    public WebElement headerUserName;

    @FindBy(xpath = "//input[@id='email']")
    public WebElement emailBox;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement passwordBox;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginSubmitButton;

    @FindBy(xpath = "//a[contains(@href,'logout')]")
    public WebElement signOutButton;

    @FindBy(xpath = "//button[@type='submit' and contains(text(),'Login')]")
    public WebElement loginPageLoginButton;

    @FindBy(xpath = "//span[@class='title' and text()='Medicines']")
    public WebElement sidebarMedicines;
    @FindBy(xpath = "//nav[@class='page-sidebar']")
    public WebElement sidebarMenu;




}
