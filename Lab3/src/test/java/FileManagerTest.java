import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverProvider;
import utils.FileStruct;
import webpages.FileManagerPage;
import webpages.LoginPage;

public class FileManagerTest {
    @RepeatedTest(20)
    public void createFileTest() {
        WebDriver driver = DriverProvider.getDriver();
        new LoginPage(driver, true).loginAndWait();

        FileManagerPage fileManPage = new FileManagerPage(driver, true);
        int filesTableRowsCount = driver.findElements(By.xpath("//div[@id='main_table']//table/tbody/tr")).size();
        FileStruct file = fileManPage.createRandomFile();

        // wait until new entry is put in the sites table
        new WebDriverWait(driver, 10).until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@id='main_table']//table/tbody/tr"), filesTableRowsCount + 1));

        // check that our new site is put in the first (ignore the header row though) row of the sites table
        WebElement newFileCell = driver.findElement(By.xpath("//div[@id='main_table']//table/tbody/tr/td[2]/div[normalize-space()='" + file.name + "']"));
        Assertions.assertEquals(file.name, newFileCell.getText());

        Assertions.assertEquals(file.contents, fileManPage.getFileContents(file.name));

        fileManPage.deleteFile(file.name);
        new WebDriverWait(driver, 10).until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@id='main_table']//table/tbody/tr"), filesTableRowsCount));
        driver.quit();
    }
}
