package webpages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.FileStruct;
import utils.Utils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class FileManagerPage {
    WebDriver driver;

    @FindBy(xpath = "//span[@id='file_actions']")
    public WebElement fileActionsButton;

    public FileManagerPage(WebDriver driver, boolean gotoPage) {
        if (gotoPage) driver.get("https://hosting.timeweb.ru/fileman");
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public FileStruct createRandomFile() {
        int filesTableRowsCount = driver.findElements(By.xpath("//div[@id='main_table']//table/tbody/tr")).size();

        fileActionsButton.click();
        WebElement newFileButton = driver.findElement(By.xpath("//div[@class='ui-tooltip-content']/div[@id='div_actions_file__new']"));
        newFileButton.click();
        WebElement fileNameInput = driver.findElement(By.xpath("//input[@id='name']"));

        WebElement createNewFileButton = driver.findElement(By.xpath("//button[@id='save_button']"));
        WebElement fileCreationValidationMessage = driver.findElement(By.xpath("//span[@id='name-jq-validate-message']"));
        String filename = "";

        while (true) {
            filename = Utils.getRandomAlphaNumericSequence(10);
            fileNameInput.clear();
            fileNameInput.sendKeys(filename);

            createNewFileButton.click();

            if (fileCreationValidationMessage.getText().equals("")) {
                break;
            }
        }

        waitLoading();
        clickIntoFileEditor();
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).perform();

        String contents = Utils.getRandomAlphaNumericSequence(10) + "\n" + Utils.getRandomAlphaNumericSequence(15) + "\n" + Utils.getRandomAlphaNumericSequence(30);
        actions.sendKeys(contents).perform();
        saveCurrentFile();

        // wait until new entry is put in the files table
        new WebDriverWait(driver, 10).until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@id='main_table']//table/tbody/tr"), filesTableRowsCount + 1));

        return new FileStruct(filename + ".html", contents);
    }

    public void deleteFile(String fileName) {
        int filesTableRowsCount = driver.findElements(By.xpath("//div[@id='main_table']//table/tbody/tr")).size();

        Utils.jsClick(driver, getFileNameCell(fileName));

        Utils.jsClick(driver, fileActionsButton);
        WebElement deleteFileButton = driver.findElement(By.xpath("//div[@class='ui-tooltip-content']/div[@id='div_actions_file__select_delete']"));
        Utils.jsClick(driver, deleteFileButton);

        new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
        new WebDriverWait(driver, 10).until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@id='main_table']//table/tbody/tr"), filesTableRowsCount));
    }

    public String getFileContents(String fileName) {
        Actions actions = new Actions(driver);
        actions.doubleClick(getFileNameCell(fileName)).perform();
        waitLoading();
        clickIntoFileEditor();
        actions.keyDown(Keys.CONTROL).sendKeys("a").sendKeys("c").keyUp(Keys.CONTROL).perform();

        String contents = "";
        try {
            contents = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException | IOException ignored) {
        }

        WebElement fileContentsCloseButton = driver.findElement(By.xpath("//div[@aria-describedby='file_redactor_dialog_2']/div[1]/button"));
        Utils.jsClick(driver, fileContentsCloseButton);

        return contents;
    }

    public WebElement getFileNameCell(String fileName) {
        return driver.findElement(By.xpath("//div[@id='main_table']//table/tbody/tr/td[2]/div[normalize-space()='" + fileName + "']"));
    }

    public void navigateTo(String[] path) {
        navigateToHome();
        Actions actions = new Actions(driver);
        for (String dir : path) {
            actions.doubleClick(getFileNameCell(dir)).perform();
            waitLoading();
        }
    }

    public void writeToFile(String fileName, String contents) {
        Actions actions = new Actions(driver);
        actions.doubleClick(getFileNameCell(fileName)).perform();
        waitLoading();

        clickIntoFileEditor();
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).perform();
        actions.sendKeys(contents).perform();
        saveCurrentFile();
    }

    public void navigateToHome() {
        WebElement homeButton = driver.findElement(By.xpath("//div[@id='home']"));
        Utils.jsClick(driver, homeButton);
        waitLoading();
    }

    public void waitLoading() {
        try {
            new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='js-preloader']")));
        } catch (Exception ignored) {
        }
        new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='js-preloader']")));
    }

    public void saveCurrentFile() {
        WebElement fileContentsSaveButton = driver.findElement(By.xpath("//div[@class='form-actions file-redactor-form__row']/button"));
        fileContentsSaveButton.click();

        WebElement fileContentsCloseButton = driver.findElement(By.xpath("//div[@role='dialog']/div[1]/button"));
        Utils.jsClick(driver, fileContentsCloseButton);
    }

    public void clickIntoFileEditor() {
        Actions actions = new Actions(driver);
        try {
            WebElement fileContentsInput = driver.findElement(By.xpath("//div[@class='CodeMirror-code']/div[1]/pre"));
            actions.click(fileContentsInput).perform();
        } catch (StaleElementReferenceException e) {
            WebElement fileContentsInput = driver.findElement(By.xpath("//div[@class='CodeMirror-code']/div[1]/pre"));
            actions.click(fileContentsInput).perform();
        }
    }
}
