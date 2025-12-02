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
}
