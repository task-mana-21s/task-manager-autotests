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

public class StatusStepDefinitions {

    private static final String BASE_URL = "http://localhost:8080";

    private static Response response;
    private static String jsonString;
    private static String statusId;

    @Given("valid status API")
    public void validStatusAPI() {
        RestAssured.baseURI = BASE_URL;
    }

    @When("GET request is sent to the status REST API")
    public void getRequestsSent() {
        RequestSpecification request = RestAssured.given();
        response = request.get("/api/status");
    }

    @Then("API for status responds with a HTTP 200 status code")
    public void apiForStatusRespondsWithAHttp200StatusCode() {
        Assert.assertEquals(200, response.getStatusCode());

    }

    @And("returns the JSON object for the specified status")
    public void returnsTheJsonObjectForTheSpecifiedStatus() {
        jsonString = response.asString();
        List<Map<String, String>> statusList = JsonPath.read(jsonString, "$._embedded.statusList");
        Assert.assertTrue(statusList.size() > 0);

/*        statusId = String.valueOf(statusList.get(0).get("id"));*/
    }

    @Given("valid status ID")
    public void validStatusId() {
        statusId = "1";
    }

    @When("GET request is sent to the status REST API with the ID")
    public void getRequestsSentWithTheId() {
        RequestSpecification request = RestAssured.given();
        response = request.get("/api/statuses/1");
    }

    @And("returns status JSON object for the specified ID")
    public void returnsStatusJsonObjectForTheSpecifiedId() {
        jsonString = response.asString();
        String statusName = JsonPath.read(jsonString, "status");
        Assert.assertNotEquals("Status name should not be empty", statusName, "");
    }

    @Given("POST request is sent to the status REST API with the status details")
    public void postRequestsSentWithTheStatusDetails() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        response = request.body("{" +
                        "  \"id\": 99," +
                        "  \"status\": \"Status API\"" +
                        "}")
                .post("/api/statuses");
    }

    @When("API for status responds with a HTTP 201 status code")
    public void apiForStatusRespondsWithAHttp201StatusCode() {
        Assert.assertEquals(201, response.getStatusCode());
    }

    @When("PUT request is sent to the status REST API with the updated status details")
    public void putRequestsSentWithTheUpdatedStatusDetails() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        response = request.body("{" +
                        "  \"status\": \"Test status\"" +
                        "}")
                .put("/api/statuses/1");
    }

    @And("returns the JSON object for the updated status")
    public void returnsTheJsonObjectForTheUpdatedStatus() {
        jsonString = response.asString();
        String statusName = JsonPath.read(jsonString, "status");
        Assert.assertEquals("Updated status name should match",statusName, "Test status");
    }

    @When("DELETE request is sent to the status REST API with the ID")
    public void deleteRequestsSentWithTheId() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.delete("/api/statuses/3");
    }


    @Then("returns the JSON object for the new status")
    public void returnsTheJSONObjectForTheNewStatus() {
        jsonString = response.asString();
        String statusName = JsonPath.read(jsonString, "status");
        Assert.assertEquals("status name should match",statusName, "Status API");
    }

}
