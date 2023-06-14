Feature: Testing a Task REST API
  Users should be able to submit GET and POST requests to a web service

  Scenario: Successfully fetching all tasks using the REST API
    Given valid API
    When GET request is sent to the task REST API
    Then API responds with a HTTP 200 status code
    And returns the JSON object for the specified task

  Scenario: Successfully fetching a task using the REST API
    Given valid task ID
    When GET request is sent to the task REST API with the ID
    Then API responds with a HTTP 200 status code
    And returns JSON object for the specified ID

  Scenario: Adding a new task using the REST API
    Given POST request is sent to the task REST API with the task details
    When API responds with a HTTP 201 status code
    Then returns the JSON object for the new task

  Scenario: Updating an existing task using the REST API
    Given PUT request is sent to the task REST API with the updated task details
    When API responds with a HTTP 200 status code
    Then returns the JSON object for the updated task

  Scenario: Deleting a task using the REST API
    Given DELETE request is sent to the task REST API with the ID
    Then API responds with a HTTP 200 status code