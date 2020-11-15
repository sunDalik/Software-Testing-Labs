package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utils.RndUtils;

public class AdministratorsPage extends TimewebPage {
    @FindBy(xpath = "//button[@class='cpS-btn-acc js-add-person-button']")
    public WebElement addAdminButton;

    public AdministratorsPage(WebDriver driver, boolean gotoPage) {
        if (gotoPage) driver.get("https://hosting.timeweb.ru/persons");
        setup(driver);
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

        String surname = RndUtils.getRandomAlphabetSequence(6);
        String name = RndUtils.getRandomAlphabetSequence(6);
        String patronymic = RndUtils.getRandomAlphabetSequence(6);

        Actions actions = new Actions(driver);

        surnameInput.sendKeys(surname);
        nameInput.sendKeys(name);
        patronymicInput.sendKeys(patronymic);
        // in firefox driver you cant directly send keys to some fields without using actions
        birthdayInput.click();
        actions.sendKeys("01.01.2000").perform();
        passportNumberInput.click();
        actions.sendKeys("1111-111111").perform();
        passportDateInput.click();
        actions.sendKeys("01.01.2014").perform();
        passportIssuedByInput.sendKeys("-");
        indexInput.click();
        actions.sendKeys("111111").perform();
        addressInput.sendKeys("-");
        phoneInput.sendKeys("+7 (000) 000-00-00");
        emailInput.sendKeys(RndUtils.getRandomEmail(5));

        // to unfocus last input element
        phoneInput.click();
        createButton.click();

        WebElement okButton = driver.findElement(By.xpath("//div[@class='js-region-administrator']//button[@class='tw-button-primary js-btn']"));
        new Actions(driver).moveToElement(okButton).click().perform();

        return surname + " " + name + " " + patronymic;
    }

    public void deleteAdmin(String fullName) {
        jsClick(driver.findElement(By.xpath("//button[@data-person-name='" + fullName + "']")));
        driver.findElement(By.xpath("//button[@class='cpS-btn-warning js-confirm']")).sendKeys(Keys.RETURN);
    }
}
