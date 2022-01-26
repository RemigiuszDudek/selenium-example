package prv.remigiuszd.selenium.calculator.model

import org.openqa.selenium.WebDriver

class PageObject {
    protected WebDriver webDriver

    PageObject(WebDriver webDriver) {
        this.webDriver = webDriver
    }
}
