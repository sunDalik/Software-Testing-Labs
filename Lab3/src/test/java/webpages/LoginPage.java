package webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Credentials;

public class LoginPage {
    WebDriver driver;

    @FindBy(xpath = "//input[@name='LoginForm[username]']")
    public WebElement loginInput;

    @FindBy(xpath = "//input[@name='LoginForm[password]']")
    public WebElement passwordInput;

    @FindBy(xpath = "//button[@name='login-button']")
    public WebElement submitButton;

    public LoginPage(WebDriver driver, boolean gotoPage) {
        if (gotoPage) driver.get("https://hosting.timeweb.ru");
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void login() {
        loginInput.sendKeys(Credentials.login);
        passwordInput.sendKeys(Credentials.password);
        submitButton.click();
    }

    public void loginAndWait() {
        login();
        //new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='acc-name js-acc-name']")));
        new HostingPage(driver, false).username.getText();
    }
}
