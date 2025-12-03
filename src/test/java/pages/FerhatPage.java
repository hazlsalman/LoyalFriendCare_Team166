package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class FerhatPage {

    public FerhatPage(){PageFactory.initElements(Driver.getDriver(),this);}

    @FindBy(xpath = "//a[@class='grid_item small']")
    public List<WebElement> tumLinklerList;

    @FindBy(xpath = "//h2[.='Beds Departments']")
    public WebElement bedsDepartments;

    @FindBy(xpath = "//a[@class='grid_item small']")
    public List<WebElement> bedsDepartmentsLinkList;

    @FindBy(xpath = "//li[i[contains(@class,'fa-money-bill-alt')]]/span")
    public WebElement fiyatBilgisi;

    @FindBy(xpath = "//section[@id='description']")
    public WebElement vaccinationParagraf;

    @FindBy(xpath = "(//a[@class='btn_add'])[1]")
    public WebElement signinButton;

    @FindBy(id = "email")
    public WebElement aramaKutusuId;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement aramaKutusuPassword;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement girisEkraniButton;

    @FindBy(xpath = "//div[@class='scroll-wrapper menu-items']")
    public WebElement userMenu;

    @FindBy(xpath = "//span[text()='Users']")
    public WebElement dropdown;

    @FindBy(xpath = "//a[text()='Users']")
    public WebElement usersDropdown;

    @FindBy(xpath = "//td[@class='v-align-middle semi-bold sorting_1']")
    public List<WebElement> kullaniciList;

    @FindBy(xpath = "//a[@rel='next']")
    public WebElement nextButton;

    @FindBy(xpath = "(//button[@type='submit'])[1]")
    public WebElement deleteButton;

    @FindBy(xpath = "//span[text()='User deleted successfully']")
    public WebElement basariText;
}
