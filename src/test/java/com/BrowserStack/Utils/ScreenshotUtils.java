package com.BrowserStack.Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    public static String captureScreenshot(WebDriver driver, String screenshotName) {
    	
        // Add timestamp to avoid overwriting screenshots
    	
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotDir = "reports/screenshots";
        String screenshotPath = screenshotDir + "/" + screenshotName + "_" + timestamp + ".png";

        // Take screenshot
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            // Ensure directory exists
            Files.createDirectories(Paths.get(screenshotDir));
            // Copy screenshot to path
            Files.copy(src.toPath(), Paths.get(screenshotPath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return screenshotPath;
    }
}
