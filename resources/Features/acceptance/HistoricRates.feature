@acceptance
Feature: Acceptance tests for the Historic Foreign Exchange Rates
  Purpose of these tests is to cover the acceptance cases
  Conversion API: https://ratesapi.io/documentation/

  Background: User has the APIs
    Given the api for the past conversion rates

  Scenario: User is able to view conversion rates for historic date
    When user enters the api with a given date "2000-01-12"
    Then validate the success status

  Scenario: User is able to view conversion rates for specific date
    When user enters the api with a given date "2010-01-12"
    Then validate the date in the response

    #Failure case.
    # Expected: As per the requirement when user tries to view the rates for future dates,
    # API should return the response with current date.
    # Actual: Date field in the response is less than a current date. Calculation of days in a month may be the cause
  Scenario: User is redirected to current day rates when tried to view future date rates
    When user enters the api with a given date "2020-10-12"
    Then validate the date in the response
