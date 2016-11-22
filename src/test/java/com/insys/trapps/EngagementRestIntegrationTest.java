package com.insys.trapps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insys.trapps.model.Business;
import com.insys.trapps.model.Engagement;
import com.insys.trapps.util.EngagementBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by vnalitkin on 11/22/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class EngagementRestIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private JacksonTester<Engagement> json;

    protected ObjectMapper objectMapper = new ObjectMapper();

    private List<Engagement> testEngagementList = new ArrayList<>();

    @Before
    public void beforeEachMethod() {
        ObjectMapper objectMapper = new ObjectMapper();
        // Possibly configure the mapper
        JacksonTester.initFields(this, objectMapper);

        testEngagementList = Arrays.asList(
                EngagementBuilder.buildEngagement("Engagement 1", null, null, null).build()
                , EngagementBuilder.buildEngagement("Engagement 1", null, null, null).build()
        );
    }

    @Test
    public void endPointTest() {
        String url = "http://localhost:8080/engagements";
        Engagement engagement = testEngagementList.get(0);
        ResponseEntity<Engagement> entity = restTemplate.postForEntity(url, engagement, Engagement.class);
        System.out.println(entity.getBody().getComments());

        // ResponseEntity<Business> responseEntity =  restTemplate.postForEntity("/business", entity, Business.class);

/*
        Business client = responseEntity.getBody();
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("test", client.getName());
*/

/*
        restTemplate.getForEntity(
                "/{username}/vehicle", String.class, "Phil");
*/
    }

}
