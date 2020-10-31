import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.DriverProvider;
import webpages.DomainRegistrationPage;
import webpages.LoginPage;

public class DomainRegistrationTest {
    @Test
    public void domainRegistrationTest() {
        WebDriver driver = DriverProvider.getDriver();
        new LoginPage(driver, true).loginAndWait();
        DomainRegistrationPage domainRegistrationPage = new DomainRegistrationPage(driver, true);
        String newDomainName = domainRegistrationPage.registerRandomDomain();

        // check that domains table has 1 entry with our recently created domain
        Assertions.assertEquals(1, driver.findElements(By.xpath("//div[@id='w0']/table/tbody/tr[@data-domain='" + newDomainName + "']")).size());
        driver.quit();
    }
}
