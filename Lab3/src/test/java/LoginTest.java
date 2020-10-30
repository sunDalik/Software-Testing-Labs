import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import utils.DriverProvider;

public class LoginTest {
    @Test
    public void loginTest() {
        WebDriver driver = DriverProvider.getDriver();
        driver.get("https://timeweb.com");
    }
}
