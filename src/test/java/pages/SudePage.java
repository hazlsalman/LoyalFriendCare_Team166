package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;

import java.time.Duration;

public class SudePage {



    public SudePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }



    @FindBy(xpath = "//img[@class='logo_normal']")
    public WebElement logo;

    @FindBy(xpath = "(//a[@class='btn_add'])[1]")
    public WebElement signInButton;

    @FindBy(xpath = "(//a[@class='btn_add'])[2]")
    public WebElement signUpButton;






}
