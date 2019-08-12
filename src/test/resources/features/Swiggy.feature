# Created by ravimeda at 2019-08-10
Feature: Swiggy orders

  Scenario: Swiggy Order but cannot signup scenario
    Given user selects a delivery location
    When selected a specific restaurant
    And ordered items and does checkout
    And tries to signup with details
    Then verify the error messages
    Then change quantity of item