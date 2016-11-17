package com.insys.trapps;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.insys.trapps.model.Business;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BusinessRestIntegrationTest {
	
	@Autowired
    private TestRestTemplate restTemplate;
    @Test
    @Ignore
    public void getBusinessList() {
        ResponseEntity<Business> responseEntity =
            restTemplate.getForEntity("/business/",  Business.class);
        Business client = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Foo", client.getName());
    }

    @Test
    public void createBusiness() {
        ResponseEntity<Business> responseEntity =
            restTemplate.getForEntity("/business/create",  Business.class);
        Business client = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("test", client.getName());
    }

}
