package com.insys.trapps.integration;

import com.insys.trapps.model.Address;
import com.insys.trapps.model.Training;
import com.insys.trapps.model.TrainingTask;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.with;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TrainingRestIntegrationTest extends TestCaseAuthorization {

    
    @Value("${spring.data.rest.basePath}")
    private String basePath;

    private static final String TRAINING_PATH = "/trainings";

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
	public void testUpdateTraining() throws Exception {
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

	private void deleteTrainingRequest(long id) {
		with()
				.auth().oauth2(access_token)
				.log().everything()
		        .delete(basePath + TRAINING_PATH + "/" + id)
		.then()
		        .statusCode(HttpStatus.NO_CONTENT.value());
	}

	private ValidatableResponse getTrainingRequest(long id) {
		return with()
				.auth().oauth2(access_token)
				.get(basePath + TRAINING_PATH + "/" + id)
		.then()
				.log().everything()
				.statusCode(HttpStatus.OK.value());
	}

	private void setNewFieldsTo(Training training, String[] newTaskNames, String newName) {
    	training.setName(newName);
    	training.getLocation().setAddress1("New Address 1");
    	training.setTasks(initTasks(newTaskNames));
	}


	private Training postTrainingRequest(Training training) {
		return 
		given()
				.auth().oauth2(access_token)
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
				.auth().oauth2(access_token)
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
		training.setTasks(initTasks("Test Task 1", "Test Task 2"));
		return training;
	}

	private Set<TrainingTask> initTasks(String... taskNames) {
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
