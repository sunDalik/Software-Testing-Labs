import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import utils.DriverProvider;
import webpages.AdministratorsPage;
import webpages.LoginPage;

public class AdminTest {
    @Test
    public void adminCreationTest() {
        WebDriver driver = DriverProvider.getDriver();
        new LoginPage(driver, true).loginAndWait();
        AdministratorsPage adminPage = new AdministratorsPage(driver, true);
        adminPage.createRandomAdmin();
        driver.quit();
    }
}
