package Task1.pageObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestUtils {
    private static int screenshotCounter = 1;
    private static List<String> reportEntries;
    private static TestUtils instance;

    public static TestUtils getInstance() {
        if (instance == null) {
            reportEntries = new ArrayList<>();
            instance = new TestUtils();
        }
        return instance;
    }

    public String getDayFromDate(Date date) {
        String day = date.toString().substring(7, 10).trim();
        if (day.charAt(0) == '0') {
            day = day.substring(1);
        }
        return day;
    }

    public boolean textFromRoomsAndGuestsHasChosenNumbers(String textFromRoomsAndGuests, String matchingString) {
        int number = StringUtils.countMatches(textFromRoomsAndGuests, matchingString);
        if (number == 3) {
            return true;
        } else return false;
    }

    public File makeScreenshot(WebDriver driver) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File
                    ("src/test/java/Task1/screenshots/screenshot" + screenshotCounter + ".png"));
            screenshotCounter++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scrFile;
    }

    public String createReportString(String testNumber, String fieldName, String stateNumber,
                                     String screenshotPath, String actionsResult, String language) {
        String reportString = "";
        reportString = reportString.concat("Test number " + testNumber + "\n");
        reportString = reportString.concat("Field name " + fieldName + "\n");
        reportString = reportString.concat("State number " + stateNumber + "\n");
        reportString = reportString.concat("Screenshot path " + screenshotPath + "\n");
        reportString = reportString.concat("Action result " + actionsResult + "\n");
        reportString = reportString.concat("Language  " + language + "\n");
        return reportString;
    }

    public void createReport() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File
                    ("src/test/java/Task1/reports/report.json"), reportEntries);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addReportEntry(String reportEntry) {
        reportEntries.add(reportEntry);
    }

    public static List<String> getReportEntries() {
        return reportEntries;
    }

    public static void setReportEntries(List<String> reportEntries) {
        TestUtils.reportEntries = reportEntries;
    }

    private TestUtils() {
    }
}

