import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import utils.DriverProvider;
import utils.FileStruct;
import webpages.FileManagerPage;
import webpages.LoginPage;

public class FileManagerTest {
    @Test
    public void createFileTest() {
        WebDriver driver = DriverProvider.getDriver();
        new LoginPage(driver, true).loginAndWait();

        FileManagerPage fileManPage = new FileManagerPage(driver, true);
        FileStruct file = fileManPage.createRandomFile();

        Assertions.assertEquals(file.name, fileManPage.getFileNameCell(file.name).getText());
        Assertions.assertEquals(file.contents, fileManPage.getFileContents(file.name));

        fileManPage.deleteFile(file.name);
        driver.quit();
    }
}
