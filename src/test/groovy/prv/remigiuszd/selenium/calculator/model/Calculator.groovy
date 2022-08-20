package prv.remigiuszd.selenium.calculator.model

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

import static java.lang.Double.parseDouble
import static org.openqa.selenium.By.className
import static org.openqa.selenium.By.tagName
import static prv.remigiuszd.selenium.calculator.model.Calculator.CalculatorNumber.*
import static prv.remigiuszd.selenium.calculator.model.Calculator.CalculatorOperation.*
import static prv.remigiuszd.selenium.util.FluentWaitUtil.waitFor
import static prv.remigiuszd.selenium.util.FluentWaitUtil.waitUtil

class Calculator extends PageObject {
    private static final URL = 'https://web2.0calc.com/'

    @FindBy(id = 'Btn0') private final WebElement button0
    @FindBy(id = 'Btn1') private final WebElement button1
    @FindBy(id = 'Btn2') private final WebElement button2
    @FindBy(id = 'Btn3') private final WebElement button3
    @FindBy(id = 'Btn4') private final WebElement button4
    @FindBy(id = 'Btn5') private final WebElement button5
    @FindBy(id = 'Btn6') private final WebElement button6
    @FindBy(id = 'Btn7') private final WebElement button7
    @FindBy(id = 'Btn8') private final WebElement button8
    @FindBy(id = 'Btn9') private final WebElement button9
    @FindBy(id = 'BtnPi') private final WebElement buttonPi

    @FindBy(id = 'BtnPlus') private final WebElement buttonPlus
    @FindBy(id = 'BtnMult') private final WebElement buttonMultiply
    @FindBy(id = 'BtnDiv') private final WebElement buttonDivide
    @FindBy(id = 'BtnSqrt') private final WebElement buttonSqrt
    @FindBy(id = 'BtnCalc') private final WebElement buttonEquals

    @FindBy(id = 'BtnCos') private final WebElement buttonCosine
    @FindBy(id = 'trigorad') private final WebElement radioButtonRadians
    @FindBy(id = 'trigodeg') private final WebElement radioButtonDegrees

    @FindBy(id = 'BtnParanL') private final WebElement buttonBrackeLeft
    @FindBy(id = 'BtnParanR') private final WebElement buttonBracketRight
    @FindBy(id = 'BtnClear') private final WebElement buttonClear

    @FindBy(id = 'hist') private final WebElement history
    @FindBy(id = 'input') private final WebElement input

    private final Map<Integer, CalculatorNumber> intToCalculatorDigit
    private final Map<CalculatorNumber, WebElement> calculatorNumbers
    private final Map<CalculatorOperation, WebElement> calculatorOperations

    Calculator(WebDriver webDriver) {
        super(webDriver)
        webDriver.get(URL)
        PageFactory.initElements(webDriver, this)
        this.intToCalculatorDigit = [0: ZERO, 1: ONE, 2: TWO, 3: THREE, 4: FOUR, 5: FIVE, 6: SIX, 7: SEVEN, 8: EIGHT, 9: NINE]
        this.calculatorNumbers = [
                (ZERO): button0, (ONE): button1, (TWO): button2, (THREE): button3, (FOUR): button4,
                (FIVE): button5, (SIX): button6, (SEVEN): button7, (EIGHT): button8, (NINE): button9,
                (PI)  : buttonPi
        ]
        this.calculatorOperations = [
                (PLUS)        : buttonPlus, (MULTIPLY): buttonMultiply, (DIVIDE): buttonDivide,
                (LEFT_BRACKET): buttonBrackeLeft, (RIGHT_BRACKET): buttonBracketRight
        ]
        handleConsents(webDriver)
    }

    private static void handleConsents(WebDriver webDriver) {
        waitUtil('handling personal data consent', webDriver, { driver ->
            def consentButtons = webDriver.findElements(By.name('choice'))
            consentButtons.find { it.getAttribute('value') == 'deny' }.click()
        })
    }

    Calculator $(int number) {
        List<CalculatorNumber> digits = []
        int currentNumber = number
        while (currentNumber > 0) {
            digits << intToCalculatorDigit[currentNumber % 10]
            currentNumber = (int) currentNumber / 10
        }
        digits.reverseEach { this.$(it) }
        this
    }

    Calculator $(CalculatorNumber number) { calculatorNumbers[number].click(); this }

    Calculator $(CalculatorOperation operation) { calculatorOperations[operation].click(); this }

    Calculator $(AngleUnit angleUnit) { angleUnit.apply(this); this }

    Calculator sqrt(int number) {
        clear()
        $(number)
        buttonSqrt.click()
        this
    }

    List<String> getHistory() {
        history.click()
        waitFor('wait for history', webDriver, {
            List<WebElement> historyElements = it.findElement(By.id('histframe')).findElements(tagName('li'))
            historyElements.collect { historyElementToString(it) }
        })
    }

    private static String historyElementToString(WebElement historyElement) {
        def result = historyElement.findElement(className('r')).text
        def equation = historyElement.findElement(className('l')).text
        return "$equation $result"
    }

    Calculator cos(CalculatorNumber number, AngleUnit angleUnit) {
        clear()
        angleUnit.apply(this)
        $(number)
        buttonCosine.click()
        this
    }

    double pressEquals() {
        buttonEquals.click()
        waitFor('waiting for result', input, { parseDouble(it.getAttribute('value')) })
    }

    Calculator clear() {
        buttonClear.click()
        this
    }

    Calculator typeIn(String textToTypeIn) {
        clear()
        input.sendKeys(textToTypeIn)
        this
    }

    static enum CalculatorNumber {
        ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE,
        PI
    }

    static enum CalculatorOperation {
        PLUS, DIVIDE, MULTIPLY, LEFT_BRACKET, RIGHT_BRACKET
    }

    static enum AngleUnit {
        DEGREES{
            @Override
            void apply(Calculator calculator) {
                calculator.radioButtonDegrees.click()
            }
        },
        RADIANS{
            @Override
            void apply(Calculator calculator) {
                calculator.radioButtonRadians.click()
            }
        }

        abstract void apply(Calculator calculator);
    }

}
