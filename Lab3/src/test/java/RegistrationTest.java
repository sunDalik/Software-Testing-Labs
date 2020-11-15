import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import utils.DriverProvider;
import utils.Utils;
import webpages.HostingPage;
import webpages.ProfilePage;
import webpages.ServicesHostingPage;

public class RegistrationTest {
    @Test
    public void registrationTest() {
        WebDriver driver = DriverProvider.getDriver();
        ServicesHostingPage servicesHostingPage = new ServicesHostingPage(driver, true);
        String name = Utils.getRandomAlphabetSequence(10);
        String email = Utils.getRandomEmail(8).toLowerCase();
        String username = Utils.getRandomAlphabetSequence(10).toLowerCase();
        servicesHostingPage.registerUser(name, email, username);

        HostingPage hostingPage = new HostingPage(driver, false);
        Assertions.assertEquals(username, hostingPage.username.getText());

        ProfilePage profilePage = new ProfilePage(driver, true);
        Assertions.assertEquals(name, profilePage.name.getText());
        Assertions.assertEquals(email, profilePage.email.getText());
        driver.quit();
    }

    @Test
    public void invalidUsernameRegistrationTest() {
        WebDriver driver = DriverProvider.getDriver();
        ServicesHostingPage servicesHostingPage = new ServicesHostingPage(driver, true);
        String name = Utils.getRandomAlphabetSequence(10);
        String email = Utils.getRandomEmail(8);
        // username must be 5-10 letters long
        String username = Utils.getRandomAlphabetSequence(4);
        servicesHostingPage.registerUser(name, email, username);

        Assertions.assertTrue(servicesHostingPage.isAlertPresent());
        driver.quit();
    }

    @Test
    public void invalidEmailRegistrationTest() {
        WebDriver driver = DriverProvider.getDriver();
        ServicesHostingPage servicesHostingPage = new ServicesHostingPage(driver, true);
        String name = Utils.getRandomAlphabetSequence(10);
        // it shouldn't allow registering emails of wrong format
        String email = Utils.getRandomAlphaNumericSequence(10);
        String username = Utils.getRandomAlphabetSequence(10).toLowerCase();
        servicesHostingPage.registerUser(name, email, username);

        Assertions.assertTrue(servicesHostingPage.isAlertPresent());
        driver.quit();
    }
}