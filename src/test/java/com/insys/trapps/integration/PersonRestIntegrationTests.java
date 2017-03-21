/**
 *
 */
package com.insys.trapps.integration;

import com.insys.trapps.model.*;
import com.insys.trapps.model.security.User;
import com.insys.trapps.model.security.UserAuthority;
import com.insys.trapps.respositories.*;
import com.insys.trapps.security.Authorities;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.ValidatableResponse;
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

import com.insys.trapps.model.Business;
import com.insys.trapps.model.BusinessType;
import com.insys.trapps.model.person.Person;
import com.insys.trapps.model.person.PersonSkill;
import com.insys.trapps.model.person.PersonType;
import com.insys.trapps.respositories.BusinessRepository;
import com.insys.trapps.respositories.PersonRepository;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import static com.jayway.restassured.RestAssured.basic;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.with;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNull;

/**
 * @author msabir
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class PersonRestIntegrationTests {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAuthorityRepository userAuthorityRepository;

    private Business business;

    private String PERSON_PATH = "/persons";

    @Value("${local.server.port}")
    private int PORT;

    @Value("${spring.data.rest.basePath}")
    private String BASE_PATH;

    @Value("${authentication.oauth.path}")
    private String OAUTH_PATH;

    @Value("${authentication.oauth.clientid}")
    private String PROP_CLIENTID;

    @Value("${authentication.oauth.secret}")
    private String PROP_SECRET;

    @Value("${authentication.oauth.username}")
    private String USERNAME;

    @Value("${authentication.oauth.password}")
    private String PASSWORD;

    @Value("${authentication.oauth.encodedPassword}")
    private String ENCODED_PASSWORD;

    private String access_token = "";

    @Before
    public void setup() {

        RestAssured.port = PORT;

        Person personUser = Person.builder().firstName("Armando").lastName("Reyna").email("areyna@insys.com").personType(PersonType.Employee).build();
        personRepository.saveAndFlush(personUser);
        log.debug("Person created successfully " + personUser.toString());

        User appUser = User.builder().username(USERNAME).password(ENCODED_PASSWORD).personId(personUser.getId()).enabled(Boolean.TRUE).build();
        appUser = userRepository.saveAndFlush(appUser);
        log.debug("User created successfully " + appUser.toString());

        business = Business.builder().name("Test Business").businessType(BusinessType.INSYS).description("Test Business").build();
        businessRepository.saveAndFlush(business);
        log.debug("Business created successfully " + business.toString());

        access_token = given().auth().basic(PROP_CLIENTID, PROP_SECRET)
                .log().everything()
                .when()
                .contentType("application/json")
                .queryParam("grant_type", "password")
                .queryParam("username", USERNAME)
                .queryParam("password", PASSWORD)
                .post(OAUTH_PATH)
                .then()
                .extract().path("access_token");

        log.debug("access_token " + access_token);
    }

    @After
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    public void testCreatePerson() throws Exception {
        Person person = personBuilder().build();
        given().auth().oauth2(access_token)
                .contentType("application/json")
                .body(person)
                .log().everything()
        .when()
                .post(BASE_PATH + PERSON_PATH)
        .then()
                .statusCode(HttpStatus.CREATED.value());
    }


    @Test
    public void testCreatePersonWithSkills() {
        Person person = createPersonWithSkills();
        given().auth().oauth2(access_token)
                .contentType("application/json")
                .body(person)
                .log().everything()
        .when()
                .post(BASE_PATH + PERSON_PATH)
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

        putPersonRequest(person);

        getPersonRequest(person)
                .statusCode(HttpStatus.OK.value())
        .assertThat()
                .body("personSkills", hasSize(3))
                .body("personSkills.id", containsInAnyOrder(asList(greaterThan(0), greaterThan(0), greaterThan(0))))
                .body("personSkills.scale", hasItems(10));
    }

    @Test
    public void testCreatePersonWithTraining() {
        Training savedTraining = postTrainingRequest(createTraining("Test Training"));
        Person savedPerson = postPersonRequest(personBuilder().build());
        savedPerson.setPersonTrainings(createPersonTrainings(savedPerson, savedTraining));

        putPersonRequest(savedPerson);

        getPersonRequest(savedPerson)
        .assertThat()
                .body("personTrainings._links.training", hasSize(1))
                .body("personTrainings._links.training.href", linksToIdOf(savedTraining));
    }

    @Test
    public void testDeletePersonTraininigs() {
        Training savedTraining = postTrainingRequest(createTraining("Test Training"));
        Person savedPerson = postPersonRequest(personBuilder().build());
        savedPerson.setPersonTrainings(createPersonTrainings(savedPerson, savedTraining));
        putPersonRequest(savedPerson);

        savedPerson.getPersonTrainings().clear();
        putPersonRequest(savedPerson);

        getPersonRequest(savedPerson)
                .assertThat()
                .body("personTrainings", hasSize(0));
    }


    @Test
    public void testUpdateTaskCompletion() {
        Training savedTraining = postTrainingRequest(createTraining("Test Training"));
        Person savedPerson = postPersonRequest(personBuilder().build());
        Set<PersonTraining> personTrainings = createPersonTrainings(savedPerson, savedTraining);
        savedPerson.setPersonTrainings(personTrainings);
        putPersonRequest(savedPerson);
        savedPerson = getPersonRequestAs(savedPerson);
        PersonTraining personTraining = savedPerson.getPersonTrainings().stream().findAny().get();
        TrainingTask trainingTask = savedTraining.getTasks().stream().findAny().get();
        personTraining.setCompletedTasks(new HashSet<>(singletonList(trainingTask)));

        putPersonRequest(savedPerson);

        getPersonRequest(savedPerson)
        .assertThat()
                .body("personTrainings.completedTasks.name", contains(contains(trainingTask.getName())));
    }
    @Test
    public void testUpdateTaskCompletionForTwoPersons() {
        Training savedTraining = postTrainingRequest(createTraining("Test Training"));
        Person savedPerson = postPersonRequest(personBuilder().build());
        Person anotherPerson = Person.builder().firstName("Omar").lastName("Sabir")
                .personType(PersonType.Employee).business(business).email("omar@insys.com").build();
        Person anotherSavedPerson = postPersonRequest(anotherPerson);
        Set<PersonTraining> personTrainings = createPersonTrainings(savedPerson, savedTraining);
        savedPerson.setPersonTrainings(personTrainings);
        putPersonRequest(savedPerson);
        Set<PersonTraining> anotherPersonTrainings = createPersonTrainings(anotherSavedPerson, savedTraining);
        anotherSavedPerson.setPersonTrainings(anotherPersonTrainings);
        putPersonRequest(anotherSavedPerson);
        savedPerson = getPersonRequestAs(savedPerson);
        anotherSavedPerson = getPersonRequestAs(anotherSavedPerson);
        PersonTraining personTraining = savedPerson.getPersonTrainings().stream().findAny().get();
        PersonTraining anotherPersonTraining = anotherSavedPerson.getPersonTrainings().stream().findAny().get();
        TrainingTask trainingTask = savedTraining.getTasks().stream().findAny().get();
        personTraining.setCompletedTasks(new HashSet<>(singletonList(trainingTask)));
        anotherPersonTraining.setCompletedTasks(new HashSet<>(singletonList(trainingTask)));

        putPersonRequest(savedPerson);
        putPersonRequest(anotherSavedPerson);

        getPersonRequest(savedPerson)
                .assertThat()
                .body("personTrainings.completedTasks.name", contains(contains(trainingTask.getName())));
        getPersonRequest(anotherSavedPerson)
                .assertThat()
                .body("personTrainings.completedTasks.name", contains(contains(trainingTask.getName())));
    }

    @Test
    public void testDeletePersonWithSkills() {
        Person person = postPersonRequest(createPersonWithSkills());
        given().auth().oauth2(access_token)
                .log().everything()
        .when()
                .delete(BASE_PATH + PERSON_PATH + "/" + person.getId())
        .then()
                .statusCode(HttpStatus.NO_CONTENT.value()).log().everything();

        person = personRepository.findOne(person.getId());
        assertNull(person);
    }

    private Person getPersonRequestAs(Person person) {
        return getPersonRequest(person)
                .extract().as(Person.class);
    }

    private ValidatableResponse getPersonRequest(Person person) {
        return with()
                .get(BASE_PATH + PERSON_PATH + "/" + person.getId())
        .then()
                .log().everything();
    }

    private Person postPersonRequest(Person person) {
        return
                given().auth().oauth2(access_token)
                        .contentType("application/json")
                        .body(person)
                .when()
                        .log().everything()
                        .post(BASE_PATH + PERSON_PATH)
                .then()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract().as(Person.class);
    }

    private void putPersonRequest(Person savedPerson) {
        given().auth().oauth2(access_token)
                .contentType("application/json")
                .body(savedPerson)
                .log().everything()
        .when()
                .put(BASE_PATH + PERSON_PATH + "/put/" + savedPerson.getId())
        .then()
                .statusCode(HttpStatus.NO_CONTENT.value()).log().everything();
    }

    private Training postTrainingRequest(Training training) {
        return
                given().auth().oauth2(access_token)
                        .contentType("application/json")
                        .body(training)
                .when()
                        .log().everything()
                        .post(BASE_PATH + "/trainings")
                .then()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract().as(Training.class);
    }

    private Person.PersonBuilder personBuilder() {
        return Person.builder().firstName("Omar").lastName("Sabir")
                .personType(PersonType.Employee).business(business).email("omar@insys.com");
    }

    private Person createPersonWithSkills() {
        Set<PersonSkill> personSkills = new HashSet<>();
        personSkills.add(PersonSkill.builder().name("Spring").scale(8).build());
        personSkills.add(PersonSkill.builder().name("JPA").scale(8).build());
        return personBuilder().personSkills(personSkills).build();

    }

    private Set<PersonTraining> createPersonTrainings(Person person, Training training) {
        return new HashSet<>(singletonList(PersonTraining.builder()
                .progress(ProgressType.IN_PROGRESS)
                .startDate(createStartDate())
                .endDate(createEndDate())
                .training(training)
                .person(person).build()));
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

    private Matcher<Iterable<String>> linksToIdOf(Training... savedTraining) {
        Matcher[] endsWithTrainingIdMatchers = Arrays.stream(savedTraining)
                .mapToLong(Training::getId)
                .mapToObj(Long::toString)
                .map(Matchers::endsWith).toArray(Matcher[]::new);
        return hasItems(endsWithTrainingIdMatchers);
    }
}
