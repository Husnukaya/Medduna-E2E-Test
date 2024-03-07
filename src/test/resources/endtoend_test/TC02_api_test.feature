@e2e @api_test
Feature: Get the room and validate

  Scenario: Send get request by id
    Given send the request by id
    Then validate the body