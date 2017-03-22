package com.insys.trapps.integration;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import com.insys.trapps.model.person.Person;
import com.jayway.restassured.response.ValidatableResponse;
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

import com.insys.trapps.model.Address;
import com.insys.trapps.model.Business;
import com.insys.trapps.model.BusinessType;
import com.insys.trapps.respositories.BusinessRepository;
import com.jayway.restassured.RestAssured;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class BusinessRestIntegrationTests extends TestCaseAuthorization {
	
	@Autowired
	private BusinessRepository repository;

    @Value("${spring.data.rest.basePath}")
    private String basePath;

    private final String BUSINESS_PATH = "/businesses";


    @Test
    public void testCreateBusiness() throws Exception {
		Business business=Business.builder().name("Comcast")
				.description("Comcast Center")
				.businessType(BusinessType.Client)
				.build();
        given()
				.auth().oauth2(access_token)
                .contentType("application/json")
                .body(business)
                .log().everything()
        .when()
                .post(basePath + BUSINESS_PATH)
        .then()
                .statusCode(HttpStatus.CREATED.value());
    }
    
    @Test
    public void testCreateBusinessWithAddresses() {
    		Set<Address> addresses =new HashSet<>();
    		addresses.add(Address.builder()
    				.address1("701 John F Kennedy Blvd")
    				.city("Philadelphia")
    				.state("PA")
    				.zipCode("19103")
    				.country("USA")
    				.build());
    		addresses.add(Address.builder()
    				.address1("Cable Street")
    				.city("Sterling")
    				.state("VA")
    				.zipCode("22030")
    				.country("USA")
    				.build());
    		Business business=Business.builder()
    				.name("Comcast")
    				.description("Comcast Center")
    				.businessType(BusinessType.Client)
    				.addresses(addresses)
    				.build();
    		
    		List<Map<String, Object>> savedAddresses =
    				given()
							.auth().oauth2(access_token)
    		                .contentType("application/json")
    		                .body(business)
    		                .log().everything()
    		        .when()
    		                .post(basePath + BUSINESS_PATH)
    		        .then()
    		                .statusCode(HttpStatus.CREATED.value())
    		                .extract().jsonPath().getList("addresses");
    		assertEquals(2, savedAddresses.size());
    		savedAddresses.forEach(address -> {
    			assertNotNull(address.get("id"));
    			log.debug("Address saved " + address.get("id") + ", " + address.get("address1") + ", " + address.get("city"));
    			});
    }
    
    @Test
    public void testUpdateBusinessWithAddresses() {
		Business business=createBusinessWithAddresses();
		Address address=Address.builder().address1("625 6th Ave")
				.city("New York")
				.state("NY")
				.zipCode("10011")
				.country("USA")
				.build();
		business.getAddresses().add(address);
		business.getAddresses().forEach(s -> {if(s.getAddress1().equals("Cable Street")) 
			{s.setCity("Reston"); s.setId(null);}});
    	
		given()
				.auth().oauth2(access_token)
                .contentType("application/json")
                .body(business)
                .log().everything()
        .when()
                .put(basePath + BUSINESS_PATH + "/put/1") //@TODO + business.getId())  hack, fix BusinessPutController!
        .then()
                .statusCode(HttpStatus.OK.value()).log().everything();


		business = getBusinessRequest(business);
		
		business.getAddresses().forEach(businessAddress -> {
			log.debug("Address created After " + businessAddress.toString());
			if(businessAddress.getAddress1().equals("Cable Street")) {
				assertEquals(new String("Reston"), businessAddress.getCity());
			}
		});
		log.debug("Business is " + business.toString());
		assertEquals(3, business.getAddresses().size());
		
    }
    
    @Test
    public void testDeleteBusinessWithAddresses() {
		Business business=createBusinessWithAddresses();
		given()
				.auth().oauth2(access_token)
                .log().everything()
        .when()
                .delete(basePath + BUSINESS_PATH + "/" + business.getId())
        .then()
                .statusCode(HttpStatus.NO_CONTENT.value()).log().everything();

		String response =
		with()
				.auth().oauth2(access_token)
				.get(basePath + BUSINESS_PATH + "/" + business.getId())
		.then()
				.log().everything()
				.extract().asString();

		assertThat(response, Matchers.isEmptyString());
	}
    
    public Business createBusinessWithAddresses() {
    	Set<Address> businessAddresses =new HashSet<>();
		businessAddresses.add(Address.builder()
				.address1("701 John F Kennedy Blvd")
				.city("Philadelphia")
				.state("PA")
				.zipCode("19103")
				.country("USA")
				.build());
		businessAddresses.add(Address.builder()
				.address1("Cable Street")
				.city("Sterling")
				.state("VA")
				.zipCode("22030")
				.country("USA")
				.build());
		Business business=Business.builder()
				.name("Comcast")
				.description("Comcast Center")
				.businessType(BusinessType.Client)
				.addresses(businessAddresses)
				.build();
		
		Map<String, Object> businessProperties=
		given()
				.auth().oauth2(access_token)
                .contentType("application/json")
                .body(business)
                .log().everything()
        .when()
                .post(basePath + BUSINESS_PATH)
        .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract().jsonPath().get();

		assertNotNull(businessProperties.get("id"));
		
		List<Map<String, Object>> addresses=(List<Map<String, Object>>)businessProperties.get("addresses");
		addresses.forEach(addressMap -> {
			log.debug("Business Properties - Addresses Map " + addressMap.get("id") + " " + addressMap.get("name"));
			
			business.getAddresses().forEach(businessAddress -> {
				if(businessAddress.getAddress1().equals(addressMap.get("name"))){
					businessAddress.setId(new Long((Integer)addressMap.get("id")));
				}
			});
			
		});
		business.setId(new Long((Integer)businessProperties.get("id")));
		return business;
    }

	private Business getBusinessRequest(Business business) {
		return
				with()
						.auth().oauth2(access_token)
						.get(basePath + BUSINESS_PATH + "/" + business.getId())
						.then()
						.log().everything()
						.extract().as(Business.class);
	}
}
