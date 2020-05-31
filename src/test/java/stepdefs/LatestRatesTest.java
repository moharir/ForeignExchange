package stepdefs;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.java.Log;
import org.junit.Assert;
import utilities.Utils;

@Log
public class LatestRatesTest extends Utils {
    RequestSpecification requestSpecification;
    Response response;

    @Given("the api for the latest conversion rates")
    public void theApiForTheConversionRates() {
        requestSpecification = setContentType(ContentType.JSON, "Latest");
    }

    @When("user enters the api")
    public void userEntersTheApi() {
        response = requestSpecification.when().get(getBaseURI() + createSearchQueryPath(""));
        setResponse(response);
    }

    @Then("validate the success status code")
    public void validateTheSuccessStatusCode() {
        Assert.assertEquals(response.getStatusCode(), getSuccessStatusCode());
    }

    @When("user enters the invalid api")
    public void userEntersTheInvalidApi() {
        response = requestSpecification.when().get(getBaseURI() + "/");
        setResponse(response);
    }

    @Then("user should see the appropriate error message")
    public void userShouldSeeTheAppropriateErrorMessage() {
        Assert.assertEquals(response.getStatusCode(), getErrorStatusCode());
        Assert.assertEquals(getResponse().get("error").getAsString(), getErrorMessageForInvalidApi());
    }

    @Then("validate the response")
    public void validateTheResponse() {
        if (response.getStatusCode() == 200) {
            Assert.assertEquals(getSuccessStatusLine(), response.getStatusLine());
        } else {
            Assert.assertEquals(getErrorStatusLine(), response.getStatusLine());
        }
    }
}
