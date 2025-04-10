package tools;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Random;

/*
 * screen przechowywać najlepiej w tym samym folderze co raporty tylko w podfolderze, bo raport może ich nie wczytać
 * zrobić ścieżkę absolutną do zapisu screena
 * zrobić ścieżkę relatywną do wczytywania screena do raportu
 * obie ścieżki bo dobrze wyjaśnia to chatgpt -> extent reports save screen correctly but image is broken in report
 */

public class Screen {

    private Screen() {
        throw new IllegalStateException("Utility class");
    }

    public static String capture64(WebDriver driver) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        return screenshot.getScreenshotAs(OutputType.BASE64);
    }

    public static String takeScreenshot(WebDriver driver) throws IOException {

        //String scrName = "fail"+ new Random().nextInt(999)+".png";
        String scrName = "Screenshot";

        // Cast driver to TakesScreenshot
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);

        // Set destination path for screenshot
        String relativePath = "./screen/" + scrName;
        String absolutePath = System.getProperty("user.dir")+"/src/main/resources/raporty/screen/"+scrName;

        try {
            // Save the screenshot to the specified directory
            FileUtils.copyFile(sourceFile, new File(absolutePath));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return relativePath;
    }
}