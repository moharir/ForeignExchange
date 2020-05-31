package utilities;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.java.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static io.restassured.RestAssured.*;

@Log
public class Utils {
    static Properties properties = new Properties();
    static Date currentDate;
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    JsonObject jsonObject;
    JsonArray jsonArray;
    Response response;

    //Global Setup Variables
    public static String path;
    public static String jsonPathTerm;

    //Get base URI
    public static String getBaseURI() {
        try {
            properties.load(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties"));
        } catch (IOException ioException) {
            log.severe("Unable to read the properties file: " + ioException.getMessage());
        }
        RestAssured.baseURI = properties.getProperty("baseURI");
        return baseURI;
    }

    //Get current date
    public static String getDate() {
        currentDate = new Date();
        return simpleDateFormat.format(currentDate);
    }

    //Get current date
    public static Date getCurrentDate() {
        return currentDate = new Date();
    }

    //Returns formatted date
    public static Date getFormattedDate(String date) {
        Date givenDate = null;
        try {
            givenDate = parseDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return givenDate;
    }

    public static Date parseDate(String date) throws ParseException {
        return simpleDateFormat.parse(date);
    }

    //Sets base path
    public static void setBasePath(String basePathTerm) {
        RestAssured.basePath = basePathTerm;
    }

    //Reset Base URI (after test)
    public static void resetBaseURI() {
        RestAssured.baseURI = null;
    }

    //Reset base path
    public static void resetBasePath() {
        RestAssured.basePath = null;
    }

    //Sets ContentType
    public static RequestSpecification setContentType(ContentType Type, String conversionType) {
        jsonPathTerm = conversionType;
        return given().contentType(Type);
    }

    //Created search query path for single parameter
    public static String createSearchQueryPath(String pathTerm, String param, String paramValue) {
        path = "/" + pathTerm + "?" + param + "=" + paramValue;
        return path;
    }

    //Created search query path for multiple parameters
    public static String createMultiQueryPath(String pathTerm, String baseValue, String symbolsValue) {
        path = "/" + pathTerm + "?" + "base" + "=" + baseValue + "&" + "symbols" + "=" + symbolsValue;
        return path;
    }

    //create search query path
    public static String createSearchQueryPath(String path) {
        if (jsonPathTerm.equalsIgnoreCase("latest"))
            path = "/latest" + "/" + path;
        else if (jsonPathTerm.equalsIgnoreCase("past"))
            path = "/" + path;
        return path;
    }

    //Returns the success status message
    public String getSuccessStatusLine() {
        return properties.getProperty("successStatus");
    }

    //Returns the error status message
    public String getErrorStatusLine() {
        return properties.getProperty("errorStatus");
    }

    //Returns the success status code
    public int getSuccessStatusCode() {
        return Integer.parseInt(properties.getProperty("successCode"));
    }


    //Returns the error message
    public String getErrorMessageForInvalidApi() {
        return properties.getProperty("error_invalid_api");
    }

    //Returns the error message for historic date
    public String getErrorMessageForHistoricDate() {
        return properties.getProperty("error_historic_dates");
    }

    //Returns the error status code
    public int getErrorStatusCode() {
        return Integer.parseInt(properties.getProperty("errorCode"));
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    //Returns the Response in JsonObject Format
    public JsonObject getResponse() {
        jsonObject = JsonParser.parseString(response.getBody().asString()).getAsJsonObject();
        return jsonObject;
    }

    //Returns the rate symbol
    public JsonObject getRateSymbol() {
        jsonObject = getResponse().getAsJsonObject().getAsJsonObject("rates");
        return jsonObject;
    }

    //Returns the Currency code
    //Need to add all other currency codes
    public String getCurrencyCode(String currency) {
        String code = "";
        switch (currency.toUpperCase()) {
            case "EURO":
                code = "EUR";
                break;
            case "USD":
                code = "USD";
                break;
            case "GBP":
                code = "GBP";
                break;
            case "INR":
                code = "INR";
                break;
        }
        return code;
    }
}