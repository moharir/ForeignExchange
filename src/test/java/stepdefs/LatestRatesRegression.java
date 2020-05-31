package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.java.Log;
import org.junit.Assert;
import utilities.Utils;

@Log
public class LatestRatesRegression extends Utils {
    RequestSpecification requestSpecification;
    Response response;

    @Given("The api for the current conversion rates")
    public void theAPIForTheCurrentConversionRates() {
        requestSpecification = setContentType(ContentType.JSON, "Latest");
    }

    @When("I call the latest rates API")
    public void iCallTheLatestRatesAPI() {
        response = requestSpecification.when().get(getBaseURI() + createSearchQueryPath(""));
        setResponse(response);
    }

    @Then("I get the response")
    public void iGetTheReponse() {
        Assert.assertEquals(response.getStatusCode(), getSuccessStatusCode());
    }

    @Then("I check the error code")
    public void iCheckTheErrorCode() {
        Assert.assertEquals(response.getStatusCode(), getErrorStatusCode());
    }

    @And("I validate the base should be {string}")
    public void iValidateTheBaseShouldBe(String base) {
        Assert.assertEquals(getResponse().get("base").getAsString(), getCurrencyCode(base));
    }

    @And("I validate the date should be current")
    public void iValidateTheDateShouldBeCurrent() {
        Assert.assertEquals(getResponse().get("date").getAsString(), getDate());
    }

    @When("I call the latest rates API with {string} as {string}")
    public void iCallTheLatestRatesAPIWith(String param, String value) {
        response = requestSpecification.when().get(getBaseURI() + createSearchQueryPath("latest", param, value));
        setResponse(response);
    }

    @When("I call the latest rates API with base as {string} and symbols as {string}")
    public void iCallTheLatestRatesAPIWithBaseAsAndSymbolsAs(String base, String symbols) {
        response = requestSpecification.when().get(getBaseURI() + createMultiQueryPath("latest", base, symbols));
        setResponse(response);
    }

    @And("I validate the symbol should be {string}")
    public void iValidateTheSymbolShouldBe(String param) {
        String params[] = param.split(",");
        for (String s : params) {
            Assert.assertTrue(getRateSymbol().has(s));
        }
    }

    @And("I validate base as {string} and symbols as {string}")
    public void iValidateAs(String baseValue, String symbolsValue) {
        Assert.assertEquals(getResponse().get("base").getAsString(), getCurrencyCode(baseValue));
        String params[] = symbolsValue.split(",");
        for (String s : params) {
            Assert.assertTrue(getRateSymbol().has(s));
        }
    }

    @And("I validate error message {string} for {string}")
    public void iValidateErrorMessageFor(String value, String param) {
        Assert.assertEquals(getResponse().get("error").getAsString(), "Base '" + value + "' is not supported.");
    }
}
