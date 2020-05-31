@acceptance
Feature: Acceptance tests for the Latest Foreign Exchange Rates
  Purpose of these tests is to cover the acceptance cases
  Conversion API: https://ratesapi.io/documentation/

  Background: User has the APIs
    Given the api for the latest conversion rates

  Scenario: User is able to view current day rates
    When user enters the api
    Then validate the success status code

  Scenario: User is able to view latest day rates
    When user enters the api
    Then validate the response

  Scenario: User sees proper error message when entered invalid API
    When user enters the invalid api
    Then user should see the appropriate error message
