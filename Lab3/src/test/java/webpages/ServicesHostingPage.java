package webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ServicesHostingPage extends TimewebPage {
    @FindBy(xpath = "//a[@class='btn js-hosting-optimo']")
    public WebElement registrationButton;

    @FindBy(xpath = "//div[@class='form w560']//input[@name='full_name']")
    public WebElement registrationNameInput;

    @FindBy(xpath = "//div[@class='form w560']//input[@name='email']")
    public WebElement registrationEmailInput;

    @FindBy(xpath = "//div[@class='form w560']//div[@class='mb10 content-slide-link']/a")
    public WebElement specifyUsernameButton;

    @FindBy(xpath = "//div[@class='form w560']//input[@name='uname']")
    public WebElement registrationUsernameInput;

    @FindBy(xpath = "//div[@class='form w560']//div[@class='hosting-items__button js-send-hosting-form mt10']")
    public WebElement registrationSubmitButton;

    public ServicesHostingPage(WebDriver driver, boolean gotoPage) {
        if (gotoPage) driver.get("https://timeweb.com/ru/services/hosting/");
        setup(driver);
    }

    public void registerUser(String name, String email, String username) {
        jsClick(registrationButton);
        registrationNameInput.sendKeys(name);
        registrationEmailInput.sendKeys(email);
        specifyUsernameButton.click();
        registrationUsernameInput.sendKeys(username);
        registrationSubmitButton.click();
    }
}
