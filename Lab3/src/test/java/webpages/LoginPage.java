package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Credentials;

public class LoginPage extends TimewebPage {
    @FindBy(xpath = "//input[@name='LoginForm[username]']")
    public WebElement loginInput;

    @FindBy(xpath = "//input[@name='LoginForm[password]']")
    public WebElement passwordInput;

    @FindBy(xpath = "//div[@class='login-page__error-message']/div")
    public WebElement errorBox;

    public LoginPage(WebDriver driver, boolean gotoPage) {
        if (gotoPage) driver.get("https://hosting.timeweb.ru");
        setup(driver);
    }

    public void login() {
        loginInput.sendKeys(Credentials.login);
        passwordInput.sendKeys(Credentials.password);
        submit();
    }

    public void submit() {
        try {
            WebElement submitButton = driver.findElement(By.xpath("//button[@name='login-button']"));
            submitButton.click();
        } catch (StaleElementReferenceException e) {
            WebElement submitButton = driver.findElement(By.xpath("//button[@name='login-button']"));
            submitButton.click();
        }
    }

    public void loginAndWait() {
        try {
            login();
            new HostingPage(driver, false).username.getText();
        } catch (Exception e) {
            login();
            new HostingPage(driver, false).username.getText();
        }
    }
}
