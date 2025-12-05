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

    @FindBy(xpath = "//div[@id='logo']//img")
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

    @FindBy(xpath = "//h1[contains(text(),'Database')]")
    public WebElement dashboardTitle;
    @FindBy(xpath = "//span[text()='Medicines']/ancestor::li[contains(@class,'open')]//ul[@class='sub-menu']/li")
    public List<WebElement> medicinesSubmenuList;
    @FindBy(xpath = "//ul[@class='sub-menu']//a[contains(text(),'Medicines')]")
    public WebElement submenuMedicines;
    @FindBy(xpath = "//div[@class='card-body']//table//tbody//tr")
    public List<WebElement> medicinesTableRows;

    @FindBy(xpath = "//div[@class='card-title' and text()='Table Medicines']")
    public WebElement tableMedicinesTitle;

    @FindBy(xpath = "//table[contains(@class,'table')]")
    public WebElement medicinesTableArea;

    @FindBy(id = "search-table")
    public WebElement medicinesSearchBox;


    @FindBy(xpath = "//table[contains(@class,'table')]//tbody/tr[1]/td[2]")
    public WebElement firstMedicineName;
    @FindBy(xpath = "(//a[contains(@class,'fa-edit')])[1]")
    public WebElement firstMedicineEditButton;

    @FindBy(id = "Title_en")
    public WebElement medicineTitleInput;

    @FindBy(id = "body_en")
    public WebElement medicineContentInput;

    @FindBy(xpath = "//button[contains(@class,'fa-save')]")
    public WebElement medicineSaveButton;
    @FindBy(id = "Title_en")
    public WebElement editMedicineTitle;
    @FindBy(id = "body_en")
    public WebElement editMedicineContent;
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement saveMedicineButton;


    @FindBy(id = "Title_en")
    public WebElement createMedicineTitleInput;

    @FindBy(id = "body_en")
    public WebElement createMedicineContentInput;

    @FindBy(xpath = "//button[@type='submit' and contains(text(),'Save Medicines')]")
    public WebElement createMedicineSaveButton;

    @FindBy(xpath = "//h3[contains(text(),'Add Medicines')]")
    public WebElement addMedicinesPageTitle;
    @FindBy(xpath = "//a[contains(@href,'Dashboard/Instagrams/create')]")
    public WebElement createMedicinesLink;
































}
