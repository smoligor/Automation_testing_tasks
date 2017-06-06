package Task1;

import Task1.pageObject.MainPage;
import Task1.pageObject.TestUtils;
import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;

public class MainPageGuestsTest {

    MainPage mainPage;
    TestUtils testUtils;

    @Before
    public void init() {
        testUtils = TestUtils.getInstance();
        mainPage = new MainPage(new JBrowserDriver());
        mainPage.getDriver().manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        mainPage.getDriver().get("http://www.booked.net");
        mainPage.getDriver().manage().window().maximize();

    }

    @Test
    public void test4_when_choose_1_room_1_adult_1_child_expect_roomsAndGuests_have_chosen_numbers() {
        mainPage.navigateToMainPage();
        List<WebElement> languages = mainPage.getLanguages();
        for (int i = 1; i < languages.size(); i++) {
            languages = mainPage.getLanguages();
            String languageString = languages.get(i).getText();
            mainPage.changeLanguage(languages.get(i));
            mainPage.choose1room1Adult1Child();
            String textFromRoomsAndGuests = mainPage.getRoomsAndGuestsText();
            File screenshot = testUtils.makeScreenshot(mainPage.getDriver());
            testUtils.addReportEntry(testUtils.createReportString("4", "roomsAndGuests", "1", screenshot.getPath(),
                    "roomsAndGuests have chosen numbers = " +
                            testUtils.textFromRoomsAndGuestsHasChosenNumbers(textFromRoomsAndGuests, "1"),
                    languageString));
            assertTrue(testUtils.textFromRoomsAndGuestsHasChosenNumbers(textFromRoomsAndGuests, "1"));
        }
    }

    @Test
    public void test5_when_choose_2_rooms_1_adult_1_child_each_expect_roomsAndGuests_have_these_numbers() {
        mainPage.navigateToMainPage();
        List<WebElement> languages = mainPage.getLanguages();
        for (int i = 1; i < languages.size(); i++) {
            languages = mainPage.getLanguages();
            String languageString = languages.get(i).getText();
            mainPage.changeLanguage(languages.get(i));
            mainPage.choose2Rooms1Adult1ChildEach();
            String textFromRoomsAndGuests = mainPage.getRoomsAndGuestsText();
            File screenshot = testUtils.makeScreenshot(mainPage.getDriver());
            testUtils.addReportEntry(testUtils.createReportString("5", "roomsAndGuests", "2", screenshot.getPath(),
                    "roomsAndGuests have chosen numbers = " + testUtils
                            .textFromRoomsAndGuestsHasChosenNumbers(textFromRoomsAndGuests, "1"),
                    languageString));
            assertTrue(testUtils.textFromRoomsAndGuestsHasChosenNumbers(textFromRoomsAndGuests, "2"));
        }
    }

    @After
    public void cleanUp() {
        mainPage.getDriver().quit();
    }
}
