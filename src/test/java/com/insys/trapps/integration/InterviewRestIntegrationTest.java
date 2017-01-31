package com.insys.trapps.integration;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.insys.trapps.model.Business;
import com.insys.trapps.model.BusinessType;
import com.insys.trapps.model.Person;
import com.insys.trapps.model.PersonType;
import com.insys.trapps.model.Role;
import com.insys.trapps.model.interview.Feedback;
import com.insys.trapps.model.interview.Interview;
import com.insys.trapps.model.interview.Interview.InterviewBuilder;
import com.insys.trapps.model.interview.Quality;
import com.insys.trapps.model.interview.Question;
import com.insys.trapps.respositories.BusinessRepository;
import com.insys.trapps.respositories.PersonRepository;
import com.insys.trapps.respositories.RoleRepository;
import com.insys.trapps.respositories.interview.FeedbackRepository;
import com.insys.trapps.util.BusinessBuilder;
import com.insys.trapps.util.RoleBuilder;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;

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

	private final String INTERVIEW_PATH = "/interviews";

	private Business business;

	private Feedback feedback;
	private Role role;

	private Person firstInterviewer, secondInterviewer, candidate, updatedCandidate;

	@Before
	public void setup() {
		RestAssured.port = port;

		business = BusinessBuilder.buildBusiness("Insys", "Interview", BusinessType.INSYS).build();
		businessRepo.saveAndFlush(business);

		role = RoleBuilder.buildRole("Swift Developer II").build();
		roleRepo.saveAndFlush(role);

		firstInterviewer = buildPerson("Hung", "Do", "hdo@insys.com", "Architect", PersonType.Employee);
		personRepo.save(firstInterviewer);
		secondInterviewer = buildPerson("Rohit", "Narwhal", "rfnu@insys.com", "Architect", PersonType.Employee);
		personRepo.save(secondInterviewer);
		candidate = buildPerson("Candidate First Name", "Candidate Last Name", "Email", "Architect",
				PersonType.Candidate);
		personRepo.save(candidate);
		updatedCandidate = buildPerson("Updated First Name", "Updated Last Name", "updated@email.com", "Updated Title",
				PersonType.Candidate);
		personRepo.saveAndFlush(updatedCandidate);

		feedback = Feedback.builder().comment("Excellent").interviewer(firstInterviewer).build();
		feedbackRepo.save(feedback);

	}

	@Test
	public void testCreateEmptyInterview() {
		Interview interview = Interview.builder().build();

		postRequestForInterviewReturns(interview, HttpStatus.CONFLICT);
	}

	@Test
	public void testCreateInterviewWithBasicInfo() {
		Interview interview = initBasicInterview();

		int id = postRequestForInterview(interview).getInt("id");
		JsonPath response = getRequestForInterview(id);

		Person candidate = response.getObject("candidate", Person.class);
		assertThat(candidate.getFirstName(), equalTo("Candidate First Name"));
		assertThat(candidate.getLastName(), equalTo("Candidate Last Name"));
	}

	@Test
	public void testUpdateInterviewWithQuestions() {
		Interview interview = createInterview();

		long id = postRequestForInterview(interview).getInt("id");
		String[] questionNames = new String[] { "Updated Question 1", "Updated Question 2" };
		setNewFieldsToInterview(interview, id, questionNames);
		long updatedId = postRequestForInterview(interview).getInt("id");
		JsonPath updatedInterview = getRequestForInterview(id);

		assertThat(updatedId, equalTo(id));
		checkPerson(updatedInterview);
		checkQuestions(updatedInterview, questionNames);
	}

	@Test
	public void testPatchInterviewWithQuestions() {
		Interview interview = createInterview();
		Map<String, Question> questionsPatch = createQuestions("Patched Question 1", "Patched Question 2").stream()
				.collect(Collectors.toMap(Question::getQuestion, question -> question));
		String questionsInJson = convertQuestionsToJson(questionsPatch);

		long id = postRequestForInterview(interview).getInt("id");
		patchRequestForInterview(id, questionsInJson);
		JsonPath updatedInterview = getRequestForInterview(id);

		checkQuestions(updatedInterview, "Patched Question 1", "Patched Question 2");
	}

	private void patchRequestForInterview(long id, String json) {
		log.debug("PATCH REQUEST");
		given().contentType("application/json").body("{\"questions\":" + json + "}").when().log().everything()
				.patch(basePath + INTERVIEW_PATH + "/" + id).then().statusCode(HttpStatus.ACCEPTED.value()).extract()
				.response();
	}

	private String convertQuestionsToJson(Map<String, Question> questionsPatch) {
		String json = "";
		try {
			json = new ObjectMapper().writeValueAsString(questionsPatch);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
		}
		return json;
	}

	private void checkQuestions(JsonPath updatedInterview, String... questionNames) {
		List<String> updatedQuestions = getQuestions(updatedInterview);
		assertThat(updatedQuestions.size(), equalTo(questionNames.length));
		assertThat(updatedQuestions, hasItems(questionNames));
	}

	private void checkPerson(JsonPath updatedInterview) {
		Person updatedCandidate = updatedInterview.getObject("candidate", Person.class);
		assertThat(updatedCandidate.getFirstName(), equalTo("Updated First Name"));
		assertThat(updatedCandidate.getLastName(), equalTo("Updated Last Name"));
	}

	@SuppressWarnings("unchecked")
	private List<String> getQuestions(JsonPath updatedTraining) {
		List<HashMap<String, Object>> tasks = (List<HashMap<String, Object>>) updatedTraining.get("questions");
		return tasks.stream().map(map -> (String) map.get("question")).collect(Collectors.toList());
	}

	private void setNewFieldsToInterview(Interview interview, long id, String... questionNames) {
		Set<Question> questions = createQuestions(questionNames);
		interview.setId(id);
		interview.setCandidate(updatedCandidate);
		interview.setQuestions(questions);
	}

	private JsonPath postRequestForInterview(Interview interview) {
		return postRequestForInterviewReturns(interview, HttpStatus.CREATED);
	}

	private JsonPath postRequestForInterviewReturns(Interview interview, HttpStatus status) {
		return given().contentType("application/json").body(interview).log().everything().when()
				.post(basePath + INTERVIEW_PATH).then().statusCode(status.value()).extract().response().jsonPath();
	}

	private JsonPath getRequestForInterview(long id) {
		return given().when().get(basePath + INTERVIEW_PATH + "/" + id).then().statusCode(HttpStatus.OK.value()).log()
				.everything().extract().jsonPath();
	}

	private Person buildPerson(String firstName, String lastName, String email, String title, PersonType type) {
		return Person.builder().firstName(firstName).lastName(lastName).email(email).title(title).personType(type)
				.business(business).build();
	}

	private Interview createInterview() {
		return getInterviewBuilder().interviewers(new HashSet<Person>(Arrays.asList(firstInterviewer, secondInterviewer)))
				.questions(createQuestions("Question 1", "Question 2", "Question 3")).feedback(feedback).build();
	}

	private Interview initBasicInterview() {
		return getInterviewBuilder().build();
	}

	private InterviewBuilder getInterviewBuilder() {
		return Interview.builder().candidate(candidate).role(role).date(createDate());
	}

	private Set<Question> createQuestions(String... questions) {
		return Arrays.stream(questions).map(this::createQuestion).collect(Collectors.toSet());
	}

	private Question createQuestion(String question) {
		return Question.builder().question(question).answer("Answer")
				.quality(Quality.Excellent).comment("Comment").build();
	}

	private long createDate() {
		return LocalDate.of(2017, Month.JANUARY, 25).toEpochDay();
	}
}
