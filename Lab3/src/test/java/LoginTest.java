import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import utils.Credentials;
import utils.DriverProvider;
import webpages.HostingPage;
import webpages.LoginPage;

public class LoginTest {
    @Test
    public void loginTest() {
        WebDriver driver = DriverProvider.getDriver();
        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.login();
        HostingPage hostingPage = new HostingPage(driver, false);
        Assertions.assertEquals(Credentials.login, hostingPage.username.getText());
        driver.quit();
    }
}
