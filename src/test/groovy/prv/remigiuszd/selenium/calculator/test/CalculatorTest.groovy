package prv.remigiuszd.selenium.calculator.test

import prv.remigiuszd.selenium.calculator.model.Calculator
import prv.remigiuszd.selenium.util.spock.SeleniumTest

import static prv.remigiuszd.selenium.calculator.model.Calculator.AngleUnit.RADIANS
import static prv.remigiuszd.selenium.calculator.model.Calculator.CalculatorNumber.PI
import static prv.remigiuszd.selenium.calculator.model.Calculator.CalculatorOperation.*

@SeleniumTest
class CalculatorTest extends E2eTest {
    def 'calculator history properly displayed when operation defined using calculator buttons'() {
        given:
        Calculator calculator = new Calculator(webDriver)

        expect:
        calculator
                .$(2).$(MULTIPLY).$(2)
                .$(PLUS)
                .$(LEFT_BRACKET).$(10).$(DIVIDE).$(2).$(RIGHT_BRACKET)
                .pressEquals() == 9

        and:
        calculator.sqrt(16).pressEquals() == 4

        and:
        calculator.cos(PI, RADIANS).pressEquals() == -1

        and:
        calculator.history == [
                'cos(pi) = -1',
                'sqrt(16) = 4',
                '2*2+(10/2) = 9'
        ]
    }

    def 'calculator history properly displayed when operation defined using calculator input'() {
        given:
        Calculator calculator = new Calculator(webDriver)

        expect:
        calculator.typeIn('35*999+(100/4)').pressEquals() == 34990

        and:
        calculator.typeIn('sqrt(81)').pressEquals() == 9

        and:
        calculator.$(RADIANS).typeIn('cos(pi)').pressEquals() == -1

        and:
        calculator.history == [
                'cos(pi) = -1',
                'sqrt(81) = 9',
                '35*999+(100/4) = 34990'
        ]
    }

    def 'failing test, see build/e2e-test/screenshots'() {
        given:
        Calculator calculator = new Calculator(webDriver)

        expect:
        calculator.typeIn('35*999+(100/4)').pressEquals() == 34990

        throw new Exception('fail')
    }
}
