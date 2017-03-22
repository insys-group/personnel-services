package com.insys.trapps.integration;


import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;

import java.sql.Timestamp;
import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insys.trapps.model.Opportunity;
import com.insys.trapps.model.OpportunityStep;
import com.insys.trapps.util.Utils;
import com.jayway.restassured.RestAssured;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class OpportunityRestIntegrationTests extends TestCaseAuthorization {

    @Value("${spring.data.rest.basePath}")
    private String basePath;

    private final String OPP_PATH = "/opportunities";
    private final String OPP_STEP_PATH = "/opportunitySteps";

    @Test
    public void testCreateOpportunity() {
        Opportunity opportunity = Opportunity.builder()
                .name("Opportunity 1")
                .comments("Comcast Opportunity").build();
        given()
                .auth().oauth2(access_token)
                .contentType("application/json")
                .body(opportunity)
                .log().everything()
        .when()
                .post(basePath + OPP_PATH)
        .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void testCreateOpportunityWithSteps() throws Exception {
        Opportunity opportunity = Opportunity.builder()
                .name("Opportunity 1")
                .comments("Aramark Opportunity")
        		.build();
        String url =
        given()
                .auth().oauth2(access_token)
                .contentType("application/json")
                .body(opportunity)
                .log().everything()
        .when()
                .post(basePath + OPP_PATH)
        .then()
                .log().everything()
                .statusCode(HttpStatus.CREATED.value())
                .extract().jsonPath().get("_links.self.href").toString();

        opportunity.setId(Utils.getId(url));

        given()
                .auth().oauth2(access_token)
                .contentType("application/json")
                .body(OpportunityStep.builder().comments("Step 1").stepTimestamp(Timestamp.valueOf(LocalDate.now().atStartOfDay())).build())
                .log().everything()
        .when()
                .post(basePath + OPP_STEP_PATH)
        .then()
                .log().everything()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void testCreateOpportunityWithContacts() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Opportunity opportunity = Opportunity.builder()
                .name("Opportunity 1")
                .comments("Aramark Opportunity").build();

        log.debug("Creating opportunity = " + mapper.writeValueAsString(opportunity));
        String url =
        given()
                .auth().oauth2(access_token)
                .contentType("application/json")
                .body(opportunity)
                .log().everything()
        .when()
                .post(basePath + OPP_PATH)
        .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract().jsonPath().get("_links.self.href").toString();

        given()
                .auth().oauth2(access_token)
                .contentType("application/json")
                .body(OpportunityStep.builder()
                        .comments("Step 1")
                        .stepTimestamp(Timestamp.valueOf(LocalDate.now().atStartOfDay()))
                        .build()
                )
                .log().everything()
        .when()
                .post(basePath + OPP_STEP_PATH)
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void testGetOpportunities() {
        given()
                .auth().oauth2(access_token)
        .when()
                .get(basePath + OPP_PATH)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void testGetSteps() {
        given()
                .auth().oauth2(access_token)
        .when()
                .get(basePath + OPP_PATH)
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}