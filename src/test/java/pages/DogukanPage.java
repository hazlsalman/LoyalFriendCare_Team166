package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import utilities.Driver;

import java.util.List;

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

    @FindBy(xpath = "//input[@name='password']")
    public WebElement loginPagePasswordBox;

    @FindBy(xpath = "//input[@name='email']")
    public WebElement loginPageEmailBox;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginPageSignInButton;

    @FindBy(xpath = "(//*[@class='btn_add'])[1]")
    public WebElement profileSettingsButton;

    @FindBy(xpath = "(//*[*='Doctors'])[1]")
    public WebElement dropDownMenuDoctorsButton;

    @FindBy(xpath = "//*[*='Create Doctors']")
    public WebElement createDoctorsMenuButton;

    @FindBy(xpath = "(//*[*='Doctors'])[2]")
    public WebElement doctorsSubMenuButton;

    @FindBy(xpath = "//tr[@role='row']")
    public List<WebElement> doctorsInformationTableList;

    @FindBy(xpath = "//*[.='Dr. Alejandro Martinez']")
    public WebElement doctorMartinexText;

    @FindBy(xpath = "//*[@class='form-control pull-right']")
    public WebElement doctorSearchingBox;

    @FindBy(xpath = "//*[.='Dr. Marcus Rodriguez']")
    public WebElement anotherDoctorText;

    @FindBy(xpath = "//tr[@role='row']//*[@class='v-align-middle semi-bold']")
    public List<WebElement> doctorsNameList;

    @FindBy(xpath = "(//input[@class='form-control '])[1]")
    public WebElement doctorsTitleBox;

    @FindBy(xpath = "(//input[@class='form-control '])[2]")
    public WebElement doctorsContentBox;

    @FindBy(xpath = "//input[@type='file']")
    public WebElement dropFileBox;

    @FindBy(xpath = "//button[@class='btn btn-success btn-cons btn-animated from-left fa fa-save pull-right']")
    public WebElement doctorsSaveButton;

    @FindBy(xpath = "//div[@class='form-group form-group-default required focused']")
    public WebElement doctorsSaveNegativeMessage;

    @FindBy(xpath = "//div[@class='dz-success-mark']")
    public WebElement uploadSuccessMark;

    @FindBy(xpath = "//img[@alt='test.jpeg']")
    public WebElement uploadedImage;

    @FindBy(xpath = "//span[.='Doctors Store successfully.']")
    public WebElement doctorsCreateSuccessMessage;

    @FindBy(xpath = "//td[*='Ali Veli']")
    public WebElement newDoctorNameText;

    @FindBy(xpath = "//*[@class='btn btn-complete btn-cons btn-animated from-left fa fa-edit']")
    public WebElement doctorEditButton;

    @FindBy(xpath = "//*[@class='btn btn-danger btn-cons btn-animated from-top fa  fa-remove']")
    public WebElement doctorDeleteButton;

    @FindBy(xpath = "//span[.='Doctors deleted successfully']")
    public WebElement doctorDeleteSuccessMessage;

    @FindBy(xpath = "(//textarea[@class='form-control'])[1]")
    public WebElement doctorEditContentBox;

    @FindBy(xpath = "//*[@class='dz-default dz-message']")
    public WebElement dropFileBoxMessageArea;
}
