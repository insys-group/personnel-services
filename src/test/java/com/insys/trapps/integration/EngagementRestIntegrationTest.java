package com.insys.trapps.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insys.trapps.model.Engagement;
import com.insys.trapps.model.EngagementOpening;
import com.insys.trapps.util.Utils;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;

/**
 * Created by vnalitkin on 11/22/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class EngagementRestIntegrationTest {
    @Value("${local.server.port}")
    private int port;

    @Value("${spring.data.rest.basePath}")
    private String basePath;

    private final String ENG_PATH = "/engagements";
    private final String ENG_OPEN_PATH = "/engagementOpenings";

    @Before
    public void setup() {
        RestAssured.port = port;
    }

    @After
    public void cleanup() {
    }


    @Test
    public void testCreateEngagement() {
        Engagement engagement = Engagement.builder().comments("Comcast Engagement").version(1L).build();
        given()
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
        Engagement engagement = Engagement.builder().comments("Aramark Engagement").version(1L).build();
        String url =
                given()
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
                .contentType("application/json")
                .body(EngagementOpening.builder()
                        .comments("engagement open 1")
                        .rate(BigDecimal.ONE)
                        .engagement(engagement)
                        .version(1L)
                        .build())
                .log().everything()
                .when()
                .post(basePath + ENG_OPEN_PATH)
                .then()
                .statusCode(HttpStatus.CREATED.value());

        given()
                .contentType("application/json")
                .body(EngagementOpening.builder()
                        .comments("engagement open 2")
                        .rate(BigDecimal.TEN)
                        .engagement(engagement)
                        .version(1L)
                        .build())
                .log().everything()
                .when()
                .post(basePath + ENG_OPEN_PATH)
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void testGetEngagements() {
        when()
                .get(basePath + ENG_PATH)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void testGetEngagementOpenings() {
        when()
                .get(basePath + ENG_OPEN_PATH)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

}
