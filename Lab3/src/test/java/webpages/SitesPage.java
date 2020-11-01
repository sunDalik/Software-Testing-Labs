package webpages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

public class SitesPage {
    WebDriver driver;

    @FindBy(xpath = "//div[@id='create_site_block']")
    public WebElement createSiteButton;

    public SitesPage(WebDriver driver, boolean gotoPage) {
        if (gotoPage) driver.get("https://hosting.timeweb.ru/sites");
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String createSite() {
        createSiteButton.click();

        WebElement siteDirInput = driver.findElement(By.xpath("//input[@id='site_dir']"));
        WebElement createSiteButton2 = driver.findElement(By.xpath("//button[@id='save_button']"));

        String dirName = "";

        while (true) {
            dirName = Utils.getRandomAlphaNumericSequence(10);
            siteDirInput.clear();
            siteDirInput.sendKeys(dirName);

            createSiteButton2.click();

            try {
                // if found alert then dirName is busy
                new WebDriverWait(driver, 5).until(ExpectedConditions.alertIsPresent());
                Alert alert = driver.switchTo().alert();
                alert.accept();
            } catch (Exception e) {
                // if no alert was found then we found a good name!
                break;
            }
        }

        return dirName;
    }

    public void deleteSite(String dirName) {
        WebElement dirNameSpan = driver.findElement(By.xpath("//span[normalize-space()='" + dirName + "']"));
        WebElement siteRow = dirNameSpan.findElement(By.xpath("./../.."));
        WebElement deleteButton = siteRow.findElement(By.xpath("./td[2]//span[contains(@class,'ui-link-btn-icon-delete')]"));
        deleteButton.click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
}
