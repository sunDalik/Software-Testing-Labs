import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverProvider;
import webpages.LoginPage;
import webpages.SitesPage;

public class SitesTest {
    @Test
    public void createSiteTest() {
        WebDriver driver = DriverProvider.getDriver();
        new LoginPage(driver, true).loginAndWait();

        SitesPage sitesPage = new SitesPage(driver, true);
        int sitesTableRowsCount = driver.findElements(By.xpath("//div[@id='catalog_new']//table/tbody/tr")).size();
        String dirName = sitesPage.createSite();

        // wait until new entry is put in the sites table
        new WebDriverWait(driver, 10).until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@id='catalog_new']//table/tbody/tr"), sitesTableRowsCount + 1));

        // check that our new site is put in the first (ignore the header row though) row of the sites table
        WebElement newSiteCell = driver.findElement(By.xpath("//div[@id='catalog_new']//table/tbody/tr[2]/td[1]/span"));
        Assertions.assertEquals(dirName, newSiteCell.getText());

        sitesPage.deleteSite(dirName);
        new WebDriverWait(driver, 10).until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@id='catalog_new']//table/tbody/tr"), sitesTableRowsCount));
        driver.quit();
    }
}
