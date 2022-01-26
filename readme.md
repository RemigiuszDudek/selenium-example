# Selenium example

Please invoke `gradlew.bat build`, the e2e test will run in background mode.
There is also one failing test to demonstrate how screenshot utils works (screenshot
is taken upon test failure)

## Design shortcuts 
* lack of further componentization of calculator - in reality page objects beg 
  for further componentization (e.g. `Calculator` could have had `DigitsPanel`, `OperationsPanel`, etc.)
* missing concept of `HistoryItem`s, currently modeled as simple string
* missing concept of `Equation`, `Equation`s should be validated in test and should be able to present itself 
  as `HistoryItem`

Implementing any of it would be over-complication. It doesn't changes a fact that in real-life case that would be, 
most probably, a design mistake.