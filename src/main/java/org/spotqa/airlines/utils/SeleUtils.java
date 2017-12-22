package org.spotqa.airlines.utils;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by lakkaraju on 22-12-2017.
 */
public class SeleUtils {

    private static Wait<WebDriver> fluentWait = null;
    private static WebDriverWait explicitWait = null;

    public static void switchToWindow(WebDriver driver) {
        String windowBefore = driver.getWindowHandle();

        for(String window : driver.getWindowHandles()){
            if(!window.equals(windowBefore))
                driver.switchTo().window(window);
        }
    }
    private static void setFluentWait(WebDriver driver) {
         fluentWait = new FluentWait<WebDriver>(driver)
                .withTimeout(90, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
    }

/*   public static WebElement applyFluentWait(WebDriver driver, final By locator) {
       fluentWait = new FluentWait<WebDriver>(driver)
               .withTimeout(90, TimeUnit.SECONDS)
               .pollingEvery(5, TimeUnit.SECONDS)
               .ignoring(NoSuchElementException.class);

       WebElement element = fluentWait.until(new Function<WebDriver, WebElement>() {
           @Override
           public WebElement apply(WebDriver driver) {
               return driver.findElement(locator);
           }
       });
       return  element;
   }
*/
   public static void setExplicitWait(WebDriver driver,long seconds) {
    explicitWait = new WebDriverWait(driver,seconds);
   }

   public static WebDriverWait getExplicitWait() {
    return explicitWait;
   }

   public static WebElement applyExplicitWait(WebDriver driver, long seconds,By locator) {
       WebElement element = null;

       if(explicitWait == null) {
           setExplicitWait(driver,seconds);
       }
       element = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
       System.out.println("---------------" + element);

       return element;
   }

    public static WebElement applyExplicitWaitPresence(WebDriver driver, long seconds,By locator) {
        WebElement element = null;

        if(explicitWait == null) {
            setExplicitWait(driver,seconds);
        }
        element = explicitWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return element;
    }

    public static void takeScreenshot(WebDriver webdriver,String fileWithPath) throws Exception{
        TakesScreenshot scrShot =((TakesScreenshot)webdriver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File(fileWithPath);
        FileUtils.copyFile(SrcFile, DestFile);
    }

    public static void WaitForReady(WebDriver driver,long waitForElement )
    {
        WebDriverWait wait = new WebDriverWait(driver, waitForElement);
        JavascriptExecutor js=(JavascriptExecutor) driver;
        Boolean isJquery =(Boolean) js.executeScript("return jQuery.active == 0");
    }

    public static boolean waitForJSToLoad(WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver,10);

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor)driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };
        return wait.until(jsLoad);
    }

}
