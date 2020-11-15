package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.RndUtils;

public class DomainRegistrationPage extends TimewebPage {
    @FindBy(xpath = "//div[@class='domain-whois-check__input js-region-domain-input']//input[@class='input__field js-input']")
    public WebElement domainNameInput;

    @FindBy(xpath = "//button[@class='tw-button-secondary zone-select-button']")
    public WebElement domainZoneSelector;

    @FindBy(xpath = "//div[@class='domain-whois-check']/button")
    public WebElement domainRegistrationButton;

    public DomainRegistrationPage(WebDriver driver, boolean gotoPage) {
        if (gotoPage) driver.get("https://hosting.timeweb.ru/domains/registration");
        setup(driver);
    }

    // requires at least one admin to exist
    public String registerRandomDomain() {
        String domainZone = ".tmweb.ru";
        domainZoneSelector.click();
        driver.findElement(By.xpath("//div[@class='zone-selector__filter js-region-filter']//input[@class='input__field js-input']")).sendKeys(domainZone);
        driver.findElement(By.xpath("//span[@title='.tmweb.ru']")).click();

        String domainName;
        do {
            domainName = RndUtils.getRandomAlphaNumericSequence(10);
            domainNameInput.clear();
            domainNameInput.sendKeys(domainName);

            // wait until either the input box becomes error box OR registration button becomes clickable
            new WebDriverWait(driver, 10).until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='input__help js-help input__help_error']")),
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='domain-whois-check']/button[not(contains(@class,'disabled'))]"))
            ));
        } while (elementHasClass(domainRegistrationButton, "disabled"));

        domainRegistrationButton.click();

        WebElement bindingButton = driver.findElement(By.xpath("//div[@class='js-region-binding']//span[@class='tw-link-pseudo']"));
        bindingButton.click();

        WebElement noBindingOption = driver.findElement(By.xpath("//div[@class='js-region-menu']/div/span[3]"));
        WebElement saveBindingButton = driver.findElement(By.xpath("//button[@class='tw-button-primary js-save-btn ']"));
        noBindingOption.click();
        saveBindingButton.click();

        driver.findElement(By.xpath("//button[@class='tw-button-primary domain-registration__submit js-ui-submit']")).click();
        waitLoading();

        return domainName.toLowerCase() + domainZone;
    }
}
