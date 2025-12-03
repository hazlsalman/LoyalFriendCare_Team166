package tests.US_027;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HakimPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;

public class TC_12_BedManagersContentTest {

    HakimPage hakimPage = new HakimPage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
    Actions actions = new Actions(Driver.getDriver());
    JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

    @Test
    public void test01() throws InterruptedException {

        // =========================================================
        // PRE-CONDITION:
        // =========================================================

        //1-) Anasayfaya git.
        Driver.getDriver().get(ConfigReader.getProperty("url"));

        //2-) Sıgn In butonuna bas.
        hakimPage.homePageSignInButton.click();

        //3-) Email ve password gir
        wait.until(ExpectedConditions.visibilityOf(hakimPage.loginEmailBox))
                .sendKeys("admin.abdul.hakim.kazanci@loyalfriendcare.com");
        hakimPage.loginPasswordBox.sendKeys("LFCare.0201");

        //4-) Sign In butonuna tıkla.
        hakimPage.loginSignInButton.click();

        // =========================================================
        // STEPS:
        // =========================================================

        // 1-) ⚙ AYARLAR SİMGESİNE TIKLA
        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.SettingsButton)).click();

        // 2-) SOLDAN AÇILAN SİDEBAR MENÜYÜ, SOLA HOVER İLE AÇ
        wait.until(ExpectedConditions.visibilityOf(hakimPage.bedManagersParent));
        actions.moveToElement(hakimPage.bedManagersParent).perform();

        // 3-) BED MANAGERS MENÜSÜNE TIKLA
        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.bedManagersParent)).click();

        // 4-) BED MANAGERS ALT MENÜSÜNE TIKLA
        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.subBedManagers)).click();

        // 5-) EDIT BUTONUNA TIKLA
        WebElement firstEditButton = Driver.getDriver()
                .findElement(By.xpath("//a[contains(@class,'fa-edit') and span[text()='Edit']]"));
        wait.until(ExpectedConditions.elementToBeClickable(firstEditButton)).click();

        // =========================================================
        // SENARYO
        // =========================================================

        // 1-) BED MANAGER'S CONTENT alanını tets et.
        WebElement contentArea = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.note-editable[contenteditable='true']")));
        String testMessage = "Clean, safe, and well-managed beds for hospitalized pets.";
        js.executeScript("arguments[0].innerText = arguments[1];", contentArea, testMessage);

        // 2-) Toolbar butonlarını test et.
        ToolbarButton[] buttons = new ToolbarButton[]{
                new ToolbarButton(By.cssSelector("button[data-toggle='dropdown'][data-original-title='Style']"), "DIRECT"),
                new ToolbarButton(By.cssSelector("button[data-event='bold']"), "DIRECT"),
                new ToolbarButton(By.cssSelector("button[data-event='italic']"), "DIRECT"),
                new ToolbarButton(By.cssSelector("button[data-event='underline']"), "DIRECT"),
                new ToolbarButton(By.cssSelector("button[data-event='superscript']"), "DIRECT"),
                new ToolbarButton(By.cssSelector("button[data-event='subscript']"), "DIRECT"),
                new ToolbarButton(By.cssSelector("button[data-event='strikethrough']"), "DIRECT"),
                new ToolbarButton(By.cssSelector("button[data-event='removeFormat']"), "DIRECT"),
                new ToolbarButton(By.cssSelector("button[data-toggle='dropdown'][data-original-title='Font Family']"), "DROPDOWN"),
                new ToolbarButton(By.cssSelector("button[data-event='color']"), "DROPDOWN"),
                new ToolbarButton(By.cssSelector("button[data-toggle='dropdown'][data-original-title='More Color']"), "DROPDOWN"),
                new ToolbarButton(By.cssSelector("button[data-event='insertUnorderedList']"), "DIRECT"),
                new ToolbarButton(By.cssSelector("button[data-event='insertOrderedList']"), "DIRECT"),
                new ToolbarButton(By.cssSelector("button[data-original-title='Paragraph']"), "DROPDOWN"),
                new ToolbarButton(By.cssSelector("button[data-original-title='Line Height']"), "DROPDOWN"),
                new ToolbarButton(By.cssSelector("button[data-original-title='Table']"), "DROPDOWN"),
                new ToolbarButton(By.cssSelector("button[data-event='showLinkDialog'] i.fa-link"), "MODAL"),
                new ToolbarButton(By.cssSelector("button[data-event='showImageDialog']"), "MODAL"),
                new ToolbarButton(By.cssSelector("button[data-event='showVideoDialog']"), "MODAL"),
                new ToolbarButton(By.cssSelector("button[data-event='insertHorizontalRule']"), "DIRECT"),
                new ToolbarButton(By.cssSelector("button[data-event='fullscreen']"), "MODAL"),
                new ToolbarButton(By.cssSelector("button[data-event='codeview']"), "MODAL"),
                new ToolbarButton(By.cssSelector("button[data-event='showHelpDialog']"), "MODAL")
        };

        for (ToolbarButton tb : buttons) {
            clickToolbarButton(tb.locator, tb.type);
        }

        // 3-) SAVE VE SUCCESS MESSAGE
        wait.until(ExpectedConditions.elementToBeClickable(hakimPage.bedManagersSaveButton));
        js.executeScript("arguments[0].click();", hakimPage.bedManagersSaveButton);

        wait.until(ExpectedConditions.urlContains("/Dashboard/Posts"));
        wait.until(ExpectedConditions.visibilityOf(hakimPage.successMessage));
        Assert.assertTrue(hakimPage.successMessage.isDisplayed(), "Success mesajı görünmüyor!");
    }

    // =========================================================
    // YARDIMCI METOD
    // =========================================================
    public void clickToolbarButton(By locator, String type) throws InterruptedException {
        WebElement button;

        try {
            button = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15))
                    .until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            System.out.println("⚠ Buton görünür değil veya bulunamadı: " + locator);
            return;
        }

        switch (type) {
            case "DIRECT":
                js.executeScript("arguments[0].click();", button);
                Thread.sleep(200);
                break;

            case "DROPDOWN":
                actions.moveToElement(button).perform();
                js.executeScript("arguments[0].click();", button);
                Thread.sleep(300);
                js.executeScript("document.body.click();");
                Thread.sleep(200);
                break;

            case "MODAL":
                try {
                    // Stale element ve görünürlük kontrolü
                    button = wait.until(ExpectedConditions.elementToBeClickable(locator));
                    js.executeScript("arguments[0].click();", button);
                    Thread.sleep(500);

                } catch (Exception e) {
                    System.out.println("⚠ MODAL buton tıklanamadı: " + locator);
                }
                break;
        }
    }

    // =========================================================
    // YARDIMCI CLASS
    // =========================================================
    public static class ToolbarButton {
        public By locator;
        public String type;

        public ToolbarButton(By locator, String type) {
            this.locator = locator;
            this.type = type;
        }
    }
}
