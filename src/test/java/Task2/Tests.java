package Task2;

import Task2.PageObject.ParisHotelsRuPage;
import Task2.PageObject.ParisHotelsWebBridgeStreet;
import Task2.PageObject.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

import static org.junit.Assert.assertFalse;

public class Tests {
    ParisHotelsRuPage parisHotelsRuPage;
    ParisHotelsWebBridgeStreet parisHotelsWebBridgeStreet;
    TestUtils testUtils;

    @Before
    public void init() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/Task1/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(chromeOptions);
        parisHotelsWebBridgeStreet = new ParisHotelsWebBridgeStreet(driver);
        parisHotelsRuPage = new ParisHotelsRuPage(driver);
        testUtils = new TestUtils();

    }

    @Test
    public void test1_when_compare_screenshots_expect_screenshots_to_be_not_identical() {
        parisHotelsRuPage.navigateToPageOne();
        File FirstIterationScreenshot = testUtils.makeScreenshot(parisHotelsRuPage.getDriver());
        parisHotelsRuPage.pickupCheckinDate(7);
        parisHotelsRuPage.pickupCkeckoutDate(15);
        File SecondIterationScreenshot = testUtils.makeScreenshot(parisHotelsRuPage.getDriver());
        Boolean areFilesEqual = testUtils.areFilesEqual(FirstIterationScreenshot,
                SecondIterationScreenshot);
        testUtils.addReportEntry(testUtils.createReportString("1",
                "http://bridgestreet-st-germain-hotel.parishotelsweb.com/ru/",
                FirstIterationScreenshot.getAbsolutePath() + " " +
                        SecondIterationScreenshot, areFilesEqual.toString()));
        assertFalse(areFilesEqual);

    }

    @Test
    public void test2_when_compare_screenshots_expect_screenshots_to_be_not_identical() {
        parisHotelsRuPage.navigateToPageTwo();
        File FirstIterationScreenshot = testUtils.makeScreenshot(parisHotelsRuPage.getDriver());
        parisHotelsRuPage.pickupCheckinDate(7);
        parisHotelsRuPage.pickupCkeckoutDate(15);
        File SecondIterationScreenshot = testUtils.makeScreenshot(parisHotelsRuPage.getDriver());
        Boolean areFilesEqual = testUtils.areFilesEqual(FirstIterationScreenshot,
                SecondIterationScreenshot);
        testUtils.addReportEntry(testUtils.createReportString("2",
                "http://www.parishotelsweb.com/search/?sort=Rating&order=DESC&part=1&Name=",
                FirstIterationScreenshot.getAbsolutePath() + " " +
                        SecondIterationScreenshot, areFilesEqual.toString()));
        assertFalse(areFilesEqual);

    }

    @Test
    public void test3_when_compare_screenshots_expect_screenshots_to_be_not_identical() {
        parisHotelsWebBridgeStreet.navigateToMainPage();
        File FirstIterationScreenshot = testUtils.makeScreenshot(parisHotelsRuPage.getDriver());
        parisHotelsWebBridgeStreet.pickupCheckinDate(7);
        parisHotelsWebBridgeStreet.pickupCkeckoutDate(15);
        parisHotelsWebBridgeStreet.pickupGuests();
        File SecondIterationScreenshot = testUtils.makeScreenshot(parisHotelsRuPage.getDriver());
        Boolean areFilesEqual = testUtils.areFilesEqual(FirstIterationScreenshot,
                SecondIterationScreenshot);
        testUtils.addReportEntry(testUtils.createReportString("3",
                "http://bridgestreet-st-germain-hotel.parishotelsweb.com/ru/",
                FirstIterationScreenshot.getAbsolutePath() + " " +
                        SecondIterationScreenshot, areFilesEqual.toString()));
        assertFalse(areFilesEqual);

    }

    @After
    public void cleanUp() {
        testUtils.createReport();
        parisHotelsRuPage.getDriver().close();
    }
}
