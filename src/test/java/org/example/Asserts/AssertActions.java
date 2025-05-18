package org.example.Asserts;

import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;
import static org.assertj.core.api.Assertions.*;

public class AssertActions {

    public void verifyResponseBody(String actual, String expected, String description){
        assertEquals(actual,expected,description);
    }

    public void verifyResponseBody(int actual, int expected, String description){
        assertEquals(actual,expected,description);
    }

    public void verifyStatusCode(Response response, Integer expected){
        assertEquals(response.getStatusCode(),expected);

    }
    public void verifyStringKey(String KeyExpect, String KeyActual){
        //Assert j

        assertThat(KeyExpect).isNotBlank();
        assertThat(KeyExpect).isNotNull();
        assertThat(KeyExpect).isEqualTo(KeyActual);
    }
    public void verifyStringKeyNotNull(Integer KeyExpect){
        assertThat(KeyExpect).isNotNull();
    }
    public void verifyStringKeyNotNull(String KeyExpect){
        assertThat(KeyExpect).isNotNull();
    }



}
