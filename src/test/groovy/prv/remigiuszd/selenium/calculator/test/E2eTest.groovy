package prv.remigiuszd.selenium.calculator.test

import org.openqa.selenium.WebDriver
import prv.remigiuszd.selenium.util.driver.WebDriverFactory
import spock.lang.Specification

class E2eTest extends Specification {
    private static final IS_HEADLESS = true
    protected WebDriver webDriver

    def setup() {
        webDriver = WebDriverFactory.create(IS_HEADLESS)
    }

    def cleanup() {
        webDriver.close()
    }

}
