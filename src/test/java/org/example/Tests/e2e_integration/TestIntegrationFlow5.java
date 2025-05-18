package org.example.Tests.e2e_integration;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.example.Base.BaseTest;
import org.example.POJO.Request.Booking;
import org.example.endpoints.API_Constants;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class TestIntegrationFlow5 extends BaseTest {

    // TestE2EFlow_05

    //  Test E2E Scenario 5
    // 1. Delete the Booking - Need to get the token, bookingID from get booking above request
    // 3.Update booking


    @Test(groups= "qa",priority = 1)
    @Owner("Shriya")
    @Description("TC#INT1 - Step1. Delete the booking by ID")
    public void test_DeleteBooking(ITestContext iTestContext) {
        Integer bookingid = getbookingid();
        iTestContext.setAttribute("bookingid", bookingid);
        String token = getToken();
        iTestContext.setAttribute("token",token);

        String basepathDELETE = API_Constants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid;

        requestSpecification.basePath(basepathDELETE).cookie("token", token);
        validatableResponse = RestAssured.given().spec(requestSpecification)
                .when().delete().then().log().all();
        validatableResponse.statusCode(201);
    }

    @Test(groups= "qa",dependsOnMethods = "test_DeleteBooking")
    @Owner("Shriya")
    @Description("TC#INT1 - Step3. Verify Updated booking by ID")
    public void test_UpdateBooking(ITestContext iTestContext) {

        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String token = (String) iTestContext.getAttribute("token");


        String basePathPUTPATCH = API_Constants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid;
        System.out.println(basePathPUTPATCH);

        requestSpecification.basePath(basePathPUTPATCH);

        response = RestAssured.given(requestSpecification)
                .cookie("token",token)
                .body(payloadmanager.fullUpdatepayloadAsString())
                .when()
                .log()
                .all()
                .put();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(405);



    }

}




