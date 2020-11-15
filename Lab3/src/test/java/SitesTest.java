import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import utils.DriverProvider;
import utils.Utils;
import webpages.*;

public class SitesTest {
    @Test
    public void createSiteTest() {
        WebDriver driver = DriverProvider.getDriver();
        new LoginPage(driver, true).loginAndWait();

        SitesPage sitesPage = new SitesPage(driver, true);
        String dirName = sitesPage.createRandomSite();

        Assertions.assertEquals(dirName, sitesPage.getSiteNameCell(dirName).getText());

        sitesPage.deleteSite(dirName);
        driver.quit();
    }

    @Test
    public void siteFilesTest() {
        WebDriver driver = DriverProvider.getDriver();
        new LoginPage(driver, true).loginAndWait();

        DomainRegistrationPage domainRegistrationPage = new DomainRegistrationPage(driver, true);
        String domainName = domainRegistrationPage.registerRandomDomain();

        SitesPage sitesPage = new SitesPage(driver, true);
        String dirName = sitesPage.createRandomSite();
        sitesPage.bindDomainToSite(domainName, dirName);

        FileManagerPage fileManPage = new FileManagerPage(driver, true);
        fileManPage.navigateTo(new String[]{dirName, "public_html"});
        String divId = Utils.getRandomAlphabetSequence(5);
        String divContents = Utils.getRandomAlphaNumericSequence(50);
        String fileContents = String.format("<html>\n<div id='%s'>\n%s\n</div>\n</html>\n", divId, divContents);
        fileManPage.writeToFile("index.htm", fileContents);

        String url = "http://" + domainName + "/";
        driver.get(url);

        // in case we get this exception
        // org.openqa.selenium.UnhandledAlertException: Dismissed user prompt dialog: При сохранении файла произошла ошибка
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException ignored) {
        }

        // if timeweb redirected us to its default page "Domain is parked" then we need to clear cache and try again
        // I do it by starting a new driver
        if (!url.equals(driver.getCurrentUrl())) {
            driver.quit();
            driver = DriverProvider.getDriver();
            new LoginPage(driver, true).loginAndWait();
            driver.get(url);
        }

        try {
            driver.findElement(By.xpath("//div[@id='" + divId + "']"));
        } catch (NoSuchElementException e) {
            driver.navigate().refresh();
        }

        Assertions.assertEquals(divContents, driver.findElement(By.xpath("//div[@id='" + divId + "']")).getText());

        sitesPage = new SitesPage(driver, true);
        sitesPage.deleteSite(dirName);
        new DomainsPage(driver, true).deleteDomain(domainName);
        fileManPage = new FileManagerPage(driver, true);
        fileManPage.navigateToHome();
        fileManPage.deleteFile(dirName);
        driver.quit();
    }
}
