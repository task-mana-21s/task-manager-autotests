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

import java.io.UnsupportedEncodingException;
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

/*        taskId = String.valueOf(taskList.get(0).get("id"));*/
    }

    @Given("valid task ID")
    public void validTaskId() {
        taskId = "1";
    }

    @When("GET request is sent to the task REST API with the ID")
    public void getRequestsSentWithTheId() {
        RequestSpecification request = RestAssured.given();
        response = request.get("/api/tasks/" + taskId);
    }

    @And("returns JSON object for the specified ID")
    public void returnsJsonObjectForTheSpecifiedId() {
        jsonString = response.asString();
        String taskName = JsonPath.read(jsonString, "name");
        Assert.assertNotEquals("Task name should not be empty",taskName, "");
    }

    @Given("POST request is sent to the task REST API with the task details")
    public void postRequestsSentWithTheTaskDetails() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        response = request.body("{" +
                        "  \"name\": \"Post Test\"," +
                        "  \"description\": \"Test description\"" +
                        "}")
                .post("/api/tasks");
    }

    @When("API responds with a HTTP 201 status code")
    public void apiRespondsWithAHttp201StatusCode() {
        Assert.assertEquals(201, response.getStatusCode());
    }

    @When("PUT request is sent to the task REST API with the updated task details")
    public void putRequestsSentWithTheUpdatedTaskDetails() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        response = request.body("{" +
                        "  \"name\": \"Put Test\"," +
                        "  \"description\": \"Test description\"" +
                        "}")
                .put("/api/tasks/1");
    }

    @And("returns the JSON object for the updated task")
    public void returnsTheJsonObjectForTheUpdatedTask() {
        jsonString = response.asString();
        String taskName = JsonPath.read(jsonString, "name");
        Assert.assertEquals("Updated task name should match",taskName, "Put Test");
    }

    @When("DELETE request is sent to the task REST API with the ID")
    public void deleteRequestsSentWithTheId() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.delete("/api/tasks/2");
    }


    @Then("returns the JSON object for the new task")
    public void returnsTheJSONObjectForTheNewTask() {
        jsonString = response.asString();
        String taskName = JsonPath.read(jsonString, "name");
        Assert.assertEquals("Task name should match",taskName, "Post Test");
    }

}
