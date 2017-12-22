package org.spotqa.airlines.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by lakkaraju on 21-12-2017.
 */
public class BrowserSetup {

    private static WebDriver driver;
    private static String PROJECT_DRIVER_PATH = "drivers/";

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(String browser, String url) {
        //Open File > Settings > Build, Execution, Deployment > Compiler > Java Compiler
        // Set the Project bytecode version to 1.8
        if (browser.equals("chrome")) {
            initiateChromeDriver(url);

        } else if (browser.equals("edge")) {
            initiateEdgeDriver(url);

        } else if(browser.equals("firefox")) {
            initiateFirefoxDriver(url);
        } else {
            System.out.println("browser : " + browser + " option not available. Initializing chrome");
            initiateChromeDriver(url);
        }
    }

    private static void initiateFirefoxDriver(String url) {
        String driverPath = PROJECT_DRIVER_PATH + "geckodriver.exe";
        System.setProperty("webdriver.gecko.driver", driverPath);
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.navigate().to(url);
      }

    private static void initiateEdgeDriver(String url) {
        String driverPath = PROJECT_DRIVER_PATH + "MicrosoftWebDriver.exe";
        System.setProperty("webdriver.edge.driver", driverPath);
        driver = new EdgeDriver();
        driver.get(url);
    }

    private static void initiateChromeDriver(String url) {
        String driverPath = PROJECT_DRIVER_PATH + "chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        driver.navigate().to(url);
    }

    public static void quitDriver() {
        driver.quit();
    }

}
