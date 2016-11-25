package com.insys.trapps;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.insys.trapps.model.BusinessEntity;
import com.insys.trapps.model.BusinessEntityType;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insys.trapps.model.Address;
import com.insys.trapps.model.BusinessType;
import com.insys.trapps.util.BusinessBuilder;

/**
 * {@link Integration Test using Spring MVC } for PersonnelServices. One can load the real
 * database for testing
 * @author Kris Krishna
 * @since 1.0.0
 **/


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(value = "classpath:clients-test-resource.xml")
// Uncomment to test with real external MySQL database
// @TestPropertySource(locations="classpath:test.properties")
public class BusinessSpringIntegrationTests {

	private static final Logger logger = LoggerFactory
			.getLogger(BusinessSpringIntegrationTests.class);

	protected MockMvc mvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	protected ObjectMapper objectMapper;

	@BeforeClass
	static public void setup() {

	}

	@AfterClass
	static public void teardown() {

	}

	@Before
	public void setUpMock() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		objectMapper = new ObjectMapper();
	}

	@Test
	@Ignore
	public void testListBusinesses() throws Exception {

		ResultActions resultActions = mvc.perform(get("/businesses"));

		MvcResult result = resultActions.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith("application/json"))
				.andReturn();

		String content = result.getResponse().getContentAsString();

		logger.info(content);

		assertNotNull(content);

	}

	@Test
	@Ignore
	public void testCreateBusiness() throws Exception {

		Address address_1 = Address.builder()
				.address1("Insys Street")
				.city("Denver")
				.state("CO")
				.zipCode("80014")
				.build();
		Address address_2 =Address.builder()
				.address1("Luxoft Street")
				.city("Seattle")
				.state("WA")
				.zipCode("70014")
				.build();

		BusinessEntity testBuisness = BusinessEntity.builder()
				.descr("test")
				.name("testing-denver")
				.entityType(BusinessEntityType.INSYS)
				.build();

        ResultActions resultActions = null;

        resultActions = mvc
                .perform(post("/address").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(address_1)));
        resultActions = mvc
                .perform(post("/address").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(address_2)));


        resultActions = mvc
				.perform(post("/businesses").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(testBuisness)));

		MvcResult result = resultActions.andExpect(status().isCreated())
				.andExpect(content().contentTypeCompatibleWith("application/json"))
				.andReturn();

		String content = result.getResponse().getContentAsString();

		logger.info(content);

		assertNotNull(content);

	}

}
