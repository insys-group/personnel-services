package com.insys.trapps;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import java.util.HashSet;
import java.util.Set;

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
import com.insys.trapps.model.Address;
import com.insys.trapps.model.Business;
import com.insys.trapps.model.BusinessType;
import com.insys.trapps.model.Location;
import com.jayway.restassured.RestAssured;

/**
 * @author Kris Krishna
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BusinessIntegrationTest {
	private static Logger logger = LoggerFactory.getLogger(BusinessIntegrationTest.class);

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
	public void testCreateBusiness() throws JsonProcessingException {
		
		ObjectMapper mapper=new ObjectMapper();

		Address address_1 = Address.builder().address1("Insys Street").city("Denver")
				.state("CO").zipCode("80014").build();

		Location location = Location.builder().address(address_1).build();

		Set<Location> h = new HashSet<Location>();
		h.add(location);
		
		logger.debug("-------------------------------------------");
		
		Business business = Business.builder().name("test").descr("testing-denver")
				.businessType(BusinessType.INSYS).locations(h).build();
		
		logger.debug("Creating business = " + mapper.writeValueAsString(business));
		
		given().contentType("application/json").body(business).log().everything().when()
				.post("/api/businesses").then()
				.log().all().body(containsString("name"))
				.statusCode(HttpStatus.CREATED.value());
		
		given().contentType("application/json").body(business).log().everything().when()
				.get("api/locations").then()
				.log().all().body(containsString("name"))
				.statusCode(HttpStatus.OK.value());
		
		logger.debug("-------------------------------------------");
	}

	
}
