package com.insys.trapps.integration;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import lombok.extern.slf4j.Slf4j;

/**
 * {@link Integration Test using RestTemplate} for PersonnelServices.
 *
 * @author Kris Krishna
 * @since 1.0.0
 **/

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class BusinessRestIntegrationTest {
    @Value("${local.server.port}")
    private int port;

    @Value("${spring.data.rest.basePath}")
    private String basePath;

    @Autowired
    private TestRestTemplate restTemplate;

    protected ObjectMapper objectMapper = new ObjectMapper();

    @Ignore
    @Test
    public void getBusinessList() {
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(basePath + "/businesses",
                List.class);
        List clients = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Ignore
    @Test
    public void createBusiness() throws JsonProcessingException {

        Address address_1 = Address.builder()
                .address1("Insys Street")
                .city("Denver")
                .state("CO")
                .zipCode("80014")
                .build();
        Address address_2 = Address.builder()
                .address1("Luxoft Street")
                .city("Seattle")
                .state("WA")
                .zipCode("70014")
                .build();
        Set<Address> addresses=new HashSet<Address>();
        addresses.add(address_1);
        addresses.add(address_2);
        
        Business testBuisness = Business.builder().name("test")
        		.description("testing-denver")
        		.businessType(BusinessType.INSYS)
                .addresses(addresses)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        log.debug("jsonString :" + objectMapper.writeValueAsString(testBuisness));

        HttpEntity<String> entity = new HttpEntity<String>(objectMapper.writeValueAsString(testBuisness), headers);

        ResponseEntity<Business> responseEntity = restTemplate.exchange("/businesses/", HttpMethod.POST, entity, Business.class);

        // ResponseEntity<Business> responseEntity =  restTemplate.postForEntity("/business", entity, Business.class);

        Business client = responseEntity.getBody();
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("test", client.getName());
    }

}
