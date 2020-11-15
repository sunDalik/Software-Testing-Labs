package webpages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;

public class TimewebPage {
    WebDriver driver;

    public void setup(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        hideBanner();
    }

    public boolean elementHasClass(WebElement element, String className) {
        return Arrays.asList(element.getAttribute("class").split(" ")).contains(className);
    }

    public void jsClick(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException ex) {
            return false;
        }
    }

    public void hideBanner() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("document.head.insertAdjacentHTML('beforeend', '<style>.info-bar{display:none !important;}</style>')");
    }

    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {
        }
    }

    public void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {
        }
    }

    public void waitLoading() {
        try {
            new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='js-preloader']")));
        } catch (Exception ignored) {
        }
        new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='js-preloader']")));
    }
}
