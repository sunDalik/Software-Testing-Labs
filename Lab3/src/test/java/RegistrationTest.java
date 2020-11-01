import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import utils.DriverProvider;
import utils.ProfileStruct;
import webpages.HostingPage;
import webpages.ProfilePage;
import webpages.ServicesHostingPage;

public class RegistrationTest {
    @Test
    public void testRegistration() {
        WebDriver driver = DriverProvider.getDriver();
        ServicesHostingPage servicesHostingPage = new ServicesHostingPage(driver, true);
        ProfileStruct createdProfile = servicesHostingPage.registerRandom();

        HostingPage hostingPage = new HostingPage(driver, false);
        Assertions.assertEquals(createdProfile.username, hostingPage.username.getText());

        ProfilePage profilePage = new ProfilePage(driver, true);
        Assertions.assertEquals(createdProfile.name, profilePage.name.getText());
        Assertions.assertEquals(createdProfile.email, profilePage.email.getText());

        driver.quit();
    }
}
