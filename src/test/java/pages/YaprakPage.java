package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class YaprakPage {

    public YaprakPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }


    // Ana Sayfadan Kullanıcı bilgileri ile Siteye giriş yapmak için
    @FindBy(xpath = "(//a[@class='btn_add'])[1]")
    public WebElement anasayfaSigninButonu;
    @FindBy(xpath = "//input[@name='email']")
    public WebElement anasayfaEmailKutusu;
    @FindBy(xpath = "//input[@name='password']")
    public WebElement anasayfaPasswordKutusu;
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement anasayfaSigninGirisButonu;
    // 1. Ana Departmanlar Listesi
    @FindBy(xpath = "//label[@class='container_check']/a")
    public List<WebElement> departmanListesi;
    // 2. Beds (Yatak) Alt Linkleri
    @FindBy(xpath = "//a[contains(@href,'/Beds/')]")
    public List<WebElement> bedsLinks;
    // 3. Detay Sayfasındaki Bilgiler (Doctor, Price vs.)
    @FindBy(xpath = "//ul/li")
    public List<WebElement> detayBilgiListesi;
    @FindBy(xpath = "//input[@value='Appointment Booking']")
    public WebElement bookingButton;


    //US_014
    @FindBy(xpath = "//*[@id='menu']/ul/li[3]/span/a")
    public WebElement anasayfaDepartmentsMenu;
    @FindBy(id = "Date")
    public WebElement appointmentDateInput;
    @FindBy(id = "serial")
    public WebElement appointmentPhoneBox;
    @FindBy(name = "problem")
    public WebElement appointmentMessageArea;
    @FindBy(xpath = "(//div[@class='nice-select wide'])[1]")
    public WebElement appointmentDepartmentDD;

    // dropdown
    @FindBy(xpath = "(//div[@class='nice-select wide'])[2]")
    public WebElement appointmentDoctorDD;
    @FindBy(id = "submit-contact-detail")
    public WebElement appointmentSubmitBtn;
    @FindBy(xpath = "//div[@class='alert alert-success']")
    public WebElement appointmentSuccessMessage;
    @FindBy(xpath = "//div[@class='alert alert-success']")
    public WebElement successMessage;
    @FindBy(xpath = "//h3[text()='Vaccinations Room']")
    public WebElement vaccinationsRoomLink;
    @FindBy(xpath = "//li[text()='Dermatology']")
    public WebElement dermatologyOption;
    @FindBy(xpath = "//li[contains(text(),'Isabella Wong')]")
    public WebElement doctorIsabellaOption;
    @FindBy(xpath = "//div[contains(@class,'nice-select')]//li[text()='Vaccinations']")
    public WebElement departmentOptionVaccinations;
    @FindBy(xpath = "//div[contains(@class,'nice-select')]//li[contains(text(),'Dr. Isabella Wong')]")
    public WebElement doctorOptionIsabella;


    // US_042
    @FindBy(xpath = "//a[@class='btn_add']")
    public WebElement adminPanelLinki;
    @FindBy(xpath = "//button[@class='profile-dropdown-toggle']")
    public WebElement profilMenuButonu;
    @FindBy(xpath = "//*[@class='profile-dropdown-toggle']")
    public WebElement profilDropdownMenu;
    @FindBy(css = ".profile-dropdown-toggle img")
    public WebElement profilResmi;
    @FindBy(xpath = "//*[@class='dropdown-item']")
    public WebElement profilSettingsButton;
    @FindBy(xpath = "//*[@class='pg-outdent']")
    public WebElement profilEditProfileButton;
    @FindBy(xpath = "//*[@class='pull-left']")
    public WebElement profilLogoutButton;
    @FindBy(id = "button")


    //US_043
    public WebElement dontChangeImageCheckbox;
    @FindBy(name = "Phone")
    public WebElement editPhoneBox;
    @FindBy(xpath = "//input[@id='password']")
    public WebElement editPasswordBox;
    @FindBy(xpath = "//input[@id='password_confirmation']")
    public WebElement editPasswordConfirmBox;
    @FindBy(xpath = "//input[@id='email']")
    public WebElement editEmailBox;
    @FindBy(xpath = "//button[contains(@class,'btn-success') and @type='submit']")
    public WebElement saveButton;
    @FindBy(xpath = "//input[@type='file']")
    public WebElement filesToUpload;
    @FindBy(css = "#dropzone div.dz-preview.dz-processing.dz-image-preview.dz-success div.dz-details img")
    public WebElement yuklenenDosyaImg;
    @FindBy(xpath = "//*[contains(text(), 'deneme.txt')]")
    public WebElement uploadedTxtFileName;

}





