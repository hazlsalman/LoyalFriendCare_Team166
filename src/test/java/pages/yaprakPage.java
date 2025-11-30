package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class yaprakPage {

    public  yaprakPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }


    // (Ana Sayfadan Kullanıcı bilgileri ile Siteye giriş yapmak için: )

    @FindBy(xpath = "(//a[@class='btn_add'])[1]")
    public WebElement anasayfaSigninButonu;

    @FindBy(xpath = "//input[@name='email']")
    public WebElement anasayfaEmailKutusu;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement anasayfaPasswordKutusu;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement anasayfaSigninGirisButonu;


    //US_0014

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

    @FindBy(xpath = "(//textarea[@placeholder='Create Message'])")
    public WebElement getAppointmentCreateMessage;

    @FindBy(xpath = "//div[@class='alert alert-success']")
    public WebElement appointmentSuccessMessage;

    @FindBy(xpath = "//div[@class='alert alert-success']")
    public WebElement successMessage;


    // US_042

    // Admin ana sayfadaki “admin.yaprak.ersan” butonu
    @FindBy(xpath = "//a[@class='btn_add']")
    public WebElement adminPanelLinki;

    // Admin panelde profil menü butonu (resimli)
    @FindBy(xpath = "//button[@class='profile-dropdown-toggle']")
    public WebElement profilMenuButonu;

    // Açılır profil menüsü (dropdown)
    @FindBy(xpath = "//*[@class='profile-dropdown-toggle']")
    public WebElement profilDropdownMenu;

    @FindBy(css = ".profile-dropdown-toggle img")
    public WebElement profilResmi;

    // Profil menü alt seçenekleri
    @FindBy(xpath = "//*[@class='dropdown-item']")
    public WebElement profilSettingsButton;

    @FindBy(xpath = "//*[@class='pg-outdent']")
    public WebElement profilEditProfileButton;

    @FindBy(xpath = "//*[@class='pull-left']")
    public WebElement profilLogoutButton;

    // Admin panel yönlendirme URL sabiti
    public static final String ADMIN_PANEL_URL = "https://qa.loyalfriendcare.com/en/admin";

    @FindBy(id = "button")
    public WebElement dontChangeImageCheckbox;


}





