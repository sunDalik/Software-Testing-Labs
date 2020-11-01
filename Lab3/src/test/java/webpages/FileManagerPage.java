package webpages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.FileStruct;
import utils.Utils;

import javax.rmi.CORBA.Util;
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

        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='CodeMirror-code']/div[1]/pre")));
        Actions actions = new Actions(driver);
        try {
            WebElement fileContentsInput = driver.findElement(By.xpath("//div[@class='CodeMirror-code']/div[1]/pre"));
            actions.click(fileContentsInput).perform();
        } catch (StaleElementReferenceException e) {
            WebElement fileContentsInput = driver.findElement(By.xpath("//div[@class='CodeMirror-code']/div[1]/pre"));
            actions.click(fileContentsInput).perform();
        }
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).perform();

        String contents = Utils.getRandomAlphaNumericSequence(10) + "\n" + Utils.getRandomAlphaNumericSequence(15) + "\n" + Utils.getRandomAlphaNumericSequence(30);
        actions.sendKeys(contents).perform();

        WebElement fileContentsSaveButton = driver.findElement(By.xpath("//div[@class='form-actions file-redactor-form__row']/button"));
        fileContentsSaveButton.click();

        WebElement fileContentsCloseButton = driver.findElement(By.xpath("//div[@aria-describedby='file_redactor_dialog_2']/div[1]/button"));
        Utils.jsClick(driver, fileContentsCloseButton);

        return new FileStruct(filename + ".html", contents);
    }

    public void deleteFile(String fileName) {
        WebElement fileNameDiv = driver.findElement(By.xpath("//div[normalize-space()='" + fileName + "']"));
        WebElement fileRow = fileNameDiv.findElement(By.xpath("./../.."));
        Utils.jsClick(driver, fileRow);

        Utils.jsClick(driver, fileActionsButton);
        WebElement deleteFileButton = driver.findElement(By.xpath("//div[@class='ui-tooltip-content']/div[@id='div_actions_file__select_delete']"));
        Utils.jsClick(driver, deleteFileButton);

        new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public String getFileContents(String fileName) {
        WebElement fileNameDiv = driver.findElement(By.xpath("//div[normalize-space()='" + fileName + "']"));
        WebElement fileRow = fileNameDiv.findElement(By.xpath("./../.."));
        Utils.jsClick(driver, fileRow);
        fileActionsButton.click();
        WebElement editFileButton = driver.findElement(By.xpath("//div[@class='ui-tooltip-content']/div[@id='div_actions_file__redactor']"));
        editFileButton.click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='js-preloader']")));
        Actions actions = new Actions(driver);
        try {
            WebElement fileContentsInput = driver.findElement(By.xpath("//div[@class='CodeMirror-code']/div[1]/pre"));
            actions.click(fileContentsInput).perform();
        } catch (StaleElementReferenceException e) {
            WebElement fileContentsInput = driver.findElement(By.xpath("//div[@class='CodeMirror-code']/div[1]/pre"));
            actions.click(fileContentsInput).perform();
        }
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
}
