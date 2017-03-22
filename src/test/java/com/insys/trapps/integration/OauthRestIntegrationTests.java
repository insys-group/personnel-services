package com.insys.trapps.integration;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class OauthRestIntegrationTests extends TestCaseAuthorization {

    @Test
    public void testOauth2() throws Exception {
        given().auth().basic(PROP_CLIENTID, PROP_SECRET)
                .log().everything()
                .when()
                .contentType("application/json")
                .queryParam("grant_type", "password")
                .queryParam("username", USERNAME)
                .queryParam("password", PASSWORD)
                .post(OAUTH_PATH)
                .then()
                .statusCode(HttpStatus.OK.value());
    }


}
