package com.insys.trapps.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.insys.trapps.model.Address;
import com.insys.trapps.model.Business;
import com.insys.trapps.model.BusinessType;
import com.insys.trapps.respositories.BusinessRepository;
import com.insys.trapps.util.BusinessBuilder;

/**
 * {@link BusinessRepositoryTest} for PersonellServices.
 *
 * @author  Kris Krishna
 * @since 1.0.0
**/

@RunWith(SpringRunner.class)
@DataJpaTest
public class BusinessRepositoryTest {

	private static final Logger logger = LoggerFactory
			.getLogger(BusinessRepositoryTest.class);

	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private BusinessRepository businessRepository;
	private Business testClient;

	@Before
	public void beforeEachMethod() {
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

		testClient = BusinessBuilder.buildBusiness("test", "testing-denver",  BusinessType.INSYS).addLocation(address_1).addLocation(address_2).build();
	}

	@Test
	public void testFindByName() {
		entityManager.persist(testClient);
		Business business = businessRepository.findOne(Long.valueOf(1));
		logger.debug("@@@  Client is " + business.toString());
		assertEquals("test", business.getName());
	}

	@Test
	public void testSaveClient() throws Exception {
		logger.debug("Enter: testSaveClient");
		this.businessRepository.save(testClient);
		Iterable<Business> businesses = this.businessRepository.findAll();
		businesses.forEach(client -> {
			logger.debug("@@@  Client is " + client.toString());
			assertTrue(testClient.getName().contains(client.getName()));
			assertNotNull(client.getDescription());
		});

		// this.clientRepository.delete(testClient);
	}

	@Test
	public void testFindClient() throws Exception {
		logger.debug("Enter: testRetrieveClient");
		Iterable<Business> businesses = this.businessRepository.findAll();
		businesses.forEach(client -> {
			logger.debug("@@@  Client is " + client.toString());
			assertTrue(testClient.getName().contains(client.getName()));
			assertNotNull(client.getDescription());
		});

		// this.clientRepository.delete(testClient);
	}
}
