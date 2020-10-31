package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class DomainsPage {
    WebDriver driver;

    public DomainsPage(WebDriver driver, boolean gotoPage) {
        if (gotoPage) driver.get("https://hosting.timeweb.ru/domains");
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void deleteDomain(String domainName) {
        driver.findElement(By.xpath("//button[@class='cpS-btn-icon js-delete-domain' and @data-domain='" + domainName + "']")).click();
        driver.findElement(By.xpath("//button[@class='tw-button-danger js-confirm']")).click();
    }
}
