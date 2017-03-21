/**
 *
 */
package com.insys.trapps.integration;

import com.insys.trapps.model.person.Person;
import com.insys.trapps.model.person.PersonType;
import com.insys.trapps.model.security.User;
import com.insys.trapps.model.security.UserAuthority;
import com.insys.trapps.respositories.PersonRepository;
import com.insys.trapps.respositories.UserAuthorityRepository;
import com.insys.trapps.respositories.UserRepository;
import com.insys.trapps.security.Authorities;
import com.jayway.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;

/**
 * @author msabir
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class OauthRestIntegrationTests {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAuthorityRepository userAuthorityRepository;

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

    @Before
    public void setup() {

        RestAssured.port = PORT;

        Person personUser = Person.builder().firstName("Armando").lastName("Reyna").email("areyna@insys.com").personType(PersonType.Employee).build();
        personRepository.saveAndFlush(personUser);
        log.debug("Person created successfully " + personUser.toString());

        User appUser = User.builder().username(USERNAME).password(ENCODED_PASSWORD).personId(personUser.getId()).enabled(Boolean.TRUE).build();
        appUser = userRepository.saveAndFlush(appUser);
        log.debug("User created successfully " + appUser.toString());

    }

    @After
    public void cleanup() {
    }

    @Test
    public void testOauth2() throws Exception {
        given().auth().basic(PROP_CLIENTID, PROP_SECRET)
                .log().everything()
                .when()
                .contentType("application/json")
                .queryParam("grant_type", "password")
                .queryParam("username", USERNAME)
                .queryParam("password", PASSWORD)
                .post(OAUTH_PATH)
                .then()
                .statusCode(HttpStatus.OK.value());
    }


}
