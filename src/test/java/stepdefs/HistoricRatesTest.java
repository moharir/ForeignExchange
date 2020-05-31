package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.java.Log;
import org.junit.Assert;
import utilities.Utils;

import java.util.Date;


@Log
public class HistoricRatesTest extends Utils {
    RequestSpecification requestSpecification;
    Response response;
    String givenDate;

    @Given("the api for the past conversion rates")
    public void theApiForTheConversionRates() {
        requestSpecification = setContentType(ContentType.JSON, "Past");
    }

    @When("user enters the api with a given date {string}")
    public void userEntersTheApiWithAGivenDate(String specifiedDate) {
        givenDate = specifiedDate;
        response = requestSpecification.when().get(getBaseURI() + createSearchQueryPath(givenDate));
        setResponse(response);
    }

    @Then("validate the date in the response")
    public void validateTheDateInTheResponse() {
        Date newDate = getFormattedDate(givenDate);
        if (newDate.before(getCurrentDate())) {
            Assert.assertEquals(givenDate, getResponse().get("date").getAsString());
        } else {
            Assert.assertEquals(getDate(), getResponse().get("date").getAsString());
        }
    }

    @Then("validate the success status")
    public void validateTheSuccessStatus() {
        if(response.getStatusCode() == 200) {
            Assert.assertEquals(getSuccessStatusLine(), response.getStatusLine());
        }else{
            Assert.assertEquals(getErrorStatusLine(), response.getStatusLine());
        }
    }
}
