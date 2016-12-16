Feature: Selenium example

  Scenario: Google search
    Given I am on the Google search page
    When I search for "selenium testing"
    Then the page title should start with "selenium"
