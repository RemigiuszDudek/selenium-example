package prv.remigiuszd.selenium.util.selenium

import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver

import java.text.SimpleDateFormat

import static org.apache.commons.io.FileUtils.copyFile
import static org.openqa.selenium.OutputType.FILE

class ScreenshotService {
	public static final String SCREENSHOT_DIR = 'build/e2e-test/screenshots'
	public static final String DEBUG_SCREENSHOT_DIR = "${SCREENSHOT_DIR}/debug"
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat('yyyyMMdd-HHmmss.SSS')

	static void takeScreenshot(WebDriver driver, File targetFile) {
		TakesScreenshot screenshot = (TakesScreenshot) driver
		File screenshotPng = screenshot.getScreenshotAs(FILE)
		copyFile(screenshotPng, targetFile)
	}

	static void takeDebugScreenshot(WebDriver driver, String fileNamePrefix) {
		TakesScreenshot screenshot = (TakesScreenshot) driver
		File screenshotPng = screenshot.getScreenshotAs(FILE)
		File targetFile = new File("${DEBUG_SCREENSHOT_DIR}/${fileNamePrefix}-${DATE_FORMAT.format(new Date())}.png")
		copyFile(screenshotPng, targetFile)
	}
}
