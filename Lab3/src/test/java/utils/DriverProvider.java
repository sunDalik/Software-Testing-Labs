package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

enum DRIVER_TYPE {
    CHROME,
    FIREFOX
}

public class DriverProvider {
    private static DRIVER_TYPE driverType;

    static {
        InputStream inputStream = Credentials.class.getClassLoader().getResourceAsStream("driver");
        Scanner s = new Scanner(inputStream).useDelimiter("\n");
        String driverTypeString = s.next();
        if (driverTypeString.toLowerCase().equals("firefox")) {
            driverType = DRIVER_TYPE.FIREFOX;
        } else {
            driverType = DRIVER_TYPE.CHROME;
        }
        System.setProperty("webdriver.chrome.driver", s.next());
        System.setProperty("webdriver.gecko.driver", s.next());
    }

    public static WebDriver getDriver() {
        WebDriver driver;
        if (driverType == DRIVER_TYPE.FIREFOX) {
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}
