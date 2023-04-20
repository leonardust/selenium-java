package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ScreenshotUtils {

    /**
     * capture screenshot and attach it to Allure report
     *
     * @param driver   as WebDriver
     * @param fileName as String
     */
    public static void captureScreenshot(WebDriver driver, String fileName) {
        try {
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(fileName, new ByteArrayInputStream(screenshotBytes));
            String path = System.getProperty("user.dir") + "/target/allure-results/" + fileName + ".png";
            try (FileOutputStream out = new FileOutputStream(path)) {
                out.write(screenshotBytes);
            }
        } catch (IOException | WebDriverException e) {
            System.out.println("screenshot failed:" + e.getMessage());
        }
    }
}