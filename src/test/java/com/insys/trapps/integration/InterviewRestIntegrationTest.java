package com.insys.trapps.integration;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.insys.trapps.model.Person;
import com.insys.trapps.model.PersonType;
import com.insys.trapps.model.Role;
import com.insys.trapps.model.interview.Feedback;
import com.insys.trapps.model.interview.Interview;
import com.insys.trapps.model.interview.Question;
import com.insys.trapps.util.PersonBuilder;
import com.insys.trapps.util.RoleBuilder;
import com.jayway.restassured.RestAssured;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class InterviewRestIntegrationTest {
	@Value("${local.server.port}")
    private int port;

    @Value("${spring.data.rest.basePath}")
    private String basePath;

    private final String INT_PATH = "/interviews";
    
    @Before
    public void setup() {
        RestAssured.port = port;
    }

    @After
    public void cleanup() {
    }

    @Test
    public void testCreateInterview() {
    	Interview interview = emptyInterview();
    	
    	given()
    		.contentType("application/json")
    		.body(interview)
    		.log().everything()
    		.when()
    		.post(basePath + INT_PATH)
    		.then()
    		.statusCode(HttpStatus.CREATED.value());
    }
    
    public void testCreateInterviewWithBasicInfo() {
    	Interview interview = partialInterview();
    	
    	given()
			.contentType("application/json")
			.body(interview)
			.log().everything()
			.when()
			.post(basePath + INT_PATH)
			.then()
			.statusCode(HttpStatus.CREATED.value());    	
    }
    
    public void testCreateCompleteInterview() {
    	Interview interview = completeInterview();
    	
    	given()
			.contentType("application/json")
			.body(interview)
			.log().everything()
			.when()
			.post(basePath + INT_PATH)
			.then()
			.statusCode(HttpStatus.CREATED.value());    	
    }
    
    @Test
    public void testGetInterviews() {
        when()
        	.get(basePath + INT_PATH)
        	.then()
        	.statusCode(HttpStatus.OK.value());
    }

    private Interview emptyInterview() {
		return Interview.builder().build();
	}
	
	private Interview partialInterview() {
		return Interview.builder()
				.candidate(new Person())
				.role(RoleBuilder.buildRole("Swift Developer").build())
				.date(new Date().getTime())
				.build();
	}
	
	private Interview completeInterview() {
		Role role = RoleBuilder.buildRole("Swift Developer II").build();
		Person employee0 = PersonBuilder
				.buildPerson("Hung", "Do", "hdo@insys.com", "Architect", PersonType.Employee)
				.build();
		Person employee1 = PersonBuilder
				.buildPerson("Rohit", "Narwhal", "rfnu@insys.com", "Architect", PersonType.Employee)
				.build();
		Person candidate = PersonBuilder
				.buildPerson("Person A", "Last name", "Email", "Architect", PersonType.Candidate)
				.build();
		
		Feedback feedback = Feedback.builder()
				.comment("Excellent")
				.interviewer(employee0).build();
		
		HashSet <Question> questions = new HashSet<>(
				Arrays.asList(
						Question.builder().question("Question 1").build(),
						Question.builder().question("Question 2").build(),
						Question.builder().question("Question 3").build())
				);
		
		return Interview.builder()
				.candidate(candidate)
				.role(role)
				.date(new Date().getTime())
				.interviewers(new HashSet<Person>(Arrays.asList(employee0, employee1)))
				.feedback(feedback)
				.questions(questions)
				.build();
	}
}
