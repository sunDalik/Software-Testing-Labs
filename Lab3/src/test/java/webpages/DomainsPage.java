package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DomainsPage extends TimewebPage {
    public DomainsPage(WebDriver driver, boolean gotoPage) {
        if (gotoPage) driver.get("https://hosting.timeweb.ru/domains");
        setup(driver);
    }

    public void deleteDomain(String domainName) {
        jsClick(driver.findElement(By.xpath("//button[@class='cpS-btn-icon js-delete-domain' and @data-domain='" + domainName + "']")));
        jsClick(driver.findElement(By.xpath("//button[@class='tw-button-danger js-confirm']")));
        waitLoading();
    }
}
