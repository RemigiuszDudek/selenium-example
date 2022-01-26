package prv.remigiuszd.selenium.util.spock

import org.spockframework.runtime.extension.IMethodInvocation

class SpockExtensionUtils {
    static String specSimpleClassName(IMethodInvocation invocation) {
        invocation.target.class.simpleName
    }

    static final String specMethodName(IMethodInvocation invocation) {
        invocation.feature.name
    }
}
