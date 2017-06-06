package Task2.PageObject;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TestUtils {
    private static int screenshotCounter = 1;
    private static java.util.List<String> reportEntries = new ArrayList<>();

    public File makeScreenshot(WebDriver driver) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File
                    ("src/test/java/Task2/screenshots/screenshot" + screenshotCounter + ".png"));
            scrFile = new File("src/test/java/Task2/screenshots/screenshot" + screenshotCounter + ".png");
            screenshotCounter++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scrFile;
    }

    public boolean areFilesEqual(File fileOne, File fileTwo) {

        Image image1 = Toolkit.getDefaultToolkit().getImage(fileOne.getAbsolutePath());
        Image image2 = Toolkit.getDefaultToolkit().getImage(fileTwo.getAbsolutePath());

        try {
            PixelGrabber grab1 = new PixelGrabber(image1, 0, 0, -1, -1, false);
            PixelGrabber grab2 = new PixelGrabber(image2, 0, 0, -1, -1, false);

            int[] data1 = null;

            if (grab1.grabPixels()) {
                data1 = (int[]) grab1.getPixels();
            }

            int[] data2 = null;

            if (grab2.grabPixels()) {
                data2 = (int[]) grab2.getPixels();
            }

            return Arrays.equals(data1, data2);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
            return false;
        }
    }

    public String createReportString(String testNumber, String link, String screenshotPath,
                                     String result) {
        String reportString = "";
        reportString = reportString.concat("Test number " + testNumber + "\n");
        reportString = reportString.concat("Link " + link + "\n");
        reportString = reportString.concat("Screenshot path " + screenshotPath + "\n");
        reportString = reportString.concat("Screenshots are identical = " + result + "\n");
        return reportString;
    }

    public void createReport() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File
                    ("src/test/java/Task2/reports/report.json"), reportEntries);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addReportEntry(String reportEntry)
    {
        reportEntries.add(reportEntry);
    }

    public TestUtils() {
    }
}
