import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Credentials;
import utils.DriverProvider;

public class LoginTest {
    @Test
    public void loginTest() {
        WebDriver driver = DriverProvider.getDriver();
        driver.get("https://timeweb.com");
        String mainPageWindowHandle = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[@href='https://hosting.timeweb.ru/']")).click();
        // switch to a new login tab (you can find it by comparing it to out main page tab)
        for (String windowHandle : driver.getWindowHandles()) {
            if (!mainPageWindowHandle.equalsIgnoreCase(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        driver.findElement(By.xpath("//input[@name='LoginForm[username]']")).sendKeys(Credentials.login);
        driver.findElement(By.xpath("//input[@name='LoginForm[password]']")).sendKeys(Credentials.password);
        driver.findElement(By.xpath("//button[@name='login-button']")).click();
        Assertions.assertEquals(Credentials.login, driver.findElement(By.xpath("//p[@class='acc-name js-acc-name']")).getText());
        driver.quit();
    }
}
