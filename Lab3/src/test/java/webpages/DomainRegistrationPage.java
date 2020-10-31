package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import javax.rmi.CORBA.Util;

public class DomainRegistrationPage {
    WebDriver driver;

    @FindBy(xpath = "//div[@class='domain-whois-check__input js-region-domain-input']//input[@class='input__field js-input']")
    public WebElement domainNameInput;

    @FindBy(xpath = "//button[@class='tw-button-secondary zone-select-button']")
    public WebElement domainZoneSelector;

    @FindBy(xpath = "//div[@class='domain-whois-check']/button")
    public WebElement domainRegistrationButton;

    public DomainRegistrationPage(WebDriver driver, boolean gotoPage) {
        if (gotoPage) driver.get("https://hosting.timeweb.ru/domains/registration");
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // requires at least one admin to exist
    public String registerRandomDomain() {
        String domainZone = ".tmweb.ru";
        domainZoneSelector.click();
        driver.findElement(By.xpath("//div[@class='zone-selector__filter js-region-filter']//input[@class='input__field js-input']")).sendKeys(domainZone);
        driver.findElement(By.xpath("//span[@title='.tmweb.ru']")).click();

        String domainName = "";
        do {
            domainName = Utils.getRandomAlphaNumericSequence(10);
            domainNameInput.clear();
            domainNameInput.sendKeys(domainName);

            // wait until either the input box becomes error box OR registration button becomes clickable
            new WebDriverWait(driver, 10).until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='input__help js-help input__help_error']")),
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='domain-whois-check']/button[not(contains(@class,'disabled'))]"))
            ));
        } while (Utils.elementHasClass(domainRegistrationButton, "disabled"));

        domainRegistrationButton.click();
        driver.findElement(By.xpath("//button[@class='tw-button-primary domain-registration__submit js-ui-submit']")).click();

        return domainName.toLowerCase() + domainZone;
    }
}
