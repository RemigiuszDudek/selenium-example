package prv.remigiuszd.selenium.util.spock

import org.openqa.selenium.WebDriver
import org.spockframework.runtime.extension.IMethodInterceptor
import org.spockframework.runtime.extension.IMethodInvocation
import org.spockframework.runtime.model.FieldInfo
import prv.remigiuszd.selenium.util.selenium.ScreenshotService

import static prv.remigiuszd.selenium.util.spock.SpockExtensionUtils.specMethodName
import static prv.remigiuszd.selenium.util.spock.SpockExtensionUtils.specSimpleClassName

class SeleniumScreenshotUponFailureInterceptor implements IMethodInterceptor {
    private final SeleniumTest screenshotUponFailureConfig

    SeleniumScreenshotUponFailureInterceptor(SeleniumTest screenshotUponFailureConfig) {
        this.screenshotUponFailureConfig = screenshotUponFailureConfig
    }

    @Override
    void intercept(IMethodInvocation invocation) throws Throwable {
        try {
            invocation.proceed()
        } catch (Throwable e) {
            takeScreenshot(invocation)
            throw e
        }
    }

    private void takeScreenshot(IMethodInvocation invocation) {
        def targetFile = screenShotFile(invocation)
        WebDriver driver = getWebDriver(invocation)
        ScreenshotService.takeScreenshot(driver, targetFile)
    }

    private File screenShotFile(IMethodInvocation invocation) {
        def folderName = specSimpleClassName(invocation)
        def fileName = specMethodName(invocation).replaceAll("[^a-zA-Z0-9]", "_")
        new File("${screenshotUponFailureConfig.screenShotDir()}/${folderName}/${fileName}.png")
    }

    private static WebDriver getWebDriver(IMethodInvocation invocation) {
        FieldInfo driverField = invocation.spec.specsTopToBottom[0].fields.find { it.type == WebDriver }
        (WebDriver) driverField.readValue(invocation.getTarget())
    }
}
