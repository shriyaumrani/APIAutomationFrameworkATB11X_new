package org.example.modules;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import org.example.POJO.Request.Auth;
import org.example.POJO.Request.Booking;
import org.example.POJO.Request.BookingDates;
import org.example.POJO.Response.BookingResponse;
import org.example.POJO.Response.TokenResponse;

public class Payloadmanager {

    Gson gson;
    Faker faker;

    // convert java object to json string to use as payload
    //serialization

    public String CreatePayloadBookingAsString() {
        Booking booking = new Booking();
        booking.setFirstname("Jim");
        booking.setLastname("Brown");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2018-01-01");
        bookingDates.setCheckout("2019-01-01");
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Breakfast");
        System.out.println(booking);

        gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        return jsonStringBooking;
    }

    //convert json response to java objet so that we can verify response body
    //DeSerialization

    public BookingResponse bookingResponseJava(String responseString) {
        gson = new Gson();
        BookingResponse bookingResponse = gson.fromJson(responseString, BookingResponse.class);
        return bookingResponse;
    }

    public String setAuthPayload() {

        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        gson = new Gson();
        String jsonPayloadString = gson.toJson(auth);
        System.out.println("Payload set to -> " + jsonPayloadString);
        return jsonPayloadString;

    }

    public String getTokenFromJSON(String tokenResponse) {

        gson = new Gson();
        TokenResponse tokenResponse1 = gson.fromJson(tokenResponse, TokenResponse.class);
        return tokenResponse1.getToken();
    }

    public String CreatePayloadBookingAsStringWrongBody() {
        Booking booking = new Booking();
        booking.setFirstname(" ");
        booking.setLastname("Brown");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2018-01-01");
        bookingDates.setCheckout("2019-01-01");
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Breakfast");
        System.out.println(booking);

        gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        return jsonStringBooking;
    }

    public String CreatePayloadBookingAsStringFakerJS() {
        Faker faker = new Faker();
        Booking booking = new Booking();
        booking.setFirstname(faker.name().firstName());
        booking.setLastname(faker.name().lastName());
        booking.setTotalprice(faker.random().nextInt(1, 1000));
        booking.setDepositpaid(faker.random().nextBoolean());

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2019-01-01");
        bookingDates.setCheckout("2019-01-01");
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Breakfast");
        System.out.println(booking);

        gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        return jsonStringBooking;
    }

    public Booking getResponseFromJSON(String getResponse) {

        gson = new Gson();
        Booking booking = gson.fromJson(getResponse, Booking.class);
        return booking;

    }

    public String fullUpdatepayloadAsString() {
        Booking booking = new Booking();
        booking.setFirstname("Lucky");
        booking.setLastname("Brown");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2018-01-01");
        bookingDates.setCheckout("2019-01-01");
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Breakfast");
        System.out.println(booking);
        return gson.toJson(booking);
    }

    public String PartialUpdatepayloadAsString() {
        Booking booking = new Booking();
        booking.setFirstname("Malhar");
        booking.setLastname("Brown");
        return gson.toJson(booking);
    }


}


