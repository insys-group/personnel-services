package com.insys.trapps.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insys.trapps.model.Address;
import com.insys.trapps.model.Business;
import com.insys.trapps.model.BusinessType;
import com.insys.trapps.service.BusinessService;
import com.insys.trapps.util.BusinessBuilder;

/**
 * @author kkrish003c
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = MockServletContext.class)
public class BusinessControllerTest {

	protected MockMvc mvc;

	@InjectMocks
	BusinessController businessController;

	protected ObjectMapper objectMapper;

	@BeforeClass
	static public void setup() {

	}

	@AfterClass
	static public void teardown() {

	}

	@Mock
	private BusinessService businessService;

	private Business testBusiness;

	@Before
	public void setUpMock() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(businessController).build();
		objectMapper = new ObjectMapper();

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

		testBusiness = BusinessBuilder
				.buildBusiness("test", "testing-denver", BusinessType.Client)
				.addLocation(address_1).addLocation(address_2).build();

	}

	@Test
	public void testlistBusinesses() throws Exception {
		Mockito.when(businessService.listBusinesses()).then(new Answer() {
			@Override
			public List<Business> answer(InvocationOnMock invocation) throws Throwable {

				return (Arrays.asList(testBusiness));
			}

		});

		MvcResult result = mvc.perform(get("/businesses")).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith("application/json"))
				.andReturn();

		String content = result.getResponse().getContentAsString();
		assertNotNull(content);
	}

	@Test
	public void testCreateBusiness() throws Exception {
		Mockito.when(businessService.createBusiness(any(Business.class))).then(new Answer() {
			@Override
			public Business answer(InvocationOnMock invocation) throws Throwable {

				return testBusiness;
			}

		});
		ResultActions resultActions = mvc.perform(post("/businesses").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(testBusiness)));
		
		 MvcResult result = resultActions.andExpect(status().isCreated()).andExpect(content().contentTypeCompatibleWith("application/json")).andReturn();

		String content = result.getResponse().getContentAsString();
		assertNotNull(content);
	}

}
