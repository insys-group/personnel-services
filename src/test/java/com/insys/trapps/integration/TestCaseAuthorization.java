package com.insys.trapps.integration;

import com.insys.trapps.model.Business;
import com.insys.trapps.model.BusinessType;
import com.insys.trapps.model.person.Person;
import com.insys.trapps.model.person.PersonType;
import com.insys.trapps.model.security.User;
import com.insys.trapps.respositories.BusinessRepository;
import com.insys.trapps.respositories.PersonRepository;
import com.insys.trapps.respositories.UserRepository;
import com.jayway.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;

@Slf4j
public class TestCaseAuthorization {

    @Autowired
    BusinessRepository businessRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    UserRepository userRepository;

    @Value("${local.server.port}")
    int PORT;

    @Value("${authentication.oauth.path}")
    String OAUTH_PATH;

    @Value("${authentication.oauth.clientid}")
    String PROP_CLIENTID;

    @Value("${authentication.oauth.secret}")
    String PROP_SECRET;

    @Value("${authentication.oauth.username}")
    String USERNAME;

    @Value("${authentication.oauth.password}")
    String PASSWORD;

    @Value("${authentication.oauth.encodedPassword}")
    String ENCODED_PASSWORD;

    String access_token = "";


    @Before
    public void authenticateUser() {
        RestAssured.port = PORT;

        Business business = Business.builder().name("INSYS").businessType(BusinessType.INSYS).description("Test Business").build();
        business = businessRepository.saveAndFlush(business);

        Person personUser = Person.builder().firstName("Armando").lastName("Reyna").email("areyna@insys.com").personType(PersonType.Employee).business(business).build();
        personRepository.saveAndFlush(personUser);
        log.debug("Person created successfully " + personUser.toString());

        if (userRepository.findAll().isEmpty()) {
            User appUser = User.builder().username(USERNAME).password(ENCODED_PASSWORD).personId(personUser.getId()).enabled(Boolean.TRUE).build();
            appUser = userRepository.saveAndFlush(appUser);
            log.debug("User created successfully " + appUser.toString());
        }

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
}
