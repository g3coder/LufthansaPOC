package org.spotqa.airlines.pageobjects.flights;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.spotqa.airlines.utils.JavaUtils;
import org.spotqa.airlines.utils.SeleUtils;

import java.util.List;

/**
 * Created by lakkaraju on 21-12-2017.
 */
public class BookingHome {
    WebDriver driver;

    By txtByOrigin = By.id("flightmanagerFlightsFormOrigin");
    By txtByDesti = By.id("flightmanagerFlightsFormDestination");
    By selectByOrigOption = By.cssSelector("#flightmanagerFlightsFormOriginPopupList table td:nth-child(1)");
    By selectOrigins = By.cssSelector("#flightmanagerFlightsFormOriginPopupList tr");
    By selectByDestOption = By.cssSelector("#flightmanagerFlightsFormDestinationPopupList td:nth-child(2)");
    By checkByOneway = By.id("lhfaToggleRoundtrip");
    By btnByTravelDetails = By.id("flightmanagerFlightsFormTraveldetailsBtn");
    By btnBySearchFlights = By.xpath("//button[text()='Search flights']");
    By dropdownByAdult = By.id("traveldetailsAdults");
    By dropdownByCabin = By.id("traveldetailsCabin");
    By btnBydatePickerOutbound = By.id("flightmanagerFlightsFormOutboundDateDisplay");
    By btnByTravelContinue = By.cssSelector(".lid.clearfix input[type='submit']");

    public BookingHome(WebDriver driver) {
        this.driver = driver;
    }

    public void sendOrigin(String origin) throws InterruptedException {
        driver.findElement(txtByOrigin).sendKeys(origin);
        WebElement origins = driver.findElement(selectOrigins);
        SeleUtils.getExplicitWait().until(ExpectedConditions.visibilityOf(origins));

        List<WebElement> originsList = origins.findElements(By.tagName("td"));
        for(WebElement elmt: originsList) {
            if(elmt.getText().equals(origin))
                elmt.click();
        }
    }

    public void sendDestination(String destination) throws InterruptedException {
        SeleUtils.applyExplicitWait(driver,20,txtByDesti).sendKeys(destination);
        WebElement selectOption = SeleUtils.applyExplicitWait(driver,30,selectByDestOption);
        selectOption.click();
        Thread.sleep(30);
    }

    public void checkOneway() {
        driver.findElement(checkByOneway).click();
    }

    public void selectDepatureDate(int days) {

        WebElement elmt = SeleUtils.applyExplicitWait(driver,40,btnBydatePickerOutbound);

        elmt.click();

        int futureDate = JavaUtils.selectFutureDate(days);
        By calXpath = By.xpath("//*[@class='months-wrapper']/div[1]//td/button[text()='" + futureDate +"']");
        WebElement calender = SeleUtils.applyExplicitWait(driver,20,calXpath);
        calender.click();
    }

    public void clickOnTravelDetails(String visibleAdultText, String visibleFareTypeText){
        WebElement travelDetails = SeleUtils.applyExplicitWait(driver,20,btnByTravelDetails);
        travelDetails.click();

        WebElement dropdownAdultNo = SeleUtils.applyExplicitWait(driver,5,dropdownByAdult);
        Select travelers = new Select(dropdownAdultNo);
        travelers.selectByVisibleText(visibleAdultText);

        WebElement dropdownCabinDetails = SeleUtils.applyExplicitWait(driver,10,dropdownByCabin);
        Select fareTypeOptions = new Select(dropdownCabinDetails);
        fareTypeOptions.selectByVisibleText(visibleFareTypeText);

        SeleUtils.applyExplicitWait(driver,5,btnByTravelContinue).click();
    }

    public void clickOnSearchFlights() {
        WebElement btnSearchFlights = SeleUtils.applyExplicitWait(driver,10,btnBySearchFlights);
        btnSearchFlights.click();
    }

    /*

        By txtByOrigin = By.id("flightmanagerFlightsFormOrigin");
    By btnByOrigin = By.id("flightmanagerFlightsFormAirportAtlasOrigin");
    By dropDownByOrigCountry = By.id("flightmanagerFlightsFormOriginAirportAtlasCountry");
    By dropdownByOrigCity = By.id("flightmanagerFlightsFormOriginAirportAtlasCity");
    By dropdownByOrigAirport = By.id("flightmanagerFlightsFormOriginAirportAtlasAirport");

    By txtByDestination = By.id("flightmanagerFlightsFormDestination");
    By btnByDestination = By.id("flightmanagerFlightsFormAirportAtlasDestination");
    By dropdownByDestCountry =  By.id("flightmanagerFlightsFormDestinationAirportAtlasCountry");
    By dropdownByDestCity = By.id("flightmanagerFlightsFormDestinationAirportAtlasCity");
    By dropdownByDestAirport = By.id("flightmanagerFlightsFormDestinationAirportAtlasAirport");

        String optionValOrigCountry = "IN";
        String optionValOrigCity = "BOM";
        String optionValOrigAirport = "BOM";

        String optionValDestCountry = "GB";
        String optionValDestCity = "LON";
        String optionValDestAirport = "LCY";

        String adult1VisibleText = "1 Adult";
        String BusVisibleText = "Business";


    public void clickOnFindOriginAirports(String optionValOrigCountry,String optionValOrigCity,String optionValOrigAirport ) {

         SeleUtils.applyExplicitWait(driver,20,btnByOrigin).click();
        //select country India
       WebElement dropdownOrigCountry = SeleUtils.applyFluentWait(driver,dropDownByOrigCountry);
        Select origCountry = new Select(dropdownOrigCountry);
        origCountry.selectByValue(optionValOrigCountry);

        //WebElement dropdownOrigCity = SeleUtils.applyFluentWait(driver,dropdownByOrigCity);
        WebElement dropdownOrigCity = SeleUtils.applyExplicitWait(driver,100,dropdownByOrigCity);
        Select orgCity = new Select(dropdownOrigCity);
        orgCity.selectByValue(optionValOrigCity);

        //select airport
        WebElement dropdownOrigAirport = SeleUtils.applyExplicitWait(driver,40,dropdownByOrigAirport);
        Select orgAirport = new Select(dropdownOrigAirport);
        orgAirport.selectByValue(optionValOrigAirport);

        btnSelect.click();

    }

    private void clickOnDestination(){
        SeleUtils.applyExplicitWait(driver,20,txtByDestination).click();
    }

    public void clickOnFindDestinationAirports(String optionValDestCountry,String optionValDestCity,String optionValDestAirport){
        clickOnDestination();

        By btnByDestination = By.id("flightmanagerFlightsFormAirportAtlasDestination");
        By dropdownByDestCountry =  By.id("flightmanagerFlightsFormDestinationAirportAtlasCountry");
        By dropdownByDestCity = By.id("flightmanagerFlightsFormDestinationAirportAtlasCity");
        By dropdownByDestAirport = By.id("flightmanagerFlightsFormDestinationAirportAtlasAirport");

        WebElement dropdownDestCountry = SeleUtils.applyFluentWait(driver,dropdownByDestCountry);
        Select destCountry = new Select(dropdownDestCountry);
        destCountry.selectByValue(optionValDestCountry);

        WebElement dropdownDestCity = SeleUtils.applyExplicitWait(driver,50,dropdownByDestCity);
        Select destCity = new Select(dropdownDestCity);
        destCity.selectByValue(optionValDestCity);

        WebElement dropdownDestAirport = SeleUtils.applyExplicitWait(driver,50,dropdownByDestAirport);
        Select destAirport = new Select(dropdownDestAirport);
        destAirport.selectByValue(optionValDestAirport);

        btnSelect.click();
    }
*/

}

