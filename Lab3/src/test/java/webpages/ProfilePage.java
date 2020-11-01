package webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage {
    @FindBy(xpath = "//article[@class='account-data-item']/span[4]")
    public WebElement email;

    @FindBy(xpath = "//p[contains(@class,'profile-name')]")
    public WebElement name;

    public ProfilePage(WebDriver driver, boolean gotoPage) {
        if (gotoPage) driver.get("https://hosting.timeweb.ru/account");
        PageFactory.initElements(driver, this);
    }
}
