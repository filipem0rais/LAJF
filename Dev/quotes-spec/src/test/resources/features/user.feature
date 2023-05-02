Feature: Application user

  Scenario: Retrieve information of the user with ID 4
    Given I have the user ID 4
    When I send a GET request to the /users/4 endpoint
    Then I receive a 200 status code
    And I receive the information of the user with ID 4


