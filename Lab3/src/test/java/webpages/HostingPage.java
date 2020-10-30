package webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HostingPage {
    @FindBy(xpath = "//p[@class='acc-name js-acc-name']")
    public WebElement username;

    public HostingPage(WebDriver driver, boolean gotoPage) {
        if (gotoPage) driver.get("https://hosting.timeweb.ru");
        PageFactory.initElements(driver, this);
    }
}
