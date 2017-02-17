package com.insys.trapps.integration;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.with;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.insys.trapps.model.Address;
import com.insys.trapps.model.Training;
import com.insys.trapps.model.TrainingTask;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.ValidatableResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TrainingRestIntegrationTest {
			
    @Value("${local.server.port}")
    private int port;
    
    @Value("${spring.data.rest.basePath}")
    private String basePath;

    private static final String TRAINING_PATH = "/trainings";
    /**
     * The container appears in REST-assured tests. There is no this element in real communication to a microservice. 
     */
    private static String JSON_NESTED_CONTAINER = "_embedded";
    
    @Before
    public void setup() {
        RestAssured.port = port;
    }
    
    @Test
    public void testCreateTraining() throws Exception {
    	Training training = initTraining();
    	
    	long id = postTrainingRequest(training).getId();
    	
		getTrainingRequest(id)
			.body("name", equalTo("Test Training"))
			.body("tasks.name", hasItems("Test Task 1", "Test Task 2"));
    }
    
    @Test
    public void testDeleteTraining() throws Exception {
    	Training training = initTraining();
    	
    	long id = postTrainingRequest(training).getId();
    	deleteTrainingRequest(id);

		checkGetRequestReturnNOT_FOUND(id);
    }

    @Test
    public void testUpdateTrainingWithPost() throws Exception {
    	Training training = initTraining();
    	String[] newTaskNames = new String[]{"New Task 1", "New Task 2", "New Task 3"};
    	String newName = "New Name";
    	
    	Training postedTraining = postTrainingRequest(training);
    	setNewFieldsTo(postedTraining, newTaskNames, newName);
    	postTrainingRequest(postedTraining);
    	
    	getTrainingRequest(postedTraining.getId())
    		.body("name", equalTo(newName)).and()
    		.body("tasks.name", hasItems(newTaskNames));
    }
    
    
    @Test
    public void testGetAllTrainings() throws Exception {
    	deletAllTrainings();
    	
    	Training training = initTraining();
    	postTrainingRequest(training);
    	
		getAllTrainingsRequest()
    		.body(JSON_NESTED_CONTAINER + ".trainings", hasSize(1));
    }

	private void deleteTrainingRequest(long id) {
		with()
				.log().everything()
		        .delete(basePath + TRAINING_PATH + "/" + id)
		.then()
		        .statusCode(HttpStatus.NO_CONTENT.value());
	}

	private ValidatableResponse getTrainingRequest(long id) {
		return with()
			.get(basePath + TRAINING_PATH + "/" + id)
		.then()
			.log().everything()
			.statusCode(HttpStatus.OK.value());
	}


	private ValidatableResponse getAllTrainingsRequest() {
		return 
		with()
    			.get(basePath + TRAINING_PATH)
    	.then()
				.log().everything()
    			.statusCode(HttpStatus.OK.value());
	}


	private void setNewFieldsTo(Training training, String[] newTaskNames, String newName) {
    	training.setName(newName);
    	training.getLocation().setAddress1("New Address 1");
    	training.setTasks(initTasks(training,newTaskNames));
	}

	private void deletAllTrainings() {
		getAllTrainingsRequest().extract().jsonPath()
			.getList(JSON_NESTED_CONTAINER + ".trainings.id").stream()
			.mapToLong(id -> new Long((Integer) id)).forEach(this::deleteTrainingRequest);
	}

	private Training postTrainingRequest(Training training) {
		return 
		given()
                .contentType("application/json")
                .body(training)
        .when()
        		.log().everything()
                .post(basePath + TRAINING_PATH)
        .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract().as(Training.class);
	}

	private ValidatableResponse checkGetRequestReturnNOT_FOUND(long id) {
		return with()
    			.get(basePath + TRAINING_PATH + "/" + id)
    	.then()
				.log().everything()
    			.statusCode(HttpStatus.NOT_FOUND.value());
	}

	private Training initTraining() {
		Training training = Training.builder()
				.name("Test Training")
				.location(initAddress())
				.online(true)
				.build();
		training.setTasks(initTasks(training,"Test Task 1", "Test Task 2"));
		return training;
	}

	private Set<TrainingTask> initTasks(Training training, String... taskNames) {
		return Arrays.stream(taskNames)
				.map(name -> TrainingTask.builder().name(name).build())
				.collect(Collectors.toSet());
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


}