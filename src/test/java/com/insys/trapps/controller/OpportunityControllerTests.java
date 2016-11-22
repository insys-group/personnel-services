package com.insys.trapps.controller;


import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;

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

import com.insys.trapps.model.Opportunity;
import com.insys.trapps.model.PersonType;
import com.insys.trapps.repository.OpportunityRepository;
import com.insys.trapps.service.OpportunityService;
import com.insys.trapps.util.OpportunityBuilder;
import com.insys.trapps.util.PersonBuilder;
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
	@Autowired
	private OpportunityRepository repository;
	
	@Autowired
	private OpportunityService service;
	
	@Value("${local.server.port}")
	private int port;
	
	@Before
	public void setup() {
		RestAssured.port = port;
		
		Opportunity comcastOpportunity=OpportunityBuilder.buildOpportunity("Comcast opportunity").addStep("Step 1").addStep("Step 2").build();
		repository.saveAndFlush(comcastOpportunity);
	}
	
	@After
	public void cleanup() {
		repository.deleteAll();
	}
	
	@Ignore
	@Test
	public void testGetOpportunities() {
		when()
			.get("/opportunities")
		
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("findAll.size()", equalTo(1))
			.and()
			.body("comments", hasItem("Comcast opportunity"));
	}
	
	@Ignore
	@Test
	public void testGetSteps() {
		Response response =
		when()
			.get("/opportunities")
		
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("findAll.size()", equalTo(1))
			.and()
			//.body("findAll {o -> o.comments == 'Comcast Opportunity2'}", notNullValue());
			.body("comments", hasItem("Comcast opportunity"))
			//.and()
			//.body("steps.comments.size()", equalTo(2))
			.and()
			.extract().response();
		
		logger.debug("Response is " + response.asString());
	}
	
	@Ignore
	@Test
	public void testCreateOpportunityWithSteps() {
		Opportunity opportunity=OpportunityBuilder.buildOpportunity("Aramark opportunity").addStep("Step 1").addStep("Step 2").build();
		logger.debug("Saving Opportunity " + opportunity);
		Response response =
		given()
			.contentType("application/json")
			.body(opportunity)
			.log().everything()

		.when()
			.post("/opportunities")

		.then()
			.statusCode(HttpStatus.CREATED.value())
			.extract().response();
		
		logger.debug("Response is " + response.asString());
	}
	
	@Ignore
	@Test
	public void testCreateOpportunityWithStepsAndContacts() throws Exception {
		Opportunity opportunity=OpportunityBuilder
				.buildOpportunity("Aramark opportunity")
					.addStep("Step 1").addStep("Step 2").addStep("Step 3")
					.addContact(PersonBuilder.buildPerson("Muhammad", "Sabir").addEmail("msabir@insys.com").addPersonType(PersonType.Employee).build())
					.addContact(PersonBuilder.buildPerson("Kacey", "Wood").addEmail("kwoods@insys.com").addPersonType(PersonType.Employee).build())
				.build();
		logger.debug("Saving Opportunity " + opportunity);
		Response response =
		given()
			.contentType("application/json")
			.body(opportunity)
			.log().everything()

		.when()
			.post("/opportunities")

		.then()
			.statusCode(HttpStatus.CREATED.value())
			.body("id", notNullValue())
			.extract().response();
		logger.debug("Response is " + response.asString());
	}
	
	//@Ignore
	@Test
	public void testUpdateOpportunityWithStep() {
		Opportunity opportunity=OpportunityBuilder
				.buildOpportunity("WW opportunity")
					.addStep("Step 1")
				.build();
		repository.saveAndFlush(opportunity);
		
		Response response =
		given()
			.contentType("application/json")
			.body(OpportunityBuilder.createStep("Step 2"))
			.log().everything()

		.when()
			.pathParam("id", "2")
			.post("/opportunities/{id}/steps")

		.then()
			.statusCode(HttpStatus.OK.value())
			.extract().response();
		logger.debug("Response is " + response.asString());
		
		Opportunity updatedOpportunity=service.searchOpportunityWithStepsAndContacts(2L);
		//Opportunity updatedOpportunity=repository.findByComments("WW opportunity").get(0);
		logger.debug("Opportunity Load is " + updatedOpportunity);
		
		assertEquals(2, updatedOpportunity.getSteps().size());
		assertEquals(1, updatedOpportunity.getSteps().stream().filter(step->step.getComments().equals("Step 2")).count());
	}

}
