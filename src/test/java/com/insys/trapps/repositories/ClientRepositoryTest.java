package com.insys.trapps.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

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
import com.insys.trapps.model.BusinessEntityType;
import com.insys.trapps.model.Client;
import com.insys.trapps.model.Location;
import com.insys.trapps.respositories.ClientRepository;
import com.insys.trapps.util.Builder;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClientRepositoryTest {

	private static final Logger logger = LoggerFactory
			.getLogger(ClientRepositoryTest.class);

	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private ClientRepository clientRepository;
	private Client testClient;

	@Before
	public void beforeEachMethod() {
		Address address_1 = new Address("Insys Street", "Denver", "CO", "80014");
		Address address_2 = new Address("Luxoft Street", "Seattle", "WA", "70014");

		testClient = Builder.buildClient("test", "testing-denver",  BusinessEntityType.CONSULTING_TYPE).addLocation(address_1).addLocation(address_2).build();
	}

	@Test
	public void testFindByName() {
		entityManager.persist(testClient);
		Client clientResult = clientRepository.findOne(Long.valueOf(1));
		logger.debug("@@@  Client is " + clientResult.toString());
		assertEquals("test", clientResult.getName());
	}

	@Test
	public void testSaveClient() throws Exception {
		logger.debug("Enter: testSaveClient");
		this.clientRepository.save(testClient);
		Iterable<Client> clients = this.clientRepository.findAll();
		clients.forEach(client -> {
			logger.debug("@@@  Client is " + client.toString());
			assertTrue(testClient.getName().contains(client.getName()));
			assertNotNull(client.getDescription());
		});

		// this.clientRepository.delete(testClient);
	}

	@Test
	public void testFindClient() throws Exception {
		logger.debug("Enter: testRetrieveClient");
		Iterable<Client> clients = this.clientRepository.findAll();
		clients.forEach(client -> {
			logger.debug("@@@  Client is " + client.toString());
			assertTrue(testClient.getName().contains(client.getName()));
			assertNotNull(client.getDescription());
		});

		// this.clientRepository.delete(testClient);
	}

}
