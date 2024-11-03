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
Feature: Gift Cards

  Scenario: Selecting a Gift Card
    Given the user navigates to the gift cards page
    When the user clicks on the gift card link
    And the user selects a birthday gift card
    Then the gift card selection is successful
    When the user fills recipient details with "John", "1234567890", "john@gmail.com", and "Happy Birthday!"
    And the user fills sender details with "Doe", "1234567891", and "doe@gmail.com"
    And the denomination is selected
    Then the gift card details are filled successfully