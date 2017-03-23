package com.insys.trapps.integration;

import com.insys.trapps.model.Project;
import com.jayway.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.with;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectRestIntegrationTest extends TestCaseAuthorization {

    @Value("${spring.data.rest.basePath}")
    private String basePath;

    private static final String ROJECT_PATH = "/projects";

    @Test
    public void testCreateProject() throws Exception {
    	Project project = initProject();
    	
    	long id = postProjectRequest(project).getId();
    	
		getProjectRequest(id)
			.body("name", equalTo("Test Project"));
    }
    
    @Test
    public void testDeleteProject() throws Exception {
    	Project project = initProject();
    	
    	long id = postProjectRequest(project).getId();
    	deleteProjectRequest(id);

		checkGetRequestReturnNOT_FOUND(id);
    }

	@Test
	public void testUpdateProject() throws Exception {
		Project project = initProject();
		String newName = "New Name";

		Project postedProject = postProjectRequest(project);
		postedProject.setName(newName);
		postProjectRequest(postedProject);

		getProjectRequest(postedProject.getId())
				.body("name", equalTo(newName));
	}

	private void deleteProjectRequest(long id) {
		with()
				.auth().oauth2(access_token)
				.log().everything()
		        .delete(basePath + ROJECT_PATH + "/" + id)
		.then()
		        .statusCode(HttpStatus.NO_CONTENT.value());
	}

	private ValidatableResponse getProjectRequest(long id) {
		return with()
				.auth().oauth2(access_token)
				.get(basePath + ROJECT_PATH + "/" + id)
		.then()
				.log().everything()
				.statusCode(HttpStatus.OK.value());
	}

	private void setNewFieldsTo(Project project, String[] newTaskNames, String newName) {
    	project.setName(newName);

	}


	private Project postProjectRequest(Project project) {
		return 
		given()
				.auth().oauth2(access_token)
                .contentType("application/json")
                .body(project)
        .when()
        		.log().everything()
                .post(basePath + ROJECT_PATH)
        .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract().as(Project.class);
	}

	private ValidatableResponse checkGetRequestReturnNOT_FOUND(long id) {
		return with()
				.auth().oauth2(access_token)
    			.get(basePath + ROJECT_PATH + "/" + id)
    	.then()
				.log().everything()
    			.statusCode(HttpStatus.NOT_FOUND.value());
	}

	private Project initProject() {
		return Project.builder()
				.name("Test Project")
				.build();
	}


}
