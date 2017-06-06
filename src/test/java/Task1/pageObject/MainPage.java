package Task1.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.stream.Collectors;
import static java.lang.Thread.sleep;

public class MainPage {
    private WebDriver driver;
    Actions builder;
    private By destination = By.cssSelector("#availbox_search_dest_0");
    private By checkInDaTe = By.cssSelector("#d_from0_0");
    private By checkOutDate = By.cssSelector("#d_to0_0");
    private By variantsOfMenus = By.className("select2-chosen");
    private By dropDownList = By.cssSelector("#container > div.content.rrow > div > " +
            "div.availbox.availbox-large > div > div > div > form > div:nth-child(16) > div > span");
    private By languages = By.cssSelector("body > div.booked_header.clearfix >" +
            " div.booked_header_user > ul > li:nth-child(3) > span");
    private By roomsAndGuests = By.cssSelector("#container > div.content.rrow > div > div.availbox.availbox-large > div > div >" +
            "div > form > div:nth-child(18) > div > a > span.select2-chosen.js-rooms-title");
    private By elementsFromDropdownMenu = By.className("select2-results-dept-0");
    private By getRoomsAndGuestsOkButton = By.cssSelector("#container > div.content.rrow > div > div.availbox.availbox-large > div > div > div > form > div.availbox_search_rooms_dialog > div >" +
            " div.row.availbox_search_roomsconf_buttons > button.btn.xmed-btn.blue-g-btn.js-ok");

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.builder = new Actions(driver);
    }

    public void writeToDestination(String string) {
        driver.findElement(destination).sendKeys(string);
    }

    public void changeLanguage(WebElement language) {
        driver.findElement(languages).click();
        language.click();
    }

    public List getLanguages() {
        List<WebElement> languagesList;
        driver.findElement(languages).click();
        languagesList = driver.findElements(By.className("change_lang_link"));
        return languagesList;
    }

    public void pickupCheckinDate(Integer day) {
        driver.findElement(checkInDaTe).click();
        List<WebElement> dates = driver.findElements(By.className("ui-state-default"));
        for (WebElement date : dates) {
            if (date.getText().equalsIgnoreCase(day.toString())) {
              date.click();
            }
        }
    }

    public void clickOnCheckinDate() {
        driver.findElement(checkInDaTe).click();
    }

    public void clickOnCheckoutDate() {
        driver.findElement(checkOutDate).click();
    }

    public String getCheckinDate() {
        String checkinDate = driver.findElement(By.cssSelector("#d_from0_0")).getAttribute("value");
        return checkinDate;
    }

    public String getCheckoutDate() {
        String checkoutDate = driver.findElement(By.cssSelector("#d_to0_0")).getAttribute("value");
        return checkoutDate;
    }

    public void pickupCheckOutDate(Integer day) {
        driver.findElement(checkOutDate).click();
        List<WebElement> dates = driver.findElements(By.className("ui-state-default"));
        for (WebElement date : dates) {
            if (date.getText().equalsIgnoreCase(day.toString())) {
                date.click();
            }
        }
    }

    public String getMaskedText() {
        String maskedText = driver.findElement(destination)
                .getAttribute("placeholder");
        return maskedText;
    }

    public WebElement getDropDownList() {
        WebElement dropDownListElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(dropDownList));
        return dropDownListElement;
    }

    public List<WebElement> getVariantsOfMenus() {
        return driver.findElements(variantsOfMenus);
    }

    public List<WebElement> getElementsFromCurrentDropdownMenu() {
        return driver.findElements(elementsFromDropdownMenu);
    }

    public void clickOnRoomAndGuestsOkButton()

    {
        driver.findElement(getRoomsAndGuestsOkButton).click();
    }

    public String getRoomsAndGuestsText() {
        return driver.findElement(roomsAndGuests).getText();
    }

    public void choose1room1Adult1Child() {
        WebElement roomsAndGuests = driver.findElement(By.cssSelector("span.select2-chosen.js-rooms-title"));
        roomsAndGuests.click();
        List<WebElement> variants = getVariantsOfMenus();
        variants = variants.stream().filter(element -> !element.getText().equals("")).collect(Collectors.toList());
        WebElement rooms = variants.get(1);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        rooms.click();
        List<WebElement> variantsOfRooms = getElementsFromCurrentDropdownMenu();
        variantsOfRooms.get(0).click();
        WebElement room1Adults = variants.get(2);
        room1Adults.click();
        List<WebElement> variantsOfAdults = getElementsFromCurrentDropdownMenu();
        variantsOfAdults.get(0).click();

        variants = refreshVariants();
        WebElement room1Children = variants.get(3);
        room1Children.click();
        List<WebElement> variantsOfChildren = getElementsFromCurrentDropdownMenu();
        variantsOfChildren.get(1).click();
        variants = refreshVariants();
        WebElement room1ChildrenAge = variants.get(4);
        room1ChildrenAge.click();
        List<WebElement> variantsOfChildrenAge = getElementsFromCurrentDropdownMenu();
        variantsOfChildrenAge.get(2).click();
        clickOnRoomAndGuestsOkButton();
    }

    public void choose2Rooms1Adult1ChildEach() {
        WebElement roomsAndGuests = driver.findElement(By.cssSelector("span.select2-chosen.js-rooms-title"));
        roomsAndGuests.click();
        List<WebElement> variants = getVariantsOfMenus();
        variants = variants.stream().filter(element -> !element.getText().equals("")).collect(Collectors.toList());
        WebElement rooms = variants.get(1);
        rooms.click();
        List<WebElement> variantsOfRooms = getElementsFromCurrentDropdownMenu();
        variantsOfRooms.get(1).click();
        variants = refreshVariants();
        WebElement room1Adults = variants.get(2);

        room1Adults.click();

        List<WebElement> variantsOfAdults = getElementsFromCurrentDropdownMenu();
        variantsOfAdults.get(0).click();

        variants = refreshVariants();
        WebElement room1Children = variants.get(3);
        room1Children.click();
        List<WebElement> variantsOfChildren = getElementsFromCurrentDropdownMenu();
        variantsOfChildren.get(1).click();
        variants = refreshVariants();
        WebElement room1ChildrenAge = variants.get(4);
        room1ChildrenAge.click();

        List<WebElement> variantsOfChildrenAge = getElementsFromCurrentDropdownMenu();
        variantsOfChildrenAge.get(2).click();
        variants = refreshVariants();
        WebElement room2Adults = variants.get(5);
        room2Adults.click();
        variantsOfAdults = getElementsFromCurrentDropdownMenu();
        variantsOfAdults.get(0).click();
        variants = refreshVariants();
        WebElement room2Children = variants.get(6);
        room2Children.click();

        variantsOfChildren = getElementsFromCurrentDropdownMenu();
        variantsOfChildren.get(1).click();
        variants = refreshVariants();
        WebElement room2ChildrenAge = variants.get(7);
        room2ChildrenAge.click();
        variantsOfChildrenAge = getElementsFromCurrentDropdownMenu();
        variantsOfChildrenAge.get(2).click();
        clickOnRoomAndGuestsOkButton();
    }

    private List<WebElement> refreshVariants() {
        List<WebElement> variants = getVariantsOfMenus();
        variants = variants.stream().filter(element -> !element.getText().equals("")).collect(Collectors.toList());
        return variants;
    }

    public void navigateToMainPage() {
        driver.get("http://www.booked.net");
    }

}


