/**
 *
 */
package com.insys.trapps.integration;

import com.insys.trapps.model.*;
import com.insys.trapps.respositories.BusinessRepository;
import com.insys.trapps.respositories.PersonRepository;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.with;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * @author msabir
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class PersonRestIntegrationTests {
    @Autowired
    private PersonRepository repository;
    @Autowired
    private BusinessRepository businessRepository;

    private Business business;

    @Value("${local.server.port}")
    private int port;

    @Value("${spring.data.rest.basePath}")
    private String basePath;

    private final String PERSON_PATH = "/persons";

    @Before
    public void setup() {
        RestAssured.port = port;
        business = Business.builder().name("Test Business").businessType(BusinessType.INSYS).description("Test Business").build();
        businessRepository.saveAndFlush(business);
        log.debug("Business created successfully" + business.toString());
    }

    @After
    public void cleanup() {
    }

    @Test
    public void testCreatePerson() throws Exception {
        Person person = Person.builder().firstName("Omar").lastName("Sabir")
                .personType(PersonType.Candidate).business(business)
                .email("omar@insys.com")
                .build();
        given()
                .contentType("application/json")
                .body(person)
                .log().everything()
        .when()
                .post(basePath + PERSON_PATH)
        .then()
                .statusCode(HttpStatus.CREATED.value());
    }


    @Test
    public void testCreatePersonWithSkills() {
        Set<PersonSkill> personSkills = new HashSet<>();
        personSkills.add(PersonSkill.builder().name("Spring").scale(8).build());
        personSkills.add(PersonSkill.builder().name("JPA").scale(8).build());
        Person person = Person.builder().firstName("Omar").lastName("Sabir")
                .personType(PersonType.Candidate).business(business).email("omar@insys.com")
                .personSkills(personSkills)
                .build();
        given()
                .contentType("application/json")
                .body(person)
                .log().everything()
        .when()
                .post(basePath + PERSON_PATH)
        .then()
                .log().everything()
                .statusCode(HttpStatus.CREATED.value())
        .assertThat()
                .body("personSkills", hasSize(2))
                .body("personSkills.id", containsInAnyOrder(asList(greaterThan(0), greaterThan(0))));
    }

    @Test
    public void testUpdatePersonWithSkills() {
        Person person = postPersonRequest(createPersonWithSkills());
        PersonSkill skill = PersonSkill.builder().name("AWS").scale(8).build();
        person.getPersonSkills().add(skill);
        person.getPersonSkills().forEach(s -> {
            if (s.getName().equals("Spring")) {
                s.setScale(10);
                s.setId(null);
            }
        });
        person.getBusiness().setDescription("TestingBusinessUpdate");
        given()
                .contentType("application/json")
                .body(person)
                .log().everything()
        .when()
                .put(basePath + PERSON_PATH + "/put/" + person.getId())
        .then()
                .statusCode(HttpStatus.NO_CONTENT.value()).log().everything();

        with()
                .contentType("application/json")
        .when()
                .get(basePath + PERSON_PATH + "/" + person.getId())
        .then()
                .log().everything()
                .statusCode(HttpStatus.OK.value())
        .assertThat()
                .body("personSkills", hasSize(3))
                .body("personSkills.id", containsInAnyOrder(asList(greaterThan(0), greaterThan(0), greaterThan(0))))
                .body("personSkills.scale", hasItems(10));
    }

    @Test
    public void testCreatePersonWithTraining() {
        Training savedTraining = postTrainingRequest(createTraining("Test Training"));
        Person personWithTraining = createPersonWithTraining(savedTraining);
        Person person = postPersonRequest(personWithTraining);

        with()
                .contentType("application/x-spring-data-verbose+json")
                .get(basePath + PERSON_PATH + "/" + person.getId())
                .then()
                .log().everything()
        .assertThat()
                .body("personTrainings._links.training", hasSize(1))
                .body("personTrainings._links.training.href", linksToIdOf(savedTraining));

    }

    @Test
    public void testUpdatePersonWithTraining() {
        Training firstTraining = postTrainingRequest(createTraining("First Test Training"));
        Person savedPerson = postPersonRequest(createPersonWithTraining(firstTraining));
        Training newTraining = postTrainingRequest(createTraining("Second Test Training"));
        PersonTraining newPersonTraining = createPersonTraining(savedPerson, newTraining);
        savedPerson.getPersonTrainings().add(newPersonTraining);

        given()
                .contentType("application/json")
                .body(savedPerson)
                .log().everything()
                .when()
                .put(basePath + PERSON_PATH + "/put/" + savedPerson.getId())
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value()).log().everything();

        Person response = getPersonRequest(savedPerson);

        assertThat(response.getPersonTrainings(), hasSize(2));
    }

    @Test
    public void testUpdateTaskCompletion() {
        Training firstTraining = postTrainingRequest(createTraining("First Test Training"));
        Person savedPerson = postPersonRequest(createPersonWithTraining(firstTraining));
        PersonTraining personTraining = savedPerson.getPersonTrainings().stream().findAny().get();
        TrainingTask trainingTask = personTraining.getTraining().getTasks().stream().findAny().get();
        personTraining.setCompletedTasks(new HashSet<>(singletonList(trainingTask)));

        given()
                .contentType("application/json")
                .body(savedPerson)
                .log().everything()
                .when()
                .put(basePath + PERSON_PATH + "/put/" + savedPerson.getId())
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value()).log().everything();

        Person response = getPersonRequest(savedPerson);

        Set<TrainingTask> completedTasks = response.getPersonTrainings().stream().findFirst().get().getCompletedTasks();
        assertThat(completedTasks, contains(trainingTask));
    }

    @Test
    public void testDeletePersonWithSkills() {
        Person person = postPersonRequest(createPersonWithSkills());
        given()
                .log().everything()
        .when()
                .delete(basePath + PERSON_PATH + "/" + person.getId())
        .then()
                .statusCode(HttpStatus.NO_CONTENT.value()).log().everything();

        person = repository.findOne(person.getId());
        assertNull(person);
    }

    private Person getPersonRequest(Person person) {
        return with()
                .get(basePath + PERSON_PATH + "/" + person.getId())
                .then()
                .log().everything()
                .extract().as(Person.class);
    }

    private Person postPersonRequest(Person person) {
        return
                given()
                        .contentType("application/json")
                        .body(person)
                        .when()
                        .log().everything()
                        .post(basePath + PERSON_PATH)
                        .then()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract().as(Person.class);
    }

    private Training postTrainingRequest(Training training) {
        return
                given()
                        .contentType("application/json")
                        .body(training)
                .when()
                        .log().everything()
                        .post(basePath + "/trainings")
                .then()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract().as(Training.class);
    }

    public Person createPersonWithSkills() {
        Set<PersonSkill> personSkills = new HashSet<>();
        personSkills.add(PersonSkill.builder().name("Spring").scale(8).build());
        personSkills.add(PersonSkill.builder().name("JPA").scale(8).build());
        return Person.builder().firstName("Omar").lastName("Sabir")
                .personType(PersonType.Candidate).business(business).email("omar@insys.com")
                .personSkills(personSkills)
                .build();

    }

    private Person createPersonWithTraining(Training training) {
        Person person = Person.builder().firstName("Omar").lastName("Sabir")
                .personType(PersonType.Employee).business(business).email("omar@insys.com")
                .build();
        PersonTraining personTraining = createPersonTraining(person, training);
        person.setPersonTrainings(new HashSet<>(singletonList(personTraining)));
        return person;
    }

    private PersonTraining createPersonTraining(Person person, Training training) {
        return PersonTraining.builder()
                .progress(ProgressType.IN_PROGRESS)
                .startDate(createStartDate())
                .endDate(createEndDate())
                .training(training)
                .person(person).build();
    }

    private Training createTraining(String name) {
        Training training = Training.builder()
                .id(0L)
                .name(name)
                .online(true)
                .tasks(createTasks("Test Task 1", "Test Task 2"))
                .build();
        training.getTasks().forEach(task -> task.setId(training.getId()));
        return training;
    }

    private Set<TrainingTask> createTasks(String... taskNames) {
        return Arrays.stream(taskNames)
                .map(name -> TrainingTask.builder().name(name).description("Spring training tasks")
                        .weblink("localhost").build())
                .collect(Collectors.toSet());
    }

    private long createStartDate() {
        return LocalDate.of(2017, Month.JANUARY, 25).toEpochDay();
    }

    private long createEndDate() {
        return LocalDate.of(2017, Month.JANUARY, 31).toEpochDay();
    }

    private Matcher<Iterable<String>> linksToIdOf(Training savedTraining) {
        return hasItems(endsWith(savedTraining.getId().toString()));
    }
}
