package com.insys.trapps.integration;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.insys.trapps.model.Address;
import com.insys.trapps.model.Business;
import com.insys.trapps.model.BusinessType;
import com.insys.trapps.model.Person;
import com.insys.trapps.model.PersonType;
import com.insys.trapps.model.ProgressType;
import com.insys.trapps.model.Training;
import com.insys.trapps.model.TrainingTask;
import com.insys.trapps.respositories.BusinessRepository;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TrainingRestIntegrationTest {
	
	@Autowired
	private BusinessRepository businessRepository;
	
	private Business business;

	
    @Value("${local.server.port}")
    private int port;

    @Value("${spring.data.rest.basePath}")
    private String basePath;

    private final String TRAINING_PATH = "/trainings";
    
    @Before
    public void setup() {
        RestAssured.port = port;
		business=initBusiness();
		businessRepository.saveAndFlush(business);
    }
    
    @Test
    public void testCreateTraining() throws Exception {
    	Training training = initTraining();
    	
    	Integer id = postTrainingRequest(training).getInt("id");
    	String name = getTrainingRequest(id).getString("name");
    	
    	assertThat(name, equalTo("Test Training"));
    }
    
    @Test
    public void testDeleteTraining() throws Exception {
    	Training training = initTraining();
    	
    	Integer id = postTrainingRequest(training).getInt("id");
    	deleteTrainingRequest(id);
    	getTrainingRequestReturnsNotFound(id);
    }

    @Test
    public void testUpdateTraining() throws Exception {
    	Training training = initTraining();
    	String[] newTaskNames = new String[]{"New Task 1", "New Task 2", "New Task 3"};
    	String newName = "New Name";
    	
    	
    	Integer id = postTrainingRequest(training).getInt("id");
    	setNewFieldsToTraining(training, (long) id, newName, newTaskNames);
    	Integer secondPostId = postTrainingRequest(training).getInt("id");
    	JsonPath updatedTraining = getTrainingRequest(secondPostId);
    	
    	assertThat(secondPostId, equalTo(id));
    	checkTrainingName(updatedTraining, newName);
    }

	private void checkTrainingName(JsonPath updatedTraining, String newName) {
		assertThat((String) updatedTraining.get("name"), equalTo(newName));
	}

	@SuppressWarnings("unchecked")
	private List<String> getTrainingTaskNames(JsonPath updatedTraining) {
		List<HashMap<String, Object>> tasks = (List<HashMap<String, Object>>) updatedTraining.get("tasks");
		return tasks.stream().map(map ->(String) map.get("name")).collect(Collectors.toList());
	}

	private void setNewFieldsToTraining(Training training, Long id, String name, String... taskNames) {
		training.setId(id);
    	training.setName(name);
    	training.setTasks(initTasks(taskNames));
	}

	private void deleteTrainingRequest(Integer id) {
		given()
		.when()
		        .delete(basePath + TRAINING_PATH + "/" + id)
		.then()
		        .statusCode(HttpStatus.NO_CONTENT.value()).log().everything();
	}

	private JsonPath getTrainingRequest(Integer id) {
		return 
		given()
    	.when()
    			.get(basePath + TRAINING_PATH + "/" + id)
    	.then()
    			.statusCode(HttpStatus.OK.value()).log().everything()
    			.extract().jsonPath();
	}

	private void getTrainingRequestReturnsNotFound(Integer id) {
		given()
    	.when()
    			.get(basePath + TRAINING_PATH + "/" + id)
    	.then()
    			.statusCode(HttpStatus.NOT_FOUND.value()).log().everything();
	}

	private JsonPath postTrainingRequest(Training training) {
		return 
		given()
                .contentType("application/json")
                .body(training)
        .when()
        		.log().everything()
                .post(basePath + TRAINING_PATH)
        .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract().jsonPath();
	}


	private Training initTraining() {
		return Training.builder()
				.name("Test Training")
				.trainees(initTrainees())
				.progress(ProgressType.NOT_STARTED)
				.location(initAddress())
				.isOnline(true)
				.startDate(new Date(LocalDate.of(2017, Month.JANUARY, 25).toEpochDay()))
				.endDate(new Date(LocalDate.of(2017, Month.JANUARY, 31).toEpochDay()))
				.tasks(initTasks())
				.build();
	}

	private Set<TrainingTask> initTasks() {
		return initTasks("Test Task 1", "Test Task 2");
	}

	private Set<TrainingTask> initTasks(String... taskNames) {
		return new HashSet<>(Arrays.stream(taskNames)
				.map(name -> TrainingTask.builder().name(name).build()).collect(Collectors.toList()));
	}

	private Address initAddress() {
		return Address.builder()
				.address1("395 W Passaic St")
				.city("Rochelle Park")
				.zipCode("07662")
				.state("NJ")
				.country("USA")
				.build();
	}

	private Person initPerson() {
		return Person.builder()
				.firstName("Mike")
				.lastName("Tian")
				.personType(PersonType.Candidate)
				.business(business)
				.email("mtian@insys.com")
				.build();
	}

	private Business initBusiness() {
		return Business.builder()
				.name("Test Business")
				.businessType(BusinessType.INSYS)
				.description("Test Business")
				.build();
	}

	private Set<Person> initTrainees() {
		Set<Person> trainees = new HashSet<>();
		trainees.add(initPerson());
		return trainees;
	}


}
