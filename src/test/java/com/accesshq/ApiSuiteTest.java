package com.accesshq;

import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiSuiteTest {

    @Test
    public void AusPostStatusTest() {
        given().relaxedHTTPSValidation().
            header("auth-key", "a961b8ec-a8f3-4f67-8189-bd66f132cc29").
            params("q", "North Rocks").
            params("state", "NSW").
        when().
            get("https://digitalapi.auspost.com.au/postcode/search").
        then().
            assertThat().statusCode(200);
    }

    @Test
    public void AusPostLocationTest() {
        given().relaxedHTTPSValidation().
            header("auth-key", "a961b8ec-a8f3-4f67-8189-bd66f132cc29").
            params("q","Parramatta").
            params("state", "NSW").
        when().
            get("https://digitalapi.auspost.com.au/postcode/search.json").
        then().
            assertThat().body("localities.locality.postcode",equalTo(2151));
    }
}
