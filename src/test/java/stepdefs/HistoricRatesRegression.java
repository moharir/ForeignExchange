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
public class HistoricRatesRegression extends Utils {
    RequestSpecification requestSpecification;
    Response response;

    @Given("The api for the historic conversion rates")
    public void theApiForTheHistoricConversionRates() {
        requestSpecification = setContentType(ContentType.JSON, "Past");
    }

    @When("I call the historic rates API for date {string}")
    public void iCallTheHistoricRatesAPI(String date) {
        response = requestSpecification.when().get(getBaseURI() + createSearchQueryPath(date));
        setResponse(response);
    }

    @When("I call the historic rates API for date {string} with {string} as {string}")
    public void iCallTheHistoricRatesAPIWithBaseAsAndSymbolsAs(String date, String param, String value) {
        response = requestSpecification.when().get(getBaseURI() + createSearchQueryPath(date, param, value));
        setResponse(response);
    }

    @Then("I check the error response")
    public void iCheckTheErrorResponse() {
        Assert.assertEquals(response.getStatusCode(), getErrorStatusCode());
    }

    @Then("I check the response")
    public void iCheckTheResponse() {
        Assert.assertEquals(response.getStatusCode(), getSuccessStatusCode());
    }

    @And("I validate the error message")
    public void iValidateTheErrorMessage() {
        Assert.assertEquals(getResponse().get("error").getAsString(), getErrorMessageForHistoricDate());
    }

    @Then("I validate the date as {string} and symbol as {string}")
    public void iCheckTheResponse(String date, String symbol) {
        Assert.assertEquals(getResponse().get("date").getAsString(), date);
        String params[] = symbol.split(",");
        for (String s : params) {
            Assert.assertTrue(getRateSymbol().has(s));
        }
    }

    @And("I validate the base as {string}")
    public void iValidateTheBaseAs(String base) {
        Assert.assertEquals(getResponse().get("base").getAsString(), getCurrencyCode(base));
    }

    @When("I call the historic rates API for date {string} with base as {string} and symbols as {string}")
    public void iCallTheHistoricRatesAPIForDateWithBaseAsAndSymbolsAs(String date, String baseValue, String symbolValue) {
        response = requestSpecification.when().get(getBaseURI() + createMultiQueryPath(date, baseValue, symbolValue));
        setResponse(response);
    }

    @And("I validate the base as {string} and symbols as {string}")
    public void iValidateTheBaseAsAndSymbolsAs(String baseValue, String symbolsValue) {
        Assert.assertEquals(getResponse().get("base").getAsString(), getCurrencyCode(baseValue));
        String params[] = symbolsValue.split(",");
        for (String s : params) {
            Assert.assertTrue(getRateSymbol().has(s));
        }
    }
}
