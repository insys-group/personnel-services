package com.insys.trapps;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insys.trapps.model.Role;
import com.insys.trapps.util.RoleBuilder;
import com.jayway.restassured.RestAssured;

/**
 * @author Kris Krishna
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoleIntegrationTest {
	private static Logger logger = LoggerFactory.getLogger(RoleIntegrationTest.class);

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
	public void testCreateRole() throws JsonProcessingException {
		
		ObjectMapper mapper=new ObjectMapper();

		Role role = RoleBuilder.buildRole("Role 1").build();
		
		logger.debug("-------------------------------------------");
		
		logger.debug("Creating role = " + mapper.writeValueAsString(role));
		
		given().contentType("application/json").body(role).log().everything().when()
				.post("/api/roles").then()
				.log().all().body(containsString("name"))
				.statusCode(HttpStatus.CREATED.value());
		
		logger.debug("-------------------------------------------");
	}

	
}
