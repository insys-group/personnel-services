package com.insys.trapps.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;

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
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insys.trapps.model.Address;
import com.insys.trapps.model.Business;
import com.insys.trapps.model.BusinessType;
import com.insys.trapps.respositories.BusinessRepository;
import com.insys.trapps.service.impl.BusinessServiceImpl;
import com.insys.trapps.util.BusinessBuilder;

/**
 * {@link BusinessService Implementation Test} for PersoneelServices.
 *
 * @author Kris Krishna
 * @since 1.0.0
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = MockServletContext.class)
public class BusinessServiceTest {

	@InjectMocks
	BusinessServiceImpl businessService;

	protected ObjectMapper objectMapper;

	@BeforeClass
	static public void setup() {

	}

	@AfterClass
	static public void teardown() {

	}

	@Mock
	private BusinessRepository businessRepository;

	private Business testBusiness;

	@Before
	public void setUpMock() throws Exception {
		MockitoAnnotations.initMocks(this);
		objectMapper = new ObjectMapper();

		Address address_1 = Address.builder().address1("Insys Street").city("Denver").state("CO").zipCode("80014")
				.build();
		Address address_2 = Address.builder().address1("Luxoft Street").city("Seattle").state("WA").zipCode("70014")
				.build();

		testBusiness = BusinessBuilder.buildBusiness("test", "testing-denver", BusinessType.Client)
				.addLocation(address_1).addLocation(address_2).build();

	}

	@Test
	public void testlistBusinesses() throws Exception {
		Mockito.when(businessRepository.findAll()).then(new Answer() {
			@Override
			public List<Business> answer(InvocationOnMock invocation) throws Throwable {

				return Arrays.asList(testBusiness);
			}

		});

		List<Business> businesses = businessService.listBusinesses();

		assertNotNull(businesses);
	}

	@Test
	public void testCreateBusiness() throws Exception {
		Mockito.when(businessRepository.save(any(Business.class))).then(new Answer() {
			@Override
			public Business answer(InvocationOnMock invocation) throws Throwable {

				return testBusiness;
			}

		});

		Business savedBusiness = businessService.createBusiness(testBusiness);

		assertNotNull(savedBusiness);
	}

}
