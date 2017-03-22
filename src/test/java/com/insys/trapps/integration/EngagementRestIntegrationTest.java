package com.insys.trapps.integration;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.insys.trapps.model.Engagement;
import com.insys.trapps.model.EngagementOpening;
import com.insys.trapps.util.Utils;
import com.jayway.restassured.RestAssured;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class EngagementRestIntegrationTest extends TestCaseAuthorization {

    @Value("${spring.data.rest.basePath}")
    private String basePath;

    private final String ENG_PATH = "/engagements";
    private final String ENG_OPEN_PATH = "/engagementOpenings";

    @Test
    public void testCreateEngagement() {
        Engagement engagement = Engagement.builder().comments("Comcast Engagement").build();
        given()
                .auth().oauth2(access_token)
                .contentType("application/json")
                .body(engagement)
                .log().everything()
        .when()
                .post(basePath + ENG_PATH)
        .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void testCreateEngagementWithOpeninig() throws Exception {
        Engagement engagement = Engagement.builder().comments("Aramark Engagement")
        		//.version(1L)
        		.build();
        String url =
                given()
                        .auth().oauth2(access_token)
                        .contentType("application/json")
                        .body(engagement)
                        .log().everything()
                .when()
                        .post(basePath + ENG_PATH)
                .then()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract().jsonPath().get("_links.self.href").toString();

        engagement.setId(Utils.getId(url));

        given()
                .auth().oauth2(access_token)
                .contentType("application/json")
                .body(EngagementOpening.builder()
                        .comments("engagement open 1")
                        .rate(BigDecimal.ONE)
                        .engagement(engagement)
                        .build())
                .log().everything()
        .when()
                .post(basePath + ENG_OPEN_PATH)
        .then()
                .statusCode(HttpStatus.CREATED.value());

        given()
                .auth().oauth2(access_token)
                .contentType("application/json")
                .body(EngagementOpening.builder()
                        .comments("engagement open 2")
                        .rate(BigDecimal.TEN)
                        .engagement(engagement)
                        .build())
                .log().everything()
        .when()
                .post(basePath + ENG_OPEN_PATH)
        .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void testGetEngagements() {
        given()
                .auth().oauth2(access_token)
        .when()
                .get(basePath + ENG_PATH)
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void testGetEngagementOpenings() {
        given()
                .auth().oauth2(access_token)
        .when()
                .get(basePath + ENG_OPEN_PATH)
        .then()
                .statusCode(HttpStatus.OK.value());
    }

}
