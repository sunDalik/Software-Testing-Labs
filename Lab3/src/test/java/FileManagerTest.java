import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import utils.DriverProvider;
import utils.FileStruct;
import webpages.FileManagerPage;
import webpages.LoginPage;

import java.util.ArrayList;

public class FileManagerTest {
    @Test
    public void createFileTest() {
        WebDriver driver = DriverProvider.getDriver();
        new LoginPage(driver, true).loginAndWait();

        FileManagerPage fileManPage = new FileManagerPage(driver, true);
        FileStruct file = fileManPage.createRandomFile();

        Assertions.assertEquals(file.name, fileManPage.getFileNameCell(file.name).getText());
        Assertions.assertEquals(file.contents, fileManPage.getFileContents(file.name));

        fileManPage.deleteFile(file.name);
        driver.quit();
    }

    @Test
    public void AZSortingTest() {
        WebDriver driver = DriverProvider.getDriver();
        new LoginPage(driver, true).loginAndWait();

        FileManagerPage fileManPage = new FileManagerPage(driver, true);
        ArrayList<FileStruct> createdFiles = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            FileStruct file = fileManPage.createRandomFile();
            createdFiles.add(file);
        }

        fileManPage.setSortMode(3);

        ArrayList<FileStruct> sortedFiles = fileManPage.getFileList();

        boolean foldersPart = true;
        String lastName = "";
        for (FileStruct file : sortedFiles) {
            if (!file.isFolder && foldersPart) {
                foldersPart = false;
                lastName = "";
            }
            if (!lastName.equals("")) {
                Assertions.assertTrue(file.name.compareTo(lastName) >= 0);
            }
            lastName = file.name;
        }

        for (FileStruct file : createdFiles) {
            fileManPage.deleteFile(file.name);
        }
        driver.quit();
    }

    @Test
    public void ZASortingTest() {
        WebDriver driver = DriverProvider.getDriver();
        new LoginPage(driver, true).loginAndWait();

        FileManagerPage fileManPage = new FileManagerPage(driver, true);
        ArrayList<FileStruct> createdFiles = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            FileStruct file = fileManPage.createRandomFile();
            createdFiles.add(file);
        }

        fileManPage.setSortMode(4);

        ArrayList<FileStruct> sortedFiles = fileManPage.getFileList();

        boolean foldersPart = true;
        String lastName = "";
        for (FileStruct file : sortedFiles) {
            if (!file.isFolder && foldersPart) {
                foldersPart = false;
                lastName = "";
            }
            if (!lastName.equals("")) {
                Assertions.assertTrue(file.name.compareTo(lastName) <= 0);
            }
            lastName = file.name;
        }

        for (FileStruct file : createdFiles) {
            fileManPage.deleteFile(file.name);
        }
        driver.quit();
    }
}
