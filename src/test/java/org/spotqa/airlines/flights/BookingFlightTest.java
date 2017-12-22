package org.spotqa.airlines.flights;

import org.spotqa.airlines.pageobjects.flights.BookingConfigureTrip;
import org.spotqa.airlines.pageobjects.flights.BookingHome;
import org.spotqa.airlines.pageobjects.flights.BookingPassengerDetails;
import org.spotqa.airlines.pageobjects.flights.BookingShoppingCart;
import org.spotqa.airlines.utils.BrowserSetup;
import org.spotqa.airlines.utils.SeleUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by lakkaraju on 22-12-2017.
 */
public class BookingFlightTest  extends  BaseFlightTest{

    @Test
    public void  VerifyBookingOneAdultOnewayBusiness() throws Exception {

        String RESULTS_FOLDER = "results/";
        String SCREEN_SHOTS_FOLDER = "screenshots/";

        String origin = "BOM";
        String destination = "LCY";
        int days = 2;
        String adult1VisibleText = "1 Adult";
        String BusVisibleText ="Business";
        String expectedHeader = "Please choose your flights";
        String expectedCartTitle = "Flights have been added to your shopping cart.";
        String expectedScheduleTitle = "Flight Schedule";
        String expectedPassengerHeader = "Your passenger details";
        String expectedPassengerTitleName = "Mr";
        String firstName = "TijuTavi";
        String lastName = "lastname";
        String email = "tijutavi@gmail.com";
        String countryCode = "+91";
        String areaCode ="044";
        String phoneNo = "0945212305";

        BookingHome bookingHome = new BookingHome(BrowserSetup.getDriver());

        bookingHome.sendOrigin(origin);
        bookingHome.sendDestination(destination);
        bookingHome.checkOneway();
        bookingHome.selectDepatureDate(days);
        bookingHome.clickOnTravelDetails(adult1VisibleText,BusVisibleText);
        bookingHome.clickOnSearchFlights();

        BookingConfigureTrip configureTrip = new BookingConfigureTrip(BrowserSetup.getDriver());
        String actualHeader = configureTrip.verifyHeaderTrip().trim();
        Assert.assertEquals(actualHeader,expectedHeader);
        configureTrip.selectLowPrice();

        BookingShoppingCart shoppingCart = new BookingShoppingCart(BrowserSetup.getDriver());
        String actualCartTitle = shoppingCart.verifyCartTitle();
        Assert.assertEquals(actualCartTitle,expectedCartTitle);

        String actualScheduleTitle = shoppingCart.verifyScheduleTitle();
        Assert.assertEquals(actualScheduleTitle,expectedScheduleTitle);

        Assert.assertTrue(shoppingCart.verifyAirportsInShoppingCart(origin,destination));
        Assert.assertTrue(shoppingCart.verifyFareType(BusVisibleText));

        SeleUtils.takeScreenshot(BrowserSetup.getDriver(),RESULTS_FOLDER+SCREEN_SHOTS_FOLDER+"itinerary.png");

        Assert.assertTrue(shoppingCart.clickOnContinue());

        BookingPassengerDetails passengerDetails = new BookingPassengerDetails(BrowserSetup.getDriver());
        String actualPassengeHeader = passengerDetails.getPassengerHeader().trim();
        Assert.assertEquals(actualPassengeHeader, expectedPassengerHeader);

        Assert.assertTrue(passengerDetails.selectPassengerTitle(expectedPassengerTitleName));
        passengerDetails.enterPassengerDetails(firstName,lastName);

        passengerDetails.selectCountryCode(countryCode);
        passengerDetails.enterContactDetails(areaCode,phoneNo,email);
        passengerDetails.clickContinue();

        SeleUtils.takeScreenshot(BrowserSetup.getDriver(),RESULTS_FOLDER+SCREEN_SHOTS_FOLDER+"passenger_details.png");
    }

}
