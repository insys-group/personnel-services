package com.insys.trapps.integration;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.insys.trapps.model.Business;
import com.insys.trapps.model.BusinessType;
import com.insys.trapps.model.Person;
import com.insys.trapps.model.PersonType;
import com.insys.trapps.model.Role;
import com.insys.trapps.model.interview.Feedback;
import com.insys.trapps.model.interview.Interview;
import com.insys.trapps.model.interview.Question;
import com.insys.trapps.respositories.BusinessRepository;
import com.insys.trapps.respositories.PersonRepository;
import com.insys.trapps.respositories.RoleRepository;
import com.insys.trapps.respositories.interview.FeedbackRepository;
import com.insys.trapps.util.BusinessBuilder;
import com.insys.trapps.util.RoleBuilder;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class InterviewRestIntegrationTest {
	@Autowired
	private FeedbackRepository feedbackRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private PersonRepository personRepo;
	@Autowired
	private BusinessRepository businessRepo;

	@Value("${local.server.port}")
	private int port;

	@Value("${spring.data.rest.basePath}")
	private String basePath;

	private final String INT_PATH = "/interviews";

	private Person employee0, employee1, candidate;
	private long date;
	private Role role;
	private Set<Question> questions;
	private Feedback feedback;
	private Set<Person> interviewers;
	private Business business;

	@Before
	public void setup() {
		RestAssured.port = port;
		
		business = BusinessBuilder.buildBusiness("Insys", "Interview", BusinessType.INSYS).build();
		businessRepo.saveAndFlush(business);
		
		date = new Date().getTime();
		role = RoleBuilder.buildRole("Swift Developer II").build();
		roleRepo.saveAndFlush(role);

		employee0 = buildPerson("Hung", "Do", "hdo@insys.com", "Architect", PersonType.Employee);
		employee1 = buildPerson("Rohit", "Narwhal", "rfnu@insys.com", "Architect", PersonType.Employee);
		candidate = buildPerson("Person A", "Last name", "Email", "Architect", PersonType.Candidate);
		personRepo.saveAndFlush(candidate);

		Question q0 = Question.builder().question("Question 1").build();
		Question q1 = Question.builder().question("Question 2").build();
		Question q2 = Question.builder().question("Question 3").build();

		questions = new HashSet<>(Arrays.asList(q0, q1, q2));
		interviewers = new HashSet<Person>(Arrays.asList(employee0, employee1));

		Person interviewer = buildPerson("Hung", "Do", "hdo@insys.com", "Architect", PersonType.Employee);
		personRepo.saveAndFlush(interviewer);
		feedback = Feedback.builder().comment("Excellent").interviewer(interviewer).build();
	}

	@After
	public void cleanup() {
	}

	@Test
	public void testCreateInterviewWithEmptyInterview() {
		Interview interview = Interview.builder().build();
		given().contentType("application/json").body(interview).log().everything()
			.when().post(basePath + INT_PATH)
			.then().statusCode(HttpStatus.CONFLICT.value());
	}
	
	@Test
	public void testCreateInterviewWithBasicInfo() {
		Interview interview = Interview.builder()
				.candidate(candidate)
				.role(role)
				.date(date).build();
		given().contentType("application/json").body(interview).log().everything()
			.when().post(basePath + INT_PATH)
			.then().statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void testUpdateInterviewWithQuestions() {
		Interview interview = createBasicInterview();
		
		Question q0 = Question.builder().question("Question 1").build();
		Question q1 = Question.builder().question("Question 2").build();
		Question q2 = Question.builder().question("Question 3").build();
		
		interview.getQuestions().add(q0);
		interview.getQuestions().add(q1);
		interview.getQuestions().add(q2);
		
		// update using put
		given()
			.contentType("application/json")
			.body(interview)
		.when()
			.put(basePath + INT_PATH + "/put/" + interview.getId())
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value()).log().everything();

		// check if update took with get
		Response response = 
		given()
			.contentType("application/json")
			.log().everything()
		.when()
			.get(basePath + INT_PATH + "/" + interview.getId())
		.then()
			.statusCode(HttpStatus.OK.value()).extract().response();
		
		List<Map<String, Object>> questions = response.jsonPath().getList("questions");
		assertEquals(3, questions.size());
		
		questions.forEach(question -> {
			assertNotNull(question.get("id"));
			log.debug("Question : " + question.get("question"));
		});
	}
	
//	@Test
//	public void testCreateInterviewWithCompleteInterview() {
//		Interview interview = Interview.builder()
//				.candidate(candidate)
//				.role(role)
//				.date(date)
//				.interviewers(interviewers)
//				.questions(questions)
//				.feedback(feedback)
//				.build();
//		given().contentType("application/json").body(interview).log().everything()
//			.when().post(basePath + INT_PATH)
//			.then().statusCode(HttpStatus.CREATED.value());
//	}

	@Test
	public void testGetInterviews() {
		when().get(basePath + INT_PATH).then().statusCode(HttpStatus.OK.value());
	}

	private Person buildPerson(String firstName, String lastName, String email, String title, PersonType type) {
		return Person.builder().firstName(firstName).lastName(lastName).email(email).title(title)
				.personType(type).business(business).build();
	}
	
	private Interview createBasicInterview() {
		if (candidate == null) {
			candidate = buildPerson("Person A", "Last name", "Email", "Architect", PersonType.Candidate);
			personRepo.saveAndFlush(candidate);
		}
		
		if (role == null) {
			role = RoleBuilder.buildRole("Swift Developer II").build();
			roleRepo.saveAndFlush(role);
		}
		
		if (date == 0) {
			date = new Date().getTime();
		}
		
		Interview interview = Interview.builder()
				.candidate(candidate)
				.role(role)
				.date(date)
				.interviewers(new HashSet<>())
				.questions(new HashSet<>())
				.build();

		Map<String, Object> interviewProps = given().contentType("application/json")
				.body(interview).log().everything()
				.when().post(basePath + INT_PATH)
				.then().statusCode(HttpStatus.CREATED.value()).extract().jsonPath()
				.get();
		
		interview.setId(new Long((Integer) interviewProps.get("id")));
		
		assertNotNull(interviewProps.get("id"));
		
		return interview;
	}
}
