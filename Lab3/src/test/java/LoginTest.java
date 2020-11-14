import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Credentials;
import utils.DriverProvider;
import utils.Utils;
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

    @Test
    public void negativeLoginTest() {
        WebDriver driver = DriverProvider.getDriver();
        LoginPage loginPage = new LoginPage(driver, true);
        // username can only be 5-10 letters long so 11 letter long login will always fail
        loginPage.loginInput.sendKeys(Utils.getRandomAlphaNumericSequence(11));
        loginPage.passwordInput.sendKeys(Utils.getRandomAlphaNumericSequence(10));
        loginPage.submitButton.click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(loginPage.errorBox));
        Assertions.assertTrue(loginPage.errorBox.isDisplayed());
        driver.quit();
    }
}
