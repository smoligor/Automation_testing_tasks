package Task2.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class ParisHotelsWebBridgeStreet {
    private WebDriver driver;
    private By checkinDate = By.cssSelector("#availability-form > form > fieldset:nth-child(1) > div > div");
    private By checkoutDate = By.cssSelector("#availability-form > form > fieldset:nth-child(2) > div > div");
    private By dates = By.className("ui-state-default");
    private By guests = By.cssSelector("#availability-form > form > div > div.popup-form-wrap.js-form-popup > " +
            "div.popup-form.clearfix > div.popup-form-adults > span.js-popup-form-adults-text");
    private By forwardArrow = By.cssSelector("#ui-datepicker-div > div.ui-datepicker-header." +
            "ui-widget-header.ui-helper-clearfix.ui-corner-all > a.ui-datepicker-next.ui-corner-all");
    private By adults = By.cssSelector("#adults");
    private By GuestsOkButton = By.cssSelector("#availability-form > form > div >" +
            " div.adults-and-childs-wrap.js-adults-childs > " +
            "div.adults-and-childs__btn-wrap > div.adults-and-childs__btn-ok.js-ok-btn");
    private By label = By.cssSelector("#availability-form > div > span");

    public void navigateToMainPage() {
        driver.get("http://bridgestreet-st-germain-hotel.parishotelsweb.com/ru/");
    }

    public void pickupCheckinDate(Integer day) {
        driver.findElement(checkinDate).click();
        driver.findElement(forwardArrow).click();
        List<WebElement> days = driver.findElements(dates);
        days.stream().filter(element -> element.getText().equalsIgnoreCase(day.toString())).
                collect(Collectors.toList()).get(0).click();
    }

    public void pickupCkeckoutDate(Integer day) {
        driver.findElement(checkoutDate).click();
        List<WebElement> days = driver.findElements(dates);
        days.stream().filter(element -> element.getText().equalsIgnoreCase(day.toString())).
                collect(Collectors.toList()).get(0).click();
        driver.findElement(label).click();

    }

    public void pickupGuests() {
        driver.findElement(guests).click();
        driver.findElement(GuestsOkButton).click();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public ParisHotelsWebBridgeStreet(WebDriver driver) {
        this.driver = driver;
    }
}
