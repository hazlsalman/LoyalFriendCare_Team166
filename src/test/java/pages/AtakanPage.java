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

    @FindBy(xpath = "//span[text()='Departments']")
    public WebElement anaDepartmentsButonu;

    @FindBy(xpath = "//a[text()='Departments']")
    public WebElement departmentsButonu;

    @FindBy(xpath = "//a[text()='Create Departments']")
    public WebElement createDepartmentsButonu;

    @FindBy(xpath = "(//input[@type='number'])[1]")
    public WebElement parentDepartments;

    @FindBy(xpath = "(//input[@type='number'])[2]")
    public WebElement orderDepartments;

    @FindBy(name = "Title_en")
    public WebElement titleDepartments;

    @FindBy(xpath = "//span[@class='select2-selection__rendered']")
    public WebElement departmentsColor;

    @FindBy(xpath = "//li[contains(text(),'color info')]")
    public WebElement departmentsColor_colorInfo;

    @FindBy(xpath = "//li[contains(text(),'color success')]")
    public WebElement departmentsColor_colorSuccess;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement crateDepartmentsSaveButonu;

    @FindBy(xpath = "//ul[@class='menu-items scroll-content']")
    public WebElement dashboardMenu;

    @FindBy(xpath = "//div[contains(text(),'Create your Departments')]")
    public WebElement createYourDepartmentsYazisi;

    @FindBy(xpath = "//*[text()='Departments Store successfully.']")
    public WebElement createDepartmentsMesajı;

    @FindBy(id = "search-table")
    public WebElement searchButonu;

    @FindBy(xpath = "//a[@class='btn btn-complete btn-cons btn-animated from-left fa fa-edit']")
    public WebElement editButonu;

    @FindBy(xpath = "//div[@class='card-title']")
    public WebElement tableDepartments;

    @FindBy(id = "tableWithSearch_info")
    public WebElement listeSonucYazisi;

    @FindBy(xpath = "//button[@*='submit']")
    public WebElement editSaveDepartmentsButonu;

    @FindBy(xpath = "//*[text()='Departments Updated successfully.']")
    public WebElement editDepartmentsUpdatedYazisi;

    @FindBy(xpath = "//*[text()='Delete']")
    public WebElement deleteButonu;

    @FindBy(xpath = "//*[text()='Departments deleted successfully']")
    public WebElement deleteYazisi;

}
