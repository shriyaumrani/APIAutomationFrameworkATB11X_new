package org.example.Tests.CRUD;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.example.Base.BaseTest;
import org.example.endpoints.API_Constants;
import org.testng.annotations.Test;

public class TestCreateToken extends BaseTest {
    @Test(groups="reg", priority = 1)
    @Owner("Shriya")
    @Description("TC#1 - Verify that token can be created")

    public void TestCreateToken_Post(){

        // preparation of request
        requestSpecification.basePath(API_Constants.AUTH_URL);
        // making request
        response = RestAssured
                .given(requestSpecification)
                .body(payloadmanager.setAuthPayload())
                .log()
                .all()
                .when()
                .post();
        // Extraction form json to java object
        String token = payloadmanager.getTokenFromJSON(response.asString());
        System.out.println(token);
        // validation
        assertActions.verifyStringKeyNotNull(token);



    }
}
