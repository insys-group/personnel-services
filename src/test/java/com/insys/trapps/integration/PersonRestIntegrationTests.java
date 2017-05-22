/**
 *
 */
package com.insys.trapps.integration;

import com.insys.trapps.model.*;
import com.insys.trapps.model.person.Person;
import com.insys.trapps.model.person.PersonSkill;
import com.insys.trapps.model.person.PersonType;
import com.insys.trapps.respositories.BusinessRepository;
import com.insys.trapps.respositories.PersonRepository;
import com.insys.trapps.respositories.SkillRepository;
import com.insys.trapps.respositories.TrainingRepository;
import com.jayway.restassured.path.json.JsonPath;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.jayway.restassured.RestAssured.given;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class PersonRestIntegrationTests extends TestCaseAuthorization {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private SkillRepository skillRepository;

    private String PERSON_PATH = "/persons";

    @Value("${spring.data.rest.basePath}")
    private String BASE_PATH;

    @Before
    public void setup() {

    }

    @Test
    public void testCreatePerson() throws Exception {
        Person person = buildPerson();
        given()
                .auth().oauth2(access_token)
                .contentType("application/json")
                .body(person)
                .log().everything()
                .when()
                .post(BASE_PATH + PERSON_PATH)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .assertThat()
                .body("id", greaterThan(0));
    }

    @Test
    public void testUpdatePerson() throws Exception {
        Person person = buildAndSave();

        person.setFirstName("UpdatedName");

        given()
                .auth().oauth2(access_token)
                .contentType("application/json")
                .body(person)
                .log().everything()
                .when()
                .post(BASE_PATH + PERSON_PATH)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .assertThat()
                .body("firstName", equalTo("UpdatedName"));
    }

    @Test
    public void testUpdatePersonWithAddress() {
        Person person = buildAndSave();
        person.setAddress(buildAddress());

        given()
                .auth().oauth2(access_token)
                .contentType("application/json")
                .body(person)
                .log().everything()
                .when()
                .post(BASE_PATH + PERSON_PATH)
                .then()
                .log().everything()
                .statusCode(HttpStatus.CREATED.value())
                .assertThat()
                .body("address", hasKey("id"))
                .body("address.id", greaterThan(0));
    }


    @Test
    public void testUpdatePersonWithSkills() {
        Person person = buildAndSave();
        person.setPersonSkills(buildSkills());

        JsonPath response = given()
                .auth().oauth2(access_token)
                .contentType("application/json")
                .body(person)
                .log().everything()
                .when()
                .post(BASE_PATH + PERSON_PATH)
                .then()
                .log().everything()
                .statusCode(HttpStatus.CREATED.value())
                .assertThat()
                .body("_links", hasKey("personSkills"))
                .body("_links.personSkills", hasKey("href"))
                .extract().jsonPath();

        String personSkillsUrl = response.getString("_links.personSkills.href");

        given().auth().oauth2(access_token)
                .contentType("application/json")
                .body(person)
                .log().everything()
                .when()
                .get(personSkillsUrl)
                .then()
                .log().everything()
                .statusCode(HttpStatus.OK.value())
                .assertThat()
                .body("_embedded", hasKey("personSkills"))
                .body("_embedded.personSkills", hasSize(1))
                .body("_embedded.personSkills.id", containsInAnyOrder(1))
                .body("_embedded.personSkills.scale", containsInAnyOrder(9));
    }

    @Test
    public void testUpdatePersonWithTrainings() {
        Person person = buildAndSave();
        person.setPersonTrainings(buildTrainings());

        given()
                .auth().oauth2(access_token)
                .contentType("application/json")
                .body(person)
                .log().everything()
                .when()
                .post(BASE_PATH + PERSON_PATH)
                .then()
                .log().everything()
                .statusCode(HttpStatus.CREATED.value())
                .assertThat()
                .body("personTrainings", hasSize(1))
                .body("personTrainings.id", containsInAnyOrder(2));
    }

    @Test
    public void testUpdatePersonWithTrainingsMakingProgress() {
        Person person = buildAndSave();
        person.setPersonTrainings(buildTrainings());

        personRepository.saveAndFlush(person);
        person = personRepository.findOne(person.getId());

        person.getPersonTrainings().forEach(training -> training.setProgress(ProgressType.COMPLETED));

        given()
                .auth().oauth2(access_token)
                .contentType("application/json")
                .body(person)
                .log().everything()
                .when()
                .post(BASE_PATH + PERSON_PATH)
                .then()
                .log().everything()
                .statusCode(HttpStatus.CREATED.value())
                .assertThat()
                .body("personTrainings", hasSize(1))
                .body("personTrainings.id", containsInAnyOrder(1));
    }

    private Person savePerson(Person person) {
        person = personRepository.saveAndFlush(person);
        return personRepository.findOne(person.getId());
    }

    private Person buildPerson() {
        Business business = Business.builder().name("INSYS").businessType(BusinessType.INSYS).description("Test Business").build();
        businessRepository.saveAndFlush(business);
        log.debug("Business created successfully " + business.toString());

        return Person.builder().firstName("Test").lastName("Test")
                .personType(PersonType.Employee).business(business).email("test@luxoft.com").build();
    }

    private Address buildAddress() {
        Address address = Address.builder().address1("2101 N Ursula St").address2("Apt 323").city("Aurora")
                .state("CO").country("USA").zipCode("80045").build();
        return address;
    }

    private Set<PersonSkill> buildSkills() {

        Skill skill = Skill.builder().name("Java").build();
        skillRepository.saveAndFlush(skill);
        skillRepository.findAll().forEach(sk -> log.debug("Skill created is " + sk.getId()));

        Set<PersonSkill> personSkills = new HashSet<>();
        PersonSkill personSkill = PersonSkill.builder().skill(skill).scale(9).build();
        personSkills.add(personSkill);
        return personSkills;
    }

    private Set<PersonTraining> createPersonTrainings(Person person, Training training) {
        return new HashSet<>(singletonList(PersonTraining.builder()
                .progress(ProgressType.IN_PROGRESS)
                .startDate(createStartDate())
                .endDate(createEndDate())
                .training(training).build()));
    }

    private Set<PersonTraining> buildTrainings() {
        final Training training = Training.builder()
                .name("Training 1")
                .online(true)
                .tasks(createTasks("Test Task 1", "Test Task 2"))
                .build();
        training.getTasks().forEach(task -> task.setId(training.getId()));
        Training savedTraining = trainingRepository.saveAndFlush(training);
        savedTraining = trainingRepository.findOne(training.getId());

        Set<PersonTraining> personTrainings = new HashSet<>();
        PersonTraining personTraining = PersonTraining.builder().training(savedTraining)
                .progress(ProgressType.IN_PROGRESS).startDate(createStartDate()).endDate(createEndDate()).build();
        personTrainings.add(personTraining);

        return personTrainings;
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


    private Person buildAndSave(){
        Person person = buildPerson();
        return savePerson(person);
    }

}
