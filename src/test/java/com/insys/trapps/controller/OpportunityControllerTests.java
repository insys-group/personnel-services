package com.insys.trapps.controller;


import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insys.trapps.model.Opportunity;
import com.insys.trapps.model.OpportunityStep;
import com.insys.trapps.model.Person;
import com.insys.trapps.model.PersonType;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

/**
 * @author Muhammad Sabir
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OpportunityControllerTests {
	private static Logger logger = LoggerFactory.getLogger(OpportunityControllerTests.class);
	
	@Value("${local.server.port}")
	private int port;
	
	@Before
	public void setup() {
		RestAssured.port = port;
	}
	
	@After
	public void cleanup() {
	}
	
	
	@Test
	@Ignore
	public void testCreateOpportunity() {
		Opportunity opportunity=Opportunity.builder().comments("Comcast Opportunity").build();
		given()
			.contentType("application/json")
			.body(opportunity)
			.log().everything()
		.when()
			.post("/api/opportunities")
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void testCreateOpportunityWithSteps() throws Exception {
		ObjectMapper mapper=new ObjectMapper();
		Opportunity opportunity=Opportunity.builder().comments("Aramark Opportunity").build();
		
		logger.debug("Creating opportunity = " + mapper.writeValueAsString(opportunity));
		String stepsUrl = 
		given()
			.contentType("application/json")
			.body(opportunity)
			.log().everything()
		.when()
			.post("/api/opportunities")
		.then()
			.statusCode(HttpStatus.CREATED.value())
			.extract().jsonPath().get("_links.steps.href").toString();
				
		given()
			.contentType("application/json")
			.body(OpportunityStep.builder().comments("Step 1").stepTimestamp(new Date()).build())
			.log().everything()
		.when()
			.post(stepsUrl)
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());
		
		given()
			.contentType("application/json")
			.body(OpportunityStep.builder().comments("Step 2").stepTimestamp(new Date()).build())
			.log().everything()
		.when()
			.post(stepsUrl)
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());
	}

	@Test
	public void testCreateOpportunityWithContacts() throws Exception {
		ObjectMapper mapper=new ObjectMapper();
		Opportunity opportunity=Opportunity.builder().comments("Aramark Opportunity").build();
		
		logger.debug("Creating opportunity = " + mapper.writeValueAsString(opportunity));
		String stepsUrl = 
		given()
			.contentType("application/json")
			.body(opportunity)
			.log().everything()
		.when()
			.post("/api/opportunities")
		.then()
			.statusCode(HttpStatus.CREATED.value())
			.extract().jsonPath().get("_links.steps.href").toString();
				
		given()
			.contentType("application/json")
			.body(OpportunityStep.builder().comments("Step 1").stepTimestamp(new Date()).build())
			.log().everything()
		.when()
			.post(stepsUrl)
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());
	}
	
	@Ignore
	@Test
	public void testGetOpportunities() {
		when()
			.get("/api/opportunities")
		
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("findAll.size()", equalTo(0));
	}
	
	@Ignore
	@Test
	public void testGetSteps() {
		Response response =
		when()
			.get("/api/opportunities")
		
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("findAll.size()", equalTo(0))
			.and()
			//.body("findAll {o -> o.comments == 'Comcast Opportunity2'}", notNullValue());
			.body("comments", hasItem("Comcast opportunity"))
			//.and()
			//.body("steps.comments.size()", equalTo(2))
			.and()
			.extract().response();
		
		logger.debug("Response is " + response.asString());
	}
}
