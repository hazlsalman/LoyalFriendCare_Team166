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

        @FindBy (xpath = "//ul[@style='display: block;']")
        public WebElement petsAdsenseAltSekmeleri;

    @FindBy (xpath = "//li/a[.='Pets adsense']")
    public WebElement petsAdsenseAltSekmesi1;

    @FindBy (xpath = "//li/a[.='Create Pets adsense']")
    public WebElement petsAdsenseAltSekmesi2;

    @FindBy (xpath = "//div[@class='card-title']")
    public WebElement petManagementYazisi;

    @FindBy (xpath = "//input[@id='search-table']")
    public WebElement petSearchInput;

    @FindBy (xpath = "//tr[@class='odd']")
    public WebElement searchResultIlkRow;

    @FindBy (xpath = "(//td/a/span[.='Edit'])[1]")
    public WebElement searchResultEditButton;

    @FindBy (xpath = "//*[.='These are Default Pets Location']")
    public WebElement afterEditButtonPressResult;

    @FindBy (xpath = "(//input[@class='form-control '])[1]")
    public WebElement afterEditButtonPetNameInput1;

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement afterEditButtonClickSAVEButton;

    @FindBy (xpath = "//div[@class='alert alert-danger']")
    public WebElement afterSaveErrorMessage;

    @FindBy (xpath = "(//input[@class='form-control '])[1]")
    public WebElement petCreateInput1;

    @FindBy (xpath = "(//input[@class='form-control '])[2]")
    public WebElement petCreateInput2;

    @FindBy(xpath = "//span[@class='switchery switchery-default']")
    public WebElement controlPetActivePetButton;

    @FindBy (xpath = "(//button[@class='btn btn-primary btn-sm  btn-rounded m-r-10'])[1]")
    public WebElement typeCodeImageButton;

    @FindBy (xpath = "(//input[@class='form-control '])[3]")
    public WebElement petCreateImageUrl;

    @FindBy (xpath = "(//button[@class='btn btn-primary btn-sm  btn-rounded m-r-10'])[2]")
    public WebElement typeCodeAdsenseButton;

    @FindBy (xpath = "//div[@class='note-editable']")
    public WebElement petCreateAdsenseTextInput;

    @FindBy (xpath = "//span[.='AdSense Store successfully.']")
    public WebElement successMessageNewPetAdded;

    @FindBy (xpath = "(//span[.='Delete'])[1]")
    public WebElement adsenseDeleteButton;

    @FindBy (xpath = "//span[.='AdSense deleted successfully']")
    public WebElement addSenseSuccessfullyDeleteMessage;

}
