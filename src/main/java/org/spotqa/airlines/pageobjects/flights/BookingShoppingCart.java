package org.spotqa.airlines.pageobjects.flights;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.spotqa.airlines.utils.SeleUtils;

import java.util.List;

/**
 * Created by lakkaraju on 21-12-2017.
 */
public class BookingShoppingCart {

    WebDriver driver;

    public BookingShoppingCart(WebDriver driver) {
        this.driver = driver;
    }

    By titleByCart = By.cssSelector("#inner div.headline div span:nth-child(2)");
    By titleByFlightSchedule = By.cssSelector(".container-stack.accordion>h3");
    By airportAbbrs = By.cssSelector(".table-flight-details div.columnFlights.first>ol>li div:nth-child(2) abbr");
    By actualFaretypes = By.cssSelector(".flightData.details.cabin>div>span");
    By btnByContinue = By.cssSelector("#TOOLBAR_2_CHECKOUT_0 span");

    public String verifyCartTitle() {
        return SeleUtils.applyExplicitWait(driver,40,titleByCart).getText();
    }

    public String verifyScheduleTitle(){
        return SeleUtils.applyExplicitWait(driver,20,titleByFlightSchedule).getText();
    }

    public Boolean verifyAirportsInShoppingCart(String expectedOrigin, String expectedDestination) {
        Boolean isOrigin = false;
        Boolean isDest = false;
        Boolean isAirports = false;

        List<WebElement> airportCodes = driver.findElements(airportAbbrs);
        for(WebElement elmt: airportCodes) {
            if(elmt.getText().equals(expectedOrigin))
                isOrigin = true;
            else if(elmt.getText().equals(expectedDestination))
                isDest = true;
        }
        if(isOrigin && isDest)
            isAirports = true;

        return isAirports;
    }

    public Boolean verifyFareType(String expectedFareType) {
        Boolean isFareType = false;
        List<WebElement> fareTypes = driver.findElements(actualFaretypes);
        for(WebElement elmt: fareTypes) {
            if(elmt.getText().equals(expectedFareType))
                isFareType = true;
        }
        return isFareType;
    }

    public Boolean clickOnContinue() {
        Boolean isConti = false;
        WebElement contiElmt = SeleUtils.applyExplicitWait(driver,30,btnByContinue);
        if(contiElmt.isSelected())
            isConti = true;
        return isConti;
    }


}
