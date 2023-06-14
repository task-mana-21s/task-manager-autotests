Feature: Testing a Status REST API
  Users should be able to submit GET and POST requests to a web service

  Scenario: Successfully fetching all status using the REST API
    Given valid status API
    When GET request is sent to the status REST API
    Then API for status responds with a HTTP 200 status code
    And returns the JSON object for the specified status

  Scenario: Successfully fetching a status using the REST API
    Given valid status API
    When GET request is sent to the status REST API with the ID
    Then API for status responds with a HTTP 200 status code
    And returns status JSON object for the specified ID

  Scenario: Adding a new status using the REST API
    Given POST request is sent to the status REST API with the status details
    When API for status responds with a HTTP 201 status code
    Then returns the JSON object for the new status

  Scenario: Updating an existing status using the REST API
    Given PUT request is sent to the status REST API with the updated status details
    When API for status responds with a HTTP 200 status code
    Then returns the JSON object for the updated status

  Scenario: Deleting a status using the REST API
    Given DELETE request is sent to the status REST API with the ID
    Then API for status responds with a HTTP 200 status code