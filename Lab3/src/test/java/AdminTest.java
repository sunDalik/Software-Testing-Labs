import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverProvider;
import webpages.AdministratorsPage;
import webpages.LoginPage;

public class AdminTest {
    @Test
    public void adminCreationTest() {
        WebDriver driver = DriverProvider.getDriver();
        new LoginPage(driver, true).loginAndWait();

        AdministratorsPage adminPage = new AdministratorsPage(driver, true);
        int adminTableRowsCount = driver.findElements(By.xpath("//div[@id='w0']/table/tbody/tr")).size();
        String adminName = adminPage.createRandomAdmin();

        // wait until new entry is put in the admin table
        new WebDriverWait(driver, 10).until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@id='w0']/table/tbody/tr"), adminTableRowsCount + 1));

        // check that our new admin is put in the last row of the admin table
        WebElement newAdminNameCell = driver.findElement(By.xpath("//div[@id='w0']/table/tbody/tr[last()]/td[1]"));
        Assertions.assertEquals(adminName, newAdminNameCell.getText());
        driver.quit();
    }
}
