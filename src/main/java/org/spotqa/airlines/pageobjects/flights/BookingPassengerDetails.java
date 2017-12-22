package org.spotqa.airlines.pageobjects.flights;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.spotqa.airlines.utils.SeleUtils;

/**
 * Created by lakkaraju on 22-12-2017.
 */
public class BookingPassengerDetails {
    WebDriver driver;

   // By passengeByHeader = By.cssSelector(".panel.panel-pax.panel-pax-1.corner-all>section>h3");
    By passengerByHeader = By.cssSelector(".headline>h1");
    By dropdownTitle = By.id("PASSENGERS_ADT_TITLE_0");
    By txtFirstName = By.id("PASSENGERS_ADT_FIRST_NAME_0");
    By txtLastName = By.id("PASSENGERS_ADT_LAST_NAME_0");
    By dropdownCountry = By.id("CONTACT_DETAILS_PHONE_PHONE_COUNTRY_0");
    //<option value="+91" role="option">+91 (India)</option>
    By txtAreaCode = By.id("CONTACT_DETAILS_PHONE_PHONE_AREA_0");
    By txtPhoneNumber = By.id("CONTACT_DETAILS_PHONE_PHONE_NUMBER_0");
    By txtEmail = By.id("CONTACT_DETAILS_EMAIL_0");
    By btnContinue = By.cssSelector("TOOLBAR_CONTINUE_0 span");



    public BookingPassengerDetails(WebDriver driver) {
        this.driver = driver;
    }

    public String getPassengerHeader() {
        return SeleUtils.applyExplicitWait(driver,60,passengerByHeader).getText();
    }

    public Boolean selectPassengerTitle(String titleName){
        Boolean isTitle = false;
        WebElement titleEle = SeleUtils.applyExplicitWait(driver,50,dropdownTitle);
        Select titleList = new Select(titleEle);
        titleList.selectByVisibleText(titleName);
        if(titleEle.isSelected())
            isTitle = true;

        return isTitle;
    }

    public void enterPassengerDetails(String firstName,String lastName) {
        driver.findElement(txtFirstName).sendKeys(firstName);
        driver.findElement(txtLastName).sendKeys(lastName);
    }

    public Boolean selectCountryCode(String countryCode) {
        Boolean isCountry = false;
        WebElement countryEle = SeleUtils.applyExplicitWait(driver,10,dropdownCountry);
        Select countrySelect = new Select(countryEle);
        countrySelect.selectByValue(countryCode);
        if(countryEle.isSelected())
            isCountry = true;

        return isCountry;
    }

    public void enterContactDetails(String areaCode, String phoneNumber, String email) {
        driver.findElement(txtAreaCode).sendKeys(areaCode);
        driver.findElement(txtPhoneNumber).sendKeys(phoneNumber);
        driver.findElement(txtEmail).sendKeys(email);
    }

    public void clickContinue() {
        driver.findElement(btnContinue).click();
    }


}
