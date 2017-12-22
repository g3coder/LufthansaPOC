package org.spotqa.airlines.pageobjects.flights;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.spotqa.airlines.utils.SeleUtils;

/**
 * Created by lakkaraju on 21-12-2017.
 */
public class BookingConfigureTrip {

    private WebDriver driver ;

    public BookingConfigureTrip(WebDriver driver) {
        this.driver = driver;
    }

    By radioByLowestPrice = By.cssSelector("vc");

   //Please choose your flights
    By headerTrip = By.cssSelector(".headline>h1");
    By btnByContinue = By.cssSelector("#TOOLBAR_CONTINUE_0 span");

    public String verifyHeaderTrip() {
        return SeleUtils.applyExplicitWait(driver,40,headerTrip).getText();
    }

    public void selectLowPrice() {
        WebElement lowestPrice = SeleUtils.applyExplicitWait(driver,40,radioByLowestPrice);
        lowestPrice.click();
        WebElement lowestPriceOption = SeleUtils.applyExplicitWait(driver,40,btnByContinue);
        lowestPriceOption.click();
    }
}
