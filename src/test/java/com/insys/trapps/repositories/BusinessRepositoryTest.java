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
import com.insys.trapps.util.Builder;

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
		Address address_1 = new Address("Insys Street", "Denver", "CO", "80014");
		Address address_2 = new Address("Luxoft Street", "Seattle", "WA", "70014");

		testClient = Builder.buildBusiness("test", "testing-denver",  BusinessType.INSYS).addLocation(address_1).addLocation(address_2).build();
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
