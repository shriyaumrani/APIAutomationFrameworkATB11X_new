package org.example.Tests.CRUD;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.example.Base.BaseTest;
import org.example.endpoints.API_Constants;
import org.testng.annotations.Test;

public class TestHealthCheck extends BaseTest {
    @Test
    @Owner("Shriya")
    @Description("TC# 1 - Verify Heath")
    public void TestHealthCheck(){
        requestSpecification.basePath(API_Constants.PING_URL);

        response = RestAssured
                .given(requestSpecification)
                .when()
                .log()
                .all()
                .get();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);




    }
}
