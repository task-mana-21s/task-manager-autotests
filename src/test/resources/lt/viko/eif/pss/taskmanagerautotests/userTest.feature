Feature: Testing a User REST API
  Users should be able to submit GET and POST requests to a web service

  Scenario: Successfully fetching all users using the REST API
    Given valid user API
    When GET request is sent to the user REST API
    Then API for user responds with a HTTP 200 status code
    And returns the JSON object for the specified user

  Scenario: Successfully fetching a user using the REST API
    Given valid user ID
    When GET request is sent to the user REST API with the ID
    Then API for user responds with a HTTP 200 status code
    And returns user JSON object for the specified ID

  Scenario: Adding a new user using the REST API
    Given POST request is sent to the user REST API with the user details
    When API for user responds with a HTTP 201 status code
    Then returns the JSON object for the new user

  Scenario: Updating an existing user using the REST API
    Given PUT request is sent to the user REST API with the updated user details
    When API for user responds with a HTTP 200 status code
    Then returns the JSON object for the updated user

  Scenario: Deleting a user using the REST API
    Given DELETE request is sent to the user REST API with the ID
    Then API for user responds with a HTTP 200 status code