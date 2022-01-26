package prv.remigiuszd.selenium.util.spock

import org.spockframework.runtime.extension.ExtensionAnnotation
import prv.remigiuszd.selenium.util.selenium.ScreenshotService

import java.lang.annotation.Retention
import java.lang.annotation.Target

import static java.lang.annotation.ElementType.TYPE
import static java.lang.annotation.RetentionPolicy.RUNTIME

@Retention(RUNTIME)
@Target(TYPE)
@ExtensionAnnotation(E2eTestSpockExtension)
@interface SeleniumTest {
    String screenShotDir() default ScreenshotService.SCREENSHOT_DIR
}