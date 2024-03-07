@e2e @database_test
Feature: Database room test

  Scenario: Validate room by JDBC
    Given connect to database
    Then read the room and validate