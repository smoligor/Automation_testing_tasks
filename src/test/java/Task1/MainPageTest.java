package Task1;

import Task1.pageObject.MainPage;
import Task1.pageObject.TestUtils;
import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;

public class MainPageTest {
    MainPage mainPage;
    TestUtils testUtils;

    @Before
    public void init() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/Task1/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        testUtils = TestUtils.getInstance();
        mainPage = new MainPage(new ChromeDriver(chromeOptions));
        mainPage.getDriver().manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        mainPage.getDriver().get("http://www.booked.net");
        mainPage.getDriver().manage().window().maximize();
    }

    @Test
    public void test1_when_insertTodayAndTomorrowCheckInAndCheckOutDate_fields_have_these_dates() {
        mainPage.navigateToMainPage();
        List<WebElement> languages = mainPage.getLanguages();
        for (int i = 1; i < languages.size(); i++) {
            languages = mainPage.getLanguages();
            String languageString = languages.get(i).getText();
            mainPage.changeLanguage(languages.get(i));
            Date today = new Date();
            String day = testUtils.getDayFromDate(today);
            mainPage.pickupCheckinDate(Integer.valueOf(day));
            mainPage.getDriver().findElement(By.cssSelector("#container > div.content.rrow > div > div.availbox.availbox-large > div > div > div > form > h2")).click();
            Date tomorrow = DateUtils.addDays(today, 1);
            day = testUtils.getDayFromDate(tomorrow);
            mainPage.pickupCheckOutDate(Integer.valueOf(day));
            mainPage.clickOnCheckinDate();
            String checkinDate = mainPage.getCheckinDate();
            mainPage.pickupCheckOutDate(Integer.valueOf(day));
            mainPage.clickOnCheckoutDate();
            String checkoutDate = mainPage.getCheckoutDate();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date actualCheckinDate = simpleDateFormat.parse(checkinDate);
                Date actualCheckoutDate = simpleDateFormat.parse(checkoutDate);
                Boolean checkinDateIsValid = actualCheckinDate.getDate() == today.getDate();
                Boolean checkoutDateIsValid = actualCheckoutDate.getDate() == tomorrow.getDate();
                assertTrue(checkinDateIsValid);
                assertTrue(checkoutDateIsValid);
                File screenshot = testUtils.makeScreenshot(mainPage.getDriver());
                testUtils.addReportEntry(testUtils.createReportString
                        ("1", "checkin and checkout", "2", screenshot.getPath(), " checkinDateIsValid = "
                                + checkinDateIsValid.toString() + "\n" + "checkotDateIsValid = "
                                + checkoutDateIsValid.toString(), languageString));

            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }

    @Test
    public void test1_when_insertTenthJuneAndFirstJuly_expect_CheckInAndCheckOutDate_fields_have_these_dates() {
        mainPage.navigateToMainPage();
        List<WebElement> languages = mainPage.getLanguages();
        for (int i = 1; i < languages.size(); i++) {
            languages = mainPage.getLanguages();
            String languageString = languages.get(i).getText();
            mainPage.changeLanguage(languages.get(i));
            mainPage.pickupCheckinDate(7);
            mainPage.pickupCheckOutDate(1);
            mainPage.clickOnCheckinDate();
            String checkinDate = mainPage.getCheckinDate();
            mainPage.clickOnCheckoutDate();
            String checkoutDate = mainPage.getCheckoutDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {

                Date actualCheckinDate = simpleDateFormat.parse(checkinDate);
                Date actualCheckoutDate = simpleDateFormat.parse(checkoutDate);
                assertTrue(actualCheckinDate.toString().equals("Wed Jun 07 00:00:00 EEST 2017"));
                assertTrue(actualCheckoutDate.toString().equals("Sat Jul 01 00:00:00 EEST 2017"));
                Boolean checkinDateIsValid = false;
                Boolean checkoutDateIsValid = false;
                if (actualCheckinDate.toString().equals("Wed Jun 07 00:00:00 EEST 2017")) {
                    checkinDateIsValid = true;
                }
                if (actualCheckoutDate.toString().equals("Sat Jul 01 00:00:00 EEST 2017")) {
                    checkinDateIsValid = true;
                }

                File screenshot = testUtils.makeScreenshot(mainPage.getDriver());
                testUtils.addReportEntry(testUtils.createReportString
                        ("6", "checkin and checkout", "3", screenshot.getPath(), " checkinDateIsValid = "
                                + checkinDateIsValid.toString() + "\n" + "checkotDateIsValid = "
                                + checkoutDateIsValid.toString(), languageString));

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test6_when_write_3_chars_to_destination_expect_dropdownMenu_isPresent() {
        mainPage.navigateToMainPage();
        List<WebElement> languages = mainPage.getLanguages();
        for (int i = 1; i < languages.size(); i++) {
            languages = mainPage.getLanguages();
            String languageString = languages.get(i).getText();
            mainPage.changeLanguage(languages.get(i));
            mainPage.writeToDestination("rom");

            File screenshot = testUtils.makeScreenshot(mainPage.getDriver());
            testUtils.addReportEntry(testUtils.createReportString("3", "destinations", "2", screenshot.getPath(),
                    "dropdown list is displayed = " + mainPage.getDropDownList().isDisplayed(),
                    languageString));
            assertTrue(mainPage.getDropDownList().isDisplayed());
        }
    }

    @Test
    public void test2_when_no_input_in_destinations_expect_masked_text() {
        mainPage.navigateToMainPage();
        List<WebElement> languages = mainPage.getLanguages();
        for (int i = 1; i < languages.size(); i++) {
            languages = mainPage.getLanguages();
            String languageString = languages.get(i).getText();
            mainPage.changeLanguage(languages.get(i));
            String maskedText = mainPage.getMaskedText();
            File screenshot = testUtils.makeScreenshot(mainPage.getDriver());
            testUtils.addReportEntry(testUtils.createReportString("1", "destinations", "2", screenshot.getPath(),
                    "masked text is present = " + !maskedText.equalsIgnoreCase(""), languageString));
            assertTrue(!maskedText.equalsIgnoreCase(""));
        }
    }

    @After
    public void cleanUp() {
        mainPage.getDriver().quit();
    }
}
