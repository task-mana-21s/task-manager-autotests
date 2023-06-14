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

public class UserStepDefinitions {

    private static final String BASE_URL = "http://localhost:8080";

    private static Response response;
    private static String jsonString;
    private static String userId;

    @Given("valid user API")
    public void validUserApi() {
        RestAssured.baseURI = BASE_URL;
    }

    @When("GET request is sent to the user REST API")
    public void getRequestsSent() {
        RequestSpecification request = RestAssured.given();
        response = request.get("/api/users");
    }

    @Then("API for user responds with a HTTP 200 status code")
    public void apiForUserRespondsWithAHttp200StatusCode() {
        Assert.assertEquals(200, response.getStatusCode());

    }

    @And("returns the JSON object for the specified user")
    public void returnsTheJsonObjectForTheSpecifiedUser() {
        jsonString = response.asString();
        List<Map<String, String>> userList = JsonPath.read(jsonString, "$._embedded.userList");
        Assert.assertTrue(userList.size() > 0);

/*        userId = String.valueOf(userList.get(0).get("id"));*/
    }

    @Given("valid user ID")
    public void validUserId() {
        userId = "1";
    }

    @When("GET request is sent to the user REST API with the ID")
    public void getRequestsSentWithTheId() {
        RequestSpecification request = RestAssured.given();
        response = request.get("/api/users/" + userId);
    }

    @And("returns user JSON object for the specified ID")
    public void returnsUserJsonObjectForTheSpecifiedId() {
        jsonString = response.asString();
        String userName = JsonPath.read(jsonString, "username");
        Assert.assertNotEquals("User name should not be empty",userName, "");
    }

    @Given("POST request is sent to the user REST API with the user details")
    public void postRequestsSentWithTheUserDetails() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        response = request.body("{" +
                        "  \"username\": \"Api Test123\"," +
                        "  \"password\": \"123\"," +
                        "  \"email\": \"test@test.gg\"" +
                        "}")
                .post("/api/register");
    }

    @When("API for user responds with a HTTP 201 status code")
    public void apiForUserRespondsWithAHttp201StatusCode() {
        Assert.assertEquals(201, response.getStatusCode());
    }

    @When("PUT request is sent to the user REST API with the updated user details")
    public void putRequestsSentWithTheUpdatedUserDetails() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        response = request.body("{" +
                        "  \"userId\": 1," +
                        "  \"username\": \"PutUser\"," +
                        "  \"password\": \"321\"," +
                        "  \"email\": \"test@put.ff\"" +
                        "}")
                .put("/api/users/1");
    }

    @And("returns the JSON object for the updated user")
    public void returnsTheJsonObjectForTheUpdatedUser() {
        jsonString = response.asString();
        String userName = JsonPath.read(jsonString, "username");
        Assert.assertEquals("Updated user name should match",userName, "PutUser");
    }

    @When("DELETE request is sent to the user REST API with the ID")
    public void deleteRequestsSentWithTheId() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.delete("/api/users/1");
    }


    @Then("returns the JSON object for the new user")
    public void returnsTheJSONObjectForTheNewUser() {
        jsonString = response.asString();
        String userName = JsonPath.read(jsonString, "username");
        Assert.assertEquals("User name should match",userName, "Api Test123");
    }

    @Then("API users responds with a null")
    public void apiUsersRespondsWithANull() {
        Assert.assertEquals(null, response.getStatusCode());
    }
}
