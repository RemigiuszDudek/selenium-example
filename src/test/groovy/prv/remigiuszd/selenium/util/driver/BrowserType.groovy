package prv.remigiuszd.selenium.util.driver

enum BrowserType {
    CHROME("chrome"),
    FIREFOX("firefox");

    final String browser

    BrowserType(String browser) {
        this.browser = browser
    }

    static BrowserType fromString(String typeAsString) {
        BrowserType type = values().find { it.browser == typeAsString}
        assert type: "unsupported type, type='$typeAsString', supportedTypes=${values()}"
        return type
    }

    @Override
    String toString() {
        browser
    }
}
