/**
 * 
 */
package com.insys.trapps.integration;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
import com.insys.trapps.model.PersonSkill;
import com.insys.trapps.model.PersonType;
import com.insys.trapps.respositories.BusinessRepository;
import com.insys.trapps.respositories.PersonRepository;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import lombok.extern.slf4j.Slf4j;

/**
 * @author msabir
 *
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
		business=Business.builder().name("Test Business").businessType(BusinessType.INSYS).description("Test Business").build();
		businessRepository.saveAndFlush(business);
		log.debug("Business created successfully" + business.toString());
    }

    @After
    public void cleanup() {
    }

    @Test
    public void testCreatePerson() throws Exception {
		Person person=Person.builder().firstName("Omar").lastName("Sabir")
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
		Set<PersonSkill> personSkills=new HashSet<>();
		personSkills.add(PersonSkill.builder().name("Spring").scale(8).build());
		personSkills.add(PersonSkill.builder().name("JPA").scale(8).build());
		Person person=Person.builder().firstName("Omar").lastName("Sabir")
				.personType(PersonType.Candidate).business(business).email("omar@insys.com")
				.personSkills(personSkills)
				.build();
		List<Map<String, Object>> savedSkills=
		given()
                .contentType("application/json")
                .body(person)
                .log().everything()
        .when()
                .post(basePath + PERSON_PATH)
        .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract().jsonPath().getList("personSkills");
		assertEquals(2, savedSkills.size());
		
		savedSkills.forEach(skill -> {
			assertNotNull(skill.get("id"));
			log.debug("Skills saved " + skill.get("id") + ", " + skill.get("name") + ", " + skill.get("scale"));
		});
    }
    
    @Test
    public void testUpdatePersonWithSkills() {
		Person person=createPersonWithSkills();
		PersonSkill skill=PersonSkill.builder().name("AWS").scale(8).build();
		person.getPersonSkills().add(skill);
		person.getPersonSkills().forEach(s -> {if(s.getName().equals("Spring")) {s.setScale(10); s.setId(null);}});
    	person.getBusiness().setDescription("TestingBusinessUpdate");
		given()
                .contentType("application/json")
                .body(person)
                .log().everything()
        .when()
                .put(basePath + PERSON_PATH + "/put/" + person.getId())
        .then()
                .statusCode(HttpStatus.NO_CONTENT.value()).log().everything();


		Response response =
		given()
                .contentType("application/json")
                .log().everything()
        .when()
                .get(basePath + PERSON_PATH + "/" + person.getId())
        .then()
                .statusCode(HttpStatus.OK.value())
                .extract().response();
		
		List<Map<String, Object>> savedSkills=response.jsonPath().getList("personSkills");
		
		assertEquals(3, savedSkills.size());
		savedSkills.forEach(s-> {
			assertNotNull(s.get("id"));
			log.debug("Skills saved " + s.get("id") + ", " + s.get("name") + ", " + s.get("scale"));
		});		
    }
    
    @Test
    public void testDeletePersonWithSkills() {
		Person person=createPersonWithSkills();
		given()
                .log().everything()
        .when()
                .delete(basePath + PERSON_PATH + "/" + person.getId())
        .then()
                .statusCode(HttpStatus.NO_CONTENT.value()).log().everything();

		person=repository.findOne(person.getId());
		assertNull(person);
    }
    
    public Person createPersonWithSkills() {
		Set<PersonSkill> personSkills=new HashSet<>();
		personSkills.add(PersonSkill.builder().name("Spring").scale(8).build());
		personSkills.add(PersonSkill.builder().name("JPA").scale(8).build());
		Person person=Person.builder().firstName("Omar").lastName("Sabir")
				.personType(PersonType.Candidate).business(business).email("omar@insys.com")
				.personSkills(personSkills)
				.build();
		Map<String, Object> personProperties=
		given()
                .contentType("application/json")
                .body(person)
                .log().everything()
        .when()
                .post(basePath + PERSON_PATH)
        .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract().jsonPath().get();
		
		assertNotNull(personProperties.get("id"));
		
		List<Map<String, Object>> skills=(List<Map<String, Object>>)personProperties.get("personSkills");
		skills.forEach(skillMap -> {
			log.debug("Person Properties - Skills Map " + skillMap.get("id") + " " + skillMap.get("name"));
			
			person.getPersonSkills().forEach(personObjectSkill -> {
				if(personObjectSkill.getName().equals(skillMap.get("name"))){
					personObjectSkill.setId(new Long((Integer)skillMap.get("id")));
				}
			});
			
		});
		person.setId(new Long((Integer)personProperties.get("id")));
		return person;
    }
    /*
    private String restBody(Person value) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider().addFilter("restBusinessFilter",
		SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "description", "businessType"));
		// and then serialize using that filter provider:
		mapper.setFilterProvider(filters);
		return mapper.writeValueAsString(value);
    }*/
}
