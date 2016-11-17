package com.insys.trapps.repositories;

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
import com.insys.trapps.model.BusinessEntity;
import com.insys.trapps.model.Location;
import com.insys.trapps.respositories.BusinessEntityRepository;
import com.insys.trapps.util.BusinessEntityBuilder;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BusinessEntityRepoTest {

	private static final Logger logger = LoggerFactory.getLogger(BusinessEntityRepoTest.class);

	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private BusinessEntityRepository businessEntityRepository;
	private BusinessEntity testBusinessEntity;

	/*
	 * @Test public void testFindByName() { Address address_1 = new
	 * Address(Long.valueOf(1), "Insys Street", "Denver", "CO", "80014");
	 * Location location_1 = new Location(Long.valueOf(1), address_1);
	 * 
	 * Address address_2 = new Address(Long.valueOf(2), "Luxoft Street",
	 * "Seattle", "WA", "70014"); Location location_2 = new
	 * Location(Long.valueOf(2), address_2); Collection<Location> locations =
	 * Arrays.asList(location_1, location_2);
	 * 
	 * Client client = new Client( "test", "testing-denver business",
	 * BusinessEntityType.CONSULTING_TYPE, locations);
	 * 
	 * entityManager.persist(client); Client clientResult =
	 * clientRepository.findOne(Long.valueOf(1)); assertEquals("test",
	 * clientResult.getName()); }
	 */

	@Before
	public void beforeEachMethod() {

		Address address1 = new Address(Long.valueOf(1), "Insys Street", "Denver", "CO", "80014");
		Location location1 = new Location(Long.valueOf(1), address1);

		Address address2 = new Address(Long.valueOf(2), "Luxoft Street", "Seattle", "WA", "70014");
		Location location2 = new Location(Long.valueOf(2), address2);
		Collection<Location> locations = Arrays.asList(location1, location2);

		BusinessEntity entity = new BusinessEntity("test", "testing-denver business",
				BusinessEntityType.CONSULTING_TYPE, locations);

		testBusinessEntity = BusinessEntityBuilder.buildEntity(entity).build();
	}

	@Test
	public void testSaveBusinessEntity() throws Exception {
		logger.debug("Enter: testSaveBusinessEntity");
		this.businessEntityRepository.save(testBusinessEntity);
		
		Iterable<BusinessEntity> entities = this.businessEntityRepository.findAll();
		entities.forEach(entity -> {
			logger.debug("@@@  Entity is " + entity.toString());
			assertTrue(testBusinessEntity.getName().contains(entity.getName()));
			assertNotNull(entity.getDescription());
		});

		this.businessEntityRepository.delete(testBusinessEntity);
	}

	@Test
	public void testFindBusinessEntity() throws Exception {
		logger.debug("Enter: testFindBusinessEntity");
		this.businessEntityRepository.save(testBusinessEntity);
		
		Iterable<BusinessEntity> entities = this.businessEntityRepository.findAll();
		entities.forEach(entity -> {
			logger.debug("@@@  Entity is " + entity.toString());
			assertTrue(testBusinessEntity.getName().contains(entity.getName()));
			assertNotNull(entity.getDescription());
		});

		this.businessEntityRepository.delete(testBusinessEntity);
	}

}
