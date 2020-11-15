import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import utils.DriverProvider;
import utils.FileStruct;
import utils.RndUtils;
import webpages.FileManagerPage;
import webpages.LoginPage;

import java.util.ArrayList;

public class FileManagerTest {
    @Test
    public void createFileTest() {
        WebDriver driver = DriverProvider.getDriver();
        new LoginPage(driver, true).loginAndWait();

        FileManagerPage fileManPage = new FileManagerPage(driver, true);
        String fileContents = RndUtils.getRandomAlphaNumericSequence(10) + "\n" + RndUtils.getRandomAlphaNumericSequence(15) + "\n" + RndUtils.getRandomAlphaNumericSequence(30);
        FileStruct file = fileManPage.createRandomFile(true, fileContents);

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
        for (int i = 0; i < 5; i++) {
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
            System.out.println(file.isFolder + ", " + file.name + ", " + lastName);
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
        for (int i = 0; i < 5; i++) {
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
            System.out.println(file.isFolder + ", " + file.name + ", " + lastName);
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

    @Test
    public void sizeAscSortingTest() {
        WebDriver driver = DriverProvider.getDriver();
        new LoginPage(driver, true).loginAndWait();

        FileManagerPage fileManPage = new FileManagerPage(driver, true);
        ArrayList<FileStruct> createdFiles = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            FileStruct file = fileManPage.createRandomFile(true, RndUtils.getRandomAlphaNumericSequence(RndUtils.randomInt(5, 100)));
            createdFiles.add(file);
        }

        fileManPage.setSortMode(5);

        ArrayList<FileStruct> sortedFiles = fileManPage.getFileList();

        boolean foldersPart = true;
        long lastSize = -1;
        for (FileStruct file : sortedFiles) {
            if (!file.isFolder && foldersPart) {
                foldersPart = false;
                lastSize = -1;
            }
            System.out.println(file.isFolder + ", " + file.size + ", " + lastSize);
            Assertions.assertTrue(file.size >= lastSize);
            lastSize = file.size;
        }

        for (FileStruct file : createdFiles) {
            fileManPage.deleteFile(file.name);
        }
        driver.quit();
    }

    @Test
    public void sizeDescSortingTest() {
        WebDriver driver = DriverProvider.getDriver();
        new LoginPage(driver, true).loginAndWait();

        FileManagerPage fileManPage = new FileManagerPage(driver, true);
        ArrayList<FileStruct> createdFiles = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            FileStruct file = fileManPage.createRandomFile(true, RndUtils.getRandomAlphaNumericSequence(RndUtils.randomInt(5, 100)));
            createdFiles.add(file);
        }

        fileManPage.setSortMode(6);

        ArrayList<FileStruct> sortedFiles = fileManPage.getFileList();

        boolean foldersPart = true;
        long lastSize = -1;
        for (FileStruct file : sortedFiles) {
            if (!file.isFolder && foldersPart) {
                foldersPart = false;
                lastSize = -1;
            }
            System.out.println(file.isFolder + ", " + file.size + ", " + lastSize);
            if (lastSize != -1) {
                Assertions.assertTrue(file.size <= lastSize);
            }
            lastSize = file.size;
        }

        for (FileStruct file : createdFiles) {
            fileManPage.deleteFile(file.name);
        }
        driver.quit();
    }
}
