package prv.remigiuszd.selenium.util.spock

import groovy.util.logging.Slf4j
import org.spockframework.runtime.extension.IMethodInterceptor
import org.spockframework.runtime.extension.IMethodInvocation

import static prv.remigiuszd.selenium.util.spock.SpockExtensionUtils.specMethodName
import static prv.remigiuszd.selenium.util.spock.SpockExtensionUtils.specSimpleClassName

@Slf4j
class SpecInfoLogInterceptor implements IMethodInterceptor {
    @Override
    void intercept(IMethodInvocation invocation) throws Throwable {
        try {
            log.info("spec started, specClass=${specSimpleClassName(invocation)}, specMethod=${specMethodName(invocation)}")
            invocation.proceed()
            log.info("spec finished, specClass=${specSimpleClassName(invocation)}, specMethod=${specMethodName(invocation)}")
        } catch (Throwable e) {
            log.info("spec failed, specClass=${specSimpleClassName(invocation)}, specMethod=${specMethodName(invocation)}")
            throw e
        }
    }
}
