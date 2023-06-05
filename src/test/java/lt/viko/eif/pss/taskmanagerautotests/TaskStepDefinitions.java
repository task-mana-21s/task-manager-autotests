package lt.viko.eif.pss.taskmanagerautotests;

import com.jayway.jsonpath.JsonPath;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class TaskStepDefinitions {

    private static final String BASE_URL = "http://localhost:8080";

    private static Response response;
    private static String jsonString;
    private static String taskId;

    @Given("valid API")
    public void anExampleScenario() {
        RestAssured.baseURI = BASE_URL;
    }

    @When("GET request is sent to the task REST API")
    public void getRequestsSent() {
        RequestSpecification request = RestAssured.given();
        response = request.get("/api/tasks");
    }

    @Then("API responds with a HTTP 200 status code")
    public void apiRespondsWithAHttp200StatusCode() {
        Assert.assertEquals(200, response.getStatusCode());

    }

    @And("returns the JSON object for the specified task")
    public void returnsTheJsonObjectForTheSpecifiedTask() {
        jsonString = response.asString();
        List<Map<String, String>> taskList = JsonPath.read(jsonString, "$._embedded.taskList");
        Assert.assertTrue(taskList.size() > 0);


        taskId = String.valueOf(taskList.get(0).get("id"));
    }

    @Given("valid task ID")
    public void validTaskId() {
        System.out.println("Hello World");
    }

    @When("GET request is sent to the task REST API with the ID")
    public void getRequestsSentWithTheId() {
    }

    @Given("new task with a unique ID and all required fields")
    public void newTaskWithAUniqueIdAndAllRequiredFields() {
        System.out.println("Hello World");
    }

    @When("POST request is sent to the task REST API with the task details")
    public void postRequestsSentWithTheTaskDetails() {
    }

    @Then("API responds with a HTTP 201 status code")
    public void apiRespondsWithAHttp201StatusCode() {
    }

    @And("task is added to the database")
    public void taskIsAddedToTheDatabase() {
    }

    @Given("existing task with a valid ID and updated fields")
    public void existingTaskWithAValidIdAndUpdatedFields() {
        System.out.println("Hello World");
    }
    @When("PUT request is sent to the task REST API with the updated task details")
    public void putRequestsSentWithTheUpdatedTaskDetails() {
    }

    @And("task is updated in the database")
    public void taskIsUpdatedInTheDatabase() {
    }

    @And("returns the JSON object for the updated task")
    public void returnsTheJsonObjectForTheUpdatedTask() {
    }

    @Given("existing task with a valid ID")
    public void existingTaskWithAValidId() {
        System.out.println("Hello World");
    }

    @When("DELETE request is sent to the task REST API with the ID")
    public void deleteRequestsSentWithTheId() {
    }

    @Then("API responds with a HTTP 204 status code")
    public void apiRespondsWithAHttp204StatusCode() {
    }

    @And("task is deleted from the database")
    public void taskIsDeletedFromTheDatabase() {
    }

    @And("JSON object is returned by the API")
    public void jsonObjectIsReturnedByTheApi() {
    }
}
