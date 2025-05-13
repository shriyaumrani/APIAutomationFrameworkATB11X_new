package org.example.Tests.sample;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class testIntegration_sample {

    //Create a booking, create a token
    // verify Get booking
    // Update the booking
    // delete a booking

    @Test(groups= "qa",priority = 1)
    @Owner("Shriya")
    @Description("TC#INT1 - Step1. Verify that the Booking can be created")
    public void test_CreateBooking(){
        Assert.assertTrue(true);
    }
    @Test(groups= "qa",priority = 2)
    @Owner("Shriya")
    @Description("TC#INT1 - Step2. Verify that the Booking by ID")
    public void test_GetBooking() {
        Assert.assertTrue(true);
    }
    @Test(groups= "qa",priority = 3)
    @Owner("Shriya")
    @Description("TC#INT1 - Step3. Verify Updated booking by ID")
    public void test_UpdateBooking() {
        Assert.assertTrue(true);
    }

    @Test(groups= "qa",priority = 4)
    @Owner("Shriya")
    @Description("TC#INT1 - Step4. Delete the booking by ID")
    public void test_DeleteBooking() {
        Assert.assertTrue(true);
    }
}
