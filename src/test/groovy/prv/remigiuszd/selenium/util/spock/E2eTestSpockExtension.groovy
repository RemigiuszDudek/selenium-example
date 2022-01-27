package prv.remigiuszd.selenium.util.spock

import org.spockframework.runtime.extension.IAnnotationDrivenExtension
import org.spockframework.runtime.model.SpecInfo

class E2eTestSpockExtension implements IAnnotationDrivenExtension<SeleniumTest> {
    @Override
    void visitSpecAnnotation(SeleniumTest annotation, SpecInfo spec) {
        spec.getFeatures().each {  feature ->
            feature.getFeatureMethod().addInterceptor(new SeleniumScreenshotUponFailureInterceptor(annotation))
            feature.getFeatureMethod().addInterceptor(new SeleniumDomUponFailureInterceptor(annotation))
            feature.getFeatureMethod().addInterceptor(new SpecInfoLogInterceptor())
        }
    }
}
