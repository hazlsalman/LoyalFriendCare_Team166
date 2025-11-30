package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import java.util.List;

public class HakimPage {

    public HakimPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    // ANASAYFA - SIGN UP BUTONU
    @FindBy(xpath = "//a[text()=' Sign Up']")
    public WebElement homePageSignUpButton;

    // REGISTER SAYFASI - USERNAME TEXTBOX
    @FindBy(id = "name")
    public WebElement usernameBox;

    // REGISTER SAYFASI - EMAIL TEXTBOX
    @FindBy(id = "email")
    public WebElement emailBox;

    // REGISTER SAYFASI - PASSWORD TEXTBOX
    @FindBy(id = "password")
    public WebElement passwordBox;

    // REGISTER SAYFASI - CONFIRM PASSWORD TEXTBOX
    @FindBy(id = "password-confirm")
    public WebElement confirmPasswordBox;

    // REGISTER SAYFASI - custom validation mesajı
    @FindBy(xpath = "//strong[contains(text(),'The password confirmation does not match')]")
    public WebElement passwordNotMatchText;

    // REGISTER SAYFASI - SIGN UP BUTONU
    @FindBy(xpath = "//button[text()='Sign Up']")
    public WebElement registerPageSignUpButton;

    // ANASAYFA - SIGN IN BUTONU
    @FindBy(xpath = "//a[contains(@class,'btn_add') and contains(text(),'Sign In')]")
    public WebElement homePageSignInButton;

    // LOGİN SAYFASI - EMAİL TEXTBOX
    @FindBy(id = "email")
    public WebElement loginEmailBox;

    // LOGİN SAYFASI - PASSWORD TEXTBOX
    @FindBy(id = "password")
    public WebElement loginPasswordBox;

    // LOGİN SAYFASI - SIGN IN BUTONU
    @FindBy(xpath = "//button[text()='Sign In']")
    public WebElement loginSignInButton;

    // LOGİN SAYFASI - HATALI GİRİŞ UYARISI
    @FindBy(xpath = "//*[contains(text(),'These credentials do not match our records')]")
    public WebElement loginPageErrorMessage;

    // ADMIN SAYFASI - AYARLAR SİMGESİ
    @FindBy(css = "i.fas.fa-cog")
    public WebElement SettingsButton;

    // ADMIN SAYFASI - Ana menü (hover için)
    @FindBy(xpath = "//li[a/span[text()='Bed managers']]")
    public WebElement bedManagersParent;

    // ADMIN SAYFASI - 'Bed managers' alt menüsü
    @FindBy(xpath = "//li[a/span[text()='Bed managers']]/ul[@class='sub-menu']//a[text()='Bed managers']")
    public WebElement subBedManagers;

    // ADMIN SAYFASI - 'Create Bed managers' alt menüsü
    @FindBy(xpath = "//li[a/span[text()='Bed managers']]/ul[@class='sub-menu']//a[text()='Create Bed managers']")
    public WebElement createBedManagers;

    // DASHBOARD SAYFASI - Search kutusu
    @FindBy(id = "search-table")
    public WebElement SearchBox;

    // BED MANAGERS EDIT SAYFASI - TITLE
    @FindBy(id = "Title_en")
    public WebElement bedManagerTitle;

    // BED MANAGERS EDIT SAYFASI - CONTENT
    @FindBy(css = "div.note-editable[contenteditable='true']")
    public WebElement bedManagerContent;

    // BED MANAGERS EDIT SAYFASI - PRICE
    @FindBy(css = "input[placeholder='Bed Price']")
    public WebElement bedPrice;

    //  BED MANAGERS EDIT SAYFASI DROPDOWN MENÜLER - DEPARTMENT
    @FindBy(css = "span#select2-category_id-container")
    public WebElement departmentDropdown;

    @FindBy(css = "ul#select2-category_id-results li")
    public List<WebElement> departmentOptions;

    //  BED MANAGERS EDIT SAYFASI DROPDOWN MENÜLER - CREATED DOCTOR
    @FindBy(css = "span#select2-author_id-container")
    public WebElement doctorDropdown;

    @FindBy(css = "ul#select2-author_id-results li")
    public List<WebElement> doctorOptions;

    //  BED MANAGERS EDIT SAYFASI DROPDOWN MENÜLER - MEDICINES BY
    @FindBy(css = "span#select2-Instagram_id-container")
    public WebElement medicinesDropdown;

    @FindBy(css = "ul#select2-Instagram_id-results li")
    public List<WebElement> medicinesOptions;

    // BED MANAGERS SAYFASI - SAVE BUTONU
    @FindBy(css = "button.btn.btn-success.btn-cons.btn-animated.from-left.fa.fa-save.pull-right")
    public WebElement bedManagersSaveButton;

    // DASHBOARD SAYFASI - SUCCESS MESSAGE
    @FindBy(xpath = "//span[text()='Tracks Updated successfully.']")
    public WebElement successMessage;

}
