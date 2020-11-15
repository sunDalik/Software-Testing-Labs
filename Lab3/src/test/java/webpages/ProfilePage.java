package webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends TimewebPage {
    @FindBy(xpath = "//article[@class='account-data-item']/span[4]")
    public WebElement email;

    @FindBy(xpath = "//p[contains(@class,'profile-name')]")
    public WebElement name;

    public ProfilePage(WebDriver driver, boolean gotoPage) {
        if (gotoPage) driver.get("https://hosting.timeweb.ru/account");
        setup(driver);
    }
}
