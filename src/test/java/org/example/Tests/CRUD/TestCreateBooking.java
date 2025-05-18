package org.example.Tests.CRUD;

import com.google.gson.Gson;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.example.Base.BaseTest;
import org.example.POJO.Request.Booking;
import org.example.POJO.Request.BookingDates;
import org.example.POJO.Response.BookingResponse;
import org.example.endpoints.API_Constants;
import org.testng.annotations.Test;

public class TestCreateBooking extends BaseTest {
    @Test(groups="reg", priority = 1)
    @Owner("Shriya")
    @Description("TC#1 - Verify that booking can be created")
    public void testCreateBookingPOST_Positive(){
        // set up a request

        requestSpecification.basePath(API_Constants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured
                .given(requestSpecification)
                .body(payloadmanager.CreatePayloadBookingAsString())
                .log()
                .all()
                .when()
                .post();

        System.out.println(response.asString());

        //extraction

        BookingResponse bookingResponse = payloadmanager.bookingResponseJava(response.asString());

        // verification

        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(),"Jim");

    }

    @Test(groups="reg", priority = 1)
    @Owner("Shriya")
    @Description("TC#2 - Verify that booking is not created (when playload is null)")
    public void testCreateBookingPOST_Negative(){

        requestSpecification.basePath(API_Constants.CREATE_UPDATE_BOOKING_URL);

        response = RestAssured
                .given(requestSpecification)
                .body("{}")
                .log()
                .all()
                .post();

        System.out.println(response.asString());

        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(500);

    }

    @Test(groups="reg", priority = 1)
    @Owner("Shriya")
    @Description("TC#3 - Verify that booking is not created (when playload is wrong)")
    public void testCreateBookingPOST_Negative_1(){

        requestSpecification.basePath(API_Constants.CREATE_UPDATE_BOOKING_URL);

        response = RestAssured
                .given(requestSpecification)
                .body(payloadmanager.CreatePayloadBookingAsStringWrongBody())
                .log()
                .all()
                .post();

        System.out.println(response.asString());

        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);

    }
    @Test(groups= "reg", priority = 3)
    @Owner("Shriya")
    @Description("TC#4 - verify that booking is created when payload is random")

    public void testCreateBookingPOST_POSITIVE_RANDOM_DATA(){
        requestSpecification.basePath(API_Constants.CREATE_UPDATE_BOOKING_URL);

        response = RestAssured
                .given(requestSpecification)
                .body(payloadmanager.CreatePayloadBookingAsStringFakerJS())
                .when()
                .log()
                .all()
                .post();

        System.out.println(response.asString());

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

    }


}
