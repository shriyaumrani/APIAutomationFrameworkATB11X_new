package org.example.Base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.Filter;
import io.restassured.http.*;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.*;
import org.example.Asserts.AssertActions;
import org.example.endpoints.API_Constants;
import org.example.modules.Payloadmanager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.security.KeyStore;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class BaseTest {
    //common to all testcases
    // URL
    //contenttype

    public RequestSpecification requestSpecification;
    public AssertActions assertActions;
    public Payloadmanager payloadmanager;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;

    @BeforeTest
    public void setup() {
        System.out.println("Starting the test");
        payloadmanager = new Payloadmanager();
        assertActions = new AssertActions();

//        requestSpecification = RestAssured.given();
//        requestSpecification.baseUri(API_Constants.Base_URL);
//        requestSpecification.contentType(ContentType.JSON);


        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(API_Constants.Base_URL)
                .addHeader("Content-Type", "application/json")
                .build().log().all();
    }

    public String getToken(){
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri(API_Constants.Base_URL);
        requestSpecification.basePath(API_Constants.AUTH_URL);
        String payload = payloadmanager.setAuthPayload();

        response = requestSpecification.contentType(ContentType.JSON).body(payload).when().post();

        String token = payloadmanager.getTokenFromJSON(response.asString());
        return token;

    }
    public Integer getbookingid(){

        requestSpecification= RestAssured.given();
        requestSpecification.baseUri(API_Constants.Base_URL)
                .basePath(API_Constants.CREATE_UPDATE_BOOKING_URL);

        response = requestSpecification.contentType(ContentType.JSON).when().log().all().get();
        List<Integer>bookingids = response.jsonPath().getList("bookingid");
        Integer bookingid = bookingids.get(0);
        return bookingid;

    }




}