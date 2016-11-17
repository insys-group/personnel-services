package com.insys.trapps;

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.json.JSONObject;
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
import com.insys.trapps.util.Builder;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BusinessRestIntegrationTest {
	
	 private static final Logger logger = LoggerFactory.getLogger(BusinessRestIntegrationTest.class);

	@Autowired
	private TestRestTemplate restTemplate;
	
	  protected ObjectMapper objectMapper = new ObjectMapper();

	@Test
	//@Ignore
	public void getBusinessList() {
		ResponseEntity<Business> responseEntity = restTemplate.getForEntity("/business",
				Business.class);
		Business client = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("Foo", client.getName());
	}

	@Test
	public void createBusiness() throws JsonProcessingException {
		
		Address address_1 = new Address("Insys Street", "Denver", "CO", "80014");
		Address address_2 = new Address("Luxoft Street", "Seattle", "WA", "70014");

		Business testBuisness = Builder.buildBusiness("test", "testing-denver",  BusinessType.INSYS).addLocation(address_1).addLocation(address_2).build();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		logger.debug("jsonString :" + objectMapper.writeValueAsString(testBuisness));

		HttpEntity<String> entity = new HttpEntity<String>(objectMapper.writeValueAsString(testBuisness), headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange("/business/",
				HttpMethod.POST, entity, String.class);

		// ResponseEntity<Business> responseEntity =
		// restTemplate.postForEntity("/business", entity, Business.class);
		// Business client = responseEntity.getBody();
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		// assertEquals("test", client.getName());
	}

}
