package com.insys.trapps;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insys.trapps.model.Address;
import com.insys.trapps.model.Business;
import com.insys.trapps.model.BusinessType;
import com.insys.trapps.util.BusinessBuilder;

/**
 * {@link Integration Test using RestTemplate} for PersonnelServices.
 * 
 * @author  Kris Krishna
 * @since 1.0.0
**/

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Ignore
public class BusinessRestIntegrationTest {
	
	 private static final Logger logger = LoggerFactory.getLogger(BusinessRestIntegrationTest.class);

	@Autowired
	private TestRestTemplate restTemplate;
	
	  protected ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void getBusinessList() {
		ResponseEntity<List> responseEntity = restTemplate.getForEntity("/businesses",
				List.class);
		List clients = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void createBusiness() throws JsonProcessingException {

		Address address_1 = Address.builder()
				.address1("Insys Street")
				.city("Denver")
				.state("CO")
				.zipCode("80014")
				.build();
		
		Address address_2 =Address.builder()
				.address1("Luxoft Street")
				.city("Seattle")
				.state("WA")
				.zipCode("70014")
				.build();

		Business testBuisness = BusinessBuilder.buildBusiness("test", "testing-denver",  BusinessType.INSYS).addLocation(address_1).addLocation(address_2).build();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		logger.debug("jsonString :" + objectMapper.writeValueAsString(testBuisness));

		HttpEntity<String> entity = new HttpEntity<String>(objectMapper.writeValueAsString(testBuisness), headers);

		ResponseEntity<Business> responseEntity = restTemplate.exchange("/businesses/", HttpMethod.POST, entity, Business.class);

		// ResponseEntity<Business> responseEntity =  restTemplate.postForEntity("/business", entity, Business.class);
		
		 Business client = responseEntity.getBody();
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals("test", client.getName());
	}

}
