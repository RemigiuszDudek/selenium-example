package prv.remigiuszd.selenium.util

import groovy.util.logging.Slf4j
import org.openqa.selenium.support.ui.FluentWait

import java.util.function.Function

import static java.time.Duration.ofMillis
import static java.time.Duration.ofSeconds

@Slf4j
class FluentWaitUtil {

    static<T> void waitUtil(String message, T element, Function<T, Boolean> untilCondition) {
        new FluentWait<>(element)
                .withMessage(message)
                .withTimeout(ofSeconds(30))
                .pollingEvery(ofMillis(200))
                .until(new Function<T, Boolean>() {
                    @Override
                    Boolean apply(T input) {
                        try {
                            untilCondition.apply(input)
                            return true
                        } catch (Exception e) {
                            log.warn(e.getMessage())
                            return false
                        }
                    }
                })
    }

    static <IN, OUT> OUT waitFor(String message, IN input, Function<IN, OUT> leafElementFindCondition) {
        new FluentWait<>(input)
                .withMessage(message)
                .withTimeout(ofSeconds(10))
                .pollingEvery(ofMillis(200))
                .until(new Function<IN, OUT>() {
                    @Override
                    OUT apply(IN anInput) {
                        try {
                            return leafElementFindCondition.apply(anInput)
                        } catch (Exception ignore) {
                            return null
                        }
                    }
                })
    }
}
