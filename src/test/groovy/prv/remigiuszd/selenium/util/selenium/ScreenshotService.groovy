package prv.remigiuszd.selenium.util.selenium

import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver

import static org.apache.commons.io.FileUtils.copyFile
import static org.openqa.selenium.OutputType.FILE

class ScreenshotService {
    static void takeScreenshot(WebDriver driver, File targetFile) {
        TakesScreenshot screenshot = (TakesScreenshot) driver
        File screenshotPng = screenshot.getScreenshotAs(FILE)
        copyFile(screenshotPng, targetFile)
    }
}
