package org.example.Tests.e2e_integration;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.example.Base.BaseTest;
import org.example.POJO.Request.Booking;
import org.example.POJO.Response.BookingResponse;
import org.example.endpoints.API_Constants;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class TestIntegrationFlow4 extends BaseTest {

    // TestE2EFlow_04

    //  Test E2E Scenario 1

    //  1. Create a Booking -> bookingID

    // 4. Update the booking ( bookingID, Token) - Need to get the token, bookingID from above request

    // 5. Delete the Booking - Need to get the token, bookingID from above request

    @Test(groups= "qa",priority = 1)
    @Owner("Shriya")
    @Description("TC#INT1 - Step1. Verify that the Booking can be created")
    public void test_CreateBooking(ITestContext iTestContext){

        requestSpecification.basePath(API_Constants.CREATE_UPDATE_BOOKING_URL);

        response = RestAssured.given(requestSpecification)
                .when()
                .body(payloadmanager.CreatePayloadBookingAsString())
                .log().all().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        BookingResponse bookingResponse = payloadmanager.bookingResponseJava(response.asString());

        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(), "Jim");
        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());

        iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());


    }

    @Test(groups= "qa",dependsOnMethods = "test_CreateBooking")
    @Owner("Shriya")
    @Description("TC#INT1 - Step2. Verify Partial Updated booking by ID")
    public void test_UpdateBooking(ITestContext iTestContext) {

        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String token = getToken();
        iTestContext.setAttribute("token",token);

        String basePathPUTPATCH = API_Constants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid;
        System.out.println(basePathPUTPATCH);

        requestSpecification.basePath(basePathPUTPATCH);

        response = RestAssured.given(requestSpecification)
                .cookie("token",token)
                .body(payloadmanager.PartialUpdatepayloadAsString())
                .when()
                .log()
                .all()
                .patch();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        Booking booking = payloadmanager.getResponseFromJSON(response.asString());

        assertActions.verifyStringKeyNotNull(booking.getFirstname());
        assertActions.verifyStringKey(booking.getFirstname(), "Malhar");

    }

    @Test(groups= "qa",dependsOnMethods ="test_UpdateBooking")
    @Owner("Shriya")
    @Description("TC#INT1 - Step4. Delete the booking by ID")
    public void test_DeleteBooking(ITestContext iTestContext) {
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String token = (String) iTestContext.getAttribute("token");

        String basepathDELETE = API_Constants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid;

        requestSpecification.basePath(basepathDELETE).cookie("token", token);
        validatableResponse = RestAssured.given().spec(requestSpecification)
                .when().delete().then().log().all();
        validatableResponse.statusCode(201);
    }
}
