package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class EbruPage {
    public EbruPage(){
        PageFactory.initElements(Driver.getDriver(),this);}

    @FindBy(xpath ="(//a[@class='btn_add'])[1]")
    public WebElement HomePageSignButonu;

    @FindBy(id = "email")
    public WebElement email;

    @FindBy(id = "password")
    public WebElement password;


    @FindBy(xpath = "//*[@type='submit']")
    public WebElement loginSignInButonu;

    @FindBy(xpath = "//footer[@class='plus_border']")
    public WebElement footerArea;

    @FindBy(xpath = " /html/body/div[1]/div[1]/footer/div/div[1]/div[1]/h3/a/img")
    public WebElement logo;

    @FindBy(xpath = "/html/body/div[1]/div[1]/footer/div/div[1]/div[1]/div/p")
    public WebElement substitle;

    @FindBy(xpath = "/html/body/div[1]/div[1]/footer/div/div[1]/div[2]/h3")
    public WebElement departmentsBasligi;

    @FindBy(xpath = "/html/body/div[1]/div[1]/footer/div/div[1]/div[2]")
    public List<WebElement> departmentsMetinleri;

    // "Follow Us" başlığı
    @FindBy(xpath = "/html/body/div[1]/div[1]/footer/div/div[1]/div[3]/h3")
    public WebElement followUsBasligi;

    // Follow Us altındaki tüm sosyal medya ikon/linkleri
    @FindBy(xpath = "/html/body/div[1]/div[1]/footer/div/div[1]/div[3]/div/div/ul")
    public List<WebElement> followUsIconListesi;


    // Facebook icon
    @FindBy(xpath = "//a[contains(@href,'facebook')]")
    public WebElement facebookIcon;

    @FindBy(xpath = "//a[@href='https://twitter.com']")
    public WebElement twitterIcon;


    // Youtube icon
    @FindBy(xpath = "//a[contains(@href,'youtube')]")
    public WebElement youtubeIcon;

    // Instagram icon
    @FindBy(xpath = "//a[contains(@href,'instagram')]")
    public WebElement instagramIcon;

    // Contacts başlığı
    @FindBy(xpath = "/html/body/div[1]/div[1]/footer/div/div[1]/div[4]/h3")
    public WebElement contactsBasligi;

    // Contacts altındaki adres (ilk satır)
    @FindBy(xpath = "/html/body/div[1]/div[1]/footer/div/div[1]/div[4]/div/ul/li[1]")
    public WebElement contactsAdres;

    // Telefon linki
    @FindBy(xpath = "/html/body/div[1]/div[1]/footer/div/div[1]/div[4]/div/ul/li[2]")
    public WebElement phoneLink;

    // E-posta linki
    @FindBy(xpath = "/html/body/div[1]/div[1]/footer/div/div[1]/div[4]/div/ul/li[3]/a")
    public WebElement emailLink;

    // Footer içerisindeki tüm linkler
    @FindBy(xpath = "//html/body/div[1]/div[1]/footer/div")
    public List<WebElement> tumFooterLinkleri;

    // Footer’daki ana başlıklar (Departments, Follow Us, Contacts)
    @FindBy(xpath = "//footer//h3")
    public List<WebElement> footerBasliklari;
}
