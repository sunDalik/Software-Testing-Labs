package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

public class AdministratorsPage {
    WebDriver driver;

    @FindBy(xpath = "//button[@class='cpS-btn-acc js-add-person-button']")
    public WebElement addAdminButton;

    public AdministratorsPage(WebDriver driver, boolean gotoPage) {
        if (gotoPage) driver.get("https://hosting.timeweb.ru/persons");
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // returns full name of the created admin
    public String createRandomAdmin() {
        addAdminButton.click();
        WebElement surnameInput = driver.findElement(By.xpath("//div[@class='js-region-first-name']//input[@class='input__field js-input']"));
        WebElement nameInput = driver.findElement(By.xpath("//div[@class='js-region-last-name']//input[@class='input__field js-input']"));
        WebElement patronymicInput = driver.findElement(By.xpath("//div[@class='js-region-father-name']//input[@class='input__field js-input']"));
        WebElement birthdayInput = driver.findElement(By.xpath("//input[@class='cpS-input js-birthdate k-input']"));
        WebElement passportNumberInput = driver.findElement(By.xpath("//div[@class='js-region-passport']//input[@class='input__field js-input']"));
        WebElement passportDateInput = driver.findElement(By.xpath("//input[@class='cpS-input js-passport_date k-input']"));
        WebElement passportIssuedByInput = driver.findElement(By.xpath("//div[@class='js-region-issued-by']//input[@class='input__field js-input']"));
        WebElement indexInput = driver.findElement(By.xpath("//div[@class='js-region-index']//input[@class='input__field js-input']"));
        WebElement addressInput = driver.findElement(By.xpath("//div[@class='js-region-address']//input[@class='input__field js-input']"));
        WebElement phoneInput = driver.findElement(By.xpath("//div[@class='js-region-phone']//input[@class='input__field js-input']"));
        WebElement emailInput = driver.findElement(By.xpath("//div[@class='js-region-email']//input[@class='input__field js-input']"));
        WebElement createButton = driver.findElement(By.xpath("//button[@class='cpS-btn-acc js-create-person disabled']"));

        String surname = Utils.getRandomAlphabetSequence(6);
        String name = Utils.getRandomAlphabetSequence(6);
        String patronymic = Utils.getRandomAlphabetSequence(6);

        surnameInput.sendKeys(surname);
        nameInput.sendKeys(name);
        patronymicInput.sendKeys(patronymic);
        birthdayInput.sendKeys("01.01.2000");
        passportNumberInput.sendKeys("1111-111111");
        passportDateInput.sendKeys("01.01.2014");
        passportIssuedByInput.sendKeys("-");
        indexInput.sendKeys("111111");
        addressInput.sendKeys("-");
        phoneInput.sendKeys("+7 (000) 000-00-00");
        emailInput.sendKeys(Utils.getRandomEmail(5));

        // to unfocus last input element
        phoneInput.click();
        createButton.click();

        WebElement okButton = driver.findElement(By.xpath("//div[@class='js-region-administrator']//button[@class='tw-button-primary js-btn']"));
        new Actions(driver).moveToElement(okButton).click().perform();

        return surname + " " + name + " " + patronymic;
    }

    public void deleteAdmin(String fullName) {
        driver.findElement(By.xpath("//button[@data-person-name='" + fullName + "']")).click();
        driver.findElement(By.xpath("//button[@class='cpS-btn-warning js-confirm']")).sendKeys(Keys.RETURN);;
    }
}
