package com.accesshq;

import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiSuiteTest {
    String authKey = "a961b8ec-a8f3-4f67-8189-bd66f132cc29";
    String postSearch = "https://digitalapi.auspost.com.au/postcode/search.json";
    String postcode = "localities.locality.postcode";
    @Test
    public void AusPostStatusTest() {
        given().relaxedHTTPSValidation().
            header("auth-key", authKey).
            params("q", "North Rocks").
            params("state", "NSW").
        when().
            get(postSearch).
        then().
            assertThat().statusCode(200);
    }

    @Test
    public void AusPostLocationSingleTest() {
        given().relaxedHTTPSValidation().
            header("auth-key", authKey).
            params("q","North Rocks").
            params("state", "NSW").
        when().
            get(postSearch).
        then().
            assertThat().body(postcode,equalTo(2151));
    }

    @Test
    public void AusPostLocationMultiTest() {
        given().relaxedHTTPSValidation().
            header("auth-key",authKey).
            params("q","Parramatta").
            params("state","NSW").
        when().
            get(postSearch).
        then().
            assertThat().body(postcode, hasItem(2151));
    }
}
