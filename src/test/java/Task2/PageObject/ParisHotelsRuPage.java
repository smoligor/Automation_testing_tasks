package Task2.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class ParisHotelsRuPage {
    WebDriver driver;
    By checkinDate = By.cssSelector("#form_find > div.check-date-wrap.pull-left > fieldset:nth-child(1) > div > div");
    By checkoutDate = By.cssSelector("#form_find > div.check-date-wrap.pull-left > fieldset:nth-child(2) > div > div");
    By dates = By.className("ui-state-default");
    By guests = By.cssSelector("#form_find > div.adults-and-submit-wrap.pull-left > div.popup-form-wrap.js-form-popup >" +
            " div.popup-form > div.popup-form-adults > span.js-popup-form-adults-text");
    By forwardArrow = By.cssSelector("#ui-datepicker-div > div.ui-datepicker-header." +
            "ui-widget-header.ui-helper-clearfix.ui-corner-all > a.ui-datepicker-next.ui-corner-all");
    By adults = By.cssSelector("#adults");

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
    }


    public void navigateToPageOne() {
        driver.get("http://www.parishotelsweb.com/ru/");
    }

    public void navigateToPageTwo() {
        driver.get("http://www.parishotelsweb.com/search/?sort=Rating&order=DESC&part=1&Name=");
    }

    public void navigateToPageThree() {
        driver.get("http://bridgestreet-st-germain-hotel.parishotelsweb.com/ru/");
    }

    public WebDriver getDriver() {
        return driver;
    }

    public ParisHotelsRuPage(WebDriver driver) {
        this.driver = driver;
    }
}
