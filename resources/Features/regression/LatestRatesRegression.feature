@regression
Feature: Regression tests for the Latest Foreign Exchange Rates
  Purpose of these tests is to cover the regression cases
  Conversion API: https://ratesapi.io/documentation/

  Background: User has the APIs
    Given The api for the current conversion rates

  Scenario: Verify that rates are quoted against the EURO by default
    When I call the latest rates API
    Then I get the response
    And I validate the base should be "EURO"

  Scenario: Verify that rates are displayed as per current date by default
    When I call the latest rates API
    Then I get the response
    And I validate the date should be current

  Scenario: Verify user is able to get specific exchange rates by setting the symbols parameter in the request.
    When I call the latest rates API with "symbols" as "INR"
    Then I get the response
    And I validate the symbol should be "INR"

  Scenario: Verify user is able to get specific exchange rates by setting the multiple symbols in the request.
    When I call the latest rates API with "symbols" as "INR,USD"
    Then I get the response
    And I validate the symbol should be "INR,USD"

  Scenario: Verify user is able to quote against different currency by setting base parameter in the request
    When I call the latest rates API with "base" as "GBP"
    Then I get the response
    And I validate the base should be "GBP"

  Scenario: Verify proper error is displayed when user tries to get the rates with two base values
    When I call the latest rates API with "base" as "GBP,INR"
    Then I check the error code
    And I validate error message "GBP,INR" for "base"

  Scenario: Verify uer is able to get specific exchange rates by setting base and symbols parameter in the request
    When I call the latest rates API with base as "GBP" and symbols as "EUR"
    Then I get the response
    And I validate base as "GBP" and symbols as "EUR"

