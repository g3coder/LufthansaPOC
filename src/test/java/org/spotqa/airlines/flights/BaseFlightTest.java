package org.spotqa.airlines.flights;

import org.openqa.selenium.WebDriver;
import org.spotqa.airlines.utils.BrowserSetup;
import org.spotqa.airlines.utils.SeleUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

/**
 * Created by lakkaraju on 22-12-2017.
 */
public class BaseFlightTest {

    @Parameters({"browser","url"})
    @BeforeTest
    public void initializeTestBaseSetup(String browser, String url) {
        try {
            BrowserSetup.setDriver(browser,url);
            SeleUtils.setExplicitWait(BrowserSetup.getDriver(),40);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
   }

    @AfterTest
    public void quitBrowser() {
        BrowserSetup.quitDriver();
    }
}
