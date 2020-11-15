package webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HostingPage extends TimewebPage {
    @FindBy(xpath = "//p[@class='acc-name js-acc-name']")
    public WebElement username;

    public HostingPage(WebDriver driver, boolean gotoPage) {
        if (gotoPage) driver.get("https://hosting.timeweb.ru");
        setup(driver);
    }
}
