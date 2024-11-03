#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@regression
Feature: Bookshelves search and filter tests

Scenario: Search for bookshelves
  Given I open the browser
  And I navigate to "https://www.pepperfry.com"
  Then I search for the "bookshelves"
  When I apply the price filter with max value "15000"
  When I sort the items by "Lowest Priced First"
  When I extract product names and prices
  Then I write the details to "BookshelvesData.xlsx"