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
import com.insys.trapps.respositories.interview.InterviewRepository;
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
	private InterviewRepository interviewRepo;
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
	
	private Business mockBusiness;

	private Interview mockInterview;
	private Long mockId = 12345L;
	private Feedback mockFeedback;
	private Set<Person> mockInterviewers;
	private Set<Question> mockQuestions;
	private Person mockCandidate;
	private long mockDate;
	private Role mockRole;
	
	private Person employee0, employee1, interviewer;
	
	@Before
	public void setup() {
		RestAssured.port = port;
		
		if (mockBusiness == null) {
			mockBusiness = BusinessBuilder.buildBusiness("Insys", "Interview", BusinessType.INSYS).build();
			businessRepo.saveAndFlush(mockBusiness);
		}
		
		if (mockDate == 0) {
			mockDate = new Date().getTime();
		}
		
		if (mockRole == null) {
			mockRole = RoleBuilder.buildRole("Swift Developer II").build();
			roleRepo.saveAndFlush(mockRole);
		}
		
		if (mockInterviewers == null) {
			employee0 = buildPerson("Hung", "Do", "hdo@insys.com", "Architect", PersonType.Employee);
			employee1 = buildPerson("Rohit", "Narwhal", "rfnu@insys.com", "Architect", PersonType.Employee);
			mockInterviewers = new HashSet<Person>(Arrays.asList(employee0, employee1));
		}
		
		if (mockCandidate == null) {
			mockCandidate = buildPerson("Person A", "Last name", "Email", "Architect", PersonType.Candidate);
			personRepo.saveAndFlush(mockCandidate);
		}

		if (mockQuestions == null) {
			Question q0 = Question.builder().question("Question 1").build();
			Question q1 = Question.builder().question("Question 2").build();
			Question q2 = Question.builder().question("Question 3").build();
	
			mockQuestions = new HashSet<>(Arrays.asList(q0, q1, q2));
		}

		if (mockInterview == null) {
			interviewer = buildPerson("Hung", "Do", "hdo@insys.com", "Architect", PersonType.Employee);
			personRepo.saveAndFlush(interviewer);
			
			mockFeedback = Feedback.builder().comment("Excellent").interviewer(interviewer).build();
			feedbackRepo.save(mockFeedback);
			
			mockInterview = createMockInterview();
			interviewRepo.saveAndFlush(mockInterview);
		}
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
				.candidate(mockCandidate)
				.role(mockRole)
				.date(mockDate).build();
		given().contentType("application/json").body(interview).log().everything()
			.when().post(basePath + INT_PATH)
			.then().statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void testUpdateInterviewWithQuestions() {
		Interview interview = mockInterview;
		
		Question q0 = Question.builder().question("Question 1").build();
		Question q1 = Question.builder().question("Question 2").build();
		Question q2 = Question.builder().question("Question 3").build();
		
		interview.getQuestions().add(q0);
		interview.getQuestions().add(q1);
		interview.getQuestions().add(q2);
		
		
		
		// update using put
		log.debug("PATCH REQUEST");
		given()
			.contentType("application/json")
			.body(interview)
		.when()
			.patch(basePath + INT_PATH + "/" + interview.getId())
		.then()
			.log().everything()
			.statusCode(HttpStatus.ACCEPTED.value())
			.extract().response();

		// check if update took with get
		log.debug("GET REQUEST");
		Response response = 
		given()
			.contentType("application/json")
		.when()
			.get(basePath + INT_PATH + "/" + interview.getId())
		.then()
			.log().everything()
			.statusCode(HttpStatus.ACCEPTED.value())
			.extract().response();
		
		// log.debug(response.jsonPath().prettify());
		
		List<Map<String, Object>> questions = response.jsonPath().getList("questions");
		assertEquals(3, questions.size());
		
//		questions.forEach(question -> {
//			assertNotNull(question.get("id"));
//			log.debug("Question : " + question.get("question"));
//		});
	}

	@Test
	public void testGetInterviews() {
		when().get(basePath + INT_PATH).then().statusCode(HttpStatus.OK.value());
	}

	private Person buildPerson(String firstName, String lastName, String email, String title, PersonType type) {
		return Person.builder().firstName(firstName).lastName(lastName).email(email).title(title)
				.personType(type).business(mockBusiness).build();
	}
	
	private Interview createMockInterview() {
		return Interview.builder()
				.candidate(mockCandidate)
				.date(mockDate)
				.role(mockRole)
				.interviewers(mockInterviewers)
				.questions(mockQuestions)
				.feedback(mockFeedback)
				.id(mockId)
				.build();
	}
}
