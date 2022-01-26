package prv.remigiuszd.selenium.util.driver

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import prv.remigiuszd.selenium.util.DownloadStorage

import java.util.concurrent.TimeUnit

class WebDriverFactory {
    private static final DEFAULT_BROWSER_TYPE = BrowserType.CHROME
    private static final BROWSER_TYPE_PROPERTY = 'remigiuszd.browser.type'

    static WebDriver create(boolean isHeadless) {
        BrowserType browserType = System.getProperty(BROWSER_TYPE_PROPERTY)
                ? BrowserType.fromString(System.getProperty(BROWSER_TYPE_PROPERTY))
                : DEFAULT_BROWSER_TYPE

        def driver = createWebDriver(browserType, isHeadless)
        configure(driver)
        return driver
    }

    private static WebDriver createWebDriver(BrowserType browserType, boolean isHeadless) {
        switch (browserType) {
            case BrowserType.CHROME:
                WebDriverManager.chromedriver().setup()
                ChromeOptions chromeOptions = new ChromeOptions()
                chromeOptions.addArguments('--ignore-certificate-errors', '--no-sandbox')
                chromeOptions.setExperimentalOption('prefs', ['download.default_directory': "${DownloadStorage.DOWNLOAD_DIRECTORY.absolutePath}"])
                if (isHeadless) chromeOptions.addArguments('--headless')
                return new ChromeDriver(chromeOptions)
            case BrowserType.FIREFOX:
                WebDriverManager.firefoxdriver().setup()
                return new FirefoxDriver()
            default:
                throw new IllegalStateException('Unknown browser type! Please check your configuration.')
        }
    }

    static void configure(WebDriver driver) {
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS)
        driver.manage().window().size = new Dimension(1920, 1080)
    }
}
