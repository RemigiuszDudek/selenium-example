# Selenium example

Please invoke `gradlew.bat build`, the e2e test will run in background mode.
There is also one failing test to demonstrate how failure context data are gathered:
* screenshot is taken upon test failure
* dom is downloaded, mind [webdriver documentation](https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/WebDriver.html) - 
_"Please consult the documentation of the particular driver being used to determine whether the returned text 
reflects the current state of the page or the text last sent by the web server"_

## Design shortcuts 
* lack of further componentization of calculator - in reality page objects beg 
  for further componentization (e.g. `Calculator` could have had `DigitsPanel`, `OperationsPanel`, etc.)
* missing concept of `HistoryItem`s, currently modeled as simple string
* missing concept of `Equation`, `Equation`s should be validated in test and should be able to present itself 
  as `HistoryItem`

Implementing any of it for such a simple example would be an over-complication. 
It doesn't change a fact that in real-life case, not implementing it would be, most probably, a design mistake.