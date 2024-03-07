package stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static base_urls.MedunnaBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static stepdefinitions.UI_StepDefs.fakeRoomNumber;
import static stepdefinitions.UI_StepDefs.roomId;

        
public class API_StepDefs {

    Response response;

    @Given("send the request by id")
    public void send_the_request_by_id() {
        //Set the url
        //https://medunna.com/api/rooms/1455
        spec.pathParams("first", "api", "second", "rooms", "third", roomId);

        //Set the expected data

        //Send the request and get th response
        response = given(spec).get("{first}/{second}/{third}");
        response.prettyPrint();

    }

    @Then("validate the body")
    public void validate_the_body() {
        //Do assertion
        response
                .then()
                .statusCode(200)
                .body("roomNumber", equalTo(fakeRoomNumber),
                        "roomType", equalTo("DELUXE"),
                        "status", equalTo(true),
                        "price", equalTo(400.0F),
                        "description", equalTo("Created for e2e test"));

    }

}