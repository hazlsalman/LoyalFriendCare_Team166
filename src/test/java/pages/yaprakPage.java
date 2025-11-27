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


    // Departments Menu
    @FindBy(xpath = "//*[@id='menu']/ul/li[3]/span/a")
    public WebElement anasayfaDepartmentsMenu;










}
