@regression
Feature: Regression tests for the Historic Foreign Exchange Rates
  Purpose of these tests is to cover the regression cases
  Conversion API: https://ratesapi.io/documentation/

  Background: User has the APIs
    Given The api for the historic conversion rates

  Scenario: Verify proper error is displayed when user tries to get the rates for year less than 1999
    When I call the historic rates API for date "1999-01-03"
    Then I check the error response
    And I validate the error message

    #Edge case: The response date in the API is wrong. It should be same as given date. Same case with 2008-01-12
  Scenario: Verify user is able to get specific exchange rate by setting symbols parameter
    When I call the historic rates API for date "2019-01-12" with "symbols" as "USD"
    Then I check the response
    And I validate the date as "2019-01-12" and symbol as "USD"

  Scenario: Verify user is able to get specific exchange rate by setting multiple symbols parameter
    When I call the historic rates API for date "2007-01-12" with "symbols" as "USD,GBP"
    Then I check the response
    And I validate the date as "2007-01-12" and symbol as "USD,GBP"

  Scenario: Verify user is able to get Specific date Foreign Exchange Rates with Base
    When I call the historic rates API for date "2007-01-12" with "base" as "GBP"
    Then I check the response
    And I validate the base as "GBP"

  Scenario: Verify user is able to get Specific date Foreign Exchange Rates with Symbols and Base
    When I call the historic rates API for date "2010-01-12" with base as "INR" and symbols as "USD"
    Then I check the response
    And I validate the base as "INR" and symbols as "USD"



