package webpages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    public String createRandomSite() {
        int sitesTableRowsCount = driver.findElements(By.xpath("//div[@id='catalog_new']//table/tbody/tr")).size();

        Utils.jsClick(driver,createSiteButton);

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

        // wait until new entry is put in the sites table
        new WebDriverWait(driver, 10).until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@id='catalog_new']//table/tbody/tr"), sitesTableRowsCount + 1));

        return dirName;
    }

    public void deleteSite(String dirName) {
        int sitesTableRowsCount = driver.findElements(By.xpath("//div[@id='catalog_new']//table/tbody/tr")).size();
        WebElement dirNameSpan = driver.findElement(By.xpath("//span[normalize-space()='" + dirName + "']"));
        WebElement siteRow = dirNameSpan.findElement(By.xpath("./../.."));
        WebElement deleteButton = siteRow.findElement(By.xpath("./td[2]//span[contains(@class,'ui-link-btn-icon-delete')]"));
        deleteButton.click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
        new WebDriverWait(driver, 10).until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@id='catalog_new']//table/tbody/tr"), sitesTableRowsCount));
    }

    public void bindDomainToSite(String domainName, String dirName) {
        WebElement domainBindingButton = getSiteNameCell(dirName).findElement(By.xpath("./../../td[3]/span[@class='ui-link-btn']"));
        domainBindingButton.click();

        WebElement listExpandButton = driver.findElement(By.xpath("//button[@id='free_domains-button']"));
        listExpandButton.click();

        WebElement domainEntry = driver.findElement(By.xpath("//ul[@id='free_domains-menu']//div[normalize-space()='" + domainName + "']"));
        domainEntry.click();

        WebElement saveButton = driver.findElement(By.xpath("//button[@id='save_button' and not(@disabled)]"));
        saveButton.click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='js-preloader']")));
    }

    public WebElement getSiteNameCell(String siteName) {
        return driver.findElement(By.xpath("//div[@id='catalog_new']//table/tbody/tr/td[1]/span[normalize-space()='" + siteName + "']"));
    }
}
